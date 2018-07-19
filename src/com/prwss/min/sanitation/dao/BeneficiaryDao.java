package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.bean.BeneficiaryEntryDetailsDto;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;

/**
 * @author BH390738
 *
 */
public interface BeneficiaryDao {

	public List<BeneficiaryEntryBean> getBeneficiaryDetails(BeneficiaryForm beneficiaryForm) throws DataAccessException;
	public boolean saveBeneficiaryDetails(BeneficiaryEntryBean beneficiaryEntryBean)throws DataAccessException;
	public List<BeneficiaryDto> populateBeneficiaryDetails(ViewRegistrationsForm viewRegistrationForm,String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	public List<BeneficiaryEntryDetailsDto> getBeneficiaryDetails(String beneficiaryId)throws DataAccessException;
	public List<BeneficiaryEntryDetailsDto> getElectricityBillData(String beneficiaryId)throws DataAccessException;
}
