package uz.abror.websocket.stopm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.stereotype.Component;
import uz.abror.websocket.model.Message;

import java.lang.reflect.Type;
/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/23/2024 2:14 PM
 */
@Slf4j
@Component
public class SimpleStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Got a new message {}", payload);
    }

}
