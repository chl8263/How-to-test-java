## Tag And Filter

JUnit can make custom tag with combination annotation.

~~~Java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
public @interface FastTest {
}
~~~

~~~Java
@FastTest
@DisplayName("fast")
void create_new_study() {

@SlowTest
@DisplayName("slow")
void create_new_study_again() {
~~~