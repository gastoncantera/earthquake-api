package com.gcantera.gl.earthquakeapi.controller;

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto;
import com.gcantera.gl.earthquakeapi.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    /**
     * Retrieves the earthquakes between a date range
     *
     * @param startTime The start date to get the earthquakes (format: yyyy-mm-dd)
     * @param endTime   The end date to get the earthquakes (format: yyyy-mm-dd)
     * @return A response containing the EarthquakeDto {@link EarthquakeDto}
     */
    @GetMapping("/daterange")
    public EarthquakeDto getEarthquakesByDateRange(@RequestParam(name = "starttime") String startTime,
                                                   @RequestParam(name = "endtime") String endTime) {
        return earthquakeService.getEarthquakesByDateRange(startTime, endTime);
    }

    /**
     * Retrieves the earthquakes between a magnitude range
     *
     * @param minMagnitude The minimum magnitude to get the earthquakes (format: n.n)
     * @param maxMagnitude The maximum magnitude to get the earthquakes (format: n.n)
     * @return A response containing the EarthquakeDto {@link EarthquakeDto}
     */
    @GetMapping("/magnituderange")
    public EarthquakeDto getEarthquakesByMagnitudeRange(@RequestParam(name = "minmagnitude") String minMagnitude,
                                                        @RequestParam(name = "maxmagnitude") String maxMagnitude) {
        return earthquakeService.getEarthquakesByMagnitudesRange(minMagnitude, maxMagnitude);
    }

    /**
     * Retrieves the earthquakes occurred in the given country
     *
     * @param country The country to get the earthquakes
     * @return A response containing the EarthquakeDto {@link EarthquakeDto}
     */
    @GetMapping("/country")
    public EarthquakeDto getEarthquakesByCountry(@RequestParam String country) {
        return earthquakeService.getEarthquakesByCountry(country);
    }
}
