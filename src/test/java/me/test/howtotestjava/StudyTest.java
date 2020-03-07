package me.test.howtotestjava;

import me.test.howtotestjava.domain.Study;
import me.test.howtotestjava.study.StudyStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@ExtendWith(FindSlowTestExtension.class)
public class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    @Order(1)
    void extensionTest() throws InterruptedException{

        Thread.sleep(1000);
        System.out.println("create extension test");
    }

    @Order(2)
    @Test
    void instanceTest(){
        System.out.println(this);
        System.out.println(value++);
        assertThat(value).isGreaterThan(1);
    }

    @Order(3)
    @Test
    void instanceTest2(){
        System.out.println(this);
        System.out.println(value++);
        assertThat(value).isGreaterThan(1);
    }


    @Test
    //@DisplayName("make study \uD83D\uDE31")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    //@EnabledOnOs(OS.WINDOWS)
    @DisabledOnOs(OS.MAC)
    //@EnabledOnJre(JRE.JAVA_11)
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10})
    void create_new_study(){
        Study study = new Study(-10);
//        assertNotNull(study);
//        assertEquals(StudyStatus.LIMIT, study.getStatus(), "When create study status muse be DRAFT");
//        assertTrue(1 < 2);
//        assertTrue(study.getLimit() > 0, "Limit must more than 0");
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.LIMIT, study.getStatus(), "When create study status muse be DRAFT"),
                () -> assertTrue(1 < 2),
                () -> assertTrue(study.getLimit() > 0, "Limit must more than 0")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals(message, "Limit must more than 0");
        assertTimeoutPreemptively(Duration.ofSeconds(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // TODO ThreadLocal
    }

    @Test
    @DisplayName("Make study1")
    //@Tag("fast")
    @FastTest
    void create_new_study_again(){
        System.out.println("create1");
    }

    @Test
    @DisplayName("Make study2")
    //@Tag("slow")
    @SlowTest
    void create_new_study_again2(){
        System.out.println("create1");
    }

    @DisplayName("Make repeat study")
    @RepeatedTest(value=10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void create_study(RepetitionInfo repetitionInfo){
        System.out.println("test" + repetitionInfo.getCurrentRepetition()+ "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("Make parameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings ={"hi", "my", "name", "Ewan"})
    @EmptySource
    @NullSource
    @NullAndEmptySource
    void parameterizedTest(String msg){
        System.out.println(msg);
    }

    @DisplayName("Make Study")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterizedTest(Study study){
        System.out.println(study.getLimit());
    }

    @DisplayName("Repeat Test")
    @RepeatedTest(value = 5, name="{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(){
        System.out.println("Repeat Test");
    }

    @ParameterizedTest(name = "Parameterized Test {0}")
    @ValueSource(strings = {"Ewan", "Ewan2", "Ewan3"})
    @NullSource
    void parameterTest(String name){
        System.out.println(name);
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
}
