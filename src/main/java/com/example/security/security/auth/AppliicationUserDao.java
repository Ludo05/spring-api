package com.example.security.security.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppliicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
