package com.kuntzeprojects.hklearn.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.kuntzeprojects.hklearn.dto.UserUpdateDTO;
import com.kuntzeprojects.hklearn.entities.User;
import com.kuntzeprojects.hklearn.repositories.UserRepository;
import com.kuntzeprojects.hklearn.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		User user = userRepository.findByEmail(dto.getEmail());
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		if(user != null) {
			list.add(new FieldMessage("email", "E-mail já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}