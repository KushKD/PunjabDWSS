/**
 * 
 */
package com.prwss.min.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleCodeLabMapping;
import com.prwss.min.bean.SchemeMapping;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.quality.ReceiveSampleForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;

/**
 * @author bhsingh
 *
 */
public class ReceiveSampleDaoImpl extends HibernateDaoSupport  implements ReceivingSampleDao{

	private Logger log = Logger.getLogger(ReceiveSampleDaoImpl.class);

	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployee(List<String> statusList,String designationId) 	throws DataAccessException {

		List<EmployeeBean> employeeBeans = null;
		try {
			List<Object[]> obj = getSession().createCriteria(EmployeeBean.class, "employeeBean")
					.add(Restrictions.eq("employeeBean.designationId",designationId))
					.add(Restrictions.in("misAuditBean.status", statusList))
					.setProjection(Projections.projectionList()
							.add(Projections.property("employeeBean.employeeId"))
							/*.add(Projections.property("employeeBean.mobilePhone"))
							.add(Projections.property("employeeBean.officialEmailId"))*/
							
							.add(Projections.property("employeeBean.employeeName"))).list();
			
			if(!MisUtility.ifEmpty(obj)){
				employeeBeans=new ArrayList<EmployeeBean>();
				for(Object[] object:obj){
					EmployeeBean bean=new EmployeeBean();
					bean.setEmployeeId(Long.parseLong(object[0].toString()));
					bean.setEmployeeName(object[1].toString());
					employeeBeans.add(bean);
			}
			}
			
					
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		//System.out.println("daooooooo+++++++++++"+employeeBeans);
		return employeeBeans;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDesignationBean> getEmployeeDesig(List<String> statusList) throws DataAccessException {
			
		List<EmployeeDesignationBean> employeeBean=null;
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeDesignationBean.class);
				
				employeeBean = getHibernateTemplate().findByCriteria(criteria);

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}
			//System.out.println("daooooooo+++++++++++"+employeeBeans);
			return employeeBean;
	}
	@Override
	public boolean saveSampleData(ReceiveSampleBean receiveSampleLst) throws DataAccessException {
		
		try{
			
			if(MisUtility.ifEmpty(receiveSampleLst)){
				getHibernateTemplate().save(receiveSampleLst);
			}
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
		return true;

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> findSchemeFromVillage(String VillageId) throws DataAccessException {
		List<VillageDetailsBean> schemeVillageBean=null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
			criteria.add(Restrictions.eq("location_id", Integer.parseInt(VillageId)));
			criteria.add(Restrictions.ne("active_flag",Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			
			schemeVillageBean = getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
		return schemeVillageBean;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PMSSchemeDetailsBean> findSchemeName(List<Integer> schemeId)
			throws DataAccessException {
		
		List<PMSSchemeDetailsBean> pMSSchemeDetailsBean=null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(PMSSchemeDetailsBean.class,"pMSSchemeDetailsBean");
			//criteria.createCriteria("pMSSchemeDetailsBean.villageSchemeMappingBean","villageSchemeMappingBean");
			criteria.add(Restrictions.in("pMSSchemeDetailsBean.scheme_id",schemeId));
			//criteria.add(Restrictions.ne("villageSchemeMappingBean.activeFlag",Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			
			pMSSchemeDetailsBean = getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
		return pMSSchemeDetailsBean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> findHabitationFromVillage(String VillageId) throws DataAccessException {
		
		List<VillageDetailsBean> villageBean=null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
			criteria.add(Restrictions.eq("location_id", Integer.parseInt(VillageId)));
			criteria.add(Restrictions.ne("active_flag",Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			villageBean = getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
		return villageBean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployeeDetails(String empId,List<String> status)throws DataAccessException {
		List<EmployeeBean> employeeName=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(EmployeeBean.class);
			criteria.add(Restrictions.eq("employeeId", Long.parseLong(empId)));
			criteria.add(Restrictions.isNotNull("desigId"));
			criteria.add(Restrictions.in("misAuditBean.status", status));
			employeeName=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return employeeName;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> getSampleCollectionByPagination(String searchString,int clickedColumn,String colOrder)
			throws DataAccessException {

		List<ReceiveSampleBean> locationMasterBeans = null;
		try {
				DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class,"receiveSampleBean");
				criteria.createAlias("receiveSampleBean.labMasterBean", "labMasterBean");
				criteria.createAlias("receiveSampleBean.schemeMapping", "schemeMapping");
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.add(Restrictions.eq("receiveSampleBean.is_distributed", Integer.parseInt(MISConstants.ZERO)));
				
				if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
					criteria.addOrder(Order.desc("sampleId"));
				}
				if (MisUtility.ifEmpty(searchString)) {
					criteria.add(Restrictions.disjunction()
							.add(Restrictions.ilike("sampleNumber", searchString, MatchMode.ANYWHERE))
							.add(Restrictions.sqlRestriction("lab_to_be_tested::text like '%" + searchString + "%'"))
							.add(Restrictions.sqlRestriction("water_source::text like '%" + searchString + "%'"))
							.add(Restrictions.sqlRestriction("habitation_type::text like '%" + searchString + "%'"))
							);

				}
				if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
					if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("sampleNumber"));
					} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("sampleNumber"));
					} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("labTested"));
					} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("labTested"));
					} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("waterSource"));
					} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("waterSource"));
					} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
						criteria.addOrder(Order.asc("habitationType"));
					} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
							&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
						criteria.addOrder(Order.desc("habitationType"));
					}
				}
				criteria.getExecutableCriteria(getSession()).setMaxResults(100);
				//getHibernateTemplate().setCacheQueries(true);
				locationMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				
				
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> getSampleCollection(ReceiveSampleForm receiveSampleForm) throws DataAccessException {
		List<ReceiveSampleBean> locationMasterBeans = null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
		
		criteria.add(Restrictions.eq("sampleNumber", receiveSampleForm.getSampleNumber()));
		criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		locationMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> findSampleCollection(ReceiveSampleForm receiveSampleForm)
			throws DataAccessException {
		List<ReceiveSampleBean> locationMasterBeans = null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
		if(MisUtility.ifEmpty(receiveSampleForm.getSampleId())){
			criteria.add(Restrictions.eq("sampleId", Integer.parseInt(receiveSampleForm.getSampleId())));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		}
			
		
		locationMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}
	@Override
	public boolean updateSampleCollection(ReceiveSampleBean receiveSampleBean)
			throws DataAccessException {
			try{
			
			if(MisUtility.ifEmpty(receiveSampleBean)){
				getHibernateTemplate().update(receiveSampleBean);
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
			return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeMapping> fetchWaterSource(String schemeId) throws DataAccessException {
		// TODO Auto-generated method stub
		
		List<SchemeMapping> schemeMappings=null;
		try{
		DetachedCriteria criteria=DetachedCriteria.forClass(SchemeMapping.class);
		criteria.add(Restrictions.eq("schemeId",Integer.parseInt(schemeId)));
		criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		schemeMappings=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
		e.printStackTrace();
		log.error(e.getLocalizedMessage(),e);
		throw e;
		}	
	return schemeMappings;
	}
	@Override
	public String fetchSchemeName(String performaMaserBeans) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabMasterBean> findSampleCode(String labId) {
		List<LabMasterBean> sampleCodeLabMappings=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(LabMasterBean.class);
			criteria.add(Restrictions.eq("labId",Long.parseLong(labId)));
			sampleCodeLabMappings=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return sampleCodeLabMappings;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SampleCodeLabMapping> findLabIdSampleMapping(String labId)throws DataAccessException {
		
		List<SampleCodeLabMapping> sampleCodeLabMappings=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SampleCodeLabMapping.class);
			criteria.add(Restrictions.eq("labId", Integer.parseInt(labId)));
			sampleCodeLabMappings=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			throw e;
		}
		return sampleCodeLabMappings;
	}
	@Override
	public boolean update(SampleCodeLabMapping sampleCodeLabMapping)throws DataAccessException {
		try{
			getHibernateTemplate().update(sampleCodeLabMapping);
		}catch(DataAccessException e){
			throw e;
		}
		return true;
	}


}
