package com.gcantera.gl.earthquakeapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class EarthquakeDto {
    String type;
    Metadata metadata;
    List<Feature> features;
    Double[] bbox;
}
