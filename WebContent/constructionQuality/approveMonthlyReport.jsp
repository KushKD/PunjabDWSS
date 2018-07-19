<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>

<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var monthId = localStorage.getItem("monthId");
						var fnclYear = localStorage.getItem("fnclYear");
						document.getElementById('month').value = monthId;
						document.getElementById('yearPlan').value = fnclYear;
						de_find();
						localStorage.setItem("count", 3);
						var monthName = localStorage.getItem("monthName");
						var yearName = localStorage.getItem("financialYear");
						$("#monthName").text(monthName);
						$("#yearPlanName").text(yearName);
           <%if (MisUtility.ifEmpty(request.getAttribute("monthlyPlanId"))) {%>
				document.getElementById('monthlyPlanId').value="<%=request.getAttribute("monthlyPlanId")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("PRE_IMPLEMENTATION"))) {%>
				document.getElementById('preImplementation').value="<%=request.getAttribute("PRE_IMPLEMENTATION")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("IMPLEMENTATION"))) {%>
				document.getElementById('implementation').value="<%=request.getAttribute("IMPLEMENTATION")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("POST_IMPLEMENTATION"))) {%>
				document.getElementById('postImplementation').value="<%=request.getAttribute("POST_IMPLEMENTATION")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("IMPORTANT_OBSERVATIONS"))) {%>
				document.getElementById('importantObservation').value="<%=request.getAttribute("IMPORTANT_OBSERVATIONS")%>";
			<%}%>
						Req = "ent_frwrd";
						de_init('ent_frwrd', "test");

					});
	function de_forward() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.sendMonthlyReportForm.action = "approvedMonthlyReportAction.do?method=approve&d__mode="
					+ d__mode + "&menuId=CQ011";
			document.sendMonthlyReportForm.submit();
		}
	}
	function de_find() {
	var count=localStorage.getItem("count");
	    
	    if(count<2){
			var result = true;
			if (result) {
				document.sendMonthlyReportForm.action = "approvedMonthlyReportAction.do?method=populate&d__mode="
						+ d__mode + "&menuId=CQ011";
				document.sendMonthlyReportForm.submit();
			}
		} 
	   
	}
</script>
<style>
#monthlyProgressTbl {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
}

#monthlyProgressTbl {
	display: block;
}
</style>
</head>
<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="construction" style='display: none;'>
			<html:errors bundle="construction" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('construction');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
			var successUrl = "monthlyReportViewAction.do?menuId=CQ010";
			window.location.href = successUrl;
		</script>
</logic:messagesPresent>
<html:form action="approvedMonthlyReportAction" method="post"
	styleId="sendMonthlyReportForm" >

	<c:set var="observation">
		<bean:message bundle="construction" key="observation" />
	</c:set>
	<c:set var="phaseId">
		<bean:message bundle="construction" key="phase.id" />
	</c:set>
	<c:set var="phaseName">
		<bean:message bundle="construction" key="phase.name" />
	</c:set>
	<c:set var="schemeId">
		<bean:message bundle="construction" key="scheme.id" />
	</c:set>
	<c:set var="schemeName">
		<bean:message bundle="construction" key="scheme.name" />
	</c:set>
	

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Send Monthly Report</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="yearly.plan" /> </label> <label
					id='yearPlanName'></label>
			</div>
			<html:hidden property='yearPlan' styleId='yearPlan' />
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="month" /> </label> <label
					id='monthName'></label>

			</div>
			<html:hidden property="month" styleId="month" />
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<!-- ------------------------- -->
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			<div class="line"></div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="form-inline col-lg-4 col-md-5 sm-hidden xs-hidden">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="upload.report" /> </label>
						<label><a href="approvedMonthlyReportAction.do?method=downloadReport&monthlyPlanId=<%=request.getAttribute("monthlyPlanId")%>"  id='report'><%=request.getAttribute("reportName")%>
					</a></label>
			
			</div>
			<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-10 col-md-10 sm-hidden xs-hidden"
				style='max-width: 1000px'>
				<div id="monthlyProgressTbl1">

					<c:if test="${not empty monthlyProgressDtos}">
						<div class="panel panel-danger" id="revalidate">
							<div class="panel-body">

								<div class=" form-inline col-lg-12 col-md-5 col-xs-12 col-sm-12">
									<div class="table-responsive">
										<table
											class="table table-bordered table-striped table-highlight"
											id="monthlyPlanProgress">
											<thead style='display: block;' class='thead-dark'>
												<tr class="row">

													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Component
														Name</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>No.
														Of Villages to be Visited</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Team
														Name</th>
													<th class='col-lg-2 col-xs-1' style='text-align: center;'>Planning</th>
													<th class='col-lg-2 col-xs-1' style='text-align: center;'>Implementation</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Post
														Implementation</th>
												</tr>
											</thead>


											<tbody id="monthlyProgressTbl">
												<%
													int i = 1;
												%>
												<logic:iterate name="monthlyProgressDtos"
													id="monthlyPlanLst" property="monthlyProgressDtos"
													indexId="rowindex"
													type="com.prwss.min.construction.quality.bean.MonthlyProgressDto">
													<tr class="row">
														<td class='col-lg-2 col-xs-3' style='text-align: center'>
															<html:text name="monthlyPlanLst" property="componentName"
																indexed="true" style="width: 182px;text-align:center"
																styleClass=" form-control" readonly="true">
															</html:text>
														</td>

														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="villageToBeVisited"
																indexed="true" styleClass="form-control"
																style="width:182px" readonly="true">
															</html:text></td>

														<td class='col-lg-2 col-xs-3'
															style='font-size: 0.9em; text-align: center'><html:text
																name="monthlyPlanLst" property="teamName"
																styleClass="cs2 form-control" style="width:185px"
																indexed="true" readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="planning" indexed="true"
																styleClass="cs2 form-control" style="width:182px"
																readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="implementation"
																indexed="true" styleClass="cs2 form-control"
																style="width:182px" readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="postimplementaion"
																indexed="true" styleClass="cs2 form-control"
																style="width:180px" readonly="true">
															</html:text></td>


													</tr>
													<%
														i++;
													%>
												</logic:iterate>
											</tbody>

										</table>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Observations</h4>
			<div
				class=" form-inline col-lg-2 col-md-1 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="pre.implementation" /> </label>

				<html:textarea property='preImplementation'
					styleId='preImplementation' styleClass="form-control ci5"
					style="width: 500px;">
				</html:textarea>
			</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="implementation" /> </label>
				<html:textarea property='implementation' styleId='implementation'
					styleClass="form-control ci5" style="width: 500px;">
				</html:textarea>
			</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>

			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="post.implementation" /> </label>
				<html:textarea property='postImplemetation'
					styleId='postImplemetation' styleClass="form-control ci5"
					style="width: 500px;">
				</html:textarea>
			</div>

			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>


			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="important.observation" /></label>
				<html:textarea property='importantObservation'
					styleId='importantObservation' styleClass="form-control ci5"
					style="width: 500px;">
				</html:textarea>
			</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div
				class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="comment" /></label>
				<html:textarea property='comment' styleId='comment'
					styleClass="form-control ci5" style="width: 500px;">
				</html:textarea>
			</div>
			<html:hidden property="monthlyPlanId" styleId="monthlyPlanId" />
		</div>
	</div>
	<div class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
	<div class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
	<div class=" form-inline col-lg-1 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
	<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">
		<button type="button" class="btn btn-primary" style="width: 150px;"
			onclick="de_forward()">Approved</button>
	</div>
	<div class=" form-inline col-lg-4 col-md-5 col-xs-hidden col-sm-hidden">&nbsp;</div>
	<div class=" form-inline col-lg-2 col-md-2 col-xs-hidden col-sm-hidden">&nbsp;</div>
</html:form>

</body>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
	
</script>
</html:html>