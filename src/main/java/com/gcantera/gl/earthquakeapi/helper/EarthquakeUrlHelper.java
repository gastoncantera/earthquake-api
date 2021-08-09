package com.gcantera.gl.earthquakeapi.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EarthquakeUrlHelper {
    @Value("${earthquake.query-url}")
    private String earthquakeQueryUrl;

    @Value("${earthquake.count-url}")
    private String earthquakeCountUrl;

    public String buildQueryByDates(String startTime, String endTime) {
        return buildQuery(earthquakeQueryUrl, "starttime", startTime, "endtime", endTime);
    }

    public String buildCountByDates(String startTime, String endTime) {
        return buildQuery(earthquakeCountUrl, "starttime", startTime, "endtime", endTime);
    }

    public String buildQueryByMagnitudes(String minMagnitude, String maxMagnitude) {
        return buildQuery(earthquakeQueryUrl, "minmagnitude", minMagnitude, "maxmagnitude", maxMagnitude);
    }

    private String buildQuery(String url,
                              String param1Label, String param1,
                              String param2Label, String param2
    ) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?").append("format=geojson");
        if (param1 != null) {
            sb.append("&")
                    .append(param1Label)
                    .append("=")
                    .append(param1);
        }
        if (param2 != null) {
            sb.append("&")
                    .append(param2Label)
                    .append("=")
                    .append(param2);
        }
        return sb.toString();
    }

}
