<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>

-->
<script type="text/javascript">
	function de_find() {
		document.villageDetailsForm.action = "villageDetailsAction.do?method=find&d__mode="+ d__mode+"&menuId=PMM001";
		document.villageDetailsForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			 var result=true;
			 if(result==true){
			 document.villageDetailsForm.action = "villageDetailsAction.do?method=update&d__mode="+ d__mode+"&menuId=PMM001";
			document.villageDetailsForm.submit();
			}
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.villageDetailsForm.action = "villageDetailsAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM001";
			document.villageDetailsForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.villageDetailsForm.action = "villageDetailsAction.do?method=post&d__mode="+ d__mode+"&menuId=PMM001";
			document.villageDetailsForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.villageDetailsForm.action = "villageDetailsAction.do?method=save&d__mode="
					+ d__mode+"&menuId=PMM001";
			document.villageDetailsForm.submit();
		}
	}
 
</script>

<link rel="stylesheet" href="css/tab_pane.css" type="text/css"
	media="screen" />
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body>
<html:form action="villageDetailsAction" method="post"
	styleId="villageDetailsForm">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 42px;"><html:errors
			bundle="pmm" /></div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" bundle="pmm" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>

	<fieldset>
	<center><label style="font-size: 20px; font-family: times;">Screen
	information (water connection) should be entered by 7th of following
	month.</label></center>
	</fieldset>
	<%@include file="../JSPF/blockVillageFilter.jspf"%>

	<div id="tabbed_box_1" class="tabbed_box">
	<div class="tabbed_area">
	<ul class="tabs">
		<li><a href="javascript:tabSwitch_2(1, 6, 'tab_', 'content_');"
			id="tab_1" class="active">Water Connections</a></li>
		<li><a href="javascript:tabSwitch_2(2, 6, 'tab_', 'content_');"
			id="tab_2">Distribution Tariff</a></li>
		<li><a href="javascript:tabSwitch_2(3, 6, 'tab_', 'content_');"
			id="tab_3">Enter Households</a></li>
		<li><a href="javascript:tabSwitch_2(4, 6, 'tab_', 'content_');"
			id="tab_4">Population</a></li>
		<li><a href="javascript:tabSwitch_2(5, 6, 'tab_', 'content_');"
			id="tab_5">Sewer Connection</a></li>
		<li><a href="javascript:tabSwitch_2(6, 6, 'tab_', 'content_');"
			id="tab_6">NC/PC Status</a></li>
	</ul>
	<div id="content_1" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
					<td nowrap>No Of Water Connection</td>
					<td><input type="text" id="noOfWaterConnectionId" Class="ci5" name="noOfWaterConnectionId"
						onkeypress="return validateKey(event,	this,'9@20')"></td>
					<td nowrap>No Of standposts</td>
					<td><input type="text" id="noOfStandpostId" Class="ci5" name="noOfStandpostId"
						onkeypress="return validateKey(event,	this,'9@20')"></td>
					<td nowrap>As On (Date)</td>
					<td><input type="text" id="asOnDateId" readonly="readonly" name="asOnDateId"/></td>
					<td><input class=ci4 type=button
						onclick="c1.innerpopup('asOnDateId','calendar_frame');"
						value="..." /></td>
				<tr>
					<td colspan="6"><b><font color="red"> NOTE: Please
					do not enter during the month data, only enter total number of
					water connections and standposts. </font></b></td>
				</tr>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="waterConnectionGrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="connectionId" title="Connection Id"
					mode="N,N,N" />
				<layout:datagridColumn property="noOfWaterConnection"
					title="No Of Water Connection" mode="I,I,I" />
				<layout:datagridColumn property="noOfStandpost"
					title="No Of standposts" mode="I,I,I" />
				<layout:datagridColumn property="asOnDate" title="As on (Date)"
					mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="return ValidateBlankFieldsForWaterConnections();"
				width="20px" /> <br>
			<img src="images/delete.png" id="delconn"
				onclick="StrutsLayout.setDatagridLineState('waterConnectionGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</div>
	<div id="content_2" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
					<td nowrap>Tariff Rate</td>
					<td><input type="text" id="tariffId" Class="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></td>
					<td nowrap>As On (Date)</td>
					<td><input type="text" id="asOnDateId2" readonly="readonly"></td>
					<td><input class=ci4 type=button onclick="c1.innerpopup('asOnDateId2','calendar_frame');" value="..."/></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="tariffRateGrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="tariffId" title="Id" mode="N,N,N" />
				<layout:datagridColumn property="tariffRate"
					title="Distribution Tariff Rate" />
				<layout:datagridColumn property="asOnDate" title="As on (Date)"
					mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="return ValidateBlankFieldsForDistributionTariff();"
				width="20px" /><br>
			<img src="images/delete.png" id="deltrf"
				onclick="StrutsLayout.setDatagridLineState('tariffRateGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</div>

	<div id="content_3" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
					<td nowrap>No of GC HouseHold</td>
						<td><input type="text" id="GCHouseHoldId" Class="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20')"></td>
						<td nowrap>No Of SC HouseHold</td>
						<td><input type="text" id="SCHouseHoldId" Class="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20')"></td>
						<td nowrap>As On (Date)</td>
						<td><input type="text" id="asOnDateId3" Class="ci3" readonly="readonly"/></td>
						<td><input class=ci4 type=button onclick="c1.innerpopup('asOnDateId3','calendar_frame');" value="..."/></td>
					
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="houseHoldGrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="householdId" title="Household Id"
					mode="N,N,N" />
				<layout:datagridColumn property="gcHouseholds"
					title="No of GC Household" mode="I,I,I" />
				<layout:datagridColumn property="scHouseholds"
					title="No of SC Household" mode="I,I,I" />
				<layout:datagridColumn property="asOnDate" title="As on (Date)"
					mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png" id="hhaddimg"
					onclick="return ValidateBlankFieldsForHouesHold();"
					width="20px" /> <br>
				<img src="images/delete.png" id="hhdelimg"
					onclick="StrutsLayout.setDatagridLineState('houseHoldGrid', 'removed')" width="20px" /></td>
		
		</tr>
	</table>
	</div>
	<div id="content_4" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
						<td nowrap>General</td>
						<td><input type="text" id="generalId" Class="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20')"></td>
						<td nowrap>SC</td>
						<td><input type="text" id="scId" Class="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20')"></td>
						<td nowrap>ST</td>
						<td><input type="text" id="stId" Class="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20')"></td>
					</tr>
					<tr>
						<td nowrap>Population Year</td>
						<td><input type="text" id="populationYearId" Class="ci5" onkeypress="return validateKey(event,	this,'9@5')"></td>
						<td nowrap>As On (Date)</td>
						<td><input type="text" id="asOnDateId4"  Class="ci3" readonly="readonly"/></td>
						<td><input class=ci4 type=button onclick="c1.innerpopup('asOnDateId4','calendar_frame');" value="..."/></td>
				
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="populationGrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="populationId" title="Population Id"
					mode="N,N,N" />
				<layout:datagridColumn property="genPopulation" title="General"
					mode="I,I,I" />
				<layout:datagridColumn property="scPopulation" title="SC"
					mode="I,I,I" />
				<layout:datagridColumn property="stPopulation" title="ST"
					mode="I,I,I" />
				<layout:datagridColumn property="populationYear"
					title="Population Year" mode="I,I,I" />
				<layout:datagridColumn property="asOnDate" title="As on (Date)"
					mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png" id="ppladdimg"
					onclick="return ValidateBlankFieldsForPopulation();"
					width="20px" /> <br>
				<img src="images/delete.png" id="ppldelimg"
					onclick="StrutsLayout.setDatagridLineState('populationGrid', 'removed')" width="20px" /></td>
		
		</tr>
	</table>
	</div>
	<div id="content_5" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
					<td nowrap>Sewerage Tariff Rate</td>
					<td><input type="text" id="sewerageTariffId"
						onkeypress="return validateKey(event,	this,'9@20@2')"></td>
					<td nowrap>As On (Date)</td>
					<td><input type="text" id="asOnDateId5" readonly="readonly" /></td>
					<td><!--<input class=ci4 type=button onclick="c1.innerpopup('asOnDateId5','calendar_frame');" value="..."/>--></td>
					<td nowrap>Sewerage Connection</td>
					<td><input type="text" id="sewConnection"
						onkeypress="return validateKey(event,	this,'9@20@2')"></td>

				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="sewerageGrid" model="datagrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="sewerageId" title="Sewerage Id" 
					mode="N,N,N" />
				<layout:datagridColumn property="tariffRate"
					title="Sewerage Tariff Rate" mode="I,I,I"/>
				<layout:datagridColumn property="asOnDate" title="As on (Date)"
					mode="I,I,I" />
				<layout:datagridColumn property="sewConnection"
					title="Sewerage Connection" mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png" id="sewaddimg"
				onclick="return ValidateBlankFieldsForSewerageTariff();"
				width="20px" /> <br>
			<img src="images/delete.png" id="sewdelimg"
				onclick="StrutsLayout.setDatagridLineState('sewerageGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</div>
	<div id="content_6" class="content">
	<table>
		<tr>
			<td colspan="1">
			<table>
				<tr>
					<td nowrap>NC/PC Status</td>
					<td><select id="statusId">
						<option value="NC">NC</option>
						<option value="PC">PC</option>
						<option value="FC">FC</option>
					</select></td>
					<td nowrap>As on Month</td>
					<td><select id="monthId">
						<option value="04">APR</option>
					</select></td>
					<td nowrap>As on Year</td>
					<td><select id="yearId">
						<option value="2013">2013</option>
					</select></td>

				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="ncPcStatusDatagrid" model="datagrid"
				selectionAllowed="true" multipleSelectionAllowed="false"
				model="datagrid">
				<layout:datagridColumn property="id" title="Id" mode="N,N,N" />
				<layout:datagridColumn property="ncPcStatus" title="Village Status"
					mode="I,I,I" />
				<layout:datagridColumn property="monthOfStatus"
					title="As on (Month)" mode="I,I,I" />
				<layout:datagridColumn property="yearOfStatus" title="As on (Year)"
					mode="I,I,I" />
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png" id="ncpcaddimg"
					onclick="StrutsLayout.addDatagridLine('ncPcStatusDatagrid','statusId~monthId~yearId')"
					width="20px"  /> <br>
				<img src="images/delete.png" id="ncpcdelimg"
					onclick="StrutsLayout.setDatagridLineState('ncPcStatusDatagrid', 'removed')" width="20px" />
					
					</td>
		</tr>
	</table>
	</div>
	</div>
	</div>

	<body>
	<script language="javascript">
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
	</script>
	<script language="javascript">
			function ValidateBlankFieldsForWaterConnections(){
				
				var noOfWaterConnectionId = document.getElementById('noOfWaterConnectionId').value;
				if(noOfWaterConnectionId == ''){
					alert("Please Enter Number of Water Connections");
					return false;
				}
				
				var noOfStandpostId = document.getElementById('noOfStandpostId').value;
				if(noOfStandpostId == ''){
					alert("Please Enter Number of Stand Pots");
					return false;
				}
				
				var asOnDateId = document.getElementById('asOnDateId').value;
				if(asOnDateId == ''){
					alert("Please Enter As On Date");
					return false;
				}
				/* else{
					var d1 = document.getElementById("asOnDateId").value;
					
					var d2 = new Date();
					
					 var x = d1.split("-");
					 var dt1  = d2.getDate(); 
					 var mon1 = d2.getMonth()+1; 
					 var yr1  = d2.getFullYear(); 
					 var dt2 = 7;
					 var mon2 = d2.getMonth()+1;
					 var yr2 = d2.getFullYear();
						d2.setDate(1);
					 var dt3 = d2.getDate();
					 var mon3 = d2.getMonth()+1;
					 var yr3 = d2.getFullYear();
					 var dt4 = d2.getDate();
					 var mon4 = d2.getMonth()+1;
					 var yr4 = d2.getFullYear();					 
					
					var asOnDate = new Date(x[2],(x[1]-1),x[0]);
					//alert(asOnDate);
					var  prDate= new Date(yr1, mon1-1, dt1+1);
					var tenDate = new Date(yr2,mon2-1,dt2);
					var tendate2=new Date(yr2,mon2-1,dt2+1);
					var todaydate = new Date(yr1, mon1-1, dt1);
					
					var fcMonthDate = new Date(yr3, mon3-1,dt3);
					var flMonthDate = new Date(yr4,mon4-2,dt4);
					
//rohit
					
				
					if(prDate<=asOnDate){
						alert("As On Date should be before today's Date");
						return false;
					}	
					else if((asOnDate>=flMonthDate) && (asOnDate<fcMonthDate) && (todaydate<=tenDate))
						{
												
						StrutsLayout.addDatagridLine('waterConnectionGrid','noOfWaterConnectionId~noOfStandpostId~asOnDateId');
						return true;
						} 
						
					else if((asOnDate>=flMonthDate)  && (asOnDate<fcMonthDate))
						{
			
						alert('Entry in Previous Date is Not Allowed..');
						return false;
						}
					else if((asOnDate>=fcMonthDate) && (asOnDate<prDate))
						{
					
						
						StrutsLayout.addDatagridLine('waterConnectionGrid','noOfWaterConnectionId~noOfStandpostId~asOnDateId');
						return true;
						} 
					
					else
						{
						
						alert("Entry in Previous Date is Not Allowed.");
						return false;
						}
					//rohit
					
					}
				//StrutsLayout.addDatagridLine('waterConnectionGrid','noOfWaterConnectionId~noOfStandpostId~asOnDateId');
				
		 */		
		 StrutsLayout.addDatagridLine('waterConnectionGrid','noOfWaterConnectionId~noOfStandpostId~asOnDateId');
		 return true;
			
			}
			function ValidateBlankFieldsForWaterConnection(){
				var noOfWaterConnectionId = document.getElementById('noOfWaterConnectionId').value;
				if(noOfWaterConnectionId == ''){
					alert("Please Enter Number of Water Connections");
					return false;
				}
				
				var noOfStandpostId = document.getElementById('noOfStandpostId').value;
				if(noOfStandpostId == ''){
					alert("Please Enter Number of Stand Pots");
					return false;
				}
				
				var asOnDateId = document.getElementById('asOnDateId').value;
				if(asOnDateId == ''){
					alert("Please Enter As On Date");
					return false;
				}
								return true;
			
			
			}

			function ValidateBlankFieldsForDistributionTariff(){
				var tariffId = document.getElementById('tariffId').value;
				if(tariffId == ''){
					alert("Please Enter Tariff Rate");
					return false;
				}
				
				var asOnDateId2 = document.getElementById('asOnDateId2').value;
				if(asOnDateId2 == ''){
					alert("Please Enter As On Date");
					return false;
				}
				StrutsLayout.addDatagridLine('tariffRateGrid','tariffId~asOnDateId2');
				return true;
			}		
			 
			function ValidateBlankFieldsForHouesHold(){
				var GCHouseHoldId = document.getElementById('GCHouseHoldId').value;
				if(GCHouseHoldId == ''){
					alert("Please Enter No of GC HouseHold");
					return false;
				}
				
				var SCHouseHoldId = document.getElementById('SCHouseHoldId').value;
				if(SCHouseHoldId == ''){
					alert("Please Enter No of SC HouseHold");
					return false;
				}
				
				var asOnDateId3 = document.getElementById('asOnDateId3').value;
				if(asOnDateId3 == ''){
					alert("Please Enter As On Date");
					return false;
				}
				StrutsLayout.addDatagridLine('houseHoldGrid','GCHouseHoldId~SCHouseHoldId~asOnDateId3');
				return true;
			}
			
			function ValidateBlankFieldsForPopulation(){
				 var populationYearId = document.getElementById('populationYearId').value;
				if(populationYearId == ''){
						alert("Please Enter Population Year");
						return false;
				}
				
				var asOnDateId4 = document.getElementById('asOnDateId4').value;
				if(asOnDateId4 == ''){
					alert("Please Enter As On Date");
					return false;
				}		
				StrutsLayout.addDatagridLine('populationGrid','generalId~scId~stId~populationYearId~asOnDateId4');
				return true;
			}
			
			function ValidateBlankFieldsForSewerageTariff(){
				 var sewerageTariffId = document.getElementById('sewerageTariffId').value;
				if(sewerageTariffId == ''){
						alert("Please Enter Sewerage Tariff Rate");
						return false;
				}
				
				var asOnDateId5 = document.getElementById('asOnDateId5').value;
				if(asOnDateId5 == ''){
					alert("Please Enter As On Date");
					return false;
				}	
				var sewConnection = document.getElementById('sewConnection').value;
				if(sewConnection == ''){
					alert("Please Enter Sewerage Connection");
					return false;
				}	
				
				StrutsLayout.addDatagridLine('sewerageGrid','sewerageTariffId~asOnDateId5~sewConnection');
				return true;
			}
			if (d__mode == 'ent_modify'){
				disable_ctrl('tab_3~tab_4~tab_6~delconn~deltrf',true);
				hide_ctrl('ncpcaddimg~ncpcdelimg~hhaddimg~hhdelimg~ppladdimg~ppldelimg',true);
				
			}
	</script>
</html:form>
</html:html>