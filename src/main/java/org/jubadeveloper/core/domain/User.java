package org.jubadeveloper.core.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity()
@Table(name = "'User'")
public class User {
    @Id
    String email;
    @Column(nullable = false)
    String username = "";
    @ColumnDefault("sysdate")
    @Column(nullable = false, updatable = false)
    LocalDate createdAt = LocalDate.now();
    public User () {}
    public User (String email, String username) {
        this.email = email;
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
