<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>

<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beneficiary Entry</title>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />

<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sanitation.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);
	
	function de_find() {
		
			document.validatRequestForm.action = "validateBeneficiaryAction.do?method=find&d__mode="
					+ d__mode + "&menuId=SNT008";
			document.validatRequestForm.submit(); 
	}
	function de_add() {
		baseLineSurveySave.addBaseLineSurvey();
	}
	/* function forwardSurveyFlow(surveyId) {
		document.getElementById("surveyId1").value=surveyId;
		
		document.validatRequestForm.action = "validateRequestAction.do?method=saveEmployee&d__mode="+ d__mode + "&menuId=SNT008"
				
		document.validatRequestForm.submit(); 
} */
	
</script>
<style type="text/css">
      
body {
	background-color: #bdc3c7;
}

tbody {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}

.table>thead>tr>th, .table>thead>tr>td {
	font-size: .7em;
	border-bottom: 0;
	letter-spacing: 1px;
	vertical-align: center;
	padding: 2px;
	background: #000000;
	text-transform: uppercase;
	color: #ffffff;
}


.modal-header {
	background-color: #283D4B;
	padding: 6px 6px;
	color: #E8EAF6;
	border-bottom: 2px dashed #283D4B;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}
</style>

</head>
<body>
<logic:messagesPresent>
	<body bgcolor="#6699FF">
	<div id="modalContainer"></div>
		<p id="sanitation" style='display: none;'>
			<html:errors bundle="sanitation" />
		</p>
		<script type="text/javascript">
		displayMessage(true);
			var para = document.getElementById('sanitation');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML=text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>


<html:form action="validateRequestAction" method="post"
	styleId="validatRequestForm">
	<html:hidden property="surveyId" styleId='surveyId1'/>
	<div class="modal fade" id="myModal3" role="dialog" >
		<div class="modal-dialog" style='width:976px;'>

			<!-- Modal content-->
			<div class="modal-content" style='width:800px;'>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Freezed Survey Details</h4>
				</div>
				<div class="modal-body3">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>
	
	<div id="container"></div>
	
	<div id="modalContainer"></div>
</html:form>
</body>
<script type="text/javascript">
validateBeneficiary.validateBeneficiarys();

</script>
</html:html>