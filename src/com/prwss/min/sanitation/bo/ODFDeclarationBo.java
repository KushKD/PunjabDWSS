/**
 * 
 */
package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.ODFDeclarationForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ODFDeclarationBo {

	public boolean save(ODFDeclarationForm odfDeclarationForm) throws MISException;
}
