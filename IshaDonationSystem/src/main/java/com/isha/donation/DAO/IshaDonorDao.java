package com.isha.donation.DAO;

import java.util.List;

import com.isha.donation.entity.Donor;

public interface IshaDonorDao {

	
	public List<Donor> findAllDonor(String status);
	public List<Donor> findDonorRemitance(String status);
	
	public Donor findDonorMobile(String mobile);
}
