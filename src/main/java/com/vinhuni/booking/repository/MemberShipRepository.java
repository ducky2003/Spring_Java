package com.vinhuni.booking.repository;

import com.vinhuni.booking.model.Membershipplan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipRepository extends JpaRepository<Membershipplan, Integer> {

}
