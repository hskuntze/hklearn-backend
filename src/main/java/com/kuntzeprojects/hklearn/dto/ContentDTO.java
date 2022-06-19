package com.kuntzeprojects.hklearn.dto;

import java.io.Serializable;

import com.kuntzeprojects.hklearn.entities.Content;
import com.kuntzeprojects.hklearn.entities.Offer;

public class ContentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String description;
	private String videoUri;
	
	private OfferDTO offer;
	
	public ContentDTO() {
	}

	public ContentDTO(Long id, String title, String description, String videoUri, OfferDTO offer) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.videoUri = videoUri;
		this.offer = offer;
	}
	
	public ContentDTO(Content content) {
		id = content.getId();
		title = content.getTitle();
		description = content.getDescription();
		videoUri = content.getVideoUri();
	}
	
	public ContentDTO(Content content, Offer offer) {
		this(content);
		this.offer = new OfferDTO(offer);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoUri() {
		return videoUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}

	public OfferDTO getOffer() {
		return offer;
	}

	public void setOffer(OfferDTO offer) {
		this.offer = offer;
	}
}
