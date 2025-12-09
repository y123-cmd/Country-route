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
    } //makes the class a routing controller

    @GetMapping("/{origin}/{destination}")
    public Map<String, List<String>> getRoute( // converst the map to json for the https response
            @PathVariable String origin, // takes origin and pass it to method as string
            @PathVariable String destination
    ) {
        return Map.of("route", routingService.findRoute(origin, destination)); // service to find route
    }
}
