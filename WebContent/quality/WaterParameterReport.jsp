<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />



<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/waterquality.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/dataTables.select.min.js"></script>

<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<script type="text/javascript" src="js/buttons.flash.min.js"></script>


<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script>

<script type="text/javascript">
	hide_ctrl('modalPeriod', true);

	function downloadPdf() {

		var elementExists = document.getElementById("parameterTable");
		
		if(elementExists) {
			
			var x = document.getElementById("parameterTable").rows.length;
			
			if(x>1){
				var TableData;
				TableData = storeTblValues();
				TableData = $.toJSON(TableData);
				//alert(TableData);
				 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=filePDF&d__mode="
						+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData +"&all=no";
				document.waterParameterReportForm.submit(); 
				
			}else{
				alert("Downloading the complete Report with  all the parameters");
				 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=filePDF&d__mode="
				+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData +"&all=yes";
		document.waterParameterReportForm.submit(); 
				
			}
			
		}else{
			
			alert("Downloading  the complete Report with  all the parameters");
			 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=filePDF&d__mode="
			+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData  +"&all=yes";
	document.waterParameterReportForm.submit(); 
		}
		
		
		
		

	}

	function storeTblValues() {
		var TableData = new Array();

		var table = document.getElementById('parameterTable');
		for (var r = 1, n = table.rows.length; r < n; r++) {
			var row = {
				"Perameter Id" : table.rows[r].cells[2].children[0].value,
				"Acceptable Limit" : table.rows[r].cells[4].children[0].value,
				"Permissible Limit" : table.rows[r].cells[6].children[0].value,
				"BDL" : table.rows[r].cells[7].children[0].checked,
				"ND" : table.rows[r].cells[8].children[0].checked,
				"Fail" : table.rows[r].cells[9].children[0].checked,
				"Pass" : table.rows[r].cells[10].children[0].checked

			}

			TableData.push(row);
		}

		return TableData;
	}

	function getParameterID() {
		var PerameterId = new Array();
		var table = document.getElementById('parameterTable');
		var rows = null;

		if (countClicks === 0) {
			var id = document.getElementById('parameter').value;
			PerameterId.push(id);
			//alert(PerameterId.length);
			return PerameterId;
		} else {
			//alert("table.rows.length" + table.rows.length);
			if (table.rows.length != null) {

				//check if array is not null i.e. length is  > 1

				for (var r = 1, n = table.rows.length; r < n; r++) {
					row = table.rows[r].cells[2].children[0].value;
					PerameterId.push(row);
				}
				return PerameterId;
			}
		}

	}

	function checkforParameterInArray(arrayData, id) {

		var arrayDataToNumeric = null;
		var id_new = new Number(id);

		//Check the Size of Array
		if (arrayData.length === 0) {
			//alert("We are About to add the Row" + arrayData.length);
			return true;

		} else {
			if (arrayData.includes(id)) {
				return false;
			} else {

				return true;
			}
		}

	}

	function checkParameterID() {
		//alert("We are Inside Check Parameter Id");
		var id = document.getElementById('parameter').value;

		var addParameterBoolean = false;
		var ParameterIdData = null;
		ParameterIdData = getParameterID();
		/* alert("Array Data:-  " + ParameterIdData);
		alert("Array Length:-  " + ParameterIdData.length);
		alert("Select ID:-  " + id); */

		if (ParameterIdData.length === 0) {
			addParameterforZeroRow();
		} else {

			addParameterBoolean = checkforParameterInArray(ParameterIdData, id);
			if (addParameterBoolean) {
				insRow();
			} else {
				var message = "The Selected Parameter is already added in the filter list.";
				var alerttype = "alert-danger";
				showalert(message, alerttype);
			}

		}

	}

	function downloadExcel() {
		
		
		
		
		var elementExists = document.getElementById("parameterTable");
		
		if(elementExists) {
			
			var x = document.getElementById("parameterTable").rows.length;
			
			if(x>1){
				var TableData;
				TableData = storeTblValues();
				TableData = $.toJSON(TableData);
				//alert(TableData);
				 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=fileEXCEL&d__mode="
						+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData +"&all=no";
				document.waterParameterReportForm.submit(); 
				
			}else{
				alert("Downloading  the complete Report with  all the parameters");
				 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=fileEXCEL&d__mode="
				+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData +"&all=yes";
		document.waterParameterReportForm.submit(); 
				
			}
			
		}else{
			
			alert("Downloading  the complete Report with  all the parameters");
			 document.waterParameterReportForm.action = "waterParameterReportAction.do?method=fileEXCEL&d__mode="
			+ d__mode + "&menuId=SAN008" + "&tabledata=" + TableData  +"&all=yes";
	document.waterParameterReportForm.submit(); 
		}
		
		
	

	}

	function addParamater() {

		
		if (validateFields()) {
			countClicks++;
			if (countClicks == 1) {

				var html = "";
				html += "<table id='parameterTable' style='width:100%''>";
				html += "<thead style='background-color:#9099A2;'> ";
				html += "<td class='text-center' style='width:5%;background-color:#9099A2;color:white;padding:10px;'>S.No</td> ";
				html += "<td class='text-center' style='width:15%;background-color:#9099A2;color:white;padding:10px;'> Parameter Name</td>";
				html += "<td class='text-center' style='width:15%;background-color:#9099A2;color:white;padding:10px;display: none'> Parameter Id</td>";
				html += "<td class='text-center' style='width:5%;background-color: darkcyan ; color:white;padding:10px;'></td> ";
				html += "<td class='text-center'style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Acceptable Limit</td>";
				html += "<td class='text-center' style='width:5%;background-color: grey ; color:white;padding:10px;'></td>";
				html += "<td class='text-center'style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Permissible Limit</td>";
				html += "<td class='text-center' style='width:10%;background-color:DarkTurquoise ; color:white;padding:10px;'>BDL</td> ";
				html += "<td class='text-center' style='width:10%;background-color:indianred; color:white;padding:10px;'>ND</td>";
				html += "<td class='text-center' style='width:10%;background-color:brown; color:white;padding:10px;'>Fail</td>";
				html += "<td class='text-center' style='width:10%;background-color:green; color:white;padding:10px;'>Pass</td>";
				html += "<td class='text-center' style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Remove</td>";
				html += "</thead>";
				html += "<tr>";
				html += "<td class='text-center' style='width:5%'>1</td> ";
				html += "<td style='width:15%'><input class=' form-control' readonly  type='text' id='parameter_name' /></td>";
				html += "<td style='width:15%;display: none;'><input class=' form-control' readonly  type='text' id='parameter_id' /></td>";
				html += "<td class='text-center' style='width:5%;  background-color: darkcyan ; color:white' >></td>";
				html += "<td style='width:10%'><input class=' form-control ' onkeypress='return isNumberKey(event);'   type='text' id='acceptableLimit'  /></td> ";
				html += "<td class='text-center' style='width:5% ; background-color: grey  ; color:white' ><=</td> ";
				html += "<td style='width:10%'><input class=' form-control ' onkeypress='return isNumberKey(event);'   type='text' id='PermissibleLimit'  /></td> ";

				html += "<td style='width:10%; padding:10px; color:white;'><input  style='margin-left:40px;'  type='radio' id='bdl' name='group1' value='bdl' '  /></td>";
				html += "<td style='width:10%; padding:10px;  color:white;'><input  style='margin-left:40px;'  type='radio' id='nd' name='group1' value='nd' ' /></td>";
				html += "<td style='width:10%; padding:10px; color:white;'><input  style='margin-left:40px;'  type='radio' id='fail' name='group1' value='fail' ' /></td>";
				html += "<td style='width:10%; padding:10px;  color:white;'><input  style='margin-left:40px;'  type='radio' id='pass'  name='group1' value='pass' '/></td>";

				html += "<td style='width:10%'><a  onclick='deleteRow(this)' id='delPOIbutton' class='btn btn-danger'><span class='glyphicon glyphicon-remove'></span>  Remove </a></td> ";
				html += "</tr>";
				html += "</table>";
				document.getElementById("paramaterDiv").innerHTML = html;
				parameter_name.value = document.getElementById('parameter').selectedOptions[0].text;
				parameter_name.innerHTML = document.getElementById('parameter').value;
				parameter_id.value = document.getElementById('parameter').value;

			} else {
				//checking goes here
				//alert("Alert for countClicks" + countClicks);
				checkParameterID();
				//insRow();
			}
		}

	}

	function addParameterforZeroRow() {
		var html = '';
		html += "<table id='parameterTable' style='width:100%''>";
		html += "<thead style='background-color:#9099A2;'> ";
		html += "<td class='text-center' style='width:5%;background-color:#9099A2;color:white;padding:10px;'>S.No</td> ";
		html += "<td class='text-center' style='width:15%;background-color:#9099A2;color:white;padding:10px;'> Parameter Name</td>";
		html += "<td class='text-center' style='width:15%;background-color:#9099A2;color:white;padding:10px;display: none'> Parameter Id</td>";
		html += "<td class='text-center' style='width:5%;background-color: darkcyan ; color:white;padding:10px;'></td> ";
		html += "<td class='text-center'style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Acceptable Limit</td>";
		html += "<td class='text-center' style='width:5%;background-color: grey ; color:white;padding:10px;'></td>";
		html += "<td class='text-center'style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Permissible Limit</td>";
		html += "<td class='text-center' style='width:10%;background-color:DarkTurquoise ; color:white;padding:10px;'>BDL</td> ";
		html += "<td class='text-center' style='width:10%;background-color:indianred; color:white;padding:10px;'>ND</td>";
		html += "<td class='text-center' style='width:10%;background-color:brown; color:white;padding:10px;'>Fail</td>";
		html += "<td class='text-center' style='width:10%;background-color:green; color:white;padding:10px;'>Pass</td>";
		html += "<td class='text-center' style='width:10%;background-color:#9099A2;color:white;padding:10px;'>Remove</td>";
		html += "</thead>";
		html += "<tr>";
		html += "<td class='text-center' style='width:5%'>1</td> ";
		html += "<td style='width:15%'><input class=' form-control' readonly  type='text' id='parameter_name' /></td>";
		html += "<td style='width:15%;display: none;'><input class=' form-control' readonly  type='text' id='parameter_id' /></td>";
		html += "<td class='text-center' style='width:5%;  background-color: darkcyan ; color:white' >></td>";
		html += "<td style='width:10%'><input class=' form-control ' onkeypress='return isNumberKey(event);'  type='text' id='acceptableLimit'  /></td> ";
		html += "<td class='text-center' style='width:5% ; background-color: grey  ; color:white' ><=</td> ";
		html += "<td style='width:10%'><input class=' form-control ' onkeypress='return isNumberKey(event);'  type='text' id='PermissibleLimit'  /></td> ";

		html += "<td style='width:10%; padding:10px; color:white;'><input  style='margin-left:40px;'  type='radio' id='bdl' name='group1' value='bdl' '  /></td>";
		html += "<td style='width:10%; padding:10px;  color:white;'><input  style='margin-left:40px;'  type='radio' id='nd' name='group1' value='nd' ' /></td>";
		html += "<td style='width:10%; padding:10px; color:white;'><input  style='margin-left:40px;'  type='radio' id='fail' name='group1' value='fail' '  /></td>";
		html += "<td style='width:10%; padding:10px;  color:white;'><input  style='margin-left:40px;'  type='radio' id='pass'  name='group1' value='pass' ' /></td>";

		html += "<td style='width:10%'><a  onclick='deleteRow(this)' id='delPOIbutton' class='btn btn-danger'><span class='glyphicon glyphicon-remove'></span>  Remove </a></td> ";
		html += "</tr>";
		document.getElementById("paramaterDiv").innerHTML = html;
		parameter_name.value = document.getElementById('parameter').selectedOptions[0].text;
		parameter_name.innerHTML = document.getElementById('parameter').value;
		parameter_id.value = document.getElementById('parameter').value;
	}
	
	function clearInput(){
		document.getElementById('acceptableLimit').value = '';
		document.getElementById('PermissibleLimit').value = '';
	}

	function insRow() {

		var x = document.getElementById('parameterTable');
		var new_row = x.rows[1].cloneNode(true);
		var len = x.rows.length;
		new_row.cells[0].innerHTML = len;

		var inp1 = new_row.cells[1].getElementsByTagName('input')[0];
		inp1.id += len;
		inp1.value = document.getElementById('parameter').selectedOptions[0].text;
		inp1.innerHTML = document.getElementById('parameter').value;

		var inpx = new_row.cells[2].getElementsByTagName('input')[0];
		inpx.id += len;
		inpx.value = document.getElementById('parameter').value;

		var inp2 = new_row.cells[4].getElementsByTagName('input')[0];
		inp2.id += len;
		inp2.value = '';
		inp2.innerHTML = '';

		var inp3 = new_row.cells[6].getElementsByTagName('input')[0];
		inp3.id += len;
		inp3.value = '';
		inp3.innerHTML = '';

		var inp4 = new_row.cells[7].getElementsByTagName('input')[0];
		inp4.id += len;
		inp4.name += len;
		inp4.checked = false;
		

		var inp5 = new_row.cells[8].getElementsByTagName('input')[0];
		inp5.id += len;
		inp5.name += len;
		inp5.innerHTML = '';
		inp5.checked = false;
		

		var inp6 = new_row.cells[9].getElementsByTagName('input')[0];
		inp6.id += len;
		inp6.name += len;
		inp6.innerHTML = '';
		inp6.checked = false;

		var inp7 = new_row.cells[10].getElementsByTagName('input')[0];
		inp7.id += len;
		inp7.name += len;
		inp7.innerHTML = '';
		inp7.checked = false;

		x.appendChild(new_row);
	}

	function deleteRow(row) {
		var i = row.parentNode.parentNode.rowIndex;
		document.getElementById('parameterTable').deleteRow(i);

		countClicks--;

	}

	function validateFields() {

		
		var parameter = document.getElementById('parameter').value;

		
		  if (parameter == "" || parameter == null) {
			var message = "Please select Parameter";
			var alerttype = "alert-danger";
			showalert(message, alerttype)
			return false;
		}else{
			return true;
		}

		
	}

	function showalert(message, alerttype) {
		$('#alert_placeholder')
				.append(
						'<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'
								+ message + '</span></div>')
		setTimeout(function() {
			$("#alertdiv").remove();
		}, 3000);
	}
	
	
	
		function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode == 46){
	        var inputValue = $("#floor").val();
	        var count = (inputValue.match(/'.'/g) || []).length;
	        if(count<1){
	            if (inputValue.indexOf('.') < 1){
	                return true;
	            }
	            return false;
	        }else{
	            return false;
	        }
	    }
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
	        return false;
	    }
	    return true;
	}
</script>

</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">

		<div class="panel panel-body panel-danger" id="modalPeriod"
			style="position: absolute; min-width: 300px;; z-index: 121; background-color: #646b71; font-size: 150%; display: none;">

			<div class="row">
				<div class="col-lg-12">
					<font size="4" color="#FFFFFF"><b><html:errors
								bundle="sanitation" /></b></font>
				</div>
				<br>

				<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 ">
					<input type="button"
						class="navbar-footer btn btn-success col-lg-12" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;">
				</div>

			</div>
		</div>
		<script type="text/javascript">
			var alertObj = document.getElementById("modalPeriod");
			// center the alert box
			if (document.all && !window.opera)
				alertObj.style.top = document.documentElement.scrollTop + 50
						+ "px";
			alertObj.style.left = (document.documentElement.scrollWidth - alertObj.offsetWidth)
					/ 4 + "px";
			//alert(alertObj.style.left+":"+document.documentElement.scrollWidth+":"+ alertObj.offsetWidth);
			hide_ctrl('modalPeriod', false);
		</script>
</logic:messagesPresent>
<html:form action="waterParameterReportAction" method="post"
	styleId="waterParameterReportForm">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Location
				Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>

			<!-- First Zone and Circle Start -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-7 col-md-8 col-xs-12 col-sm-6">
				<html:radio property="reportType" value="zone" title="Zone"
					styleId="zone1" onclick="validateReportType(this);">
					<label class="text-left">Zone Wise</label>
				</html:radio>
				<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<html:radio property="reportType" value="district" title="District"
					styleId="district1" onclick="validateReportType(this);">
					<label class="text-left">District Wise</label>
				</html:radio>

				<html:radio property="reportType" value="constituency"
					title="Constituency" styleId="constituency1"
					onclick="validateReportType(this);">
					<label class="text-left">Constituency Wise</label>
				</html:radio>
			</div>
			
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div id="zoneDiv">
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="zone11">
					<label class="text-right labledesign">Zone</label>
					<html:select property="zone" styleId="zone" style="width: 150px;"
						styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?parentCircle='+this.value+'&method=fetchCircle', 'circle');">
						<html:option value=""> Select Zone</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="circle11">
					<label class="text-right labledesign">Circle</label>
					<html:select property="circle" styleId="circle"
						styleClass="cs2 form-control" style="width: 150px;" value=""
						onchange="ajaxFunction('GetFilterValues.to?circle='+this.value+'&method=fetchDivision', 'division');">
						<html:option value=""> Select Circle</html:option>
					</html:select>

				</div>


				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<!-- First Zone and Circle End -->





				<!-- Second District and Division Start -->
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="division11">
					<label class="text-right labledesign">Division</label>
					<html:select property="division" styleId="division"
						style="width: 150px;" styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?subdivision='+this.value+'&method=fetchSubDivision', 'subdivision');ajaxFunction('GetFilterValues.to?divisionIds='+this.value+'&method=fetchSchemeByDivisionId', 'schemeName');
					ajaxFunction('GetFilterValues.to?division='+this.value+'&method=fetchVillageByDivisionId', 'villageId');">
						<html:option value="">Select Division</html:option>

					</html:select>
				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="subdivision11">
					<label class="text-right labledesign">Sub Division</label>
					<html:select property="subdivision" styleId="subdivision"
						style="width: 150px;" styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?subDivisionIds='+this.value+'&method=fetchSchemeBySubDivisionId', 'schemeName');ajaxFunction('GetFilterValues.to?subDivision='+this.value+'&method=fetchVillageBySubDivisionId', 'villageId');">
						<html:option value="">Select Sub Division</html:option>

					</html:select>
				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<!--Second District and Division Start -->
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

			</div>

			<div id="districtDiv">
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="district11">
					<label class="text-right labledesign">District</label>
					<html:select property="district" styleId="district"
						style="width: 150px;" styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');
					ajaxFunction('GetFilterValues.to?division='+this.value+'&method=fetchDivision', 'division');
					">
						<html:option value="">Select District</html:option>

					</html:select>
				</div>


				<!-- Sub Division and Block -->


				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="block11">
					<label class="text-right labledesign">Block</label>
					<html:select property="block" styleId="block"
						styleClass="cs2 form-control" style="width: 150px;" value=""
						onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');
					          ajaxFunction('GetFilterValues.to?subdivision1='+this.value+'&method=fetchScheme', 'schemeName')">
						<html:option value="">Please Select Block</html:option>
					</html:select>

				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<!--  Sub Division and Block -->





				<!-- Constituency and Scheme -->


				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>

			<div id="constituencyDiv">
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="constituency11">
					<label class="text-right labledesign">Constituency</label>
					<html:select property="constituency" styleId="constituency"
						styleClass="cs2 form-control" style="width: 150px;" value=""
						onchange="ajaxFunction('GetFilterValues.to?constituencyId='+this.value+'&method=fetchSchemeByConstituency', 'schemeName');
						ajaxFunction('GetFilterValues.to?constituencyVlg='+this.value+'&method=fetchVillageByConstituency', 'villageId');">
						<html:option value=""> Select </html:option>
					</html:select>

				</div>
				<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>


				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>

			<div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Scheme</label>
					<html:select property="schemeName" styleId="schemeName"
						style="width: 150px;" styleClass="form-control cs5 " value=""
						onchange="if(document.getElementById('villageId').value===''){
							ajaxFunction('GetFilterValues.to?schemeId1='+this.value+'&method=fetchvillageFromScheme', 'villageId');
						}if(document.getElementById('schemeName').value===''){
							checkDropDwnScheme();
						}
						">
						<html:option value="">Select Scheme</html:option>

					</html:select>
				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Village/Habitation</label>
					<html:select property="villageId" styleId="villageId" value=""
						styleClass="ci5 form-control" style="width: 150px;"
						onchange="if(document.getElementById('schemeName').value===''){
							ajaxFunction('GetFilterValues.to?village='+this.value+'&method=fetchSchemeByVillage', 'schemeName');
						}if(document.getElementById('villageId').value===''){
							checkDropDwnVillage();
						}
						">


						<html:option value="">Select Village</html:option>
					</html:select>
				</div>
				
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			<!-- Village -->
		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Parameter
				Selection</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-6 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Select Parameter</label>
				<html:select property="parameter" styleId="parameter"
					style="width: 300px;" styleClass="form-control cs5 " value="">
					<html:option value="">Select Parameter</html:option>

				</html:select>

				<div class="col-lg-2 col-md-2  pull-right"
					style="margin-left: -50px;">
					<a onclick="addParamater();" class="btn btn-info"> <span
						class="glyphicon glyphicon-ok"></span> Add Parameter
					</a>
				</div>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>


			</div>

			<!-- Buttons -->
			<div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-2  ">
					<a onclick="downloadPdf();" class="btn btn-danger"> <span
						class="glyphicon glyphicon-save"></span> Download PDF
					</a>
				</div>
				<div class="col-lg-2 col-md-2  ">
					<a onclick="downloadExcel();" class="btn btn-success"> <span
						class="glyphicon glyphicon-save"></span> Download Excel
					</a>
				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			<!-- Buttons -->

			<div class="row" style="margin-top: 20px; padding: 5px;">
				<div id="paramaterDiv"></div>
			</div>



		</div>
	</div>



</html:form>

<script>
	$(window)
			.load(
					function() {
						
						$('input.float').on('input', function() {
							  this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
							});
						
						 //parameter
						ajaxFunction(
								'waterParameterReportAction.do?method=fetchParameter',
								'parameter');
						de_kyenable(
								true,'zone1@district1@constituency1@zone@circle@division@subdivision@district@block@constituency');
						 de_kyenable(
								true,
								'villageId@schemeName@parameter'); 
					});
	
	
	
	function validateReportType(e){
		
		if(e.value==='zone'){
			hide_ctrl(
					'districtDiv~constituencyDiv',true);
			hide_ctrl('zoneDiv',false);
			document.getElementById('constituency').value='';
			document.getElementById('district').value='';
			document.getElementById('block').value='';
			ajaxFunction(
					'GetFilterValues.to?parentZone=-1&method=fetchZone',
					'zone');
		}if(e.value==='district'){
			hide_ctrl(
					'zoneDiv~constituencyDiv',true);
			hide_ctrl('districtDiv',false);
			ajaxFunction('GetFilterValues.to?districtType=3&method=fetchDistrictByType', 'district');
			document.getElementById('zone').value='';
			document.getElementById('circle').value='';
			document.getElementById('division').value='';
			document.getElementById('subdivision').value='';
			document.getElementById('constituency').value='';
		}if(e.value==='constituency'){
			hide_ctrl(
					'districtDiv~zoneDiv',true);
			hide_ctrl('constituencyDiv',false);
			ajaxFunction('GetFilterValues.to?constituencyType=7&method=fetchConstituencyByType', 'constituency');
			document.getElementById('zone').value='';
			document.getElementById('circle').value='';
			document.getElementById('division').value='';
			document.getElementById('subdivision').value='';
			document.getElementById('district').value='';
			document.getElementById('block').value='';
		}
		
	}
	
	function checkDropDwnScheme(){
		var scheme=document.getElementById('schemeName').value;
		var villageId=document.getElementById('villageId').value;
		if(scheme===''){
			if(document.getElementById('subdivision').value===''){
				if(document.getElementById('division').value!=''){
					ajaxFunction('GetFilterValues.to?divisionIds='+document.getElementById('division').value+'&method=fetchSchemeByDivisionId', 'schemeName');
					ajaxFunction('GetFilterValues.to?division='+document.getElementById('division').value+'&method=fetchVillageByDivisionId', 'villageId');
				}
			}else{
				alert('insie')
				ajaxFunction('GetFilterValues.to?subDivisionIds='+document.getElementById('subdivision').value+'&method=fetchSchemeBySubDivisionId', 'schemeName');
				ajaxFunction('GetFilterValues.to?subDivision='+document.getElementById('subdivision').value+'&method=fetchVillageBySubDivisionId', 'villageId');
			}
			if(document.getElementById('block').value!=''){
				 ajaxFunction('GetFilterValues.to?subdivision1='+document.getElementById('block').value+'&method=fetchScheme', 'schemeName');
				ajaxFunction('GetFilterValues.to?block='+document.getElementById('block').value+'&method=fetchVillage', 'villageId');
			}if(document.getElementById('constituency').value!=''){
				ajaxFunction('GetFilterValues.to?constituencyId='+document.getElementById('constituency').value+'&method=fetchSchemeByConstituency', 'schemeName')
				ajaxFunction('GetFilterValues.to?constituencyVlg='+document.getElementById('constituency').value+'&method=fetchVillageByConstituency', 'villageId');
			}
		}
	}
	function checkDropDwnVillage(){
		var villageId=document.getElementById('villageId').value;
		if(villageId===''){
			if(document.getElementById('subdivision').value===''){
				if(document.getElementById('division').value!=''){
					ajaxFunction('GetFilterValues.to?divisionIds='+document.getElementById('division').value+'&method=fetchSchemeByDivisionId', 'schemeName');
					ajaxFunction('GetFilterValues.to?division='+document.getElementById('division').value+'&method=fetchVillageByDivisionId', 'villageId');
				}
			}else{
				ajaxFunction('GetFilterValues.to?subDivisionIds='+document.getElementById('subdivision').value+'&method=fetchSchemeBySubDivisionId', 'schemeName');
				ajaxFunction('GetFilterValues.to?subDivision='+document.getElementById('subdivision').value+'&method=fetchVillageBySubDivisionId', 'villageId');
			}
			if(document.getElementById('block').value!=''){
				 ajaxFunction('GetFilterValues.to?subdivision1='+document.getElementById('block').value+'&method=fetchScheme', 'schemeName');
				 ajaxFunction('GetFilterValues.to?block='+document.getElementById('block').value+'&method=fetchVillage', 'villageId');
			}if(document.getElementById('constituency').value!=''){
				ajaxFunction('GetFilterValues.to?constituencyId='+document.getElementById('constituency').value+'&method=fetchSchemeByConstituency', 'schemeName');
				ajaxFunction('GetFilterValues.to?constituencyVlg='+document.getElementById('constituency').value+'&method=fetchVillageByConstituency', 'villageId');
			}
		}
	}
</script>

</body>

</html:html>