package com.gcantera.gl.earthquakeapi.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EarthquakeUrlHelper {
    @Value("${earthquake.query-url}")
    private String earthquakeQueryUrl;

    public String buildEarthquakeUrlByDates(String startTime, String endTime) {
        StringBuilder sb = new StringBuilder(earthquakeQueryUrl);
        sb.append("?").append("format=geojson");
        if (startTime != null) {
            sb.append("&")
                    .append("starttime")
                    .append("=")
                    .append(startTime);
        }
        if (endTime != null) {
            sb.append("&")
                    .append("endtime")
                    .append("=")
                    .append(endTime);
        }
        return sb.toString();
    }

    public String buildEarthquakeUrlByMagnitudes(String minMagnitude, String maxMagnitude) {
        StringBuilder sb = new StringBuilder(earthquakeQueryUrl);
        sb.append("?").append("format=geojson");
        if (minMagnitude != null) {
            sb.append("&")
                    .append("minmagnitude")
                    .append("=")
                    .append(minMagnitude);
        }
        if (maxMagnitude != null) {
            sb.append("&")
                    .append("maxmagnitude")
                    .append("=")
                    .append(maxMagnitude);
        }
        return sb.toString();
    }

}
