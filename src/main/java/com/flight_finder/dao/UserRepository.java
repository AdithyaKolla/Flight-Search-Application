package com.flight_finder.dao;

import com.flight_finder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select U from User U where U.userName = :userName and U.password = :password")
    User findByUsernamePassword(@Param("userName") String userName, @Param("password") String password);

    User findByUserName(String userName);
}
