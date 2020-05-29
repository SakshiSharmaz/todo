package com.lattice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping(value = "/webhook")
    public void webhook(@RequestBody JsonNode jsonNode) {

        System.out.println(jsonNode.toString());
        System.out.println("Success");
    }
    }
