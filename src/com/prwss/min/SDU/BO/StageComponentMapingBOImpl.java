package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.dao.StageComponentMappingDao;
import com.prwss.min.SDU.form.StageCompMpgPlanGrid;
import com.prwss.min.SDU.form.StageComponentMpgForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class StageComponentMapingBOImpl implements StageComponentMapingBO {

	private StageComponentMappingDao stageComponentMpgDao;

	public StageComponentMappingDao getStageComponentMpgDao() {
		return stageComponentMpgDao;
	}

	public void setStageComponentMpgDao(StageComponentMappingDao stageComponentMpgDao) {
		this.stageComponentMpgDao = stageComponentMpgDao;
	}

	@Override
	public Boolean CheckBeforeSave(StageComponentMpgForm form, Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub

		boolean status = false;
		try {

			if (MisUtility.ifEmpty(form)) {

				List<StageComponentBean> stageComponentMappingBean = populateStageComponentBean(form, userId);

				System.out.println(stageComponentMappingBean.toString());

				status = stageComponentMpgDao.saveData(stageComponentMappingBean);

			}

		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		return status;
	}

	/*
	 * -----------------------------------------------------populate
	 * Start--------------------------------------------------------------------
	 * ---
	 */

	@SuppressWarnings("unused")
	private List<StageComponentBean> populateStageComponentBeanWithOutlineId(
			List<StageComponentBean> stageComponentMappingBean, List<DivisionWiseSummaryBean> idOutlinesTableData) {

		// SET The Values of idOutlinesTableData to Bean
		for (int i = 0; i < idOutlinesTableData.size(); i++) {
			stageComponentMappingBean.get(i).setOutlinePlanId(idOutlinesTableData.get(i).getOutlinePlanId());
			System.out.println(stageComponentMappingBean.get(i).getOutlinePlanId());
		}

		return stageComponentMappingBean;
	}

	@SuppressWarnings("unchecked")
	private List<StageComponentBean> populateStageComponentBean(StageComponentMpgForm form, Integer employeeId) {

		List<StageComponentBean> stageComponentBeans = null;
		StageComponentBean stageComponentBean = null;
		Map<String, Integer> GetVillagesMap = null;
		List<Boolean> statusList = null;

		try {
			if (MisUtility.ifEmpty(form)) {
				Collection<StageCompMpgPlanGrid> grid = form.getStageCompMpgPlanGrid().getAddedData();
				statusList = new ArrayList<Boolean>();
				if (!MisUtility.ifEmpty(grid)) {
					stageComponentBeans = new ArrayList<StageComponentBean>();

					//GetVillagesMap = new HashMap<String, Integer>();
					// get the total number of villages catagory wise and check
					// it with the DB if Sum is less then save data else Do not
					// save Data
				//	GetVillagesMap = checkNumerOfVillages(grid);

					// GetStatus NumberofVillages
				//	statusList = getNumberofVillagesBoolean(GetVillagesMap);
					
					
					//if(statusList.contains(false)){
						//throw new MISException(e);
				//	}else{
						// Setting theBean to save the Data
						for (StageCompMpgPlanGrid divisionWiseSummaryGrid : grid) {

							Integer Category = null;
							int OutLineid;

							stageComponentBean = new StageComponentBean();

							// Component
							Integer ComponentId = getCategoryId(divisionWiseSummaryGrid.getComponent());
							System.out.println("Component  name-------->" + ComponentId);
							stageComponentBean.setComponentId(ComponentId);

							// State
							Integer Stage = getCategoryId(divisionWiseSummaryGrid.getStage());
							System.out.println("Stage Id-------->" + Stage);
							stageComponentBean.setStageId(Stage);

							// Category
							if (MisUtility.ifEmpty(divisionWiseSummaryGrid.getCategory())) {
								Category = getCategoryId(divisionWiseSummaryGrid.getCategory());
								System.out.println("Category Id-------->" + Category);

							}

							// Number of Villages
							if (MisUtility.ifEmpty(divisionWiseSummaryGrid.getNoOfVillage())) {
								stageComponentBean
										.setNumberVillages(Integer.parseInt(divisionWiseSummaryGrid.getNoOfVillage()));
								System.out.println("Villages Id-------->" + divisionWiseSummaryGrid.getNoOfVillage());
							}

							// GetOutLine Plan ID on the basis of Financial Year ,
							// ID and Div and Category
							try {

								OutLineid = stageComponentMpgDao.getOutlineIds(form.getFinancialYear(), form.getDivision(),
										Category);
								stageComponentBean.setOutlinePlanId(OutLineid);
							} catch (DataAccessException ex) {
								ex.printStackTrace();
							}

							stageComponentBean.setCreatedByUser(employeeId);
							stageComponentBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

							stageComponentBeans.add(stageComponentBean);
						}
					}
					
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return stageComponentBeans;
	}

	@SuppressWarnings("unused")
	private List<Boolean> getNumberofVillagesBoolean(Map<String, Integer> getVillagesMap) {

		List<Boolean> listBoolean = null;
		listBoolean = new ArrayList<Boolean>();
		for (Map.Entry<String, Integer> entry : getVillagesMap.entrySet()) {
			String key = null;
			key  = entry.getKey().toString();
			Integer value = 0;
			value = entry.getValue();
			System.out.println("key, " + key + " value " + value);

			if (key.equalsIgnoreCase("IEC")) {
					if(value > 0 ){
						//Check for value in DB
						listBoolean.add( getTrueFalse(value , stageComponentMpgDao.getVillages(148)));
					}
			}

			if (key.equalsIgnoreCase("GENDER")) {
				if(value > 0 ){
					//Check for value in DB
					listBoolean.add( getTrueFalse(value , stageComponentMpgDao.getVillages(149)));
				}
			}

			if (key.equalsIgnoreCase("SDA")) {
				if(value > 0 ){
					//Check for value in DB
					listBoolean.add( getTrueFalse(value , stageComponentMpgDao.getVillages(150)));
				}
			}

		}

		return listBoolean;

	}

	private Boolean getTrueFalse(Integer value, Integer villages) {

		if(value>villages  && value == villages ){
			return false;
		}else{
			return true;
		}
        
	}

	@SuppressWarnings("unused")
	private Map<String, Integer> checkNumerOfVillages(Collection<StageCompMpgPlanGrid> grid) {

		Map<String, Integer> categorySumVillages = new HashMap<String, Integer>();
		Integer Category = null;
		Integer numberVillages148 = 0;
		Integer numberVillages149 = 0;
		Integer numberVillages150 = 0;
		for (StageCompMpgPlanGrid divisionWiseSummaryGrid : grid) {

			Category = getCategoryId(divisionWiseSummaryGrid.getCategory());

			if (Category == 148) {
				System.out.println("148");
				numberVillages148 += Integer.parseInt(divisionWiseSummaryGrid.getNoOfVillage());
			}
			if (Category == 149) {
				System.out.println("149");
				numberVillages149 += Integer.parseInt(divisionWiseSummaryGrid.getNoOfVillage());
			}
			if (Category == 150) {
				System.out.println("150");
				numberVillages150 += Integer.parseInt(divisionWiseSummaryGrid.getNoOfVillage());
			}

		}

		System.out.println("No Villages 148" + numberVillages148);
		System.out.println("No Villages 149" + numberVillages149);
		System.out.println("No Villages 150" + numberVillages150);

		// Put the Values in the HashMap
		categorySumVillages.put("IEC", numberVillages148);
		categorySumVillages.put("GENDER", numberVillages149);
		categorySumVillages.put("SDA", numberVillages150);

		return categorySumVillages;

	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------------getCategoryId
	 * Start--------------------------------------------------------------------
	 * ---
	 */

	private Integer getCategoryId(String categoryName) {
		Integer catId = null;
		try {
			String categoryId = categoryName.substring(categoryName.indexOf('(') + 1, categoryName.length() - 1);
			System.out.println("--------------1---------------------" + categoryId);
			if (MisUtility.ifEmpty(categoryId)) {
				catId = Integer.parseInt(categoryId);
			}
		} catch (Exception e) {

		}
		return catId;
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */

}
