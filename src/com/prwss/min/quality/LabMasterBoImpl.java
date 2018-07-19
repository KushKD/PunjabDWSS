package com.prwss.min.quality;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.LabMasterDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class LabMasterBoImpl extends AbstractPaginationMaster<LabMasterForm> implements LabMasterBo<LabMasterForm> {

	private Logger log = Logger.getLogger(LabMasterBoImpl.class);

	private LabMasterDao labMasterDao;

	/**
	 * @return the labMasterDao
	 */
	public LabMasterDao getLabMasterDao() {
		return labMasterDao;
	}

	/**
	 * @param labMasterDao
	 *            the labMasterDao to set
	 */
	public void setLabMasterDao(LabMasterDao labMasterDao) {
		this.labMasterDao = labMasterDao;
	}

	@Override
	public boolean saveLabMaster(LabMasterForm labMasterForm) throws MISException {
		LabMasterBean labMasterBean = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(labMasterForm)) {

				labMasterBean = populateLabMaster(labMasterForm);

				if (MisUtility.ifEmpty(labMasterBean)) {
					status = labMasterDao.saveLabData(labMasterBean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean updateLab(LabMasterForm labMasterForm) throws MISException {
		// TODO Auto-generated method stub
		System.out.println("inside update--------");
		LabMasterBean labMasterBean = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(labMasterForm.getLabId())) {

				List<LabMasterBean> labMasterBeans = labMasterDao.getLabMasterByLabId(labMasterForm);

				if (!MisUtility.ifEmpty(labMasterBeans)) {
					labMasterBean = getLabMasterBean(labMasterForm);
					status = labMasterDao.UpdateLabMaster(labMasterBean);
				}
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return status;

	}

	private LabMasterBean populateLabMaster(LabMasterForm labMasterForm) {
		LabMasterBean labMasterBean = new LabMasterBean();

		if (MisUtility.ifEmpty(labMasterForm.getStatus())) {
			labMasterBean.setActiveFlag(Integer.parseInt(labMasterForm.getStatus()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getBlock())) {
			labMasterBean.setBlockId(Integer.parseInt(labMasterForm.getBlock()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getDistrict())) {
			labMasterBean.setDistrictId(Integer.parseInt(labMasterForm.getDistrict()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getLabName())) {
			labMasterBean.setLabName(labMasterForm.getLabName());
		}
		if (MisUtility.ifEmpty(labMasterForm.getVillageId())) {
			labMasterBean.setVillageId(Integer.parseInt(labMasterForm.getVillageId()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getEnteredBy())) {
			labMasterBean.setCrtByUsr(labMasterForm.getEnteredBy());
		}

		if (MisUtility.ifEmpty(labMasterForm.getLabLevel())) {
			labMasterBean.setLabLevel(Integer.parseInt(labMasterForm.getLabLevel()));
		}

		if (MisUtility.ifEmpty(labMasterForm.getDivision())) {
			labMasterBean.setDivisionId(Integer.parseInt(labMasterForm.getDivision()));
		}

		if (MisUtility.ifEmpty(labMasterForm.getPhoneNo())) {
			labMasterBean.setPhoneNo(Long.parseLong(labMasterForm.getPhoneNo()));
		}
		labMasterBean.setAddress(labMasterForm.getAddress());
		labMasterBean.setContactPerson(labMasterForm.getContactPerson());
		if (MisUtility.ifEmpty(labMasterForm.getMobileNo())) {
			labMasterBean.setMobileNo(Long.parseLong(labMasterForm.getMobileNo()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getEndDate())) {
			labMasterBean.setEndDate(MisUtility.convertStringSqlDate(labMasterForm.getEndDate()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getStartDate())) {
			labMasterBean.setStartDate(MisUtility.convertStringSqlDate(labMasterForm.getStartDate()));
		}
		labMasterBean.setLabIdName("NLWTL");
		return labMasterBean;
	}

	private LabMasterBean getLabMasterBean(LabMasterForm labMasterForm) {
		LabMasterBean labMasterBean = new LabMasterBean();

		labMasterBean.setLabId(Long.parseLong(labMasterForm.getLabId()));
		if (MisUtility.ifEmpty(labMasterForm.getStatus())) {
			labMasterBean.setActiveFlag(Integer.parseInt(labMasterForm.getStatus()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getBlock())) {
			labMasterBean.setBlockId(Integer.parseInt(labMasterForm.getBlock()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getDistrict())) {
			labMasterBean.setDistrictId(Integer.parseInt(labMasterForm.getDistrict()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getLabName())) {
			labMasterBean.setLabName(labMasterForm.getLabName());
		}
		if (MisUtility.ifEmpty(labMasterForm.getVillageId())) {
			labMasterBean.setVillageId(Integer.parseInt(labMasterForm.getVillageId()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getEnteredBy())) {
			labMasterBean.setCrtByUsr(labMasterForm.getEnteredBy());
		}
		if (MisUtility.ifEmpty(labMasterForm.getLabLevel())) {
			labMasterBean.setLabLevel(Integer.parseInt(labMasterForm.getLabLevel()));
		}

		if (MisUtility.ifEmpty(labMasterForm.getDivision())) {
			labMasterBean.setDivisionId(Integer.parseInt(labMasterForm.getDivision()));
		}

		if (MisUtility.ifEmpty(labMasterForm.getPhoneNo())) {
			labMasterBean.setPhoneNo(Long.parseLong(labMasterForm.getPhoneNo()));
		}
		labMasterBean.setAddress(labMasterForm.getAddress());
		labMasterBean.setContactPerson(labMasterForm.getContactPerson());
		if (MisUtility.ifEmpty(labMasterForm.getMobileNo())) {
			labMasterBean.setMobileNo(Long.parseLong(labMasterForm.getMobileNo()));
		}
		
		if (MisUtility.ifEmpty(labMasterForm.getEndDate())) {
			labMasterBean.setEndDate(MisUtility.convertStringSqlDate(labMasterForm.getEndDate()));
		}
		if (MisUtility.ifEmpty(labMasterForm.getStartDate())) {
			labMasterBean.setStartDate(MisUtility.convertStringSqlDate(labMasterForm.getStartDate()));
		}

		return labMasterBean;
	}

	@Override
	public List<LabMasterForm> getLabMasterByPagination(String searchString, int clickedColumn, String colOrder) {
		List<LabMasterBean> labMasterBean = null;
		List<LabMasterForm> labMasterForm = null;

		try {

			labMasterBean = labMasterDao.getLabMasterByPagination(searchString, clickedColumn, colOrder);

			if (!MisUtility.ifEmpty(labMasterBean)) {
				labMasterForm = new ArrayList<LabMasterForm>();
				for (LabMasterBean bean : labMasterBean) {
					LabMasterForm form = new LabMasterForm();
					form.setLabId(String.valueOf(bean.getLabId()));
					form.setLabName(bean.getLabName());
					form.setBlock(String.valueOf(bean.getBlockId()));
					form.setDistrict(String.valueOf(bean.getDistrictId()));
					form.setVillageId(String.valueOf(bean.getVillageId()));
					form.setDistrictName(bean.getDistrictDetailBean().getLocationName());
					if (MisUtility.ifEmpty(bean.getBlockDetailBean())) {
						form.setBlockName(bean.getBlockDetailBean().getLocationName());
					}
					form.setAddress(bean.getAddress());
					form.setDivision(String.valueOf(bean.getDivisionId()));
					form.setContactPerson(bean.getContactPerson());
					form.setMobileNo(String.valueOf(bean.getMobileNo()));
					form.setPhoneNo(String.valueOf(bean.getPhoneNo()));
					form.setLabLevel(String.valueOf(bean.getLabLevel()));
					form.setStatus(String.valueOf(bean.getActiveFlag()));
					if (MisUtility.ifEmpty(bean.getStartDate())) {
						form.setStartDate(MisUtility.convertDateString(bean.getStartDate()));
					}
					if (MisUtility.ifEmpty(bean.getEndDate())) {
						form.setEndDate(MisUtility.convertDateString(bean.getEndDate()));
					}

					labMasterForm.add(form);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labMasterForm;
	}

	@Override
	public List<LabMasterForm> getListBasedOnSearchParameter(String searchParameter, List<LabMasterForm> formList) {
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				List<LabMasterForm> locationMasterList = new ArrayList<LabMasterForm>();
				searchParameter = searchParameter.toUpperCase();
				for (LabMasterForm masterForm : formList) {
					if (masterForm.getBlock().toUpperCase().indexOf(searchParameter) != -1) {
						locationMasterList.add(masterForm);
					}
					if (masterForm.getDistrict().toUpperCase().indexOf(searchParameter) != -1) {
						locationMasterList.add(masterForm);
					}
					if (masterForm.getLabName().toUpperCase().indexOf(searchParameter) != -1) {
						locationMasterList.add(masterForm);

					}

				}
				formList = locationMasterList;
				locationMasterList = null;
			}
		} catch (Exception e) {

		}
		return formList;
	}

	@Override
	public List<LabMasterForm> getListBasedOnColumnSorting(List<LabMasterForm> formList, Integer sortingColumn,
			String sortingOrder, Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
