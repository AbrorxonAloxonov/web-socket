package uz.abror.websocket.controller;

import lombok.*;
import org.springframework.stereotype.Service;

/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/22/2024 1:07 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class OutputMessage {

    private String from;
    private String text;
    private String time;

}
