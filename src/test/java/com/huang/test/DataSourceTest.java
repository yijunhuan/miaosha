package com.huang.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.huang.MiaoShaApplicationMain;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MiaoShaApplicationMain.class })
public class DataSourceTest {
    @Autowired
    DataSource datasource;
    
    @Autowired
    ThymeleafViewResolver resolver;

    @Test
    public void test() {
        System.out.println(datasource.getClass());
        assertNotNull(datasource);

        DruidDataSource dataSource = (DruidDataSource) datasource;
        int initialSize = dataSource.getInitialSize();
        System.out.println(initialSize);
    }
    
    @Test
    public void testResolver() {
        System.out.println(resolver);
    }
    
    

}
