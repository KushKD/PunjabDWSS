<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<script type="text/javascript">
var isPDF=false;
hide_ctrl('modalSwap',true);
	function de_view() {
		document.agendaReportsForm.action = "agendaReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=PMMRPT001";
		document.agendaReportsForm.submit();
	}
	function de_filePrint() {
			document.committedLiabilityReportForm.action = "agendaReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=PMMRPT001";
			document.agendaReportsForm.submit();		
	}
	function de_filePDF() {
		isPDF=true;
		
		if(document.getElementById("selectReport2").checked||document.getElementById("selectReport3").checked){
				hide_ctrl('modalSwap',false);
				document.getElementById("swap_ok").focus();
		}
		else{		
			document.agendaReportsForm.action = "agendaReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=PMMRPT001";
			document.agendaReportsForm.submit();
		}
	}
	function de_fileExcel() {
		
		if(document.getElementById("selectReport2").checked||document.getElementById("selectReport3").checked){
				hide_ctrl('modalSwap',false);
				document.getElementById("swap_ok").focus();
		}
		else{		
			document.agendaReportsForm.action = "agendaReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=PMMRPT001";
			document.agendaReportsForm.submit();		
		}	
	}
	function de_file_swap(){
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		
		document.agendaReportsForm.action = "agendaReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=PMMRPT001";
        document.agendaReportsForm.submit();              				
	}

</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
--></head>
<body>

		<html:form action="agendaReportsAction">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		<table>	<tr>	
						<td>Select Zone</td>
				<td>
					<html:radio property="selectZone" title="Selection" value="S" styleId="zoneSelection">
					Selection</html:radio>
					<html:radio property="selectZone" title="All" value="A" styleId="zoneAll">
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="zoneId" styleId="zoneId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?zoneId='+this.value, 'circleId');
					triggerEvent(document.getElementById('circleId'), 'onchange');selectRadioZone();">
						<html:option value="">Select Zone</html:option>
						<html:options collection="zones" labelProperty="label" property="value"></html:options>						
					</html:select>
				</td>
				</tr>	
			<tr>
				<td>Select Circle</td>
				<td >
					<html:radio property="selectCircle" title="Selection" value="S" styleId="circleSelection" >
					Selection</html:radio>
					<html:radio property="selectCircle" title="All" value="A" styleId="circleAll" >
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="circleId" styleId="circleId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?circleId='+this.value, 'districtId');selectRadioCircle();">
						
					</html:select>
				</td>
			</tr>	
			<tr>
			<td>Select District</td>
				<td>
					<html:radio property="selectDistrict" title="Selection" value="S" styleId="districtSelection" >
					Selection</html:radio>
					<html:radio property="selectDistrict" title="All" value="A" styleId="districtAll">
					All</html:radio>
				   </td>
				<td colspan="2">
					<html:select property="districtId" styleId="districtId" style="width: 255px" styleClass="ci5" onchange="selectRadioDistrict();">
						
					</html:select>
				</td>
			</tr>	
			
			<tr>	
				<td>Select Program</td>
				<td>
					<html:radio property="selectProgram" title="Selection" value="S" styleId="programSelection">Selection</html:radio>
					<html:radio property="selectProgram" title="All" value="A" styleId="programAll">All</html:radio>		
				</td>
				<td colspan="2">
					<html:select property="programId" styleId="programId" style="width: 255px" styleClass="ci5" onchange="selectRadioProgram();">
						<html:option value="">Select Program</html:option>
						<html:options collection="programs" labelProperty="label" property="value"></html:options>
					</html:select>
				</td>
			</tr>
			<tr>
			<td nowrap>Report for Month   </td>
						<td><html:select property="monthId">
						<option value="0">Select</option>
						<option value="01">JAN</option>
						<option value="02">FEB</option>
						<option value="03">MAR</option>
						<option value="04">APR</option>
						<option value="05">MAY</option>
						<option value="06">JUN</option>
						<option value="07">JUL</option>
						<option value="08">AUG</option>
						<option value="09">SEP</option>
						<option value="10">OCT</option>
						<option value="11">NOV</option>
						<option value="12">DEC</option>
						</html:select></td>
						<td nowrap>Financial Year</td>
						<td><html:select property ="finYearId">
						<option value="0">Select</option>
						<option value="1">2007-08</option>
						<option value="2">2008-09</option>
						<option value="3">2009-10</option>
						<option value="4">2010-11</option>
						<option value="5">2011-12</option>
						<option value="6">2012-13</option>
						<option value="7">2013-14</option>
						<option value="8">2014-15</option>
						<option value="9">2015-16</option>
						<option value="10">2016-17</option>
						
						</html:select></td>
			</tr>
			
		</table>
		</center>
		</fieldset>
			
		<fieldset>
		<legend> Reports</legend>
		<center>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" value="AGDRPT001_1">Completion of Commissioned Schemes</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport2" value="AGDRPT001_4">Division wise performance under (SWAP IDA/SWAP NON-IDA/NON-SWAP)</html:radio>  </td></tr><!--
			<tr><td colspan="3"> <html:radio property="selectReport" value="AGDRPT001_3">Performance of  ARWSP (Normal ) SWAp Mode program</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="AGDRPT001_4">Performance of ARWSP Quality Affected – Achievement  Schemes / Villages  program</html:radio>  </td></tr>
			
			
			<tr><td colspan="3"> <html:radio property="selectReport" value="AGDRPT001_5">Performance of NABARD program</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="AGDRPT001_6">Performance of PIDB/ BRGF program</html:radio>  </td></tr>
			-->
			
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport3" value="AGDRPT001_7">Utilization of Funds under All Programs</html:radio>  </td></tr>
				<tr>
				<td colspan="3"> 
					<html:radio property="selectReport"  value="AGDRPT001_8">Review of Progress of World Bank Aided Project(Pilot)</html:radio>
				</td>
				
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_11">Testing of water samples by Mobile water testing laboratory</html:radio></td>
			</tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="AGDRPT001_9">Status of Private Water Connections for the Commissioned Villages Under World Bank Project</html:radio></td></tr>
			
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="PMMRPT001_4">Scheme-wise detail of villages</html:radio>  </td></tr>
			<tr>
				<td colspan="3"> 
					<html:radio property="selectReport"  value="AGDRPT001_8">Review of Progress of World Bank Aided Project(Pilot)</html:radio>
				</td>
				
			</tr>
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="AGDRPT001_10">Review of Progress of World Bank Aided Project(Batch-I)</html:radio></td></tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_11">Testing of water samples by Mobile water testing laboratory</html:radio></td>
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_12">Status of Commissioning of Villages</html:radio></td>
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_13">Status of private connections for the villages commissioned under SWAp mode </html:radio></td>
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_13A">Status of private connections for the villages commissioned under  Nabard</html:radio></td>
			</tr>
			
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="AGDRPT001_14">Status of Schemes  where work stand allotted, work is in progress or under process of allotment & commissioning of the schemes of each division as per the Works Programme</html:radio></td>
			</tr>
			
			--><tr><td colspan="3"> <html:radio property="selectReport"  value="AGDRPT001_15">Coverage of the villages as per Works Programme under various programmes</html:radio></td></tr>
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_17">Abstract of Constituency wise Planning </html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_18">Detail of total Schemes Commissioned</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_19">Program Management: Table-2c</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_20">Result Indicator for Community Development Support: Table-3</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_21">Result Indicator for Community Development Support: Table-3a</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_22">Result Indicator for Community Development Support: Table-3b</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_24">Result Indicator for Infrastructure Development Support: Table-4</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_25">Result Indicator for Infrastructure Development Support: Table-4a</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_26">Result Indicator for Infrastructure Development Support: Table-4b</html:radio></td></tr>-->
			<!--<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_27">Operational Sustainability</html:radio></td></tr>-->
			</table>
			</center>
			</fieldset>
				<div id="modalSwap" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
										<tr>
												<td>Please select SWAp/NON-SWAp</td>
												<td>
												<html:select property="swap" styleId="swap" styleClass="cs3" >
														<html:option value="A">All</html:option>
														<html:option value="NONSWAP">NONSWAP</html:option>
														<html:option value="SWAP-IDA">SWAP-IDA</html:option>
														<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
													</html:select>
												</td>	
										</tr>										
										</table>  		
										<input type="button" value="OK" id="swap_ok" onClick="de_file_swap();hide_ctrl('modalSwap',true);return false;">
										<input type="button" value="Cancel" id="swap_cancel" onClick="hide_ctrl('modalSwap',true);return false;">
									</div>
		</html:form>
				<script>
		function selectRadioZone(){
					var zone = document.getElementById('zoneId').value;
				//	alert(zone);
					if(zone!=""){
				//		alert("inside");
						document.getElementById('zoneSelection').checked=true;
					}else{
						document.getElementById('zoneAll').checked=true;
						document.getElementById('circleAll').checked=true;
						document.getElementById('districtAll').checked=true;
						document.getElementById('programAll').checked=true;
					}
		}

		function selectRadioCircle(){
					var circle = document.getElementById('circleId').value;
					if(circle!=""){
							document.getElementById('circleSelection').checked=true;
					}else{
							document.getElementById('circleAll').checked=true;
							document.getElementById('districtAll').checked=true;
							document.getElementById('programAll').checked=true;
					}
		}

		function selectRadioDistrict(){
					var district = document.getElementById('districtId').value;
					if(district!=""){
							document.getElementById('districtSelection').checked=true;
					}else{
							document.getElementById('districtAll').checked=true;
							document.getElementById('programAll').checked=true;
					}
		}


		function selectRadioProgram(){
					var program = document.getElementById('programId').value;
					if(program!=""){
							document.getElementById('programSelection').checked=true;
					}else{
							document.getElementById('programAll').checked=true;
					}
		}
		</script>
	

</body>
</html>