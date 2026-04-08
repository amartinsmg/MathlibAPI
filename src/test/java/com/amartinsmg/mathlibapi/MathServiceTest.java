package com.amartinsmg.mathlibapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.amartinsmg.mathlibapi.service.MathService;

public class MathServiceTest {

    @Test
    public void testCubeArea() {
        double result = MathService.cubeArea(5);
        assertEquals(150, result, 1e-6);
    }

    @Test
    public void testFactorial() {
        double result = MathService.factorialSmart(12);
        assertEquals(479001600, result, 1e-3);
        result = MathService.factorialSmart(30);
        assertEquals(2.652528598e32, result, 1e23);
    }

}
