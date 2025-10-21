package Lab3.src;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodingBatTest {
    CodingBat cb = new CodingBat();

    @Test
    public void testBackAround() {
        assertEquals("tcatt", cb.backAround("cat"));
        assertEquals("oHelloo", cb.backAround("Hello"));
        assertEquals("aaa", cb.backAround("a"));
    }

    @Test
    public void testDiff21() {
        assertEquals(2, cb.diff21(19));
        assertEquals(0, cb.diff21(21));
        assertEquals(2, cb.diff21(22));
    }

    @Test
    public void testHelloName() {
        assertEquals("Hello Bob!", cb.helloName("Bob"));
        assertEquals("Hello Alice!", cb.helloName("Alice"));
        assertEquals("Hello Jack!", cb.helloName("Jack"));
    }

    @Test
    public void testCountEvens() {
        assertEquals(3, cb.countEvens(new int[]{2, 1, 2, 3, 4}));
        assertEquals(0, cb.countEvens(new int[]{1,3,5}));
        assertEquals(5, cb.countEvens(new int[]{2, 2, 0, 4, 10}));
    }
}