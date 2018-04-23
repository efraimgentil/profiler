package me.efraimgentil.profiler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("me.efraimgentil.profiler")
public class ProfilerAutoConfiguration {
}
