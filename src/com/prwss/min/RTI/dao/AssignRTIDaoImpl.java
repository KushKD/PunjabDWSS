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

import com.prwss.min.RTI.bean.AssignRTIDetailsDto;
import com.prwss.min.RTI.bean.AssignRtiDto;
import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.form.AssignRtiForm;
import com.prwss.mis.admin.dao.DesignationBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class AssignRTIDaoImpl extends HibernateDaoSupport implements AssignRTIDao {
	
	private Logger log = Logger.getLogger(AssignRTIDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AssignRtiDto> getAllRTI(String searchString,int clickedColumn,String colOrder) throws DataAccessException, DataAccessException {
		// TODO Auto-generated method stub
		
		List<AssignRtiDto> RTIDto = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubmitRtiBean.class);
			
			criteria.add(Restrictions.isNull("isclosed"));
			criteria.add(Restrictions.isNull("isassigned"));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
				
				if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
					criteria.addOrder(Order.desc("rtiID"));
				}
				if (MisUtility.ifEmpty(searchString)) {
					criteria.add(Restrictions.disjunction()
							.add(Restrictions.sqlRestriction("rti_id::text like '%" + searchString + "%'"))
							.add(Restrictions.ilike("shortname", searchString, MatchMode.ANYWHERE))
							.add(Restrictions.ilike("rtiRefNo", searchString, MatchMode.ANYWHERE))
							.add(Restrictions.ilike("name", searchString, MatchMode.ANYWHERE))
							);

				}
				if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
					if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("rtiID"));
					} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("rtiID"));
					} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("shortname"));
					} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("shortname"));
					} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("rtiRefNo"));
					} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("rtiRefNo"));
					} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("name"));
					} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("name"));
					}
					
				}
				
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("rtiID"),"rtiID")
						.add(Projections.property("shortname"),"shortname")
						.add(Projections.property("rtiRefNo"),"rtiRefNo")
						.add(Projections.property("name"),"name")); 
				
				
				criteria.getExecutableCriteria(getSession()).setMaxResults(100);
				criteria.setResultTransformer(Transformers.aliasToBean(AssignRtiDto.class));
				
				

			
				RTIDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println("RTI BEAN" + RTIDto.toString());

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return RTIDto;
	}

	/**TODO
	 * ||
	 * ||
	 * ||
	 * ||
	 * ||
	 * \/
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssignRTIDetailsDto> getRTIDetails(String rtiId) throws DataAccessException {
		List<AssignRTIDetailsDto> rtiAssignDetailsDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubmitRtiBean.class, "rtiAssignBean");
			/*criteria.createAlias("rtiAssignBean.combodetailGender", "combodetailGender");
			criteria.createAlias("rtiAssignBean.locationDetailBean", "locationDetailBean");
			criteria.createAlias("rtiAssignBean.districtDetailBean", "districtDetailBean");
			criteria.createAlias("rtiAssignBean.blockDetailBean", "blockDetailBean");*/

			if (MisUtility.ifEmpty(rtiId)) {
				criteria.add(Restrictions.eq("rtiID", Integer.parseInt(rtiId)));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("rtiAssignBean.rtiID"), "rtiID")
						.add(Projections.property("rtiAssignBean.rtiRefNo"), "rtiRefNo")
						.add(Projections.property("rtiAssignBean.office"), "office")
						//.add(Projections.property("combodetailGender.cmb_name"), "gender")
						.add(Projections.property("rtiAssignBean.shortname"), "shortname")
						.add(Projections.property("rtiAssignBean.details"), "details")
						.add(Projections.property("rtiAssignBean.mobile"), "mobile")
						.add(Projections.property("rtiAssignBean.landline"), "landline")
						.add(Projections.property("rtiAssignBean.email"), "email")
						.add(Projections.property("rtiAssignBean.address"), "address")
						.add(Projections.property("rtiAssignBean.literate"), "literate")
						.add(Projections.property("rtiAssignBean.poverty"), "poverty")
						.add(Projections.property("rtiAssignBean.name"), "name")
						//.add(Projections.property("locationDetailBean.locationName"), "villageName")
						//.add(Projections.property("districtDetailBean.locationName"), "districtName")
						//.add(Projections.property("blockDetailBean.locationName"), "blockName")
						.add(Projections.property("rtiAssignBean.applicationFileName"), "applicationFileName")
						.add(Projections.property("rtiAssignBean.documentsFileName"), "documentsFileName")
						.add(Projections.property("rtiAssignBean.amount"), "amount")
						.add(Projections.property("rtiAssignBean.paymentMode"), "paymentMode")
						.add(Projections.property("rtiAssignBean.datePayment"), "datePayment")
						.add(Projections.property("rtiAssignBean.remarks"), "remarks"));
				
				
				criteria.setResultTransformer(Transformers.aliasToBean(AssignRTIDetailsDto.class));
				rtiAssignDetailsDto = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		
		return rtiAssignDetailsDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DesignationBean> getAllDesignation() throws DataAccessException {
		List<DesignationBean> designation = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DesignationBean.class);
			designation = getHibernateTemplate().findByCriteria(criteria);
				
				

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return designation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getAllEmployee(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
		//designationId
		List<EmployeeBean> designation = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			criteria.add(Restrictions.eq("designationId", id));
			designation = getHibernateTemplate().findByCriteria(criteria);
				
				

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return designation;
		
	}
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<SubmitRtiBean> findRtiCollection(AssignRtiForm rtiForm)
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
	public Boolean assignRtiResponse(SubmitRtiBean submitRtiBean2)
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

}
