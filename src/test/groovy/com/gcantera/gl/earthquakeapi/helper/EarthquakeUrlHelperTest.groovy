package com.gcantera.gl.earthquakeapi.helper

import spock.lang.Specification
import spock.lang.Unroll

class EarthquakeUrlHelperTest extends Specification {
    EarthquakeUrlHelper earthquakeUrlHelper = new EarthquakeUrlHelper()

    def setup() {
        earthquakeUrlHelper.setEarthquakeQueryUrl("http://fakequakes/query")
        earthquakeUrlHelper.setEarthquakeCountUrl("http://fakequakes/count")
    }

    @Unroll
    def "test buildQueryByDates"(String startTime, String endTime, String expectedUrl) {
        expect:
        earthquakeUrlHelper.buildQueryByDates(startTime, endTime) == expectedUrl

        where:
        startTime    | endTime      | expectedUrl
        "2019-11-20" | "2019-11-21" | "http://fakequakes/query?format=geojson&starttime=2019-11-20&endtime=2019-11-21"
        "2019-11-20" | null         | "http://fakequakes/query?format=geojson&starttime=2019-11-20"
        null         | "2019-11-21" | "http://fakequakes/query?format=geojson&endtime=2019-11-21"
        null         | null         | "http://fakequakes/query?format=geojson"
    }

    @Unroll
    def "test buildQueryByMagnitudes"(String minMagnitude, String maxMagnitude, String expectedUrl) {
        expect:
        earthquakeUrlHelper.buildQueryByMagnitudes(minMagnitude, maxMagnitude) == expectedUrl

        where:
        minMagnitude | maxMagnitude | expectedUrl
        "4.1"        | "5.3"        | "http://fakequakes/query?format=geojson&minmagnitude=4.1&maxmagnitude=5.3"
        "6.2"        | null         | "http://fakequakes/query?format=geojson&minmagnitude=6.2"
        null         | "7.4"        | "http://fakequakes/query?format=geojson&maxmagnitude=7.4"
        null         | null         | "http://fakequakes/query?format=geojson"
    }

    def "test buildCountByMagnitudes"() {
        given:
        String expectedUrl

        when:
        expectedUrl = earthquakeUrlHelper.buildCountByMagnitudes(null,null)

        then:
        expectedUrl == "http://fakequakes/count?format=geojson"
    }
}
