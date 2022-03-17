package com.rideboard.data.dao;

import java.util.List;

import com.rideboard.bean.SponsorInfoBean;
import com.rideboard.data.DummyData;
import com.rideboard.data.model.SponsorModel;

public class SponsorDao {
	public SponsorModel findSponsorById(int key) {
		return DummyData.findSponsorById(key);
	}

	public java.util.Collection<SponsorModel> all() {
		return DummyData.listSponsors();
	}

	public SponsorInfoBean parseInfoBean(SponsorModel sponsor) {
		SponsorInfoBean retVal = null;
		if (sponsor != null) {
			retVal = new SponsorInfoBean();
			retVal.setAmount(sponsor.getAmount());
			retVal.setBnNumber(sponsor.getBn_number());
			retVal.setCompanyName(sponsor.getCompanyName());
			retVal.setJobDesc(sponsor.getJobDesc());
			retVal.setJobTitle(sponsor.getJobTitle());
			retVal.setRequestDateStr(sponsor.getRequestDateStr());
			retVal.setSponsorId(sponsor.getSponsorId());
		}
		return retVal;
	}

	public List<SponsorModel> findSponsorByUserId(Integer userId) {
		return null;
	}
}
