package com.isha.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isha.donation.entity.Donar;
import com.isha.donation.repository.IDonarRepository;

@Service
public class DonarService {

    @Autowired
    private IDonarRepository mDonarRepository;

    public Object findAll() {
        return mDonarRepository.findAll();
    }

    public Donar findById(Long id) {
        return mDonarRepository.findOne(id);
    }

    public Donar save(Donar donar) {
        return mDonarRepository.save(donar);
    }

    public void delete(Donar donar) {
        mDonarRepository.delete(donar);
        return;
    }

    public Donar findByMobileNumber(String mobileNumber) {
        return mDonarRepository.findByMobileNumber(mobileNumber);
    }
}
