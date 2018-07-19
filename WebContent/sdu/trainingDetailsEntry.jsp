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

//-------------------------------------------------------------------------------------------------------------------
$(document).ready(function(){
	$("#noOfCopiesDist,#panchayatMembers,#male,#female,#scMale,#scFemale").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	
	ajaxFunction(
			'GetFilterValues.to?parentComboId=106&method=getCombo',
			'financialYear');
	ajaxFunction(
			'GetFilterValues.to?parentComboId=1000&method=getCombo',
			'typeOfMaterial');
	ajaxFunction(
			'GetFilterValues.to?parentComboId=173&method=getCombo',
			'lvlOfTrng');
});

// -------------------------------------------------------------------------------------------------------------------- 

function getDivisionsBasedOnUserId(){
var username = '<%=request.getSession().getAttribute("username")%> ';
	if (username === "") {
		alert("Something Bad happened. Please try again!");
	} else {
		//call Ajax
		ajaxFunction("trainingDetailsEntryAction.do?method=getDivisionsBasedOnUserId&username_=" + username, 'division');
	}
}
//---------------------------------------------------------------------------------------------------------------------

function getVillageNameAndId(){
		
		var financialYear = document.getElementById('financialYear').value;
		var division = document.getElementById('division').value;
		
		ajaxFunction("trainingDetailsEntryAction.do?method=getVillageNameAndId&financialYear=" +financialYear+ "&division=" +division, 'village');
	} 
//-----------------------------------------------------------------------------------------------------------------------

function getActivityNameAndId(){
		
		var financialYear = document.getElementById('financialYear').value;
		var division = document.getElementById('division').value;
		var village = document.getElementById('village').value;
		ajaxFunction("trainingDetailsEntryAction.do?method=getActivityNameAndId&financialYear=" +financialYear+ "&division=" +division+ "&village=" +village, 'activity');
	} 
//-----------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------
	function de_add() {
		//var result = true;
		var status = validateDate();
		var status1  = validatePopulation();
		var status2 = validateFields();
		if (status2){
		if (status1){
		if (status) {
			document.trainingDetailsEntryForm.action = "trainingDetailsEntryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SDU011";
			document.trainingDetailsEntryForm.submit();
		}
		}
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
<c:set var="village">
	<bean:message bundle="SDU" key="village" />
</c:set>
<c:set var="activity">
	<bean:message bundle="SDU" key="activity" />
</c:set>
<c:set var="trainingRefNo">
	<bean:message bundle="SDU" key="trainingRefNo" />
</c:set>
<c:set var="tainingDate">
	<bean:message bundle="SDU" key="tainingDate" />
</c:set>
<c:set var="lvlOfTrng">
	<bean:message bundle="SDU" key="lvlOfTrng" />
</c:set>
<c:set var="trainer">
	<bean:message bundle="SDU" key="trainer" />
</c:set>
<c:set var="nameOfTrng">
	<bean:message bundle="SDU" key="nameOfTrng" />
</c:set>
<c:set var="expenditure">
	<bean:message bundle="SDU" key="expenditure" />
</c:set>
<c:set var="total">
	<bean:message bundle="SDU" key="total" />
</c:set>
<c:set var="male">
	<bean:message bundle="SDU" key="male" />
</c:set>
<c:set var="scMale">
	<bean:message bundle="SDU" key="scMale" />
</c:set>
<c:set var="female">
	<bean:message bundle="SDU" key="female" />
</c:set>
<c:set var="scFemale">
	<bean:message bundle="SDU" key="scFemale" />
</c:set>
<c:set var="scTotal">
	<bean:message bundle="SDU" key="scTotal" />
</c:set>
<c:set var="panchayatMembers">
	<bean:message bundle="SDU" key="panchayatMembers" />
</c:set>
<c:set var="typeOfMaterial">
	<bean:message bundle="SDU" key="type.of.material" />
</c:set>
<c:set var="noOfCopiesDist">
	<bean:message bundle="SDU" key="no.of.copies.dist" />
</c:set>
<c:set var="photoTitle">
	<bean:message bundle="SDU" key="photoTitle" />
</c:set>
<c:set var="upldPhoto">
	<bean:message bundle="SDU" key="upldPhoto" />
</c:set>


<html:form action="trainingDetailsEntryAction" method="post"
	styleId="trainingDetailsEntryForm" enctype="multipart/form-data">



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Training Details Entry</h4>
			<div class="line"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${financialYear}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getDivisionsBasedOnUserId()">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${division}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='division' styleId='division'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getVillageNameAndId()">
				</html:select>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
				<label class="text-right labledesign">${village}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='village' styleId='village'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getActivityNameAndId()">
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 c	ol-sm-6">
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
			</div>
			</div>
			
			
			
			
			<div class="panel panel-danger">
		<div class="panel-body">
		
		<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Training Details</h4>
			<div class="line"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: auto;">${trainingRefNo}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="trainingRefNo" styleId="trainingRefNo"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" >${tainingDate}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="tainingDate" styleId="tainingDate"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: auto;">${lvlOfTrng}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="lvlOfTrng" styleId="lvlOfTrng"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="validateDate()">
				</html:select>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${trainer}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="trainer" styleId="trainer"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>	
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: auto;">${nameOfTrng}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="nameOfTrng" styleId="nameOfTrng"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${expenditure}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="expenditure" styleId="expenditure"
					style="width: 150px;" styleClass="cs2 form-control"
					onkeypress="return validateKey1(event,	this,'9@12@2')">
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
		
		<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Participants Details</h4>
			<div class="line"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${male}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="male" styleId="male"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="checkNumber()">
				</html:text>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${scMale}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="scMale" styleId="scMale"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="validatePopulation()">
				</html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>	
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${female}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="female" styleId="female"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="totalPopulation()">
				</html:text>

			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${scFemale}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="scFemale" styleId="scFemale"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="totalSCPopulation();validatePopulation()">
				</html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>	
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${total}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="total" styleId="total"
					style="width: 150px;" styleClass="cs2 form-control" readonly="true">
				</html:text>

			</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${scTotal}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="scTotal" styleId="scTotal"
					style="width: 150px;" styleClass="cs2 form-control" readonly="true">
				</html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;&nbsp;</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" >${panchayatMembers}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="panchayatMembers" styleId="panchayatMembers"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
         
		</div>
		</div>
	
	
	<div class="panel panel-danger">
		<div class="panel-body">
		
		<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Material Distribution Details</h4>
			<div class="line"></div>
		
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: auto;">${typeOfMaterial}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='typeOfMaterial' styleId='typeOfMaterial'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${noOfCopiesDist}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property='noOfCopiesDist' styleId='noOfCopiesDist'
					styleClass="form-control ci5" style="width: 150px;"
					onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
				</html:text>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				
			<input type='hidden' name='typeOfMaterialName' id="typeOfMaterialName">
				

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="materialDistGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="typeOfMaterialName"
										title="${typeOfMaterial}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfCopiesDistName"
										title="${noOfCopiesDist}" mode="I,I,I"></layout:datagridColumn>
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
								onclick="StrutsLayout.setDatagridLineState('materialDistGrid','removed');remove11();">
								<i class='fa fa-trash-o'></i>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	
	
	
	<div class="panel panel-danger">
		<div class="panel-body">
		
		<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Upload Photos</h4>
			<div class="line"></div>
		
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: auto;">${photoTitle}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property='photoTitle' styleId='photoTitle'
					styleClass="form-control ci5" style="width: 150px;">
				</html:text>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${upldPhoto}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:file property='upldPhoto' styleId='upldPhoto'
					styleClass="form-control ci5" style="width: 150px;">
				</html:file>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				
		<!-- 	<input type='hidden' name='typeOfMaterialName' id="typeOfMaterialName">
			<input type='hidden' name='noOfCopiesDistName' id="noOfCopiesDistName"> -->
				

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<%-- <div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="materialDistGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="photoTitle"
										title="${photoTitle}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfCopiesDist"
										title="${noOfCopiesDist}" mode="I,I,I"></layout:datagridColumn>
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
								onclick="StrutsLayout.setDatagridLineState('materialDistGrid','removed');remove11();">
								<i class='fa fa-trash-o'></i>
							</button>
						</td>
					</tr>
				</table>
			</div> --%>
		</div>
	</div>

</html:form>

</body>
 <script type="text/javascript">
	


function totalPopulation(){
	
	var male = document.getElementById('male').value;
	var female = document.getElementById('female').value;
	var maleNum = new Number(male);
	var femaleNum = new Number(female);
	document.getElementById('total').value = (maleNum+femaleNum);
} 

function totalSCPopulation(){
	
	var scMale = document.getElementById('scMale').value;
	var scFemale = document.getElementById('scFemale').value;
	var scMaleNum = new Number(scMale);
	var scFemaleNum = new Number(scFemale);
	document.getElementById('scTotal').value = (scMaleNum+scFemaleNum);
} 

 
	function changeRowColor() {

		$('#materialDistGridJsId>tbody>tr').each(function() {
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
		
		var typeOfMaterialName = $("#typeOfMaterial option:selected").text();
		var typeOfMaterial = document.getElementById('typeOfMaterial').value;
		var typeOfMaterialNameId = typeOfMaterialName + "(" + typeOfMaterial + ")";
		document.getElementById('typeOfMaterialName').value = typeOfMaterialNameId;
		
		var noOfCopiesDist = document.getElementById('noOfCopiesDist').value;

		//var status = validateGridField();
		var status = validatefieldsAdd();
		if (status) {
			StrutsLayout
					.addDatagridLine(
							'materialDistGrid',
							'typeOfMaterialName~noOfCopiesDist');
		}
	}
	
	function validatefieldsAdd(){
		var typeOfMaterial = document.getElementById('typeOfMaterial').value;
		var noOfCopiesDist = document.getElementById('noOfCopiesDist').value;
		
		if(typeOfMaterial=="" || typeOfMaterial==null){
			alert("Please Select Type Of Material.");
			return false;
		}else if(noOfCopiesDist==""||noOfCopiesDist==null){
			alert("Please Enter Number of Copies Distributed");
			return false;
		}
		return true;
	}
	
	$('#tainingDate,#defaultInline').datepick();
	
	function remove11() {
		$('#materialDistGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
				}
			}
		});
	}
	
	
	function validateDate() {
	//	var startDate = new Date($('#startDate').val());
		var tainingDate = new Date($('#tainingDate').val());
		if (tainingDate != '' && typeof tainingDate != 'undefined'
				&& tainingDate != 'Invalid Date') {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!
			var yyyy = today.getFullYear();

			if (dd < 10) {
				dd = '0' + dd
			}

			if (mm < 10) {
				mm = '0' + mm
			}

			today = mm + '/' + dd + '/' + yyyy;

			var EffectiveDate = $.trim($("[id$='tainingDate']").val());

			if (EffectiveDate > today) {
				alert('Training date could not be ahead of current date');
				return false;
			} 
			/* if (endDate < startDate) {
				alert('end date could not less than start date');
				return false;
			} */
		}

		return true;
	}
	
	
	function validatePopulation(){
		var male = document.getElementById('male').value;
		var female = document.getElementById('female').value;
		var scMale = document.getElementById('scMale').value;
		var scFemale = document.getElementById('scFemale').value;
		var total = document.getElementById('total').value;
		var panchayatMembers = document.getElementById('panchayatMembers').value;
		
		var maleNum = new Number(male);
		var femaleNum = new Number(female);
		var scMaleNum = new Number(scMale);
		var scFemaleNum = new Number(scFemale);
		var totalNum = new Number(total);
		var panchayatMembersNum = new Number(panchayatMembers);
		
		if(scMaleNum > maleNum){
			alert("Total Male cannot be less than Total SC Male.");
			return false;
		}else if(scFemaleNum > femaleNum){
			alert("Total Female cannot be less than Total SC Male.")
			return false;
		}else if(panchayatMembersNum > totalNum){
			alert("Total Panchayat Members cannot be more than Total Population.")
			return false;
		}
		return true;
	}
	
	
	function validateFields(){
		var financialYear = document.getElementById('financialYear').value
		var division = document.getElementById('division').value
		var village = document.getElementById('village').value
		var activity = document.getElementById('activity').value
		var trainingRefNo = document.getElementById('trainingRefNo').value
		var tainingDate = document.getElementById('tainingDate').value
		var lvlOfTrng = document.getElementById('lvlOfTrng').value
		var trainer = document.getElementById('trainer').value
		var nameOfTrng = document.getElementById('nameOfTrng').value
		var expenditure = document.getElementById('expenditure').value
		var total = document.getElementById('total').value
		var male = document.getElementById('male').value
		var scMale = document.getElementById('scMale').value
		var female = document.getElementById('female').value
		var scFemale = document.getElementById('scFemale').value
		var scTotal = document.getElementById('scTotal').value
		var panchayatMembers = document.getElementById('panchayatMembers').value
		var photoTitle = document.getElementById('photoTitle').value
		var upldPhoto = document.getElementById('upldPhoto').value
		var typeOfMaterialName = document.getElementById('typeOfMaterialName').value
		
		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year.');
			return false;
		} else if (division === '' || division === null) {
			alert('Please Select Division.');
			return false;
		} else if (village === '' || village === null) {
			alert('Please Select village.');
			return false;
		} else if (activity === '' || activity === null) {
			alert('Please Select activity.');
			return false;
		}else if (trainingRefNo === '' || trainingRefNo === null) {
			alert('Please Enter training Reference No.');
			return false;
		} else if (tainingDate === '' || tainingDate === null) {
			alert('Please Select taining Date.');
			return false;
		} else if (lvlOfTrng === '' || lvlOfTrng === null) {
			alert('Please Select Level of training.');
			return false;
		}else if (trainer === '' || trainer === null) {
			alert('Please Enter trainer.');
			return false;
		} else if (nameOfTrng === '' || nameOfTrng === null) {
			alert('Please Enter Name of training.');
			return false;
		} else if (expenditure === '' || expenditure === null) {
			alert('Please Enter expenditure.');
			return false;
		} else if (male === '' || male === null) {
			alert('Please Enter male population.');
			return false;
		}else if (scMale === '' || scMale === null) {
			alert('Please Enter sc Male population.');
			return false;
		} else if (female === '' || female === null) {
			alert('Please Enter female population.');
			return false;
		} else if (scFemale === '' || scFemale === null) {
			alert('Please Enter sc Female population.');
			return false;
		}else if (panchayatMembers === '' || panchayatMembers === null) {
			alert('Please Enter panchayat Members.');
			return false;
		} else if (photoTitle === '' || photoTitle === null) {
			alert('Please Enter photo Title.');
			return false;
		} else if (upldPhoto === '' || upldPhoto === null) {
			alert('Please Upload Photo.');
			return false;
		}else if (typeOfMaterialName === '' || typeOfMaterialName === null) {
			alert('Please Add Data in Grid.');
			return false;
		}
		return true;
	}
	
</script>
</html:html>