package com.jasperstrijp.wolftracker.api;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Factory {
    private final ApplicationContext applicationContext;


    public Factory() {
        this.applicationContext = new ClassPathXmlApplicationContext("mapping.xml");
    }
}
