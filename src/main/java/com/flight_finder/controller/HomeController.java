package com.flight_finder.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Controller
@RequestMapping("/api")
public class HomeController {

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/public")
    @ResponseBody
    public String index() {
        return "That's pretty basic!";
    }

    @RequestMapping("/search")
    @ResponseBody
    public String index(@RequestParam(value = "departure_airport_code", required = false) String departure_airport_code,
                        @RequestParam(value = "arrival_airport_code", required = false) String arrival_airport_code,
                        @RequestParam(value = "flight_class", required = false) String flight_class) throws IOException {
        return objectMapper.writeValueAsString(objectMapper.readValue(new ClassPathResource("response.json").getFile(), JsonNode.class));
    }

}
