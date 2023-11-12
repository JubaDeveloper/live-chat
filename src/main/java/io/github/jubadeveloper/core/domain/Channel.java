package io.github.jubadeveloper.core.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = true)
    private String creatorId;
    @ManyToMany(mappedBy = "channels",
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    private List<User> users = new ArrayList<>();
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> messages = new ArrayList<>();
    public Channel () {}
    public Channel(String creatorId, String name, String description) {
        this.creatorId = creatorId;
        this.name = name;
        this.description = description;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
