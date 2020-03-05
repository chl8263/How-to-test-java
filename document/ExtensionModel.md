## Extension Model

JUnit can register extension model and there are several way how to register extension model.

1. `@ExtendWith()`

First of all, make custom extension model for measure which test is most slower in test case.

Need `BeforeTestExecutionCallback` and `AfterTestExecutionCallback`. They can give callback when execute before test and after test.

~~~Java
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final long THRESHOLD = 1000L;

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
~~~

In test case, just write `@ExtendWith(FindSlowTestExtension.class)` for use `FindSlowTestExtension.class` which made before.   

~~~java
@ExtendWith(FindSlowTestExtension.class)
public class StudyTest {

    @Test
    @Order(1)
    void extensionTest() throws InterruptedException{

        Thread.sleep(1000);
        System.out.println("create extension test");
    }

...
~~~

The `extensionTest` has Thread.sleep(1000), therefore the result will be shown as like below code.

~~~
Please consider mark method [extensionTest] with @SlowTest.
~~~

However, if want to use parameter for each extension?

First of all, make that as like below code.

~~~java
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private long THRESHOLD = 1000L;

    public FindSlowTestExtension(long THRESHOLD){
        this.THRESHOLD = THRESHOLD;
    }
...
~~~

__Then, how can use constructor in test code?__

In this case, use `@RegisterExtension` instead of `@ExtendWith(FindSlowTestExtension.class)`.

~~~java
public class StudyTest {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);
...
~~~