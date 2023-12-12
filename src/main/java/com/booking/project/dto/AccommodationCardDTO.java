package com.booking.project.dto;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Address;
import com.booking.project.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationCardDTO {
    private Long id;
    private String title;
    private Address address;
    private String description;
    private Double avgRate;
    private Double pricePerNight;
    private Double totalPrice;
    private byte[] image;

    public AccommodationCardDTO(Accommodation accommodation){
        this.id = accommodation.getId();
        this.title = accommodation.getTitle();
        this.address = accommodation.getAddress();
        this.description = accommodation.getDescription();
    }
}
