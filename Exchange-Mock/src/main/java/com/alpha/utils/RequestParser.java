package com.alpha.utils;

import com.alpha.entity.Request;
import com.alpha.enums.Duration;
import com.alpha.enums.OrderType;
import com.alpha.enums.RequestType;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
public class RequestParser {
    String requestFileName;

    public RequestParser(String requestFileName) {
        this.requestFileName = requestFileName;


    }
    public Request getParsedRequest(){
        Request request = new Request();
        try{
            File file = new File(requestFileName);
            Scanner input = new Scanner(file);
            input.useDelimiter("-|\n");
            // read request data
            // TODO - logger

            String requestString = input.next().trim();
            String[] requestArray = requestString.split("\\|");
            request.setRequestType(RequestType.valueOf(requestArray[0].split(":")[1]));
            String orderIdStr = requestArray[1].split(":")[1];
            long orderId = Long.parseLong(requestArray[1].split(":")[1]);
            request.setOrderId(orderId);
            request.setToken(requestArray[2].split(":")[1]);
            request.setSymbol(requestArray[3].split(":")[1]);
            request.setSide(requestArray[4].split(":")[1]);
            BigDecimal price = new BigDecimal(requestArray[5].split(":")[1]);// TODO - verify the decimals
            request.setPrice(price);
            int quantity = Integer.parseInt(requestArray[6].split(":")[1]);
            request.setQuantity(quantity);
            int quantityFilled = Integer.parseInt(requestArray[7].split(":")[1]);
            request.setQuantityFilled(quantityFilled);
            int disclosedQnty = Integer.parseInt(requestArray[8].split(":")[1]);
            request.setDisclosedQnty(disclosedQnty);
            long timestamp = Long.parseLong(requestArray[9].split(":")[1]);
            request.setTimeStamp(timestamp);
            request.setDuration(Duration.valueOf(requestArray[10].split(":")[1]));
            request.setOrderType(OrderType.valueOf(requestArray[11].split(":")[1]));
            request.setAccount(requestArray[12].split(":")[1]);
            int exchange = Integer.parseInt(requestArray[13].split(":")[1]);
            request.setExchange(exchange);
            int numCopies = Integer.parseInt(requestArray[14].split(":")[1]);
            request.setNumCopies(numCopies);
        } catch(IOException e){
            System.out.println("[RequestParser IOException] " + e.getMessage());
        } catch(Exception e){
            System.out.println("[RequestParser Exception] " + e.getMessage());
        }
        return request;
    }
    // verify parser
    public static void main(String[] args){
        RequestParser parser = new RequestParser("src/test/resources/request1.txt");
        System.out.println(parser.getParsedRequest());
    }
}


