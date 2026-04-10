package com.amartinsmg.mathlibapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.service.MathService;

public class MathServiceTest {

    @Test
    public void testCubeArea() {
        double result = MathService.cubeArea(5);
        assertEquals(150, result, 1e-6);
    }

    @Test
    public void testFactorialSmallNumbers() {
        double result = MathService.factorialSmart(12);
        assertEquals(479001600, result, 1e-3);
    }

    @Test
    public void testFactorialLargeNumbers() {
        double result = MathService.factorialSmart(30);
        assertEquals(2.652528598e32, result, 1e23);

    }

    @Test
    public void testFactorialThrowsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> MathService.factorialSmart(-1)
        );
        assertEquals("num must be >= 0", ex.getMessage());
    }

    @Test
    public void testCombinationSmallNumbers() {
        double result = MathService.combinationSmart(15, 12);
        assertEquals(455, result, 1e-6);
    }

    @Test
    public void testCombinationLargeNumbers() {
        double result = MathService.combinationSmart(60, 6);
        assertEquals(50063860, result, 1e-6);
    }

    @Test
    public void shouldCombinationThrowsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> MathService.combinationSmart(10, 11)
        );
        assertEquals("select must be <= total", ex.getMessage());
    }

    @Test
    public void testPoisson() {
        double result = MathService.poisson(5, 2);
        assertEquals(0.08422, result, 1e-5);
    }

    @Test
    public void shouldPoissonThrowsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> MathService.poisson(3, -1)
        );
        assertEquals("x must be >= 0", ex.getMessage());
    }

}
