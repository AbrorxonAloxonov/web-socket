package uz.abror.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @author Abror
 * @see uz.abror.websocket.handler
 * @since 5/22/2024 4:50 PM
 */
@Slf4j
@Component
public class SimpMessageChannel implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("I'm handle message : {}", message);
    }

}
