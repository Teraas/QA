package com.alpha.utils;

import com.alpha.entity.Response;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * @created 16/04/23
 */
public class FileUtil {
    String responseFields = "ResponseType,OrderID,Symbol,Side,Price,Quantity,AccountID,ErrorCode,TimeStamp,Exchange_Order_Id,ChildResponseType,Duration,ExchTs";
    public void convertResponseToCSV(List<Response> list){
        try{
        Writer writer = new FileWriter("response.csv"); // create file to home location
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(responseFields.split(","));
        for(Response resp : list){
            String[] row = new String[]{resp.getResponseType().toString(), String.valueOf(resp.getOrderId()),resp.getSymbol(),
                    resp.getSide(), resp.getPrice().toString(), String.valueOf(resp.getQuantity()), resp.getAccount(),
                    resp.getErrorCode(), String.valueOf(resp.getTimestamp()), String.valueOf(resp.getExchangeOrderId()),
                    resp.getChildResponseType().toString(),resp.getDuration().toString(), String.valueOf(resp.getExchTs())};
            csvWriter.writeNext(row);

        }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e){
            System.out.println("[CSV write Exception ] " + e.getMessage());
        }




    }

    public List<String[]> readCSV(String path){
        List<String[]> rowStrings = new ArrayList<>();
        FileReader reader;
        try {
            reader = new FileReader(path);
            CSVReader csvReader = new CSVReader(reader);
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                rowStrings.add(nextLine);
            }

        } catch(CsvValidationException | IOException e){
            System.out.println("[File Reader Exception ] " + e.getMessage());
        } catch(Exception ee){
            System.out.println("[File Reader Exception ] " + ee.getMessage());
        }
        return rowStrings;

    }
}
