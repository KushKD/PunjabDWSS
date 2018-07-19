package com.prwss.min.RTI.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.form.SubmitRtiForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class SubmitRtiDaoImpl extends HibernateDaoSupport implements SubmitRtiDao{
	private Logger log = Logger.getLogger(SubmitRtiDaoImpl.class);
	
	private MISSessionBean misSessionBean;
	
	
	
	
	
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}


	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}


	@Override
	public boolean saveRtiDetails(SubmitRtiBean submitRtiBean) throws DataAccessException {
		try {
			getHibernateTemplate().save(submitRtiBean);

		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<UpdateRTIDto> getRtiByPagination(String searchString,int clickedColumn,String colOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub

		List<UpdateRTIDto> submitRtiBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SubmitRtiBean.class);
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.isNull("isclosed"));
			criteria.add(Restrictions.eq("isassigned", Integer.parseInt(MISConstants.ONE)));
			//criteria.add(Restrictions.eq("employee", misSessionBean.getEnteredBy()));
			
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("rtiID"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("rtiRefNo", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("shortname", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("details", searchString, MatchMode.ANYWHERE))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("rtiRefNo"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("rtiRefNo"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("shortname"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("shortname"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("name"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("name"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("details"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("details"));
				}
				
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("rtiID"), "rtiID")
					.add(Projections.property("rtiRefNo"), "rtiRefNo")
					.add(Projections.property("shortname"), "shortname")
					.add(Projections.property("name"), "name")
					.add(Projections.property("details"), "details"));
			
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			criteria.setResultTransformer(Transformers.aliasToBean(UpdateRTIDto.class));
			
			submitRtiBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return submitRtiBean;
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<UpdateRTIDto> getRtiDetails(String rtiId) throws DataAccessException {

		List<UpdateRTIDto> updateRTIDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubmitRtiBean.class,"submitRtiBean");

			if (MisUtility.ifEmpty(rtiId)) {
				

				criteria.add(Restrictions.eq("submitRtiBean.rtiID", Integer.parseInt(rtiId)));
				criteria.add(Restrictions.eq("submitRtiBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("submitRtiBean.rtiID"), "rtiID")
						.add(Projections.property("submitRtiBean.rtiRefNo"), "rtiRefNo")
						.add(Projections.property("submitRtiBean.office"), "office")
						//.add(Projections.property("combodetailGender.cmb_name"), "gender")
						.add(Projections.property("submitRtiBean.shortname"), "shortname")
						.add(Projections.property("submitRtiBean.details"), "details")
						.add(Projections.property("submitRtiBean.mobile"), "mobile")
						.add(Projections.property("submitRtiBean.landline"), "landline")
						.add(Projections.property("submitRtiBean.email"), "email")
						.add(Projections.property("submitRtiBean.address"), "address")
						.add(Projections.property("submitRtiBean.literate"), "literate")
						.add(Projections.property("submitRtiBean.poverty"), "poverty")
						.add(Projections.property("submitRtiBean.name"), "name")
						//.add(Projections.property("locationDetailBean.locationName"), "villageName")
						//.add(Projections.property("districtDetailBean.locationName"), "districtName")
						//.add(Projections.property("blockDetailBean.locationName"), "blockName")
						.add(Projections.property("submitRtiBean.applicationFileName"), "applicationFileName")
						.add(Projections.property("submitRtiBean.documentsFileName"), "documentsFileName")
						.add(Projections.property("submitRtiBean.amount"), "amount")
						.add(Projections.property("submitRtiBean.paymentMode"), "paymentMode")
						.add(Projections.property("submitRtiBean.datePayment"), "datePayment")
						.add(Projections.property("submitRtiBean.remarks"), "remarks"));
				
				
				criteria.setResultTransformer(Transformers.aliasToBean(UpdateRTIDto.class));
				updateRTIDto = getHibernateTemplate().findByCriteria(criteria);
				//criteria.setResultTransformer(Transformers.aliasToBean(UpdateRTIDto.class));
				//updateRTIDto=getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		
		return updateRTIDto;
	}

	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<UpdateRTIDto> getAttachmentData(String rtiID) throws DataAccessException {

		List<UpdateRTIDto> updateRTIDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubmitRtiBean.class, "submitRtiBean");
			if (MisUtility.ifEmpty(rtiID)) {
				criteria.add(Restrictions.eq("submitRtiBean.rtiID", Integer.parseInt(rtiID)));
				criteria.add(Restrictions.eq("submitRtiBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("submitRtiBean.applicationFileName"), "applicationFileName")
						.add(Projections.property("submitRtiBean.applicationFile"), "applicationFile")
						.add(Projections.property("submitRtiBean.documentsFileName"), "documentsFileName")
						.add(Projections.property("submitRtiBean.documentsFile"), "documentsFile"));
				criteria.setResultTransformer(Transformers.aliasToBean(UpdateRTIDto.class));
				updateRTIDto = getHibernateTemplate().findByCriteria(criteria);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return updateRTIDto;
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<SubmitRtiBean> findRtiCollection(SubmitRtiForm rtiForm)
			throws DataAccessException {
		List<SubmitRtiBean> submitRtiBeans = null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(SubmitRtiBean.class);
		/*if(MisUtility.ifEmpty(surveymasterform.getSurveyID())){
			criteria.add(Restrictions.eq("survey_id", Integer.parseInt(surveymasterform.getSurveyID())));*/
		if(MisUtility.ifEmpty(rtiForm.getRtiID())){
				criteria.add(Restrictions.eq("rtiID",Integer.parseInt(rtiForm.getRtiID())));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		}
			
		
		submitRtiBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return submitRtiBeans;
	}
	
	
	@Transactional
	@Override
	public Boolean updateRtiResponse(SubmitRtiBean submitRtiBean2)
			throws DataAccessException {
		try {
			System.out.println("DAO" + submitRtiBean2.toString());
			getHibernateTemplate().update(submitRtiBean2);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}
}
