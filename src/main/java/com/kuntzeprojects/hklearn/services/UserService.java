package com.kuntzeprojects.hklearn.services;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuntzeprojects.hklearn.dto.RoleDTO;
import com.kuntzeprojects.hklearn.dto.UserDTO;
import com.kuntzeprojects.hklearn.dto.UserInsertDTO;
import com.kuntzeprojects.hklearn.dto.UserUpdateDTO;
import com.kuntzeprojects.hklearn.entities.Role;
import com.kuntzeprojects.hklearn.entities.User;
import com.kuntzeprojects.hklearn.repositories.RoleRepository;
import com.kuntzeprojects.hklearn.repositories.UserRepository;
import com.kuntzeprojects.hklearn.services.exceptions.DatabaseException;
import com.kuntzeprojects.hklearn.services.exceptions.ResourceNotFoundException;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable){
		Page<User> page = repository.findAll(pageable);
		return page.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar este usuário"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserInsertDTO obj, String siteURL) throws MessagingException, IOException{
		User user = new User();
		dtoToEntity(obj, user);
		user.setPassword(encoder.encode(obj.getPassword()));
		
		String random = RandomString.make(64);
		System.out.println("AAAAQUIII ==>>  "+random);
		user.setVerificationCode(random);
		user.setEnabled(false);
		
		user = repository.save(user);
		sendVerificationEmail(user, siteURL);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO update(Long id, UserUpdateDTO obj) {
		try {
			User user = repository.getOne(id);
			dtoToEntity(obj, user);
			user = repository.save(user);
			return new UserDTO(user);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Não foi possível localizar este usuário: " + obj.getEmail());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possível localizar este usuário!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade do banco de dados: " + e.getMessage());
		}
	}
	
	private void dtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.getRoles().clear();
		for(RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username + " not found!!");
		}
		
		return user;
	}
	
	public boolean verify(String verificationCode) {
		User user = repository.findByVerificationCode(verificationCode);
		
		if(user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			repository.save(user);
			
			return true;
		}
	}
	
	private void sendVerificationEmail(User user, String siteURL) throws MessagingException, IOException {
		String toAddress = user.getEmail();
		String fromAddress = "hskuntze@gmail.com";
		String subject = "Verify your account";
		String content = "Olá, [[name]],<br>" +
						"Verifique sua conta clicando no link abaixo:<br>" +
						"<h5><a href=\"[[URL]]\"></a></h5>";
		
		content = content.replace("[[name]]", user.getName());
		String verifyUrl = siteURL + "/verify?code=" + user.getVerificationCode();
		
		content = content.replace("[[URL]]", verifyUrl);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddress);
		message.setSubject(subject);
		message.setTo(toAddress);
		message.setText(content);
		
		mailSender.send(message);
	}
}
