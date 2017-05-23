package com.isha.donation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Donar;

@Repository
public interface IDonarRepository extends CrudRepository<Donar,Long>{
    public Donar findByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
