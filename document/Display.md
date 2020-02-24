## Display for Test

Test in JUnit can given another name.

Here are two representative ones.

1. Using annotation above Class.

2. Using annotation each Test method.

### 1. Using annotation above Class.

The test name `create_new_study` is displayed as shown in the code below.

~~~Java
public class StudyTest {

    @Test
    void create_new_study(){
    }
~~~

If use `@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)`, The test name is displayed as `create new study`.

`_` is changed to blank in the test name.

~~~Java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {

    @Test
    void create_new_study(){
    }
~~~

### 2. Using annotation each Test method.

Actually this recommend method that give name each test method. 

Can given name each method even use Emoji.

~~~Java
@Test
@DisplayName("make study \uD83D\uDE31")
void create_new_study(){
}
~~~