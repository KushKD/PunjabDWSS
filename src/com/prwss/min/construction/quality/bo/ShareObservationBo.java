/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.ShareObservationForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ShareObservationBo {

	public boolean saveObservation(ShareObservationForm shareObservationForm,int entBy)throws MISException;
}
