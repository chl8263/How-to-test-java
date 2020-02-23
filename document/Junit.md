## JUnit5

JUnit is testing framework for Java.

Unlike previous version of JUnit, JUnit5 is composed of several different modules from three different sub-project.

`JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage`

####1. JUnit Platform
The JUnit Platform serves as a foundation for launching testing frameworks on the JVM.
It also defines the `TestEngine` API for developing a testing framework that runs on the platform.

####2. JUnit Jupiter
JUnit Jupiter is the combination of the new programming model and extension model for writing test and extensions in JUnit5.


####3. JUnit Vintage
JUnit Vintage provides a TestEngine for running JUnit 3 and JUnit 4 based tests on the platform.


### Supported Java Versions
JUnit5 required Java8 at runtime. However, you can still test code that has been compiled with previous version of the JDK.


### Annotations

JUnit Jupiter support the following annotations for configuring test and extending the framework.

Unless otherwise stated, all core annotations are located in the `org.junit.jupiter.api` package in the `junit-jupiter-api module`.

|Annotation|Description|
|---|---|
|@Test|Denotes that method is a test method. Unlike JUnit4's `@Test` annotation, this annotation does not declare any attribution, since test extension in JUnit Jupiter operate vased on their own dedicated annotations.|
|@ParameterizedTest|Denotes that a method is a parameterized test.|
|@RepeatedTest|Denote that a method is a test template for a repeated test|
|@TestFactory|Denotes that a method is a test factory for dynamic tests. |
|@TestTemplate|Denotes that a method is a template for test cases designed to be invoked multiple times depending on the number of invocation contexts returned by the registered providers.|
|@BeforeEach|Denotes that the annotated method should be executed before each @Test|
|@AfterEach|Denotes that the annotated method should be executed after each @Test|
|@BeforeAll|Denotes that the annotated method should be executed before all @Test|
|@AfterAll|Denotes that the annotated method should be executed after all @Test|
|@Nested|Denotes that the annotated class is a non-static nested test class. @BeforeAll and @AfterAll methods cannot be used directly in a @Nested test class unless the "per-class" test instance lifecycle is used. Such annotations are not inherited.|
|@Disabled|Used to disable a test class or test method; analogous to JUnit 4’s @Ignore. Such annotations are not inherited.|

### Example

~~~Java
@Test
void create(){
    Study study = new Study();
    assertNotNull(study);
}

@Test
@Disabled
void create1(){
    System.out.println("create1");
}

@BeforeAll
static void beforeAll(){
    System.out.println("before all");
}

@AfterAll
static void afterAll(){
    System.out.println("after all");
}

@BeforeEach
void beforeEach(){
    System.out.println("before each");
}

@AfterEach
void afterEach(){
    System.out.println("after each");
}
~~~

~~~
before all
before each
after each
after all
~~~

### Flow

~~~
            @BeforeClass
                 :
                 \/
@Before -----> @Test -----> @After
                 :
@Before -----> @Test -----> @After
                 :
@Before -----> @Test -----> @After
                 :
                 \/
            @AfterClass
~~~