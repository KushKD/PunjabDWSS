/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.GPWSCRegisterBo;
import com.prwss.min.finance.dao.GPWSCRegisterDao;
import com.prwss.min.finance.form.GPWSCRegisterForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.document.dao.DocumentNumberBean;
import com.prwss.mis.common.document.dao.DocumentNumberDAO;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class GPWSCRegisterAction extends DispatchAction {

	private Logger log = Logger.getLogger(GPWSCRegisterAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private GPWSCRegisterDao gPWSCRegisterDao;
	private GPWSCRegisterBo<FinanceDto> gPWSCRegisterBo;
	private DocumentNumberDAO documentNumberDao;

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

	
	public GPWSCRegisterBo<FinanceDto> getgPWSCRegisterBo() {
		return gPWSCRegisterBo;
	}

	public void setgPWSCRegisterBo(GPWSCRegisterBo<FinanceDto> gPWSCRegisterBo) {
		this.gPWSCRegisterBo = gPWSCRegisterBo;
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

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		log.debug("Unspecified........GPWSCRegisterAction");
		System.out.println("Unspecified........GPWSCRegisterAction");

		return mapping.findForward("display");
	}

	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		GPWSCRegisterForm gpwscRegisterForm = (GPWSCRegisterForm) form;

		System.out.println("Inside fundSourceMaster Save()->");
		log.debug("Inside fundSourceMaster Save()->" + gpwscRegisterForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = gPWSCRegisterBo.save(gpwscRegisterForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved for the transaction number"+"\t"+gpwscRegisterForm.getTransactionNo());
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
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refershGPWSCRegisterForm(gpwscRegisterForm);

		return mapping.findForward("display");

	}
	
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}
	
	private void refershGPWSCRegisterForm(GPWSCRegisterForm gpwscRegisterForm ){
		gpwscRegisterForm.setAmount(null);
		gpwscRegisterForm.setBank(null);
		gpwscRegisterForm.setBillNo(null);
		gpwscRegisterForm.setDivision(null);
		gpwscRegisterForm.setgPWSC(null);
		gpwscRegisterForm.setPayeeName(null);
		gpwscRegisterForm.setPaymentRemark(null);
		gpwscRegisterForm.setPaymentRemark(null);
		gpwscRegisterForm.setPaymentType(null);
		gpwscRegisterForm.setReceiptAmount(null);
		gpwscRegisterForm.setReceiptType(null);
		gpwscRegisterForm.setPaymentRemark(null);
	}

	public ActionForward fetchGPWSC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<GramPanchayatDetailBean> gramPanchayatDetailBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("villageId"))) {

				gramPanchayatDetailBeans = gPWSCRegisterDao.getGPWSC(request.getParameter("villageId"),null);
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

	public ActionForward fetchTransactionNumber(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {
		
		int financialYear = 0;
		StringBuffer buffer = new StringBuffer();
		String transactionNo = null;
		
		try {
			System.out.println("--------------1----------------------------"+request.getParameter("transactionType"));
			if (MisUtility.ifEmpty(request.getParameter("transactionType"))) {
				
				DocumentNumberBean documentNumebrBean = new DocumentNumberBean();
				documentNumebrBean.setDocumentType("GPWSC-REGISTER");
				DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
				
				if (!(MisUtility.ifEmpty(documentNumberBean))) {
					throw new MISException();
				}
				financialYear = MisUtility.getFiscalYear();

				if (MisUtility.ifEmpty(financialYear)) {
					transactionNo = request.getParameter("transactionType") + "/" + financialYear + "/"
							+ documentNumberBean.getLastNumber();
					
					System.out.println("transactionNo====================>"+transactionNo);
					buffer.append(transactionNo);
				}
				
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
					out.print(buffer.toString());
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public ActionForward populateGPWSCRegister(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<FinanceDto> gpwscRegister = null;
		List<FinanceDto> gpwscRegister1 = null;
		try {
			
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			gpwscRegister = new ArrayList<FinanceDto>();
			
			gpwscRegister = gPWSCRegisterDao.getGPWSCRegiter(searchParameter, clickedColumn, clickedColumnDir);
			
			gpwscRegister1=getFinanceDto(gpwscRegister);
			
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(gpwscRegister1)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(gpwscRegister1)) {
				locationJson.setiTotalDisplayRecords(gpwscRegister1.size());
				locationJson.setiTotalRecords(gpwscRegister1.size());
			}
			List<FinanceDto> gpwscRegister2 = null;
			if (!MisUtility.ifEmpty(gpwscRegister1)) {
				gpwscRegister2 = gPWSCRegisterBo.getListBasedOnPageNumber(gpwscRegister1, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(gpwscRegister2);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<FinanceDto> getFinanceDto(List<FinanceDto> gpwscRegister){
		
		try{
			Iterator<FinanceDto> it=gpwscRegister.iterator();
			
			while(it.hasNext()){
				FinanceDto financeDto=(FinanceDto)it.next();
				financeDto.setTransactionDate1(MisUtility.convertDateString(financeDto.getTransactionDate()));
			}
			
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return gpwscRegister;
	}

}
