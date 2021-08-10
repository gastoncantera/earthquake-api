package com.gcantera.gl.earthquakeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feature {
    String type;
    Properties properties;
    Geometry geometry;
    String id;
}
