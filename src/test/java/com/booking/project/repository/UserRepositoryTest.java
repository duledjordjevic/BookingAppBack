package com.booking.project.repository;

import com.booking.project.model.User;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void shouldSave(){
        User user = new User(null, "marko@gmail.com", "123", UserType.GUEST, false, UserStatus.ACTIVE, "a");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).usingRecursiveComparison().ignoringFields("id").ignoringFields("jwt").isEqualTo(user);
    }
}
