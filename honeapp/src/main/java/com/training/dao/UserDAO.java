package com.training.dao;

import java.util.List;

import com.training.model.DUser;

public interface UserDAO {

    void save(DUser user);

    DUser getById(int id);

    List<DUser> getAll();

    void update(DUser user);

    void delete(int id);
}
