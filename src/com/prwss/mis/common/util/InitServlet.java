package com.prwss.mis.common.util;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
	
	//private static Set<ComponentBean> componentCodes;
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 9064346969140104230L;
	
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		ComponentDao componentDao = (ComponentDao)WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()).getBean("componentDao");
		componentCodes = componentDao.getDistinctComponentCodes();
		config.getServletContext().setAttribute("componentCodes", componentCodes);
	}
	

	public static Set<ComponentBean> getComponentCodes() {
		
		return componentCodes;
	}
	*/
	

}
