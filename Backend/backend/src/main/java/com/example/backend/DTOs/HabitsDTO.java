package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class HabitsDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("day_count")
    private Integer day_count;
    @JsonProperty("description")
    private String description;
    @JsonProperty("completed")
    private Boolean completed;
    @JsonProperty("tracks")
    private List<TrackedDaysDTO> tracks;
}
