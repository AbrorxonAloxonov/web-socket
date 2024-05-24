package uz.abror.websocket.stopm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import uz.abror.websocket.controller.OutputMessage;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/23/2024 2:11 PM
 */

@Slf4j
@Getter
@Component
public class WsResponseSessionHandler implements StompSessionHandler {

    private StompSession session;
    private Integer id;
    private static final String TRAN_ID = "transactionId";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("""
                Connection to STOMP server established.
                Session: {}
                Headers: {}""", session, connectedHeaders);
        this.session = session;
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.warn("""
                Got an exception while handling a frame.
                Command: {}
                Headers: {}
                Payload: {}
                {}""", command, headers, payload, exception.getMessage());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Retrieved a transport session: {}, error: {}", session, exception.getMessage());
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutputMessage.class;
    }

    @SneakyThrows
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Got a new message {}, headers {}", payload, headers);
    }
}