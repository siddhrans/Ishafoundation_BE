package com.isha.donation.DAO;

 

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.isha.donation.DAO.AbstractDao;
import com.isha.donation.entity.Donor;

 
	
	@Repository("ishaDonor")
	public class IshaDonorImpl extends AbstractDao<Long,Donor>  implements IshaDonorDao{

		 @SuppressWarnings("unchecked")
		@Override
		public List<Donor> findAllDonor(String status) {
			 Criteria criteria= createEntityCriteria();
			 criteria.add(Restrictions.eq("status",status));
			 //criteria.addOrder(Order.asc("type"));
			 
			return (List<Donor>)criteria.list();
		}
		 
		 
		 @SuppressWarnings("unchecked")
		public List<Donor> findDonorRemitance(String status){
			 
			 Criteria criteria=createEntityCriteria();
			 criteria.add(Restrictions.eq("status", status));
			 criteria.add(Restrictions.gt("amount", 999999.0));
			 
			return (List<Donor>)criteria.list(); 
		 }


		@Override
		public Donor findDonorMobile(String mobile) {
			 Criteria criteria=createEntityCriteria();
			 criteria.add(Restrictions.eq("mobileNumber", mobile));
			Donor donor=(Donor)criteria.uniqueResult();
			 
			return  donor;
		}
		 
		 

	}
