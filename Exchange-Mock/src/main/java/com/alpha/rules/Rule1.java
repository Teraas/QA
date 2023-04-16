package com.alpha.rules;

import com.alpha.entity.Request;
import com.alpha.entity.Response;
import com.alpha.enums.ChildResponseType;
import com.alpha.enums.ResponseType;

import java.math.BigDecimal;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
public class Rule1 implements IRule {
    /**
     * Rule business logic:
     * If price is 123 then generate reject
     * @param request
     * @return
     */
    public Response executeRule(Request request) {
        Response response = new Response();
        if(request.getPrice().compareTo(new BigDecimal("123"))==1){
            response.setResponseType(ResponseType.REJECT);
            response.setErrorCode("100109");// TODO - use enum
            response.setChildResponseType(ChildResponseType.CANCEL_ORDER_REJECT_MIDDLE);

        }
        else {
            response.setResponseType(ResponseType.NEW_ORDER_CONFIRM);
            response.setErrorCode("1");// TODO - use enum
            response.setChildResponseType(ChildResponseType.NULL_RESPONSE_MIDDLE);
        }
        response.setOrderId(request.getOrderId());
        response.setSymbol(request.getSymbol());
        response.setSide(request.getSide());
        response.setPrice(request.getPrice());
        response.setQuantity(request.getQuantity());
        response.setAccount(request.getAccount());

        response.setTimestamp(request.getTimeStamp());
        response.setExchangeOrderId(130073061);// TODO - generate random number if adds any value in comparison
        response.setDuration(request.getDuration());
        response.setExchTs(request.getTimeStamp()+1000);// using random ts for exchange
        System.out.println("Executed Rule1");

        return response;
    }
}
