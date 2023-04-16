package com.alpha.entity;

import com.alpha.enums.ChildResponseType;
import com.alpha.enums.Duration;
import com.alpha.enums.ResponseType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
@Data
@NoArgsConstructor
public class Response {
    ResponseType responseType;
    long orderId;
    String symbol;
    String side;
    BigDecimal price;
    int quantity;
    String account;
    String errorCode;
    long timestamp;
    long exchangeOrderId;
    ChildResponseType childResponseType;
    Duration duration;
    long exchTs;
}
