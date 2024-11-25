package com.vinhuni.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hotel_ID", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "City", nullable = false, length = 50)
    private String city;

    @Lob
    @Column(name = "description")
    private String description;

//    @Column(name = "PhoneNumber", length = 15)
//    private String phoneNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "Email", length = 100)
    private String email;

    @ColumnDefault("1")
    @Column(name = "HasServices")
    private Boolean hasServices;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "Created_At")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "Updated_At")
    private Instant updatedAt;

    @Column(name = "HotelImg", length = 300)
    private String hotelImg;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "RegionID")
    private Region region;
    public Hotel(){
        super();
    }
    public Hotel(Integer id, String name, String address, String city, String description, String phoneNumber, String email, Boolean hasServices, Instant createdAt, Instant updatedAt, String hotelImg, Boolean hasServices1, String hotelImg1, String phoneNumber1, Region regionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hasServices = hasServices;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hotelImg = hotelImg;

        this.region = regionID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasServices() {
        return hasServices;
    }

    public void setHasServices(Boolean hasServices) {
        this.hasServices = hasServices;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }



    public Region getRegionID() {
        return region;
    }

    public void setRegionID(Region regionID) {
        this.region = regionID;
    }
}