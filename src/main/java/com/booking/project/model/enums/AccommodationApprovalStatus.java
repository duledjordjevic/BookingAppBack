package com.booking.project.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccommodationApprovalStatus {
    @JsonProperty("APPROVED")
    APPROVED,
    @JsonProperty("PENDING")
    PENDING,
    @JsonProperty("DECLINED")
    DECLINED

//    private final String text;
//    private AccommodationApprovalStatus(final String text) {
//        this.text = text;
//    }
//    @JsonValue
//    public String toString() {
//        return text;
//    }
}
