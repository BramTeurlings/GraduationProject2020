package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.List;

import nl.brickx.domain.Models.Permission;

public class User {
    private int id;
    private String username;
    private String apiKey;
    private List<Permission> permissions = new ArrayList<>();

    public User() {
    }

    public User(int id, String username, String apiKey, List<Permission> permissions) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
