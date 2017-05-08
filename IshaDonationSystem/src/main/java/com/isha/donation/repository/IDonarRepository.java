package com.isha.donation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isha.donation.entity.Donar;

@Repository
public interface IDonarRepository extends CrudRepository<Donar, Long>{

}
