package br.com.pedrocampos.api.resources;

import br.com.pedrocampos.api.domain.dto.UserDTO;
import br.com.pedrocampos.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(mapper.map(this.userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.userService.findAll()
                        .stream()
                        .map(user -> mapper.map(user, UserDTO.class))
                        .collect(Collectors.toList()));
    }

}
