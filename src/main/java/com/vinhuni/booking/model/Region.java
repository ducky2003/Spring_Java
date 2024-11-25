package com.vinhuni.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Region_ID", nullable = false)
    private Integer id;

    @Column(name = "RegionName", nullable = false, length = 100)
    private String regionName;

    @Lob
    @Column(name = "Description")
    private String description;
    @Transient
    private int hotelCount;
    public Region() {
        super();
    }
    @Column(name="image")
    private String image;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Hotel> hotels;
    @PrePersist
    void prePersist() {
        if (regionName == null || regionName.isEmpty()) {
            this.regionName = "Unnamed Region";
        }
    }
    public Region(Integer id, String regionName, String description, String image) {
        this.id = id;
        this.regionName = regionName;
        this.description = description;
        this.image = image;
    }
    public int getHotelCount() {
        return hotelCount;
    }

    public void setHotelCount(int hotelCount) {
        this.hotelCount = hotelCount;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

}