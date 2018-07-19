/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.DivisionBudgetBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.dao.DivisionBudgetDao;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.document.dao.DocumentNumberBean;
import com.prwss.mis.common.document.dao.DocumentNumberDAO;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;

/**
 * @author BH390738
 *
 */
public class DivisionBudgetBoImpl extends AbstractPaginationMaster<FinanceDto> implements DivisionBudgetBo<FinanceDto> {

	private Logger log = Logger.getLogger(DivisionBudgetBoImpl.class);

	private DocumentNumberDAO documentNumberDao;
	private DivisionBudgetDao divisionBudgetDao;

	public DocumentNumberDAO getDocumentNumberDao() {
		return documentNumberDao;
	}

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public DivisionBudgetDao getDivisionBudgetDao() {
		return divisionBudgetDao;
	}

	public void setDivisionBudgetDao(DivisionBudgetDao divisionBudgetDao) {
		this.divisionBudgetDao = divisionBudgetDao;
	}

	@Override
	public String save(DivisionBudgetForm divisionBudgetForm, MISSessionBean misSessionBean) throws MISException {

		DivisionBudgetBean divisionBudgetBean = null;
		boolean flag = false;
		String budgetReferenceNo = null;
		try {
			if (MisUtility.ifEmpty(divisionBudgetForm)) {
				DocumentNumberBean documentNumebrBean = new DocumentNumberBean();
				documentNumebrBean.setDocumentType("DIVISION-BUDGET");
				DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
				if (!(MisUtility.ifEmpty(documentNumberBean))) {
					throw new MISException();
				}
				List<String> ddoName = divisionBudgetDao.getDdoName(divisionBudgetForm.getDivision());
				if (MisUtility.ifEmpty(ddoName)) {
					throw new MISException();
				}

				divisionBudgetBean = populateDivisionalBudgetBean(divisionBudgetForm, misSessionBean,
						documentNumberBean, ddoName);

				if (MisUtility.ifEmpty(divisionBudgetBean)) {
					flag = divisionBudgetDao.save(divisionBudgetBean);
					if (flag) {
						documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
						budgetReferenceNo = divisionBudgetBean.getBudgetRefNo();
					}
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}

		return budgetReferenceNo;
	}

	@Override
	public String update(DivisionBudgetForm divisionBudgetForm, MISSessionBean misSessionBean) throws MISException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		DivisionBudgetBean divisionBudgetBean = null;
		String budgetReferenceNo = null;
		try {
			if (MisUtility.ifEmpty(divisionBudgetForm.getDivAnnBudgId())) {

				divisionBudgetBeans = divisionBudgetDao.getDivisionBudgetBean(divisionBudgetForm.getDivAnnBudgId());

				if (!MisUtility.ifEmpty(divisionBudgetBeans)) {
					divisionBudgetBean = populateDivisionBean(divisionBudgetForm, misSessionBean, divisionBudgetBeans);
					if (MisUtility.ifEmpty(divisionBudgetBean)) {
						divisionBudgetDao.save(divisionBudgetBean);
						budgetReferenceNo = divisionBudgetBean.getBudgetRefNo();
					}
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return budgetReferenceNo;
	}

	private DivisionBudgetBean populateDivisionBean(DivisionBudgetForm divisionBudgetForm,
			MISSessionBean misSessionBean, List<DivisionBudgetBean> divisionBudgetBeans) throws MISException {

		DivisionBudgetBean divisionBudgetBean = null;
		try {
			for (DivisionBudgetBean divisionBudgetBean3 : divisionBudgetBeans) {
				divisionBudgetBean = new DivisionBudgetBean();
				if (MisUtility.ifEmpty(divisionBudgetForm.getFinancialYear()))
					divisionBudgetBean.setFinYear(Integer.parseInt(divisionBudgetForm.getFinancialYear()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getDivision()))
					divisionBudgetBean.setDivisionId(Integer.parseInt(divisionBudgetForm.getDivision()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getScheme()))
					divisionBudgetBean.setSchemeId(Long.parseLong(divisionBudgetForm.getScheme()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getComponent()))
					divisionBudgetBean.setComponentId(Long.parseLong(divisionBudgetForm.getComponent()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getSubComponent()))
					divisionBudgetBean.setSubComponentId(Long.parseLong(divisionBudgetForm.getSubComponent()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getSubSubComponent()))
					divisionBudgetBean.setSubSubComponentId(Long.parseLong(divisionBudgetForm.getSubSubComponent()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getSubSubComponentLevel3()))
					divisionBudgetBean
							.setSubComponentLevel3Id(Long.parseLong(divisionBudgetForm.getSubSubComponentLevel3()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getEstimatedCosts()))
					divisionBudgetBean.setEstimatedCost(Long.parseLong(divisionBudgetForm.getEstimatedCosts()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getAlreadyExpended()))
					divisionBudgetBean.setAlreadySpent(Long.parseLong(divisionBudgetForm.getAlreadyExpended()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getActivity()))
					divisionBudgetBean.setActivityId(Long.parseLong(divisionBudgetForm.getActivity()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getNextYearReq()))
					divisionBudgetBean.setReqNxtYear(Long.parseLong(divisionBudgetForm.getNextYearReq()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter1()))
					divisionBudgetBean.setQtr1(Double.parseDouble(divisionBudgetForm.getQuarter1()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter2()))
					divisionBudgetBean.setQtr2(Double.parseDouble(divisionBudgetForm.getQuarter2()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter3()))
					divisionBudgetBean.setQtr3(Double.parseDouble(divisionBudgetForm.getQuarter3()));

				if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter4()))
					divisionBudgetBean.setQtr4(Double.parseDouble(divisionBudgetForm.getQuarter4()));
				divisionBudgetBean.setDivAnnBudgId(divisionBudgetBean3.getDivAnnBudgId());
				divisionBudgetBean.setBudgetRefNo(divisionBudgetBean3.getBudgetRefNo());

				if (MisUtility.ifEmpty(divisionBudgetBean3.getLyingWithUsr())) {
					divisionBudgetBean.setLyingWithUsr(divisionBudgetBean3.getLyingWithUsr());
				}

				divisionBudgetBean.setCrtByUsr(misSessionBean.getEnteredBy());
				divisionBudgetBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

				if (MisUtility.ifEmpty(divisionBudgetBean3.getIsForward())) {
					divisionBudgetBean.setIsForward(divisionBudgetBean3.getIsForward());
				} else {
					divisionBudgetBean.setIsForward(Integer.parseInt(MISConstants.ZERO));
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return divisionBudgetBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String forward(DivisionBudgetForm divisionBudgetForm, MISSessionBean misSessionBean) throws MISException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		String userName = null;
		try {
			if (MisUtility.ifEmpty(divisionBudgetForm)) {
				divisionBudgetBeans = divisionBudgetDao.getDivisionBudgets(divisionBudgetForm.getFinancialYear(),
						divisionBudgetForm.getDivision(), misSessionBean);

				if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

					List<String> userId = divisionBudgetDao.getUserId(divisionBudgetForm.getDivision());

					if (!MisUtility.ifEmpty(userId)) {
						List<DivisionBudgetBean> divisionBudgetBeans2 = populateDivisionBeanList(divisionBudgetBeans,
								userId);

						if (!MisUtility.ifEmpty(divisionBudgetBeans2)) {
							boolean status = divisionBudgetDao.updateDivisionBudget(divisionBudgetBeans2);

							if (status) {
								List<LoginUserBean> loginUserBeans = divisionBudgetDao.getUserName(userId);

								if (!MisUtility.ifEmpty(loginUserBeans)) {
									userName = loginUserBeans.get(0).getUserName();
								}
							}
						}

					}
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return userName;
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String forwardState(DivisionBudgetForm divisionBudgetForm, MISSessionBean misSessionBean) throws MISException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		String userName = null;
		try {
			if (MisUtility.ifEmpty(divisionBudgetForm)) {
				divisionBudgetBeans = divisionBudgetDao.getDivisionBudgetState(divisionBudgetForm.getFinancialYear(),
						divisionBudgetForm.getDivision(), misSessionBean);

				if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

					
						List<DivisionBudgetBean> divisionBudgetBeans2 = populateDivisionBeanStateList(divisionBudgetBeans,
								MISConstants.JCFA);

						if (!MisUtility.ifEmpty(divisionBudgetBeans2)) {
							boolean status = divisionBudgetDao.updateDivisionBudget(divisionBudgetBeans2);

							if (status) {
								List<LoginUserBean> loginUserBeans = divisionBudgetDao.getStateUserName(MISConstants.JCFA);

								if (!MisUtility.ifEmpty(loginUserBeans)) {
									userName = loginUserBeans.get(0).getUserName();
								}
							}
						}

					}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return userName;
	}

	private List<DivisionBudgetBean> populateDivisionBeanList(List<DivisionBudgetBean> divisionBudgetBeans,
			List<String> userId) {

		try {
			if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

				Iterator<DivisionBudgetBean> iterator = divisionBudgetBeans.iterator();
				while (iterator.hasNext()) {
					DivisionBudgetBean divisionBudgetBean = (DivisionBudgetBean) iterator.next();
					divisionBudgetBean.setIsForward(Integer.parseInt(MISConstants.ONE));
					divisionBudgetBean.setLyingWithUsr(userId.get(0));
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return divisionBudgetBeans;
	}
	
	private List<DivisionBudgetBean> populateDivisionBeanStateList(List<DivisionBudgetBean> divisionBudgetBeans,
			String userId) {

		try {
			if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

				Iterator<DivisionBudgetBean> iterator = divisionBudgetBeans.iterator();
				while (iterator.hasNext()) {
					DivisionBudgetBean divisionBudgetBean = (DivisionBudgetBean) iterator.next();
					divisionBudgetBean.setIsForward(Integer.parseInt(MISConstants.ONE));
					divisionBudgetBean.setLyingWithUsr(userId);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return divisionBudgetBeans;
	}

	private DivisionBudgetBean populateDivisionalBudgetBean(DivisionBudgetForm divisionBudgetForm,
			MISSessionBean misSessionBean, DocumentNumberBean documentNumberBeans, List<String> ddoName)
			throws MISException {

		DivisionBudgetBean divisionBudgetBean = null;
		try {
			divisionBudgetBean = new DivisionBudgetBean();

			if (MisUtility.ifEmpty(divisionBudgetForm.getFinancialYear()))
				divisionBudgetBean.setFinYear(Integer.parseInt(divisionBudgetForm.getFinancialYear()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getDivision()))
				divisionBudgetBean.setDivisionId(Integer.parseInt(divisionBudgetForm.getDivision()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getScheme()))
				divisionBudgetBean.setSchemeId(Long.parseLong(divisionBudgetForm.getScheme()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getComponent()))
				divisionBudgetBean.setComponentId(Long.parseLong(divisionBudgetForm.getComponent()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getSubComponent()))
				divisionBudgetBean.setSubComponentId(Long.parseLong(divisionBudgetForm.getSubComponent()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getSubSubComponent()))
				divisionBudgetBean.setSubSubComponentId(Long.parseLong(divisionBudgetForm.getSubSubComponent()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getSubSubComponentLevel3()))
				divisionBudgetBean
						.setSubComponentLevel3Id(Long.parseLong(divisionBudgetForm.getSubSubComponentLevel3()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getEstimatedCosts()))
				divisionBudgetBean.setEstimatedCost(Long.parseLong(divisionBudgetForm.getEstimatedCosts()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getAlreadyExpended()))
				divisionBudgetBean.setAlreadySpent(Long.parseLong(divisionBudgetForm.getAlreadyExpended()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getActivity()))
				divisionBudgetBean.setActivityId(Long.parseLong(divisionBudgetForm.getActivity()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getNextYearReq()))
				divisionBudgetBean.setReqNxtYear(Long.parseLong(divisionBudgetForm.getNextYearReq()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter1()))
				divisionBudgetBean.setQtr1(Double.parseDouble(divisionBudgetForm.getQuarter1()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter2()))
				divisionBudgetBean.setQtr2(Double.parseDouble(divisionBudgetForm.getQuarter2()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter3()))
				divisionBudgetBean.setQtr3(Double.parseDouble(divisionBudgetForm.getQuarter3()));

			if (MisUtility.ifEmpty(divisionBudgetForm.getQuarter4()))
				divisionBudgetBean.setQtr4(Double.parseDouble(divisionBudgetForm.getQuarter4()));
			if (!MisUtility.ifEmpty(ddoName)) {
				divisionBudgetBean.setBudgetRefNo(divisionBudgetForm.getFinancialYearName() + "/" + ddoName.get(0) + "/"
						+ documentNumberBeans.getLastNumber());
			} else {
				divisionBudgetBean.setBudgetRefNo(
						divisionBudgetForm.getFinancialYearName() + "-" + documentNumberBeans.getLastNumber());
			}
			divisionBudgetBean.setCrtByUsr(misSessionBean.getEnteredBy());
			divisionBudgetBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			divisionBudgetBean.setIsForward(Integer.parseInt(MISConstants.ZERO));
			divisionBudgetBean.setIsApprove(Integer.parseInt(MISConstants.ZERO));

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return divisionBudgetBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean approve(DivisionBudgetForm divisionBudgetForm, MISSessionBean misSessionBean) throws MISException {
		List<DivisionBudgetBean> divisionBudgetBeans = null;
		try {
			if (MisUtility.ifEmpty(divisionBudgetForm)) {
				divisionBudgetBeans = divisionBudgetDao.getDivisionBudgetState(divisionBudgetForm.getFinancialYear(),
						null, misSessionBean);

				if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

						List<DivisionBudgetBean> divisionBudgetBeans2 = populateDivisionBeanState(divisionBudgetBeans);

						if (!MisUtility.ifEmpty(divisionBudgetBeans2)) {
							divisionBudgetDao.updateDivisionBudget(divisionBudgetBeans2);
							
						}

					}
				}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		
		return true;
	}
	
	private List<DivisionBudgetBean> populateDivisionBeanState(List<DivisionBudgetBean> divisionBudgetBeans) {

		try {
			if (!MisUtility.ifEmpty(divisionBudgetBeans)) {

				Iterator<DivisionBudgetBean> iterator = divisionBudgetBeans.iterator();
				while (iterator.hasNext()) {
					DivisionBudgetBean divisionBudgetBean = (DivisionBudgetBean) iterator.next();
					divisionBudgetBean.setIsApprove(Integer.parseInt(MISConstants.ONE));
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return divisionBudgetBeans;
	}

}
