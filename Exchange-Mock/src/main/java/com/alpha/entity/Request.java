package com.alpha.entity;

import com.alpha.enums.Duration;
import com.alpha.enums.OrderType;
import com.alpha.enums.RequestType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
@Data
@NoArgsConstructor
public class Request {
    RequestType RequestType; // use enum if more than 1 type of requestType. Currently assuming only 1 type.
    long orderId;
    String token;
    String symbol;
    String side;
    BigDecimal price;// need to support >17 decimal. But double supports 16 decimal.
    int quantity;
    int quantityFilled;
    int disclosedQnty;
    long timeStamp; // considering enough atleast till 2200 years
    Duration duration;
    OrderType orderType;
    String account;
    int exchange;
    int numCopies;

}
