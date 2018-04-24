package me.efraimgentil.profiler;

import me.efraimgentil.profiler.config.ProfilerProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilerAspect {

    private final ProfilerProperties profilerProperties;
    private final ProfilerLogger profilerLogger;

    @Autowired
    public ProfilerAspect(ProfilerProperties profilerProperties) {
        this.profilerProperties = profilerProperties;
        if(profilerProperties.isPrintToSout()) {
            this.profilerLogger = new SoutLogger();
        }else{
            this.profilerLogger = new Sl4jLogger(LoggerFactory.getLogger(this.getClass()));
        }
    }

    @Around("@annotation(me.efraimgentil.profiler.Profile)")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long beforeStart = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        profilerLogger.log(beforeStart, joinPoint);

        return proceed;
    }


    abstract class ProfilerLogger{
        abstract void log(long beforeStartTime , ProceedingJoinPoint joinPoint );
    }

    class SoutLogger extends ProfilerLogger{
        @Override
        public void log(long beforeStartTime, ProceedingJoinPoint joinPoint) {
            System.out.println(String.format("Took %d milliseconds to execute", (System.currentTimeMillis() - beforeStartTime)));
        }
    }

    class Sl4jLogger extends ProfilerLogger{

        final Logger logger;

        public Sl4jLogger(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void log(long beforeStartTime, ProceedingJoinPoint joinPoint) {
            logger.info(String.format("Took %d milliseconds to execute", (System.currentTimeMillis() - beforeStartTime)));
        }
    }

}
