<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">


<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	$(window)
			.load(
					function() {
						ajaxFunction(
								'GetFilterValues.to?bottle_type=bottle_type&method=getCombo',
								'bottleType');
						ajaxFunction(
								'GetFilterValues.to?water_source=water_source&method=getCombo',
								'waterSourceUrban');
						document.getElementById("checkurban").checked = false;
						document.getElementById("checkrural").checked = false;
					});

	hide_ctrl('modalPeriod', true);
	function de_find() {
		document.receiveSampleForm.action = "receiveSampleAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=ADM014";
		document.schemeMasterForm.submit();

	}
	function de_modify() {
		//alert('inside modify----'+d__mode);
		//var status = validateFields();
		//alert('status---------'+status);
		if (d__mode == 'ent_modify') {
			var result = true;//valid.validate();

			if (result) {
				document.receiveSampleForm.action = "receiveSampleAction.do?method=update&d__mode="
						+ d__mode + "&menuId=ADM014";
				document.receiveSampleForm.submit();
			}
		}
	}

	function de_add() {

		var status = validate_fields();
		//alert(status)
		//validate_fields()
		if (status) {
			document.receiveSampleForm.action = "receiveSampleAction.do?method=save&d__mode="
					+ d__mode + "&menuId=ADM014";
			document.receiveSampleForm.submit();
		}
	}

	function de_qrcode() {

		var district = document.getElementById("district").selectedOptions[0].text;
		var block = document.getElementById("block").selectedOptions[0].text;
		var collectedBy = document.getElementById('collectedBy').value;

		var waterSource = document.getElementById("waterSource").value;
		
		if (waterSource === '') {
			waterSource = document.getElementById("waterSourceUrban").selectedOptions[0].text;
		}else{
			waterSource = document.getElementById("waterSource").selectedOptions[0].text;
		}
		
		var schemeName = document.getElementById("schemeName").value;
		if (schemeName === '') {
			schemeName = 'Urban';
		}else{
			schemeName = document.getElementById("schemeName").selectedOptions[0].text;
		}

		var habitation = document.getElementById("villageId").value;
		if (habitation === '') {
			habitation = '';
		}else{
			schemeName = document.getElementById("villageId").selectedOptions[0].text;
		}

		if (true) {
			//document.receiveSampleForm.action = "receiveSampleAction.do?method=generateQrCode&d__mode=" + d__mode + "&menuId=ADM014";
			/* + "&habitation="
					+ habitation
					+ "&schemeName="
					+ schemeName
					
			 Earlier Working */
			document.receiveSampleForm.action = "receiveSampleAction.do?method=generateQrCode&d__mode="
					+ d__mode
					+ "&menuId=ADM014"
					+ "&district="
					+ district
					+ "&block="
					+ block
					+ "&schemeName="
					+ schemeName
					+ "&waterSource="
					+ waterSource
					+ "&habitation="
					+ habitation
					+ "&collectedBy=" + collectedBy;

			document.receiveSampleForm.submit();
		}
	}

	function urbanField() {

		if (document.getElementById("checkurban").checked) {
			document.getElementById("checkrural").checked = false;
			document.getElementById("villageId").value = "";
			document.getElementById("villageHavitation").style.visibility = 'hidden';
			document.getElementById("cityDiv").style.visibility = 'visible';
			document.getElementById("depth").disabled = false; //rural_schemeDetails
			document.getElementById("urban_schemeDetails").style.display = 'block'; //schemeNameUrban
			document.getElementById("snu").style.display = 'none';
			document.getElementById("snu").value = "";
			document.getElementById("rural_schemeDetails").style.display = 'none';
			document.getElementById("block").disabled = true;
			document.getElementById("block").value="";
			
		} else {
			document.getElementById("block").disabled = false;
			document.getElementById("checkrural").checked = true;
			document.getElementById("villageHavitation").style.visibility = 'visible';
			document.getElementById("cityDiv").style.visibility = 'hidden';
			document.getElementById("city").value = "";
			document.getElementById("waterSourceUrban").value = "";
			document.getElementById("depth").disabled = true;
			document.getElementById("rural_schemeDetails").style.display = 'block';
			document.getElementById("urban_schemeDetails").style.display = 'none';
		}

	}

	function ruralField() {
		if (document.getElementById("checkrural").checked) {
			document.getElementById("block").disabled = false;
			document.getElementById("checkurban").checked = false;
			document.getElementById("villageHavitation").style.visibility = 'visible';
			document.getElementById("cityDiv").style.visibility = 'hidden';
			document.getElementById("city").value = "";
			document.getElementById("waterSourceUrban").value = "";
			document.getElementById("depth").disabled = true;
			document.getElementById("urban_schemeDetails").style.display = 'none';
			document.getElementById("rural_schemeDetails").style.display = 'block';
		} else {
			document.getElementById("block").value="";
			document.getElementById("block").disabled = true;
			document.getElementById("checkurban").checked = true;
			document.getElementById("villageId").value = "";
			document.getElementById("villageHavitation").style.visibility = 'hidden';
			document.getElementById("cityDiv").style.visibility = 'visible';
			document.getElementById("depth").disabled = false;
			document.getElementById("urban_schemeDetails").style.display = 'block';
			document.getElementById("rural_schemeDetails").style.display = 'none';
		}
	}

	function validate_fields() {
		var lab = document.getElementById('lab').value.trim();
		var collectionType = document.getElementById('collectionType').value
				.trim();
		var district = document.getElementById('district').value.trim();
		var block = document.getElementById('block').value.trim();
		var testType = document.getElementById('testType').value.trim();
		var sampleNumber = document.getElementById('sampleNumber').value.trim();
		var collDate = document.getElementById('collDate').value.trim();
		var bottleType = document.getElementById('bottleType').value.trim();
		var letterRefNum = document.getElementById('letterRefNum').value.trim();
		var quantity = document.getElementById('quantity').value.trim();

		if (lab === "") {
			alert("Please Select Lab Name");
			return false;
		} else if (collectionType === "") {
			alert("Please Select the Collection Type (DWSS/External)");
			return false;
		} else if (district === "") {
			alert("Please select District");
			return false;
		} 
		else if(document.getElementById('checkurban').checked==false){
			if (block === "") {
				alert("Please select block");
				return false;
			}
		}
		  else if (sampleNumber === "") {
			alert("Sample Number cannot be empty. Please refresh the page.");
			return false;
		} else if (testType === "") {
			alert("Please select Test Type");
			return false;
		} else if (collDate === "") {
			alert("Please select Collection Date");
			return false;
		} else if (bottleType === "") {
			alert("Please select the Type of bottle in which sample is taken.");
			return false;
		} /* else if (letterRefNum === "") {
			alert("Please enter the reference Number.");
			return false;
		}  */
		else if (quantity === "") {
			alert("Please enter the quantity of the sample taken in Milliliter");
			return false;
		} else if (collectionType == '2') {

			var email = document.getElementById("email").value.trim();
			var mobileNo = document.getElementById("mobileNo").value.trim();
			var poiType = document.getElementById("poiType").value.trim();
			var poiId = document.getElementById("poiId").value.trim();
			if (mobileNo === "") {
				alert('Please enter mobile no');
				return false;
			}
			/* if (poiType === "") {
				alert('Please enter POI Type');
				return false;
			}
			if (poiId === "") {
				alert('Please enter POI Id');
				return false;
			} */
			/* if (email === "") {
				alert('Please enter Email Address');
				return false;
			} */
			return true;
		} else if (collectionType === '1') {
			var designation = document.getElementById('designation').value;
			var collectedBy = document.getElementById('collectedBy').value;
			if (designation === "") {
				alert("Please Select Designation");
				return false;
			}
			if (collectedBy === "") {
				alert("Please Enter collected By");
				return false;
			}
			return true;
		}
		return true;
	}
</script>

<style type="text/css">
</style>

</head>


<html:html>
<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="waterQuality" style='display: none;'>
			<html:errors bundle="Waterquality" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('waterQuality');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>
<html:form action="receiveSampleAction" method="post"
	styleId="receiveSampleForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Receive Sample Action</h4>



			<div class="line">
				<h5
					class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-left">
					All Fields marked with<span class="text-danger"> &nbsp;*</span> are
					mandatory.
				</h5>
			</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="form-inline col-lg-8 col-md-10 col-xs-12 col-sm-12">
				<label class="text-right labledesign">Collected By</label> <label
					class="text-right  text-warning ">&nbsp; &nbsp;<%=request.getSession().getAttribute("username")%></label>


			</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>




			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Lab<span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="lab" style="width: 150px;" styleId='lab'
					styleClass="ci5 form-control" onchange="getSampleNo(this.value)">
					<html:option value="">Select Lab</html:option>
					<html:options collection="labNames" labelProperty="label"
						property="value"></html:options>
				</html:select>


			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Collection Type<span
					class="text-danger"> &nbsp;*</span></label>

				<html:select property='collectionType' styleId='collectionType'
					styleClass=" form-control ci5" style="width: 150px;" value=""
					onchange="if(this.value==='1'){
								ajaxFunction('receiveSampleAction.do?collectionType='+this.value+'&method=fetchEmpDesignation', 'designation');
								       document.getElementById('collectedBy').value ='';
										$('#mobileNo1').text('');
										 $('#emailId').text(''); 
										document.getElementById('collectedBy').value ='';
								        $('#dwss_employee1').show();
								        $('#dwss_employee2').show();
								      $('#other_employee1').hide();
								     $('#other_employee2').hide();
								         $('#schemeLabel').show();
								 $('#sourceLabel').show();
							}
							if(this.value==='2'){
							    $('#schemeLabel').hide();
							
									 $('#dwss_employee1').hide();
								        $('#dwss_employee2').hide();
								      $('#other_employee1').show();
								     $('#other_employee2').show();
								     $('#sourceLabel').hide();
							}if(this.value===''){
							   $('#schemeLabel').show();
									 $('#dwss_employee1').hide();
								        $('#dwss_employee2').hide();
								      $('#other_employee1').hide();
								     $('#other_employee2').hide();
								   $('#mobileNo1').text('');
										$('#emailId').text(''); 
										$('#sourceLabel').show();
								
							}">
					<html:option value="">Please select</html:option>
					<html:option value="1">DWSS Employee</html:option>
					<html:option value="2">Other Source</html:option>

				</html:select>
			</div>






			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>







			<div id='dwss_employee1'>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Designation<span
						class="text-danger"> &nbsp;*</span></label>

				<%-- 	<html:select property="designation" styleId="designation"
						styleClass=" form-control" style="width: 150px;"
						onchange="ajaxFunction('receiveSampleAction.do?designation='+this.value+'&method=fetchEmployee', 'collectedBy');">
					</html:select> --%>
					
						<html:select property="designation" styleId="designation"
						styleClass=" form-control" style="width: 150px;"
						>
					</html:select>


				</div>

				<%-- <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Collected By<span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property='collectedBy' styleId='collectedBy'
						style="width: 150px;" styleClass=" ci5 form-control"
						onchange="Receivesample.collectionSample()"></html:select>
				</div> --%>

	<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Collected By<span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property='collectedBy' styleId='collectedBy'
						style="width: 150px;" styleClass=" ci5 form-control"></html:text>
				</div>


				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>

			<div class="emialandmobile">
				<div class="emialandmobiledwss" id='dwss_employee2'>

					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">Email Id</label> <label
							class="text-right  text-warning " id='emailId'>&nbsp;
							&nbsp;&nbsp;&nbsp;</label> <html:text styleId='emailDwss'
							property='emailDwss'  style="width: 150px;" styleClass=" ci5 form-control"/>


					</div>

					<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">Mobile No.<span
							class="text-danger"> &nbsp;*</span></label> <label
							class="text-right  text-warning " id='mobileNo1'>&nbsp;
							&nbsp;&nbsp;&nbsp;</label> <html:text styleId='mobileNoDwss'
							property='mobileNoDwss' style="width: 150px;" styleClass=" ci5 form-control" onkeypress="return validateKey1(event,	this,'9@10@3')"/>

					</div>



					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
				</div>

				<div class="emialandmobileothers" id='other_employee1'>

					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">Email<span
							class="text-danger"> &nbsp;</span>
						</label>
						<html:text styleClass="text-right form-control"
							style="width:150px" styleId="email" property="email">&nbsp; &nbsp;</html:text>

					</div>

					<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">Mobile No.<span
							class="text-danger"> &nbsp;*</span></label>
						<html:text styleClass="text-right  form-control "
							style="width:150px" styleId="mobileNo" property="mobileNo"
							onkeypress="return validateKey1(event,	this,'9@10@3')"
							onchange="if(this.value.toString().length<10){
								alert('Mobile number should not be less than 10');
							}">&nbsp; &nbsp;</html:text>
					</div>



					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
				</div>
			</div>

			<div class="poi" id='other_employee2'>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">POI Type</label>

					<html:select property='poiType' styleId='poiType'
						style="width:150px" styleClass="ci5 form-control">
						<html:option value="">Please select</html:option>
						<html:option value="1">Pan Card</html:option>
						<html:option value="2"> Aadhaar Card</html:option>
						<html:option value="2"> Election id</html:option>
					</html:select>

				</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">POI ID<span
						class="text-danger"> &nbsp;</span></label>
					<html:text property="poiId" styleId="poiId" style="width: 150px;"
						styleClass="ci5 form-control"></html:text>
				</div>



				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

			</div>


		</div>
	</div>


	<div class="panel panel-danger">
		<div class="panel-body">

			<!-- Urban and Rural -->
			<div id='urban_rural'>

				<h4
					class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Please
					Select Urban/ Rural</h4>
				<div class="col-lg-5 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>


				<div class=" form-inline col-lg-1 col-md-5 col-xs-12 col-sm-6">
					<html:checkbox styleId="checkurban" property="urban" value="urban"
						onchange="urbanField();">Urban </html:checkbox>



				</div>

				<div class=" form-inline col-lg-1 col-md-5 col-xs-12 col-sm-6">
					<html:checkbox styleId="checkrural" property="rural" value="rural"
						onchange="ruralField();">Rural </html:checkbox>
				</div>
				<div class="col-lg-3 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
		</div>
	</div>



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Sample
				Location Details</h4>
			<div class="line"></div>
			<div >
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District<span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
				id="cityDiv">
				<label class="text-right labledesign" >City/Town<span
					class="text-danger"> &nbsp;*</span></label>
				<html:text property="city" styleId="city" style="form-control" 
					value=""></html:text>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" id="urban_blockDetails">
				<label class="text-right labledesign">Block<span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="block" styleId="block"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Please Select Block</html:option>
				</html:select>

			</div>

			<div id=villageHavitation
				class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Village/Habitation</label>
				<html:select property="villageId" styleId="villageId" value=""
					styleClass="ci5 form-control" style="width: 150px;"
					onchange="ajaxFunction('receiveSampleAction.do?villageId='+this.value+'&method=fetchScheme', 'schemeName');
							">
					<html:option value="">Select Village</html:option>
				</html:select>
			</div>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>





			<div id="landmarkDiv">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign" style="font-size: 11px">LandMark</label>
					<html:textarea property="landMark" styleId="landMark"
						styleClass="form-control" value=""></html:textarea>

				</div>


				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign" style="font-size: 11px">
						Other Details</label>
					<html:textarea property="sampleotherdetails"
						styleId="sampleotherdetails" styleClass="form-control" value=""></html:textarea>

				</div>
			</div>



		</div>
	</div>


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Scheme
				Details</h4>
			<div class="line"></div>



			<div class="row" id="urban_schemeDetails" style="display: none;">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id="snu">
					<label class="text-right labledesign">Scheme Name<span
						class="text-danger" > &nbsp;</span></label>
					<html:text property="schemeNameUrban" styleId='schemeNameUrban'
						styleClass="ci5 form-control" style="width:150px" />

				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Water Source<span
						class="text-danger"> &nbsp;</span></label>
					<html:select property="waterSourceUrban" styleId='waterSourceUrban'
						styleClass="ci5 form-control" style="width:150px" value="">
						<html:option value="">Select Water Source</html:option>
					</html:select>

				</div>

			</div>

			<div class="row" id="rural_schemeDetails" style="display: none;">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Scheme Name<span
						class="text-danger" id='schemeLabel'> &nbsp;</span></label>
					<html:select property="schemeName" styleId='schemeName'
						styleClass="ci5 form-control" style="width:150px" value=""
						onchange="ajaxFunction('receiveSampleAction.do?schemeName='+this.value+'&method=fetchWaterSource', 'waterSource');">
						<html:option value="">Select Scheme</html:option>
					</html:select>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Water Source<span
						class="text-danger" id='sourceLabel'> &nbsp;</span></label>
					<html:select property="waterSource" styleId='waterSource'
						styleClass="ci5 form-control" style="width:150px" value="">
						<html:option value="">Select Water Source</html:option>
					</html:select>

				</div>

			</div>


			<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Depth(Meter)</label>
					<html:text property='depth' styleId='depth' style="width:150px"
						styleClass="ci5 form-control" onkeypress="return validateKey1(event,	this,'9@10@3')"></html:text>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Test Type<span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="testType" styleId="testType"
						style="width: 150px;" styleClass="ci5 form-control">
						<html:option value="">Select Test Type</html:option>
						<html:option value="1">FTK</html:option>
						<html:option value="2">Lab Test</html:option>

					</html:select>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Sample Number</label>
					<html:text property='sampleNumber' styleId='sampleNumber'
						style="width:150px" styleClass="form-control" readonly='true'></html:text>

				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>

			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Collection Date<span
						class="text-danger"> &nbsp;*</span></label> <input type="text"
						name="collDate" id="collDate" class="form-control labledesign"
						style="width: 150px" />
				</div>
				<%-- <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Sample taken from</label>
					<html:select property="sampleWaterWorks" styleId="sampleWaterWorks"
						style="width: 150px;" styleClass="ci5 form-control">
						<html:option value="">Select water works</html:option>
						<html:option value="Internal">Internal</html:option>
						<html:option value="External">Private</html:option>

					</html:select>
				</div> --%>
			</div>
		</div>
	</div>
	<input type='hidden' name='sampleId' id='sampleId'>


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Other
				Details</h4>
			<div class="line"></div>

			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Bottle Type<span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="bottleType" styleId="bottleType"
						style="width: 150px;" styleClass="ci5 form-control">
						<html:option value="">Select Bottle Type</html:option>
						<html:option value="1">Purple</html:option>
						<html:option value="2">White</html:option>

					</html:select>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Reference Letter
						Number
					</label>
					<html:text property='letterRefNum' styleId='letterRefNum'
						style="width:150px" styleClass="form-control"></html:text>

				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>

			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>



				<div class=" form-inline col-lg-6 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Quantity</label>
					<html:text property='quantity' styleId='quantity'
						style="width:150px" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@10@3')"></html:text>
					<span class="text-danger"> &nbsp; In Milliliter (ml)</span>

				</div>

			</div>

		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="padding: 10px; display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
				<table id="sampleCollection"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th></th>
							<th>Sample Number</th>
							<th>Lab Name</th>
							<th>Water Source Name</th>
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>





</html:form>


</body>
<script type='text/javascript'>
	$(document).ready(function() {
		document.getElementById("datatable_sh").style.display = '';
		ReceiveSample.sample();
	});

	$('#startDate,#defaultInline').datepick();
	$('#endDate,#defaultInline').datepick();
	$('#collDate,#defaultInline').datepick();

	$(document).ready(function() {
		$('#dwss_employee1').hide();
		$('#dwss_employee2').hide();
		$('#other_employee1').hide();
		$('#other_employee2').hide();

	});

	function isEmail(email) {
		var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regex.test(String(email).toLowerCase())) {
			alert('Please enter valid email id');
			return false;
		}
		return true;
	}
	function getSampleNo(labId) {

		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			// alert(1);
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			// alert(2);
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"receiveSampleAction.do?method=fetchSampleNo&labId=" + labId,
				false);
		xmlHttp.send(null);
		// alert(3);
		if (xmlHttp.readyState == 4) {
			// alert(4);
			if (xmlHttp.status == 200) {
				// alert(5);
				var optionContent = xmlHttp.responseText;
				document.getElementById('sampleNumber').value = optionContent;
			}
		}
	}
</script>

</html:html>