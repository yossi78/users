package com.vicarius.io.users.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLoginTimeUtc;


    public User() {}

    public User(String id, String firstName, String lastName, LocalDateTime lastLoginTimeUtc) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLoginTimeUtc = lastLoginTimeUtc;
    }



}
