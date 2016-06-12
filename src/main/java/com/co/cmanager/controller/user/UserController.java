package com.co.cmanager.controller.user;

import com.co.cmanager.model.user.User;
import com.co.cmanager.service.user.UserService;
import com.co.cmanager.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    MessageSource messageSource;
    private UserService userService;

    @Autowired
    UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page searchUsersByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                  @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sort,
                                  @RequestParam(value = "search", required = false) String search) {

        Pageable pageable = new PageRequest(page, size, Utils.sortBy(sort));
        Page<User> pageList;
        if (search != null) {
            pageList = userService.search(search, pageable);
        } else {
            pageList = userService.findUserByPage(pageable);
        }
        return pageList;
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public User editUserForm() {
        User user = new User();
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, params = "form")
    public User addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editUserForm();
        }
        user.setCreatedDate(LocalDateTime.now());
        return userService.saveUser(user).get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "form")
    public User editUser(@RequestBody @Valid User user, @PathVariable("id") BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editUserForm();
        }
        user.setLastUpdatedDate(LocalDateTime.now());
        return userService.saveUser(user).get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User showUser(@PathVariable("id") Long id, Model uiModel) {
        Optional<User> userProfile = userService.findUserById(id);
        return userProfile.orElse(userProfile.get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "form")
    public User editUser(@PathVariable("id") Long id) {
        Optional<User> userProfile = userService.findUserById(id);
        return userProfile.orElse(userProfile.get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id).get();
        userService.deleteUser(user);
        return user;
    }

}
