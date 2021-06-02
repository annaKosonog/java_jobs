package com.junioroffers.security.exception.api.response;

public interface SampleUserExistsByUsername {

    default UserExistsByUsername sampleUserExistsByUsername(String username){
        return new UserExistsByUsername(username);
    }
}
