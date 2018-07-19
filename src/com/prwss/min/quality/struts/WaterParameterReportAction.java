package com.prwss.min.quality.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.dao.ParameterMasterDao;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

public class WaterParameterReportAction extends DispatchAction {

	private Logger log = Logger.getLogger(SampleDistributionAction.class);
	

	@SuppressWarnings("rawtypes")
	private Map parameters = null;

	@SuppressWarnings("rawtypes")
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}

	private ParameterMasterDao parameterMasterDao;
	private MISSessionBean misAuditBean;

	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;

	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public MISJasperUtil getMisJasperUtil() {
		return misJasperUtil;
	}

	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}

	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public ParameterMasterDao getParameterMasterDao() {
		return parameterMasterDao;
	}

	public void setParameterMasterDao(ParameterMasterDao parameterMasterDao) {
		this.parameterMasterDao = parameterMasterDao;
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

		System.out.println("Unspecified........Sample Disrtibuion");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		// request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	public ActionForward fetchParameter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("--------------inside fetch All Parameter--------------");
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Parameter");
			buffer.append("</option>");
			List<ParameterMasterBean> parameterMasterData = parameterMasterDao.getAllParameters();

			for (ParameterMasterBean parameterMasterBean : parameterMasterData) {
				buffer.append("<option value=\"").append(parameterMasterBean.getParameterId()).append("\">");
				buffer.append(parameterMasterBean.getParameterName());
				buffer.append("</option>");
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
	
	
	//fileEXCEL
	@SuppressWarnings("unchecked")
	public void fileEXCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------IN file PDF----------------");
		String yes_No = null;
		WaterParameterReportForm waterParameterform = (WaterParameterReportForm) form;
		List<Parameters> parametersJson=null;
		try{
			yes_No = request.getParameter("all");
			
			if (yes_No.equalsIgnoreCase("no")){
				
				System.out.println("inside Save Data--->");
	
				JSONParser parser = new JSONParser();
				JSONArray json = (JSONArray) parser.parse(request.getParameter("tabledata"));
				System.out.println(json);
	
				parametersJson = new ArrayList<Parameters>();
	
				Parameters formElement = null;
				JSONObject object = null;
				for (int i = 0; i < json.size(); i++) {
					formElement = new Parameters();
					object = (JSONObject) json.get(i);
					
					System.out.println("object-------------fail--------"+object.get("Fail").toString());
					formElement.setParameterID(object.get("Perameter Id").toString());
					formElement.setAcceptableLimit(object.get("Acceptable Limit").toString());
					formElement.setPermissibleLimit(object.get("Permissible Limit").toString());
					formElement.setBdl(Boolean.parseBoolean(object.get("BDL").toString()));
					formElement.setNd(Boolean.parseBoolean(object.get("ND").toString()));
					formElement.setFail(Boolean.parseBoolean(object.get("Fail").toString()));
					formElement.setPass(Boolean.parseBoolean(object.get("Pass").toString()));
	
					System.out.println("Form Element ==1111111111111111111111==" + formElement.toString());
					parametersJson.add(formElement);
	
				}
	
				waterParameterform.setParametersList(parametersJson);
	
				System.out.println("Populated Form IS:- " + waterParameterform.toString());
	
				
				
				
			}else{
				
				waterParameterform.setParametersList(null);
				System.out.println("Populated Form IS:- " + waterParameterform.toString());
				
			}
	
			String jasperFile = waterParameterform.getFileName();
			String fileTitle = waterParameterform.getFileTitle();
			setWhere(waterParameterform, request);
			JRSwapFileVirtualizer virtualizer = null;
			virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
			System.out.println("------1-------" + virtualizer);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			System.out.println(jasperFile);
			System.out.println(fileTitle);
			misJasperUtil.exportToExcel(jasperFile, fileTitle, parameters, request, response);
		}catch(Exception e){
			log.debug(e.getMessage());
		}
	}
	
	

	@SuppressWarnings("unchecked")
	public void filePDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------IN file PDF----------------");
		String yes_No = null;
		WaterParameterReportForm waterParameterform = (WaterParameterReportForm) form;
		List<Parameters> parametersJson=null;
		
		try{
		yes_No = request.getParameter("all");
		
		if (yes_No.equalsIgnoreCase("no")){
			
			System.out.println("inside Save Data--->");
			System.out.println("Table Data " + request.getParameter("tabledata"));

			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(request.getParameter("tabledata"));
			System.out.println(json);

			parametersJson = new ArrayList<Parameters>();

			Parameters formElement = null;
			JSONObject object = null;
			for (int i = 0; i < json.size(); i++) {
				formElement = new Parameters();
				object = (JSONObject) json.get(i);
				formElement.setParameterID(object.get("Perameter Id").toString());
				formElement.setAcceptableLimit(object.get("Acceptable Limit").toString());
				formElement.setPermissibleLimit(object.get("Permissible Limit").toString());
				formElement.setBdl(Boolean.parseBoolean(object.get("BDL").toString()));
				formElement.setNd(Boolean.parseBoolean(object.get("ND").toString()));
				formElement.setFail(Boolean.parseBoolean(object.get("Fail").toString()));
				formElement.setPass(Boolean.parseBoolean(object.get("Pass").toString()));

				System.out.println("Form Element ====" + formElement.toString());
				parametersJson.add(formElement);

			}

			waterParameterform.setParametersList(parametersJson);

			System.out.println("Populated Form IS:- " + waterParameterform.toString());

			
			
			
		}else{
			
			waterParameterform.setParametersList(null);
			System.out.println("Populated Form IS:- " + waterParameterform.toString());
			
		}
		
		String jasperFile = waterParameterform.getFileName();
		String fileTitle = waterParameterform.getFileTitle();
		setWhere(waterParameterform, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		System.out.println("------1-------" + virtualizer);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		System.out.println(jasperFile);
		System.out.println(fileTitle);
		misJasperUtil.exportToPDF(jasperFile, fileTitle, parameters, request, response);
		
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setWhere(WaterParameterReportForm waterParameterform, HttpServletRequest request) {
		// TODO Auto-generated method stub
		parameters = new HashMap(); 
		
		StringBuffer sb = null;
		String village = null;
		String scheme = null;
		
		StringBuffer innerWhere = null;
		
		
		try{
			sb = new StringBuffer();
		if(!MisUtility.ifEmpty(waterParameterform.getZone()) &&
			!MisUtility.ifEmpty(waterParameterform.getCircle())&&
			!MisUtility.ifEmpty(waterParameterform.getDistrict()) &&
			!MisUtility.ifEmpty(waterParameterform.getDivision()) &&
			!MisUtility.ifEmpty(waterParameterform.getSubdivision()) &&
			!MisUtility.ifEmpty(waterParameterform.getBlock())&&
			!MisUtility.ifEmpty(waterParameterform.getConstituency())){
			
			sb.append("  1=1 ");
		}else{
			if(MisUtility.ifEmpty(waterParameterform.getZone())){
				sb.append(" zone_Id=" + waterParameterform.getZone());
			
			
			if(MisUtility.ifEmpty(waterParameterform.getCircle())){
				sb.append(" AND circle_Id=" + waterParameterform.getCircle());
			}else{
				sb.append(" AND 1= 1");
			}
			
			
			if(MisUtility.ifEmpty(waterParameterform.getDivision())){
				sb.append(" AND divisional_id=" + waterParameterform.getDivision());
			}else{
				sb.append(" AND 1= 1");
			}
			
			if(MisUtility.ifEmpty(waterParameterform.getSubdivision())){
				sb.append(" AND subdiv_id=" + waterParameterform.getSubdivision());
			}else{
				sb.append(" AND 1= 1");
			}
			
			}
			if(MisUtility.ifEmpty(waterParameterform.getDistrict())){
				sb.append("district_Id=" + waterParameterform.getDistrict());
			
			
		
			if(MisUtility.ifEmpty(waterParameterform.getBlock())){
				sb.append(" AND block_Id=" + waterParameterform.getBlock());
			}else{
				sb.append(" AND 1= 1");
			}
			
			
			}
			if(MisUtility.ifEmpty(waterParameterform.getConstituency())){
				sb.append("constituency_id=" + waterParameterform.getConstituency());
			}
		}
		
		
		
		
		if(MisUtility.ifEmpty(waterParameterform.getVillageId())){
			village = "village_id= "+waterParameterform.getVillageId();
		}else{
			village = "  1= 1 ";
		}
		
		if(MisUtility.ifEmpty(waterParameterform.getSchemeName())){
			scheme = "scheme_id= "+waterParameterform.getSchemeName();
		}else{
			scheme = " 1=1 ";
		}
		
	
		
		innerWhere = new StringBuffer();
		
		if(waterParameterform.getParametersList()!=null){
			
			System.out.println(waterParameterform.getParametersList().size());
			
			//Check the Size of List
			if(waterParameterform.getParametersList().size()==1){
				
				for(int i=0;i<=waterParameterform.getParametersList().size();i++){
					
					if(waterParameterform.getParametersList().get(i).getBdl()){
						
						//innerWhere.append(" param_id = ");
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
						//innerWhere.append(" AND ");
						//innerWhere.append(" actual_value = -3 ");
						innerWhere.append(" =-3 ");
						break;
					}
					
					if(waterParameterform.getParametersList().get(i).getNd()){
						//innerWhere.append(" param_id =  ");
						//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
						//innerWhere.append(" AND ");
						//innerWhere.append(" actual_value = -2 ");
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
						innerWhere.append("   =-2 ");
						break;
					}
					
					if(!waterParameterform.getParametersList().get(i).getPass() && 
							   !waterParameterform.getParametersList().get(i).getFail() &&
							   !waterParameterform.getParametersList().get(i).getNd() && 
							   !waterParameterform.getParametersList().get(i).getBdl()){
								
								//innerWhere.append(" param_id =  ");
								//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								//innerWhere.append(" AND ");
								//innerWhere.append(" actual_value >= ");
						innerWhere.append("  >= ");
								innerWhere.append(waterParameterform.getParametersList().get(i).getAcceptableLimit());
								innerWhere.append(" AND ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								//innerWhere.append(" actual_value <= ");
								innerWhere.append("  <= ");
								innerWhere.append(waterParameterform.getParametersList().get(i).getPermissibleLimit());
								
								break;
							}
					
					if(waterParameterform.getParametersList().get(i).getFail()){
						//innerWhere.append(" param_id =  ");
						//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
						//innerWhere.append(" AND ");
						//innerWhere.append(" actual_value >=  ");
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
						innerWhere.append("   >");
						if(parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())).equalsIgnoreCase("No relaxation")){
							innerWhere.append(" "+parameterMasterDao.getParameterAcceptableLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
						}else{
							innerWhere.append(" "+parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
						}
						innerWhere.append(" ");
						break;
					}
					
					if(waterParameterform.getParametersList().get(i).getPass()){
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
						//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
						//innerWhere.append(" AND ");
						//innerWhere.append(" actual_value <= ");
						innerWhere.append("   <= ");
						if(parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())).equalsIgnoreCase("No relaxation")){
							innerWhere.append(" "+parameterMasterDao.getParameterAcceptableLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
						}else{
							innerWhere.append(" "+parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
						}
						innerWhere.append(" AND ");
						innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
						innerWhere.append("  in (-1,-2,-3) ");
						
						break;
					}
					
					
				}
				
				
			}
			//Go For Foe Loop
			if(waterParameterform.getParametersList().size()>1){
				
					for(int i=0;i<waterParameterform.getParametersList().size();i++){
					 
							if(i==0){
								innerWhere.append(" ");
							}else{
								innerWhere.append(" AND ");
							}
	
							if(waterParameterform.getParametersList().get(i).getBdl()){
								
								//innerWhere.append(" param_id = ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								//innerWhere.append(" AND ");
								//innerWhere.append(" actual_value = -3 ");
								innerWhere.append(" =-3 ");
								
							}
							
							if(waterParameterform.getParametersList().get(i).getNd()){
								//innerWhere.append(" param_id =  ");
								//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
								//innerWhere.append(" AND ");
								//innerWhere.append(" actual_value = -2 ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								innerWhere.append("   =-2 ");
								
							}
							
							if(!waterParameterform.getParametersList().get(i).getPass() && 
									   !waterParameterform.getParametersList().get(i).getFail() &&
									   !waterParameterform.getParametersList().get(i).getNd() && 
									   !waterParameterform.getParametersList().get(i).getBdl()){
										
										//innerWhere.append(" param_id =  ");
										//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
										//innerWhere.append(" AND ");
										//innerWhere.append(" actual_value >= ");
								innerWhere.append("  >= ");
										innerWhere.append(waterParameterform.getParametersList().get(i).getAcceptableLimit());
										innerWhere.append(" AND ");
										innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
										//innerWhere.append(" actual_value <= ");
										innerWhere.append("  <= ");
										innerWhere.append(waterParameterform.getParametersList().get(i).getPermissibleLimit());
										
										
									}
							
							if(waterParameterform.getParametersList().get(i).getFail()){
								//innerWhere.append(" param_id =  ");
								//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
								//innerWhere.append(" AND ");
								//innerWhere.append(" actual_value >=  ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								innerWhere.append("   >= ");
								if(parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())).equalsIgnoreCase("No relaxation")){
									innerWhere.append(" "+parameterMasterDao.getParameterAcceptableLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
								}else{
									innerWhere.append(" "+parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
								}								innerWhere.append(" AND ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								innerWhere.append("  not in (-1,-2,-3) ");
								
							}
							
							if(waterParameterform.getParametersList().get(i).getPass()){
								//innerWhere.append(" param_id =  ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								//innerWhere.append(waterParameterform.getParametersList().get(i).getParameterID());
								//innerWhere.append(" AND ");
								//innerWhere.append(" actual_value <= ");
								innerWhere.append("   <= ");
								if(parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())).equalsIgnoreCase("No relaxation")){
									innerWhere.append(" "+parameterMasterDao.getParameterAcceptableLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
								}else{
									innerWhere.append(" "+parameterMasterDao.getParameterPermissibleLimit(Integer.parseInt(waterParameterform.getParametersList().get(i).getParameterID())));
								}								innerWhere.append(" AND ");
								innerWhere.append(" "+parameterMasterDao.getParameterIdName(waterParameterform.getParametersList().get(i).getParameterID()));
								innerWhere.append("  in (-1,-2,-3) ");
								
								
							}
				
				
				
				
			}
			
		}
		  
		
		
		
	}else{
		innerWhere.append("");
	}

		System.out.println(sb.toString());
		parameters.put("where", sb.toString().trim());
		parameters.put("village", village);
		System.out.println(village);
		parameters.put("scheme", scheme);
		System.out.println(scheme);
		
		if(innerWhere.toString().length()==0){
			System.out.println("Not Setting the Actual Parameter======" + innerWhere.toString().trim());
		}else{
			parameters.put("param", innerWhere.toString());
			System.out.println("======" + innerWhere.toString().trim());
		}
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		
		
		//parameters.put("param", param.toString());
		
	
}
}
