/**
 * 
 */
package com.prwss.min.quality.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.SampleDto;
import com.prwss.min.dao.FreezeUnFreezeDao;
import com.prwss.min.quality.FreezeUnFreezeBo;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author BH390738
 *
 */
public class FreezeUnFreezeAction extends DispatchAction{

	private Logger log = Logger.getLogger(FreezeUnFreezeAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private FreezeUnFreezeDao freezeUnFreezeDao;
	private FreezeUnFreezeBo<SampleDto> freezeUnFreezeBo;
	private  LocationDao locationDao; 
	
	
	
	
	public LocationDao getLocationDao() {
		return locationDao;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	public FreezeUnFreezeDao getFreezeUnFreezeDao() {
		return freezeUnFreezeDao;
	}
	public void setFreezeUnFreezeDao(FreezeUnFreezeDao freezeUnFreezeDao) {
		this.freezeUnFreezeDao = freezeUnFreezeDao;
	}
	public FreezeUnFreezeBo<SampleDto> getFreezeUnFreezeBo() {
		return freezeUnFreezeBo;
	}
	public void setFreezeUnFreezeBo(FreezeUnFreezeBo<SampleDto> freezeUnFreezeBo) {
		this.freezeUnFreezeBo = freezeUnFreezeBo;
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
	
	
	public ActionForward populateSampleDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("-------------------populateSampleDetails---------------");
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

				int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
				String clickedColumnDir = request.getParameter("sSortDir_0");

				Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				Integer pageNumber = 0;
				Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
				if (null != request.getParameter("iDisplayStart"))
					pageNumber = (iDisplayStart / pageDisplayLength) + 1;
				String searchParameter = request.getParameter("sSearch");
				
				List<SampleDto> sampleDto =freezeUnFreezeDao.getSampleDetails(misAuditBean,request.getParameter("lab"),request.getParameter("fromDate"),request.getParameter("toDate")
						,searchParameter, clickedColumn, clickedColumnDir);
				LocationJsonObject<SampleDto> locationJson = new LocationJsonObject<SampleDto>();

				if (MisUtility.ifEmpty(sampleDto)) {
					locationJson.setAaData(new ArrayList<SampleDto>());
				}
				if (!MisUtility.ifEmpty(sampleDto)) {
					locationJson.setiTotalDisplayRecords(sampleDto.size());
					locationJson.setiTotalRecords(sampleDto.size());
					// locationJson.setAaData(beneficiaryDto);
				}
				List<SampleDto> sampleDtos = null;
				if (!MisUtility.ifEmpty(sampleDto)) {
					sampleDtos = freezeUnFreezeBo.getListBasedOnPageNumber(sampleDto, pageDisplayLength,
							pageNumber, iDisplayStart);
					locationJson.setAaData(sampleDtos);
				}
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(locationJson);
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
		} catch (Exception e) {
			log.debug(e.getMessage());

		}
		return null;
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
		System.out.println("Unspecified........freeze ");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}

	
	
}
