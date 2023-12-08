package com.booking.project.service;

import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.dto.UserDTO;
import com.booking.project.dto.UserInfoDTO;
import com.booking.project.dto.UserUpdateDTO;
import com.booking.project.model.*;
import com.booking.project.model.enums.ReservationStatus;
import com.booking.project.service.interfaces.*;
import com.booking.project.utils.email.IEmailSender;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IEmailSender emailSender;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IConfirmationTokenService confirmationTokenService;
    @Autowired
    private IGuestService guestService;
    @Autowired
    private IHostService hostService;
    @Autowired
    private IReservationService reservationService;
    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }
    public User registerUser(UserInfoDTO userInfoDTO){
        Optional<User> userExist = repository.findByEmail(userInfoDTO.getEmail());

        if(!userExist.isEmpty()){
            return null;
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(userInfoDTO.getPassword());
        User user = new User(userInfoDTO);
        user.setPassword(passwordEncoded);
        repository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:8080/api/register/confirm?token=" + token;

        emailSender.send(user.getEmail(),buildEmail(userInfoDTO.getName(),link));

        return user;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        repository.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
    @Override
    public User save(User user) throws Exception {
        return repository.save(user);
    }

    @Override
    public User updateAdmin(UserCredentialsDTO userCredentialsDTO, Long id) throws Exception{
        Optional<User> userForUpdate = findById(id);

        if(userForUpdate.isEmpty()) return null;

        userForUpdate.get().setEmail(userCredentialsDTO.getEmail());
        userForUpdate.get().setPassword(userCredentialsDTO.getPassword());

        save(userForUpdate.get());
        return userForUpdate.get();
    }

    @Override
    public User update(UserUpdateDTO userUpdateDTO, Long id) throws Exception {
        Optional<User> user = findById(id);
        if(!BCrypt.checkpw(userUpdateDTO.getOldPassword(), user.get().getPassword())) return null;
        if(user.isEmpty()) return null;
        if(!userUpdateDTO.getNewPassword().equals("")){
            String encodedPassword = bCryptPasswordEncoder.encode(userUpdateDTO.getNewPassword());
            user.get().setPassword(encodedPassword);
            save(user.get());
        }

        if(user.get().getUserType().equals(UserType.GUEST)){
            Guest guest = guestService.findByUser(id);
            guest.setName(userUpdateDTO.getName());
            guest.setLastName(userUpdateDTO.getLastname());
            guest.setAddress(userUpdateDTO.getAddress());
            guest.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            guestService.save(guest);

        }else if(user.get().getUserType().equals(UserType.HOST)){
            Host host = hostService.findByUser(id);
            host.setName(userUpdateDTO.getName());
            host.setLastName(userUpdateDTO.getLastname());
            host.setAddress(userUpdateDTO.getAddress());
            host.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            hostService.save(host);
        }
        return user.get();

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Override
    public boolean deleteUserById(Long id) throws Exception {
        Optional<User> user = findById(id);

        if(user.isEmpty()) return false;

        if(user.get().getUserType().equals(UserType.GUEST)){
            Collection<Reservation> guestReservations = reservationService.findByGuestId(guestService.findByUser(id).getId());
            if(guestReservations.size() != 0) return false;

        }else if(user.get().getUserType().equals(UserType.HOST)){
            Collection<Reservation> hostReservations = reservationService.findByHostId(hostService.findByUser(id).getId());
            if(hostReservations.size() != 0) return false;
        }

        user.get().setStatus(UserStatus.DELETED);
        save(user.get());

        return true;
    }
    @Override
    public UserDTO changeStatus(Long id, UserStatus status, UserType userType) throws Exception {
        Optional<User> user = findById(id);

        if(user.isEmpty()) return null;
        if(!user.get().getUserType().equals(userType)) return null;

        user.get().setStatus(status);
        save(user.get());
        UserDTO userDTO = new UserDTO(user.get());
        return userDTO;

    }
    @Override
    public UserDTO report(Long id) throws Exception {
        Optional<User> user = findById(id);

        if(user.isEmpty()) return null;

        user.get().setReported(true);
        save(user.get());
        UserDTO userDTO = new UserDTO(user.get());
        return userDTO;
    }
    @Override
    public Collection<UserDTO> findReportedUsers(){
        Collection<User> users = repository.findByIsReportedTrue();

        Collection<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
