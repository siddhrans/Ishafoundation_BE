package com.isha.donation.DAO;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.isha.donation.entity.Donor;
import com.isha.donation.entity.UploadDonor;

@Repository("uploaddao")
public class UploadDaoImpl extends AbstractDao<Long, UploadDonor> implements UploadDAO{

	@Override
	public void saveUploadedDonor(UploadDonor uploadonor) {
		 
		persist(uploadonor);
		
	}

	@Override
	public UploadDonor findUploadDonorMobile(String mobile) {
		
		 Criteria criteria=createEntityCriteria();
		 criteria.add(Restrictions.eq("updatemobileNumber", mobile));
		UploadDonor donor=(UploadDonor)criteria.uniqueResult();
		System.out.println("UploadDaoImpl->"+donor); 
		return  donor;
	}

}
