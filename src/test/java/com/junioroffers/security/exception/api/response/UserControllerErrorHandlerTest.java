package com.junioroffers.security.exception.api.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerErrorHandlerTest implements SampleUserErrorResponse, SampleUserExistsByUsername {

    @Test
    public void should_return_user_error_conflict() {
        final String USERNAME = "user";

        UserControllerErrorHandler userControllerErrorHandler = new UserControllerErrorHandler();
        final UserExistsByUsername exception = sampleUserExistsByUsername(USERNAME);
        final UserErrorResponse expectedResponse = sampleUserErrorResponse();

        final UserErrorResponse actualResponse = userControllerErrorHandler.usersConflict(exception);

        assertThat(actualResponse.equals(expectedResponse));

    }
}
