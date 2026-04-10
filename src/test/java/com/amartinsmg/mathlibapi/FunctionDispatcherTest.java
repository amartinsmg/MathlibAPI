package com.amartinsmg.mathlibapi;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.api.FunctionDispatcher;
import com.amartinsmg.mathlibapi.service.MathService;

public class FunctionDispatcherTest {

    @Test
    public void testCirclePerimeterFunctionExists() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        dispatcher.get("circle-perimeter");
    }

    @Test
    public void testThrowsFunctionNotFound() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> dispatcher.get("hey-jude")
        );

        assertEquals("Function not found: hey-jude", ex.getMessage());
    }

    @Test
    public void testTriangleArea1Return() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        Method m = dispatcher.get("triangle-area-1");

        var result = dispatcher.call(m, new Object[]{3.0, 5.0});

        assertInstanceOf(Double.class, result);
    }

    @Test
    public void testIsPrime() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        Method m = dispatcher.get("is-prime");

        var result = dispatcher.call(m, new Object[]{37L});

        assertInstanceOf(Boolean.class, result);

        assertTrue((boolean) result);
    }

}
