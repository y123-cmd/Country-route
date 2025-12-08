package com.example.routing.service;

import com.example.routing.loader.CountryDataLoader;
import com.example.routing.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RoutingService {

    private final Map<String, Country> countryMap;

    public RoutingService(CountryDataLoader loader) {
        this.countryMap = loader.getCountryMap();
    }

    public List<String> findRoute(String origin, String destination) {
        origin = origin.toUpperCase();
        destination = destination.toUpperCase();

        if (!countryMap.containsKey(origin) || !countryMap.containsKey(destination)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid country code");
        }
        if (origin.equals(destination)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No land route found: origin and destination are the same");
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(List.of(origin));
        visited.add(origin);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (current.equals(destination)) {
                return path;
            }


            for (String neighbour : countryMap.get(current).getBorders()) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);

                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbour);
                    queue.add(newPath);
                }
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No land route found");
    }
}