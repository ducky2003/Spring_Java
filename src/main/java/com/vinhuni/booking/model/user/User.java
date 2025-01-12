package com.vinhuni.booking.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID", nullable = false)
    private Long id;

    @Column(name = "Full_Name", nullable = true)
    private String fullName;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Phone_Number", length = 15)
    private String phoneNumber;

//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "created_at")
//    private Instant created_at;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "updated_at")
//    private Instant updated_at;

    @Column(name = "Gender")
    private Boolean gender;

    @Column(name = "Address", length = 4500)
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "enabled")
    private Boolean enabled;
    @PrePersist
    public void prePersist() {
        if (this.enabled == null) {
            this.enabled = true;
        }
    }

    public User(){
        super();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(Long id, String fullName, String email, String password, String phoneNumber, Boolean gender, String address, Set<Role> roles, String username, Boolean enabled) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.roles = roles;
        this.username = username;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public Instant getCreatedAt() {
//        return created_at;
//    }
//
//    public void setCreatedAt(Instant createdAt) {
//        this.created_at = createdAt;
//    }
//
//    public Instant getUpdatedAt() {
//        return updated_at;
//    }
//
//    public void setUpdatedAt(Instant updatedAt) {
//        this.updated_at = updatedAt;
//    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}