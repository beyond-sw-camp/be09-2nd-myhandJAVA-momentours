package com.myhandjava.momentours.user.command.domain.repository;


import com.myhandjava.momentours.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}