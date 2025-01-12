package com.vinhuni.booking.repository;

import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository <Room,Long>{
    List<Room> findByHotelId(Long hotelId);
    Optional<Room> findById(Long roomId);
    List<Room> findByHotel(Hotel hotel);
    Page<Room> findByRoomType(String name, Pageable pageable);
    @Query("SELECT r FROM Room r " +
            "JOIN r.hotel h " +
            "WHERE (:roomType IS NULL OR r.roomType = :roomType) " +
            "AND (:priceFrom IS NULL OR r.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR r.price <= :priceTo) " +
            "AND (:hotelName IS NULL OR h.hotelName LIKE LOWER(CONCAT('%', :hotelName, '%'))) " +
            "AND (:location IS NULL OR h.location LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND (:floor IS NULL OR r.floor = :floor)")

    List<Room> searchRooms(@Param("roomType") String roomType,
                           @Param("priceFrom") Double priceFrom,
                           @Param("priceTo") Double priceTo,
                           @Param("hotelName") String hotelName,
                           @Param("location") String location,@Param("floor") Integer floor);
}
