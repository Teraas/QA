import com.alpha.entity.Request;
import com.alpha.entity.Response;
import com.alpha.runner.Exchange;
import com.alpha.utils.Comparator;
import com.alpha.utils.FileUtil;
import com.alpha.utils.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;

/**
 * @author harish.kumar-mbp
 * @created 16/04/23
 */
public class Test {
    Request request;
    Exchange exchange;
    @Before
    public void setData(){
        RequestParser parser = new RequestParser("src/test/resources/request1.txt");
        request = parser.getParsedRequest();

    }
    @org.junit.Test
    public void verifyOrderExecuted(){
        exchange = new Exchange(request,"Rule1,Rule2");
        List<Response> responses = exchange.executeOrder();
        System.out.println(responses);
        Assert.assertTrue("Response is not empty", !responses.isEmpty());
        FileUtil fileUtil = new FileUtil();
        //fileUtil.convertResponseToCSV(responses);
//        Comparator comparator = new Comparator();
//        comparator.compare("response.csv","response2.csv");
    }
    @org.junit.Test
    public void compareOrderExecutedNotMatch(){
        exchange = new Exchange(request,"Rule1,Rule2");
        List<Response> responses = exchange.executeOrder();
        System.out.println(responses);

        FileUtil fileUtil = new FileUtil();
        fileUtil.convertResponseToCSV(responses); // Made this explicit rather than implicit, to give more flexibility to assert values of responses.
        Comparator comparator = new Comparator();

        Assert.assertTrue("Response is not matching", comparator.compare("response.csv","response2.csv"));
    }

    @org.junit.Test
    public void compareOrderExecutedMatch(){
        exchange = new Exchange(request,"Rule1,Rule2");
        List<Response> responses = exchange.executeOrder();
        System.out.println(responses);

        FileUtil fileUtil = new FileUtil();
        fileUtil.convertResponseToCSV(responses); // Made this explicit rather than implicit, to give more flexibility to assert values of responses.
        Comparator comparator = new Comparator();

        Assert.assertTrue("Response is not matching", comparator.compare("response.csv","responsePositive.csv"));
    }
}
