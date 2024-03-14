package com.beantastic.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

public class ApiCaller<R> {

    private final String uri;

    private final String accessToken;

    private final Class<R[]> rClass;

    private final RestClient defaultClient;

    public ApiCaller(String uri, String accessToken, Class<R[]> rClass) {
        this.uri = uri;
        this.accessToken = accessToken;
        this.rClass = rClass;
        this.defaultClient = RestClient.create();
    }

    private String getApiCall() {
        String result = defaultClient.get()
                .uri(uri)
                .header("Authorization", ("Bearer " + accessToken))
                .retrieve()
                .body(String.class);

        if (result == null || result.trim().isEmpty())
            throw new IllegalArgumentException("Server sent back empty data");

        return result;
    }

    public List<R> getDTO() {
        try {
            return Arrays.stream(new ObjectMapper().readValue(getApiCall(), rClass)).toList();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Server sent back malformed data");
        }
    }
}
