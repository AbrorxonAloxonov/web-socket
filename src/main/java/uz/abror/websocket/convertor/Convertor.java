package uz.abror.websocket.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Abror
 * @see uz.abror.websocket.convertor
 * @since 5/24/2024 3:43 PM
 */

@Component
@Slf4j
public class Convertor implements MessageConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object fromMessage(org.springframework.messaging.Message<?> message, Class<?> targetClass) {
        if (targetClass == Message.class) {
            try {
                String json = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
                return objectMapper.readValue(json, Message.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert JSON message to Message object", e);
            }
        }
        return null;
    }

    @Override
    public org.springframework.messaging.Message<?> toMessage(Object payload, MessageHeaders headers) {
        if (payload instanceof Message) {
            try {
                String json = objectMapper.writeValueAsString(payload);
                return MessageBuilder.createMessage(json.getBytes(StandardCharsets.UTF_8), headers);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert Message object to JSON message", e);
            }
        }
        return null;
    }
}
