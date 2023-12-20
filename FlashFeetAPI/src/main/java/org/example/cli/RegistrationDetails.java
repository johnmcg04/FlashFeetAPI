package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class RegistrationDetails {
    private String username;
    private String password;
    @ApiModelProperty(example = "2")
    private int roleId;

    @JsonCreator
    public RegistrationDetails(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("roleId") int roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}