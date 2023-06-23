package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubtaskDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("done")
    private Boolean done;
    @JsonProperty("id")
    private Long id;
}
