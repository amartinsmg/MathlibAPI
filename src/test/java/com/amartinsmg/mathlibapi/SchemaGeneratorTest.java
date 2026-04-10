package com.amartinsmg.mathlibapi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.schema.SchemaGenerator;
import com.amartinsmg.mathlibapi.service.MathService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemaGeneratorTest {

    @Test
    void shouldGenerateSchema() {
        SchemaGenerator schema = new SchemaGenerator(MathService.class);

        assertNotNull(schema);
    }

    @Test
    void shoutGetSchema() {
        var schema = new SchemaGenerator(MathService.class).getSchema();

        assertFalse(schema.isEmpty());
    }

    @Test
    void shouldAccessFunction() {
        var fn = new SchemaGenerator(MathService.class)
                .getFunctionSchema("arrangement");

        assertEquals("combinatorics", fn.namespace);
        assertEquals("Calculates the arrangement of selecting items from a total", fn.description);
    }

    @Test
    void shouldSaveSchema() throws Exception {

        if (!Boolean.getBoolean("exportSchema")) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        var schema = new SchemaGenerator(MathService.class).getSchema();

        assertFalse(schema.isEmpty());

        Path path = Paths.get("target/schema.json");

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(path.toFile(), schema);

        assertTrue(Files.exists(path));
    }
}
