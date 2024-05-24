package uz.abror.websocket.stopm;

import lombok.*;

/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/23/2024 2:13 PM
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsDTO {

    private WsMethods method;

    private String id;
}