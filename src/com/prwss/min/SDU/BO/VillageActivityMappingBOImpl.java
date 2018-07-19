package com.prwss.min.SDU.BO;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.dao.VillageActivityMappingDao;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.min.SDU.form.VillageActivityMpgGrid;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class VillageActivityMappingBOImpl implements VillageActivityMappingBO{
	
	private VillageActivityMappingDao villageActivityMappingdao;
	
	

	public VillageActivityMappingDao getVillageActivityMappingdao() {
		return villageActivityMappingdao;
	}

	public void setVillageActivityMappingdao(VillageActivityMappingDao villageActivityMappingdao) {
		this.villageActivityMappingdao = villageActivityMappingdao;
	}

	@Override
	public boolean save(VillageActivityMpgForm villageActivityMpgForm, int parseInt) throws DataAccessException {
		
		
		int AtivityVillageId;
		boolean status_table2 = false;
		try {

			if (MisUtility.ifEmpty(villageActivityMpgForm)) {
				ActivityVillageMappingBean ActivityVillageMapping = populateDivisionVillageMapping(villageActivityMpgForm, parseInt);
				System.out.println(ActivityVillageMapping.toString());

				// First check weather the Id Exist or not
				List<ActivityVillageMappingDetalBean> Activity_Village_Id = villageActivityMappingdao.getActivityVillageId(villageActivityMpgForm);
				

				if (Activity_Village_Id.size() == 0) {
					// Save the Data
					// Send divisionVillageMapping to Save the Data
					AtivityVillageId = villageActivityMappingdao.saveVillageActivityBean(ActivityVillageMapping);
					// getID
					//AtivityVillageId = villageActivityMappingdao.getActivityVillageIdValue(villageActivityMpgForm);
					System.out.println("In if" + AtivityVillageId);
				} else {
					// Don't Save but get the Id
					AtivityVillageId = villageActivityMappingdao.getActivityVillageIdValue(villageActivityMpgForm);
					System.out.println("In Else" + AtivityVillageId);
				}

				// Save the Other Bean
				System.out.println("Activity Id " + AtivityVillageId);
				// Polpulate the Detail Bean
				List<ActivityVillageMappingDetalBean> divisionVillageMappingDetailBean;
			
					divisionVillageMappingDetailBean = populateDivisionVillageDetail(
							villageActivityMpgForm, parseInt, AtivityVillageId);
					status_table2 = villageActivityMappingdao.saveDetailActivityVillage(divisionVillageMappingDetailBean);
				
				// Send the Bean to Dao for Save
				

			}
			return status_table2;

		} catch (DataAccessException ex) {
			ex.printStackTrace();
			return status_table2;
		}
	}
	
	private ActivityVillageMappingBean populateDivisionVillageMapping(VillageActivityMpgForm form, Integer employeeId) {

		ActivityVillageMappingBean villageActivityMappingean = null;

		try {
			if (MisUtility.ifEmpty(form)) {
				
			
				villageActivityMappingean = new ActivityVillageMappingBean();
				villageActivityMappingean.setCreatedByUser(employeeId);
				villageActivityMappingean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
				villageActivityMappingean.setFinancialYear(Integer.parseInt(form.getFinancialYear()));
				villageActivityMappingean.setDivisionId(Integer.parseInt(form.getDivision()));
				villageActivityMappingean.setCategoryId(Integer.parseInt(form.getCategory()));
				villageActivityMappingean.setStageId(Integer.parseInt(form.getStage()));
				villageActivityMappingean.setComponentId(Integer.parseInt(form.getComponent()));
				villageActivityMappingean.setVillageId(Integer.parseInt(form.getVillage()));
				//GetCampain ID
				villageActivityMappingean.setCampaignId(Integer.parseInt(form.getActivity()));
				
				

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return villageActivityMappingean;
	}
	
	private List<ActivityVillageMappingDetalBean> populateDivisionVillageDetail(VillageActivityMpgForm form, int userId,
			int div_Village_Id)  {
		List<ActivityVillageMappingDetalBean> divisionVillageMappingDetalBeans = null;
		ActivityVillageMappingDetalBean divisionVillageMappingDetalBean = null;

		@SuppressWarnings("unchecked")
		Collection<VillageActivityMpgGrid> grid = form.getVillageActivityMpgGrid().getAddedData();  //getVillageDivisionMpgGrid().getAddedData();

		if (!MisUtility.ifEmpty(grid)) {
			divisionVillageMappingDetalBeans = new ArrayList<ActivityVillageMappingDetalBean>();
			for (VillageActivityMpgGrid divisionWiseSummaryGrid : grid) {

				// Integer Category = null;
				// int OutLineid ;

				divisionVillageMappingDetalBean = new ActivityVillageMappingDetalBean();

				// village
				Integer ActivityId = getCategoryId(divisionWiseSummaryGrid.getActivity());
				System.out.println("Activity Id-------->" + ActivityId);
				System.out.println("Activity Village Id-------->" + div_Village_Id);
				divisionVillageMappingDetalBean.setActivityId(ActivityId);
				
				//Start Date
				System.out.println("STart Date" + divisionWiseSummaryGrid.getExpectedStartDate());
				//MisUtility.convertDateString()
				Date StartDate;
					StartDate = MisUtility.convertStringSqlDate(divisionWiseSummaryGrid.getExpectedStartDate());
					divisionVillageMappingDetalBean.setStartDate(StartDate);
				
				//EndDate
				System.out.println("End Date" + divisionWiseSummaryGrid.getExpectedEndDate());
				Date EndDate;
				
					EndDate = MisUtility.convertStringSqlDate(divisionWiseSummaryGrid.getExpectedEndDate());
					divisionVillageMappingDetalBean.setEndDate(EndDate);
				
				
				
				divisionVillageMappingDetalBean.setCreatedByUser(userId);
				//divisionVillageMappingDetalBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
				divisionVillageMappingDetalBean.setActivityVillageId(div_Village_Id);
				divisionVillageMappingDetalBeans.add(divisionVillageMappingDetalBean);
			}
		}

		return divisionVillageMappingDetalBeans;
	}
	
	private Integer getCategoryId(String categoryName) {
		Integer catId = null;
		try {
			String categoryId = categoryName.substring(categoryName.indexOf('(') + 1, categoryName.length() - 1);

			if (MisUtility.ifEmpty(categoryId)) {
				catId = Integer.parseInt(categoryId);
			}
		} catch (Exception e) {

		}
		return catId;
	}


}
