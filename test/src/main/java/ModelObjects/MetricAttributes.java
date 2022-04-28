package ModelObjects;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricAttributes {
    public String status;
    public String environment;
    public String serviceName;
    public String priority;
    public String buildJobName;
    public String testMode;
    public String toxicType;
    public String dependencyType;
    @JsonProperty("interface")
    public String myInterface;
    public String testType;

    public String getInterface() {
        return myInterface;
    }
    public void setInterface(String myInterface) {
        this.myInterface = myInterface;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBuildJobName() {
        return buildJobName;
    }
    public void setBuildJobName(String buildJobName) {
        this.buildJobName = buildJobName;
    }
    public String getTestMode() {
        return testMode;
    }
    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }
    public String getToxicType() {
        return toxicType;
    }
    public void setToxicType(String toxicType) {
        this.toxicType = toxicType;
    }
    public String getDependencyType() {
        return dependencyType;
    }
    public void setDependencyType(String dependencyType) {
        this.dependencyType = dependencyType;
    }
    public String getTestType() {
        return testType;
    }
    public void setTestType(String testType) {
        this.testType = testType;
    }

    /*
     * set metric attribute based on execution type
     */
//    public MetricAttributes addMetricAttribute(Map<String, Object> result, String type, String status){
//        // Map<String, Object> metricsParam = new HashMap<String, Object>();
//        MetricAttributes metricAttributes = new MetricAttributes();
//        metricAttributes.setEnvironment(result.get("Environment").toString());
//        metricAttributes.setServiceName(result.get("ServiceName").toString());
//        metricAttributes.setPriority(result.get("Priority").toString());
//        metricAttributes.setInterface(result.get("TestMode").toString());
//        metricAttributes.setStatus(status);
//        switch (type) {
//            case "PPT" :
//                metricAttributes.setBuildJobName(result.get("BuildJobName").toString());
//            case "chaos" :
//                metricAttributes.setBuildJobName(result.get("BuildJobName").toString());
//                metricAttributes.setToxicType(result.get("ToxicType").toString());
//                metricAttributes.setDependencyType(result.get("DependencyType").toString());
//        }
//        return metricAttributes;
//    };
}
