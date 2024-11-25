package com.vinhuni.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "HotelID", nullable = false)
    private Hotel hotelID;

    @Column(name = "RoomNumber", nullable = false, length = 20)
    private String roomNumber;

    @Lob
    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Lob
    @Column(name = "Description")
    private String description;

    @ColumnDefault("1")
    @Column(name = "IsAvailable")
    private Boolean isAvailable;

    @Column(name = "area")
    private Double area;

    @Column(name = "floor")
    private Integer floor;
    public Room(){
        super();
    }
    public Room(Integer id, Hotel hotelID, String roomNumber, String type, BigDecimal price, String description, Boolean isAvailable, Double area, Integer floor) {
        this.id = id;
        this.hotelID = hotelID;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.description = description;
        this.isAvailable = isAvailable;
        this.area = area;
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotelID() {
        return hotelID;
    }

    public void setHotelID(Hotel hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}