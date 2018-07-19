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
//-------------------------------------------------------------------------------------------------------------------------------------------------

$(document).ready(function() {
	$("#noOfVillages").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	
	ajaxFunction(
			'GetFilterValues.to?parentComboId=106&method=getCombo',
			'financialYear1');
	ajaxFunction(
			'GetFilterValues.to?parentComboId=147&method=getCombo',
			'category');
}); 

//-------------------------------------------------------------------------------------------------------------------------------------------------
	function de_add() {
		//var result = true;
		var status = validateFields();
		if (status) {
			
			if(arrayObj.length === 0){
				alert("Please add catagory and number of villages");
				return false;
			}else{
				document.divisionWiseSummaryForm.action = "divisionWiseSummaryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SDU001";
			document.divisionWiseSummaryForm.submit();
			//alert("inside save button");
			}
			
			
			
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------
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
<c:set var="noOfVillages">
	<bean:message bundle="SDU" key="no.of.villages" />
</c:set>

<html:form action="divisionWiseSummaryAction" method="post"
	styleId="divisionWiseSummaryForm">



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Division Wise Summary</h4>
			<div class="line"></div>



				<html:hidden property='financialYear' styleId="financialYear"></html:hidden>
			<html:hidden property='division' styleId="division"></html:hidden>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${financialYear}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='financialYear1' styleId='financialYear1'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${division}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="division1" styleId="division1"
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
				<label class="text-right labledesign">${category}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='category' styleId='category' onchange="clearVillages();"
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>

<input type='hidden' name='categoryName' id="categoryName">

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${noOfVillages}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="noOfVillages" styleId="noOfVillages"
					style="width: 150px;" styleClass="cs2 form-control"
					onkeypress="return validateKey1(event,	this,'9@5@3')">
				</html:text>

			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
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
								<layout:datagrid styleClass="DATAGRID" property="divWiseSumPlanGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="category"
										title="${category}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfVillages"
										title="${noOfVillages}" mode="I,I,I"></layout:datagridColumn>

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


	 function changeRowColor() {

		$('#divWiseSumPlanGridJsId>tbody>tr').each(function() {
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
		var status = validateGridField();

		if(status){
		var categoryName = $("#category option:selected").text();
		var category = document.getElementById('category').value;
		var categoryNameId = categoryName + "(" + category + ")";
		document.getElementById('categoryName').value = categoryNameId;
		
		
		//Send Ajax anc check weather category exist in the db or not
		var financialYear = document.getElementById("financialYear1").value;
		var division = document.getElementById('division1').value;
		var category = document.getElementById("category").value; 
		
		//Create an array and store the the value of category in the array
		 objectComp = createObjectGrid(category,division,financialYear);
	 
		 if(arrayObj.length === 0){
				//Save the Object in an array
				 $.ajax({
					type : "POST",
					async: false,
					url : "/PRWSS_MIS/divisionWiseSummaryAction.do?method=checkCategory",
					data : {
								
								financialYear : financialYear,
								Division: division,
								CategoryId: category,
								
							},
					success : function(data) {
						
						if(data === 'true'){
							arrayObj.push(objectComp);
							StrutsLayout.addDatagridLine('divWiseSumPlanGrid','categoryName~noOfVillages');
							
							document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
							document.getElementById('division').value = document.getElementById('division1').value;
							
							$('#financialYear1').attr('disabled', 'disabled');
							$('#division1').attr('disabled', 'disabled');
						}
						else{
							alert("Category Alredy Exist for the selected financial year and Division." );
							
						}
						
						
					}
				
				});
			}
		 
		 else{
			 for(var i = 0; i < arrayObj.length ; i++){
					
				 
				 if (typeof arrayObj[i] !== "undefined") {
					 var addArray = null;
					    addArray =  compareObject(arrayObj[i],objectComp);
					trueFalseObj.push(addArray);
					}else{
						console.log("Something is Undefined");
					}
					
				 
				 
				 
			
			 }
			 
		
			  if(trueFalseObj.includes(true)){
				  alert("Category already added .Please select other category.");
			 }else{
				
				 $.ajax({
						type : "POST",
						async: false,
						url : "/PRWSS_MIS/divisionWiseSummaryAction.do?method=checkCategory",
						data : {
									
									financialYear : financialYear,
									Division: division,
									CategoryId: category,
									
								},
						success : function(data) {
							
							if(data === 'true'){
								arrayObj.push(objectComp);
								StrutsLayout.addDatagridLine('divWiseSumPlanGrid','categoryName~noOfVillages');
								
								 document.getElementById('financialYear').value = document.getElementById('financialYear1').value;
									document.getElementById('division').value = document.getElementById('division1').value;
									
									$('#financialYear1').attr('disabled', 'disabled');
									$('#division1').attr('disabled', 'disabled');
								
							}
							else{
								alert("Category Alredy Exist for the selected financial year and Division." );
								
							}
							
							
						}
					
					});
				
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

function createObjectGrid(category,division,financialYear){
	
	var gridObject = {
			
			category: category,
			division: division,
			financialYear: financialYear
	}
	
	return gridObject;
}

 function emptyDataGrid(){
	  clearArray = new Array();
	//alert(arrayObj);
	array = StrutsLayout.setDatagridLineStateNew('divWiseSumPlanGrid','removed');
	alert(array);
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
		$('#divWiseSumPlanGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
					gridElements = [];
				}
			}
		});
	} 

	function validateGridField() {
		var financialYear = document.getElementById("financialYear1").value;
		var division = document.getElementById('division1').value;
		var category = document.getElementById("category").value;
		var noOfVillages = document.getElementById('noOfVillages').value;

		if (financialYear == "" || financialYear == null){
			alert("Please Select Financial Year.");
			return false;
		}else if(division == "" || division == null){
			alert("Please Select Division.");
			return false;
		}else if (category === '' || category === null) {
			alert('Please Select Category.');
			return false;
		} else if (noOfVillages === '' || noOfVillages === null) {
			alert('Please Enter Number of Villages.');
			return false;
		}
		return true;
	}
	
	function validateFields() {
		var categoryName = document.getElementById("categoryName").value;
		
		if(categoryName == "" || categoryName == null){
			alert("Please Add Data to the Grid.");
			return false;
		}
		return true;
	}
	
	var username = '<%=request.getSession().getAttribute("username")%> ';

	if (username === "") {
		alert("Something Bad happened. Please try again!");
	} else {
		//call Ajax
		ajaxFunction("divisionWiseSummaryAction.do?method=getDivisionsBasedOnUserId&username_=" + username, 'division1');
	}
	
	function clearVillages(){
		document.getElementById('noOfVillages').value = "";
	}
	
	
</script>
</html:html>