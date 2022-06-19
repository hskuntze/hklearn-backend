package com.kuntzeprojects.hklearn.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuntzeprojects.hklearn.dto.RoleDTO;
import com.kuntzeprojects.hklearn.services.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleResource {
	
	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<List<RoleDTO>> findAll(){
		List<RoleDTO> page = service.findAll();
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDTO> findById(@PathVariable Long id){
		RoleDTO role = service.findById(id);
		return ResponseEntity.ok().body(role);
	}
}
