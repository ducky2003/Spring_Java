package com.vinhuni.booking.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", nullable = false)
    private Integer id;

    @Column(name = "Full_Name", nullable = false)
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;
    public User(){
        super();
    }

    public User(Integer id, String fullName, String email, String password, String phoneNumber, Instant createdAt, Instant updatedAt, Boolean gender, String address, Set<UserRole> userRoles) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
//        this.created_at = createdAt;
//        this.updated_at = updatedAt;
        this.gender = gender;
        this.address = address;
        this.userRoles = userRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}