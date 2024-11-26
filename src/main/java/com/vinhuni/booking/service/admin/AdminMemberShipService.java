package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Membershipplan;
import com.vinhuni.booking.repository.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMemberShipService {
    @Autowired
    private MemberShipRepository memberShipRepository;
    public List<Membershipplan> getAllMembershipplans() {
        return memberShipRepository.findAll();
    }
    public Membershipplan getMembershipplanById(Integer id) {
        return memberShipRepository.findById(id).orElse(null);
    }
    public Membershipplan saveMembershipplan(Membershipplan membershipplan) {
        return memberShipRepository.save(membershipplan);
    }
    public void deleteMembershipplan(Integer id) {
        memberShipRepository.deleteById(id);
    }
}
