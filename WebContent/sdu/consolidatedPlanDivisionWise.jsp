<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"%>
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
	function de_find() {
		var status = validateFields();
		if (status) {
			document.consolidatedPlanDivisionWiseForm.action = "consolidatedPlanDivisionWiseAction.do?method=getAbstract&d__mode="
					+ d__mode + "&menuId=SDU008";
			document.consolidatedPlanDivisionWiseForm.submit();
		}
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

<html:form action="consolidatedPlanDivisionWiseAction" method="post"
	styleId="consolidatedPlanDivisionWiseForm">
<div>
<div class="panel panel-danger">
		<div class="panel-body">
		
		
				
			<h4 class="text-on-pannel text-primary col-lg-12  center-block text-center">
				Consolidated Plan-Division Wise</h4>
				
			<div class="line"></div>
			<h4
				class="text-on-pannel text-primary col-lg-12  center-block text-center" style="visibility: hidden; margin-top: -150px;">
				Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
				</h4>

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
					style="width: 150px;" onclick="de_find()">Find</button>
			</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">&nbsp;</div>


		</div>
	</div>

	<c:if test="${not empty consolidatePlanDivisionDtos}">
	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-12 ">
			
				<h4 class="text-on-pannel text-primary col-lg-12 col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Results for Consolidated Plan-Division Wise</h4> 
				<div class="line"></div>
				<div class="col-lg-12 col-md-12 sm-hidden xs-hidden"
					style="overflow-x: auto;">
					<table class="table table-hover table-responsive table-bordered ">

							<tr>
								<th scope="col" class="hidden">Division Id</th>
								<th scope="col">Division</th>
								<th scope="col">Component</th>
								<th scope="col">Total Villages</th>
							</tr>
						
						 <tbody>
							<%int i=1; %>
						<logic:iterate name="consolidatePlanDivisionDtos" id="consolidatePlanDivisionDto">
							<tr> 
								<td scope="row" class="hidden"><html:hidden name="consolidatePlanDivisionDto" property="divisionId" styleId='<%="divisionIds" + i%>'/></td>
								<td scope="row"><bean:write name="consolidatePlanDivisionDto" property="divisionName" /></td>
								<td scope="col"><bean:write name="consolidatePlanDivisionDto" property="componentName" /></td>
								<td scope="col"><bean:write name="consolidatePlanDivisionDto" property="totalVillage" /><button type="button" style="bottom-margin"class="btn btn-link btn-xs  pull-right" onclick="getDetails(document.getElementById('<%="divisionIds" + i%>').value)"><b>Details</b></button></td>
							</tr>
							<% i++; %>
						</logic:iterate>
						</tbody> 
					</table>
				</div>
			</div>
			
			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">&nbsp;</div>
				<button type="button" class="btn btn-danger col-lg-2" onclick="getPendingDivisions()">Pending Divisions</button>
		
				
		</div>
	</div>
</c:if>


<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content" style="width:auto"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Pending Divisions</h4>
				</div>
				<div class="modal-body">
				
				</div>
			</div> 
		</div>
	</div>



<div class="modal fade" id="detailModal" role="dialog">
		<div class="modal-dialog" style="margin: 30px 200px;">
			<!-- Modal content -->
			<div class="modal-content" style="width:1100px"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Details of Selected Division</h4>
				</div>
				<div class="modal-body1">
				
				</div>
			</div> 
		</div>
	</div>
</div>

</html:form>

</body>
 <script type="text/javascript">
 
 
 function getDetails(divisionId){
	 var financialYear = document.getElementById('financialYear').value;
	 
	 $
		.ajax({
			type : "POST",
			url : "consolidatedPlanDivisionWiseAction.do?method=getDetails&d__mode="
					+ d__mode + "&menuId=SDU008",
			data : {
				financialYear : financialYear,
				divisionId : divisionId
			},
			success : function(data) {
				var parsed = JSON.parse(data);
				displayDetails(parsed);
				$("#detailModal").modal('show');
			},
			error : function(error) {
			}
		});
 }
 
 
 function displayDetails(data) {
		var ctx =  window.location.origin;
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Division Details</h4>";
		html += "<div>";
		
		html += "<div class='bg-success  col-lg-12 col-md-12'>";
		html += "<div class='form-inline col-lg-2 col-md-2 col-xs-12 col-sm-12' >";
		html += "<label class='labledesign text-center' style='font-size: 1.1em;'>Village</label>";
		html += "</div>";
		html += "<div class='form-inline col-lg-6 col-md-6 col-xs-12 col-sm-12'>";
		html += "<label class=' labledesign text-center' style='font-size: 1.1em;'>Activity</label>";
		html += "</div>";
		html += "<div class='form-inline col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
		html += "<label class='text-center labledesign ' style='font-size: 1.1em;'>Expected Start Date</label>";
		html += "</div>";
		html += "<div class='form-inline col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
		html += "<label class='text-center labledesign' style='font-size: 1.1em;'>Expected End Date</label>";
		html += "</div>";
		html += "</div>";
		
		for ( var i in data) {
			html += "<div class=' col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
			html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].villageName+"</text:area>";
			html += "</div>";
			html += "<div class=' col-lg-6 col-md-6 col-xs-12 col-sm-12'>";
			html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].activityName+"</text:area>";
			html += "</div>";
			html += "<div class=' col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
			html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].expectedStartDate+"</text:area>";
			html += "</div>";
			html += "<div class=' col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
			html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].expectedEndDate+"</text:area>";
			html += "</div>"; 
			html += "<div class=' col-lg-12 col-md-12 col-xs-12 col-sm-12'>&nbsp;</div>";
		} 
		
		html += "</div>";
		html += "</div>";
		html += "</div>";
		
		html += "<div class='modal-footer' style='margin:auto'>";
		html += "<button type='button' class='btn btn-danger col-lg-4 ' data-dismiss='modal'>Close</button>";
		html += "</div>";
		document.getElementsByClassName("modal-body1")[0].innerHTML = html;
	}
 
 
 
 function getPendingDivisions(){
		var financialYear = document.getElementById('financialYear').value;
		
		$
		.ajax({
			type : "POST",
			url : "consolidatedPlanDivisionWiseAction.do?method=getPendingDivisions&d__mode="
					+ d__mode + "&menuId=SDU008",
			data : {
				financialYear : financialYear
			},
			success : function(data) {
				var parsed = JSON.parse(data);
				displayPendingDivisions(parsed);
				$("#myModal").modal('show');
			},
			error : function(error) {
			}
		});
	} 
 
 
 function displayPendingDivisions(data) {
		var ctx =  window.location.origin;
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Pending Divisions</h4>";
		html += "<div>";
		
		html += "<table class='table table-bordered table-striped' id='surveyTable2'>";
		html += "<tbody style='display: block;'>";
		for ( var i in data) {
			html += "<tr class='row'>";
			html += "<td class='col-lg-12'>";
			html += " <span>" + data[i].divisionName
					+ "</span> ";
			html += "<button type='button' class='btn btn-info btn-sm pull-right'>Send SMS</button></td>";
			html += "</tr>";
		}
		
		html += "</tbody>";
		html += "</table>";
		
		html += "</div>";
		html += "</div>";
		html += "</div>";
		
		html += "<div class='modal-footer' style='margin:auto'>";
		html += "<button type='button' class='btn btn-danger col-lg-4 ' data-dismiss='modal'>Close</button>";
		html += "<button type='button' class='btn btn-info col-lg-4 pull-right'>Send SMS To All</button>";
		html += "</div>";
		document.getElementsByClassName("modal-body")[0].innerHTML = html;
	}
 
 
 $(window).load(function() {
	 de_kyenable(true,"financialYear"); 
	 
	 <%if (MisUtility.ifEmpty(request.getAttribute("financialYear"))) {%>
		document.getElementById('financialYear').value="<%=request.getAttribute("financialYear")%>";
	 <%}%>
	 
	 Req="ent_frwrd";
		de_init('ent_frwrd', "test");
	});
 
 ajaxFunction( 'GetFilterValues.to?parentComboId=106&method=getCombo', 'financialYear');
//financialYear  @


function validateFields() {
		var financialYear = document.getElementById('financialYear').value;

		if (financialYear === "" || financialYear == null) {
			alert("Please Select Financial Year.")
			return false;
		} 
		return true;
	}


</script> 
</html:html>