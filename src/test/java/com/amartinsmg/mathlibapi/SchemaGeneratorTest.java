package com.amartinsmg.mathlibapi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.schema.SchemaGenerator;
import com.amartinsmg.mathlibapi.service.MathService;
import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemaGeneratorTest {

    @Test
    void shouldGenerateAndSaveSchema() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        var schema = SchemaGenerator.generateSchema(MathLibWrapper.class);

        schema.addAll(SchemaGenerator.generateSchema(MathService.class));

        Path path = Paths.get("target/schema.json");

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(path.toFile(), schema);

        assertTrue(Files.exists(path));

    }
}
