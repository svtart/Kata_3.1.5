package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String rolename;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }
    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.rolename = name;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> roles;

    public Set<User> getRoles() {
        return roles;
    }

    public void setRoles(Set<User> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String name) {
        this.rolename = name;
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    @Override
    public String toString() {
        return getRolename();
    }
}