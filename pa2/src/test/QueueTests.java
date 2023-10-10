import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class QueueTests {
    @Test
    @Score(1)
    public void test1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Queue<Integer> q = new Queue<>();

            q.enqueue(3);
            q.enqueue(4);
            assertThat(q.peek(), is(3));
            assertThat(q.dequeue(), is(3));
            assertThat(q.dequeue(), is(4));
        });
    }

    @Test
    @Score(1)
    public void test2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Queue<Integer> q = new Queue<>();

            assertThat(q.isEmpty(), is(true));
            assertThat(q.getSize(), is(0));
            assertThat(q.dequeue(), is(nullValue()));
            assertThat(q.peek(), is(nullValue()));

            for (int i=0; i<10000; i++) {
                q.enqueue(i);
                assertThat(q.peek(), is(0));
                assertThat(q.getSize(), is(i+1));
                assertThat(q.isEmpty(), is(false));
            }

            for (int i=0; i<9999; i++) {
                assertThat(q.dequeue(), is(i));
                assertThat(q.isEmpty(), is(false));
            }

            q.clear();
            assertThat(q.isEmpty(), is(true));
            assertThat(q.getSize(), is(0));
            assertThat(q.dequeue(), is(nullValue()));
            assertThat(q.peek(), is(nullValue()));
        });
    }

    @Test
    @Score(1)
    public void test3() {
        Queue<Integer> q = new Queue<>();

        for (int i=0; i<1000000; i++) {
            q.enqueue(i);
        }

        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            for (int i=0; i<999999; i++) {
                assertThat(q.dequeue(), is(i));
                assertThat(q.isEmpty(), is(false));
            }

            q.clear();
            assertThat(q.isEmpty(), is(true));
            assertThat(q.getSize(), is(0));
            assertThat(q.dequeue(), is(nullValue()));
            assertThat(q.peek(), is(nullValue()));
        });
    }
}
