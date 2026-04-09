package com.amartinsmg.mathlibapi;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.api.FunctionRegistry;
import com.amartinsmg.mathlibapi.service.MathService;

public class FunctionRegistryTest {

    @Test
    public void testSquareAreaFunctionExists() {
        FunctionRegistry registry = new FunctionRegistry(MathService.class);

        Method squareArea = registry.get("square-area");

        assertNotNull(squareArea);
    }

    @Test
    public void testFactorialFunctionExists() {
        FunctionRegistry registry = new FunctionRegistry(MathService.class);

        Method factorial = registry.get("factorial");

        assertEquals("factorialSmart", factorial.getName());
    }

    @Test
    public void testRectangleArea() throws Exception {
        FunctionRegistry registry = new FunctionRegistry(MathService.class);

        Method rectangleArea = registry.get("rectangle-area");

        Object result = rectangleArea.invoke(null, 3, 5);

        assertEquals(15.0, result);
    }

    @Test
    public void testLcm() throws Exception {
        FunctionRegistry registry = new FunctionRegistry(MathService.class);

        Method lcm = registry.get("lcm");

        Object result = lcm.invoke(null, 8, 10);

        assertEquals(40L, result);
    }

}
