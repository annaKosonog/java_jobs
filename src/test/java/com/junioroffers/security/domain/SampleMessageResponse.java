package com.junioroffers.security.domain;

import com.junioroffers.security.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface SampleMessageResponse {

    default ResponseEntity<MessageResponse> messageResponse(String message){
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
