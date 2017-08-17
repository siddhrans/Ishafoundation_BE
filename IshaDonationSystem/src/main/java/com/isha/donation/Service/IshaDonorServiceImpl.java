package com.isha.donation.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isha.donation.DAO.IshaDonorDao;
import com.isha.donation.DAO.UploadDAO;
import com.isha.donation.entity.Donor;
import com.isha.donation.entity.UploadDonor;


@Service("ishaDonorService")
@Transactional
public class IshaDonorServiceImpl implements IshaDonorService{
	
	 @Autowired
	 private IshaDonorDao ishaDonorDao;
	 
	 @Autowired
	 private UploadDAO uploadDAO;
	
	@Override
	public List<Donor> findAllDonor(String status) {
	 
		return ishaDonorDao.findAllDonor(status);
	}

	
	public void saveUploadedDonor(UploadDonor uploadonor){
		uploadDAO.saveUploadedDonor(uploadonor);
	}


	@Override
	public List<Donor> findDonorRemitance(String status) {
	 
		return ishaDonorDao.findDonorRemitance(status);
	}


	@Override
	public Donor findDonorMobile(String mobile) {
		 
		return ishaDonorDao.findDonorMobile(mobile);
	}
	
	public UploadDonor findUploadDonorMobile(String mobile){
		return uploadDAO.findUploadDonorMobile(mobile);
	}
	
	
	
	
}
