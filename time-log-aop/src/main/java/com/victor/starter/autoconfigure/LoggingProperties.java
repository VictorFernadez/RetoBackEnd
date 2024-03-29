package com.victor.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("logging.method.exec")
public class LoggingProperties {

    private String loggerName = "AuditLogger";

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
