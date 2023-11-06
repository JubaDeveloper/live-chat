package org.jubadeveloper.core.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "'User'")
public class User {
    @Id
    private String email;
    @Column(nullable = false)
    private String username = "";
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
    public User (String email, String username) {
        this.email = email;
        this.username = username;
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
}
