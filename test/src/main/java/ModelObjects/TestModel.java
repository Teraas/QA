package ModelObjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestModel {
    public static void main(String[] args) throws JsonProcessingException {
        Metric m = new Metric();
        m.setIntervalMs(111);
        m.setValue(1);
        m.setName("Test INT");
        m.setType("count");
        m.setTimestamp(213132323);
        MetricAttributes mAtr = new MetricAttributes();
        mAtr.setStatus("Success");
        mAtr.setEnvironment("Staging");
        mAtr.setInterface("gRPC");
        mAtr.setServiceName("account");
        mAtr.setPriority("1");
        m.setMetricAttributes(mAtr);
        //ObjectMapper mapper = new ObjectMapper();
        List<Metric> list = new ArrayList<>();
        list.add(m);
        list.add(m);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
    }

    private void addResultNR(String testCasePriority, String assignCategory, Integer status, long timestamp){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("Environment", "env");
        resultMap.put("Priority", testCasePriority);
        resultMap.put("ServiceName", assignCategory);
        resultMap.put("BuildJobName", "");
        resultMap.put("Status", status);
        resultMap.put("TestMode", "");
        resultMap.put("ToxicType", "");
        resultMap.put("DependencyType", "");
        resultMap.put("Timestamp", timestamp);
        resultMap.put("TestType", "");
        //this.resultMapList.add(resultMap);
        Metric m = new Metric();
        m.setIntervalMs(111);
        m.setValue(1);
        m.setName("Test INT");
        m.setType("count");
        m.setTimestamp(timestamp);
        m.setMetricAttributes( new MetricAttributes());
    }

//    public void addToListMetric(){
//        //MetricList mList = new MetricList(); Not needed to create a separate class.
//        List<Metric> list = new ArrayList<>();
//        if(isMetricInList()){ //Implement this method, parse thru the list
//            Metirc m = getMetric();
//            m.setCount(m.count +1)
//        }
//        else
//            Metric m = createMetric();
//            list.add(m);
//
//    }
}
