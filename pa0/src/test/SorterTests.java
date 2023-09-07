import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class SorterTests {
	@Test
	@Score(1)
	public void testBasic1() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] {};
			assertThat(s.ascending(a.clone()), is(new int[] {}));
			assertThat(s.descending(a.clone()), is(new int[] {}));
		});
	}

	@Test
	@Score(1)
	public void testBasic2() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 2, 1, 4, 3 };
			assertThat(s.ascending(a.clone()), is(new int[] { 1, 2, 3, 4 }));
			assertThat(s.descending(a.clone()), is(new int[] { 4, 3, 2, 1 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic3() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 7, 5, 4, 9, 13 };
			assertThat(s.ascending(a.clone()), is(new int[] { 4, 5, 7, 9, 13 }));
			assertThat(s.descending(a.clone()), is(new int[] { 13, 9, 7, 5, 4 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic4() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { -1, 0, -1, 0, -1};
			assertThat(s.ascending(a.clone()), is(new int[] { -1, -1, -1, 0, 0 }));
			assertThat(s.descending(a.clone()), is(new int[] { 0, 0, -1, -1, -1 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic5() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 1, 3, 5, 7, 6, 4, 3, 2, -1};
			assertThat(s.ascending(a.clone()), is(new int[] { -1, 1, 2, 3, 3, 4, 5, 6, 7 }));
			assertThat(s.descending(a.clone()), is(new int[] { 7, 6, 5, 4, 3, 3, 2, 1, -1 }));
		});
	}
}
