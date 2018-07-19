/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.ContractManagementMasterBean;
import com.prwss.min.finance.bean.FundRequestDto;
import com.prwss.min.finance.bo.FundRequestBo;
import com.prwss.min.finance.dao.FundRequestDao;
import com.prwss.min.finance.dao.GPWSCRegisterDao;
import com.prwss.min.finance.form.FundRequestForm;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.document.dao.DocumentNumberBean;
import com.prwss.mis.common.document.dao.DocumentNumberDAO;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FundRequestAction extends DispatchAction {

	private Logger log = Logger.getLogger(FundRequestAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private FundRequestBo fundRequestBo;
	private GPWSCRegisterDao gPWSCRegisterDao;
	private DocumentNumberDAO documentNumberDao;
	private FundRequestDao fundRequestDao;
	
	

	public FundRequestDao getFundRequestDao() {
		return fundRequestDao;
	}

	public void setFundRequestDao(FundRequestDao fundRequestDao) {
		this.fundRequestDao = fundRequestDao;
	}

	public DocumentNumberDAO getDocumentNumberDao() {
		return documentNumberDao;
	}

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public GPWSCRegisterDao getgPWSCRegisterDao() {
		return gPWSCRegisterDao;
	}

	public void setgPWSCRegisterDao(GPWSCRegisterDao gPWSCRegisterDao) {
		this.gPWSCRegisterDao = gPWSCRegisterDao;
	}

	public FundRequestBo getFundRequestBo() {
		return fundRequestBo;
	}

	public void setFundRequestBo(FundRequestBo fundRequestBo) {
		this.fundRequestBo = fundRequestBo;
	}

	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		FundRequestForm fundRequestForm = (FundRequestForm) form;

		System.out.println("Inside fundRequestForm Save()->");
		log.debug("Inside fundRequestForm Save()->" + fundRequestForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			if (!(fundRequestForm.getAttachment().getFileName().endsWith(".docx")
					||fundRequestForm.getAttachment().getFileName().endsWith(".docm")
					|| fundRequestForm.getAttachment().getFileName().endsWith(".dotm")||fundRequestForm.getAttachment().getFileName().endsWith(".pdf"))) {
				throw new MISException(MISExceptionCodes.MIS003,
						"File Format should be of PDF/Word");
			}
			if (fundRequestForm.getAttachment().getFileSize() > 512000) {
				throw new MISException(MISExceptionCodes.MIS003,
						"File size should not be greater than 500 KB");
			}
			status = fundRequestBo.saveFundRequest(fundRequestForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			
			if (MISExceptionCodes.MIS003.equals(e.getCode())) {	
				log.error(e.getLocalizedMessage(), e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
			}
			else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshFundRequestForm(fundRequestForm);

		return mapping.findForward("display");

	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Unspecified........FundRequestAction");
		System.out.println("Unspecified........FundRequestAction");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		FundRequestForm fundRequestForm = (FundRequestForm) form;
		refreshFundRequestForm(fundRequestForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	private void refreshFundRequestForm(FundRequestForm fundRequestForm) {
		fundRequestForm.setActuallyCollected(null);
		fundRequestForm.setInstallment(null);
		fundRequestForm.setDistrict(null);
		fundRequestForm.setDivision(null);
		fundRequestForm.setBlock(null);
		fundRequestForm.setGpwsc(null);
		fundRequestForm.setRequestNo(null);
		fundRequestForm.setSchemeType(null);
		fundRequestForm.setSchemeName(null);
		fundRequestForm.setApprovalNo(null);
		fundRequestForm.setValue(null);
		fundRequestForm.setDate(null);
		fundRequestForm.setMisCode(null);
		fundRequestForm.setSanctionApprovalNo(null);
		fundRequestForm.setSanctionDate(null);
		fundRequestForm.setSanctionValue(null);
		fundRequestForm.setWorldBankAppdate(null);
		fundRequestForm.setWorldBankApprovalNo(null);
		fundRequestForm.setDue(null);
		fundRequestForm.setActuallyCollected(null);
		fundRequestForm.setNetDSR(null);
		fundRequestForm.setProcDate(null);
		fundRequestForm.setProcNumber(null);
		fundRequestForm.setAwardDate(null);
		fundRequestForm.setAwardNumber(null);
		fundRequestForm.setAwardValue(null);
		fundRequestForm.setValueInstallment(null);
		fundRequestForm.setSchemePackage(null);
		fundRequestForm.setPackageNo(null);
		
		//fundRequestForm.setFundRequestGrid(getFundRequestDatagrid(null));

	}
	
	/*private Datagrid getFundRequestDatagrid(List<String> fundRequest) {
		Datagrid fundRequest1 = null;
		try {
			fundRequest1 = Datagrid.getInstance();
			fundRequest1.setDataClass(FundRequestGridBean.class);
			List<FundRequestGridBean> villageBeans = new ArrayList<FundRequestGridBean>();
			fundRequest1.setData(villageBeans);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}

		return fundRequest1;
	}*/
	
	
	
	public ActionForward fetchGPWSC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<GramPanchayatDetailBean> gramPanchayatDetailBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("districtId"))) {

				gramPanchayatDetailBeans = gPWSCRegisterDao.getGPWSC(null, request.getParameter("districtId"));
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");
				if (!MisUtility.ifEmpty(gramPanchayatDetailBeans)) {
					for (GramPanchayatDetailBean gramPanchayatDetailBean : gramPanchayatDetailBeans) {
						buffer.append("<option value=\"")
								.append(gramPanchayatDetailBean.getGramPanchayatMasterBean().getGramPanchayatId())
								.append("\">");
						buffer.append(gramPanchayatDetailBean.getGramPanchayatMasterBean().getNameofGramPanchayat());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchSchemeDetailsUpgrade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FundRequestDto> fundRequestDtos = null;
		StringBuffer buffer = new StringBuffer();

		try {
			
			System.out.println();
			if (MisUtility.ifEmpty(request.getParameter("schemeId"))) {

				fundRequestDtos = fundRequestDao.getSchemeDetailsUpgrade(request.getParameter("schemeId"));
			
				if (!MisUtility.ifEmpty(fundRequestDtos)) {
					for (FundRequestDto pmsSchemeDetailUpgradeBean : fundRequestDtos) {
						buffer.append(pmsSchemeDetailUpgradeBean.getAddmin_app_no());
						buffer.append("~");
						buffer.append(pmsSchemeDetailUpgradeBean.getAdmin_app_amount());
						buffer.append("~");
						buffer.append(MisUtility.convertDateToString(pmsSchemeDetailUpgradeBean.getAddmin_app_date()));
						buffer.append("~");
						buffer.append(pmsSchemeDetailUpgradeBean.getSchemeNo());
						
						//Technical sanction
						buffer.append("~");
						buffer.append(pmsSchemeDetailUpgradeBean.getTech_app_no());
						buffer.append("~");
						
						buffer.append(pmsSchemeDetailUpgradeBean.getTech_app_amount());
						buffer.append("~");
						buffer.append(MisUtility.convertDateToString(pmsSchemeDetailUpgradeBean.getTech_app_date()));
						buffer.append("~");
						
						//World Bank Approval
						buffer.append(MisUtility.convertDateToString(pmsSchemeDetailUpgradeBean.getWb_app_date()));
						buffer.append("~");
						buffer.append(pmsSchemeDetailUpgradeBean.getWb_app_no());
					
						
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
	public ActionForward fetchContractAwardDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<ContractManagementMasterBean> contractManagementMasterBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			
			System.out.println();
			if (MisUtility.ifEmpty(request.getParameter("schemeId"))) {

				contractManagementMasterBeans = fundRequestDao.getContractAwardDetails(request.getParameter("schemeId"));
			
				if (!MisUtility.ifEmpty(contractManagementMasterBeans)) {
					for (ContractManagementMasterBean contractManagementMasterBean : contractManagementMasterBeans) {
						buffer.append(MisUtility.convertDateToString(contractManagementMasterBean.getAward_date()));
						buffer.append("~");
						buffer.append(contractManagementMasterBean.getAward_no());
						buffer.append("~");
						buffer.append(contractManagementMasterBean.getAward_amount());
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
	
	
	public ActionForward fetchRequestNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		int financialYear = 0;
		StringBuffer buffer = new StringBuffer();
		String requestNo = null;

		try {

			DocumentNumberBean documentNumebrBean = new DocumentNumberBean();
			documentNumebrBean.setDocumentType("FUND-REQUEST");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);

			if (!(MisUtility.ifEmpty(documentNumberBean))) {
				throw new MISException();
			}
			financialYear = MisUtility.getFiscalYear();

			if (MisUtility.ifEmpty(financialYear)) {
				requestNo = financialYear + "/" + documentNumberBean.getLastNumber();

				System.out.println("transactionNo====================>" + requestNo);
				buffer.append(requestNo);
			}

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}
		return null;
	}

	

}
