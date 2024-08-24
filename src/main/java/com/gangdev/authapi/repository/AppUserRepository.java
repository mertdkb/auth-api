package com.gangdev.authapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gangdev.authapi.user.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {
    Optional<AppUser> findByLogin(String login);
}
