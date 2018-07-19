/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.FundSourceMasterBo;
import com.prwss.min.finance.dao.FundSourceMasterDao;
import com.prwss.min.finance.form.FundSourceMasterForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.admin.ProgramMasterBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 * 
 */
public class FundSourceMasterAction extends DispatchAction {

	private Logger log = Logger.getLogger(FundSourceMasterAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private FundSourceMasterBo<FinanceDto> fundSourceMasterBo;
	private FundSourceMasterDao fundSourceMasterDao;

	public FundSourceMasterDao getFundSourceMasterDao() {
		return fundSourceMasterDao;
	}

	public void setFundSourceMasterDao(FundSourceMasterDao fundSourceMasterDao) {
		this.fundSourceMasterDao = fundSourceMasterDao;
	}

	public FundSourceMasterBo<FinanceDto> getFundSourceMasterBo() {
		return fundSourceMasterBo;
	}

	public void setFundSourceMasterBo(
			FundSourceMasterBo<FinanceDto> fundSourceMasterBo) {
		this.fundSourceMasterBo = fundSourceMasterBo;
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

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		FundSourceMasterForm fundSourceMasterForm = (FundSourceMasterForm) form;

		System.out.println("Inside fundSourceMaster Save()->");
		log.debug("Inside fundSourceMaster Save()->"
				+ fundSourceMasterForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = fundSourceMasterBo.saveFundSource(fundSourceMasterForm,
					misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Saved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry",
					e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry",
					"Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshSourceMaster(fundSourceMasterForm);

		return mapping.findForward("display");

	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		log.debug("Unspecified........FundSourceMasterAction");
		System.out.println("Unspecified........FundSourceMasterAction");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	private void refreshSourceMaster(FundSourceMasterForm fundSourceMasterForm) {

		fundSourceMasterForm.setProgram(null);
		fundSourceMasterForm.setCenterShare(null);
		fundSourceMasterForm.setFundSrcMaster(null);
		fundSourceMasterForm.setScheme(null);
		fundSourceMasterForm.setStateShare(null);

	}

	public ActionForward fetchProgram(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException, IOException {

		List<ProgramMasterBean> programMasterBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			programMasterBeans = fundSourceMasterDao.getProgramBean();

			if (!MisUtility.ifEmpty(programMasterBeans)) {
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(programMasterBeans)) {
					for (ProgramMasterBean programMasterBean : programMasterBeans) {
						buffer.append("<option value=\"")
								.append(programMasterBean.getProgramMstId())
								.append("\">");
						buffer.append(programMasterBean.getProgramDetailBean()
								.getProgName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return null;
	}

	public ActionForward fetchSchemeByProgram(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<Integer> schemeId = null;
		StringBuffer buffer = new StringBuffer();
		List<PMSSchemeDetailsBean> pmsSchemeDetailsBeans = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			buffer.append("<option value='' selected>");
			buffer.append("Please Select");
			buffer.append("</option>");
			if (MisUtility.ifEmpty(request.getParameter("programId"))) {
				schemeId = fundSourceMasterDao.getSchemeByProgram(request
						.getParameter("programId"));

				if (!MisUtility.ifEmpty(schemeId)) {
					pmsSchemeDetailsBeans = fundSourceMasterDao
							.getSchemeName(schemeId);
					if (!MisUtility.ifEmpty(pmsSchemeDetailsBeans)) {
						for (PMSSchemeDetailsBean pmsSchemeDetailsBean : pmsSchemeDetailsBeans) {
							buffer.append("<option value=\"")
									.append(pmsSchemeDetailsBean
											.getSchemeDtlPk()).append("\">");
							buffer.append(pmsSchemeDetailsBean.getSchemeName());
							buffer.append("</option>");
						}
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString())
						&& buffer.length() > 1) {
					out.print(buffer.toString());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return null;
	}

	public ActionForward populateFundSourceMaster(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		try {
			List<FinanceDto> financeHeadBeans = null;
			int clickedColumn = Integer.parseInt(request
					.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request
					.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request
					.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			financeHeadBeans = new ArrayList<FinanceDto>();

			financeHeadBeans = fundSourceMasterDao.populateFundSourceMaster(
					searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeHeadBeans)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				locationJson.setiTotalDisplayRecords(financeHeadBeans.size());
				locationJson.setiTotalRecords(financeHeadBeans.size());
			}
			List<FinanceDto> financeHeadBeans2 = null;
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				financeHeadBeans2 = fundSourceMasterBo
						.getListBasedOnPageNumber(financeHeadBeans,
								pageDisplayLength, pageNumber, iDisplayStart);
				locationJson.setAaData(financeHeadBeans2);
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
}
