package uz.abror.websocket.stopm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Abror
 * @see uz.abror.websocket.controller
 * @since 5/23/2024 2:13 PM
 */
@Getter
@RequiredArgsConstructor
public enum WsMethods {

    OPEN_CONNECTION,
    METER_VALUES,
    STATUS_CHANGED_OBJECTS,
    SEARCH_NEARBY_CHARGE_BOXES,
    START_TRANSACTION,
    START_TRANSACTION_SIMULATE,
    STOP_TRANSACTION,
    STOP_TRANSACTION_SIMULATE
}