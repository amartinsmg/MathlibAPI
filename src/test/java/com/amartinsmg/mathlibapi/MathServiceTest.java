package com.amartinsmg.mathlibapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.core.exceptions.BusinessException;
import com.amartinsmg.mathlibapi.service.MathService;

public class MathServiceTest {

    @Test
    public void testCubeArea() {
        double result = MathService.cubeArea(5);
        assertEquals(150, result, 1e-6);
    }

    @Test
    public void testFactorialSmallNumbers() {
        String result = MathService.factorialSmart(12);
        assertEquals("479001600", result);
    }

    @Test
    public void testFactorialLargeNumbers() {
        String result = MathService.factorialSmart(30);
        assertEquals("2.652528598121910e+32", result);
    }

    @Test
    public void testCombinationSmallNumbers() {
        String result = MathService.combinationSmart(15, 12);
        assertEquals("455", result);
    }

    @Test
    public void testCombinationLargeNumbers() {
        String result = MathService.combinationSmart(60, 6);
        assertEquals("50063860", result);
    }

    @Test
    public void shouldCombinationThrowsException() {
        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> MathService.combinationSmart(10, 11)
        );
        assertEquals("selected must be <= total", ex.getMessage());
    }

    @Test
    public void testPoisson() {
        double result = MathService.poisson(5, 2);
        assertEquals(0.08422, result, 1e-5);
    }
}
