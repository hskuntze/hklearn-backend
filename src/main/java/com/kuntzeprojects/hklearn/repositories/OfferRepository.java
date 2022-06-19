package com.kuntzeprojects.hklearn.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kuntzeprojects.hklearn.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
	
	@Query("SELECT DISTINCT obj FROM Offer obj "+ 
			" WHERE (LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))) "
			+ "AND (LOWER(obj.edition) LIKE LOWER(CONCAT('%',:edition,'%')))")
	Page<Offer> find(Pageable pageable, String name, String edition);
}
