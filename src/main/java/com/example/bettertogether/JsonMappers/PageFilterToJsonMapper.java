package com.example.bettertogether.JsonMappers;

import com.example.bettertogether.DB.PageFilter;
import com.example.bettertogether.Test.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PageFilterToJsonMapper {

    public static String convertToJsonConverter(PageFilter filter) {
        ObjectMapper ow = new ObjectMapper();
        try {
            return ow.writeValueAsString(filter);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
