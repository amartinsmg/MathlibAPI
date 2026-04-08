package com.amartinsmg.mathlibapi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;

public class MathLibWrapperTest {

    static double[] arr = {4, 5, 8, 13, 13, 15, 16, 18, 20};

    @Test
    public void testGcd(){
        long result = MathLibWrapper.gcd(88, 55);
        assertEquals(11, result);
    }

    @Test
    public void testMean() {
        double result = MathLibWrapper.mean(arr);
        assertTrue(Math.abs(result - 12.444444444) < 1e-6);
    }

    @Test
    public void testMode() {
        double[] result = MathLibWrapper.mode(arr),
                expected = {13};
        assertArrayEquals(expected, result, 1e-6);
    }

}
