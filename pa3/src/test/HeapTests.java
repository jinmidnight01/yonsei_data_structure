
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class HeapTests {
    @Test
    @Score(1)
    public void test1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            heap.insert(2);
            heap.insert(3);
            heap.insert(1);
            assertThat(heap.removeMin(), is(1));

            Heap<Integer> heap2 = new Heap<>();
            heap2.insert(0);
            heap2.insert(4);

            heap.merge(heap2);

            assertThat(heap.removeMin(), is(0));
        });
    }

    @Test
    @Score(1)
    public void test2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            heap.insert(2);
            heap.insert(3);
            heap.insert(1);
            heap.insert(0);
            heap.insert(9);
            heap.insert(10);
            heap.insert(-1);
            heap.insert(5);
            heap.insert(7);
            heap.insert(2);
            heap.insert(0);
            heap.insert(8);
            assertThat(heap.min(), is(-1));
            assertThat(heap.removeMin(), is(-1));
            assertThat(heap.removeMin(), is(0));
            assertThat(heap.removeMin(), is(0));
            heap.insert(-1);
            heap.insert(0);
            heap.insert(0);

            Heap<Integer> heap2 = new Heap<>();
            heap2.insert(0);
            heap2.insert(4);

            assertThat(heap2.getSize(), is(2));
            heap2.clear();
            assertThat(heap2.isEmpty(), is(true));
            assertThat(heap2.getSize(), is(0));

            heap2.insert(0);
            heap2.insert(4);
            heap2.insert(7);
            heap2.insert(1000);
            heap2.insert(30);
            heap2.insert(-4);
            heap2.insert(2);
            heap2.insert(0);
            heap2.insert(6);
            heap2.insert(3);
            assertThat(heap2.isEmpty(), is(false));

            heap.merge(heap2);
            assertThat(heap.getSize(), is(22));

            assertThat(heap.removeMin(), is(-4));
            assertThat(heap.removeMin(), is(-1));
            assertThat(heap.removeMin(), is(0));
            assertThat(heap.removeMin(), is(0));
            assertThat(heap.removeMin(), is(0));
            assertThat(heap.removeMin(), is(0));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.removeMin(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertThat(heap.removeMin(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertThat(heap.removeMin(), is(4));
            assertThat(heap.removeMin(), is(5));
            assertThat(heap.removeMin(), is(6));
            assertThat(heap.removeMin(), is(7));
            assertThat(heap.removeMin(), is(7));
            assertThat(heap.removeMin(), is(8));
            assertThat(heap.removeMin(), is(9));
            assertThat(heap.removeMin(), is(10));
            assertThat(heap.removeMin(), is(30));
            assertThat(heap.removeMin(), is(1000));
        });
    }
}
