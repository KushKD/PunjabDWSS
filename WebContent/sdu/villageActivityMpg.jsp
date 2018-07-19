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

var arrayObj =  new Array();
var  trueFalseObj =  null;
var clearArray = null;


	function de_add() {
		//var result = true;
		var status = validateGrid();
		if (status) {
			document.villageActivityMpgForm.action = "villageActivityMpgAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SDU005";
			document.villageActivityMpgForm.submit();
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

<html:form action="villageActivityMpgAction" method="post"
	styleId="villageActivityMpgForm">

<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Village Activity Mapping</h4>

			<div class="line"></div>
			
			
			
			<html:hidden property='financialYear' styleId="financialYear"></html:hidden>
			<html:hidden property='division' styleId="division"></html:hidden>
			<html:hidden property='category' styleId="category"></html:hidden>
			<html:hidden property='stage' styleId="stage"></html:hidden>
			<html:hidden property='component' styleId="component"></html:hidden>
			<html:hidden property='village' styleId="village"></html:hidden>
			
			
			

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="financial.year" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='financialYear1' styleId='financialYear1'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="division" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division1"
					onchange="getComponent(); getVillagsFromDivision();"
					styleId="division1" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:select>

			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-12 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Village Activity Mapping</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
				bundle="SDU" key="category" /><span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='category1' styleId='category1' 
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign"><bean:message
				bundle="SDU" key="stage" /><span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='stage1' styleId='stage1' onchange="getComponentNameAndId();" styleClass="form-control ci5" style="width: 150px;">
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
				bundle="SDU" key="component" /><span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="component1" styleId="component1"   onchange="getVillagesList1();"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${village}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="village1" styleId="village1" onchange="getActivities();"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
         
		</div>
	</div>
	
	
	<div class="panel panel-danger">
		<div class="panel-body">
		
		<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${activity}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='activity' styleId='activity'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				&nbsp;<br>&nbsp;
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${expectedStartDate}<span
					class="text-danger"> &nbsp;*</span>
				</label>
					<html:text property="expectedStartDate" styleId="expectedStartDate" style="width:150px;"
					 styleClass="form-control"></html:text>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${expectedEndDate}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="expectedEndDate" styleId="expectedEndDate" style="width:150px;"
					 styleClass="form-control"></html:text>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<!-- <button type="button" class="btn btn-primary" onclick='addDataGrid()'
				style="width: 200px;">Add Detail</button> -->
		</div>
	</div>
	<input type='hidden' name='villageName' id="villageName"> <input
				type='hidden' name='activityName' id="activityName"> <input
				type='hidden' name='startDate' id="startDate"><input
				type='hidden' name='endDate' id="endDate">
	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="villageActivityMpgGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="village"
										title="${village}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="activity"
										title="${activity}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="expectedStartDate"
										title="${expectedStartDate}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="expectedEndDate"
										title="${expectedEndDate}" mode="I,I,I"></layout:datagridColumn>

								</layout:datagrid>

							</div>
						</td>
						<td><button title='Add' type='button'
								class="btn btn-primary active" style="width: 50px;"
								onclick="addDataGrid()">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</button> <br>
							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="emptyDataGrid();">
								<i class='fa fa-trash-o'></i>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</html:form>

</body>
 <script type="text/javascript">
 
 ajaxFunction( 'GetFilterValues.to?parentComboId=106&method=getCombo', 'financialYear1');
 var username = '<%=request.getSession().getAttribute("username")%>';

 	if (username === "") {
 		alert("Something Bad happened. Please try again!");
 	} else {
 		//call Ajax
 		ajaxFunction(
 				"stageComponentMpgAction.do?method=getDivisionsBasedOnUserId&username_="
 						+ username, 'division1');
 		ajaxFunction("stageComponentMpgAction.do?method=getStages", 'stage1');

 	}

 	function getComponent() {
 		var year = document.getElementById("financialYear1").value;
 		var division_ = document.getElementById("division1").value;
 		//alert(division_);
 		if (year === "" && division == "") {
 			alert("Please select Division and Financial Year");
 		} else {
 			ajaxFunction(
 					"stageComponentMpgAction.do?method=getComponents&year="
 							+ year + "&div=" + division_, 'category1');
 		}
 	}

 	function getComponentNameAndId() {

 		var financialYear = document.getElementById('financialYear1').value;
 		var division = document.getElementById('division1').value;
 		var category = document.getElementById('category1').value;
 		var stage = document.getElementById('stage1').value;

 		ajaxFunction(
 				"villageDivisionMpgAction.do?method=getComponentNameAndId&financialYear2="
 						+ financialYear + "&division2=" + division
 						+ "&category2=" + category + "&stage2=" + stage,
 				'component1');
 	}
 
 $('#expectedStartDate,#defaultInline').datepick();
	$('#expectedEndDate,#defaultInline').datepick();
	
	
	function getVillagesList1() {

		var category = document.getElementById('category1').value;
		var stage = document.getElementById('stage1').value;
		var component = document.getElementById('component1').value;

		ajaxFunction(
				"villageActivityMpgAction.do?method=getVillagesList&category2=" + category + "&stage2=" + stage +"&component2="+component,
				'village1');
	}
	
	
	function getActivities(){
		var category = document.getElementById('category1').value;
		var stage = document.getElementById('stage1').value;
		var component = document.getElementById('component1').value;
		var divId = document.getElementById('division1').value;
		
		
		ajaxFunction(
				"villageActivityMpgAction.do?method=getActivity&category2=" + category + "&stage2=" + stage +"&component2="+component +"&divId2="+divId ,'activity' );
		
	}
	
	
 
   function changeRowColor() {

		$('#villageActivityMpgGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_SEL")) {
					//$('table tr').css('color','#c51717');
					$(this).css('color', '#c51717');
				} else {
					//$('table tr').css('color','#000000');
					$(this).css('color', '#000000');

				}
			}

		});

	}

	function addDataGrid() {
		
		var objectComp = null;
		trueFalseObj =  new Array();

		var status = validateFields()
		
		if(status){
		//Village
		var villageName = $("#village1 option:selected").text();
		var village = document.getElementById('village1').value;
		
		var villageNameId = villageName + "(" + village + ")";
		document.getElementById('villageName').value = villageNameId;
		
		
		
		//Activity
		var activityName = $("#activity option:selected").text();
		var activity = document.getElementById('activity').value;
		
		var activityNameId = activityName + "(" + activity + ")";
		document.getElementById('activityName').value = activityNameId;
		
		//Start Date
			var startDate = document.getElementById('expectedStartDate').value ;
		
		document.getElementById('startDate').value = startDate;
		
		//End Date
			var endDate = document.getElementById('expectedEndDate').value ;
		
		var enddateNameId = endDate ;
		document.getElementById('endDate').value = enddateNameId;
		
		 objectComp = createObjectGrid(village,activity);

		//var status = validateGridField();
		//var status = true;
		if (status) {
			
			if(arrayObj.length === 0){
				//Save the Object in an array
				arrayObj.push(objectComp);
				StrutsLayout
				.addDatagridLine(
						'villageActivityMpgGrid',
						'villageName~activityName~startDate~endDate');
				
				document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
				document.getElementById('division').value = document.getElementById('division1').value;
				document.getElementById('stage').value = document.getElementById('stage1').value;
				document.getElementById('category').value = document.getElementById('category1').value;
				document.getElementById('component').value = document.getElementById('component1').value;
				document.getElementById('village').value = document.getElementById('village1').value;
				
				$('#financialYear1').attr('disabled', 'disabled');
				$('#division1').attr('disabled', 'disabled');
				$('#stage1').attr('disabled', 'disabled');
				$('#category1').attr('disabled', 'disabled');
				$('#component1').attr('disabled', 'disabled');
				$('#village1').attr('disabled', 'disabled');
				
				
			}else{
				
				 
				
				 for(var i = 0; i < arrayObj.length ; i++){
					
					 console.log("i is ",i );
					// alert(arrayObj[i]);
					 
					 if (typeof arrayObj[i] !== "undefined") {
						 var addArray = null;
						    addArray =  compareObject(arrayObj[i],objectComp);
						trueFalseObj.push(addArray);
						 console.warn("What To Do",trueFalseObj);
						}else{
							console.log("Something is Undefined");
						}
						
					 
					 
					 
				
				 }
				 
				/*  if(trueFalseObj.every(isFalse)) */
				// alert(trueFalseObj);
				  if(trueFalseObj.includes(true)){
					  alert("Village and Activity already exists !!");
				 }else{
					
					 arrayObj.push(objectComp);
					 StrutsLayout
						.addDatagridLine(
								'villageActivityMpgGrid',
								'villageName~activityName~startDate~endDate');
					 
					 document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
						document.getElementById('division').value = document.getElementById('division1').value;
						document.getElementById('stage').value = document.getElementById('stage1').value;
						document.getElementById('category').value = document.getElementById('category1').value;
						document.getElementById('component').value = document.getElementById('component1').value;
						document.getElementById('village').value = document.getElementById('village1').value;
						
						$('#financialYear1').attr('disabled', 'disabled');
						$('#division1').attr('disabled', 'disabled');
						$('#stage1').attr('disabled', 'disabled');
						$('#category1').attr('disabled', 'disabled');
						$('#component1').attr('disabled', 'disabled');
						$('#village1').attr('disabled', 'disabled');
					
				 } 
				 
		}
			
			
			
			
			
		}
		
		
		}
	}
	
	
	


	function compareObject(o1, o2){
			for(var p in o1){
				if(o1[p] !== o2[p]){
					return false;
				}
			}
			for(var p in o2){
				if(o1[p] !== o2[p]){
					return false;
				}
			}
			return true;
		
	}

	function createObjectGrid(village,activity){
		
		var gridObject = {
				
				village : village,
				activity    : activity,
		}
		
		return gridObject;
	}

	function emptyDataGrid(){
		//alert(arrayObj);
		clearArray = new Array();
		array = StrutsLayout.setDatagridLineStateNew('villageActivityMpgGrid','removed');
		//alert(array);
		remove11();
		clearArrayObj(array);
		//alert(arrayObj);
		
	}

	function clearArrayObj(arrayToBeCleared){
		
		for(var p in arrayToBeCleared){
			var x = arrayToBeCleared[p]
			delete arrayObj[x] ;  
		}
	}
	
	function remove11() {
		$('#villageActivityMpgGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
				}
			}
		});
	}
	
	
	
	function validateFields(){
		var financialYear = document.getElementById('financialYear1').value
		var division = document.getElementById('division1').value
		var stage = document.getElementById('stage1').value
		var category = document.getElementById('category1').value
		var component = document.getElementById('component1').value
		var village = document.getElementById('village1').value
		var activity = document.getElementById('activity').value
		var expectedStartDate = document.getElementById('expectedStartDate').value
		var expectedEndDate = document.getElementById('expectedEndDate').value
		
		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year.');
			return false;
		} else if (division === '' || division === null) {
			alert('Please Select Division.');
			return false;
		} else if (stage === '' || stage === null) {
			alert('Please Select Stage.');
			return false;
		} else if (category === '' || category === null) {
			alert('Please Select Category.');
			return false;
		} else if (component === '' || component === null) {
			alert('Please Select Component.');
			return false;
		} else if (village === '' || village === null) {
			alert('Please Select Village.');
			return false;
		}else if (activity === '' || activity === null) {
			alert('Please Select Activity.');
			return false;
		}else if (expectedStartDate === '' || expectedStartDate === null) {
			alert('Please Enter Expected Start Date.');
			return false;
		}else if (expectedEndDate === '' || expectedEndDate === null) {
			alert('Please Enter Expected End Date.');
			return false;
		}
		return true;
	}
	
	function validateGrid() {
		var activityName = document.getElementById('activityName').value;

		if (activityName === '' || activityName === null) {
			alert('Please Add Data in the Grid.');
			return false;
		} 
		return true;
	}
/*
	 $(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?parentComboId=106&method=getCombo',
								'financialYear');
						ajaxFunction(
								'GetFilterValues.to?parentComboId=112&method=getCombo',
								'inspectionType');
						ajaxFunction(
								'GetFilterValues.to?parentComboId=115&method=getCombo',
								'component');
						ajaxFunction(
								'yearlyInspectionPlanAction.do?method=fetchExternalAgency',
								'assignedTo');

					}); 
	//$('#finalizationDate,#defaultInline').datepick();
	

	 function populateField() {
		document.getElementById("financialYearName").value = $(
				"#financialYear option:selected").text();
	} 

	 function getTotalVisit() {

		if (document.getElementById("visitPerVillage").value != ""
				&& document.getElementById("visitPerVillage").value != null) {
			document.getElementById("totalVisit").value = parseInt(document
					.getElementById("visitVillage").value)
					* parseInt(document.getElementById("visitPerVillage").value);
		}
	}
	function getTotalMonthVisit() {
		if (document.getElementById("totalDuration").value != ""
				&& document.getElementById("totalDuration").value != null) {
			var result = parseInt(document.getElementById("totalVisit").value)
					/ parseInt(document.getElementById("totalDuration").value);
	
			document.getElementById("perMonthVisit").value = parseInt(result);
					
			
		}
	}

	function validateGridField() {
		var financialYear = document.getElementById("financialYear").value;
		var component = document.getElementById('component').value;
		var finalizationDate = document.getElementById('finalizationDate').value;
		var numberOfVillage = document.getElementById('numberOfVillage').value;

		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year');
			return false;
		} else if (component === '' || component === null) {
			alert('Please Select Component');
			return false;
		} else if (finalizationDate === '' || finalizationDate === null) {
			alert('Please enter Finalization Date');
			return false;
		} else if (numberOfVillage === '' || numberOfVillage === null) {
			alert('Please enter No of Villages');
			return false;
		}
		return true;

	} 
	 
	function regex(e11){
		var regex1=/^[0-9]+\.?[0-9]*$/;
		alert(e11);
		alert(regex1.match(e11))
		return e11.match(regex1);
	}   */
</script> 
</html:html>