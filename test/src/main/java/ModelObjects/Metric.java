package ModelObjects;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {

    @JsonProperty("interval.ms")
    public int intervalMs;
    public String name;
    @JsonProperty("attributes")
    public MetricAttributes metricAttributes;
    public String type;
    public int value;
    public long timestamp;


    public int getIntervalMs() {
        return intervalMs;
    }
    public void setIntervalMs(int intervalMs) {
        this.intervalMs = intervalMs;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public MetricAttributes getMetricAttributes(){
        return metricAttributes;
    }
    public void setMetricAttributes(MetricAttributes metricAttributes){
        this.metricAttributes = metricAttributes;

    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /*
     * Convert test result that is groupped based on service name to new relic metrics
     */
//    public MetricList buildMetricFromServiceList(List<Map<String, Object>> serviceList){
//        MetricList metricList = new MetricList();
//        MetricAttributes metricAttributes = new MetricAttributes();
//        for(Map service : serviceList){
//            String type = (String) service.get("TestType");
//            String[] statusList = {"Success", "Failed"};
//            for(String status : statusList){
//                Metric metric = new Metric();
//                if(!service.get(status).equals(0)){
//                    metric.setName("test." + type);
//                    metric.setType("count");
//                    metric.setValue((int) service.get(status));
//                    metric.setTimestamp((long) service.get("Timestamp"));
//                    metric.setIntervalMs(1000);
//                    metric.setMetricAttributes(metricAttributes.addMetricAttribute(service, type, status));
//                    metricList.addMetrics(metric);
//                }
//            }
//        }
//        return metricList;
//    }

}
