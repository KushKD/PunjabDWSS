/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.ComponentBean;
import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.dao.ComponentDao;
import com.prwss.min.finance.form.ComponentForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ComponentBoImpl extends AbstractPaginationMaster<FinanceDto> implements ComponentBo<FinanceDto> {

	private Logger log = Logger.getLogger(ComponentBoImpl.class);

	private ComponentDao componentDao;

	public ComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean save(ComponentForm componentForm, MISSessionBean misSessionBean) throws MISException {

		ComponentBean componentBean = null;
		ComponentDetailsBean componentDetailsBean = null;
		Long componentId;
		boolean status=false;
		try {
			componentBean = getComponentBean(componentForm, misSessionBean);
			if (MisUtility.ifEmpty(componentBean)) {
				componentId = componentDao.save(componentBean);

				if (MisUtility.ifEmpty(componentId)) {
					componentDetailsBean=populateComponentDetailsBean(componentForm, misSessionBean, componentId);
					if(MisUtility.ifEmpty(componentDetailsBean)){
						status=componentDao.saveDetails(componentDetailsBean);
					}
				}
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}
	
	
	private ComponentBean getComponentBean(ComponentForm componentForm, MISSessionBean misSessionBean) {

		ComponentBean componentBean = null;
		try {
			if (MisUtility.ifEmpty(componentForm)) {
				componentBean = new ComponentBean();

				if (MisUtility.ifEmpty(componentForm.getComponentType())) {
					componentBean.setComponentType(Integer.parseInt(componentForm.getComponentType()));
				}
				if (MisUtility.ifEmpty(componentForm.getComponentType())) {
					componentBean.setParentCompoId(Integer.parseInt(componentForm.getParentComponent()));
				}
				componentBean.setActiveFlag(Integer.parseInt(componentForm.getStatus()));
				componentBean.setCrtByUsr(misSessionBean.getEnteredBy());
				if(MisUtility.ifEmpty(componentForm.getComponentId())){
					componentBean.setComponentId(Long.parseLong(componentForm.getComponentId()));
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return componentBean;
	}

	private ComponentDetailsBean populateComponentDetailsBean(ComponentForm componentForm,
			MISSessionBean misSessionBean, Long componentId) {

		ComponentDetailsBean componentDetailsBean = null;
		try {
			if (MisUtility.ifEmpty(componentForm.getComponentName())) {
				componentDetailsBean = new ComponentDetailsBean();
				componentDetailsBean.setComponentName(componentForm.getComponentName());
				componentDetailsBean.setFinCompId(componentId);
				componentDetailsBean.setActiveFlag(Integer.parseInt(componentForm.getStatus()));
				componentDetailsBean.setCrtByUsr(misSessionBean.getEnteredBy());
				componentDetailsBean.setDescription(componentForm.getDescription());
				if(MisUtility.ifEmpty(componentForm.getComponentDetailsId())){
					componentDetailsBean.setComponentDetailsId(Long.parseLong(componentForm.getComponentDetailsId()));
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return componentDetailsBean;
	}

	
}
