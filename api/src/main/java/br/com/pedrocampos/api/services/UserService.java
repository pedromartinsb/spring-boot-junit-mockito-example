package br.com.pedrocampos.api.services;

import br.com.pedrocampos.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
