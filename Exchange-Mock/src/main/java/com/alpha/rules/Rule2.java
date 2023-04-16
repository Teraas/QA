package com.alpha.rules;

import com.alpha.entity.Request;
import com.alpha.entity.Response;
import com.alpha.enums.ChildResponseType;
import com.alpha.enums.ResponseType;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
public class Rule2 implements IRule {
    int x = 5;
    /**
     * Rule business logic:
     *  If the qty of an order is multiple of x then generate NEW_ORDER_CONFIRM otherwise reject
     *  Creating a unique rule for the number x. If its dynamic and requires many rules, it could be passed as param.
     *  This sample rule assumes x is not changing frequently.
     * @param request
     * @return
     */
    public Response executeRule(Request request) {

        Response response = new Response();
        if(request.getQuantity() % x == 0){
            response.setResponseType(ResponseType.NEW_ORDER_CONFIRM);
            response.setErrorCode("1");// TODO - use enum
            response.setChildResponseType(ChildResponseType.NULL_RESPONSE_MIDDLE);
        }
        else {
            response.setResponseType(ResponseType.REJECT);
            response.setErrorCode("100109");// TODO - use enum
            response.setChildResponseType(ChildResponseType.CANCEL_ORDER_REJECT_MIDDLE);
        }
        response.setOrderId(request.getOrderId());
        response.setSymbol(request.getSymbol());
        response.setSide(request.getSide());
        response.setPrice(request.getPrice());
        response.setQuantity(request.getQuantity());
        response.setAccount(request.getAccount());

        response.setTimestamp(request.getTimeStamp());
        response.setExchangeOrderId(13007306);// TODO - generate random number if adds any value in comparison
        response.setDuration(request.getDuration());
        response.setExchTs(request.getTimeStamp()+1000);// using random ts for exchange
        System.out.println("Executed Rule2");

        return response;
    }
}
