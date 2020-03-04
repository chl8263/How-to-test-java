## Repeat the Test

JUnit provide for repeat test.

The most simple way is using just `@RepeatedTest()` annotation and put integer what you want to repeat.  

~~~Java
@RepeatedTest(5)
void repeatTest(){
    System.out.println("Repeat Test");
}
~~~

The result is `Repeat Test` and they will repeated 5 time.

If want to change main display name, just use `@Displayname()`.

~~~Java
@DisplayName("Repeat Test")
@RepeatedTest(5)
void repeatTest(){
    System.out.println("Repeat Test");
}
~~~

But, if want to give sub test name, can use `name` attribution in `@RepeatedTest()`.

Then should enter `value` for repeat the test.

The `name` attribution has few element.

- displayName - Show main test name
- currentRepetition - Show current display repeat
- totalRepetitions - Show total repeat count in the test

~~~Java
@DisplayName("Repeat Test")
@RepeatedTest(value = 5, name="{displayName}, {currentRepetition}/{totalRepetitions}")
void repeatTest(){
    System.out.println("Repeat Test");
}
~~~

The sub test names are like below.

~~~
Repeat Test, 1/5
Repeat Test, 2/5
Repeat Test, 3/5
Repeat Test, 4/5
Repeat Test, 5/5
~~~

JUnit also provide test using parameter like below.

When use parameter, write `@ValueSource()`, `@NullSource`, `@EmptySource`, `@NullAndEmptySource`, `@EnumSource`, `@MethodSource`, `@CvsSource`, `@CvsFileSource`, `@ArgumentSource`

~~~Java
@ParameterizedTest(name = "Parameterized Test")
@ValueSource(strings = "Ewan")
void parameterTest(String name){
    System.out.println(name);
}
~~~

The result is like below.

~~~
Ewan
~~~

`parameterTest` method parameter get value as `Ewan` that type is string.

Also can take more than one argument using `{"Ewan", "Ewan2", "Ewan3"}`.

~~~Java
@ParameterizedTest(name = "Parameterized Test")
 @ValueSource(strings = {"Ewan", "Ewan2", "Ewan3"})
void parameterTest(String name){
    System.out.println(name);
}
~~~

The above test repeat total 3 times.

If want to see parameter name at sub test name, can use `{0}` in `@ParameterizedTest()`.
It is more visibility for test.

~~~Java
@ParameterizedTest(name = "Parameterized Test {0}")
@ValueSource(strings = {"Ewan", "Ewan2", "Ewan3"})
void parameterTest(String name){
    System.out.println(name);
}
~~~

Can check null or empty argument `@NullSource`, `@EmptySource`.
The below test total count is 5. 

~~~Java
@ParameterizedTest(name = "Parameterized Test {0}")
@ValueSource(strings = {"Ewan", "Ewan2", "Ewan3"})
@NullSource
@EmptySource
void parameterTest(String name){
    System.out.println(name);
}
~~~