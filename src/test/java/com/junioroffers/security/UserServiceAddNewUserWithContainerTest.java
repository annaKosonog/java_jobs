package com.junioroffers.security;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.security.domain.LoginRequestDto;
import com.junioroffers.security.domain.SampleLoginRequestDto;
import com.junioroffers.security.domain.SampleUser;
import com.junioroffers.security.domain.User;
import com.junioroffers.security.exception.api.response.UserExistsByUsername;
import com.junioroffers.security.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@ActiveProfiles("container_test")
public class UserServiceAddNewUserWithContainerTest implements SampleLoginRequestDto, SampleUser {


    @Container
    private static final MongoDBContainer mongoDbContainers = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDbContainers.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDbContainers.getFirstMappedPort()));
    }

    @Test
    void should_add_user_in_database_when_username_is_unique(@Autowired UserService userService,
                                                             @Autowired UserRepository userRepository) {
        //GIVEN
        final User user = userParametersWithoutId("exists_username", "admin");

        userRepository.save(user);

        final LoginRequestDto loginRequestDto = userTestDto();
        then(userRepository.existsByUsername("exists_username")).isTrue();

        //WHEN
        final User actual = userService.addUsers(loginRequestDto);

        //THEN

        assertThat(loginRequestDto.getUsername()).isEqualTo(actual.getUsername());
        assertThat(userRepository.existsByUsername("test")).isTrue();
    }

    @Test
    void should_thrown_exception_when_entered_username_already_exists(@Autowired UserService userService,
                                                                      @Autowired UserRepository userRepository){

       //GIVEN
        final  LoginRequestDto userToWriteDb = loginDtoParametersWithoutId("exists_username", "user");
        then(userRepository.existsByUsername("exists_username")).isFalse();

        userService.addUsers(userToWriteDb);

        final LoginRequestDto existsUsernameDb = loginDtoParametersWithoutId("exists_username", "user");

        //WHEN
        Throwable throwable = catchThrowable(()-> userService.addUsers(userToWriteDb));

        //THEN
        assertThat(throwable).isInstanceOf(UserExistsByUsername.class)
                .hasMessage("Error: Username is already taken !!! " + existsUsernameDb.getUsername());
        assertThat(userRepository.existsByUsername("exists_username")).isTrue();
    }
}
