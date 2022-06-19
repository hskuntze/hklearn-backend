package com.kuntzeprojects.hklearn.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuntzeprojects.hklearn.dto.ContentDTO;
import com.kuntzeprojects.hklearn.entities.Content;
import com.kuntzeprojects.hklearn.entities.Offer;
import com.kuntzeprojects.hklearn.repositories.ContentRepository;
import com.kuntzeprojects.hklearn.repositories.OfferRepository;
import com.kuntzeprojects.hklearn.services.exceptions.DatabaseException;
import com.kuntzeprojects.hklearn.services.exceptions.ResourceNotFoundException;

@Service
public class ContentService {
	
	@Autowired
	private ContentRepository repository;
	
	@Autowired
	private OfferRepository offerRepo;
	
	@Transactional(readOnly = true)
	public Page<ContentDTO> findAll(Pageable pageable){
		Page<Content> page = repository.findAll(pageable);
		return page.map(x -> new ContentDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<ContentDTO> findAllPaged(Pageable pageable, Long offerId, String title){
		Offer offers = (offerId == 0) ? null : offerRepo.getOne(offerId);
		Page<Content> page = repository.find(pageable, offers, title);
		repository.findContentWithOffer(page.getContent());
		return page.map(x -> new ContentDTO(x, x.getOffer()));
	}
	
	@Transactional(readOnly = true)
	public ContentDTO findById(Long id) {
		Optional<Content> obj = repository.findById(id);
		Content content = obj.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar este conteúdo."));
		return new ContentDTO(content, content.getOffer());
	}
	
	@Transactional
	public ContentDTO insert(ContentDTO content) {
		Content obj = new Content();
		dtoToEntity(content, obj);
		obj = repository.save(obj);
		return new ContentDTO(obj, obj.getOffer());
	}
	
	@Transactional
	public ContentDTO update(Long id, ContentDTO content) {
		try {
			Content obj = repository.getOne(id);
			dtoToEntity(content, obj);
			obj = repository.save(obj);
			return new ContentDTO(obj, obj.getOffer());
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Não foi possível localizar este conteúdo: "+ e.getMessage());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Não foi possível localizar este conteúdo: "+ e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação da integridade da dados. Mensagem: \n\n" + e.getMessage());
		}
	}
	
	private void dtoToEntity(ContentDTO dto, Content entity) {
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setVideoUri(dto.getVideoUri());
		Offer offer = offerRepo.getOne(dto.getOffer().getId());
		entity.setOffer(offer);
	}
}
