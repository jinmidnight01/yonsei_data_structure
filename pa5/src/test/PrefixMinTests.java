
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class PrefixMinTests {
    @Test
    @Score(1)
    public void sampleTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            PrefixMin pm = new PrefixMin();

            YonseiString s1 = new YonseiString(new int[] {1,2,3,4,5,6});
            YonseiString s2 = new YonseiString(new int[] {1,2,3,3,3,3});
            YonseiString s3 = new YonseiString(new int[] {3,3,3,3,3,3});
            YonseiString s4 = new YonseiString(new int[] {1,2,3});

            pm.insert(s1,5);
            pm.insert(s2,10);
            pm.insert(s1,4);
            pm.insert(s3,1);
            assertThat(pm.getMin(s4), is(5));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            PrefixMin pm = new PrefixMin();

            YonseiString s1 = new YonseiString(new int[] {'b', 'e','a','r'});
            YonseiString s2 = new YonseiString(new int[] {'b','e','l','l'});
            YonseiString s3 = new YonseiString(new int[] {'b','i','d'});
            YonseiString s4 = new YonseiString(new int[] {'b','u','l','l'});
            YonseiString s5 = new YonseiString(new int[] {'b','u','y'});
            YonseiString s6 = new YonseiString(new int[] {'s','e','l','l'});
            YonseiString s7 = new YonseiString(new int[] {'s','t','o','c','k'});
            YonseiString s8 = new YonseiString(new int[] {'s','t','o','p'});
            YonseiString s9 = new YonseiString(new int[] {'b','e'});

            YonseiString test = new YonseiString(new int[] {'s','t','o'});

            pm.insert(s1,5);
            pm.insert(s2,10);
            pm.insert(s1,4);
            pm.insert(s3,1);
            pm.insert(s4,2);
            pm.insert(s5,3);
            pm.insert(s6,4);
            pm.insert(s7,5);
            pm.insert(s8,6);
            //pm.insert(s9,-1);
            assertThat(pm.getMin(test), is(5));
        });

    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            PrefixMin pm = new PrefixMin();

            YonseiString s1 = new YonseiString(new int[] {'b','e','a','r'});
            YonseiString s2 = new YonseiString(new int[] {'b','e','l','l'});
            YonseiString s3 = new YonseiString(new int[] {'b','i','d'});
            YonseiString s4 = new YonseiString(new int[] {'b','u','l','l'});
            YonseiString s5 = new YonseiString(new int[] {'b','u','y'});
            YonseiString s6 = new YonseiString(new int[] {'s','e','l','l'});
            YonseiString s7 = new YonseiString(new int[] {'s','t','o','c','k'});
            YonseiString s8 = new YonseiString(new int[] {'s','t','o','p'});
            YonseiString s9 = new YonseiString(new int[] {'b','e'});

            YonseiString test = new YonseiString(new int[] {'b','e','l','l'});

            pm.insert(s1,5);
            pm.insert(s2,10);
            pm.insert(s3,1);
            pm.insert(s4,2);
            pm.insert(s5,3);
            pm.insert(s6,4);
            pm.insert(s7,5);
            pm.insert(s8,6);
            pm.insert(s9,-1);
            assertThat(pm.getValue(test), is(10));
        });
    }

    @Test
    @Score(1)
    public void sampleTest4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            PrefixMin pm = new PrefixMin();

            YonseiString s1 = new YonseiString(new int[] {'b','e','a','r'});
            YonseiString s2 = new YonseiString(new int[] {'b','e','l','l'});
            YonseiString s3 = new YonseiString(new int[] {'b','i','d'});
            YonseiString s4 = new YonseiString(new int[] {'b','u','l','l'});
            YonseiString s5 = new YonseiString(new int[] {'b','u','y'});
            YonseiString s6 = new YonseiString(new int[] {'s','e','l','l'});
            YonseiString s7 = new YonseiString(new int[] {'s','t','o','c','k'});
            YonseiString s8 = new YonseiString(new int[] {'s','t','o','p'});
            YonseiString s9 = new YonseiString(new int[] {'b','e'});

            YonseiString test = new YonseiString(new int[] {'b','i','d'});

            pm.insert(s1,5);
            pm.insert(s2,10);
            pm.insert(s3,1);
            pm.insert(s4,2);
            pm.insert(s5,3);
            pm.insert(s6,4);
            pm.insert(s7,5);
            pm.insert(s8,6);
            pm.insert(s9,-1);
            pm.clear();
            assertThat(pm.getMin(test), is(Integer.MIN_VALUE));
        });
    }

    @Test
    @Score(1)
    public void sampleTest5() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            PrefixMin pm = new PrefixMin();

            YonseiString s1 = new YonseiString(new int[] {'b','e','a','r'});
            YonseiString s2 = new YonseiString(new int[] {'b','e','l','l'});
            YonseiString s3 = new YonseiString(new int[] {'b','i','d'});
            YonseiString s4 = new YonseiString(new int[] {'b','u','l','l'});
            YonseiString s5 = new YonseiString(new int[] {'b','u','y'});
            YonseiString s6 = new YonseiString(new int[] {'s','e','l','l'});
            YonseiString s7 = new YonseiString(new int[] {'s','t','o','c','k'});
            YonseiString s8 = new YonseiString(new int[] {'s','t','o','p'});
            YonseiString s9 = new YonseiString(new int[] {'b','e'});

            YonseiString test = new YonseiString(new int[] {'s'});

            pm.insert(s1,5);
            pm.insert(s2,10);
            pm.insert(s3,-2);
            pm.insert(s4,2);
            pm.insert(s5,3);
            pm.insert(s6,4);
            pm.insert(s7,5);
            pm.insert(s8,6);
            pm.insert(s9,-1);
            pm.remove(s8);
            assertThat(pm.getMin(test), is(5));
        });
    }

}
