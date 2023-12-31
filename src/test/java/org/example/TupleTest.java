package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TupleTest {
    @Test
    void createTuple() {
        Tuple a = new Tuple(4.3, -4.2, 3.1, 1.0);
        assertEquals(a.getX(),4.3);
        assertEquals(a.getY(), -4.2);
        assertEquals(a.getZ(), 3.1);
        assertEquals(a.getW(), 1.0);
        Tuple b = new Tuple(4.3, -4.2, 3.1, 0.0);
        assertEquals(b.getX(),4.3);
        assertEquals(b.getY(), -4.2);
        assertEquals(b.getZ(), 3.1);
        assertEquals(b.getW(), 0.0);
    }

    @Test
    void createPoint() {
        TupleCreation tp = new TupleCreation();
        Tuple p = tp.point(4, -4, 3);
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), -4);
        assertEquals(p.getZ(), 3);
        assertEquals(p.getW(), 1);
    }

    @Test
    void createVector() {
        TupleCreation tp = new TupleCreation();
        Tuple v = tp.vector(4, -4, 3);
        assertEquals(v.getX(), 4);
        assertEquals(v.getY(), -4);
        assertEquals(v.getZ(), 3);
        assertEquals(v.getW(), 0);
    }

    @Test
    void addTwoVectors() {
        Tuple v1 = new Tuple(3, -2, 5, 0);
        Tuple v2 = new Tuple(-2, 3, 1, 0);
        Tuple res = v1.add(v2);
        assertEquals(res.getX(), 1);
        assertEquals(res.getY(), 1);
        assertEquals(res.getZ(), 6);
        assertEquals(res.getW(), 0);
    }

    @Test
    void addPointVector() {
        TupleCreation tp = new TupleCreation();
        Tuple v = tp.vector(3, -2, 5);
        Tuple p = tp.point(-2, 3, 1);
        Tuple res = v.add(p);
        Tuple res1 = p.add(v);
        assertEquals(res.getX(), 1);
        assertEquals(res.getY(), 1);
        assertEquals(res.getZ(), 6);
        assertEquals(res.getW(), 1);
        assertEquals(res1.getX(), 1);
        assertEquals(res1.getY(), 1);
        assertEquals(res1.getZ(), 6);
        assertEquals(res1.getW(), 1);
    }

    @Test
    void subtractPoints() {
        TupleCreation tp = new TupleCreation();
        Tuple p1 = tp.point(3, 2, 1);
        Tuple p2 = tp.point(5, 6, 7);
        Tuple res = p1.subtract(p2);
        assertEquals(res.getX(), -2);
        assertEquals(res.getY(), -4);
        assertEquals(res.getZ(), -6);
    }

    @Test
    void subtractVectorFromPoint() {
        TupleCreation tp = new TupleCreation();
        Tuple p = tp.point(3, 2, 1);
        Tuple v = tp.vector(5, 6, 7);
        Tuple result = p.subtract(v);
        assertEquals(result.getX(), -2);
        assertEquals(result.getY(), -4);
        assertEquals(result.getZ(), -6);
    }

    @Test
    void subtractVectors() {
        TupleCreation tp = new TupleCreation();
        Tuple v1 = tp.vector(3, 2, 1);
        Tuple v2 = tp.vector(5, 6, 7);
        Tuple result = v1.subtract(v2);
        assertEquals(result.getX(), -2);
        assertEquals(result.getY(), -4);
        assertEquals(result.getZ(), -6);
    }

    @Test
    void negate() {
        Tuple t = new Tuple(1, -2, 3, -4);
        t.negate();
        assertEquals(t.getX(), -1);
        assertEquals(t.getY(), 2);
        assertEquals(t.getZ(), -3);
        assertEquals(t.getW(), 4);
    }

    @Test
    void multiplyByScalar() {
        Tuple t = new Tuple(1, -2, 3, -4);
        Tuple newT = t.multiplyScalar(3.5);
        assertEquals(newT.getX(), 3.5);
        assertEquals(newT.getY(), -7);
        assertEquals(newT.getZ(), 10.5);
        assertEquals(newT.getW(), -14);
        Tuple t2 = new Tuple(1, -2, 3, -4);
        Tuple newT2 = t.multiplyScalar(0.5);
        assertEquals(newT2.getX(), 0.5);
        assertEquals(newT2.getY(), -1);
        assertEquals(newT2.getZ(), 1.5);
        assertEquals(newT2.getW(), -2);
    }

    @Test
    void testMagnitude() {
        TupleCreation tp = new TupleCreation();
        Tuple v1 = tp.vector(1, 2, 3);
        Tuple v2 = tp.vector(-1, -2, -3);
        double m1 = v1.magnitude();
        double m2 = v2.magnitude();
        assertEquals(m1, Math.sqrt(14));
        assertEquals(m2, Math.sqrt(14));
    }

    @Test
    void testNormalization() {
        TupleCreation tp = new TupleCreation();
        Tuple v = tp.vector(4, 0, 0);
        Tuple v_norm = v.normal();
        assertEquals(v_norm.getX(), 1);
        assertEquals(v_norm.getY(), 0);
        assertEquals(v_norm.getZ(), 0);
    }

    @Test
    void testDotProduct() {
        TupleCreation tp = new TupleCreation();
        Tuple a = tp.vector(1, 2, 3);
        Tuple b = tp.vector(2, 3, 4);
        double ans = a.dotProduct(b);
        assertEquals(ans, 20);
    }

    @Test
    void testCrossProduct() {
        TupleCreation tp = new TupleCreation();
        Tuple a = tp.vector(1, 2, 3);
        Tuple b = tp.vector(2, 3, 4);
        Tuple c = a.crossProduct(b);
        Tuple d = b.crossProduct(a);
        Tuple ans1 = new Tuple(-1, 2, -1, 0);
        Tuple ans2 = new Tuple(1, -2, 1, 0);
        boolean eq1 = c.equalTuples(ans1);
        boolean eq2 = d.equalTuples(ans2);
        assertTrue(eq1);
        assertTrue(eq2);
    }

    @Test
    void testColor() {
        Color c = new Color(-0.5, 0.4, 1.7);
        assertEquals(c.getRed(), -0.5);
        assertEquals(c.getGreen(), 0.4);
        assertEquals(c.getBlue(), 1.7);
    }

    @Test
    void addColorTest() {
        Color c1 = new Color(0.9, 0.6, 0.75);
        Color c2 = new Color(0.7, 0.1, 0.25);
        Color res = c1.addColors(c2);
        assertEquals(res.getRed(), 1.6);
        assertEquals(res.getGreen(), 0.7);
        assertEquals(res.getBlue(), 1.0);
    }

    @Test
    void subtractColorTest() {
        Color c1 = new Color(0.9, 0.6, 0.75);
        Color c2 = new Color(0.7, 0.1, 0.25);
        Color res = c1.subtractColors(c2);
        Color test = new Color(0.2, 0.5, 0.5);
        assertTrue(res.equalColors(test));
    }

    @Test
    void scalarColorTest() {
        Color c = new Color(0.2, 0.3, 0.4);
        double scalar = 2;
        Color c_scaled = c.scalarColor(scalar);
        Color test = new Color(0.4, 0.6, 0.8);
        assertTrue(c_scaled.equalColors(test));
    }

    @Test
    void hadamardProductTest() {
        Color c1 = new Color(1, 0.2, 0.4);
        Color c2 = new Color(0.9, 1, 0.1);
        Color res = c1.hadamardProduct(c2);
        Color test = new Color(0.9, 0.2, 0.04);
        assertTrue(res.equalColors(test));

    }
}