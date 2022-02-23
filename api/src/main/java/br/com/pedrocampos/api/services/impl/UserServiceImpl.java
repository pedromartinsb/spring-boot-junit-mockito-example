package br.com.pedrocampos.api.services.impl;

import br.com.pedrocampos.api.domain.User;
import br.com.pedrocampos.api.domain.dto.UserDTO;
import br.com.pedrocampos.api.repositories.UserRepository;
import br.com.pedrocampos.api.services.UserService;
import br.com.pedrocampos.api.services.exceptions.DataIntegrateViolationException;
import br.com.pedrocampos.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(final Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User create(UserDTO dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public User update(UserDTO dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        this.userRepository.deleteById(id);
    }

    private User saveOrUpdate(UserDTO dto) {
        findByEmail(dto);
        return this.userRepository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDTO dto) {
        Optional<User> user = this.userRepository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrateViolationException("E-mail já cadastrado no sistema");
        }
    }
}
