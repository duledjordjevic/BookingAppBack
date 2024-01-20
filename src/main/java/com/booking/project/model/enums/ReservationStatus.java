package com.booking.project.model.enums;

import com.booking.project.dto.ReservationDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//@JsonDeserialize(using = ReservationDTO.ReservationStatusDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ReservationStatus {
    PENDING,
    ACCEPTED,
    EXPIRED,
    DECLINED,
    CANCELLED;

}
