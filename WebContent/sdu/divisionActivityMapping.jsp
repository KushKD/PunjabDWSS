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
			document.divisionActivityMpgForm.action = "divisionActivityMpgAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SDU003";
			document.divisionActivityMpgForm.submit();
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

<c:set var="financialYear">
	<bean:message bundle="SDU" key="financial.year" />
</c:set>
<c:set var="division">
	<bean:message bundle="SDU" key="division" />
</c:set>
<c:set var="category">
	<bean:message bundle="SDU" key="category" />
</c:set>
<c:set var="stage">
	<bean:message bundle="SDU" key="stage" />
</c:set>
<c:set var="component">
	<bean:message bundle="SDU" key="component" />
</c:set>
<c:set var="activity">
	<bean:message bundle="SDU" key="activity" />
</c:set>
<c:set var="campaign">
	<bean:message bundle="SDU" key="campaign" />
</c:set>

<html:form action="divisionActivityMpgAction" method="post"
	styleId="divisionActivityMpgForm">



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Division Activity Mapping</h4>
			<div class="line"></div>
			
			
			<input type='hidden' name='financialYearName' id="financialYearName">
			<input type='hidden' name='divisionName' id="divisionName">
			<input type='hidden' name='categoryName' id="categoryName">
			<input type='hidden' name='stageName' id="stageName">
			<input type='hidden' name='componentName' id="componentName">
			<input type='hidden' name='campaignName' id="campaignName">
			<input type='hidden' name='activityName' id="activityName">
			
			<html:hidden property='financialYear' styleId="financialYear"></html:hidden>
			<html:hidden property='division' styleId="division"></html:hidden>
			<html:hidden property='category' styleId="category"></html:hidden>
			<html:hidden property='stage' styleId="stage"></html:hidden>
			<html:hidden property='component' styleId="component"></html:hidden>
			<html:hidden property='campaign' styleId="campaign"></html:hidden>

			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${financialYear}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='financialYear1' styleId='financialYear1'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${division}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='division1' styleId='division1'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction('divisionActivityMpgAction.do?financialYear_='+financialYear1.value+'&division_='+this.value+'&method=getCategoryNameAndId', 'category1')">
				</html:select>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${category}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='category1' styleId='category1'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getStageNameAndId()">
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${stage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='stage1' styleId='stage1'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getComponentNameAndId();">
				</html:select>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${campaign}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="campaign1" styleId="campaign1"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="getActivityNameAndId()">
				</html:select>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${component}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="component1" styleId="component1"
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
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<!-- <button type="button" class="btn btn-primary" onclick='addDataGrid()'
				style="width: 200px;">Add Detail</button> -->
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="divActivityMpgGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="financialYear"
										title="${financialYear}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="division"
										title="${division}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="category"
										title="${category}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="stage"
										title="${stage}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="component"
										title="${component}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="campaign"
										title="${campaign}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="activity"
										title="${activity}" mode="I,I,I"></layout:datagridColumn>

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
 
	function getComponentNameAndId(){
		
		var financialYear = document.getElementById('financialYear1').value;
		var division = document.getElementById('division1').value;
		var category = document.getElementById('category1').value;
		var stage = document.getElementById('stage1').value;
		
		ajaxFunction("divisionActivityMpgAction.do?method=getComponentNameAndId&financialYear1=" +financialYear+ "&division1=" +division+ "&category1=" +category+ "&stage1=" +stage, 'component1');
	} 
	
	
function getStageNameAndId(){
		
		var financialYear = document.getElementById('financialYear1').value;
		var division = document.getElementById('division1').value;
		var category = document.getElementById('category1').value;
		
		ajaxFunction("divisionActivityMpgAction.do?method=getStageNameAndId&financialYear1=" +financialYear+ "&division1=" +division+ "&category1=" +category, 'stage1');
	} 
	
	
function getActivityNameAndId(){
		
		var financialYear = document.getElementById('financialYear1').value;
		var campaign = document.getElementById('campaign1').value;
		var category = document.getElementById('category1').value;
		var stage = document.getElementById('stage1').value;
		
		ajaxFunction("divisionActivityMpgAction.do?method=getActivityNameAndId&financialYear1=" +financialYear+ "&campaign1=" +campaign+ "&category1=" +category+ "&stage1=" +stage+"&parentId=169", 'activity');
	} 
	
	function changeRowColor() {

		$('#divActivityMpgGridJsId>tbody>tr').each(function() {
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
		
		var status = validateFields();
		
		if(status){
		var categoryName = $("#category1 option:selected").text();
		var category = document.getElementById('category1').value;
		var categoryNameId = categoryName + "(" + category + ")";
		document.getElementById('categoryName').value = categoryNameId;
		
		var financialYearName = $("#financialYear1 option:selected").text();
		var financialYear = document.getElementById('financialYear1').value;
		var financialYearNameId = financialYearName + "(" + financialYear + ")";
		document.getElementById('financialYearName').value = financialYearNameId;
		
		var divisionName = $("#division1 option:selected").text();
		var division = document.getElementById('division1').value;
		var divisionNameId = divisionName + "(" + division + ")";
		document.getElementById('divisionName').value = divisionNameId;
		
		var stageName = $("#stage1 option:selected").text();
		var stage = document.getElementById('stage1').value;
		var stageNameId = stageName + "(" + stage + ")";
		document.getElementById('stageName').value = stageNameId;
		
		var componentName = $("#component1 option:selected").text();
		var component = document.getElementById('component1').value;
		var componentNameId = componentName + "(" + component + ")";
		document.getElementById('componentName').value = componentNameId;
		
		var campaignName = $("#campaign1 option:selected").text();
		var campaign = document.getElementById('campaign1').value;
		var campaignNameId = campaignName + "(" + campaign + ")";
		document.getElementById('campaignName').value = campaignNameId;
		
		var activityName = $("#activity option:selected").text();
		var activity = document.getElementById('activity').value;
		var activityNameId = activityName + "(" + activity + ")";
		document.getElementById('activityName').value = activityNameId;
		
		

		//var status = validateGridField();
		//var status = true;
		if (status) {
			
			 objectComp = createObjectGrid(category,financialYear,division,stage,component,campaign,activity);
			 
			 if(arrayObj.length === 0){
					//Save the Object in an array
					arrayObj.push(objectComp);
					StrutsLayout
					.addDatagridLine(
							'divActivityMpgGrid',
							'financialYearName~divisionName~categoryName~stageName~componentName~campaignName~activityName');
					
					document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
					document.getElementById('division').value = document.getElementById('division1').value;
					document.getElementById('stage').value = document.getElementById('stage1').value;
					document.getElementById('category').value = document.getElementById('category1').value;
					document.getElementById('campaign').value = document.getElementById('campaign1').value;
					document.getElementById('component').value = document.getElementById('component1').value;
					
					$('#financialYear1').attr('disabled', 'disabled');
					$('#division1').attr('disabled', 'disabled');
					$('#stage1').attr('disabled', 'disabled');
					$('#category1').attr('disabled', 'disabled');
					$('#campaign1').attr('disabled', 'disabled');
					$('#component1').attr('disabled', 'disabled');
					
				}
			 else{
					
				 
					
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
					  alert("Stage , Category , Component and Activity already exists !!");
				 }else{
					
					 arrayObj.push(objectComp);
					 StrutsLayout
						.addDatagridLine(
								'divActivityMpgGrid',
								'financialYearName~divisionName~categoryName~stageName~componentName~campaignName~activityName');
					 
					 document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
						document.getElementById('division').value = document.getElementById('division1').value;
						document.getElementById('stage').value = document.getElementById('stage1').value;
						document.getElementById('category').value = document.getElementById('category1').value;
						document.getElementById('campaign').value = document.getElementById('campaign1').value;
						document.getElementById('component').value = document.getElementById('component1').value;
						
						$('#financialYear1').attr('disabled', 'disabled');
						$('#division1').attr('disabled', 'disabled');
						$('#stage1').attr('disabled', 'disabled');
						$('#category1').attr('disabled', 'disabled');
						$('#campaign1').attr('disabled', 'disabled');
						$('#component1').attr('disabled', 'disabled');
					
				 } 
				 
		}
			 
			
		}
		
		
		}
	}

	$(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?parentComboId=106&method=getCombo',
								'financialYear1');
						ajaxFunction(
								'divisionActivityMpgAction.do?attributeId=170&method=getCampaignNameAndId',
								'campaign1');
						
						var username = '<%=request.getSession().getAttribute("username")%> ';
						if (username === "") {
							alert("Something Bad happened. Please try again!");
						} else {
							//call Ajax
							ajaxFunction("divisionActivityMpgAction.do?method=getDivisionsBasedOnUserId&username_=" + username, 'division1');
						}
					}); 
	
	
	
	


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

	function createObjectGrid(category,financialYear,division,stage,component,campaign,activity){
		
		var gridObject = {
				
				category : category,
				stage    : stage,
				component: component,
				financialYear : financialYear,
				division : division,
				campaign : campaign,
				activity : activity
				
		}
		
		return gridObject;
	}
	
	function emptyDataGrid(){
		//alert(arrayObj);
		 clearArray = new Array();
		array = StrutsLayout.setDatagridLineStateNew('divActivityMpgGrid','removed');
		//alert(array);
		remove11();
		clearArrayObj(array);
		//alert(arrayObj);
		
	}

	function clearArrayObj(arrayToBeCleared){
		
		for(var p in arrayToBeCleared){
			var x = arrayToBeCleared[p];
			delete arrayObj[x] ;  
		}
	}
	
	function remove11() {
		$('#divActivityMpgGridJsId>tbody>tr').each(function() {
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
		var campaign = document.getElementById('campaign1').value
		var component = document.getElementById('component1').value
		var activity = document.getElementById('activity').value
		
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
		}else if (campaign === '' || campaign === null) {
			alert('Please Select Campaign');
			return false;
		} else if (component === '' || component === null) {
			alert('Please Select Component.');
			return false;
		} else if (activity === '' || activity === null) {
			alert('Please Select Activity.');
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
	function regex(e11){
		var regex1=/^[0-9]+\.?[0-9]*$/;
		alert(e11);
		alert(regex1.match(e11))
		return e11.match(regex1);
	} */
</script>
</html:html>