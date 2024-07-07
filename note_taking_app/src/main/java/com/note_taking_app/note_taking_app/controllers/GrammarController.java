package com.note_taking_app.note_taking_app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/n")
public class GrammarController {
    private static final String API_URL = "https://api.languagetool.org/v2/check";
    private static final Logger logger = LoggerFactory.getLogger(GrammarController.class);

    @PostMapping("/grammar_check")
    public ResponseEntity<String> checkGrammar(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");

        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        // Set up parameters as form data
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);
        body.add("language", "en-US");

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create HttpEntity with headers and parameters
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // Parse the JSON response
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode matches = root.path("matches");

            StringBuilder result = new StringBuilder();
            for (JsonNode match : matches) {
                String message = match.path("message").asText();
                String sentence = match.path("context").path("text").asText();
                String shortMessage = match.path("shortMessage").asText();
                result.append(String.format("Sentence: %s%n", sentence))
                        .append(String.format("Message: %s%n", message))
                        .append(String.format("Short Message: %s%n", shortMessage));
            }

            return ResponseEntity.ok(result.toString());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("HTTP error: " + e.getResponseBodyAsString());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("JSON processing error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}
