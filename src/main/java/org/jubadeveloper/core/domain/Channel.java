package org.jubadeveloper.core.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(nullable = true)
    private String creatorId;
    @ManyToMany(mappedBy = "channels",
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    private List<User> users;
    public Channel () {
        users = new ArrayList<>();
    }
    public Channel(String creatorId, String name) {
        this.creatorId = creatorId;
        this.name = name;
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
