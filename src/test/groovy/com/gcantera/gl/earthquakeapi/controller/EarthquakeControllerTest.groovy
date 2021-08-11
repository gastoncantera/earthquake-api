package com.gcantera.gl.earthquakeapi.controller

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto
import com.gcantera.gl.earthquakeapi.dto.Feature
import com.gcantera.gl.earthquakeapi.dto.Geometry
import com.gcantera.gl.earthquakeapi.dto.Metadata
import com.gcantera.gl.earthquakeapi.dto.Properties
import com.gcantera.gl.earthquakeapi.service.EarthquakeService
import spock.lang.Specification

class EarthquakeControllerTest extends Specification {
    private EarthquakeService earthquakeService = Mock {}
    private EarthquakeController earthquakeController = new EarthquakeController(earthquakeService)

    def "test getEarthquakesByDateRange"() {
        given:
        EarthquakeDto response
        String startTime = "2021-08-08"
        String endTime = "2021-08-10"

        when:
        earthquakeService.getEarthquakesByDateRange(startTime, endTime) >> fakeEarthquakeDto()

        response = earthquakeController.getEarthquakesByDateRange(startTime, endTime)

        then:
        response != null
        response.features.size() == 1
        response.metadata.count == 1
    }

    private EarthquakeDto fakeEarthquakeDto() {
        Geometry geometry = Geometry.builder()
                .coordinates()
                .type("fake")
                .build();
        Properties properties = Properties.builder()
                .place("Bahia Blanca, Argentina")
                .mag(2.6)
                .build();
        Feature feature = Feature.builder()
                .geometry(geometry)
                .id("fake")
                .properties(properties)
                .type("fake")
                .build()
        List<Feature> features = new ArrayList<>();
        features.add(feature);

        EarthquakeDto earthquakeDto = EarthquakeDto.builder()
                .bbox()
                .features(features)
                .metadata(Metadata.builder()
                        .count(features.size())
                        .build())
                .type("fake")
                .build();

        return earthquakeDto;
    }
}
