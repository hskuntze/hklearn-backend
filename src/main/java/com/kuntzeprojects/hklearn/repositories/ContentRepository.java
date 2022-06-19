package com.kuntzeprojects.hklearn.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kuntzeprojects.hklearn.entities.Content;
import com.kuntzeprojects.hklearn.entities.Offer;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{
	@Query("SELECT DISTINCT obj FROM Content obj INNER JOIN obj.offer ofr "+
			"WHERE (COALESCE(:offer) IS NULL OR ofr = :offer) AND "+
			"(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')))")
	Page<Content> find(Pageable pageable, Offer offer, String title);
	
	@Query("SELECT obj FROM Content obj JOIN FETCH obj.offer WHERE obj IN :contents")
	List<Content> findContentWithOffer(List<Content> contents);
}
