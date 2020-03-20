package ru.javawebinar.topjava.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.DuplicateEmailException;
import ru.javawebinar.topjava.web.validator.UserToFormValidator;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminUIController extends AbstractUserController {

	@Autowired
	UserToFormValidator userToFormValidator;
	
	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userToFormValidator);
	}
	
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@ModelAttribute("userForm") @Validated UserTo userTo, BindingResult result) {

    	if (result.hasErrors()) {
    		throw new DuplicateEmailException("User with this email already exists");
    	}

    	if (userTo.isNew()) {
			super.create(userTo);
		} else {
			super.update(userTo, userTo.id());
		}
    }

    @Override
    @PostMapping("/{id}")
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}
