package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskDTO {
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Long id;
}
