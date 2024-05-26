package uz.abror.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import uz.abror.websocket.model.Message;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/22/2024 1:04 PM
 */

@Slf4j
@Controller
public class WebController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(@Payload Message message) throws Exception {
        log.info("Message method : {}",message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
