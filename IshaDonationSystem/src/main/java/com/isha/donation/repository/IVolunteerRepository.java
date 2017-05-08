package com.isha.donation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Volunteer;

@Repository
public interface IVolunteerRepository extends CrudRepository<Volunteer,Long> {
    public Volunteer findByEmail(String email);
}
