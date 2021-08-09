package com.gcantera.gl.earthquakeapi.dto;

import lombok.Data;

@Data
public class Metadata {
    Long generated;
    String url;
    String title;
    Integer status;
    String api;
    Integer count;
}
