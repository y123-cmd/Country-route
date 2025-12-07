package com.example.routing.loader;

import com.example.routing.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryDataLoader {

    private final Map<String, Country> countryMap = new HashMap<>();

    public Map<String, Country> getCountryMap() {
        return countryMap;
    }

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonURL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

        URI uri = new URI(jsonURL);
        URL url = uri.toURL();

        try (InputStream in = url.openStream()) {
            List<Country> countries = mapper.readValue(in, new TypeReference<List<Country>>() {
            });

            for (Country c : countries) {
                countryMap.put(c.getCca3(), c);
            }
        }


    }
}