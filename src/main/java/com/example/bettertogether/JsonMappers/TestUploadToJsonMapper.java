package com.example.bettertogether.JsonMappers;

import com.example.bettertogether.UploadTest.TestToUpload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUploadToJsonMapper {

    public static String convertToJsonConverter(TestToUpload test) {
        ObjectMapper ow = new ObjectMapper();
        try {
            return ow.writeValueAsString(test);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
