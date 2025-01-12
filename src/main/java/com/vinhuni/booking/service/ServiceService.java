package com.vinhuni.booking.service;


import com.vinhuni.booking.model.Services;
import com.vinhuni.booking.repository.HotelRepository;
import com.vinhuni.booking.repository.ServiceRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepositoy serviceRepositoy;
    @Autowired
    private HotelRepository hotelRepository;
    public List<Services> getAllServices() {
        return serviceRepositoy.findAll();
    }
    public List<Services> getLimitedServices(int limit) {
        return serviceRepositoy.findAll().stream().limit(limit).toList();
    }

}
