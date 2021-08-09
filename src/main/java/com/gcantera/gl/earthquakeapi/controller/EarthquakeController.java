package com.gcantera.gl.earthquakeapi.controller;

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto;
import com.gcantera.gl.earthquakeapi.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/daterange")
    public EarthquakeDto getEarthquakesByDateRange(@RequestParam(name = "starttime") String startTime,
                                                   @RequestParam(name = "endtime") String endTime) {
        return earthquakeService.getEarthquakesByDateRange(startTime, endTime);
    }

    @GetMapping("/magnituderange")
    public EarthquakeDto getEarthquakesByMagnitudeRange(@RequestParam(name = "minmagnitude") String minMagnitude,
                                                        @RequestParam(name = "maxmagnitude") String maxMagnitude) {
        return earthquakeService.getEarthquakesByMagnitudesRange(minMagnitude, maxMagnitude);
    }
}
