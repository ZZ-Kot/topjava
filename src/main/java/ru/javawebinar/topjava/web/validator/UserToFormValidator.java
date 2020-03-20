package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class UserToFormValidator implements Validator {
	
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserTo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserTo userTo = (UserTo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		
		if(userService.getByEmail(userTo.getEmail()) != null) {
			errors.rejectValue("email", "Pattern.userForm.email");
		}
	}

}
