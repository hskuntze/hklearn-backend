package com.kuntzeprojects.hklearn.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuntzeprojects.hklearn.dto.RoleDTO;
import com.kuntzeprojects.hklearn.entities.Role;
import com.kuntzeprojects.hklearn.repositories.RoleRepository;
import com.kuntzeprojects.hklearn.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public List<RoleDTO> findAll(){
		List<Role> roles = repository.findAll();
		return roles.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		Optional<Role> role = repository.findById(id);
		Role obj = role.orElseThrow(() -> new ResourceNotFoundException("Role não foi encontrada"));
		return new RoleDTO(obj);
	}
}
