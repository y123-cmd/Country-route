package com.example.routing.controller;


import com.example.routing.service.RoutingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService; // This is a private field that will hold an instance of RoutingService

    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/{origin}/{destination}")
    public Map<String, List<String>> getRoute(
            @PathVariable String origin,
            @PathVariable String destination
    ) {
        return Map.of("route", routingService.findRoute(origin, destination));
    }
}
