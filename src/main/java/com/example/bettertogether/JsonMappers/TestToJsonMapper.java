package com.example.bettertogether.JsonMappers;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestToJsonMapper {
    public void convertToJsonConverter(Test test) {
        ObjectMapper ow = new ObjectMapper();
        try {
            String json = ow.writeValueAsString(test);
            createJsonFile(test.getTestName(), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void createJsonFile(String filename, String json) {
        File file = new File(FolderPaths.pathToTestFolder + filename + ".json");
        if(file.exists()) {
            file.delete();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTestAsJson(Test test) {
        ObjectMapper ow = new ObjectMapper();
        String json;
        try {
            json = ow.writeValueAsString(test);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
