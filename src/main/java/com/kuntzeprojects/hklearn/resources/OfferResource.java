package com.kuntzeprojects.hklearn.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kuntzeprojects.hklearn.dto.ContentDTO;
import com.kuntzeprojects.hklearn.dto.OfferDTO;
import com.kuntzeprojects.hklearn.services.OfferService;

@RestController
@RequestMapping(value = "/offers")
public class OfferResource {
	
	@Autowired
	private OfferService service;
	
	@GetMapping
	public ResponseEntity<Page<OfferDTO>> findAll(Pageable pageable,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "edition", defaultValue = "") String edition){
		Page<OfferDTO> page = service.findAllPaged(pageable, name, edition);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OfferDTO> findById(@PathVariable Long id){
		OfferDTO offer = service.findById(id);
		return ResponseEntity.ok().body(offer);
	}
	
	@GetMapping(value = "/contents/{id}")
	public ResponseEntity<List<ContentDTO>> findByIdWithContentList(@PathVariable Long id){
		List<ContentDTO> list = service.findByIdWithContentList(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<OfferDTO> insert(@RequestBody OfferDTO obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OfferDTO> update (@PathVariable Long id, @RequestBody OfferDTO obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
