package com.kuntzeprojects.hklearn.dto;

import java.io.Serializable;
import java.time.Instant;

import com.kuntzeprojects.hklearn.entities.Offer;

public class OfferDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String imgUri;
	private String description;
	private String edition;
	private Instant startMoment;
	private Instant endMoment;
	
	public OfferDTO() {
	}

	public OfferDTO(Long id, String name, String imgUri, String description, String edition, Instant startMoment, Instant endMoment) {
		this.id = id;
		this.name = name;
		this.imgUri = imgUri;
		this.description = description;
		this.edition = edition;
		this.startMoment = startMoment;
		this.endMoment = endMoment;
	}
	
	public OfferDTO(Offer offer) {
		id = offer.getId();
		name = offer.getName();
		imgUri = offer.getImgUri();
		description = offer.getDescription();
		edition = offer.getEdition();
		startMoment = offer.getStartMoment();
		endMoment = offer.getEndMoment();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Instant getStartMoment() {
		return startMoment;
	}

	public void setStartMoment(Instant startMoment) {
		this.startMoment = startMoment;
	}

	public Instant getEndMoment() {
		return endMoment;
	}

	public void setEndMoment(Instant endMoment) {
		this.endMoment = endMoment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUri() {
		return imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
