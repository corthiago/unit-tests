package com.thiago.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @InjectMocks
    private AuthService authService;

    @Nested
    class authenticate {

        @Test
        @DisplayName("Should authenticate successfully")
        void shouldAuthenticateSuccessfully() {
            var username = "thiago";
            var password = "123";
            doReturn(user).when(userRepository).findByUsername(eq(username));
            doReturn(true).when(user).isValidPassword(eq(password));

            var isAuthenticationValid = authService.authenticate(username, password);

            assertTrue(isAuthenticationValid);
            verify(userRepository, times(1)).findByUsername(eq(username));
            verify(user, times(1)).isValidPassword(eq(password));
        }

        @Test
        @DisplayName("Should not authenticate when user not found")
        void shouldNotAuthenticateWhenUserNotFound() {
            var username = "thiago";
            var password = "123";
            doReturn(null).when(userRepository).findByUsername(eq(username));

            var isAuthenticationValid = authService.authenticate(username, password);

            assertFalse(isAuthenticationValid);
            verify(userRepository, times(1)).findByUsername(eq(username));
        }

        @Test
        @DisplayName("Should not authenticate when password is invalid")
        void shouldNotAuthenticateWhenPasswordIsInvalid() {
            var username = "thiago";
            var password = "123";
            doReturn(user).when(userRepository).findByUsername(eq(username));
            doReturn(false).when(user).isValidPassword(eq(password));

            var isAuthenticationValid = authService.authenticate(username, password);

            assertFalse(isAuthenticationValid);
            verify(userRepository, times(1)).findByUsername(eq(username));
            verify(user, times(1)).isValidPassword(eq(password));
        }

    }

    @Nested
    class register {

        @Test
        @DisplayName("Should register successfully")
        void shouldRegisterSuccessfully(){
            var username = "thiago";
            var password = "123";
            doReturn(null).when(userRepository).findByUsername(eq(username));

            authService.register(username, password);

            verify(userRepository, times(1)).findByUsername(eq(username));
            verify(userRepository, times(1)).save(userArgumentCaptor.capture());
            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(username, userCaptured.getUsername());
            assertEquals(password, userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when user already exists")
        void shouldThrowExceptionWhenUserAlreadyExists(){
            var username = "thiago";
            var password = "123";
            doReturn(user).when(userRepository).findByUsername(eq(username));

            var exception = assertThrows(IllegalArgumentException.class, () -> {
                authService.register(username, password);
            });

            verify(userRepository, times(1)).findByUsername(eq(username));
            verify(userRepository, times(0)).save(any());
            assertEquals("Usuário já existe", exception.getMessage());
        }

    }

}