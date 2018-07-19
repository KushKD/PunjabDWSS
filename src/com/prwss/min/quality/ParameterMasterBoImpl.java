package com.prwss.min.quality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ObjectComparator;
import com.prwss.min.dao.ParameterMasterDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class ParameterMasterBoImpl extends AbstractPaginationMaster<ParameterMasterForm>
		implements ParameterMasterBo<ParameterMasterForm> {

	private Logger log = Logger.getLogger(ParameterMasterBoImpl.class);

	private ParameterMasterDao parameterMasterDao;

	/**
	 * @return the parameterMasterDao
	 */
	public ParameterMasterDao getParameterMasterDao() {
		return parameterMasterDao;
	}

	/**
	 * @param parameterMasterDao
	 *            the parameterMasterDao to set
	 */
	public void setParameterMasterDao(ParameterMasterDao parameterMasterDao) {
		this.parameterMasterDao = parameterMasterDao;
	}

	@Override
	public List<ParameterMasterForm> getParameterMasterByPagination(String searchString, int clickedColumn,
			String colOrder) throws MISException {
		System.out.println("Here In BO 111::::");
		List<ParameterMasterBean> parameterMasterBeans = null;
		List<ParameterMasterForm> ParameterMasterFormLst = null;
		try {
			parameterMasterBeans = parameterMasterDao.getParameterMasterByPagination(searchString, clickedColumn,
					colOrder);

			if (!MisUtility.ifEmpty(parameterMasterBeans)) {
				ParameterMasterFormLst = getParameterMasterForm(parameterMasterBeans);
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return ParameterMasterFormLst;
	}

	private List<ParameterMasterForm> getParameterMasterForm(List<ParameterMasterBean> parameterMasterBeans) {

		List<ParameterMasterForm> parameterMasterForm = new ArrayList<ParameterMasterForm>();
		try{
		for (ParameterMasterBean parameterMasterBean : parameterMasterBeans) {

			ParameterMasterForm parameterForm = new ParameterMasterForm();

			parameterForm.setParameterName(parameterMasterBean.getParameterName());
			if (parameterMasterBean.getPermissibleLimit().trim().equalsIgnoreCase("-1")) {
				parameterForm.setPermissibleLimit("No Relaxation");
			} else {
				parameterForm.setPermissibleLimit(parameterMasterBean.getPermissibleLimit());
			}
			parameterForm.setAcceptableLimit(parameterMasterBean.getAcceptableLimit());
			parameterForm.setwHOPermissibleLimit(String.valueOf(parameterMasterBean.getwHOPermissibleLimit()));
			parameterForm.setwHOAcceptableLimit(String.valueOf(parameterMasterBean.getwHOAcceptableLimit()));
			if (MisUtility.ifEmpty(parameterMasterBean.getCombodetailUom())) {
				parameterForm.setUom(String.valueOf(parameterMasterBean.getCombodetailUom().getCmb_name()));
				parameterForm.setUom1(String.valueOf(parameterMasterBean.getCombodetailUom().getCmb_id()));
			}
			parameterForm.setStatus(String.valueOf(parameterMasterBean.getActiveFlag()));
			parameterForm.setStartDate(MisUtility.convertDateString(parameterMasterBean.getStartDate()));
			parameterForm.setEndDate(MisUtility.convertDateString(parameterMasterBean.getEndDate()));
			parameterForm.setParameterId(String.valueOf(parameterMasterBean.getParameterId()));
			
			parameterMasterForm.add(parameterForm);
		}
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return parameterMasterForm;
	}

	public boolean saveParameter(ParameterMasterForm parameterMasterForm) {

		ParameterMasterBean parameterMasterLst = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(parameterMasterForm)) {
				parameterMasterLst = populateParameterMaster(parameterMasterForm);

				if (MisUtility.ifEmpty(parameterMasterLst)) {
					status = parameterMasterDao.saveParameterData(parameterMasterLst);
				}

			}

		} catch (Exception e) {

		}
		return status;
	}

	public boolean updateParameter(ParameterMasterForm parameterMasterForm) {

		ParameterMasterBean parameterMasterLst = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(parameterMasterForm)) {
				List<ParameterMasterBean> parameterMasterBeans = parameterMasterDao
						.findParameterData(parameterMasterForm);
				if (!MisUtility.ifEmpty(parameterMasterBeans)) {
					parameterMasterLst = populateParameterMasterBeans(parameterMasterForm, parameterMasterBeans);

					if (MisUtility.ifEmpty(parameterMasterLst)) {
						status = parameterMasterDao.updateParameterData(parameterMasterLst);
					}
				}

			}

		} catch (Exception e) {

		}
		return status;
	}

	private ParameterMasterBean populateParameterMasterBeans(ParameterMasterForm parameterMasterForm,
			List<ParameterMasterBean> parameterMasterBeans) {

		ParameterMasterBean parameterMasterBean = null;
		try {
			if (!MisUtility.ifEmpty(parameterMasterBeans)) {
				parameterMasterBean = new ParameterMasterBean();
				parameterMasterBean.setParameterId(parameterMasterBeans.get(0).getParameterId());
				parameterMasterBean.setParameterName(parameterMasterForm.getParameterName().trim());
				if (MisUtility.ifEmpty(parameterMasterForm.getUom())) {
					parameterMasterBean.setUom(Integer.parseInt(parameterMasterForm.getUom()));
				}
				if (MisUtility.ifEmpty(parameterMasterForm.getAcceptableLimit())) {
					parameterMasterBean.setAcceptableLimit(parameterMasterForm.getAcceptableLimit());
				}
				if (MisUtility.ifEmpty(parameterMasterForm.getNoRelaxation())) {
					parameterMasterBean.setPermissibleLimit(parameterMasterForm.getNoRelaxation());
				}
				if (!(MisUtility.ifEmpty(parameterMasterForm.getNoRelaxation()))) {
					parameterMasterBean.setPermissibleLimit(parameterMasterForm.getPermissibleLimit());
				}
				if (MisUtility.ifEmpty(parameterMasterForm.getwHOPermissibleLimit())) {
					parameterMasterBean.setwHOPermissibleLimit(parameterMasterForm.getwHOPermissibleLimit());
				}
				if (MisUtility.ifEmpty(parameterMasterForm.getwHOAcceptableLimit())) {
					parameterMasterBean.setwHOAcceptableLimit(parameterMasterForm.getwHOAcceptableLimit());
				}
				if (MisUtility.ifEmpty(parameterMasterForm.getStatus())) {
					parameterMasterBean.setActiveFlag(Integer.parseInt(parameterMasterForm.getStatus()));
				}
				parameterMasterBean.setStartDate(MisUtility.convertStringSqlDate(parameterMasterForm.getStartDate()));
				parameterMasterBean.setEndDate(MisUtility.convertStringSqlDate(parameterMasterForm.getEndDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterMasterBean;
	}

	private ParameterMasterBean populateParameterMaster(ParameterMasterForm parameterMasterForm) {

		ParameterMasterBean parameterMasterBean = null;
		try {
			parameterMasterBean = new ParameterMasterBean();

			parameterMasterBean.setParameterName(parameterMasterForm.getParameterName().trim());

			if (MisUtility.ifEmpty(parameterMasterForm.getUom())) {
				parameterMasterBean.setUom(Integer.parseInt(parameterMasterForm.getUom()));
			}

			if (MisUtility.ifEmpty(parameterMasterForm.getNoRelaxation())) {
				parameterMasterBean.setPermissibleLimit(parameterMasterForm.getNoRelaxation());
			}

			if (!(MisUtility.ifEmpty(parameterMasterForm.getNoRelaxation()))) {
				parameterMasterBean.setPermissibleLimit(parameterMasterForm.getPermissibleLimit());
			}

			parameterMasterBean.setAcceptableLimit(parameterMasterForm.getAcceptableLimit());

			if (MisUtility.ifEmpty(parameterMasterForm.getwHOPermissibleLimit())) {
				parameterMasterBean.setwHOPermissibleLimit(parameterMasterForm.getwHOPermissibleLimit());
			}

			if (MisUtility.ifEmpty(parameterMasterForm.getwHOAcceptableLimit())) {
				parameterMasterBean.setwHOAcceptableLimit(parameterMasterForm.getwHOAcceptableLimit());
			}

			if (MisUtility.ifEmpty(parameterMasterForm.getStatus())) {
				parameterMasterBean.setActiveFlag(Integer.parseInt(parameterMasterForm.getStatus()));
			}

			parameterMasterBean.setStartDate(MisUtility.convertStringSqlDate(parameterMasterForm.getStartDate()));

			parameterMasterBean.setEndDate(MisUtility.convertStringSqlDate(parameterMasterForm.getEndDate()));

			parameterMasterBean
					.setCreatedByUSer(Integer.parseInt(String.valueOf(parameterMasterForm.getCreatedByUser())));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterMasterBean;
	}

	@Override
	public List<ParameterMasterForm> getListBasedOnColumnSorting(List<ParameterMasterForm> formList,
			Integer sortingColumn, String sortingOrder, Object t) {
		try {
			if (sortingColumn == 1) {
				Collections.sort(formList,
						new ObjectComparator<ParameterMasterForm>("parameterName", sortingOrder, t.getClass()));
			}
			if (sortingColumn == 2) {
				Collections.sort(formList,
						new ObjectComparator<ParameterMasterForm>("uom", sortingOrder, t.getClass()));
			}
			if (sortingColumn == 3) {
				Collections.sort(formList,
						new ObjectComparator<ParameterMasterForm>("acceptableLimit", sortingOrder, t.getClass()));
			}
			if (sortingColumn == 4) {
				Collections.sort(formList,
						new ObjectComparator<ParameterMasterForm>("permissibleLimit", sortingOrder, t.getClass()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formList;
	}

	@Override
	public List<ParameterMasterForm> getListBasedOnSearchParameter(String searchParameter,
			List<ParameterMasterForm> formList) {
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				List<ParameterMasterForm> parameterMasterList = new ArrayList<ParameterMasterForm>();
				searchParameter = searchParameter.toUpperCase();
				for (ParameterMasterForm masterForm : formList) {
					if (masterForm.getParameterName().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getStatus().toUpperCase().indexOf(searchParameter) != -1) {

						parameterMasterList.add(masterForm);
					}
				}
				formList = parameterMasterList;
				parameterMasterList = null;
			}
		} catch (Exception e) {

		}
		return formList;
	}

}
