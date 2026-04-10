package com.amartinsmg.mathlibapi;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.api.ApiCore;
import com.amartinsmg.mathlibapi.service.MathService;

public class ApiCoreTest {

    @Test
    public void testCreateApiCore() {
        var core = new ApiCore(MathService.class);
        assertNotNull(core);
    }

    @Test
    public void testEngine() throws Exception {
        var core = new ApiCore(MathService.class);

        Map<String, Object> args = new HashMap<>();

        args.put("degrees", 180.0);

        var result = core.engine("deg-to-rad", args);

        assertEquals(3.14159265, (double) result, 1e-6);
    }
}
