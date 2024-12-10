package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.in.web.data.investmentanalysis.response.InvestmentAnalysisResponse;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentAnalysisUseCase;
import codeping.flex.investment.application.ports.out.InvestmentAnalysisOutPort;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import codeping.flex.investment.global.config.openai.OpenAiConfig;
import codeping.flex.investment.application.openai.prompt.OpenAiPrompts;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;

import java.util.List;
import java.util.Objects;

import static codeping.flex.investment.domain.exception.InvestmentAnalysisErrorCode.*;

@ApplicationService
@RequiredArgsConstructor
public class InvestmentAnalysisService implements InvestmentAnalysisUseCase {

    private static final int MINIMUM_REQUIRED_TRANSCATIONS = 10;

    private final InvestmentAnalysisOutPort investmentAnalysisOutPort;
    private final OpenAiChatModel openAiChatModel;
    private final OpenAiConfig openAiConfig;

    /**
     * OpenAI API 요청을 위한 프롬프트를 생성합니다.
     *
     * @param investments 분석할 투자 내역 JSON 문자열
     * @return OpenAI API 요청을 위한 Prompt 객체
     */
    private Prompt createAnlasisPrompt(String investments) {
        // 투자 내역 분석을 위한 JSON 스키마 설정
        var jsonSchema = openAiConfig.inputConverter().getJsonSchema();

        // 시스템 메시지와 사용자 메시지를 생성
        Message systemMessage = new SystemMessage(OpenAiPrompts.INVESTMENT_ANALYSIS_SYSTEM_MESSAGE);
        Message userMessage = new UserMessage(
                String.format(OpenAiPrompts.INVESTMENT_ANALYSIS_USER_MESSAGE, investments)
        );

        // 프롬프트 생성
        return new Prompt(
                List.of(systemMessage, userMessage), // 시스템 메시지와 사용자 메시지를 조합
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_4_O_MINI) // 모델 설정: GPT-4o-mini
                        .withResponseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, jsonSchema)) // 응답 형식 설정: JSON 스키마
                        .build());
    }

    /**
     * 사용자의 투자 내역을 분석하여 투자 성향과 전략을 제시합니다.
     *
     * @param userId 분석할 투자 내역의 유저 ID
     * @return 투자 내역 분석 결과
     * @throws ApplicationException 투자 내역 조회 실패, 프롬프트 생성 실패, OpenAI API 응답 실패
     */
    @Override
    public InvestmentAnalysisResponse analyzeInvestments(Long userId) {
        try {
            // 거래 건수 확인
            long count = investmentAnalysisOutPort.countInvestmentsByUserId(userId);
            if (count < MINIMUM_REQUIRED_TRANSCATIONS) {
                throw ApplicationException.from(INSUFFICIENT_INVESTMENT_DATA, MINIMUM_REQUIRED_TRANSCATIONS, count);
            }

            // 사용자의 투자 내역 조회
            String investments = investmentAnalysisOutPort.getAllInvestmentsByUserId(userId);
            if (Objects.equals(investments, "[]")) {
                throw ApplicationException.from(GET_INVESTMENT_FAILED);
            }

            Prompt prompt;
            try {
                // OpenAI API 요청을 위한 프롬프트 생성
                prompt = createAnlasisPrompt(investments);
            } catch (Exception e) {
                throw ApplicationException.from(PROMPT_GENERATION_FAILED);
            }

            try {
                // OpenAI API 요청
                ChatResponse response = openAiChatModel.call(prompt);
                String content = response.getResult()
                                         .getOutput()
                                         .getContent();
                return openAiConfig.outputConverter().convert(content);
            } catch (Exception e) {
                throw ApplicationException.from(INVALID_API_RESPONSE);
            }

        } catch (ApplicationException e) {
            throw e;
        }
    }

}
