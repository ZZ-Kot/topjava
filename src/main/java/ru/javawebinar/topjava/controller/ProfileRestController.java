package ru.javawebinar.topjava.controller;

import static ru.javawebinar.topjava.util.SecurityUtil.authUserId;

import org.springframework.stereotype.Controller;

import ru.javawebinar.topjava.model.User;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.getOne(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}