package com.gcantera.gl.earthquakeapi.service;

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto;

public interface EarthquakeService {

    EarthquakeDto getEarthquakesByDateRange(String startTime, String endTime);

    EarthquakeDto getEarthquakesByMagnitudesRange(String minMagnitude, String maxMagnitude);

}
