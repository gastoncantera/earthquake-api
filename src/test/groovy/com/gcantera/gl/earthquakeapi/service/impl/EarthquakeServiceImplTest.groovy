package com.gcantera.gl.earthquakeapi.service.impl

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto
import com.gcantera.gl.earthquakeapi.dto.Feature
import com.gcantera.gl.earthquakeapi.dto.Geometry
import com.gcantera.gl.earthquakeapi.dto.Metadata
import com.gcantera.gl.earthquakeapi.dto.Properties
import com.gcantera.gl.earthquakeapi.helper.EarthquakeUrlHelper
import com.gcantera.gl.earthquakeapi.service.EarthquakeService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class EarthquakeServiceImplTest extends Specification {
    EarthquakeUrlHelper earthquakeUrlHelper = Mock {}
    RestTemplate restTemplate = Mock {}
    EarthquakeService earthquakeService = new EarthquakeServiceImpl(restTemplate, earthquakeUrlHelper)

    def "test getEarthquakesByDateRange"() {
        given:
        EarthquakeDto response
        String startTime = "2021-08-08"
        String endTime = "2021-08-10"
        String url = "http://fakequakes?format=geoformat&starttime=" + startTime + "&endtime=" + endTime

        when:
        earthquakeUrlHelper.buildQueryByDates(startTime, endTime) >> url
        restTemplate.exchange(url, HttpMethod.GET, _ as HttpEntity, EarthquakeDto.class) >> new ResponseEntity(fakeEarthquakeDto(), HttpStatus.OK)

        response = earthquakeService.getEarthquakesByDateRange(startTime, endTime)

        then:
        response != null
        response.features.size() == 1
        response.metadata.count == 1
    }

    private EarthquakeDto fakeEarthquakeDto() {
        Geometry geometry = Geometry.builder()
                .coordinates()
                .type("fake")
                .build()
        Properties properties = Properties.builder()
                .place("Bahia Blanca, Argentina")
                .mag(2.6)
                .build()
        Feature feature = Feature.builder()
                .geometry(geometry)
                .id("fake")
                .properties(properties)
                .type("fake")
                .build()
        List<Feature> features = new ArrayList<>()
        features.add(feature)

        EarthquakeDto earthquakeDto = EarthquakeDto.builder()
                .bbox()
                .features(features)
                .metadata(Metadata.builder()
                        .count(features.size())
                        .build())
                .type("fake")
                .build()

        return earthquakeDto
    }
}
