package br.com.pedrocampos.api.services.impl;

import br.com.pedrocampos.api.domain.User;
import br.com.pedrocampos.api.domain.dto.UserDTO;
import br.com.pedrocampos.api.repositories.UserRepository;
import br.com.pedrocampos.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Pedro";
    public static final String EMAIL = "pedro@test.com";
    public static final String PASSWORD = "123";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final int INDEX = 0;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstace() {
        // Given
        when(this.userRepository.findById(anyInt()))
                .thenReturn(optionalUser);

        // When
        User response = this.userService.findById(ID);

        // Then
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        // Given
        when(userRepository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));

        try {
            // When
            userService.findById(ID);
        } catch (Exception ex) {
            // Then
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(user));

        // When
        List<User> response = userRepository.findAll();

        // Then
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test

    void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        this.optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}