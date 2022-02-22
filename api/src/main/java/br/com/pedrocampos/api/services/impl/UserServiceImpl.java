package br.com.pedrocampos.api.services.impl;

import br.com.pedrocampos.api.domain.User;
import br.com.pedrocampos.api.repositories.UserRepository;
import br.com.pedrocampos.api.services.UserService;
import br.com.pedrocampos.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(final Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
