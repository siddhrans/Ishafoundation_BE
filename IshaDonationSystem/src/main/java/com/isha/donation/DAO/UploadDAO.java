package com.isha.donation.DAO;

import com.isha.donation.entity.UploadDonor;

public interface UploadDAO {
	public void saveUploadedDonor(UploadDonor uploadonor);

	public UploadDonor findUploadDonorMobile(String mobile);
}
