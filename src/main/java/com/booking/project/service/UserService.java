package com.booking.project.service;

import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.dto.UserDTO;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserRepository;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public User findByEmail(String email){
        return repository.findByEmail(email).get();
    }

    @Override
    public User save(User user) throws Exception {
        return repository.save(user);
    }

    @Override
    public User update(UserCredentialsDTO userCredentialsDTO, Long id) throws Exception{
        Optional<User> userForUpdate = findById(id);

        if(userForUpdate.isEmpty()) return null;

        userForUpdate.get().setEmail(userCredentialsDTO.getEmail());
        userForUpdate.get().setPassword(userCredentialsDTO.getPassword());

        save(userForUpdate.get());
        return userForUpdate.get();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
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


}
