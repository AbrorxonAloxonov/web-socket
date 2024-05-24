package uz.abror.websocket.stopm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import uz.abror.websocket.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/23/2024 2:10 PM
 */
@Slf4j
@Service
public class StompController implements ApplicationRunner {

    private static final String URL = "ws://localhost:8080/chat";
    private static final String SEND = "/app/chat";


    public void connectSocket() throws ExecutionException, InterruptedException, TimeoutException {
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        WebSocketClient webSocketClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        SimpleStompSessionHandler simpleStompSessionHandler = new SimpleStompSessionHandler();


        StompSession connectAsync = stompClient.connectAsync(URL, simpleStompSessionHandler)
                .get(5, TimeUnit.SECONDS);

        subscribeAfterConnected(connectAsync);
        sendSimpleUrl(connectAsync);
        Thread.sleep(6000);

        log.info("disconnect after 3 second!");
    }

    public void sendSimpleUrl(StompSession stompSession) {
        stompSession.send(SEND, new Message("Abror", "Hello World"));
    }

    private void subscribeAfterConnected(StompSession stompSession) {
        log.info("Start Subscribe topics");
        stompSession.subscribe("/topic/messages", new WsResponseSessionHandler());
        log.info("End Subscribe topics");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        connectSocket();
    }
}