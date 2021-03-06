package com.kuntzeprojects.hklearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuntzeprojects.hklearn.entities.Enrollment;
import com.kuntzeprojects.hklearn.entities.pk.EnrollmentPK;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK>{

}
