package me.efraimgentil.profiler.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("me.efraimgentil.profiler")
@EnableConfigurationProperties(ProfilerProperties.class)
public class ProfilerAutoConfiguration {
}
