package com.gcantera.gl.earthquakeapi.dto;

import lombok.Data;

@Data
public class Feature {
    String type;
    Properties properties;
    Geometry geometry;
    String id;
}
