package com.junioroffers.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String username;

    @NotBlank
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
