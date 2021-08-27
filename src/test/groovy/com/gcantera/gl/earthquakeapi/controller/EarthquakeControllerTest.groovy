package com.gcantera.gl.earthquakeapi.controller

import com.gcantera.gl.earthquakeapi.dto.EarthquakeDto
import com.gcantera.gl.earthquakeapi.dto.Feature
import com.gcantera.gl.earthquakeapi.dto.Geometry
import com.gcantera.gl.earthquakeapi.dto.Metadata
import com.gcantera.gl.earthquakeapi.dto.Properties
import com.gcantera.gl.earthquakeapi.helper.JwtTokenHelper
import com.gcantera.gl.earthquakeapi.service.EarthquakeService
import com.gcantera.gl.earthquakeapi.service.impl.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [EarthquakeController])
class EarthquakeControllerTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @Autowired
    EarthquakeService earthquakeService

    @WithMockUser(roles = ['FULL_ACCESS'])
    def "test getEarthquakesByDateRange"() {
        given:
        String startTime = "2021-08-08"
        String endTime = "2021-08-10"

        and:
        earthquakeService.getEarthquakesByDateRange(startTime, endTime) >> fakeEarthquakeDto()

        when:
        def results = mockMvc.perform(get('/daterange')
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("starttime", startTime)
                .param("endtime", endTime)
        )

        then:
        results.andExpect(status().is2xxSuccessful())
        results.andExpect(jsonPath('$.features').exists())
        results.andExpect(jsonPath('$.features').isArray())
    }

    def "test getEarthquakesByMagnitudeRange"() {
        given:
        String startTime = "2021-08-08"
        String endTime = "2021-08-10"

        when:
        def results = mockMvc.perform(get('/magnituderange')
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("starttime", startTime)
                .param("endtime", endTime)
        )

        then:
        results.andExpect(status().isUnauthorized())
    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        JwtTokenHelper jwtTokenHelper() {
            return new JwtTokenHelper();
        }

        @Bean
        UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }

        @Bean
        EarthquakeService earthquakeService() {
            return detachedMockFactory.Stub(EarthquakeService)
        }
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
