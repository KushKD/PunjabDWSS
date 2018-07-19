<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>

<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beneficiary Entry</title>
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript">
	function de_add() {
		var result = validateField();
		if (result) {
			document.motivatorEntryForm.action = "motivatorEntryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT006";
			document.motivatorEntryForm.submit();
		}
	}
</script>

</head>
<body>
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

	<html:form action="motivatorEntryAction" method="post"
		styleId="motivatorEntryForm" enctype="multipart/form-data">

		<div class="form-group">
			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Person Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">Name:</label>
						<html:text property="personName" styleId="personName"
							styleClass="form-control" style="width:50%;"></html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Father/Spouse Name:</label>
						<html:text property="fatherSpouseName" styleId="fatherSpouseName"
							styleClass="form-control" style="width:50%;"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">DOB</label> <input type='text' name='dob'
							id="dob" class="form-control" style="width:50%;" />
					</div>
					<div class="col-lg-4 col-md-5 sm-hidden xs-hidden"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
						<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">B. Category:</label>
						<html:select property="benifCategory" styleId="benifCategory"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Religion:</label>
						<html:select property="religion" styleId="religion"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Email Id:</label>
						<html:text property="emailId" styleId="emailId"
							styleClass="form-control" style="width:50%;"></html:text>
					</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Phone Number:</label>
						<html:text property="phoneNumber" styleId="phoneNumber" style="width:50%;"
							styleClass="form-control"
							onkeypress="return validateKey1(event,	this,'9@10@3')"
							onchange="if(this.value.toString().length<10){
								alert('Mobile number should not be less than 10');
							}"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Gender:</label>
						<html:select property="gender" styleId="gender"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-left  labledesign">Photograph:</label>
						<html:file property="photograph" styleId="photograph"
							styleClass="form-control" style="width:50%;"></html:file>
					</div>
					<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
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
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Correspondence Address Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">District:</label>
						<html:select property="districtCorsp" styleId="districtCorsp"
							styleClass="form-control" style="width:50%;"
							onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'blockCorsp');" >
							<html:option value="">Select Location</html:option>
							<html:options collection="districtLocations"
								labelProperty="label" property="value"></html:options>
						</html:select>
					</div>
					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Block:</label>
						<html:select property="blockCorsp" styleId="blockCorsp"
							styleClass="form-control" style="width:50%;"
							onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageCorsp');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Village:</label>
						<html:select property="villageCorsp" styleId="villageCorsp"
							styleClass="form-control" style="width:50%;"
							onchange="ajaxFunction('GetFilterValues.to?villageId='+this.value+'&method=fetchGramPanchayat', 'gramPanchayat');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Location/Hamlet/Area:</label>
						<html:text property="locationAreaCorsp"
							styleId="locationAreaCorsp"  style="width:50%;" styleClass="form-control">
						</html:text>
					</div>
					
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Street:</label>
						<html:text property="streetCorsp" style="width:50%;" styleId="streetCorsp"
							styleClass="form-control">
						</html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Land Mark:</label>
						<html:text property="landMarkCorsp"
							styleId="landMarkCorsp" styleClass="form-control" style="width:50%;">
						</html:text>
					</div>
					
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">House Number:</label>
						<html:text property="houseNumberCorsp" style="width:50%;" styleId="houseNumberCorsp"
							styleClass="form-control">
						</html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Pincode:</label>
						<html:text property="pincodeCorsp" style="width:50%;" styleId="pincodeCorsp"
							styleClass="form-control">
						</html:text>
					</div>
				</div>
			</div>


			<!-- permanent address -->

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Permanent Address Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">District:</label>
						<html:select property="districtPermanent"
							styleId="districtPermanent" styleClass="form-control" style="width:50%;"
							onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'blockPermanent');">
							<html:option value="">Select Location</html:option>
							<html:options collection="districtLocations"
								labelProperty="label" property="value"></html:options>
						</html:select>
					</div>
					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Block:</label>
						<html:select property="blockPermanent" styleId="blockPermanent" style="width:50%;"
							styleClass="form-control"
							onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villagePermanent');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Village:</label>
						<html:select property="villagePermanent" style="width:50%;"
							styleId="villagePermanent" styleClass="form-control"
							onchange="ajaxFunction('GetFilterValues.to?villageId='+this.value+'&method=fetchGramPanchayat', 'gramPanchayat');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Location/Hamlet/Area:</label>
						<html:text property="locationAreaPermanent" style="width:50%;"
							styleId="locationAreaPermanent" styleClass="form-control">
						</html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Street:</label>
						<html:text property="streetPermanent" styleId="streetPermanent" style="width:50%;"
							styleClass="form-control">
						</html:text>
					</div>
					<div class="form-inline col-lg-4  col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Land Mark:</label>
						<html:text property="landMarkPermanent" style="width:50%;"
							styleId="landMarkPermanent" styleClass="form-control">
						</html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4  col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">House Number:</label>
						<html:text property="houseNumberPermanent" style="width:50%;"
							styleId="houseNumberPermanent" styleClass="form-control">
						</html:text>
					</div>
					<div class="form-inline col-lg-4  col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Pincode:</label>
						<html:text property="pincodePermanent" styleId="pincodePermanent" style="width:50%;"
							styleClass="form-control">
						</html:text>
					</div>
				</div>
			</div>

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Identity Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-left labledesign">POI Type:</label>
						<html:select property="poiType" styleId="poiType" style="width:50%;"
							styleClass="form-control">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">POI Number:</label>
						<html:text property="poiNumber" styleId="poiNumber" style="width:50%;"
							styleClass="form-control"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-left  labledesign">Aadhar Card Number:</label>
						<html:text property="adharNumber" styleId="adharNumber"
							styleClass="form-control" style="width:50%;"
							onkeypress="return validateKey1(event,	this,'9@12@3')"
							onchange="if(this.value.toString().length<12){
								alert('Aadhar Number should not be less than 12');
							}"></html:text>
					</div>



				</div>
			</div>

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12 col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Bank Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">Name of Bank:</label>
						<html:select property="bankName" styleId="bankName" style="width:50%;"
							styleClass="form-control">
							<html:option value="">Please select Bank</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Branch:</label>
						<html:text property="branch" styleId="branch" style="width:50%;"
							styleClass="form-control"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Account Number:</label>
						<html:text property="accountNo" styleId="accountNo" style="width:50%;"
							styleClass="form-control"
							onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">IFSC Code:</label>
						<html:text property="ifsCode" styleId="ifsCode" style="width:50%;"
							styleClass="form-control"></html:text>
					</div>

					<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

				</div>
			</div>
			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Academic Details</h4>
					<div class="line"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-highlight">

								<thead>
									<tr>
										<th>Examination Passed</th>
										<th>Year</th>
										<th>Total Marks</th>
										<th>Marks Obtained</th>
										<th>Board / University</th>
									</tr>

								</thead>

								<tbody>
									<tr>
										<td><html:text property="examHighschool" value="HighSchool"  styleClass="form-control" readonly="true"></html:text> </td>
										<td><html:text property="highPassedYear"
												styleId="highPassedYear" styleClass="date-own form-control" >
												</html:text></td>
										<td><html:text property="highTotalMarks"
												styleId="highTotalMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20')" ></html:text></td>
										<td><html:text property="highObtainedMarks"
												styleId="highObtainedMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20@2')"></html:text></td>
										<td><html:text property="highBoardUniversity"
												styleId="highBoardUniversity" styleClass="form-control"></html:text></td>
									</tr>
									<tr>
										<td><html:text property="examIntermediate" value="Intermediate"  styleClass="form-control" readonly="true" ></html:text></td>
										<td><html:text property="interPassedYear"
												styleId="interPassedYear" styleClass="date-own form-control" ></html:text></td>
										<td><html:text property="interTotalMarks"
												styleId="interTotalMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text></td>
										<td><html:text property="interObtainedMarks"
												styleId="interObtainedMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text></td>
										<td><html:text property="interBoardUniversity"
												styleId="interBoardUniversity" styleClass="form-control"></html:text></td>
									</tr>
									<tr>
										<td><html:text property="examGraduation" value="Graduation"  styleClass="form-control" readonly="true"></html:text></td>
										<td><html:text property="grdPassedYear"
												styleId="grdPassedYear" styleClass="date-own form-control" ></html:text></td>
										<td><html:text property="grdTotalMarks"
												styleId="grdTotalMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20')"></html:text></td>
										<td><html:text property="grdObtainedMarks"
												styleId="grdObtainedMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text></td>
										<td><html:text property="grdBoardUniversity"
												styleId="grdBoardUniversity" styleClass="form-control"></html:text></td>
									</tr>
									<tr>
										<td><html:text  property="examPGraduation" value="Post Graduation" styleClass="form-control" readonly="true"></html:text></td>
										<td><html:text property="pgrdPassedYear"
												styleId="pgrdPassedYear" styleClass="date-own form-control"  ></html:text></td>
										<td><html:text property="pgrdTotalMarks"
												styleId="pgrdTotalMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20')"> </html:text></td>
										<td><html:text property="pgrdObtainedMarks"
												styleId="pgrdObtainedMarks" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text></td>
										<td><html:text property="pgrdBoardUniversity"
												styleId="pgrdBoardUniversity" styleClass="form-control"></html:text></td>
									</tr>
								</tbody>
							</table>

						</div>
				</div>
			</div>

		</div>

	</html:form>
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/jquery.plugin.js"></script>
	<script type="text/javascript" src="js/jquery.datepick.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
	
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?gender=gender&method=getCombo',
								'gender');
						
						ajaxFunction(
								'GetFilterValues.to?category=category&method=getCombo',
								'benifCategory');
						ajaxFunction(
								'GetFilterValues.to?religion=religion&method=getCombo',
								'religion');
						ajaxFunction(
								'GetFilterValues.to?bankName=bankName&method=getCombo',
								'bankName');
						ajaxFunction(
								'GetFilterValues.to?poiType=poiType&method=getCombo',
								'poiType');
						$('#dob,#defaultInline').datepick();
						/* $('#pgrdPassedYear').yearselect({
							 start: 1980,
							 end: 2017,
							 order: 'asc'
						}); */
						 $('.date-own').datepicker({
							 format: "yyyy",
							    minViewMode: 2,
					       });
	
					});

 	function validateField() {
 		
		var personName = $('#personName').val();
		var fatherSpouseName = $('#fatherSpouseName').val();
		var benifCategory = $('#benifCategory').val();
		var religion = $('#religion').val();
		var phoneNumber = $('#phoneNumber').val();
		var gender = $('#gender').val();
		var photograph = $('#photograph').val();
		var district = $('#district').val();
		var block = $('#block').val();
		var village = $('#village').val();
		var poiType = $('#poiType').val();
		var poiNumber = $('#poiNumber').val();
		var adharNumber = $('#adharNumber').val();
		var bankName = $('#bankName').val();
		var branch = $('#branch').val();
		var accountNo = $('#accountNo').val();
		var ifsCode = $('#ifsCode').val();
		
		var dob=$('#dob').val();
		
		var emailId=$('#emailId').val();
		var districtCorsp=$('#districtCorsp').val();
		var blockCorsp=$('#blockCorsp').val();
		var villageCorsp=$('#villageCorsp').val();
		var locationAreaCorsp=$('#locationAreaCorsp').val();
		var streetCorsp=$('#streetCorsp').val();
		var landMarkCorsp=$('#landMarkCorsp').val();
		var houseNumberCorsp=$('#houseNumberCorsp').val();
		var pincodeCorsp=$('#pincodeCorsp').val();
		var districtPermanent=$('#districtPermanent').val();
		var blockPermanent=$('#blockPermanent').val();
		var villagePermanent=$('#villagePermanent').val();
		var locationAreaPermanent=$('#locationAreaPermanent').val();
		var streetPermanent=$('#streetPermanent').val();
		var landMarkPermanent=$('#landMarkPermanent').val();
		var houseNumberPermanent=$('#houseNumberPermanent').val();
		var pincodePermanent=$('#pincodePermanent').val();
	
		var highPassedYear=$('#highPassedYear').val();
		var highTotalMarks=$('#highTotalMarks').val();
		var highObtainedMarks=$('#highObtainedMarks').val();
		var highBoardUniversity=$('#highBoardUniversity').val();
		var interPassedYear=$('#interPassedYear').val();
		var interTotalMarks=$('#interTotalMarks').val();
		var interObtainedMarks=$('#interObtainedMarks').val();
		var interBoardUniversity=$('#interBoardUniversity').val();
		var grdPassedYear=$('#grdPassedYear').val();
		var grdTotalMarks=$('#grdTotalMarks').val();
		var grdObtainedMarks=$('#grdObtainedMarks').val();
		
		var grdBoardUniversity=$('#grdBoardUniversity').val();
		var pgrdPassedYear=$('#pgrdPassedYear').val();
		var pgrdTotalMarks=$('#pgrdTotalMarks').val();
		var pgrdObtainedMarks=$('#pgrdObtainedMarks').val();
		var pgrdBoardUniversity=$('#pgrdBoardUniversity').val();
		
		if(parseInt(highTotalMarks)<parseInt(highObtainedMarks)){
			alert('High School Total marks should be greater than Obtained marks');
			return false;
		}
		if(parseInt(interTotalMarks)<parseInt(interObtainedMarks)){
			alert('Intermediate Total marks should be greater than Obtained marks');
			return false;
		}
		if(parseInt(grdTotalMarks)<parseInt(grdObtainedMarks)){
			alert('Graduation Total marks should be greater than Obtained marks');
			return false;
		}
		if(parseInt(pgrdTotalMarks)<parseInt(pgrdObtainedMarks)){
			alert('Post Graduation Total marks should be greater than Obtained marks');
			return false;
		}
		
		
		if (personName === '' || personName === null) {
			alert('Name field should not be blank');
			return false;
		}
		if (fatherSpouseName == '' || fatherSpouseName == null) {
			alert('Father/Spouse Name field should not be blank');
			return false;
		}
		if (benifCategory == '' || benifCategory == null) {
			alert('B. Category field should not be blank');
			return false;
		}
		
		if (religion == '' || religion == null) {
			alert('Religion field should not be blank');
			return false;
		}
		if (phoneNumber == '' || phoneNumber == null) {
			alert('Phone Number field should not be blank');
			return false;
		}
		if (phoneNumber.toString().length < 10) {
			alert('Mobile number should not be less than 10');
			return false;
		}
		if (gender == '' || gender == null) {
			alert('Gender field should not be blank');
			return false;
		}
		if (photograph == '' || photograph == null) {
			alert('Please upload image should not be blank');
			return false;
		}
		
		if (poiType == '' || poiType == null) {
			alert('POI Type field should not be blank');
			return false;
		}
		if (poiNumber == '' || poiNumber == null) {
			alert('POI Number field should not be blank');
			return false;
		}
		if (adharNumber == '' || adharNumber == null) {
			alert('Aadhar Card Number field should not be blank');
			return false;
		}
		if (adharNumber.toString().length < 12) {
			alert('Aadhar Number should not be less than 12');
			return false;
		}
		
		if (bankName == '' || bankName == null) {
			alert('Name of Bank field should not be blank');
			return false;
		}
		if (branch == '' || branch == null) {
			alert('Branch field should not be blank');
			return false;
		}
		if (accountNo == '' || accountNo == null) {
			alert('Account Number field should not be blank');
			return false;
		}
		if (ifsCode == '' || ifsCode == null) {
			alert('IFSC Code field should not be blank');
			return false;
		}
	//
		if (emailId == '' || emailId == null) {
			alert('Email Id field should not be blank');
			 $('#emailId').css("border","red solid 1px");
			return false;
		}
		if(emailId!=''&&emailId!=null){
			var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			 if(emailReg.test(emailId )==false){
				 alert('Invalid Email Address');
				 $('#emailId').css("border","red solid 1px");
				 return false;
			 }
		}
		if (districtCorsp == '' || districtCorsp == null) {
			alert('District field should not be blank');
			 $('#districtCorsp').css("border","red solid 1px");
			return false;
		}
		if (blockCorsp == '' || blockCorsp == null) {
			alert('Block field should not be blank');
			 $('#blockCorsp').css("border","red solid 1px");
			return false;
		}
		if (villageCorsp == '' || villageCorsp == null) {
			alert('Village field should not be blank');
			$('#villageCorsp').css("border","red solid 1px");
			return false;
		}
		if (locationAreaCorsp == '' || locationAreaCorsp == null) {
			alert('Location Area field should not be blank');
			$('#locationAreaCorsp').css("border","red solid 1px");
			return false;
		}
		if (streetCorsp == '' || streetCorsp == null) {
			alert('Street field should not be blank');
			$('#streetCorsp').css("border","red solid 1px");
			return false;
		}
		if (landMarkCorsp == '' || landMarkCorsp == null) {
			alert('Land Mark field should not be blank');
			$('#landMarkCorsp').css("border","red solid 1px");
			return false;
		}
		if (houseNumberCorsp == '' || houseNumberCorsp == null) {
			alert('House number field should not be blank');
			$('#houseNumberCorsp').css("border","red solid 1px");
			return false;
		}
		if (pincodeCorsp == '' || pincodeCorsp == null) {
			alert('Pincode field should not be blank');
			$('#pincodeCorsp').css("border","red solid 1px");
			return false;
		}
		if (districtPermanent == '' || districtPermanent == null) {
			alert('District field should not be blank');
			$('#districtPermanent').css("border","red solid 1px");
			return false;
		}
		if (blockPermanent == '' || blockPermanent == null) {
			alert('Block field should not be blank');
			$('#blockPermanent').css("border","red solid 1px");
			return false;
		}
		if (villagePermanent == '' || villagePermanent == null) {
			alert('Village field should not be blank');
			$('#villagePermanent').css("border","red solid 1px");
			return false;
		}
		if (locationAreaPermanent == '' || locationAreaPermanent == null) {
			alert('Location Area field should not be blank');
			$('#locationAreaPermanent').css("border","red solid 1px");
			return false;
		}
		if (streetPermanent == '' || streetPermanent == null) {
			alert('Street field should not be blank');
			$('#streetPermanent').css("border","red solid 1px");
			return false;
		}
		if (landMarkPermanent == '' || landMarkPermanent == null) {
			alert('Land Mark field should not be blank');
			$('#landMarkPermanent').css("border","red solid 1px");
			return false;
		}
		if (houseNumberPermanent == '' || houseNumberPermanent == null) {
			alert('House Number field should not be blank');
			$('#houseNumberPermanent').css("border","red solid 1px");
			return false;
		}
		if (pincodePermanent == '' || pincodePermanent == null) {
			alert('Pincode field should not be blank');
			$('#pincodePermanent').css("border","red solid 1px");
			return false;
		}
		if (highPassedYear == '' || highPassedYear == null) {
			alert('High School Year field should not be blank');
			$('#highPassedYear').css("border","red solid 1px");
			return false;
		}
		if (highTotalMarks == '' || highTotalMarks == null) {
			alert('High School Total Marks field should not be blank');
			$('#highPassedYear').css("border","red solid 1px");
			return false;
		}
		if (highObtainedMarks == '' || highObtainedMarks == null) {
			alert('High School Obtained Marks field should not be blank');
			$('#highObtainedMarks').css("border","red solid 1px");
			return false;
		}
		if (highBoardUniversity == '' || highBoardUniversity == null) {
			alert('High School Board / University  field should not be blank');
			$('#highBoardUniversity').css("border","red solid 1px");
			return false;
		}
		if (interPassedYear == '' || interPassedYear == null) {
			alert('Intermediate Year field should not be blank');
			$('#interPassedYear').css("border","red solid 1px");
			return false;
		}
		if (interTotalMarks == '' || interTotalMarks == null) {
			alert('Intermediate Total Marks field should not be blank');
			$('#interTotalMarks').css("border","red solid 1px");
			return false;
		}
		
		
		if (interObtainedMarks == '' || interObtainedMarks == null) {
			alert('Intermediate Obtained Marks field should not be blank');
			$('#interObtainedMarks').css("border","red solid 1px");
			return false;
		}
		
	
		if (interBoardUniversity == '' || interBoardUniversity == null) {
			alert('Intermediate Board / University field should not be blank');
			$('#interBoardUniversity').css("border","red solid 1px");
			return false;
		}
		if (grdPassedYear == '' || grdPassedYear == null) {
			alert('Graduation Year field should not be blank');
			$('#grdPassedYear').css("border","red solid 1px");
			return false;
		}
		if (grdTotalMarks == '' || grdTotalMarks == null) {
			alert('Graduation Total Marks field should not be blank');
			$('#grdTotalMarks').css("border","red solid 1px");
			return false;
		}
		if (grdObtainedMarks == '' || grdObtainedMarks == null) {
			alert('Graduation Obtained Marks field should not be blank');
			$('#grdObtainedMarks').css("border","red solid 1px");
			return false;
		}
		if (grdBoardUniversity == '' || grdBoardUniversity == null) {
			alert('Graduation  Board / University field should not be blank');
			$('#grdBoardUniversity').css("border","red solid 1px");
			return false;
		}
		if (pgrdPassedYear == '' || pgrdPassedYear == null) {
			alert('Post Graduation  Year field should not be blank');
			$('#pgrdPassedYear').css("border","red solid 1px");
			return false;
		}
		
		if (pgrdTotalMarks == '' || pgrdTotalMarks == null) {
			alert('Post Graduation  Total Marks field should not be blank');
			$('#pgrdTotalMarks').css("border","red solid 1px");
			return false;
		}
		if (pgrdObtainedMarks == '' || pgrdObtainedMarks == null) {
			alert('Post Graduation  Obtained Marks field should not be blank');
			$('#pgrdObtainedMarks').css("border","red solid 1px");
			return false;
		}
		if (pgrdBoardUniversity == '' || pgrdBoardUniversity == null) {
			alert('Post Graduation  Board / University field should not be blank');
			$('#pgrdObtainedMarks').css("border","red solid 1px");
			return false;
		}
		return true;
	} 
</script>

<script type="text/javascript">


</script>
</html:html>