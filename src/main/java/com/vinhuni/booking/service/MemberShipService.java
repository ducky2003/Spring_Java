package com.vinhuni.booking.service;

import com.vinhuni.booking.model.Membershipplan;
import com.vinhuni.booking.repository.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberShipService {
    @Autowired
    private MemberShipRepository memberShipRepository;
    public List<Membershipplan> getAllregion(){
        return memberShipRepository.findAll();
    }
}
