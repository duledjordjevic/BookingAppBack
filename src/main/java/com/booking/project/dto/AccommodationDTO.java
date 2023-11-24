package com.booking.project.dto;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Address;
import com.booking.project.model.Photo;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccomodationType;
import com.booking.project.model.enums.Amenities;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class AccommodationDTO {
    private Long id;
    private String title;
    private String description;
    private Address address;
    private List<Amenities> amenities;
    private Set<Photo> photos;
    private int minGuest;
    private int maxGuest;
    private AccomodationType type;
    private Set<PriceList> prices;
    private Long hostId;

    public AccommodationDTO(Accommodation accommodation){
        this.id = accommodation.getId();
        this.title = accommodation.getTitle();
        this.description = accommodation.getDescription();
        this.address = accommodation.getAddress();
        this.amenities = accommodation.getAmenities();
        this.photos = accommodation.getPhotos();
        this.minGuest = accommodation.getMinGuests();
        this.maxGuest = accommodation.getMaxGuests();
        this.type = accommodation.getType();
        this.prices = accommodation.getPrices();
        this.hostId = accommodation.getHost().getId();
    }


}
