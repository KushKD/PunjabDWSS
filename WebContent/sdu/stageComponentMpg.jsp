<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ci"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>


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


function isFalse(currentValue) {
	 if(currentValue === false){
	 return false;
	 }
	}
	
	
$(document).ready(function(){
	
			$("#noOfVillage").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			        //display error message
			        $("#errmsg").html("Digits Only").show().fadeOut("slow");
			               return false;
			    }
			   });
	
			ajaxFunction('GetFilterValues.to?parentComboId=106&method=getCombo', 'financialYear');
			ajaxFunction( 'GetFilterValues.to?parentComboId=115&method=getCombo', 'component');
//------------------------------------------------------------------------------------------------------------------------------------------			
			var username = '<%=request.getSession().getAttribute("username")%> ';

			if (username === "") {
				alert("Something Bad happened. Please try again!");
			} else {
				//call Ajax
				ajaxFunction("stageComponentMpgAction.do?method=getDivisionsBasedOnUserId&username_=" + username, 'division');
				ajaxFunction("stageComponentMpgAction.do?method=getStages", 'stage');

			}
});
//------------------------------------------------------------------------------------------------------------------------------------------
	function de_add() {
		//var result = true;
		var status = validateSave();
		if (status) {
			document.stageComponentMpgForm.action = "stageComponentMpgAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SDU0026";
			document.stageComponentMpgForm.submit();
		}
	}
	
	function de_find() {
		//var result = true;
		var status = validatePopulate();
		if (status) { 
			document.stageComponentMpgForm.action = "stageComponentMpgAction.do?method=find&d__mode="
					+ d__mode + "&menuId=SDU002";
			document.stageComponentMpgForm.submit();
		} 
	}
</script>

<style>
#categoryVillageTbl {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
}

#categoryVillageTbl {
	display: block;
}

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
	</body>
</logic:messagesPresent>

<c:set var="category">
	<bean:message bundle="SDU" key="category" />
</c:set>
<c:set var="noOfVillages">
	<bean:message bundle="SDU" key="no.of.villages" />
</c:set>
<c:set var="stage">
	<bean:message bundle="SDU" key="stage" />
</c:set>
<c:set var="component">
	<bean:message bundle="SDU" key="component" />
</c:set>
<c:set var="noOfVillage">
	<bean:message bundle="SDU" key="no.of.village" />
</c:set>

<html:form action="stageComponentMpgAction" method="post"
	styleId="stageComponentMpgForm">



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Stage-Component Mapping</h4>

			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="financial.year" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="SDU" key="division" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control">
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
					style="width: 150px;" onclick="de_find()">Populate</button>
			</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">&nbsp;</div>

		</div>
	</div>

	<div style="margin-top: 20px;">



		<c:if test="${not empty stageComponetDtos}">

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Total Number of Villages in Respective Category</h4>
					<div class=" form-inline col-lg-3">&nbsp;</div>
					<div class=" form-inline col-lg-6">
						<div>
							<table class="table  table-responsive table-bordered table-hover">

								<tr>

									<th style='text-align: center;'>Category Name</th>
									<th style='text-align: center;'>No. Of Villages</th>

								</tr>




								<logic:iterate name="stageComponetDtos" id="listUserId"
									property="stageComponetDtos" indexId="rowindex"
									type="com.prwss.min.SDU.bean.StageComponetDto">
									<tr>
										<td style='text-align: center'><bean:write
												name="listUserId" property="categoryName" /></td>
										<td style='text-align: center'><bean:write
												name="listUserId" property="villages" /></td>
									</tr>
								</logic:iterate>

							</table>
						</div>
					</div>
					<div class=" form-inline col-lg-3">&nbsp;</div>
				</div>
			</div>
		</c:if>

	</div>


	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${stage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='stage' styleId='stage'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getComponent();">
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${category}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='category' styleId='category'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<input type='hidden' name='categoryName' id="categoryName"> <input
				type='hidden' name='stageName' id="stageName"> <input
				type='hidden' name='component' id="componentName"> <input
				type='hidden' name='id' id="id">

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${component}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='component' styleId='component'
					styleClass="form-control ci5" style="width: 150px;">
					<html:option value="">Please Select</html:option>

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${noOfVillage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="noOfVillage" styleId="noOfVillage"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

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
								<layout:datagrid styleClass="DATAGRID"
									property="stageCompMpgPlanGrid" selectionAllowed="true"
									multipleSelectionAllowed="true" model="datagrid">
									<layout:datagridColumn property="category" title="${category}"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="stage" title="${stage}"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="component"
										title="${component}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfVillage"
										title="${noOfVillage}" mode="I,I,I"></layout:datagridColumn>

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
							</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</html:form>

<script type="text/javascript">
<%-- alert(<% request.getAttribute("fin"); %>);
document.getElementById("financialYear").value = "<%=request.getAttribute("fin_")%>";
document.getElementById("division").value = "<% out.print(request.getAttribute("div_")); %>";  --%>



$(document).ready(
		function() {
			
			
			 <%if (MisUtility.ifEmpty(request.getAttribute("fin_"))) {%>
				document.getElementById('financialYear').value="<%=request.getAttribute("fin_")%>";
				//triggerEvent(document.getElementById("yearPlan"), 'onchange');
			 <%}%>
			 <%if (MisUtility.ifEmpty(request.getAttribute("div_"))) {%>
		
				document.getElementById('division').value="<%=request.getAttribute("div_")%>"; 
<%}%>



				});





function changeRowColor() {

	$('#stageCompMpgPlanGridJsId>tbody>tr').each(function() {
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
	
	var status = validateGrid();
	
	if(status){
	
	var objectComp = null;
	trueFalseObj =  new Array();

	var categoryName = $("#category option:selected").text();
	var category = document.getElementById('category').value;
	
	var categoryNameId = categoryName + "(" + category + ")";
	document.getElementById('categoryName').value = categoryNameId;
	
	//stage
	var stageName = $("#stage option:selected").text();
	var stage = document.getElementById('stage').value;
	
	var stageNameId = stageName + "(" + stage + ")";
	document.getElementById('stageName').value = stageNameId;
	
	//component
		var componentName = $("#component option:selected").text();
	var component = document.getElementById('component').value;
	
	var componentNameId = componentName + "(" + component + ")";
	document.getElementById('componentName').value = componentNameId;
	
	//Category Id , Stage Id ,Component Id
	//Create Onject of
	 objectComp = createObjectGrid(category,stage,component);
	
	
	
	if(arrayObj.length === 0){
		//Save the Object in an array
		arrayObj.push(objectComp);
		StrutsLayout
		.addDatagridLine(
				'stageCompMpgPlanGrid',
				'categoryName~stageName~componentName~noOfVillage');
		
		
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
				  alert("Stage , Category and Component already exists !!");
			 }else{
				
				 arrayObj.push(objectComp);
				 StrutsLayout
					.addDatagridLine(
							'stageCompMpgPlanGrid',
							'categoryName~stageName~componentName~noOfVillage');
				
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

function createObjectGrid(category,stage,component){
	
	var gridObject = {
			
			category : category,
			stage    : stage,
			component: component,
	}
	
	return gridObject;
}



function emptyDataGrid(){  //stageCompMpgPlanGrid
	  clearArray = new Array();
		//alert(arrayObj);
		array = StrutsLayout.setDatagridLineStateNew('stageCompMpgPlanGrid','removed');
		//alert(array);
		remove11();
		clearArrayObj(array);
	
}

function clearArrayObj(arrayToBeCleared){
	//alert("array from Grid"+ arrayToBeCleared);
	for(var p in arrayToBeCleared){
		//alert("values to be cleared"+arrayToBeCleared[p]);
		var x = arrayToBeCleared[p]
		delete arrayObj[x] ;  
	}
}

function remove11() {
		$('#stageCompMpgPlanGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					//alert($(this).value());
						
					$(this).hide();
					
			 
				}
			}
		});
	}
	
	
	
	
	
function getComponent(){
		var year = document.getElementById("financialYear").value;
		var division_ = document.getElementById("division").value;
		//alert(division_)
		if(year === "" &&  division_ == ""){
			alert ("Please select Division and Financial Year");
		}else{
			ajaxFunction("stageComponentMpgAction.do?method=getComponents&year=" + year +"&div="+ division_ , 'category');
		}
	}

function validatePopulate(){
	var financialYear = document.getElementById("financialYear").value
	var division = document.getElementById("division").value
	
	if(financialYear == "" || financialYear == null){
		alert('Please Select Financial Year.');
		return false;
	}else if(division == "" || division == null){
		alert('Please Select Division');
		return false;
	}
	return true;
}

 function validateGrid(){
	var financialYear = document.getElementById("financialYear").value
	var division = document.getElementById("division").value
	var stage = document.getElementById("stage").value
	var category = document.getElementById("category").value
	var component = document.getElementById("component").value
	var noOfVillage = document.getElementById("noOfVillage").value
	
	if(financialYear == "" || financialYear == null){
		alert('Please Select Financial Year.');
		return false;
	}else if(division == "" || division == null){
		alert('Please Select Division.');
		return false;
	}else if(stage == "" || stage == null){
		alert('Please Select Stage.');
		return false;
	}else if(category == "" || category == null){
		alert('Please Select Category.');
		return false;
	}else if(component == "" || component == null){
		alert('Please Select Component.');
		return false;
	}else if(noOfVillage == "" || noOfVillage == null){
		alert('Please Enter Number of Villages.');
		return false;
	}
	return true;
} 


	function validateSave() {
		var categoryName = document.getElementById("categoryName").value

		 if (categoryName === '' || categoryName === null) {
			alert('Please Add Data to the Grid.');
			return false;
		}
		return true;
	} 
	
	
</script>
</body>
</html:html>