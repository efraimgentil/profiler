package me.efraimgentil.profiler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "profiler")
public class ProfilerProperties implements Serializable {

    private boolean printToSout;
    private boolean logParameters;

    @Override
    public String toString() {
        return "ProfilerProperties{" +
                "printToSout=" + printToSout +
                ", logParameters=" + logParameters +
                '}';
    }

    public boolean isPrintToSout() {
        return printToSout;
    }

    public void setPrintToSout(boolean printToSout) {
        this.printToSout = printToSout;
    }

    public boolean isLogParameters() {
        return logParameters;
    }

    public void setLogParameters(boolean logParameters) {
        this.logParameters = logParameters;
    }
}
