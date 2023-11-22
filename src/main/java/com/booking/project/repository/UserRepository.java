package com.booking.project.repository;

import com.booking.project.model.User;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserRepository;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> users;
    public UserRepository(){
        users = new ArrayList<>();
        User user1 = new User(1L, "blabla1", "1", UserType.HOST);
        User user2 = new User(2L, "blabla2", "2", UserType.GUEST);
        User user3 = new User(3L, "blabla3", "3", UserType.HOST);
        User user4 = new User(4L, "blabla4", "4", UserType.GUEST);
        User user5 = new User(5L, "blabla3", "5", UserType.HOST);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    @Override
    public Collection<User> findAll() {
        return users;
    }

    @Override
    public User find(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        users.stream().filter(userToUpdate -> userToUpdate.getId().equals(user.getId())).forEach(userToUpdate -> userToUpdate.copyValues(user));
        return user;
    }

    @Override
    public void delete(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
