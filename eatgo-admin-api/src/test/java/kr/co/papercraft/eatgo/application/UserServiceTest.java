package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.User;
import kr.co.papercraft.eatgo.domain.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .id(1L)
                .email("papercrafter@naver.com")
                .name("paper")
                .level(1L)
                .build());
        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName() ,is("paper"));
    }

    @Test
    public void addUser(){

        String email = "ppaper@naver.com";
        String name = "ppap";

        User mockUser = User.builder()
                .id(1L)
                .email(email)
                .name(name)
                .level(3L)
                .build();
        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(mockUser);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser(){

        Long id = 1L;
        String email = "ppaper@naver.com";
        String name = "ppap22";
        Long level = 3L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .level(level)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
        String nameChanged = "ppapa22";

        mockUser.setName(nameChanged);
        given(userRepository.save(mockUser)).willReturn(mockUser);
        User user = userService.updateUser(id, email, nameChanged, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is("ppapa22"));
    }

    @Test
    public void deactiveUser(){
        Long id = 1L;
        String email = "ppaper@naver.com";
        String name = "ppap22";
        Long level = 3L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .level(level)
                .build();

        given(userRepository.findById(mockUser.getId())).willReturn(Optional.of(mockUser));

        mockUser.deactivate();
        given(userRepository.save(mockUser)).willReturn(mockUser);

        User user = userService.deactiveUser(1L);

        verify(userRepository).findById(mockUser.getId());
        verify(userRepository).save(mockUser);

        userService.deactiveUser(mockUser.getId());

        assertThat(user.getLevel(), is(0L));
        assertThat(user.isAdmin(), is(false));
    }

}