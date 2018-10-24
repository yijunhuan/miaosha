package com.huang.aop;

import org.springframework.stereotype.Component;


@Component("mathCala")
public class MathCala {
    public void plus(int x, int y) {
        swe(x, y);
    }
    
    
    
    
    public static int swe(int x,int y) {
        return x + y;
    }
}
