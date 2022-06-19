package com.kuntzeprojects.hklearn.resources;

import java.net.URI;

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
import com.kuntzeprojects.hklearn.services.ContentService;

@RestController
@RequestMapping(value = "/contents")
public class ContentResource {
	
	@Autowired
	private ContentService service;
	
	@GetMapping
	public ResponseEntity<Page<ContentDTO>> findAll(
			@RequestParam(value = "offerId", defaultValue = "0") Long offerId,
			@RequestParam(value = "title", defaultValue = "") String title,
			Pageable pageable) {
		Page<ContentDTO> page = service.findAllPaged(pageable, offerId, title);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContentDTO> findById(@PathVariable Long id){
		ContentDTO cnt = service.findById(id);
		return ResponseEntity.ok().body(cnt);
	}
	
	@PostMapping
	public ResponseEntity<ContentDTO> insert(@RequestBody ContentDTO obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContentDTO> update(@PathVariable Long id, @RequestBody ContentDTO obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
