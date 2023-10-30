
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class SchedulerTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IJob j = new TestJob(5);
            IScheduler s = new Scheduler();
            s.register(j);
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] patienceArr1 = new int[2];
            patienceArr1[0] = 4;
            patienceArr1[1] = 5;

            int[] patienceArr2 = new int[3];
            patienceArr2[0] = 1;
            patienceArr2[1] = 4;
            patienceArr2[2] = 5;

            IJob j1 = new TestJob(2,patienceArr1);
            IJob j2 = new TestJob(3,patienceArr2);
            IScheduler s = new Scheduler();

            s.register(j1);
            s.register(j2);

            boolean j1HasLargerPid = (j1.getPid() > j2.getPid());
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j1HasLargerPid ? j2 : j1));
            assertThat(s.process(), is(j1HasLargerPid ? j1 : j2));
        });
    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] patienceArr1 = new int[2];
            patienceArr1[0] = 4; // 9
            patienceArr1[1] = 5; // 12

            int[] patienceArr2 = new int[3];
            patienceArr2[0] = 1; // 3
            patienceArr2[1] = 4; // 10
            patienceArr2[2] = 5; // 13

            int[] patienceArr3 = new int[3];
            patienceArr3[0] = 2; // 6
            patienceArr3[1] = 5; // 14
            patienceArr3[2] = 9; // 22

            int[] patienceArr4 = new int[3];
            patienceArr4[0] = -1; // 1
            patienceArr4[1] = 0; // 2
            patienceArr4[2] = 1; // 4

            int[] patienceArr5 = new int[3];
            patienceArr5[0] = 2; // 7
            patienceArr5[1] = 3; // 8
            patienceArr5[2] = 4; // 11

            int[] patienceArr6 = new int[3];
            patienceArr6[0] = 1; // 5
            patienceArr6[1] = 8; // 18
            patienceArr6[2] = 9; // 23

            int[] patienceArr7 = new int[3];
            patienceArr7[0] = 7; // 15
            patienceArr7[1] = 1; // 16
            patienceArr7[2] = 2; // 17

            int[] patienceArr8 = new int[3];
            patienceArr8[0] = 8; // 19
            patienceArr8[1] = 3; // 20
            patienceArr8[2] = 0; // 21

            IJob j1 = new TestJob(2,patienceArr1);
            IJob j2 = new TestJob(3,patienceArr2);
            IJob j3 = new TestJob(3,patienceArr3);
            IJob j4 = new TestJob(3,patienceArr4);
            IJob j5 = new TestJob(3,patienceArr5);
            IJob j6 = new TestJob(3,patienceArr6);
            IJob j7 = new TestJob(3,patienceArr7);
            IJob j8 = new TestJob(3,patienceArr8);
            IScheduler s = new Scheduler();

            s.register(j1);
            s.register(j2);
            s.register(j3);
            s.register(j4);
            s.register(j5);
            s.register(j6);
            s.register(j7);
            s.register(j8);

            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j4));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j5));
            assertThat(s.process(), is(j1));
            assertThat(s.process(), is(j2));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j7));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j8));
            assertThat(s.process(), is(j3));
            assertThat(s.process(), is(j6));
        });
    }
}
