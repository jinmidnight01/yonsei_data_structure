
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class BinarySearchTreeTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IBinarySearchTree<Integer> b = new BinarySearchTree<>();
            b.put(2);
            assertThat(b.getSize(), is(1));
            b.put(5);
            assertThat(b.getSize(), is(2));
            b.put(7);
            assertThat(b.getSize(), is(3));
            assertThat(b.contains(2), is(true));
            assertThat(b.contains(3), is(false));
            assertThat(b.getNext(6), is(7));
            assertThat(b.getPrev(4), is(2));
            b.remove(5);
            assertThat(b.contains(5), is(false));
            assertThat(b.getSize(), is(2));
            b.remove(2);
            assertThat(b.getSize(), is(1));
            b.remove(7);
            assertThat(b.getSize(), is(0));
            b.remove(3);
            assertThat(b.getSize(), is(0));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            BinarySearchTree<Integer> b = new BinarySearchTree<>();
            b.put(10);
            assertThat(b.getSize(), is(1));
            b.put(20);
            assertThat(b.getSize(), is(2));
            b.put(30);
            assertThat(b.getSize(), is(3));
            b.put(40);
            assertThat(b.getSize(), is(4));
            b.put(50);
            assertThat(b.getSize(), is(5));
            b.put(25);
            assertThat(b.getSize(), is(6));
            assertThat(b.getPrev(25), is(25));
            assertThat(b.getNext(25), is(30));
            assertThat(b.contains(20), is(true));
            assertThat(b.contains(35), is(false));
//            b.inOrder(b.root);
            b.clear();
            assertThat(b.getSize(), is(0));
        });
    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            BinarySearchTree<String> b = new BinarySearchTree<>();
            b.put("apple");
            assertThat(b.getSize(), is(1));
            b.put("banana");
            assertThat(b.getSize(), is(2));
            b.put("carrot");
            assertThat(b.getSize(), is(3));
            b.put("dragon");
            assertThat(b.getSize(), is(4));
            b.put("eggplant");
            assertThat(b.getSize(), is(5));
            b.put("bed");
            assertThat(b.getSize(), is(6));
            assertThat(b.getPrev("bedroom"), is("bed"));
            assertThat(b.getNext("bedroom"), is("carrot"));
            assertThat(b.contains("banana"), is(true));
            assertThat(b.contains("dog"), is(false));
//            b.inOrder(b.root);
            b.clear();
            assertThat(b.getSize(), is(0));
        });
    }

    @Test
    @Score(1)
    public void sampleTest4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            BinarySearchTree<Integer> b = new BinarySearchTree<>();
            b.put(10);
            assertThat(b.getSize(), is(1));
            b.put(20);
            assertThat(b.getSize(), is(2));
            b.put(30);
            assertThat(b.getSize(), is(3));
            b.put(40);
            assertThat(b.getSize(), is(4));
            b.put(50);
            assertThat(b.getSize(), is(5));
            b.put(25);
            assertThat(b.getSize(), is(6));
            b.remove(10);
            assertThat(b.getSize(), is(5));
            b.put(9);
            assertThat(b.getSize(), is(6));
            b.remove(10);
            assertThat(b.getSize(), is(6));
            b.put(45);
            assertThat(b.getSize(), is(7));
            b.put(55);
            assertThat(b.getSize(), is(8));
            b.put(49);
            assertThat(b.getSize(), is(9));
            b.remove(45);
            assertThat(b.getSize(), is(8));

            assertThat(b.getPrev(10), is(9));
            assertThat(b.getNext(44), is(49));
            assertThat(b.contains(20), is(true));
            assertThat(b.contains(35), is(false));
//            b.inOrder(b.root);
            b.clear();
            assertThat(b.getSize(), is(0));
        });
    }
}
