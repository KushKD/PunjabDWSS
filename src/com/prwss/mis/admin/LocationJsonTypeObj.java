package com.prwss.mis.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prwss.min.quality.struts.LocationMasterForm;

public class LocationJsonTypeObj {
	 int iTotalRecords;

	    int iTotalDisplayRecords;
	    int iDisplayStart;
	    String sEcho;

	    String sColumns;
	    private String level2;
	    List<LocationMasterForm> aaData;
	    
	    HttpServletRequest request;
	    
}
