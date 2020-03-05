## Test Instance

JUnit make new instance each test because prevent error according to independence.

But, If want to use just one instance, use `@TestInstance(TestInstance.Lifecycle.PER_CLASS)` 

~~~Java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest {
...
~~~

Also give order each test for example when __Usecase Test__ or __Integration Test__.

Typically, use `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` like below code.

And give order as `@Order()`.

~~~Java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyTest {

    int value = 1;

    @Order(1)
    @Test
    void instanceTest(){
        System.out.println(this);
        System.out.println(value++);
        assertThat(value).isGreaterThan(1);
    }

    @Order(2)
    @Test
    void instanceTest2(){
        System.out.println(this);
        //System.out.println(value++);
        assertThat(value).isGreaterThan(1);
    }
~~~
