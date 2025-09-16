package com.tlh.travel_randomizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    // 从 application.properties 读取配置
    @Value("${ark.api.key}")
    private String apiKey;

    @Value("${ark.api.url}")
    private String apiUrl;

    @Value("${ark.api.model}")
    private String apiModel;

    private final RestTemplate restTemplate = new RestTemplate();

    // 创建一个内部类来匹配前端发送的JSON结构
    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> chatWithAi(@RequestBody ChatRequest chatRequest) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 构造请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", apiModel);

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "你是一个知识渊博、乐于助人的人工智能旅行助手。");

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", chatRequest.getMessage());

        requestBody.put("messages", List.of(systemMessage, userMessage));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        Map<String, Object> responseMap = new HashMap<>();

        try {
            // 发送请求
            ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

            // 解析响应
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    String reply = (String) message.get("content");
                    responseMap.put("reply", reply);
                    return ResponseEntity.ok(responseMap);
                }
            }
            // 如果未能成功解析，返回错误信息
            responseMap.put("error", "Failed to get a valid response from AI service.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);

        } catch (Exception e) {
            // 捕获异常
            e.printStackTrace();
            responseMap.put("error", "Error while calling AI service: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
        }
    }
}