package com.booking.project.dto;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Address;
import com.booking.project.model.Photo;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccomodationType;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.CancellationPolicy;
import com.booking.project.model.enums.ReservationMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {
    private Long id;
    private String title;
    private String description;
    private Address address;
    private List<Amenities> amenities;
    private String images;
    private int minGuest;
    private int maxGuest;
    private AccomodationType type;
    private CancellationPolicy cancellationPolicy;
    private ReservationMethod reservationMethod;
    private boolean isAvailableForReservation;
    private boolean isPriceForEntireAcc;
    private Set<PriceList> prices;
    private Long hostId;

    public AccommodationDTO(Accommodation accommodation){
        this.id = accommodation.getId();
        this.title = accommodation.getTitle();
        this.description = accommodation.getDescription();
        this.address = accommodation.getAddress();
        this.amenities = accommodation.getAmenities();
        this.images = accommodation.getImages();
        this.minGuest = accommodation.getMinGuests();
        this.maxGuest = accommodation.getMaxGuests();
        this.type = accommodation.getType();
        this.prices = accommodation.getPrices();
        this.hostId = accommodation.getHost().getId();
        this.isPriceForEntireAcc = accommodation.isPriceForEntireAcc();
        this.isAvailableForReservation = accommodation.isAvailableForReservation();
        this.reservationMethod = accommodation.getReservationMethod();
        this.cancellationPolicy = accommodation.getCancellationPolicy();
    }


}
