package me.efraimgentil.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilerAspect {

    @Around("@annotation(me.efraimgentil.profiler.Profile)")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long beforeStart = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        System.out.println(String.format("Took %d milliseconds to execute", (System.currentTimeMillis() - beforeStart) ));

        return proceed;
    }

}
