package org.jubadeveloper.core.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email",
            foreignKey = @ForeignKey(name = "USER_EMAIL_FK"))
    private User creator;
    @ManyToMany(mappedBy = "channels",
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    private List<User> users;
    public Channel () {
        users = new ArrayList<>();
    }
    public Channel(User creator) {
        this.creator = creator;
        users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
