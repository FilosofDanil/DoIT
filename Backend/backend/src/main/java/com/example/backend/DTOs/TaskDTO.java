package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class TaskDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("today")
    private Date today;
    @JsonProperty("done")
    private Boolean done;
    @JsonIgnore
    private Long id;
    @JsonProperty("daily_id")
    private Long daily_id;
}
