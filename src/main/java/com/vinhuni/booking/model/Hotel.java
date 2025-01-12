package com.vinhuni.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hotel_ID", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "description")
    private String description;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "Created_At")
    private Instant createdAt;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy = "hotel")
    private Set<Article> articles = new LinkedHashSet<>();

    public Hotel() {
        super();
    }

//    public Hotel(Integer id, String description, Instant createdAt, String hotelName, Region region, String location, Float rating, String imageUrl) {
//        this.id = id;
//        this.description = description;
//        this.createdAt = createdAt;
//        this.hotelName = hotelName;
//        this.region = region;
//        this.location = location;
//        this.rating = rating;
//        this.imageUrl = imageUrl;
//    }

    public Hotel(Integer id, String description, Instant createdAt, String hotelName, Region region, String location, Float rating, String imageUrl, Set<Article> articles) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
        this.hotelName = hotelName;
        this.region = region;
        this.location = location;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}