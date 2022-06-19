package com.kuntzeprojects.hklearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuntzeprojects.hklearn.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
