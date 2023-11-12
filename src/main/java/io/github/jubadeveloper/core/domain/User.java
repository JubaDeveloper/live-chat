package io.github.jubadeveloper.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "'User'")
@JsonIgnoreProperties({ "channels", "hibernateLazyInitializer", "handler" })
public class User {
    @Id
    private String email;
    @Column(nullable = false, unique = true)
    private String username = "";
    @Column(nullable = false)
    private String password;
    @ColumnDefault("sysdate")
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();
    @ManyToMany(targetEntity = Channel.class,
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    private List<Channel> channels;
    public User () {
        channels = new ArrayList<>();
    }
    public User (String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        channels = new ArrayList<>();
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User { email=%s, username=%s, password=%s }", this.getEmail(), this.getUsername(), this.getPassword());
    }
}
