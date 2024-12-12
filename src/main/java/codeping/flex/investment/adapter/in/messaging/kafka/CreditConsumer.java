package codeping.flex.investment.adapter.in.messaging.kafka;

import codeping.flex.investment.application.ports.in.investment.domain.CreditUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CreditConsumer {

    private final CreditUseCase creditUseCase;

    @KafkaListener(topics = "flex_mysql.flex_user.user", groupId = "credit-signup")
    public void handleSignUpEvent(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) throws IOException {
        String message = record.value();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(message);
        JsonNode payloadNode = rootNode.path("payload");
        long userId = payloadNode.path("user_id").asLong();

        creditUseCase.handleCreditSignUp(userId, 1000000);

        acknowledgment.acknowledge();
    }
}
