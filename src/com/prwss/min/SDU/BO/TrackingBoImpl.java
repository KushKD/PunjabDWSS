package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.dao.TrakingDao;
import com.prwss.min.SDU.form.TrackingForm;
import com.prwss.mis.common.util.MisUtility;

public class TrackingBoImpl implements TrackingBo {

	List<Integer> villageActivityID = null;
	private TrakingDao trackingdao;

	public TrakingDao getTrackingdao() {
		return trackingdao;
	}

	public void setTrackingdao(TrakingDao trackingdao) {
		this.trackingdao = trackingdao;
	}

	@Override
	public boolean save(TrackingForm trackingForm, int parseInt) throws DataAccessException {
		// TODO Auto-generated method stub

		List<ActivityVillageMappingDetalBean> saveBeans = null;

		Boolean status = false;

		try {

			if (MisUtility.ifEmpty(trackingForm)) {

				// Get villageActivityID from Form
				//villageActivityID = popluateVillageActivityListID(trackingForm);
				// GEt Bean from villageActivityID
				//List<ActivityVillageMappingDetalBean> beansOnTheBasisOfVillageDetailId = trackingdao.getBeansActivityDeailMapping(villageActivityID);

				// Set the Dto to bean File
				//saveBeans = new ArrayList<ActivityVillageMappingDetalBean>();
				saveBeans = populateBeanfromDto( trackingForm.getTrackingDtos(),parseInt);

				// Send the Beans to Update the Table
				status = trackingdao.saveUpdateData(saveBeans);

			}
		} catch (Exception ex) {
			status = false;
		}

		return status;
	}

	private List<ActivityVillageMappingDetalBean> populateBeanfromDto( List<TrackingDto> trackingDtos , Integer userId) {

		List<ActivityVillageMappingDetalBean> updatedList = null;


		System.out.println("DTO" + trackingDtos.size());

			updatedList = new ArrayList<ActivityVillageMappingDetalBean>();
			for (TrackingDto trackingDto:trackingDtos) {
				ActivityVillageMappingDetalBean activityVillageMappingDetalBean=new ActivityVillageMappingDetalBean();
				activityVillageMappingDetalBean.setActualStartDate(MisUtility.convertStringSqlDate(trackingDto.getaStartDate()));
				activityVillageMappingDetalBean.setActualEndDate(MisUtility.convertStringSqlDate(trackingDto.getaEndDate()));
				activityVillageMappingDetalBean.setOutComeAchieved(getInt((trackingDto.getDbAcheived())));
				activityVillageMappingDetalBean.setComments(trackingDto.getComents());
				activityVillageMappingDetalBean.setActivityVillageDetailId(trackingDto.getVillageActivityDetailID());
				activityVillageMappingDetalBean.setActivityId(trackingDto.getActivityId());
				activityVillageMappingDetalBean.setActivityVillageId(trackingDto.getVillageactivityId());
				activityVillageMappingDetalBean.setStartDate(trackingDto.getExpected_Start_Date());
				activityVillageMappingDetalBean.setEndDate(trackingDto.getExpected_End_Date());
				activityVillageMappingDetalBean.setCreatedByUser(userId);
				
				//ActivityVillageId
				updatedList.add(activityVillageMappingDetalBean);
			}

		

		return updatedList;
	}

	private Integer getInt(String string) {
		// TODO Auto-generated method stub

		if (string.equalsIgnoreCase("on")) {
			return 1;
		} else {
			return 0;
		}
	}

	private List<Integer> popluateVillageActivityListID(TrackingForm trackingForm) {

		if (MisUtility.ifEmpty(trackingForm)) {
			villageActivityID = new ArrayList<Integer>();
			for (int i = 0; i < trackingForm.getTrackingDtos().size(); i++) {

				villageActivityID.add(trackingForm.getTrackingDto(i).getVillageActivityDetailID());

			}

		}

		return villageActivityID;
	}

}
