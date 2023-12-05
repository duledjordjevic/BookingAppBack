package com.booking.project.repository.inteface;
import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Collection<User> findByIsReportedTrue();
    public Optional<User> findByEmail(String email);
//    @Query("Select u " +
//            "from User u " +
//            "where u.email = :email")
//    public User findUserByEmail(@Param(":email") String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
        "SET a.status = 'ACTIVE' WHERE a.email = ?1")
    int enableAppUser(String email);
}
