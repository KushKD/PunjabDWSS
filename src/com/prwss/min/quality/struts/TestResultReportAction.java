/**
 * 
 */
package com.prwss.min.quality.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.dao.TestResultReportDao;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.min.quality.TestResultReportForm;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

/**
 * @author BH390738
 *
 */
public class TestResultReportAction extends DispatchAction {

	private Logger log = Logger.getLogger(TestResultReportAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private LocationDao locationDao;
	private TestResultReportDao testResultReportDao;
	private MISJasperUtil misJasperUtil;
	private MISJdcDaoImpl misJdcDaoImpl;
	
	
	
	
	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	@SuppressWarnings("rawtypes")
	private Map parameters;

	public MISJasperUtil getMisJasperUtil() {
		return misJasperUtil;
	}

	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(@SuppressWarnings("rawtypes") Map parameters) {
		this.parameters = parameters;
	}

	public TestResultReportDao getTestResultReportDao() {
		return testResultReportDao;
	}

	public void setTestResultReportDao(TestResultReportDao testResultReportDao) {
		this.testResultReportDao = testResultReportDao;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
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

		Set<LabelValueBean> labName = null;

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);

		long empId = misAuditBean.getEnteredBy();
		labName = new TreeSet<LabelValueBean>();
		List<Long> labIdLst = new ArrayList<Long>();
		StringBuffer buffer = new StringBuffer();

		buffer.append("<option value='' selected>");
		buffer.append("Select Lab");
		buffer.append("</option>");
		List<LabEmployee> labEmp = locationDao.getLabDetails(Integer.parseInt(String.valueOf(empId)));

		if (!MisUtility.ifEmpty(labEmp)) {
			for (LabEmployee employee : labEmp) {
				labIdLst.add(Long.parseLong(String.valueOf(employee.getLabId())));
			}
			List<LabMasterBean> labMasterBean = locationDao.getLabName(labIdLst);
			for (LabMasterBean beans : labMasterBean) {
				labName.add(new LabelValueBean(beans.getLabName(), String.valueOf(beans.getLabId())));
			}

		}
		request.getSession().setAttribute("labNames", labName);
		System.out.println("Unspecified........testresultacion");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}
	

	private void refreshTestResultReportLocationForm(TestResultReportForm testResultReportForm) {

		testResultReportForm.setLab(null);
	}

	public ActionForward fetchSample(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSample");
		List<String> sampleDtos = null;
		StringBuffer buffer = new StringBuffer();

		try {
			System.out.println("----request.getParameter---11----------" + request.getParameter("labId"));
			if (MisUtility.ifEmpty(request.getParameter("labId"))) {
				System.out.println("----request.getParameter-------------" + request.getParameter("labId"));
				sampleDtos = testResultReportDao.fetchSample(request.getParameter("labId"));
				System.out.println(sampleDtos.toString());
				for (String sampleDto : sampleDtos) {
					buffer.append("<option value=\"").append(sampleDto).append("\">");
					buffer.append(sampleDto);
					buffer.append("</option>");
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

	@SuppressWarnings("unchecked")
	public void filePDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------IN file PDF----------------");
		TestResultReportForm testResultReportForm = (TestResultReportForm) form;
		String jasperFile = testResultReportForm.getJasperFile();
		String fileTitle = testResultReportForm.getFileTitle();
		
		
		System.out.println("jasperFile-------->"+jasperFile);
		System.out.println("fileTitle-------->"+fileTitle);
		
		
		
		setWhere(testResultReportForm, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile, fileTitle, parameters, request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setWhere(TestResultReportForm testResultReportForm, HttpServletRequest request) {
		StringBuffer sampleNumber=new StringBuffer();
		parameters = new HashMap();
		String sample=" 1=1";
		String select_fields[] = testResultReportForm.getSelect_fields();
			for(int i=0;i<select_fields.length;i++){
				if(i>0){
					sampleNumber.append(",");
				}
				sampleNumber.append('\'').append(select_fields[i]).append('\'');
			}
			sample=" sample_number in("+sampleNumber.toString()+")";
			
			System.out.println("sample number"+sampleNumber.toString());
			parameters.put("sample", sample);
			parameters.put("sample_top", sampleNumber.toString());
			

	}
}
