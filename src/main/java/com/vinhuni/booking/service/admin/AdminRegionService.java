package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRegionService {
    @Autowired
    private RegionRepository regionRepository;
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
    public Region getRegionId(Integer id){
        return regionRepository.findById(id).orElse(null);
    }
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }
    public void deleteRegion(Integer id) {
        regionRepository.deleteById(id);
    }

}
