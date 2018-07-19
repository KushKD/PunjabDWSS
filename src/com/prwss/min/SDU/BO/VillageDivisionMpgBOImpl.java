package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.SDU.bean.DivisionVillageMappingBean;
import com.prwss.min.SDU.bean.DivisionVillageMappingDetalBean;
import com.prwss.min.SDU.dao.VillageDivisionMappingDao;
import com.prwss.min.SDU.form.VillageDivisionMpgForm;
import com.prwss.min.SDU.form.VillageDivisionMpgGrid;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class VillageDivisionMpgBOImpl implements VillageDivisionMpgBO {

	private VillageDivisionMappingDao villageDivisionMpgDao;

	public VillageDivisionMappingDao getVillageDivisionMpgDao() {
		return villageDivisionMpgDao;
	}

	public void setVillageDivisionMpgDao(VillageDivisionMappingDao villageDivisionMpgDao) {
		this.villageDivisionMpgDao = villageDivisionMpgDao;
	}

	@Override
	@Transactional
	public Boolean saveData(VillageDivisionMpgForm form, int UserId) throws DataAccessException {
		boolean status = false;
		boolean status_table2 = false;
		int divisionId;
		try {

			if (MisUtility.ifEmpty(form)) {
				DivisionVillageMappingBean divisionVillageMapping = populateDivisionVillageMapping(form, UserId);
				System.out.println(divisionVillageMapping.toString());

				// First check weather the Id Exist or not
				List<DivisionVillageMappingBean> Div_Village_Id = villageDivisionMpgDao.getDivisionVillageId(form);
				int DivisionVillageId;

				if (Div_Village_Id.size() == 0) {
					// Save the Data
					// Send divisionVillageMapping to Save the Data
					status = villageDivisionMpgDao.getDivVillageId(divisionVillageMapping);
					// getID
					DivisionVillageId = villageDivisionMpgDao.getDivVillageIdValue(form);
					System.out.println("In if" + DivisionVillageId);
				} else {
					// Don't Save but get the Id
					DivisionVillageId = villageDivisionMpgDao.getDivVillageIdValue(form);
					System.out.println("In Else" + DivisionVillageId);
				}

				// Save the Other Bean
				System.out.println("Division Id " + DivisionVillageId);
				// Polpulate the Detail Bean
				List<DivisionVillageMappingDetalBean> divisionVillageMappingDetailBean = populateDivisionVillageDetail(
						form, UserId, DivisionVillageId);
				// Send the Bean to Dao for Save
				status_table2 = villageDivisionMpgDao.saveDetailDivisonVillage(divisionVillageMappingDetailBean);

			}
			return status_table2;

		} catch (DataAccessException ex) {
			ex.printStackTrace();
			return status_table2;
		}

	}

	@SuppressWarnings("unchecked")
	private List<DivisionVillageMappingDetalBean> populateDivisionVillageDetail(VillageDivisionMpgForm form, int userId,
			int div_Village_Id) {
		List<DivisionVillageMappingDetalBean> divisionVillageMappingDetalBeans = null;
		DivisionVillageMappingDetalBean divisionVillageMappingDetalBean = null;

		Collection<VillageDivisionMpgGrid> grid = form.getVillageDivisionMpgGrid().getAddedData();

		if (!MisUtility.ifEmpty(grid)) {
			divisionVillageMappingDetalBeans = new ArrayList<DivisionVillageMappingDetalBean>();
			for (VillageDivisionMpgGrid divisionWiseSummaryGrid : grid) {

				// Integer Category = null;
				// int OutLineid ;

				divisionVillageMappingDetalBean = new DivisionVillageMappingDetalBean();

				// village
				Integer VillageId = getCategoryId(divisionWiseSummaryGrid.getVillage());
				System.out.println("Village Id-------->" + VillageId);
				System.out.println("Division Village Id-------->" + div_Village_Id);
				divisionVillageMappingDetalBean.setVillageId(VillageId);
				divisionVillageMappingDetalBean.setDivVillageId(div_Village_Id);
				divisionVillageMappingDetalBean.setCreatedByUser(userId);
				divisionVillageMappingDetalBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

				divisionVillageMappingDetalBeans.add(divisionVillageMappingDetalBean);
			}
		}

		return divisionVillageMappingDetalBeans;
	}

	private DivisionVillageMappingBean populateDivisionVillageMapping(VillageDivisionMpgForm form, Integer employeeId) {

		List<DivisionVillageMappingBean> villageDivisionMappingeans = null;
		DivisionVillageMappingBean villageDivisionMappingean = null;

		try {
			if (MisUtility.ifEmpty(form)) {

				villageDivisionMappingean = new DivisionVillageMappingBean();
				villageDivisionMappingean.setCreatedByUser(employeeId);
				villageDivisionMappingean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
				villageDivisionMappingean.setFinancialYear(Integer.parseInt(form.getFinancialYear()));
				villageDivisionMappingean.setDivisionId(Integer.parseInt(form.getDivision()));
				villageDivisionMappingean.setCategoryId(Integer.parseInt(form.getCategory()));
				villageDivisionMappingean.setStageId(Integer.parseInt(form.getStage()));
				villageDivisionMappingean.setComponentId(Integer.parseInt(form.getComponent()));

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return villageDivisionMappingean;
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
