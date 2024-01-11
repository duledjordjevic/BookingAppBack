package com.booking.project.service;

import com.booking.project.dto.UserBlockDTO;
import com.booking.project.dto.UserDTO;
import com.booking.project.dto.UserInfoDTO;
import com.booking.project.dto.UserReportDTO;
import com.booking.project.model.*;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserReportRepository;
import com.booking.project.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserReportService implements IUserReportService {
    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IGuestService guestService;
    @Autowired
    private IHostService hostService;
    @Override
    public Collection<UserBlockDTO> findAll(){
        Collection<UserReport> userReports = userReportRepository.findAll();
        Collection<UserBlockDTO> userForBlock = new ArrayList<>();

        for(UserReport userReport: userReports){
            Optional<User> userReported = userService.findById(userReport.getReportedUser().getId());
            Optional<User> userReporting = userService.findById(userReport.getReportingUser().getId());

            Optional<Reservation> reservation = reservationService.findById(userReport.getReservation().getId());

            if(userReported.get().getUserType().equals(UserType.GUEST)){
                Optional<Guest> guestReported = guestService.findByUser(userReported.get().getId());
                Host hostReporting = hostService.findByUser(userReporting.get().getId());

                UserInfoDTO userInfoReported = new UserInfoDTO(guestReported.get(),userReported.get());
                UserInfoDTO userInfoReporting = new UserInfoDTO(hostReporting, userReporting.get());

                UserBlockDTO userBlockDTO = new UserBlockDTO(userReport.getId(),userReport.getReason(),userInfoReported,
                        userInfoReporting,reservation.get().getAccommodation().getTitle(),reservation.get().getStartDate(),
                        reservation.get().getEndDate());
                userForBlock.add(userBlockDTO);
            }else if(userReported.get().getUserType().equals(UserType.HOST)){
                Host hostReported = hostService.findByUser(userReported.get().getId());
                Optional<Guest> guestReporting = guestService.findByUser(userReporting.get().getId());

                UserInfoDTO userInfoReported = new UserInfoDTO(hostReported, userReported.get());
                UserInfoDTO userInfoReporting = new UserInfoDTO(guestReporting.get(),userReporting.get());

                UserBlockDTO userBlockDTO = new UserBlockDTO(userReport.getId(),userReport.getReason(),userInfoReported,
                        userInfoReporting,reservation.get().getAccommodation().getTitle(),reservation.get().getStartDate(),
                        reservation.get().getEndDate());
                userForBlock.add(userBlockDTO);
            }

        }
        return userForBlock;
    }
    @Override
    public UserReportDTO create(UserReportDTO userReportDTO) throws Exception {
        UserReport userReport = new UserReport();
        Optional<User> userReported = userService.findById(userReportDTO.getUserReportedId());
        Optional<User> userReporting = userService.findById(userReportDTO.getUserReportingId());
        Optional<Reservation> reservation = reservationService.findById(userReportDTO.getReservationId());

        if(userReported.isEmpty() || userReporting.isEmpty() || reservation.isEmpty()){
            return null;
        }
        if(userReported.get().getUserType().equals(UserType.GUEST)){
            reservation.get().setGuestReported(true);
        }else if(userReported.get().getUserType().equals(UserType.HOST)) {
            reservation.get().setHostReported(true);
        }
        reservationService.save(reservation.get());

        userReported.get().setReported(true);
        userService.save(userReported.get());

        userReport.setReportedUser(userReported.get());
        userReport.setReportingUser(userReporting.get());
        userReport.setReason(userReportDTO.getReason());
        userReport.setReservation(reservation.get());

        userReportRepository.save(userReport);
        return userReportDTO;

    }
    @Override
    public void deleteUserReports(Long id){
        userReportRepository.deleteUserReportAfterBlock(id);
    }

    @Override
    public UserDTO blockUser(Long id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty()) return null;

        if(user.get().getUserType().equals(UserType.GUEST)){
            reservationService.cancellGuestReservations(user.get().getId());
        }
        user.get().setStatus(UserStatus.BLOCKED);
        userReportRepository.deleteUserReportAfterBlock(user.get().getId());

        return new UserDTO(user.get());
    }

}
