<%@ page import="com.prwss.mis.common.MISSessionBean"%>
<%@ page import="com.prwss.mis.common.util.MisUtility;"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<style>
div.error {
	background: url("images/error.gif") no-repeat scroll 5px 5px #ffddbe;
	border: solid 1px #ed0812;
	color: #ed0812;
	font: 12px/16px Verdana;
	margin: 20px 0px 0px 0px;
	padding: 5px 5px 5px 25px;
	width: 95%;
}

div.success {
	background: url("images/success.gif") no-repeat scroll 5px 5px #BEFBB0;
	border: 1px solid #0B7000;
	color: #0B7000;
	font: 12px/16px Verdana;
	margin: 0 0 10px;
	padding: 5px 5px 5px 25px;
	width: 95%;
}

div.process {
	background: url("images/loading.gif") no-repeat scroll 5px 5px #F8EBBF;
	border: 1px solid #D8D2BE;
	color: #5F3703;
	font: 12px/16px Verdana;
	margin: 0 0 10px;
	padding: 5px 5px 5px 25px;
	width: 95%;
}
</style>
<link href="css/font-awesome.min.css" rel="stylesheet"  />
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet"  />
<link href="css/style_g.css" rel="stylesheet"  />
<script  src="js/bootstrap.min.js"></script>
<script  src="js/jquery.min.js"></script>
<script  src="js/easyResponsiveTabs.js"></script>
</head>
<body style="background-color: #16214d;">

<div class="container" >
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<%@include file="header.jsp"%>
</div>
<div class="row center-block" >

<logic:messagesPresent>
			<div id="errorDiv" class="error displaynone"
				style="margin-bottom: 7px; height: 30px;"><html:errors />
			</div>
		</logic:messagesPresent>
		
		<logic:messagesPresent message="true">
			<div id="successDiv" class="success diplaynone" style="width: 370px;">
			<html:messages id="message" message="true">
				<li><bean:write name="message" /></li>
			</html:messages></div>
		
		</logic:messagesPresent>
		
		<%@include file="login.jsp"%>

 
 </div>
 
 <div class="row">
 <div style="height: 20px; "class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
 <%@include file="footer.jsp"%></td>
 </div>
 <div class="overlay"></div>
 </div>

</div>
</div>

<% 
    MISSessionBean misSessionBean = (MISSessionBean)session.getAttribute("misSessionBean"); 
    if(MisUtility.ifEmpty(misSessionBean)){
            session.invalidate();
    }
    %>
    
 
</body>
</html>