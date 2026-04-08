package com.amartinsmg.mathlibapi;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;

public class MathLibWrapperTest {

    static double[] arr = {4, 5, 8, 13, 13, 15, 16, 18, 20};

    @Test
    public void testLogarithm() {
        double result = MathLibWrapper.logarithm(65536, 2);
        assertEquals(16, result, 1e-6);
    }

    @Test
    public void testSphereArea() {
        double result = MathLibWrapper.sphereArea(5);
        assertEquals(314.159265, result, 1e-6);
    }

    @Test
    public void testConeVol() {
        double result = MathLibWrapper.coneVol(3, 5);
        assertEquals(47.1238898, result, 1e-6);
    }

    @Test
    public void testGcd() {
        long result = MathLibWrapper.gcd(88, 55);
        assertEquals(11, result);
    }

    @Test
    public void testPrimeFactors() {
        long[] result = MathLibWrapper.primeFactors(1025640),
                expected = {2, 2, 2, 3, 3, 5, 7, 11, 37};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMean() {
        double result = MathLibWrapper.mean(arr);
        assertEquals(12.444444444, result, 1e-6);
    }

    @Test
    public void testMode() {
        double[] result = MathLibWrapper.mode(arr),
                expected = {13};
        assertArrayEquals(expected, result, 1e-6);
    }

}
