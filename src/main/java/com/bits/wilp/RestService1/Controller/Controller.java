package com.bits.wilp.RestService1.Controller;

import com.bits.wilp.RestService1.APIRequestBody;
import com.bits.wilp.RestService1.APIResponse;
import com.opencsv.CSVWriter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("/api/service-1/performance-test")
public class Controller {

    static final String DOWNSTREAM_SERVICE_URL = "http://192.168.29.152:8081/api/service-2/performance-test";

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public APIResponse getMappingRequest() throws IOException {
        APIResponse response = new APIResponse();
        FileWriter writer = new FileWriter("PerformanceResultGetCall.csv", true);
        Long t1 = System.currentTimeMillis();
        writer.append(t1.toString());
        writer.append(",");
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(DOWNSTREAM_SERVICE_URL, Object.class);
        Long t2 = System.currentTimeMillis();
        writer.append(t2.toString());
        writer.append(",");
        long difference = t2-t1;
        writer.append(Long.toString(difference));
        writer.append("\n");
        response.setError("");
        response.setStatus("Success");
        response.setResult(result != null ? result.toString() : "");
        writer.close();
        return response;
    }

    @PostMapping
    public APIResponse postMappingRequest(@RequestBody Map<String, Object> payload) throws IOException {
        APIResponse response = new APIResponse();
        FileWriter writer = new FileWriter("PerformanceResultPostCall.csv", true);
        Long t1 = System.currentTimeMillis();
        writer.append(t1.toString());
        writer.append(",");
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.postForObject(DOWNSTREAM_SERVICE_URL, payload, Object.class);
        Long t2 = System.currentTimeMillis();
        writer.append(t2.toString());
        writer.append(",");
        long difference = t2-t1;
        writer.append(Long.toString(difference));
        writer.append("\n");
        writer.close();
        response.setError("");
        response.setStatus("Success");
        response.setResult(result != null ? result.toString() : "");
        return response;
    }

    @PostMapping
    @RequestMapping("/2")
    public APIResponse postMappingRequest2(@RequestBody Map<String, Object> payload) throws IOException, InterruptedException {
        APIResponse response = new APIResponse();
        FileWriter writer = new FileWriter("PerformanceResultPostCall2.csv", true);
        Long t1 = System.currentTimeMillis();

        writer.append(t1.toString());
        writer.append(",");
        RestTemplate restTemplate = new RestTemplate();

        //Thread.sleep(20);
        Object result = restTemplate.postForObject(DOWNSTREAM_SERVICE_URL, payload, Object.class);
        Long t2 = System.currentTimeMillis();
        writer.append(t2.toString());
        writer.append(",");
        long difference = t2-t1;
        writer.append(Long.toString(difference));
        writer.append("\n");
        writer.close();
        response.setError("");
        response.setStatus("Success");
        response.setResult(result != null ? result.toString() : "");
        return response;
    }
}
