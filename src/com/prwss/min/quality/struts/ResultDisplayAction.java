package com.prwss.min.quality.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.TestResultCommentBean;
import com.prwss.min.dao.DisplayTestResultDao;
import com.prwss.min.dao.ResultEntryDao;
import com.prwss.min.quality.DisplayEntryBo;
import com.prwss.min.quality.ResultDisplayForm;
import com.prwss.min.quality.ResultEntryDto;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class ResultDisplayAction extends DispatchAction {

	private Logger log = Logger.getLogger(ResultDisplayAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;

	private DisplayTestResultDao displayTestResultDao;
	private ResultEntryDao resultEntryDao;
	private DisplayEntryBo displayEntryBo;

	/**
	 * @return the displayEntryBo
	 */
	public DisplayEntryBo getDisplayEntryBo() {
		return displayEntryBo;
	}

	/**
	 * @param displayEntryBo
	 *            the displayEntryBo to set
	 */
	public void setDisplayEntryBo(DisplayEntryBo displayEntryBo) {
		this.displayEntryBo = displayEntryBo;
	}

	/**
	 * @return the resultEntryDao
	 */
	public ResultEntryDao getResultEntryDao() {
		return resultEntryDao;
	}

	/**
	 * @param resultEntryDao
	 *            the resultEntryDao to set
	 */
	public void setResultEntryDao(ResultEntryDao resultEntryDao) {
		this.resultEntryDao = resultEntryDao;
	}

	/**
	 * @return the displayTestResultDao
	 */
	public DisplayTestResultDao getDisplayTestResultDao() {
		return displayTestResultDao;
	}

	/**
	 * @param displayTestResultDao
	 *            the displayTestResultDao to set
	 */
	public void setDisplayTestResultDao(DisplayTestResultDao displayTestResultDao) {
		this.displayTestResultDao = displayTestResultDao;
	}

	/**
	 * @return the misAuditBean
	 */
	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	/**
	 * @param misAuditBean
	 *            the misAuditBean to set
	 */
	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	/**
	 * @return the misSessionBean
	 */
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	/**
	 * @param misSessionBean
	 *            the misSessionBean to set
	 */
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
		request.setAttribute("level2", "true");
		this.setAttrib(request);

		System.out.println("Unspecified........Sample Disrtibuion");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	public ActionForward displayTestResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("inside displayTestResult--------------" + request.getParameter("testResultId"));
		List<ResultEntryDto> resultEntryBean = null;

		try {
			ResultDisplayForm resultDisplayForm=(ResultDisplayForm)form;
			setAttrib(request);
			if (MisUtility.ifEmpty(request.getParameter("testResultId"))) {

				resultEntryBean = displayTestResultDao
						.getResultEntry(Integer.parseInt(request.getParameter("testResultId")));

				if (!MisUtility.ifEmpty(resultEntryBean)) {
					request.setAttribute("sampleNumber", resultEntryBean.get(0).getSamplenumber());
					request.setAttribute("samplePartNumber", resultEntryBean.get(0).getSamplepartno());
					request.setAttribute("testDoneBy", resultEntryBean.get(0).getTestdoneby());
					request.setAttribute("labName", resultEntryBean.get(0).getLabname());
					request.setAttribute("teststatus", resultEntryBean.get(0).getTeststatus());
					request.setAttribute("requestlevel", resultEntryBean.get(0).getRequestlevel());
					
					resultDisplayForm.setResultEntryDtos(resultEntryBean);
					request.setAttribute("resultEntryDtos", resultDisplayForm);
				}

				// business logic to get all comments based on testResultId

				List<TestResultCommentBean> testResultCommentBeans = getComments(request.getParameter("testResultId"));
				if (!MisUtility.ifEmpty(testResultCommentBeans)) {
					request.setAttribute("testResultCommentBean", testResultCommentBeans);
				}
				
				//end
				
			}
			// }

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return mapping.findForward("display");
	}

	private List<TestResultCommentBean> getComments(String testResultId) throws MISException {

		List<TestResultCommentBean> testResultCommentBean = null;
		try {
			testResultCommentBean = displayTestResultDao.fetchComments(Integer.parseInt(testResultId));
			/*
			 * request.getServletContext().setAttribute("testResultCommentBean",
			 * testResultCommentBean); PrintWriter out =
			 * MisUtility.getPrintWriter(response);
			 * out.print(testResultCommentBean);
			 */

		} catch (Exception e) {

		}
		return testResultCommentBean;

	}

	public ActionForward updateTestEntryResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("------------------inside updateTestEntryResult---------------------------------");
		boolean flag = false;
		List<String> status = null;
		try {
			PrintWriter out = response.getWriter();

			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			String empId = String.valueOf(misSessionBean.getEnteredBy());

			String testresultid = request.getParameter("testResultId");
			String requestlevel = request.getParameter("requestLevel");
			String requestFlow = request.getParameter("flow");
			String comments = request.getParameter("comments");

			System.out.println("===========INSIDE Action" + requestlevel);

			if (MisUtility.ifEmpty(testresultid) && MisUtility.ifEmpty(requestlevel)) {
				flag = displayEntryBo.updateResultEntry(testresultid, requestlevel, requestFlow, empId, comments);
			}
			if (flag) {

				status = new ArrayList<String>();

				status.add(MISConstants.MASTER_STATUS_APPROVED);
				status.add(MISConstants.MASTER_STATUS_VERIFIED);
				int resultEntryBean = 0;
				List<ResultEntryBean> resultEntryBeans = displayTestResultDao
						.getEmployeeId(Integer.parseInt(testresultid));
				for (ResultEntryBean bean : resultEntryBeans) {
					resultEntryBean = bean.getLyingWithUser();
				}
				List<EmployeeBean> empBean = displayTestResultDao
						.fetchEmployeeName(Long.parseLong(String.valueOf(resultEntryBean)), status);
				for (EmployeeBean bean : empBean) {
					out.print("Successfully " + requestFlow + " " + "to" + "\t" + bean.getEmployeeName());

				}
			}
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return null;
	}

}
