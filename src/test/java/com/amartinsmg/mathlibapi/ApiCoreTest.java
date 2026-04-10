package com.amartinsmg.mathlibapi;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.core.ApiCore;
import com.amartinsmg.mathlibapi.service.MathService;

public class ApiCoreTest {

    @Test
    public void shouldGenerateCore() {
        var core = new ApiCore(MathService.class);
        assertNotNull(core);
    }

    @Test
    public void shouldThrowsException() throws Exception {
        var core = new ApiCore(MathService.class);

        Map<String, Object> args = Map.of("num", 10.2);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> core.execEngine("prime-factors", args)
        );
        assertEquals("Invalid argument num: expected int64", ex.getMessage());
    }

    @Test
    public void testExecEngine() throws Exception {
        var core = new ApiCore(MathService.class);

        Map<String, Object> args = Map.of("degrees", 180.0);

        var result = core.execEngine("deg-to-rad", args);

        assertInstanceOf(Double.class, result);

        assertEquals(3.14159265, (double) result, 1e-6);
    }

    @Test
    public void testReturnNormaiizer() throws Exception {
        var core = new ApiCore(MathService.class);

        Map<String, Object> args = Map.of("a-x", 0.0, "a-y", 0.0, "b-x", 3.0, "b-y", 5.0);

        var result = core.execEngine("midpoint", args);

        assertInstanceOf(Map.class, result);

        Map<?, ?> map = (Map<?, ?>) result;

        assertEquals(1.5, (double) map.get("x"), 1e-6);
        assertEquals(2.5, (double) map.get("y"), 1e-6);
    }
}
