package com.example.bettertogether.DB;

import com.example.bettertogether.JsonMappers.JsonToTestMapper;
import com.example.bettertogether.JsonMappers.PageFilterToJsonMapper;
import com.example.bettertogether.JsonMappers.TestUploadToJsonMapper;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.UploadTest.DBUploadResult;
import com.example.bettertogether.UploadTest.TestToUpload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DB {

    public static DBUploadResult sendTestAsJson(TestToUpload test) {

        String jsonString = TestUploadToJsonMapper.convertToJsonConverter(test);
        System.out.println(jsonString);
        if(jsonString.isEmpty()) {
            return DBUploadResult.FILE_ERROR;
        }

        URL url = null;
        try {
            url = new URL("http://localhost:8083/postTest");
        } catch (MalformedURLException e) {
            return DBUploadResult.FAILED_TO_CONNECT;
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (IOException e) {
                return DBUploadResult.OUTPUT_STREAM_ERROR;
            }
            con.connect();

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                if(response.toString().equals("false")) {
                    return DBUploadResult.TEST_ALREADY_EXISTS;
                }
            } catch (IOException e) {
                return DBUploadResult.OUTPUT_STREAM_ERROR;
            }

        } catch (IOException e) {
            return DBUploadResult.FAILED_TO_CONNECT;
        }

        return DBUploadResult.SUCCESSFUL_CONNECTION;
    }

    public static List getTestPage(int pageNumber, String userName, String testName, List<String> keywords) {
        PageFilter filter = new PageFilter(userName, testName, keywords, pageNumber);
        String jsonString = PageFilterToJsonMapper.convertToJsonConverter(filter);
        if(jsonString.isEmpty()) {
            return new ArrayList<>();
        }

        URL url = null;
        try {
            url = new URL("http://localhost:8083/page");
        } catch (MalformedURLException e) {
            return new ArrayList<>();
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (IOException e) {
                return null;
            }
            con.connect();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                if(response.toString().equals("false")) {
                    return new ArrayList<>();
                }
                return jsonToList(response.toString());
            } catch (IOException e) {
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }

    public static TestToUpload getJsonTestFromDB(String userName, String testName) {

        userName = userName.replaceAll(" ", "%20");
        testName = testName.replaceAll(" ", "%20");
        String id = userName + testName;
        URL url = null;
        try {
            url = new URL("http://localhost:8083/downloadTest/" + id);
        } catch (MalformedURLException e) {
            return null;
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
                JsonToTestMapper mapper = new JsonToTestMapper();
                return mapper.createTestFromJsonText(response.toString());
            } catch (IOException e) {
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }

    private static List jsonToList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException("not supported yet (occurred while mapping json to test class.");
        }
    }

}
