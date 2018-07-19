/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.SampleDto;
import com.prwss.mis.common.MISSessionBean;

/**
 * @author BH390738
 *
 */
public interface FreezeUnFreezeDao {

	public List<SampleDto> getSampleDetails(MISSessionBean misSessionBean,String lab,String fromDate,String toDate,String searchString, int clickedColumn, String colOrder)throws DataAccessException;
}
