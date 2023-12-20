package com.booking.project.controller;

import com.booking.project.dto.*;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import com.booking.project.service.interfaces.IGuestService;
import com.booking.project.service.interfaces.IHostService;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGuestService guestService;

    @Autowired
    private IHostService hostService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(){
        Collection<User> users = userService.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty()){
            return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
        }
        if(user.get().getUserType().equals(UserType.GUEST)){

            Optional<Guest> guest =  guestService.findByUser(user.get().getId());
            UserInfoDTO userInfoDTO = new UserInfoDTO(guest.get(),user.get());
            return new ResponseEntity<UserInfoDTO>(userInfoDTO, HttpStatus.OK);

        }else if(user.get().getUserType().equals(UserType.HOST)){

            Host host = hostService.findByUser(user.get().getId());
            UserInfoDTO userInfoDTO = new UserInfoDTO(host,user.get());
            return new ResponseEntity<UserInfoDTO>(userInfoDTO, HttpStatus.OK);
        }

        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }
    @PutMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@RequestBody UserDeleteDTO userDeleteDTO,@PathVariable("id") Long id) throws Exception {
        Boolean isUserDeleted = userService.deleteUserById(userDeleteDTO,id);

        if(isUserDeleted.equals(false)){
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        User savedUser = userService.save(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAdmin(@RequestBody UserAdminUpdateDTO userAdminDTO, @PathVariable Long id) throws Exception{
        User userForUpdate = userService.updateAdmin(userAdminDTO, id);

        if(userForUpdate == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<User>(userForUpdate, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('GUEST') OR hasRole('HOST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id) throws Exception{
        User userForUpdate = userService.update(userUpdateDTO, id);

        if(userForUpdate == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<User>(userForUpdate, HttpStatus.OK);
    }

    @PutMapping(value = "/guest/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable Long id) throws Exception{
        Guest guestForUpdate = guestService.update(guestDTO, id);

        if(guestForUpdate == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(guestForUpdate, HttpStatus.CREATED);
    }

    @PutMapping(value = "/host/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateHost(@RequestBody HostDTO hostDTO, @PathVariable Long id) throws Exception{
        Host hostForUpdate = hostService.update(hostDTO, id);

        if(hostForUpdate == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(hostForUpdate, HttpStatus.CREATED);
    }
    @PutMapping(value = "/host/{id}/userStatus/{status}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeHostStatus(@PathVariable Long id,@PathVariable UserStatus status) throws Exception {
        UserDTO userDTO = userService.changeStatus(id,status, UserType.HOST);

        if(userDTO == null) return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    @PutMapping(value = "/guest/{id}/userStatus/{status}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeGuestStatus(@PathVariable Long id,@PathVariable UserStatus status) throws Exception {
        UserDTO userDTO = userService.changeStatus(id,status,UserType.GUEST);

        if(userDTO == null) return new ResponseEntity<UserDTO>(userDTO,HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/guest",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGuest(@RequestBody GuestDTO guestDTO) throws Exception {
        GuestDTO savedGuest = guestService.addGuest(guestDTO);
        return new ResponseEntity<GuestDTO>(savedGuest, HttpStatus.CREATED);
    }

    @PostMapping(value = "/host",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createHost(@RequestBody HostDTO hostDTO) throws Exception {
        HostDTO savedHost = hostService.addHost(hostDTO);
        return new ResponseEntity<HostDTO>(savedHost, HttpStatus.CREATED);
    }
    @PutMapping(value = "/report/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reportUser(@PathVariable Long id) throws Exception {
        UserDTO userDTO = userService.report(id);

        if(userDTO == null) return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/reported", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReportedUsers() throws Exception{
        Collection<UserDTO> reportedUsersDTOs = userService.findReportedUsers();

        return new ResponseEntity<Collection<UserDTO>>(reportedUsersDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{user_id}")
    public ResponseEntity<?> getHostId(@PathVariable Long user_id) throws Exception{
        Host host = hostService.findByUser(user_id);

        if(host == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Integer>(Math.toIntExact(host.getId()), HttpStatus.OK);
    }
 }
