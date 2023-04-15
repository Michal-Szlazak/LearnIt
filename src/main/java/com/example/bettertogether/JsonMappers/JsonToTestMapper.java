package com.example.bettertogether.JsonMappers;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonToTestMapper {
    public Test createTestFromJson(String jsonFileName) {

        String jsonFileText = getFileText(jsonFileName);

        if(jsonFileName.isEmpty()) {
            throw new UnsupportedOperationException("not supported yet (occurred when reading json file).");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonFileText, Test.class);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException("not supported yet (occurred while mapping json to test class.");
        }
    }

    private String getFileText(String jsonFileName) {
        Path pathToJson = Path.of(FolderPaths.pathToTestFolder + jsonFileName);
        try {
            return Files.readString(pathToJson);
        } catch (IOException e) {
            return "";
        }
    }

}
