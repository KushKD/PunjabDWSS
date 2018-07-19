
<%@page import="com.prwss.mis.common.MISSessionBean"%>
<%@page import="com.prwss.mis.login.dao.LoginUserPermissionBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<head>
<link rel="stylesheet" type="text/css" href="pro_drop_1/pro_drop_1.css" />
<script src="pro_drop_1/stuHover.js" type="text/javascript"></script>
<style type="text/css">
.pre-scrollable {
	max-height: 600px;
	overflow-y: scroll;
}

::-webkit-scrollbar {
	width: 12px;
}

::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

::-webkit-scrollbar-thumb {
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 10.2);
}
</style>
</head>
<%
	MISSessionBean misSessionBean = null;
	if (session.getAttribute("misSessionBean") != null) {
		misSessionBean = (MISSessionBean) session.getAttribute("misSessionBean");
	}

	@SuppressWarnings("unchecked")
	Map<String, String> loginUserPermissionBeans = (TreeMap<String, String>) session
			.getAttribute("menuPermission");
%>


<%-- <div class="sidebar-header  center-block">
	<%
		if(misSessionBean != null ) {
	%>

	<div >
		<font color="#FFFFFF" size="2">User Name : <%=misSessionBean.getEmployeeName()%>
		</font>

		<form >

			<a href="welcome.jsp" type="submit" class="btn btn-warning">Logout</a>
		</form>
	</div>

	<%
		}
	%>


</div> --%>

<div id="closemenu" class="center-block pull-right"
	style="padding: 10px;">
	<i class="glyphicon glyphicon-arrow-left"></i>
</div>

<div class="sidebar-header" style="background-color: #646b71">
	<h5>MIS-Applicaion</h5>
</div>

<ul class="list-unstyled components pre-scrollable">



	<logic:present name="menuPermission" scope="session">
		<!-- <li  class="dropdown"> -->
		<!-- <a href="#nogo1" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Finance<span class="caret"></span></a>
						 -->
		<li class="active"><a href="#waterQualitySubMenu"
			data-toggle="collapse" aria-expanded="false">Water Quality</a>
			<ul class="collapse list-unstyled" id="waterQualitySubMenu">
				<%
					if (loginUserPermissionBeans.containsKey("WQ002")) {
				%><li><a href="labMasterAction.do?menuId=WQ002">Lab Master</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ009")) {
				%><li><a href="collectionCenterAction.do?menuId=WQ009">Collection
						Center</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("WQ006")) {
				%><li><a href="parameterMasterAction.do?menuId=WQ006">Parameter
						Master</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ001")) {
				%><li><a href="receiveSampleAction.do?menuId=WQ001">Receive
						Sample</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("WQ003")) {
				%><li><a href="sampleDistributionAction.do?menuId=WQ003">Sample
						Distribution</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ004")) {
				%><li><a href="resultEntryAction.do?menuId=WQ004">Test
						Result Entry</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ007")) {
				%><li><a href="testResultEntryAR.do?menuId=WQ007">Test
						Result Entry</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ008")) {
				%><li><a href="waterParameterReportAction.do?menuId=WQ008">Water
						Parameters Report</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("WQ010")) {
				%><li><a href="testResultReportAction.do?menuId=WQ010">Test
						Result Reports</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("WQ011")) {
				%><li><a href="freezeUnFreezeAction.do?menuId=WQ011">Freeze
						Sample View Report</a></li>
				<%
					}
				%>


				<%-- <%
					if (loginUserPermissionBeans.containsKey("WQ005")) {
				%><li><a href="resultDisplayAction.do?menuId=WQ005">Test
						Result Display</a></li>
				<%
					}
				%> --%>
				<%-- <%
					if (loginUserPermissionBeans.containsKey("RPT001")) {
				%><li><a
					href="estimatesAwardContractsReportAction.do?menuId=RPT001">PMS
						Reports</a></li>
				<%
					}
				%> --%>
			</ul></li>

		 <li><a href="#adminSubmenu" data-toggle="collapse"
			aria-expanded="false">Admin</a>
			<ul class="collapse list-unstyled" id="adminSubmenu">


				<%
					if (loginUserPermissionBeans.containsKey("ADM014")) {
				%><li><a href="locationMasterAction.do?menuId=ADM014">Location
						Master</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("ADM015")) {
				%><li><a href="locationTypeAction.do?menuId=ADM015">Location
						Type Master</a></li>
				<%
					}
				%>
				
				<%
					if (loginUserPermissionBeans.containsKey("SNT015")) {
				%><li><a href="gramPanchayatMasterAction.do?menuId=SNT015">Gram
						Panchayat Master</a></li>
				<%
					}
				%>
			</ul></li> 


		<!-- start sanitaion  -->


		<li><a href="#sanitationsubmenu" data-toggle="collapse"
			aria-expanded="false">Sanitation</a>
			<ul class="collapse list-unstyled" id="sanitationsubmenu">

				<%
					if (loginUserPermissionBeans.containsKey("SAN001")) {
				%><li><a href="gramPanchayatRegisterAction.do?menuId=SAN001">Gram
						Panchayat Register</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SAN002")) {
				%><li><a href="viewRegistrationsAction.do?menuId=SAN002">View
						Registrations</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SAN003")) {
				%><li><a href="surveyMasterAction.do?menuId=SAN003">Survey
						Master</a></li>
				<%
					}
				%>


				<%
					if (loginUserPermissionBeans.containsKey("SNT004")) {
				%><li><a href="benifecieryEntryAction.do?menuId=SNT004">Beneficiary
						Entry</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT005")) {
				%><li><a href="benifecieryEntryViewAction.do?menuId=SNT005">View
						Beneficiary Entry</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT006")) {
				%><li><a href="motivatorEntryAction.do?menuId=SNT006">Motivator
						Entry</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SNT007")) {
				%><li><a href="baselineSurveyAction.do?menuId=SNT007">BaseLine
						Survey</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT008")) {
				%><li><a href="validateRequestAction.do?menuId=SNT008">Create
						Request</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT011")) {
				%><li><a href="validateBeneficiaryAction.do?menuId=SNT011">Beneficiary
						Validation</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT012")) {
				%><li><a href="reValidateBeneficiaryAction.do?menuId=SNT012">Beneficiary
						Re-Validation</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SNT013")) {
				%><li><a href="beneficiaryApprovalAction.do?menuId=SNT013">Beneficiary
						Approval</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SNT014")) {
				%><li><a href="paymentRequestAction.do?menuId=SNT014">Request
						For Payment</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT016")) {
				%><li><a href="verifyPaymentAction.do?menuId=SNT016">Verify
						Payment</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("SNT017")) {
				%><li><a href="approvePaymentAction.do?menuId=SNT017">Approve
						Payment</a></li>
				<%
					}
				%>


				<%
					if (loginUserPermissionBeans.containsKey("SNT009")) {
				%><li><a href="latrineUsageAction.do?menuId=SNT009">Latrine
						Usage</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT010")) {
				%><li><a href="odfDeclarationAction.do?menuId=SNT010">ODF
						Declaration</a></li>
				<%
					}
				%>


				
				<%
					if (loginUserPermissionBeans.containsKey("SNT019")) {
				%><li><a href="hallofFameAction.do?menuId=SNT019">Hall of
						Fame</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SNT020")) {
				%><li><a href="progressofWorkAction.do?menuId=SNT020">Progress
						of Work</a></li>
				<%
					}
				%>
			</ul></li>


		<li><a href="#qualityConstruction" data-toggle="collapse"
			aria-expanded="false">Construction Quality</a>
			<ul class="collapse list-unstyled" id="qualityConstruction">
				<%
					if (loginUserPermissionBeans.containsKey("CQ001")) {
				%><li><a href="yearlyInspectionPlanAction.do?menuId=CQ001">Yearly
						Inspection Plan</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ002")) {
				%><li><a href="updateYearlyPlanAction.do?menuId=CQ002">Update
						Yearly Plan</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ003")) {
				%><li><a href="createTeamAction.do?menuId=CQ003">Create
						Team</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ004")) {
				%><li><a href="monthlyPlanInspectionAction.do?menuId=CQ004">Monthly
						Inspection Plan</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("CQ005")) {
				%><li><a href="updateMonthlyPlanAction.do?menuId=CQ005">Update
						Monthly Plan</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ006")) {
				%><li><a href="monthlyPlanViewAction.do?menuId=CQ006">Monthly
						Plan View</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("CQ007")) {
				%><li><a href="enterMonthlyProgressAction.do?menuId=CQ007">Monthly
						Progress</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ008")) {
				%><li><a href="saveMonthlyReportAction.do?menuId=CQ008">Save
						Monthly Report</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("CQ009")) {
				%><li><a href="sendMonthlyReportAction.do?menuId=CQ009">Send
						Monthly Report</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("CQ010")) {
				%><li><a href="monthlyReportViewAction.do?menuId=CQ010">View
						Monthly Progress Report</a></li>
				<%
					}
				%>
				<%-- <%
					if (loginUserPermissionBeans.containsKey("CQ011")) {
				%><li><a href="approvedMonthlyReportAction.do?menuId=CQ011">Approved Monthly Report</a></li>
				<%
					}
				%> --%>
				<%
					if (loginUserPermissionBeans.containsKey("CQ012")) {
				%><li><a href="shareObservationAction.do?menuId=CQ012">Sharing
						Observation</a></li>
				<%
					}
				%>
			</ul></li>
		<!-- end sanitation -->
		<!-- START RTI -->
		<li><a href="#rtisubmenu" data-toggle="collapse"
			aria-expanded="false">RTI</a>
			<ul class="collapse list-unstyled" id="rtisubmenu">

				<%
					if (loginUserPermissionBeans.containsKey("RTI001")) {
				%><li><a href="rtiOnlineAction.do?menuId=RTI001">Submit RTI</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("RTI002")) {
				%><li><a href="assignrtiOnlineAction.do?menuId=RTI002">Assign
						RTI</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("RTI003")) {
				%><li><a href="updateRtiAction.do?menuId=RTI003">Update RTI</a></li>
				<%
					}
				%>


			</ul></li>
		<!-- END RTI -->
		
		
		<!-- START SDU -->
		<li><a href="#sdusubmenu" data-toggle="collapse"
			aria-expanded="false">SDU</a>
			<ul class="collapse list-unstyled" id="sdusubmenu">

				<%
					if (loginUserPermissionBeans.containsKey("SDU001")) {
				%><li><a href="divisionWiseSummaryAction.do?menuId=SDU001">Division Wise Summary</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU002")) {
				%><li><a href="stageComponentMpgAction.do?menuId=SDU002">Stage-Component Mapping</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU003")) {
				%><li><a href="divisionActivityMpgAction.do?menuId=SDU003">Division Activity Mapping</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU004")) {
				%><li><a href="villageDivisionMpgAction.do?menuId=SDU004">Village Division Mapping</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU005")) {
				%><li><a href="villageActivityMpgAction.do?menuId=SDU005">Village Activity Mapping</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU006")) {
				%><li><a href="divisionWisePlanViewAction.do?menuId=SDU006">Division Wise Plan View</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU007")) {
				%><li><a href="consolidatedPlanActivityWiseAction.do?menuId=SDU007">Consolidated Plan-Activity Wise</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU008")) {
				%><li><a href="consolidatedPlanDivisionWiseAction.do?menuId=SDU008">Consolidated Plan-Division Wise</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU009")) {
				%><li><a href="trackingAction.do?menuId=SDU009">Tracking</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("SDU011")) {
				%><li><a href="trainingDetailsEntryAction.do?menuId=SDU011">Training Details Entry</a></li>
				<%
					}
				%>
			
			</ul></li>
		<!-- END SDU -->

		<!-- START FINANCE -->
		<li><a href="#financesubmenu" data-toggle="collapse"
			aria-expanded="false">Finance</a>
			<ul class="collapse list-unstyled" id="financesubmenu">
				<%
					if (loginUserPermissionBeans.containsKey("FN001")) {
				%><li><a href="financialHeadsAction.do?menuId=FN001">Finance
						Heads</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN002")) {
				%><li><a href="financialHeadStructureAction.do?menuId=FN002">Head
						Structure</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN003")) {
				%><li><a href="componentAction.do?menuId=FN003">Components</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("FN004")) {
				%><li><a href="divisionBudgetAction.do?menuId=FN004">Budget
						Division</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN005")) {
				%><li><a href="viewDivisionBudgetAction.do?menuId=FN005">Budget
						Grid</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN006")) {
				%><li><a href="divisionBudgetViewAction.do?menuId=FN006">Division
						Budget View</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN008")) {
				%><li><a href="nodalDivisionBudgetViewAction.do?menuId=FN008">Nodal
						Division Budget View</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("FN007")) {
				%><li><a href="stateBudgetViewAction.do?menuId=FN007">State
						Budget View</a></li>
				<%
					}
				%>

				<%
					if (loginUserPermissionBeans.containsKey("FN009")) {
				%><li><a href="fundSourceMasterAction.do?menuId=FN009">Fund
						Source Master</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN010")) {
				%><li><a href="gpwscRegisterAction.do?menuId=FN010">GPWSC
						Register</a></li>
				<%
					}
				%>
				<%
					if (loginUserPermissionBeans.containsKey("FN011")) {
				%><li><a href="ddoMasterAction.do?menuId=FN011">DDO	Master</a></li>
				<%
					}
				%>
				
				<%
					if (loginUserPermissionBeans.containsKey("FN012")) {
				%><li><a href="fundRequestAction.do?menuId=FN012">Fund Request</a></li>
				<%
					}
				%>
				
				<%
					if (loginUserPermissionBeans.containsKey("FN013")) {
				%><li><a href="allocationAction.do?menuId=FN013">Allocation</a></li>
				<%
					}
				%>

			</ul></li>
		<!-- END FINANCE -->
		
		<!-- Start Environment -->
		<li><a href="#environmentsubmenu" data-toggle="collapse"
			aria-expanded="false">Environment</a>
			<ul class="collapse list-unstyled" id="environmentsubmenu">
				<%
					if (loginUserPermissionBeans.containsKey("ENV001")) {
				%><li><a href="environmentDataCollectionAction.do?menuId=ENV001">Environment Data Sheet (EDS)
						</a></li>
				<%
					}
				%>
				
				</ul>
				</li>
		<!-- End Environment -->
	</logic:present>


</ul>
