package com.gokul.springdatajpatablesampleproject.repository;

import com.gokul.springdatajpatablesampleproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
