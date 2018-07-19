<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


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
de_kyenable(true,"financialYear@division@village@stage@category@component");
	
	
	function de_saveorUpdate (){
		document.trackingForm.action = "trackingAction.do?method=save&d__mode="
			+ d__mode + "&menuId=SDU005";
	document.trackingForm.submit();
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

<c:set var="expectedStartDate">
	<bean:message bundle="SDU" key="expected.start.date" />
</c:set>
<c:set var="expectedEndDate">
	<bean:message bundle="SDU" key="expected.end.date" />
</c:set>
<c:set var="activity">
	<bean:message bundle="SDU" key="activity" />
</c:set>
<c:set var="village">
	<bean:message bundle="SDU" key="village" />
</c:set>

<html:form action="trackingAction" method="post" styleId="trackingForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Tracking</h4>

			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="financial.year" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="division" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division"
					onchange="getComponent(); getVillagsFromDivision();"
					styleId="division" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:select>
				<%--     --%>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>



			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${village}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="village" styleId="village"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
				style="visibility: hidden;">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="financial.year" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='financialYear' styleClass="form-control ci5"
					style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-12 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>




			<h4
				class="text-on-pannel text-danger col-lg-10 col-xs-12 col-sm-12 col-md-12 center-block text-left "
				style="margin-left: 200px; margin-bottom: 20px;">Filters</h4>


			<%-- <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="category" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='category' styleId='category'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="stage" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='stage' styleId='stage'
					onchange="getComponentNameAndId();" styleClass="form-control ci5"
					style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="component" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>
 --%>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="category" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='category' styleId='category'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="stage" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='stage' styleId='stage'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="component" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>





			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
				style="margin-left: -170px;">
				<button type="button" class="btn btn-primary center-block"
					style="width: 150px;" onclick="searchx()">Populate</button>
			</div>


		</div>
	</div>






	<!-- 
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div> -->


	<c:if test="${not empty trackingDtos}">
		<div class="panel panel-danger" id="revalidate">
			<div class="panel-body">


				<div class="table-responsive" >
					<table class="table table-bordered  table-hover" id="monthlyPlan"
						style='display: block;'>
						<thead>
							<tr>

								<!-- <th class='col-lg-3 col-xs-1' style='text-align: center'>S.No</th> -->
								<th style='text-align: center; background-color: #FFFFFF'>Activity
									Name</th>
								<th style='text-align: center; background-color: #FFFFFF'>Expected
									Start Date</th>
								<th style='text-align: center; background-color: #FFFFFF'>Expected
									End Date</th>
								<th style='text-align: center; background-color: #FFFFFF'>Actual
									Start Date</th>
								<th style='text-align: center; background-color: #FFFFFF'>Actual
									End Date</th>
								<th style='text-align: center; background-color: #FFFFFF'>Outcome
								</th>
								<th style='text-align: center; background-color: #FFFFFF'>Achieved</th>
								<th style='text-align: center; background-color: #FFFFFF'>Comments</th>


							</tr>
						</thead>

						<tbody id='fnlTable'>

							<%
								int i = 1;
							%>
							<logic:iterate name="trackingDtos" id="trackingDto"
								property="trackingDtos" indexId="rowindex"
								type="com.prwss.min.SDU.BO.TrackingDto">


								<html:hidden name="trackingDto" property="activityId"
									styleId='<%="activityId" + i%>' indexed="true" />

								<html:hidden name="trackingDto" property="villageactivityId"
									styleId='<%="villageactivityId" + i%>' indexed="true" />

								<tr>
									<td id='<%="activityName" + i%>'><html:textarea
											name="trackingDto" property="activity_Name" rows="5"
											styleId='<%="activityName" + i%>' indexed="true"
											styleClass=" form-control " style="min-width:230px;"
											readonly="true"></html:textarea></td>

									<td id='<%="expected_Start_Date" + i%>'><html:text
											name="trackingDto" property="expected_Start_Date"
											styleId='<%="expected_Start_Date" + i%>' indexed="true" style="min-width:90px;"
											disabled="true" styleClass=" form-control" readonly="true"></html:text></td>

									<td id='<%="expected_End_Date" + i%>'><html:text
											name="trackingDto" property="expected_End_Date"
											styleId='<%="expected_End_Date" + i%>' indexed="true" style="min-width:90px;"
											disabled="true" styleClass=" form-control" readonly="true"></html:text></td>

									<td id='<%="actual_start_date" + i%>'><html:text
											name="trackingDto" property="aStartDate"
											styleId='<%="aStartDate" + i%>' indexed="true" style="min-width:110px;"
											styleClass=" form-control datepicker"></html:text></td>

									<td id='<%="actual_End_Date" + i%>'><html:text
											name="trackingDto" property="aEndDate"
											styleId='<%="aEndDate" + i%>' indexed="true" style="min-width:110px;"
											styleClass=" form-control datepicker"></html:text></td>

									<td id='<%="outcome_Name" + i%>'><html:textarea
											name="trackingDto" property="outcome_Name" readonly="true"
											styleId='<%="outcome_Name" + i%>' indexed="true" rows="3" style="min-width:110px;"
											disabled="true" styleClass=" form-control "></html:textarea></td>

									<td id='<%="achieved" + i%>'><html:checkbox
											name="trackingDto" indexed="true" property="dbAcheived"
											styleId='<%="achieved" + i%>' style="margin-left:30px; "></html:checkbox></td>

									<td id='<%="coments" + i%>'><html:textarea
											name="trackingDto" property="coments"
											styleId='<%="coments" + i%>' indexed="true" style="min-width:100px;"></html:textarea></td>

								</tr>

								<%
									i++;
								%>
							</logic:iterate>

						</tbody>

					</table>
				</div>


				<div>
					<div class="col-lg-5 col-md-4 sm-hidden xs-hidden">&nbsp;</div>
					<div class="col-lg-7 col-md-4 sm-hidden xs-hidden">
						<button type="button" class="btn btn-warning pull-right"
							onclick="de_saveorUpdate()" style="width: 200px;">Update
							Record</button>
					</div>


				</div>



			</div>
		</div>

	</c:if>


</html:form>

</body>
<script type="text/javascript">




 


 
$(document).ready(function() {
	
	
	
	ajaxFunction( 'GetFilterValues.to?parentComboId=106&method=getCombo', 'financialYear');
	 ajaxFunction( 'GetFilterValues.to?parentComboId=115&method=getCombo', 'component');
	 var username = '<%=request.getSession().getAttribute("username")%> ';
	 
	 if (username === "") {
			alert("Something Bad happened. Please try again!");
		} else {
			//call Ajax
			ajaxFunction("trackingAction.do?method=getDivisionsBasedOnUserId&username_="+ username, 'division');
			ajaxFunction("trackingAction.do?method=getStages", 'stage');

		}
	 
	 
	 <%if (MisUtility.ifEmpty(request.getParameter("financialYear"))) {%>
	 //alert('<%=request.getParameter("financialYear")%>');
	 document.getElementById("financialYear").value = '<%=request.getParameter("financialYear")%>';
	 <%}%>
	 
	 <%if (MisUtility.ifEmpty(request.getParameter("division"))) {%>
	 document.getElementById("division").value = '<%=request.getParameter("division")%>'; 
	 <%}%>
	 
	 <%if (MisUtility.ifEmpty(request.getParameter("stage"))) {%>
	 document.getElementById("stage").value = '<%=request.getParameter("stage")%>';
	 triggerEvent(document.getElementById('division'), 'onchange');
	 <%}%>
	 
	 <%if (MisUtility.ifEmpty(request.getParameter("category"))) {%>
	 document.getElementById("category").value = '<%=request.getParameter("category")%>'; 
	 triggerEvent(document.getElementById('stage'), 'onchange');
	 <%}%>
	 
	 <%if (MisUtility.ifEmpty(request.getParameter("component"))) {%>
	 document.getElementById("component").value = '<%=request.getParameter("component")%>';
<%}%>
	}

			);

	function getVillagsFromDivision() {
		var divisionId = document.getElementById('division').value;
		//alert(divisionId);
		ajaxFunction("villageDivisionMpgAction.do?method=getVillages&divId="
				+ divisionId, 'village');

	}

	function getComponent() {
		var year = document.getElementById("financialYear").value;
		var division_ = document.getElementById("division").value;
		ajaxFunction("stageComponentMpgAction.do?method=getComponents&year="
				+ year + "&div=" + division_, 'category');

		/* if(year === "" &&  division_ == ""){
			alert ("Please select Division and Financial Year");
		}else{
			} */
	}

	$('#expectedStartDate,#defaultInline').datepick();
	$('#expectedEndDate,#defaultInline').datepick();

	function searchx() {
		if (validateFields()) {
			document.trackingForm.action = "trackingAction.do?method=find&d__mode="
					+ d__mode + "&menuId=SDU005";
			document.trackingForm.submit();
		}

	}

	function validateFields() {
		var financialYear = document.getElementById("financialYear").value;

		var division = document.getElementById("division").value;

		var stage = document.getElementById("stage").value;

		var category = document.getElementById("category").value;

		var component = document.getElementById("component").value;
		var village = document.getElementById("village").value;

		if (financialYear == "" || financialYear == null) {
			alert("Please Select Financial Year.");
			return false;
		} else if (division == "" || division == null) {
			alert("Please Select Division .");
			return false;
		} else if (village == "" || village == null) {
			alert("Please Select village .");
			return false;
		} else if (stage == "" || stage == null) {
			alert("Please Select Stage .");
			return false;
		} else if (category == "" || category == null) {
			alert("Please Select category .");
			return false;
		} else if (component == "" || component == null) {
			alert("Please Select component .");
			return false;
		}
		return true;
	}

	$('.datepicker,#defaultInline').datepick();
</script>
<!-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<style>
thead tr {
	background-color: #646b71;
}
</style>
</html:html>