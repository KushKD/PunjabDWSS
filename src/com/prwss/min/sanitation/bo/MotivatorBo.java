/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.MotivatorBean;
import com.prwss.min.sanitation.form.MotivatorEntryForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface MotivatorBo {

	public boolean saveMotivatorDetails(MotivatorEntryForm motivatorEntryForm)throws MISException;
	public List<MotivatorBean> validateMotivatorDetails(MotivatorEntryForm motivatorEntryForm)throws MISException;

	
}
