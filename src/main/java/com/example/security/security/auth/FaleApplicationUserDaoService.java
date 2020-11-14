package com.example.security.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.security.security.security.ApplicationUserRoles.*;

@Repository("fake")
public class FaleApplicationUserDaoService implements AppliicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FaleApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUserList = Lists.newArrayList(
                new ApplicationUser(
                        "lewis",
                        passwordEncoder.encode("password"),
                        STUDENT.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true


                ),
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true


                ),
                new ApplicationUser(
                        "trainee",
                        passwordEncoder.encode("password"),
                        TRAINEE.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true


                )

        );
        return applicationUserList;
    }
}
