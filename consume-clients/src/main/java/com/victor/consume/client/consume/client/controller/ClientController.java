package com.victor.consume.client.consume.client.controller;

import com.victor.consume.client.consume.client.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/clients")
    public String getUsers(Model model) {
        Client[] clients = restTemplate.getForObject("http://localhost:8002/v1/client/", Client[].class);
        model.addAttribute("clients", clients);
        return "clients";
    }

}
