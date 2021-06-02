package com.junioroffers.security.exception.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class UserControllerErrorHandler {


    @ExceptionHandler(UserExistsByUsername.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public UserErrorResponse usersConflict(UserExistsByUsername exception){
        String message = "Error: Username is already taken!!!" + exception.getInfo();
        log.info("Conflict: {}", exception.getMessage());
        log.debug("Conflict: ", exception);
        return new UserErrorResponse(HttpStatus.CONFLICT, message);
    }
}
