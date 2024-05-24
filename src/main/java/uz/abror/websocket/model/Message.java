package uz.abror.websocket.model;

import lombok.*;

/**
 * @author Abror
 * @see uz.abror.websocket.model
 * @since 5/22/2024 1:03 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    private String from;

    private String text;

}
