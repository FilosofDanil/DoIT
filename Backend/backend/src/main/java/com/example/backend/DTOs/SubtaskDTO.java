package com.example.backend.DTOs;

import com.example.backend.entities.Tasks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubtaskDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("done")
    private Boolean done;
    @JsonProperty("id")
    private Long id;
    @JsonIgnore
    private Tasks task;
}
