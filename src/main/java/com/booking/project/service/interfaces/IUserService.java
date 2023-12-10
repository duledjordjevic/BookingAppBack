package com.booking.project.service.interfaces;

import com.booking.project.dto.*;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {

    Collection<User> findAll();

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User registerUser(UserInfoDTO userInfoDTO);

    User save(User user) throws Exception;

    void deleteById(Long id);
    boolean deleteUserById(UserDeleteDTO userDeleteDTO,Long id) throws Exception;

    User updateAdmin(UserAdminUpdateDTO userAdminDTO, Long id) throws Exception;
    User update(UserUpdateDTO userUpdateDTO, Long id) throws Exception;

    UserDTO changeStatus(Long id, UserStatus status, UserType userType) throws Exception;
    UserDTO report(Long id) throws Exception;

    Collection<UserDTO> findReportedUsers();
}
