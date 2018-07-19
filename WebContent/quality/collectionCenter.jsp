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
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>


<script type="text/javascript">
	function de_add() {
			document.collectionCenterForm.action = "collectionCenterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=WQ009";
			document.collectionCenterForm.submit();
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


	<html:form action="collectionCenterAction" method="post"
		styleId="collectionCenterForm">

		<div class="panel panel-danger">
			<div class="panel-body">
				<h4
					class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
					Collection Center</h4>
				<div class="line"></div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="Waterquality" key="name" /><span class="text-danger">
							&nbsp;*</span> </label>

					<html:text property='name' styleId='name'
						styleClass="form-control ci5" style="width: 150px;"></html:text>
				</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="Waterquality" key="phone.no" />  </label>
					<html:text property="phoneNo" styleId="phoneNo"
						style="width: 150px;" styleClass="cs2 form-control" onkeypress="return validateKey1(event,	this,'9@10@3')">
					</html:text>
				</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-7 col-xs-12 col-sm-12">
					<label class="text-right labledesign"><bean:message
							bundle="Waterquality" key="address" /> <span
						class="text-danger"> &nbsp;*</span>
					</label>
					<html:textarea property="address" styleId="address"
						style="width: 73%;height:15%" styleClass="form-control">
					</html:textarea>
				</div>
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
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
							bundle="Waterquality" key="lab.name" /> <span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="lab" style="width: 150px;" styleId='lab'
					styleClass="ci5 form-control">
					<html:option value="">Select Lab</html:option>
					<html:options collection="labNames" labelProperty="label"
						property="value"></html:options>
				</html:select>


			</div>

			</div>
		</div>
	</html:form>
</body>
</html:html>