package cn.xpeanut.monitor.sdk.model;

import java.util.List;

public class LogMessage {

    private String systemMessage;

    private String className;

    private String methodName;

    private List<String> logList;

    public LogMessage() {
    }

    public LogMessage(String systemMessage, String className, String methodName, List<String> logList) {
        this.systemMessage = systemMessage;
        this.className = className;
        this.methodName = methodName;
        this.logList = logList;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getLogList() {
        return logList;
    }
}
