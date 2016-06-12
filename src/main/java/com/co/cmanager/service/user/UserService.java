package com.co.cmanager.service.user;

import com.co.cmanager.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
public interface UserService {

    Optional<User> findUserById(Long id);

    Page<User> findUserByPage(Pageable pageable);

    Page<User> search(String search, Pageable pageable);

    Optional<User> saveUser(User form);

    void deleteUser(User user);
}
