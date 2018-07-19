package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatDto;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.min.sanitation.dao.GramPanchayatMasterDao;
import com.prwss.min.sanitation.form.GramPanchayatMasterForm;
import com.prwss.min.sanitation.form.GramPanchayatMasterGrid;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class GramPanchayatMasterBoImpl extends AbstractPaginationMaster<GramPanchayatDto> implements GramPanchayatMasterBo<GramPanchayatDto> {

	private Logger log = Logger.getLogger(GramPanchayatMasterBoImpl.class);

	GramPanchayatMasterDao gramPanchayatMasterDao;

	public GramPanchayatMasterDao getGramPanchayatMasterDao() {
		return gramPanchayatMasterDao;
	}

	public void setGramPanchayatMasterDao(GramPanchayatMasterDao gramPanchayatMasterDao) {
		this.gramPanchayatMasterDao = gramPanchayatMasterDao;
	}

	
/*---------------------------------------------------------saveGramPanchayat--------------------------------------------------------------------*/
	
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveGramPanchayat(GramPanchayatMasterForm gramPanchayatMasterForm) throws MISException {

		GramPanchayatMasterBean masterentryBean = null;
		List<GramPanchayatMasterBean> masterentryBeans = null;
		List<GramPanchayatDetailBean> detailentryBean = null;
		int gramPanchayatId = 0;
		boolean status = false;
		try {

			masterentryBean = populateGramPanchayatMaster(gramPanchayatMasterForm);
			masterentryBeans = gramPanchayatMasterDao.getgramPanchayatMaster(masterentryBean);

			if (MisUtility.ifEmpty(masterentryBeans)) {
				gramPanchayatId = gramPanchayatMasterDao.savegramPanchayatMaster(masterentryBean);
				if (MisUtility.ifEmpty(gramPanchayatId)) {
					detailentryBean = populateGramPanchayatDetail(gramPanchayatMasterForm, gramPanchayatId);
					status = gramPanchayatMasterDao.savegramPanchayatDetail(detailentryBean);
				}
			} else {
				detailentryBean = populateGramPanchayatDetail(gramPanchayatMasterForm,
						masterentryBeans.get(0).getGramPanchayatId());
				status = gramPanchayatMasterDao.savegramPanchayatDetail(detailentryBean);
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

	
/*---------------------------------------------------------populateGramPanchayatMaster--------------------------------------------*/
	

	private GramPanchayatMasterBean populateGramPanchayatMaster(GramPanchayatMasterForm gramPanchayatMasterForm) {
		GramPanchayatMasterBean gramPanchayatMasterBean = null;
		if (MisUtility.ifEmpty(gramPanchayatMasterForm)) {
			gramPanchayatMasterBean = new GramPanchayatMasterBean();
			gramPanchayatMasterBean.setNameofGramPanchayat(gramPanchayatMasterForm.getNameofGramPanchayat());
			gramPanchayatMasterBean.setNameofSarpanch(gramPanchayatMasterForm.getNameofSarpanch());
			gramPanchayatMasterBean.setCreatedByUser(Integer.parseInt(String.valueOf(gramPanchayatMasterForm.getCreatedByUser())));
			if(gramPanchayatMasterForm.getStatus().equalsIgnoreCase("1")){
				gramPanchayatMasterBean.setStatus(1);
			}else{
				gramPanchayatMasterBean.setStatus(0);
			}
			
			
		}

		return gramPanchayatMasterBean;

	}
	

/*---------------------------------------------------populateGramPanchayatDetail---------------------------------------------------------------------*/

	private List<GramPanchayatDetailBean> populateGramPanchayatDetail(GramPanchayatMasterForm gramPanchayatMasterForm,
			int gramPanchayatId) {
		List<GramPanchayatDetailBean> gramPanchayatDetails = null;
		GramPanchayatDetailBean  gramPanchayatDetail = null;
		
//Grid work to be done here
		try {
			@SuppressWarnings("unchecked")
			Collection<GramPanchayatMasterGrid> grid = gramPanchayatMasterForm.getGramPanchayaMasterGrid().getAddedData(); 
			
			if (!MisUtility.ifEmpty(grid)) {
				gramPanchayatDetails = new ArrayList<GramPanchayatDetailBean>();
				
				for (GramPanchayatMasterGrid divisionWiseSummaryGrid : grid) {
					gramPanchayatDetail = new GramPanchayatDetailBean();
  					gramPanchayatDetail.setGramPanchayatId(gramPanchayatId);
					gramPanchayatDetail.setDistrict(getCategoryId(divisionWiseSummaryGrid.getDistrictGrid()));
					gramPanchayatDetail.setBlock(getCategoryId(divisionWiseSummaryGrid.getBlockGrid()));
					gramPanchayatDetail.setVillage(getCategoryId(divisionWiseSummaryGrid.getVillageGrid()));
					//gramPanchayatDetail.setStatus(1);
					
						gramPanchayatDetail.setStatus(getCategoryId(divisionWiseSummaryGrid.getStatusGrid()));
					
					
					gramPanchayatDetail.setCreatedByUser(Integer.parseInt(String.valueOf(gramPanchayatMasterForm.getCreatedByUser())));
					gramPanchayatDetails.add(gramPanchayatDetail);
				}
				}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gramPanchayatDetails;
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

/*-----------------------------------------getGramPanchayatMasterByPagination------------------------------------------------------------------------*/
	
	@Override
	public List<GramPanchayatDto> getGramPanchayatMasterByPagination() throws MISException {
		List<GramPanchayatDto> locationMasterFormLst = null;
		try {
				locationMasterFormLst = gramPanchayatMasterDao.getLocationMasterByPagination();
				
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return locationMasterFormLst;
	}

}