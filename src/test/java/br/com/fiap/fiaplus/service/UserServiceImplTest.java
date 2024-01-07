package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.mapper.UserMapper;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.UserRequest;
import br.com.fiap.fiaplus.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class UserServiceImplTest {
    @Mock
    ReactiveMongoTemplate mongoTemplate;
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    Logger log;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create User - Should save and return the created user")
    void testCreate() {
        // Arrange
        UserRequest userRequest = new UserRequest("Alice", "alice@example.com");
        User user = new User("id1", "Alice", "alice@example.com", List.of(), LocalDateTime.now());
        when(userMapper.toEntity(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(Mono.just(user));

        // Act
        Mono<User> result = userServiceImpl.create(userRequest);

        // Assert
        Assertions.assertNotNull(result.block());
        Assertions.assertEquals(user, result.block());
    }

    @Test
    @DisplayName("List All Users - Should return a page of users")
    void testListAll() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        UserCriteria criteria = new UserCriteria();
        List<User> userList = List.of(new User("id1", "Bob", "bob@example.com", List.of(), LocalDateTime.now()));
        when(mongoTemplate.find(any(), eq(User.class))).thenReturn(Flux.fromIterable(userList));
        when(userRepository.count()).thenReturn(Mono.just((long) userList.size()));

        // Act
        Mono<PageImpl<User>> result = userServiceImpl.listAll(pageable, criteria);

        // Assert
        Assertions.assertNotNull(result.block());
        Assertions.assertEquals(userList.size(), result.block()
                .getContent()
                .size());
    }

    @Test
    @DisplayName("List User by ID - Should return the user with the given ID")
    void testListById() {
        // Arrange
        String id = "user_id";
        User user = new User("user_id", "Charlie", "charlie@example.com", List.of(), LocalDateTime.now());
        when(userRepository.findById(id)).thenReturn(Mono.just(user));

        // Act
        Mono<User> result = userServiceImpl.listById(id);

        // Assert
        Assertions.assertNotNull(result.block());
        Assertions.assertEquals(user, result.block());
    }

    @Test
    @DisplayName("Update User - Should update and return the updated user")
    void testUpdate() {
        // Arrange
        String id = "user_id";
        UserRequest request = new UserRequest("Dave", "dave@example.com");
        User existingUser = new User("user_id", "Charlie", "charlie@example.com", List.of(), LocalDateTime.now());
        User updatedUser = new User("user_id", "Dave", "dave@example.com", List.of(), LocalDateTime.now());
        when(userRepository.findById(id)).thenReturn(Mono.just(existingUser));
        when(userMapper.toEntity(request, existingUser)).thenReturn(updatedUser);
        when(userRepository.save(updatedUser)).thenReturn(Mono.just(updatedUser));

        // Act
        Mono<User> result = userServiceImpl.update(id, request);

        // Assert
        Assertions.assertNotNull(result.block());
        Assertions.assertEquals(updatedUser, result.block());
    }

    @Test
    @DisplayName("Delete User by ID - Should delete the user with the given ID")
    void testDeleteById() {
        // Arrange
        String id = "user_id";
        User user = new User("user_id", "Eve", "eve@example.com", List.of(), LocalDateTime.now());
        when(userRepository.findById(id)).thenReturn(Mono.just(user));
        when(userRepository.delete(user)).thenReturn(Mono.empty());

        // Act
        userServiceImpl.deleteById(id);

        // Assert
        verify(userRepository).delete(user);
    }

}

