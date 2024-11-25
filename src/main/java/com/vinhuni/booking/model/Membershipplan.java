package com.vinhuni.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "membershipplans")
public class Membershipplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "Monthly_Fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyFee;

    @ColumnDefault("0.00")
    @Column(name = "Discount_Rate", precision = 5, scale = 2)
    private BigDecimal discountRate;

    @Column(name = "Max_Bookings_Per_Month")
    private Integer maxBookingsPerMonth;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "Created_At")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "Updated_At")
    private Instant updatedAt;

    public Membershipplan(){
        super();
    }
    public Membershipplan(Integer id, String name, String description, BigDecimal monthlyFee, BigDecimal discountRate, Integer maxBookingsPerMonth, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.monthlyFee = monthlyFee;
        this.discountRate = discountRate;
        this.maxBookingsPerMonth = maxBookingsPerMonth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getMaxBookingsPerMonth() {
        return maxBookingsPerMonth;
    }

    public void setMaxBookingsPerMonth(Integer maxBookingsPerMonth) {
        this.maxBookingsPerMonth = maxBookingsPerMonth;
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
}