package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class TrackedDaysDTO {
    @JsonProperty("marked")
    private Boolean marked;
    @JsonProperty("marking_day")
    private Date marking_day;
}
