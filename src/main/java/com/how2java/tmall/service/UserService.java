package com.how2java.tmall.service;

import com.how2java.tmall.dao.UserDAO;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<User> pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<User>(pageFromJPA, navigatePages);
    }

    public boolean isExist(String name) {
        User user = getByName(name);
        return user != null;
    }

    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name, password);
    }

    public User getByName(String name) {
        return userDAO.findByName(name);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
