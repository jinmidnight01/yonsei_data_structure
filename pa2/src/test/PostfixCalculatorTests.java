import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class PostfixCalculatorTests {
    @Test
    @Score(1)
    public void test1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate("5 10 4 x + 9 + 5 11 x -"), is(-1));
        });
    }

    @Test
    @Score(1)
    public void test2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate("5"), is(5));
        });
    }

    @Test
    @Score(1)
    public void test3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate("3 5 7 - x"), is(-6));
        });
    }

    @Test
    @Score(1)
    public void test4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate("3 5 - 7 9 11 - x x"), is(28));
        });
    }

    @Test
    @Score(1)
    public void test5() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate("3 5 - 7 x"), is(-14));
        });
    }

    @Test
    @Score(1)
    public void test6() {
        String exp = "100001 1 - 100001 1 - -";
        for (int i=0; i< 1000000; i++){
            exp += " 100001 1 - 100001 1 - - +";
        }
        String finalExp = exp;
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IPostfixCalculator p = new PostfixCalculator();
            assertThat(p.evaluate(finalExp), is(0));
        });
    }

}
