package com.booking.project.dto;

import com.booking.project.model.Address;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HostDTO {
    private Long id;
    private String name;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private boolean isNotificationEnabled;
    private UserCredentialsDTO userCredentialsDTO;

    public HostDTO(Host host){
        this.id = host.getId();
        this.name = host.getName();
        this.lastName = host.getLastName();
        this.address = host.getAddress();
        this.phoneNumber = host.getPhoneNumber();
        this.isNotificationEnabled = host.isNotificationEnabled();
        this.userCredentialsDTO = new UserCredentialsDTO(host.getUser());
    }
}
