package com.kuntzeprojects.hklearn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuntzeprojects.hklearn.dto.ContentDTO;
import com.kuntzeprojects.hklearn.dto.OfferDTO;
import com.kuntzeprojects.hklearn.entities.Offer;
import com.kuntzeprojects.hklearn.repositories.OfferRepository;
import com.kuntzeprojects.hklearn.services.exceptions.DatabaseException;
import com.kuntzeprojects.hklearn.services.exceptions.ResourceNotFoundException;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepository repository;
	
	private void dtoToEntity(OfferDTO dto, Offer entity) {
		entity.setName(dto.getName());
		entity.setEdition(dto.getEdition());
		entity.setStartMoment(dto.getStartMoment());
		entity.setEndMoment(dto.getEndMoment());
		entity.setImgUri(dto.getImgUri());
		entity.setDescription(dto.getDescription());
	}
	
	@Transactional(readOnly = true)
	public Page<OfferDTO> findAll(Pageable pageable){
		Page<Offer> page = repository.findAll(pageable);
		return page.map(offer -> new OfferDTO(offer));
	}
	
	@Transactional(readOnly = true)
	public Page<OfferDTO> findAllPaged(Pageable pageable, String name, String edition) {
		Page<Offer> page = repository.find(pageable, name, edition);
		return page.map(x -> new OfferDTO(x));
	}
	
	@Transactional(readOnly = true)
	public OfferDTO findById(Long id) {
		Optional<Offer> obj = repository.findById(id);
		Offer offer = obj.orElseThrow(() -> new ResourceNotFoundException("Oferta não foi localizada"));
		return new OfferDTO(offer);
	}
	
	@Transactional(readOnly = true)
	public List<ContentDTO> findByIdWithContentList(Long id) {
		Offer obj = repository.getOne(id);
		List<ContentDTO> contents = new ArrayList<>();
		obj.getContents().forEach(cnt -> contents.add(new ContentDTO(cnt, cnt.getOffer())));
		return contents;
	}
	
	@Transactional
	public OfferDTO insert(OfferDTO dto) {
		Offer offer = new Offer();
		dtoToEntity(dto, offer);
		offer = repository.save(offer);
		return new OfferDTO(offer);
	}
	
	@Transactional
	public OfferDTO update(Long id, OfferDTO dto) {
		try {
			Offer offer = repository.getOne(id);
			dtoToEntity(dto, offer);
			offer = repository.save(offer);
			return new OfferDTO(offer);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Não foi possível localizar esta oferta. \n" + e.getMessage());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Não foi possível localizar esta oferta. \n" + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação da integridade da dados. Mensagem: \n\n" + e.getMessage());
		}
	}
}
