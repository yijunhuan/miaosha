package com.huang.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huang.MiaoShaApplicationMain;
import com.huang.config.RedisConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MiaoShaApplicationMain.class})
public class RedisValueTest {
    @Autowired
    RedisConfig rs;
    
    @Test
    public void test() {
        System.out.println(rs);
    }

}
