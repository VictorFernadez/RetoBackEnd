package com.victor.starter.autoconfigure;


import com.victor.starter.aop.TimeLoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


@Configuration
@ConditionalOnClass(TimeLoggingAspect.class)
@ConditionalOnProperty(name = "logging.api.enabled")
@EnableConfigurationProperties(LoggingProperties.class)
public class TimeLoggingAutoconfigure {

    private final LoggingProperties properties;

    public TimeLoggingAutoconfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public TimeLoggingAspect loggableAspect(){
        return new TimeLoggingAspect(properties.getLoggerName());
    }
}
