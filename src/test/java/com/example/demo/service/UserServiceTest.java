
package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepositoryMock;
    private PasswordEncoder passwordEncoderMock;
    private RoleService roleServiceMock;


    @BeforeAll
    public void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        roleServiceMock = Mockito.mock(RoleService.class);

        userService = new UserService(userRepositoryMock, passwordEncoderMock, roleServiceMock);

    }


    @AfterEach
    public void resetDb() {
        userRepositoryMock.deleteAll();
    }


    @Test
    public void findAll() {
        when(userRepositoryMock.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, userService.findAll().size());
    }

    @Test
    void findUserById() {
        when(userRepositoryMock.findById(1L))
                .thenReturn(Optional.of(new User(1L, "Вася", "123", new HashSet<>())));
        assertEquals("Вася", userService.findUserById(1L).getUsername());
    }

    @Test
    void findUserByIdException() {
        assertThrows(NotFoundException.class, () -> userService.findUserById(2L));
    }


    @Test
    void deleteById() {
        when(userRepositoryMock.findById(1L))
                .thenReturn(Optional.of(new User(1L, "Вася", new HashSet<>())));
        userService.deleteById(1L);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdException() {
        assertThrows(NotFoundException.class, () -> userService.deleteById(2L));
    }

    @Test
    void saveStudent() {
        when(userRepositoryMock.save(any(User.class)))
                .thenReturn(new User(1L, "Вася", "123", new HashSet<>()));
        User actualUser = userService.saveStudent(new UserDto(1L, "Вася", "123", new HashSet<>()));

        assertEquals("Вася", actualUser.getUsername());
    }

    @Test
    void findUsersNotAssignedToCourse() {
        userService.findUsersNotAssignedToCourse(1L);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findUsersNotAssignedToCourse(1L);
    }

    @Test
    void findUserByUsername() {
        Mockito.doThrow(new NotFoundException()).when(userRepositoryMock).findUserByUsername("Вася");
        assertThrows(NotFoundException.class, () -> userService.findUserByUsername("Вася"));
    }

    @Test
    void updateUserException() {
        assertThrows(NotFoundException.class, () -> userService.updateUser(new UserDto()));
    }

    @Test
    void updateUser() {
        when(userRepositoryMock.existsById(any())).thenReturn(true);
        when(userRepositoryMock.save(any(User.class))).thenReturn(new User(1L, "Вася", "123", new HashSet<>()));
        User actualUser = userService.updateUser(new UserDto(1L, "Вася", "123"));
        assertEquals("Вася", actualUser.getUsername());
    }


    @Test
    public void passwordAlwaysShouldBeEncoded() {
        userService.saveStudent(new UserDto(1L, "Вася", "123"));
        Mockito.verify(passwordEncoderMock, Mockito.times(1)).encode(anyString());
    }


}