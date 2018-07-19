/**
 * 
 */
package com.prwss.min.quality;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface CollectionCenterBo {

	public boolean save(CollectionCenterForm collectionCenterForm,MISSessionBean misSessionBean)throws MISException;
}
