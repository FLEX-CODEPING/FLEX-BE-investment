package codeping.flex.investment.application.openai.prompt;

public final class OpenAiPrompts {
    private OpenAiPrompts() {
        throw new IllegalStateException("Utility class");
    }


    public static final String INVESTMENT_ANALYSIS_SYSTEM_MESSAGE = """
            당신은 전문 투자 분석가입니다. 투자자의 거래 데이터를 바탕으로 투자 특성을 분석하고 명확하고 체계적인 분석 결과를 제공합니다.
            
            입력 데이터 형식:
            ```
            투자자 거래내역:
            - 종목정보: stockCode, corpName
            - 거래정보: investType, quantity, price, totalPrice
            - 실적정보: profit
            - 시간정보: createdAt
            주식시장 데이터:
            - 종목정보: stockCode, corpName
            - 가격정보: curPrice, priceChange, priceChangeSign, priceChangeRate
            - 거래정보: accVolume
            - 고가정보: highPrice, highPriceTime, highPriceDate
            - 저가정보: lowPrice, lowPriceTime, lowPriceDate
            - 비율정보: lowPriceToCurRate, closingPriceToCurRate
            - 연속정보: continuousRiseDays, highPriceToCurRate, continuousFallDays
            - 시가정보: openPriceChangeSign, openPriceChange, openPriceChangeRate
            - 기간정보: periodChange, periodChangeRate
            - 기타정보: listedShares, marketCap, marketRatio
            ```
            
            응답 형식:
            ```
            {
              "investmentStyle": [
                "1. {내용}",
                "2. {내용}",
                "3. {내용}"
              ],
              "investmentStrategy": [
                "1. {내용}",
                "2. {내용}",
                "3. {내용}"
              ],
              "investmentTrend": [
                "1. {내용}",
                "2. {내용}",
                "3. {내용}"
              ]
            }
            ```
            
            응답 예시:
            {
              "investmentStyle": [
                "1. 안정성 추구: 이 세 기업은 각 분야에서 선도적인 위치를 차지하고 있는 대기업으로, 상대적으로 안정적인 투자를 선호하는 성향을 보입니다.",
                "2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.",
                "3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다."
              ],
              "investmentStrategy": [
                "1. 분산 투자: 세 기업에 골고루 투자하여 리스크를 분산시킵니다.",
                "2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.",
                "3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다."
              ],
              "investmentTrend": [
                "1. 안정성 추구: 이 세 기업은 각 분야에서 선도적인 위치를 차지하고 있는 대기업으로, 상대적으로 안정적인 투자를 선호하는 성향을 보입니다.",
                "2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.",
                "3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다."
              ]
            }
            ```
            
            **제한사항**:
            1. 주어진 데이터 외에 근거 없는 가정이나 추론은 하지 마세요.
            2. 특정 종목 추천이나 향후 주가 예측은 포함하지 마세요.
            3. 투자 조언은 구체적이고 실행 가능한 방식으로 제시하세요.
            4. 리스크 관리 방안을 투자 전략에 반드시 포함하세요.
            5. 결과는 한국어로 작성하며 명확한 구조를 유지하세요.
            6. 반드시 응답 형식에 맞게 결과를 반환하고 응답 예시를 단순 참고하세요. 응답 형식에 없는 항목은 절대 기입하지 마시오.
            """;

    public static final String INVESTMENT_ANALYSIS_USER_MESSAGE = """
            {
              "investmentHistory": %s,
              "fluctuationRanking": %s,
              "volumeRanking": %s,
              "marketCapRanking": %s
            }
            
            추가 지시사항:
            1. 예시처럼 깔끔한 목록 형식(1, 2, 3)으로 답변하세요.
            2. 분석 내용은 간결하면서도 핵심적인 정보를 전달하세요.
            """;

    public static final String INVESTMENT_HISTORY_FORMAT = """
            {
              "stockCode": "%s",
              "corpName": "%s",
              "investType": "%s",
              "quantity": %s,
              "price": %s,
              "totalPrice": %s,
              "profit": %s,
              "createdAt": "%s"
            }""";

    public static final String FLUCTUATION_RANKING_FORMAT = """
            {
              "stockCode": "%s",
              "dataRank": "%s",
              "stockName": "%s",
              "curPrice": "%s",
              "priceChange": "%s",
              "priceChangeSign": "%s",
              "priceChangeRate": "%s",
              "accVolume": "%s",
              "highPrice": "%s",
              "highPriceTime": "%s",
              "highPriceDate": "%s",
              "lowPrice": "%s",
              "lowPriceTime": "%s",
              "lowPriceDate": "%s",
              "lowPriceToCurRate": "%s",
              "closingPriceToCurRate": "%s",
              "continuousRiseDays": "%s",
              "highPriceToCurRate": "%s",
              "continuousFallDays": "%s",
              "openPriceChangeSign": "%s",
              "openPriceChange": "%s",
              "openPriceChangeRate": "%s",
              "periodChange": "%s",
              "periodChangeRate": "%s"
            }""";

    public static final String VOLUME_RANKING_FORMAT = """
            {
              "corpName": "%s",
              "stockCode": "%s",
              "ranking": "%s",
              "curPrice": "%s",
              "priceChangeSign": "%s",
              "priceChange": "%s",
              "priceChangeRate": "%s",
              "accTradingVolume": "%s",
              "preDayTradingVolume": "%s",
              "listedShares": "%s",
              "avgTradingVolume": "%s",
              "prevPeriodPriceChangeRate": "%s",
              "volIncreaseRate": "%s",
              "volTurnoverRate": "%s",
              "periodVolTurnoverRate": "%s",
              "avgTradingValue": "%s",
              "accTradingValue": "%s"
            }""";

    public static final String MARKET_CAP_RANKING_FORMAT = """
            {
              "stockCode": "%s",
              "ranking": "%s",
              "corpName": "%s",
              "curPrice": "%s",
              "priceChange": "%s",
              "priceChangeSign": "%s",
              "priceChangeRate": "%s",
              "accTradingVol": "%s",
              "listedShares": "%s",
              "marketCap": "%s",
              "marketRatio": "%s"
            }""";
}