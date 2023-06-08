package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HabitsDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("day_count")
    private Integer day_count;
    @JsonProperty("description")
    private String description ;
    @JsonProperty("completed")
    private Boolean completed;
}
