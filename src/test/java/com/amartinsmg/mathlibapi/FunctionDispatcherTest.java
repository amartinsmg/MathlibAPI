package com.amartinsmg.mathlibapi;

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

        dispatcher.call("circle-perimeter", new Object[]{14.0});
    }

    @Test
    public void testThrowsFunctionNotFound() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> dispatcher.call("hey-jude", new Object[]{})
        );

        assertEquals("Function not found: hey-jude", ex.getMessage());
    }

    @Test
    public void testTriangleArea1Return() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        var result = dispatcher.call("triangle-area-1", new Object[]{3.0, 5.0});

        assertInstanceOf(Double.class, result);
    }

    @Test
    public void testIsPrime() throws Exception {
        FunctionDispatcher dispatcher = new FunctionDispatcher(MathService.class);

        var result = dispatcher.call("is-prime", new Object[]{37L});

        assertInstanceOf(Boolean.class, result);

        assertTrue((boolean) result);
    }

}
