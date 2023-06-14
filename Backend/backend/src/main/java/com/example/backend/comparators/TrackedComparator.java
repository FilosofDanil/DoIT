package com.example.backend.comparators;

import com.example.backend.DTOs.TrackedDaysDTO;

import java.util.Comparator;

public class TrackedComparator implements Comparator<TrackedDaysDTO> {
    @Override
    public int compare(TrackedDaysDTO o1, TrackedDaysDTO o2) {
        if (o1.getMarking_day().before(o2.getMarking_day())) {
            return -1;
        }
        if (o1.getMarking_day().after(o2.getMarking_day())) {
            return 1;
        }
        return 0;
    }
}
