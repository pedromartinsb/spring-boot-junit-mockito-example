package br.com.pedrocampos.api.resources;

import br.com.pedrocampos.api.domain.dto.UserDTO;
import br.com.pedrocampos.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(ID)
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

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(ID)
                .buildAndExpand(this.userService.create(dto).getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,
                                          @RequestBody UserDTO dto) {
        dto.setId(id);
        return ResponseEntity
                .ok()
                .body(
                        mapper.map(this.userService.update(dto), UserDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
        this.userService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
