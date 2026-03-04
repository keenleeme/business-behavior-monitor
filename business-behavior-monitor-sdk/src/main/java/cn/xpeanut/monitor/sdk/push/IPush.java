package cn.xpeanut.monitor.sdk.push;

import cn.xpeanut.monitor.sdk.model.LogMessage;

public interface IPush {

    void open(String host, Integer port);

    void send(LogMessage logMessage);

}
