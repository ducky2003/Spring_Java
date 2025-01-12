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

    @Column(name = "Region_Name", nullable = false, length = 100)
    private String regionName;

    @Lob
    @Column(name = "Description")
    private String description;
    @Transient
    private int hotelCount;

    @Column(name = "region_img", length = 455)
    private String regionImg;

    public Region() {
        super();
    }
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Hotel> hotels;
    @PrePersist
    void prePersist() {
        if (regionName == null || regionName.isEmpty()) {
            this.regionName = "Unnamed Region";
        }
    }

    public Region(Integer id, String regionName, String description, int hotelCount, String regionImg, Set<Hotel> hotels) {
        this.id = id;
        this.regionName = regionName;
        this.description = description;
        this.hotelCount = hotelCount;
        this.regionImg = regionImg;
        this.hotels = hotels;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getRegionImg() {
        return regionImg;
    }

    public void setRegionImg(String regionImg) {
        this.regionImg = regionImg;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public void setHotelCount(int hotelCount) {
        this.hotelCount = hotelCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}