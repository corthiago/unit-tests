package com.thiago.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    class changePassword {

        @Test
        @DisplayName("Should change password with success")
        void shouldChangePasswordWithSuccess(){
            var newPassword = "123";
            var oldPassword = "456";
            var dummyUser = new User("thiago", oldPassword);

            dummyUser.changePassword(newPassword);

            assertEquals(newPassword, dummyUser.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when password is null")
        void shouldThrowExceptionWhenPasswordIsNull(){
            String password = "123";
            String newPassword = null;
            var dummyUser = new User("thiago", password);

            var exception = assertThrows(IllegalArgumentException.class, () -> {
                dummyUser.changePassword(newPassword);
            });
            assertEquals("A senha não pode ser vazia", exception.getMessage());
        }

        @Test
        @DisplayName("Should not change password when new password is empty")
        void shouldNotChangePasswordWhenIsEmpty(){
            String password = "123";
            String newPassword = "";
            var dummyUser = new User("thiago", password);

            assertThrows(IllegalArgumentException.class, () -> {
                dummyUser.changePassword(newPassword);
            });

        }
    }

    @Nested
    class isValidPassword {

        @Test
        @DisplayName("Should return true when password is valid")
        void shouldReturnTrueWhenPasswordIsValid(){
            String password = "123";
            var user = new User("thiago", password);

            var isValidPassword = user.isValidPassword(password);

            assertTrue(isValidPassword);
        }

        @Test
        @DisplayName("Should return false when password is invalid")
        void shouldReturnFalseWhenPasswordIsInvalid(){
            String password = "123";
            String otherPassword = "456";
            var user = new User("thiago", password);

            var isValidPassword = user.isValidPassword(otherPassword);

            assertFalse(isValidPassword);
        }

    }

}