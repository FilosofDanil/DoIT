package com.example.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DailyTaskDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("today")
    private Date today;
    @JsonIgnore
    private Long id;

}
