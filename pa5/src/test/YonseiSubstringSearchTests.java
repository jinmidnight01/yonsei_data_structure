
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class YonseiSubstringSearchTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] pattern1 = new int[] { 'i' };

            int[] pattern2 = new int[] {'i', 's', 's', 'i'};

            int[] pattern3 = new int[] {'i', 'p'};

            int[] pattern4 = new int[] {'s', 's', 's'};

            int[] text = new int[] {'m', 'i', 's', 's', 'i', 's', 's', 'i', 'p', 'p', 'i'};
            int [] hi = new int[] {'-', -1, 2, 3 };
            for (int e : hi) {
                System.out.println(e);
            }

            YonseiSubstringSearch test = new YonseiSubstringSearch(new YonseiString(text));

            assertThat(test.countPattern(new YonseiString(pattern1)), is(4));
            assertThat(test.countPattern(new YonseiString(pattern2)), is(2));
            assertThat(test.countPattern(new YonseiString(pattern3)), is(1));
            assertThat(test.countPattern(new YonseiString(pattern4)), is(0));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] pattern = new int[] {1,2,1};

            int[] text = new int[100];
            for(int i=0; i<100; i++){
                text[i] = (i % 2 == 0 ? 1 : 2);
            }

            YonseiString p = new YonseiString(pattern);
            YonseiString t = new YonseiString(text);

            YonseiSubstringSearch test = new YonseiSubstringSearch(t);
            assertThat(test.countPattern(p), is(49));
        });
    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] pattern1 = new int[] { 'a', 'b' };

            int[] pattern2 = new int[] {'e', 'a'};

            int[] pattern3 = new int[] {'e', 'a', 'b'};

            int[] pattern4 = new int[] {'s', 's', 's'};

            int[] text1 = new int[] {'a', 'b', 'c', 'd', 'e', 'a', 'b', 'c', 'd', 'e', 'a', 'e', 'a'};

            int[] pattern5 = new int[] { 1, 3 };

            int[] pattern6 = new int[] {4, 5};

            int[] pattern7 = new int[] {7, 1};

            int[] pattern8 = new int[] {7, 1, 9};

            int[] text2 = new int[] {3,5,7,1,9,7,1,3,4,5};

            YonseiSubstringSearch test = new YonseiSubstringSearch(new YonseiString(text1));
            assertThat(test.countPattern(new YonseiString(pattern1)), is(2));
            assertThat(test.countPattern(new YonseiString(pattern2)), is(3));
            assertThat(test.countPattern(new YonseiString(pattern3)), is(1));
            assertThat(test.countPattern(new YonseiString(pattern4)), is(0));

            YonseiSubstringSearch test2 = new YonseiSubstringSearch(new YonseiString(text2));
            assertThat(test2.countPattern(new YonseiString(pattern5)), is(1));
            assertThat(test2.countPattern(new YonseiString(pattern6)), is(1));
            assertThat(test2.countPattern(new YonseiString(pattern7)), is(2));
            assertThat(test2.countPattern(new YonseiString(pattern8)), is(1));
        });
    }
}
