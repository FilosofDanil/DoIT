package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    @JsonProperty("id")
    private Long id;
    @JsonProperty("daily_id")
    private Long daily_id;
    @JsonProperty("subtasks")
    private List<SubtaskDTO> subtasks;
}
