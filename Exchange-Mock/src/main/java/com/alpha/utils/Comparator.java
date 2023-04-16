package com.alpha.utils;

import com.alpha.entity.Response;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author harish.kumar-mbp
 * @created 16/04/23
 */
public class Comparator {

    public boolean compare(String filePathExpected, String filePathActual) {
        FileUtil fileUtil = new FileUtil();

        List<String[]> responseStrings1 = fileUtil.readCSV(filePathExpected);
        List<String[]> responseStrings2 = fileUtil.readCSV(filePathActual);
        responseStrings1.remove(0);// removing headers before comparison
        responseStrings2.remove(0);// removing headers before comparison
        if(responseStrings1.size() != responseStrings2.size()) {
            System.out.println("Comparison failed due to size mismatch");
            return false;
        }
        return compare(responseStrings1, responseStrings2);


    }

    private boolean compare(List<String[]> responseStringsExp, List<String[]> responseStringsAct) {
        // here size is same. Now, compare for a unique Exch_Order_Id row
        // read and get unique Exch_Order_Ids from expected
        Map<String, String[]> expectedMap = new HashMap();
        Map<String, Boolean> resultMap = new HashMap();
        boolean res = true;
        for(String[] row : responseStringsExp){
            expectedMap.put(row[9], row);
        }
        for(String[] row : responseStringsAct){
            res = compare(expectedMap.get(row[9]),row); // expectedmap could return null if actual Exch_Order_Id not found
            resultMap.put(row[9],res);
        }
        // compare if all responses matched
        for(String key : resultMap.keySet()){
            if(!resultMap.get(key))
                res = false;
        }
        return res;
    }

    private boolean compare(String[] expected, String[] actual) {
        List<String> match = new ArrayList<>();
        if(actual == null && actual !=null) // No need to handle actual/expected both null
            return false;
        // compare all values even if mismatch found before last value
        for(int i =0;i< expected.length;i++){
            if(expected[i].equals(actual[i])){
                match.add("Matched");
            }
            else
                match.add("[ NotMatched] " + "expected => " + expected[i] + " but found => " + actual[i]);

        }
        for(String s: match){
            if(!s.equals("Matched")){
                System.out.println("Compare Result - > " + match);
                return false;
            }

        }
        return true;
    }
}
