import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class CLLTests {
	@Test
	@Score(1)
	public void testBasic1() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ICLL<Integer> l = new CircularLinkedList<Integer>();
			assertThat(l.size(), is(0));
			l.insert(5);
			l.insert(4);
			l.insert(3);
			l.rotate(Direction.TO_NEXT);
			assertThat(l.getHead(), is(4));
			l.rotate(Direction.TO_NEXT);
			assertThat(l.delete(), is(3));
			assertThat(l.getHead(), is(5));
		});
	}

	@Test
	@Score(1)
	public void testBasic2() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ICLL<Integer> l = new CircularLinkedList<Integer>();
			l.insert(5);
			l.insert(4);
			l.insert(3);
			l.rotate(Direction.TO_PREVIOUS);
			assertThat(l.getHead(), is(3));
			l.rotate(Direction.TO_PREVIOUS);
			assertThat(l.delete(), is(4));
			assertThat(l.getHead(), is(3));
		});
	}

	@Test
	@Score(1)
	public void testBasic3() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ICLL<String> l = new CircularLinkedList<String>();
			l.insert("hi");
			l.insert("good luck");
			l.insert("I am your TA");
			assertThat(l.getHead(), is("hi"));
			assertThat(l.find("I am your TA"), is(true));
			assertThat(l.getHead(), is("I am your TA"));
		});
	}
}
