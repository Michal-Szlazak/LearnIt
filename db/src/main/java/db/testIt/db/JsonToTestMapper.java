package db.testIt.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.testIt.db.Test.Test;

public class JsonToTestMapper {
    public Test createTestFromJson(String jsonFileText) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonFileText, Test.class);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException("not supported yet (occurred while mapping json to test class.");
        }
    }
}
