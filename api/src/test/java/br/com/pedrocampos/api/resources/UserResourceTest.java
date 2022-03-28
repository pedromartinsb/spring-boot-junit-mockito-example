package br.com.pedrocampos.api.resources;

import br.com.pedrocampos.api.domain.User;
import br.com.pedrocampos.api.domain.dto.UserDTO;
import br.com.pedrocampos.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Pedro";
    public static final String EMAIL = "pedro@test.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserResource userResource;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}