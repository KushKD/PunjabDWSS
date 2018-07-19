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


<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/sanitation.js"></script>
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
	var arrayObj = new Array();
	var trueFalseObj = null;
	var clearArray = null;

	hide_ctrl('modalPeriod', true);

	function de_add() {
		var status = validateFields();

		if (true) {
			document.gramPanchayatMasterForm.action = "gramPanchayatMasterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT013";
			document.gramPanchayatMasterForm.submit();
		}
	}
</script>

</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="sanitation1" style='display: none;'>
			<html:errors bundle="sanitation" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('sanitation1');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>
<html:form action="gramPanchayatMasterAction" method="post"
	styleId="gramPanchayatMasterForm" enctype="multipart/form-data">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Gram
				Panchayat Details</h4>
			<div class="line"></div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Name of Gram Panchayat</label>
				<html:text property='nameofGramPanchayat'
					styleId='nameofGramPanchayat' styleClass="form-control ci5"
					style="width:150px;" onkeypress="return lettersOnly()"></html:text>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Name of Sarpanch</label>
				<html:text property='nameofSarpanch' styleId='nameofSarpanch'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Gram
				Panchayat Details</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District</label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Block</label>
				<html:select property="block" styleId="block"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Please Select Block</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Village</label>
				<html:select property="village" styleId="villageId" value=""
					styleClass="ci5 form-control" style="width: 150px;">
					<html:option value="">Select Village</html:option>
				</html:select>
			</div>
			
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Status <span
					class="text-danger"> &nbsp;*</span>
				</label>

				<html:select property='status' styleId='status'
					styleClass="ci5 form-control" style="width: 150px;">
					<html:option value="1">Active</html:option>
					<html:option value="0">InActive</html:option>

				</html:select>
			</div>

		 	<div class="col-lg-4 col-md-5 col-xs-12 col-sm-6">&nbsp; &nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<br>
			 <input type='hidden' name='districtgrid' id="districtgrid">
			<input type='hidden' name='blockgrid' id="blockgrid"> <input
				type='hidden' name='villagegrid' id="villagegrid">
				<input
				type='hidden' name='statusgrid' id="statusgrid">
				
				
				

					

					<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

						<table onclick="changeRowColor()">
							<tr>
								<td>
									<div class="divgrid">
										<layout:datagrid styleClass="DATAGRID"
											property="gramPanchayaMasterGrid" selectionAllowed="true"
											multipleSelectionAllowed="true" model="datagrid">
											<layout:datagridColumn property="gramPanchayatName"
												title="Gram Panchayat Name" mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="districtGrid"
												title="District" mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="blockGrid" title="Block"
												mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="villageGrid" title="Village"
												mode="I,I,I"></layout:datagridColumn>
												<layout:datagridColumn property="statusGrid" title="Status"
												mode="I,I,I"></layout:datagridColumn>


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
										style="width: 50px;" onclick="emptyDataGrid();">
										<i class='fa fa-trash-o'></i>
									</button></td>
							</tr>
						</table>
					</div>

		</div>
	</div>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="gramPanchayatMasterDT"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">

					<thead>
						<tr>

							<th>Name of Gram Panchayat</th>
							<th>District</th>
							<th>Block</th>
							<th>Village</th>

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</html:form>

<script type='text/javascript'>
	gramPanchayatMasters.GramPanchayatType();
	document.getElementById("datatable_sh").style.display = '';

	function validateFields() {

		var nameofGramPanchayat = document
				.getElementById('nameofGramPanchayat').value;
		var nameofSarpanch = document.getElementById('nameofSarpanch').value;
		var district = document.getElementById('district').value;
		var block = document.getElementById('block').value;
		var village = document.getElementById('villageId').value;
		var status = document.getElementById('status').value;

		if (nameofGramPanchayat == "" || nameofGramPanchayat == null) {
			alert("Please Enter Gram Panchayat Name")
			return false;
		} else if (nameofSarpanch == "" || nameofSarpanch == null) {
			alert("Please Enter Sarpanch Name");
			return false;
		} else if (district == "" || district == null) {
			alert("Please Select District");
			return false;
		} else if (block == "" || block == null) {
			alert("Please Select Block");
			return false;
		} else if (village == "" || village == null) {
			alert("Please Select Village");
			return false;
		}else if (status == "" || status == null) {
			alert("Please Select Village");
			return false;
		} else
			return true;
	}

	function changeRowColor() {

		$('#gramPanchayaMasterGridJsId>tbody>tr').each(function() {
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
		trueFalseObj = new Array();

		var status = validateFields()

		if (status) {
			//district
			var districtName = $("#district option:selected").text();
			var district = document.getElementById('district').value;

			var districtId = districtName + "(" + district + ")";
			document.getElementById('districtgrid').value = districtId;
			
			//block
			var blockName = $("#block option:selected").text();
			var block = document.getElementById('block').value;

			var blockId = blockName + "(" + block + ")";
			document.getElementById('blockgrid').value = blockId;
			
			//village
			var villageName = $("#villageId option:selected").text();
			var village = document.getElementById('villageId').value;

			var villageId = villageName + "(" + village + ")";
			document.getElementById('villagegrid').value = villageId;

			

			
			//GM NAME
			var nameofGramPanchayat = document.getElementById('nameofGramPanchayat').value;
			var nameofGramPanchayatId = nameofGramPanchayat;
			document.getElementById('nameofGramPanchayat').value = nameofGramPanchayatId;
			
			
			//Satus
				var statusName = $("#status option:selected").text();
			//alert(statusName);
			var status = document.getElementById('status').value;
			//alert(status);
			var statusId = statusName + "(" + status + ")";
			//alert(statusId);
			document.getElementById('statusgrid').value = statusId;
			//alert(statusId);
			
			
			objectComp = createObjectGrid(villageName,status);

			//var status = validateGridField();
			//var status = true;
			if (status) {

				if (arrayObj.length === 0) {
					
					//Run Ajax 
					//Save the Object in an array
				 $.ajax({
					type : "POST",
					async: false,
					url : "gramPanchayatMasterAction.do?method=checkVillagenStatus",
					data : {
								// /PRWSS_MIS/
								village : village,
								status: status
								
								
							},
					success : function(data) {
						
						if(data === 'true'){
							//Save the Object in an array
							arrayObj.push(objectComp);
							StrutsLayout.addDatagridLine('gramPanchayaMasterGrid', 'nameofGramPanchayat~districtgrid~blockgrid~villagegrid~statusgrid');
						}
						else{
							alert("Village and  Gram Panchayat Name  already exists !!" );
							
						}
						
						
					}
				
				}); 
					

			

				} else {

					for (var i = 0; i < arrayObj.length; i++) {

						console.log("i is ", i);
						// alert(arrayObj[i]);

						if (typeof arrayObj[i] !== "undefined") {
							var addArray = null;
							addArray = compareObject(arrayObj[i], objectComp);
							trueFalseObj.push(addArray);
							console.warn("What To Do", trueFalseObj);
						} else {
							console.log("Something is Undefined");
						}

					}

					if (trueFalseObj.includes(true)) {
						alert("Village and  Gram Panchayat Name  already exists !!");
					} else {
						
						$.ajax({
							type : "POST",
							async: false,
							url : "gramPanchayatMasterAction.do?method=checkVillagenStatus",
							data : {
										// /PRWSS_MIS/
										village : village,
										status: status
										
										
									},
							success : function(data) {
								
								if(data === 'true'){
									//Save the Object in an array
									arrayObj.push(objectComp);
									StrutsLayout.addDatagridLine('gramPanchayaMasterGrid', 'nameofGramPanchayat~districtgrid~blockgrid~villagegrid~statusgrid');
	
								
								}
								else{
									alert("Village and  Gram Panchayat Name  already exists !!" );
									
								}
								
								
							}
						
						}); 

						

					

					}

				}

			}

		}
	}

	function compareObject(o1, o2) {
		for ( var p in o1) {
			if (o1[p] !== o2[p]) {
				return false;
			}
		}
		for ( var p in o2) {
			if (o1[p] !== o2[p]) {
				return false;
			}
		}
		return true;

	}

	function createObjectGrid(villageName,status) {

		var gridObject = {

			
			village : villageName,
			status: status
		}

		return gridObject;
	}

	function emptyDataGrid() {
		//alert(arrayObj);
		clearArray = new Array();
		array = StrutsLayout.setDatagridLineStateNew('gramPanchayaMasterGrid', 'removed');
		//alert(array);
		remove11();
		clearArrayObj(array);
		//alert(arrayObj);

	}

	function clearArrayObj(arrayToBeCleared) {

		for ( var p in arrayToBeCleared) {
			var x = arrayToBeCleared[p]
			delete arrayObj[x];
		}
	}

	function remove11() {
		$('#gramPanchayaMasterGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
				}
			}
		});
	}
</script>

</body>

</html:html>