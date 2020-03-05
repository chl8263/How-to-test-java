package me.test.howtotestjava;


import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private long THRESHOLD = 1000L;

    public FindSlowTestExtension(long THRESHOLD){
        this.THRESHOLD = THRESHOLD;
    }


    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        Method method = context.getRequiredTestMethod();
        Annotation slowAnnotation = method.getAnnotation(SlowTest.class);

        String testMethodName = method.getName();

        ExtensionContext.Store store = getStore(context);

        long start_time = store.remove("START_TIME", Long.class);
        long duration = System.currentTimeMillis() - start_time;
        if(duration > THRESHOLD && slowAnnotation == null){
            System.out.println("Please consider mark method ["+testMethodName+"] with @SlowTest.\n");
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext context){
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
