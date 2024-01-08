package org.setsuma.springboot.demorest.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..)) "
            + "and within(org.setsuma.springboot.demorest.controllers..*)")
    public void beforeRequestMapping(JoinPoint joinPoint) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("START {} with argument[s] = {}",
                    getMethodSignature(joinPoint),
                    Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.getTarget();
            if (LOG.isDebugEnabled()) {
                LOG.debug("END: {} with result = {}",
                        getMethodSignature(joinPoint),
                        result);
            }
        } catch (IllegalArgumentException e) {
            LOG.error("Illegal argument: {} in {}",
                    Arrays.toString(joinPoint.getArgs()),
                    getMethodSignature(joinPoint)
                    );
            throw e;
        }
    }

    @Around("@annotation(org.setsuma.springboot.demorest.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        LOG.debug(getMethodSignature(joinPoint) + " executed in " + executionTime + "ms");
        return proceed;
    }
    
    private String getMethodSignature(JoinPoint joinPoint) {
        var result = new StringBuilder();
        CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
        result.append(methodSignature.getDeclaringTypeName());
        result.append('.');
        result.append(methodSignature.getName());
        result.append('(');
        Class[] parameterTypes = methodSignature.getParameterTypes();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            if(i>0) {
                result.append(',');    
            }
            String parameterName = parameterNames[i];
            Class parameterType = parameterTypes[i];
            result.append(parameterType.getSimpleName());
            result.append(' ');
            result.append(parameterName);
        }
        result.append(')');
        return result.toString();
    }
}