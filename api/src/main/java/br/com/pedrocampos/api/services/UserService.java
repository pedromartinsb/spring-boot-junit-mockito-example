package br.com.pedrocampos.api.services;

import br.com.pedrocampos.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
