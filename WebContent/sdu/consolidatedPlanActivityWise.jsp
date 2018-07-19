<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>

<script type="text/javascript">
	function de_add() {
		var result = true;
		var status = validateFields();
		if (status) {
			document.consolidatedPlanActivityWiseForm.action = "consolidatedPlanActivityWiseAction.do?method=getAbstract&d__mode="
					+ d__mode + "&menuId=SDU007";
			document.consolidatedPlanActivityWiseForm.submit();
		}
	}
	
	
	
	function validateFields(){
		
		var financialYear = document.getElementById('financialYear').value;
		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year.');
			return false;
		}
		return true;
	}
</script>

<style>
tr th.DATAGRID {
	border-left: 2px solid #BDBDBD;
	width: 189px;
	top: 0;
}

.DATAGRID {
	border: .5px;
	color: #000000;
}
</style>

</head>
<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="sdu" style='display: none;'>
			<html:errors bundle="SDU" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('sdu');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>



<html:form action="consolidatedPlanActivityWiseAction" method="post"
	styleId="consolidatedPlanActivityWiseForm">

<div class="panel panel-danger">
		<div class="panel-body">
		<h4
				class="text-on-pannel text-primary col-lg-12  center-block text-center" style="visibility: hidden; margin-top: -50px;">
				Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
				</h4>
			<h4
				class="text-on-pannel text-primary col-lg-12  center-block text-center">
				Consolidated Plan-Activity Wise
				</h4>
			<div class="line"></div>

				
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-10 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="financial.year" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-12 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				<div class="col-lg-2 col-md-4 sm-hidden xs-hidden">&nbsp;</div>
			<div class="col-lg-6 col-md-1 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary center-block"
					style="width: 150px;" onclick="de_add()">Find</button>
			</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">&nbsp;</div>


		</div>
	</div>

	
	
	
	
	<c:if test="${not empty consolidatePlanActivityDtos}">
	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-12 ">

				
				<h4
				class="text-on-pannel text-primary col-lg-12 col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Results for Consolidated Plan-Activity Wise</h4> 
				<div class="line"></div>
				<div class="col-lg-12 col-md-12 sm-hidden xs-hidden"
					style="overflow-x: auto;">
					<table class="table table-hover table-responsive table-bordered "
						id="divisionBudgetTbl">

						
							<tr>
								<th scope="col">Activity Name</th>
								<th scope="col">Activity Category</th>
								<th scope="col">Total</th>
							</tr>
						
						 <tbody>
							
						<logic:iterate name="consolidatePlanActivityDtos" id="consolidatePlanActivityDto">
								<tr> <td scope="row">	<bean:write name="consolidatePlanActivityDto" property="activityName" /> </td>
								 <td scope="col"><bean:write name="consolidatePlanActivityDto"
											property="cmb_name" /></td>
											 <td scope="col"><bean:write name="consolidatePlanActivityDto"
											property="totalComponent" /></td>
								</tr>
								</logic:iterate>
								<%-- <td scope="col"><%=request.getAttribute("granTotal")%></td> --%>
							
						</tbody> 

					</table>
				</div>
			
			</div>
		</div>
	</div>
</c:if>
</html:form>

</body>
 <script type="text/javascript">
 
 
 $(window).load(function() {
	 // executes when HTML-Document is loaded and DOM is ready
	 de_kyenable(true,"financialYear");
	 
	 <%if (MisUtility.ifEmpty(request.getAttribute("financialYear"))) {%>
		document.getElementById('financialYear').value="<%=request.getAttribute("financialYear")%>";
	 <%}%>
	 
	});
 
 ajaxFunction( 'GetFilterValues.to?parentComboId=106&method=getCombo', 'financialYear');
//financialYear  @



</script> 
</html:html>