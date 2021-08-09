package com.gcantera.gl.earthquakeapi.service.impl;

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto;
import com.gcantera.gl.earthquakeapi.dto.Feature;
import com.gcantera.gl.earthquakeapi.helper.EarthquakeUrlHelper;
import com.gcantera.gl.earthquakeapi.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    @Autowired
    private RestTemplate restClient;

    @Autowired
    private EarthquakeUrlHelper earthquakeUrlHelper;

    @Override
    public EarthquakeDto getEarthquakesByDateRange(String startTime, String endTime) {
        EarthquakeDto earthquakeDto = null;

        ResponseEntity<EarthquakeDto> response = restClient.exchange(
                earthquakeUrlHelper.buildQueryByDates(startTime, endTime),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                EarthquakeDto.class
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            earthquakeDto = response.getBody();
        }

        return earthquakeDto;
    }

    @Override
    public EarthquakeDto getEarthquakesByMagnitudesRange(String minMagnitude, String maxMagnitude) {
        EarthquakeDto earthquakeDto = null;

        ResponseEntity<EarthquakeDto> response = restClient.exchange(
                earthquakeUrlHelper.buildQueryByMagnitudes(minMagnitude, maxMagnitude),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                EarthquakeDto.class
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            earthquakeDto = response.getBody();
        }

        return earthquakeDto;
    }

    @Override
    public EarthquakeDto getEarthquakesByCountry(String country) {
        EarthquakeDto earthquakeDto = getEarthquakesByDateRange(null, null);

        if (earthquakeDto != null) {
            List<Feature> filteredFeatures = earthquakeDto.getFeatures().stream()
                    .filter(feature -> feature.getProperties().getTitle().toLowerCase().endsWith(country.toLowerCase()))
                    .collect(Collectors.toList());

            earthquakeDto.setFeatures(filteredFeatures);
            earthquakeDto.getMetadata().setCount(filteredFeatures.size());
        }

        return earthquakeDto;
    }

}
