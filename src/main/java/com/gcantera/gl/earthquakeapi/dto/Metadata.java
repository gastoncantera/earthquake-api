package com.gcantera.gl.earthquakeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    Long generated;
    String url;
    String title;
    Integer status;
    String api;
    Integer count;
}
