package com.huang.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.huang.MiaoShaApplicationMain;
import com.huang.aop.MathCala;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MiaoShaApplicationMain.class})
public class AopTest {
    @Autowired
    ApplicationContext context;
    
    
    
    @Test
    public void test() {
        MathCala math = context.getBean(MathCala.class);
        math.plus(2,5);
    }

}
