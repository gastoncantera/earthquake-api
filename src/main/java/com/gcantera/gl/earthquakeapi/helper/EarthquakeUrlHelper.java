package com.gcantera.gl.earthquakeapi.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EarthquakeUrlHelper {
    @Value("${earthquake.baseUrl}")
    private String earthquakeBaseUrl;

    public String buildEarthquakeUrlByDates(String startTime, String endTime) {
        StringBuilder sb = new StringBuilder(earthquakeBaseUrl);
        sb.append("?").append("format=geojson");
        if (startTime != null) {
            sb.append(sb.indexOf("?") == -1 ? "?" : "&")
                    .append("starttime")
                    .append("=")
                    .append(startTime);
        }
        if (endTime != null) {
            sb.append(sb.indexOf("?") == -1 ? "?" : "&")
                    .append("endtime")
                    .append("=")
                    .append(endTime);
        }
        return sb.toString();
    }

}
