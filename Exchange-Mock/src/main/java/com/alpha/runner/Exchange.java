package com.alpha.runner;

import com.alpha.entity.Request;
import com.alpha.entity.Response;
import com.alpha.rules.IRule;
import com.alpha.utils.Comparator;
import com.alpha.utils.FileUtil;
import com.alpha.utils.RequestParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
public class Exchange {
    List<IRule> rules;
    Request request;

    public Exchange(Request request, String rules) {

        this.request = request;
        this.rules = new ArrayList<>();
        setRules(rules);// rule names can be passed from properties file or from any client/test class
    }

    private void setRules(String rules) {
        String[] ruleNames = rules.split(",");
        for(String ruleName : ruleNames){
            try{
                this.rules.add((IRule) Class.forName("com.alpha.rules." + ruleName.replaceAll("\\s", "")).getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException e){
                System.out.println("[ Exchange Rule Exception ] " + e.getMessage());
            } catch (NoSuchMethodException e){
                System.out.println("[ Exchange Rule Exception ] " + e.getMessage());
            } catch (Exception e){
                System.out.println("[ Exchange Rule Exception ] " + e.getMessage());
            }

        }


    }

    public List<Response> executeOrder(){
        List<Response> responses = new ArrayList<Response>();
        for(IRule rule : rules){
            responses.add(rule.executeRule(request));
        }
        System.out.println("Order is executed");
        return responses;
    }

    // verify exchange
    public static void main(String[] args){
        RequestParser parser = new RequestParser("src/test/resources/request1.txt");
        System.out.println(parser.getParsedRequest());
        Exchange exch = new Exchange(parser.getParsedRequest(), "Rule1,Rule2");
        List<Response> responses = exch.executeOrder();
        System.out.println(responses);
        FileUtil fileUtil = new FileUtil();
        fileUtil.convertResponseToCSV(responses);
        Comparator comparator = new Comparator();
        comparator.compare("response.csv","response2.csv");
    }
}
