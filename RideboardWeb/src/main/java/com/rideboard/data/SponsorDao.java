package com.rideboard.data;

import java.util.List;

import com.rideboard.bean.SponsorInfoBean;
import com.rideboard.data.model.SponsorModel;

public class SponsorDao {
	public SponsorModel findSponsorById(int key) {
		return Database.findSponsorById(key);
	}
	public java.util.Collection<SponsorModel> all() {
		return Database.listSponsors();
	}
	public SponsorInfoBean parseInfoBean(SponsorModel sponsors) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SponsorModel> findSponsorByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
