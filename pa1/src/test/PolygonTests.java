import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class PolygonTests {
	@Test
	@Score(1)
	public void testBasic1() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ICLL<Point> l = new CircularLinkedList<Point>();
			l.insert(new Point(10,-10));
			l.insert(new Point(11,2));
			l.insert(new Point(9,10));
			l.insert(new Point(-4,8));
			l.insert(new Point(-1,-1));
			Polygon p = new Polygon(l);
			assertThat(p.pointInPolygon(new Point(0,0)), is(true));
			assertThat(p.pointInPolygon(new Point(1, 9)), is(false));
		});
	}
}
