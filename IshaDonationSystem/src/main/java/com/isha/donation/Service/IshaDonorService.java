package com.isha.donation.Service;

import java.util.List;

import com.isha.donation.entity.Donor;
import com.isha.donation.entity.UploadDonor;

public interface IshaDonorService {
public List<Donor> findAllDonor(String status);
	
	public void saveUploadedDonor(UploadDonor uploadonor);

	public List<Donor> findDonorRemitance(String status);
	
	public Donor findDonorMobile(String mobile);
	
	public UploadDonor findUploadDonorMobile(String mobile);
}