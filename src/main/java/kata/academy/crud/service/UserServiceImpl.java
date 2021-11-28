package kata.academy.crud.service;

import kata.academy.crud.config.SecurityConfiguration;
import kata.academy.crud.dao.UserDao;
import kata.academy.crud.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    @Transactional
    public void create(User user) {
        user.setPassword(SecurityConfiguration.passwordEncoder().encode(user.getPassword()));
        userDao.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(SecurityConfiguration.passwordEncoder().encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
