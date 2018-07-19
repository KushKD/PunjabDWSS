/**
 * 
 */
package com.prwss.min.sanitation.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.ODFDeclarationBean;
import com.prwss.min.sanitation.dao.ODFDeclarationDao;
import com.prwss.min.sanitation.form.ODFDeclarationForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ODFDeclarationBoImpl implements ODFDeclarationBo {

	private Logger log = Logger.getLogger(ODFDeclarationBoImpl.class);
	private ODFDeclarationDao odfDeclarationDao;

	public ODFDeclarationDao getOdfDeclarationDao() {
		return odfDeclarationDao;
	}

	public void setOdfDeclarationDao(ODFDeclarationDao odfDeclarationDao) {
		this.odfDeclarationDao = odfDeclarationDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean save(ODFDeclarationForm odfDeclarationForm) throws MISException {
		ODFDeclarationBean odfDeclarationBean = null;
		boolean flag = false;
		try {
			odfDeclarationBean = populateODFDeclarationDetails(odfDeclarationForm);
			if (MisUtility.ifEmpty(odfDeclarationBean)) {
				flag = odfDeclarationDao.save(odfDeclarationBean);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return flag;
	}

	private ODFDeclarationBean populateODFDeclarationDetails(ODFDeclarationForm odfDeclarationForm)
			throws MISException {

		ODFDeclarationBean odfDeclarationBean = new ODFDeclarationBean();
		try {
			if (MisUtility.ifEmpty(odfDeclarationForm.getDistrict()))
				odfDeclarationBean.setDistrictId(Integer.parseInt(odfDeclarationForm.getDistrict()));

			if (MisUtility.ifEmpty(odfDeclarationForm.getBlock()))
				odfDeclarationBean.setBlockId(Integer.parseInt(odfDeclarationForm.getBlock()));

			if (MisUtility.ifEmpty(odfDeclarationForm.getVillage()))
				odfDeclarationBean.setVillageId(Integer.parseInt(odfDeclarationForm.getVillage()));

			odfDeclarationBean.setGramPanchayatId(odfDeclarationForm.getGramPanchayat());

			if (MisUtility.ifEmpty(odfDeclarationForm.getDateOfMeeting())) {
				odfDeclarationBean
						.setMeetingDate(MisUtility.convertStringSqlDate(odfDeclarationForm.getDateOfMeeting()));
			}

			if (MisUtility.ifEmpty(odfDeclarationForm.getMeetingPlace())) {
				odfDeclarationBean.setMeetingPlace(Integer.parseInt(odfDeclarationForm.getMeetingPlace()));
			}
			odfDeclarationBean.setChairedBy(odfDeclarationForm.getChairedBy());
			odfDeclarationBean.setDeclarationName(odfDeclarationForm.getDeclaration().getFileName());
			odfDeclarationBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			odfDeclarationBean.setCrtByUsr(Integer.parseInt(String.valueOf(odfDeclarationForm.getCreatedBy())));

			if (MisUtility.ifEmpty(odfDeclarationForm.getDeclaration())) {
				odfDeclarationBean.setDeclaration(odfDeclarationForm.getDeclaration().getFileData());
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException();
		}

		return odfDeclarationBean;
	}
}
