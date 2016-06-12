package com.co.cmanager.service.user.jpaImpl;

import com.co.cmanager.model.user.User;
import com.co.cmanager.repository.user.UserRepository;
import com.co.cmanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findUserByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> search(String search, Pageable pageable) {
        return userRepository.search(search, pageable);
    }

    @Override
    public Optional<User> saveUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
