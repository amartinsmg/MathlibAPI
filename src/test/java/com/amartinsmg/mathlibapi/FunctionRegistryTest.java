package com.amartinsmg.mathlibapi;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.api.FunctionRegistry;
import com.amartinsmg.mathlibapi.service.MathService;

public class FunctionRegistryTest {

    @Test
    public void testSquareAreaFunctionExists() {
        FunctionRegistry registry = new FunctionRegistry();
        registry.register(MathService.class);

        Method squareArea = registry.get("squareArea");

        assertEquals("squareArea", squareArea.getName());
    }

    @Test
    public void testFactorialFunctionExists() {
        FunctionRegistry registry = new FunctionRegistry();
        registry.register(MathService.class);

        Method factorial = registry.get("factorial");

        assertEquals("factorialSmart", factorial.getName());
    }

    @Test
    public void testRetangleArea() throws Exception {
        FunctionRegistry registry = new FunctionRegistry();
        registry.register(MathService.class);

        Method retangleArea = registry.get("retangleArea");

        Object result = retangleArea.invoke(null, 3, 5);

        assertEquals(15.0, result);
    }

    @Test
    public void testLcm() throws Exception {
        FunctionRegistry registry = new FunctionRegistry();
        registry.register(MathService.class);

        Method lcm = registry.get("lcm");

        Object result = lcm.invoke(null, 8, 10);

        assertEquals((long) 40, result);
    }

}
