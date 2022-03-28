package com.keencho.spring.jpa.config.eventlistener;

import com.keencho.spring.jpa.listeners.SimpleEventListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SimpleEventListenerConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<SimpleEventListeners> simpleEventListenerList;

    @PostConstruct
    public void injectDependency() {
        simpleEventListenerList.forEach(el -> {
            Arrays.stream(el.getClass().getDeclaredFields())
                    .filter(field -> Modifier.isStatic(field.getModifiers()))
                    .forEach(field -> {
                        field.setAccessible(true);
                        try {
                            field.set(field.getType(), applicationContext.getBean(field.getType()));
                        } catch (Exception ex) {
                            System.err.println(ex.getMessage());
                        }
                    });
        });
    }
}
