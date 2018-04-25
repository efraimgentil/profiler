package me.efraimgentil.profiler;

import me.efraimgentil.profiler.config.ProfilerProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProfilerAspect {

    private final ProfilerProperties profilerProperties;
    private final ProfilerLogger profilerLogger;

    private final String messageTemplate = "Took %d milliseconds to execute the method %s";

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

        if(profilerProperties.isLogProperties()){
            profilerLogger.logParameters(joinPoint);
        }

        Object proceed = joinPoint.proceed();

        profilerLogger.logExecutiontime(beforeStart, joinPoint);

        return proceed;
    }


    abstract class ProfilerLogger{

        abstract void log( String message );

        protected final void logExecutiontime(long beforeStartTime , ProceedingJoinPoint joinPoint ){
            String name = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            log( String.format(messageTemplate , (System.currentTimeMillis() - beforeStartTime) , name) );
        }

        protected final void logParameters(ProceedingJoinPoint joinPoint){
            String name = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            StringBuilder calling_method_ = new StringBuilder("Calling method ")
                    .append(name).append(" with parameters [");
            Arrays.stream(joinPoint.getArgs()).forEach(o -> calling_method_.append(", \n\t").append(o));
            if(joinPoint.getArgs().length > 0) calling_method_.append("\n");
            log(calling_method_.append("]").toString());
        }
    }

    class SoutLogger extends ProfilerLogger{
        @Override
        public void log(String message) {
            System.out.println(message);
        }
    }

    class Sl4jLogger extends ProfilerLogger{

        final Logger logger;

        public Sl4jLogger(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void log(String message) {
            logger.info(message);
        }

    }

}
