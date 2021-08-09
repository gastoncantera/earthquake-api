package com.gcantera.gl.earthquakeapi.dto;

import lombok.Data;

@Data
public class Properties {
    Double mag;
    String place;
    Long time;
    Long updated;
    Integer tz;
    String url;
    String detail;
    Integer felt;
    Double cdi;
    Double mmi;
    String alert;
    String status;
    Integer tsunami;
    Integer sig;
    String net;
    String code;
    String ids;
    String sources;
    String types;
    Integer nst;
    Double dmin;
    Double rms;
    Integer gap;
    String magType;
    String type;
    String title;
}
