package com.prwss.min.quality.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LocationJsonObject<T> {

	 int iTotalRecords;

	    int iTotalDisplayRecords=0;
	    int iDisplayStart=0;
	    String sEcho;
	    int draw=0;

	    String sColumns;
	    private String level2;
	    List<T> aaData;
	    
	    HttpServletRequest request;
	    
	    
	    
	    /**
		 * @return the draw
		 */
		public int getDraw() {
			return draw;
		}

		/**
		 * @param draw the draw to set
		 */
		public void setDraw(int draw) {
			this.draw = draw;
		}

		/**
		 * @return the request
		 */
		public HttpServletRequest getRequest() {
			return request;
		}

		/**
		 * @param request the request to set
		 */
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		/**
		 * @return the level2
		 */
		public String getLevel2() {
			return level2;
		}

		/**
		 * @param level2 the level2 to set
		 */
		public void setLevel2(String level2) {
			this.level2 = level2;
		}

		/**
		 * @return the iDisplayStart
		 */
		public int getiDisplayStart() {
			return iDisplayStart;
		}

		/**
		 * @param iDisplayStart the iDisplayStart to set
		 */
		public void setiDisplayStart(int iDisplayStart) {
			this.iDisplayStart = iDisplayStart;
		}

		public int getiTotalRecords() {
	    return iTotalRecords;
	    }

	    public void setiTotalRecords(int iTotalRecords) {
	    this.iTotalRecords = iTotalRecords;
	    }

	    public int getiTotalDisplayRecords() {
	    return iTotalDisplayRecords;
	    }

	    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
	    this.iTotalDisplayRecords = iTotalDisplayRecords;
	    }

	    public String getsEcho() {
	    return sEcho;
	    }

	    public void setsEcho(String sEcho) {
	    this.sEcho = sEcho;
	    }

	    public String getsColumns() {
	    return sColumns;
	    }

	    public void setsColumns(String sColumns) {
	    this.sColumns = sColumns;
	    }

		/**
		 * @return the aaData
		 */
		public List<T> getAaData() {
			return aaData;
		}

		/**
		 * @param aaData the aaData to set
		 */
		public void setAaData(List<T> aaData) {
			this.aaData = aaData;
		}

		@Override
		public String toString() {
			return "LocationJsonObject [iTotalRecords=" + iTotalRecords + ", iTotalDisplayRecords="
					+ iTotalDisplayRecords + ", iDisplayStart=" + iDisplayStart + ", sEcho=" + sEcho + ", draw=" + draw
					+ ", sColumns=" + sColumns + ", level2=" + level2 + ", aaData=" + aaData + ", request=" + request
					+ "]";
		}


}
