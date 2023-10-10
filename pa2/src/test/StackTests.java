import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class StackTests {
    @Test
    @Score(1)
    public void test1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Stack<Integer> s = new Stack<>();

            s.push(3);
            s.push(4);
            assertThat(s.peek(), is(4));
            assertThat(s.pop(), is(4));
            assertThat(s.pop(), is(3));
        });
    }

    @Test
    @Score(1)
    public void test2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Stack<String> s = new Stack<>();

            s.push("hello");
            s.push("world");
            assertThat(s.peek(), is("world"));
            assertThat(s.pop(), is("world"));
            assertThat(s.pop(), is("hello"));
        });
    }

    @Test
    @Score(1)
    public void test3() {
        Stack<Integer> s = new Stack<>();

        for (int i=0; i<1000000; i++) {
            s.push(i);
        }

        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            for (int i=0; i<999999; i++) {
                assertThat(s.pop(), is(999999-i));
                assertThat(s.isEmpty(), is(false));
            }

            s.clear();
            assertThat(s.isEmpty(), is(true));
            assertThat(s.getSize(), is(0));
            assertThat(s.pop(), is(nullValue()));
            assertThat(s.peek(), is(nullValue()));
        });
    }
}
