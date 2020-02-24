package me.test.howtotestjava;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {

    @Test
    //@DisplayName("make study \uD83D\uDE31")
    void create_new_study(){
        Study study = new Study(-10);
        assertNotNull(study);
        assertEquals(StudyStatus.LIMIT, study.getStatus(), "When create study status muse be DRAFT");
        assertTrue(1 < 2);
        assertTrue(study.getLimit() > 0, "Limit must more than 0");
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
    //@DisplayName("make study again \uD83D\uDE31")
    void create_new_study_again(){
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
}
