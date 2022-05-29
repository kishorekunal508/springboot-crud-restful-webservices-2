package com.kunal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
