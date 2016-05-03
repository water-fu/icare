package com.wisdom.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBean生成工厂
 * Created by fusj on 15/12/23.
 */
@Component
public class ApplicationContextFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String key) {
        if(applicationContext == null) {
            throw new RuntimeException("spring context 没有初始化");
        }

        return applicationContext.getBean(key);
    }

    public static Object getBean(Class clazz) {
        if(applicationContext == null) {
            throw new RuntimeException("spring context 没有初始化");
        }
        return applicationContext.getBean(clazz);
    }
}
