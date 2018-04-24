package me.efraimgentil.profiler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "profiler")
public class ProfilerProperties implements Serializable {

    private boolean printToSout;
    private boolean logProperties;

    @Override
    public String toString() {
        return "ProfilerProperties{" +
                "printToSout=" + printToSout +
                ", logProperties=" + logProperties +
                '}';
    }

    public boolean isPrintToSout() {
        return printToSout;
    }

    public void setPrintToSout(boolean printToSout) {
        this.printToSout = printToSout;
    }

    public boolean isLogProperties() {
        return logProperties;
    }

    public void setLogProperties(boolean logProperties) {
        this.logProperties = logProperties;
    }
}
