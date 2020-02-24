## Assert

JUnit provide a few method for verify value in test method.

|Method|Description|
|---|---|
|assertEquals(expected, actual)|Check actual value is equals to expected value|
|assertNotNull(actual)|Check if value is not null|
|assertTrue(boolean)|Check if value is true|
|assertAll(executables...)|Check all test|
|assertThrows(expectedType, executable)|Check for exception|
|assertTimeout(duration, executable)|Verify that execution complete within a certain time|


#### 1. assertEquals(expected, actual)

In assertEquals method, first parameter is actual value and second parameter is expected method.

Can use third parameter for appear user defined message.

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = new Study(-10);
    assertEquals(StudyStatus.LIMIT, study.getStatus(), "When create study status muse be DRAFT");
}
~~~

#### 2. assertNotNull(actual)

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = new Study(-10);
    assertNotNull(study);
}
~~~

#### 3. assertTrue(boolean)

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    assertTrue(1 < 2);
}
~~~

#### 4. assertAll(executables...)

In below code, study value is null, so test will be break at `assertNotNull(study)` then test can not verify `assertTrue(1 < 2)`.   

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = null;
    assertNotNull(study);
    assertTrue(1 < 2);
}
~~~

Use assertAll(executables...) for verify all test like below code.

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = new Study(-10);
    assertAll(
            () -> assertNotNull(study),
            () -> assertEquals(StudyStatus.LIMIT, study.getStatus(), "When create study status muse be DRAFT"),
            () -> assertTrue(1 < 2),
            () -> assertTrue(study.getLimit() > 0, "Limit must more than 0")
    );
}
~~~

#### 5. assertThrows(expectedType, executable)

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = new Study(-10);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
}
~~~

Can get error message in exception, verify error message using assertEquals. 

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    Study study = new Study(-10);
    String message = exception.getMessage();
    assertEquals(message, "Limit must more than 0");
}
~~~

#### 6. assertTimeout(duration, executable)

`assertTimeout(duration, executable)` is for verify execution test within certain time.

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
    assertTimeoutPreemptively(Duration.ofSeconds(100), () -> {
        new Study(10);
        Thread.sleep(300);
    });
}
~~~