package com.huang.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("mathAspect")
public class MathAspect {
    Logger logger = LoggerFactory.getLogger(MathAspect.class);
    
    @Pointcut("execution (* com.huang.aop.MathCala.*(..))")
    public void pointCut() {};
    
    
    
    
    @Before("pointCut() && args(x,y)")
    public void loginStart(int x,int y) {
        logger.info("传入参数是x:"+x +" y:"+y);
        logger.info("正在进行运算");
    }
    
    
}
