package com.vinhuni.booking.service.admin;


import com.vinhuni.booking.model.Services;
import com.vinhuni.booking.repository.ServiceRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceService {
    @Autowired
    private ServiceRepositoy serviceRepositoy;
    public List<Services> getAllService(){
        return serviceRepositoy.findAll();
    }
    public void saveService(Services service){
        serviceRepositoy.save(service);
    }
    public Services getServiceById(Long id){
        return serviceRepositoy.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Service"));
    }
    public void deleteService(Long id) {
        serviceRepositoy.deleteById(id);
    }
}
