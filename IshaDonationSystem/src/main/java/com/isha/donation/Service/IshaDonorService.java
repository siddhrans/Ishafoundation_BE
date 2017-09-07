package com.isha.donation.Service;

import java.util.List;

import com.isha.donation.entity.Donor;
 

public interface IshaDonorService {

	public List<Donor> findAllDonor(String status);
	
	/*public void saveUploadedDonor(UploadDonor uploadonor);*/

	public List<Donor> findDonorRemitance(String status);
	
	public Donor findDonorMobile(String mobile);
	
	public void save(Donor donor);
	
	/*public UploadDonor findUploadDonorMobile(String mobile);*/
	
}
