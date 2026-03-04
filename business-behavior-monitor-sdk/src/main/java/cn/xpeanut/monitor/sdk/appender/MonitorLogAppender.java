package cn.xpeanut.monitor.sdk.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import cn.xpeanut.monitor.sdk.model.LogMessage;
import cn.xpeanut.monitor.sdk.push.IPush;
import cn.xpeanut.monitor.sdk.push.impl.RedisPush;

import java.util.Arrays;

/**
 * 监控日志采集
 *
 * @author: zhen.li
 * @date: 2026/3/3 15:14
 */
public class MonitorLogAppender<E> extends AppenderBase<E> {

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 只采集指定范围的日志
     */
    private String groupId;

    /**
     * redis 连接地址
     */
    private String host;

    /**
     * redis 端口
     */
    private Integer port;

    private final IPush push = new RedisPush();

    @Override
    protected void append(E eventObject) {
        // 开启推送
        push.open(host, port);

        // 获取日志信息
        if (eventObject instanceof ILoggingEvent event) {
            String methodName = "unknown";
            String className = "unknown";

            StackTraceElement[] callerDataArray = event.getCallerData();
            if (null != callerDataArray && callerDataArray.length > 0) {
                StackTraceElement callerData = callerDataArray[0];
                methodName = callerData.getMethodName();
                className = callerData.getClassName();
            }

            if (!className.startsWith(groupId)) {
                return;
            }

            // 构建日志信息
            LogMessage logMessage = new LogMessage(systemName, className, methodName, Arrays.asList(event.getFormattedMessage().split(" ")));

            // 推送日志信息
            push.send(logMessage);
        }
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
