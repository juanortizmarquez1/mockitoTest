package com.generation.org.apidemo;

import com.generation.org.apidemo.controller.UserController;
import com.generation.org.apidemo.model.User;
import com.generation.org.apidemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    User user = new User("Pepe", "3");

    @Test
    public void getUserTest(){
        when(userService.getUser("3")).thenReturn(user);
        assertEquals(userController.getUser("3").getId(), user.getId());
        assertEquals(userController.getUser("3").getName(), user.getName());
    }

    @Test
    public void newAndUpdateUserTest(){
        when(userService.save(user)).thenReturn(user);
        assertEquals(userController.newUser(user).getId(), user.getId());
        assertEquals(userController.updateUser(user).getId(), user.getId());
    }

    @Test
    public void deleteUserTest(){
        userController.deleteUser("3");
        verify(userService, times(1)).delete("3");
    }
}
