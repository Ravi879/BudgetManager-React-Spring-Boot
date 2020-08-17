package com.budget.manager.repository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {

    private static final long serialVersionUID = 2035000457464505430L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="password_reset_sequence_generator")
    @SequenceGenerator(name="password_reset_sequence_generator", sequenceName="password_reset_sequence")
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false, columnDefinition = "DATE" )
    private LocalDate creationDate = LocalDate.now();

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}

