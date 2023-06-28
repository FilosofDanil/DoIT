package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class TrackedDaysDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("marked")
    private Boolean marked;
    @JsonIgnore
    private Date marking_day;
    @JsonProperty("status")
    private String status;
    @JsonProperty(("marking_day"))
    private String date;
}
