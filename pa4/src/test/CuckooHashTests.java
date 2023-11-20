import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class CuckooHashTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            ICuckooHash c = new CuckooHash(0.5, 4, 4);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(100), is(false));
            c.insert(100);
            c.insert(104);
            hnext = c.getParams();
            assertThat(c.contains(104), is(true));
            assertThat(c.contains(100), is(true));
            testEquality(hprev, hnext);
            c.insert(108);
            hnext = c.getParams();
            assertThat(hnext.N, is(not(hprev.N)));
        });
    }

    private void testEquality(HashParameter hprev, HashParameter hnext){
        assertThat(hprev.a1, is(hnext.a1));
        assertThat(hprev.a2, is(hnext.a2));
        assertThat(hprev.b1, is(hnext.b1));
        assertThat(hprev.b2, is(hnext.b2));
        assertThat(hprev.N, is(hnext.N));
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            ICuckooHash c = new CuckooHash(0.2, 4, 4);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(100), is(false));
            c.insert(100);
            hnext = c.getParams();
            testEquality(hprev, hnext);
            c.insert(104);
            hnext = c.getParams();
            assertThat(hnext.N, is(not(hprev.N)));
        });
    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            CuckooHash c = new CuckooHash(0.5, 4, 4);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(100), is(false));
            c.insert(100);
            c.insert(104);
//            assertThat(c.elements.size, is(2));
            hnext = c.getParams();
            assertThat(c.contains(104), is(true));
            assertThat(c.contains(100), is(true));
            testEquality(hprev, hnext);
            c.insert(108);
            hnext = c.getParams();
//            assertThat(c.elements.size, is(3));
        });
    }

    @Test
    @Score(1)
    public void sampleTest4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            ICuckooHash c = new CuckooHash(0.5, 100000, 8);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(3), is(false));
            c.insert(3);
            c.insert(7);
            hnext = c.getParams();
            assertThat(c.contains(7), is(true));
            assertThat(c.contains(3), is(true));
            testEquality(hprev, hnext);
            c.insert(19);
            c.insert(1);
            hnext = c.getParams();
            testEquality(hprev, hnext);
        });
    }

    @Test
    @Score(1)
    public void sampleTest5() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            CuckooHash c = new CuckooHash(0.5, 4, 8);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(3), is(false));
            c.insert(3);
//            assertThat(c.elements.size, is(1));
            c.insert(7);
//            assertThat(c.elements.size, is(2));
            hnext = c.getParams();
            assertThat(c.contains(7), is(true));
            assertThat(c.contains(3), is(true));
            testEquality(hprev, hnext);
            c.insert(19);
//            assertThat(c.elements.size, is(3));
            c.insert(1);
//            assertThat(c.elements.size, is(4));
        });
    }

    @Test
    @Score(1)
    public void sampleTest6() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            CuckooHash c = new CuckooHash(0.5, 3, 8);
            HashParameter hprev, hnext;

            hprev = c.getParams();
            assertThat(c.contains(3), is(false));
            c.insert(3);
//            assertThat(c.elements.size, is(1));
            c.delete(3);
//            assertThat(c.elements.size, is(0));
            c.delete(3);
//            assertThat(c.elements.size, is(0));
            c.insert(3);
//            assertThat(c.elements.size, is(1));
            c.insert(7);
//            assertThat(c.elements.size, is(2));
            hnext = c.getParams();
            assertThat(c.contains(7), is(true));
            assertThat(c.contains(3), is(true));
            testEquality(hprev, hnext);
            c.insert(19);
//            assertThat(c.elements.size, is(3));
            c.insert(1);
//            assertThat(c.elements.size, is(4));
        });
    }

    @Test
    @Score(1)
    public void sampleTest7() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            CuckooHash c = new CuckooHash(0.1, 100000, 8);
            HashParameter hprev, hnext, hnext2, hnext3;

            hprev = c.getParams();
            assertThat(c.contains(3), is(false));
            c.insert(3);
//            assertThat(c.elements.size, is(1));
            c.delete(3);
//            assertThat(c.elements.size, is(0));
            c.delete(3);
//            assertThat(c.elements.size, is(0));
            c.insert(3);
//            assertThat(c.elements.size, is(1));
            c.insert(7);
//            assertThat(c.elements.size, is(2));
            c.delete(7);
//            assertThat(c.elements.size, is(1));
            hnext = c.getParams();
            assertThat(hnext.N, is(not(hprev.N)));
            assertThat(hnext.N, is(16));
            assertThat(c.contains(7), is(false));
            assertThat(c.contains(3), is(true));
            c.insert(7);
//            assertThat(c.elements.tail.key, is(7));
            c.insert(19);
//            assertThat(c.elements.tail.key, is(19));
            hnext2 = c.getParams();
            assertThat(hnext2.N, is(hnext.N));
//            assertThat(c.elements.size, is(3));
            c.insert(1);
            hnext3 = c.getParams();
            assertThat(hnext3.N, is(32));
//            assertThat(c.elements.size, is(4));
        });
    }
}
