/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.finance.bean.ComponentBean;
import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ComponentDaoImpl extends HibernateDaoSupport implements ComponentDao {

	private Logger log = Logger.getLogger(ComponentDaoImpl.class);
	
	@Override
	public Long save(ComponentBean componentBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdate(componentBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return componentBean.getComponentId();
	}

	@Override
	public boolean saveDetails(ComponentDetailsBean componentDetailBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdate(componentDetailBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentDetailsBean> getParent(String componentType,String secondComType) throws DataAccessException {
		
		List<ComponentDetailsBean>  componentDetailsBeans=null;
		List<Integer> componentTypes=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean","componentBean");
			criteria.add(Restrictions.eq("componentBean.activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			if(MisUtility.ifEmpty(secondComType)){
				componentTypes=new ArrayList<Integer>();
				componentTypes.add(Integer.parseInt(componentType));
				componentTypes.add(Integer.parseInt(secondComType));
				criteria.add(Restrictions.in("componentBean.componentType", componentTypes));
			}else{
				criteria.add(Restrictions.eq("componentBean.componentType", Integer.parseInt(componentType)));
			}
			
			componentDetailsBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return componentDetailsBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateComponent(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> componentDto = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean","componentBean");
			criteria.createAlias("componentBean.comboDetailsComponentType", "comboDetailsComponentType");
			criteria.createAlias("componentBean.componentDetailsBean","componentDetailsBean",CriteriaSpecification.LEFT_JOIN);
			
			/*criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));*/
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("componentDetailsId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("componentName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("comboDetailsComponentType.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("componentDetailsBean.componentName", searchString, MatchMode.ANYWHERE))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("componentName"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("componentName"));
				}else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboDetailsComponentType.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboDetailsComponentType.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("componentDetailsBean.componentName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("componentDetailsBean.componentName"));
				}
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("componentName"), "componentName")
					.add(Projections.property("comboDetailsComponentType.cmb_name"), "componentType")
					.add(Projections.property("componentDetailsBean.componentName"), "parentComponentName")
					.add(Projections.property("description"), "description")
					.add(Projections.property("componentBean.componentId"), "componentId")
					.add(Projections.property("componentDetailsId"), "componentDetailsId")
					.add(Projections.property("componentBean.componentType"), "componentTypes")
					.add(Projections.property("componentBean.parentCompoId"), "parentCompoId")
					.add(Projections.property("componentBean.activeFlag"), "activeFlag")
					);
					
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			//getHibernateTemplate().setCacheQueries(true);
			componentDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return componentDto;
	}

}
