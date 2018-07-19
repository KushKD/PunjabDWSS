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
		document.worksProgrammeReportsForm.action = "worksProgrammeReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=PMMRPT001";
		document.worksProgrammeReportsForm.submit();
	}
	function de_filePrint() {
			document.committedLiabilityReportForm.action = "worksProgrammeReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=PMMRPT001";
			document.worksProgrammeReportsForm.submit();		
	}
	function de_filePDF() {
		isPDF=true;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
    	var periodReport=habitationDataReport();
    	
    	var listSwap='WRKRPT001_2,WRKRPT001_4,WRKRPT001_6,PMMRPT001_4,WRKRPT001_8,WRKRPT001_10,WRKRPT001_17,WRKRPT001_18,WRKRPT001_19';
    	if (inList(periodReport,listSwap)){
			hide_ctrl('modalSwap',false);
			document.getElementById("swap_ok").focus();
		}
    	else{
    		document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			document.worksProgrammeReportsForm.action = "worksProgrammeReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=PMMRPT001";
			document.worksProgrammeReportsForm.submit();
    	}
	}
	function de_fileExcel() {
		isPDF=false;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
    	var periodReport=habitationDataReport();
    	
    	var listSwap='WRKRPT001_2,WRKRPT001_4,WRKRPT001_6,PMMRPT001_4,WRKRPT001_8,WRKRPT001_10,WRKRPT001_17,WRKRPT001_18,WRKRPT001_19';
    	if (inList(periodReport,listSwap)){
			hide_ctrl('modalSwap',false);
			document.getElementById("swap_ok").focus();
		}
    	else{
		document.worksProgrammeReportsForm.action = "worksProgrammeReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=PMMRPT001";
		document.worksProgrammeReportsForm.submit();		
    	}
	}
	function de_file_swap(){
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		document.getElementById("toDate").value=today;
		document.worksProgrammeReportsForm.action = "worksProgrammeReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=PMMRPT001";
		document.worksProgrammeReportsForm.submit();         				
	}
	function disable_enable(ele){
		var select=['swap_nonswap_abstract','swap_nonswap_detail'];
		//if(ele=='') return;
		for (var i=0; i<select.length;i++){
			if(ele==select[i]) document.getElementById(ele).disabled=false;
			else {
				document.getElementById(select[i]).disabled=true;
				document.getElementById(select[i]).selectedIndex=0;
			}
		}
	}
 
	function habitationDataReport(){
		var val="";
		var swap_nonswap_abstract=document.getElementById("swap_nonswap_abstract").value;
		var swap_nonswap_detail=document.getElementById("swap_nonswap_detail").value;
		
		if(swap_nonswap_abstract=="ABWRK_1" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_1";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_2" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_3";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_3" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_5";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_4" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_7";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_5" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_9";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_6" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_11";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_7" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_12";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_8" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_13";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_9" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_14";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_10" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_15";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_11" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_16";//alert("In2: "+val);}
		}	
		if(swap_nonswap_abstract=="ABWRK_12" && document.getElementById("selectReport1").checked){
			 val="WRKRPT001_20";//alert("In2: "+val);}
		}	
		
		
		
		if(swap_nonswap_detail=="DTWRK_1" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_2";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_2" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_4";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_3" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_6";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_4" && document.getElementById("selectReport2").checked){
			 val="PMMRPT001_4";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_5" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_8";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_6" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_10";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_7" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_17";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_8" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_18";//alert("In2: "+val);}
		}	
		if(swap_nonswap_detail=="DTWRK_9" && document.getElementById("selectReport2").checked){
			 val="WRKRPT001_19";//alert("In2: "+val);}
		}	
		
		return val;
	}
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
--></head>
<body>

		<html:form action="worksProgrammeReportsAction">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		
		<table>	<tr>	
						<td>Select Zone</td>
				<td>
					<html:radio property="selectZone" title="Selection" value="S" styleId="selectZone">
					Selection</html:radio>
					<html:radio property="selectZone" title="All" value="A" styleId="selectZone">
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="zoneId" styleId="zoneId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?zoneId='+this.value, 'circleId');
					triggerEvent(document.getElementById('circleId'), 'onchange');">
						<html:option value="">Select Zone</html:option>
						<html:options collection="zones" labelProperty="label" property="value"></html:options>						
					</html:select>
				</td>
				</tr>	
			<tr>
				<td>Select Circle</td>
				<td >
					<html:radio property="selectCircle" title="Selection" value="S" styleId="selectCircle" >
					Selection</html:radio>
					<html:radio property="selectCircle" title="All" value="A" styleId="selectCircle" >
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="circleId" styleId="circleId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?circleId='+this.value, 'districtId');">
						
					</html:select>
				</td>
			</tr>	
			<tr>
			<td>Select District</td>
				<td>
					<html:radio property="selectDistrict" title="Selection" value="S" styleId="selectDistrict" >
					Selection</html:radio>
					<html:radio property="selectDistrict" title="All" value="A" styleId="selectDistrict" >
					All</html:radio>
				   </td>
				<td colspan="2">
					<html:select property="districtId" styleId="districtId" style="width: 255px" styleClass="ci5">
						
					</html:select>
				</td>
			</tr>	
			
			<tr>	
				<td>Select Program</td>
				<td>
					<html:radio property="selectProgram" title="Selection" value="S" styleId="selectProgram">Selection</html:radio>
					<html:radio property="selectProgram" title="All" value="A" styleId="selectProgram">All</html:radio>		
				</td>
				<td colspan="2">
					<html:select property="programId" styleId="programId" style="width: 255px" styleClass="ci5">
						<html:options collection="programs" labelProperty="label" property="value"></html:options>
					</html:select>
				</td>
			</tr>
			<tr>
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
							<legend>Reports-SWAp / Non-SWAp Program</legend>
							<center>
							<table>
								<tr>
									<td>
										<html:radio property="selectReport" value="swap_nonswap_abstract" onclick="disable_enable('swap_nonswap_abstract');" styleId="selectReport1" > Abstracts</html:radio> 
									</td>
									<td>
										<select id="swap_nonswap_abstract" class="cs3" onchange="document.getElementById('selectReport1').value = habitationDataReport()" >
											<option value="">Select Report</option>
											<option value="ABWRK_1" >Abstract village commissioned where work to be completed</option>
											<option value="ABWRK_2">Abstract of Villages in Progress  but to be Commissioned</option>
											<option value="ABWRK_3">Abstract of Schemes approved but work yet to be physically started</option>
											<option value="ABWRK_4" >Abstract of Schemes whose estimates are under process of Admn. Approval and to be Commissioned</option>
											<option value="ABWRK_5" >Abstract of Schemes whose estimates are yet to be submitted for administrative approval to SE /CE/Govt</option>
											<option value="ABWRK_6" >Division/ District Wise Abstract of Work Programme</option>
											<option value="ABWRK_7" >Circle Wise Abstract of Work Programme</option>
											<option value="ABWRK_8" >Wing Wise Abstract of Work Programme</option>
											<option value="ABWRK_9">Abstract of Work Programme (SWAp && Non SWAp)(Abstract Performa wise)</option>
											<option value="ABWRK_10">Abstract of Work Programme (SWAp && Non SWAp)(Abstract Circle Wise)</option>
											<option value="ABWRK_11">Abstract of Schemes where works under Sustainability will be carried out</option>
											<option value="ABWRK_12">Abstract of Constituency wise Planning</option> 
											
										</select>
									</td>
									<td><html:radio property="selectReport" value="swap_nonswap_detail" onclick="disable_enable('swap_nonswap_detail');" styleId="selectReport2"> Details</html:radio></td>
									<td>
										
											<select id="swap_nonswap_detail" class="cs3" onchange="document.getElementById('selectReport2').value = habitationDataReport()" disabled="true">
												<option value="">Select Report</option>
												<option value="DTWRK_1">Scheme wise detail of Schemes already commissioned but work to be completed</option>
												<option value="DTWRK_2">Scheme wise detail of Schemes in progress,but to be commissioned</option>
												<option value="DTWRK_3" >Details of Schemes approved but work yet to be physically started</option>
												<option value="DTWRK_4">Scheme-wise detail of villages </option>
												<option value="DTWRK_5" >Details of Schemes whose estimates are under process of Admn. Approval and to be Commissioned</option>
												<option value="DTWRK_6" >Details of SchemeS whose estimates are yet to be submitted for administrative approval to SE /CE/Govt</option>
												<option value="DTWRK_7" >Detail of Yet to be approved schemes where works under sustainability will be carried out</option>
												<option value="DTWRK_8" >Total schemes Commissioned </option>
												<option value="DTWRK_9">Detail of total Schemes Commissioned</option>
												
											</select>
										
									</td>
                            </tr> 	
							<tr id="td1" style="visibility: hidden;">
								<td>Period
									
									<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod_s">Selection</html:radio>
									<html:radio property="selectPeriod" title="All" value="A" styleId="selectPeriod_a">All</html:radio>
								</td>		
								<td>From:&nbsp;&nbsp;&nbsp;
									<html:text property="fromDate" styleId="fromDate"	styleClass="ci3"></html:text> 
									<!--<input class=ci4 type=button onclick="c1.innerpopup('fromDate','calendar_frame');" value="..." />-->
								</td>
								<td>To:&nbsp;&nbsp;&nbsp;
									<html:text property="toDate" styleId="toDate"	styleClass="ci3"></html:text> 
									<!--<input class=ci4 type=button onclick="c1.innerpopup('toDate','calendar_frame');" value="..." />-->
								</td>  					
							</tr>
							</table>
							</center>	
				
						</fieldset>
						<div id="modalSwap" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
										<tr>
												<td>Please select SWAp/NON-SWAp</td>
												<td>
												<html:select property="swap" styleId="swap" styleClass="cs3" style="width: 150px">
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
			
		<!--<fieldset>
		<legend> Reports</legend>
		<center>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_1">Abstract village commissioned where work to be completed</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_2">Scheme wise detail of Schemes already commissioned but work to be completed</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_3">Abstract of Villages in Progress  but to be Commissioned </html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_4">Scheme wise detail of Schemes in progress,but to be commissioned</html:radio>  </td></tr>
			
			
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_5">Abstract of Schemes approved but work yet to be physically started</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" value="WRKRPT001_6">Details of Schemes approved but work yet to be physically started</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_7">Abstract of Schemes whose estimates are under process of Admn. Approval and to be Commissioned</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="PMMRPT001_4">Scheme-wise detail of villages</html:radio>  </td></tr>
			<tr>
				<td colspan="3"> 
					<html:radio property="selectReport"  value="WRKRPT001_8">Details of Schemes whose estimates are under process of Admn. Approval and to be Commissioned</html:radio>
				</td>
				
			</tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_9">Abstract of Schemes whose estimates are yet to be submitted for administrative approval to SE /CE/Govt</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_10">Details of SchemeS whose estimates are yet to be submitted for administrative approval to SE /CE/Govt</html:radio></td></tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="WRKRPT001_11">Division/ District Wise Abstract of Work Programme </html:radio></td>
			</tr>
			
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="WRKRPT001_12">Circle Wise Abstract of Work Programme </html:radio></td>
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="WRKRPT001_13">Wing Wise Abstract of Work Programme </html:radio></td>
			</tr>
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="WRKRPT001_14">Abstract of Work Programme (SWAp && Non SWAp)(Abstract Performa wise)</html:radio></td>
			</tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_15">Abstract of Work Programme (SWAp && Non SWAp)(Abstract Circle Wise)</html:radio></td></tr>
			
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_16">Abstract of Schemes where works under Sustainability will be carried out </html:radio></td></tr>
			
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_17">Detail of Yet to be approved schemes where works under sustainability will be carried out </html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_18">Total schemes Commissioned </html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_19">Detail of total Schemes Commissioned</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_20">Abstract of Constituency wise Planning </html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_19">Program Management: Table-2c</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_20">Result Indicator for Community Development Support: Table-3</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_21">Result Indicator for Community Development Support: Table-3a</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_22">Result Indicator for Community Development Support: Table-3b</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_24">Result Indicator for Infrastructure Development Support: Table-4</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_25">Result Indicator for Infrastructure Development Support: Table-4a</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_26">Result Indicator for Infrastructure Development Support: Table-4b</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="WRKRPT001_27">Operational Sustainability</html:radio></td></tr>
			</table>
			</center>
			</fieldset>
		--></html:form>
	

</body>
</html>