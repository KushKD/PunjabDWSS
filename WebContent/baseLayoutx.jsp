<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title><tiles:getAsString name="title" ignore="true" /></title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/stylenew.css" rel="stylesheet" type="text/css" />
<link href="css/responsiveElements.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="css/prwss.css">
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" /> -->

<script src="js/jquery-1.12.4.js"></script>
	<!-- Bootstrap Js CDN -->
	<script
		src="js/bootstrap.min.js"></script>
	<script
		src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/layout.js"></script>

<!-- <link href="css/jquery-ui-calendar.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery/jquery.js"></script>
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery-ui.min.js"></script> -->
<%
	String Rpt = (String) request.getAttribute("Rpt");
	String op = (String) request.getAttribute("op");
	String d__mode = (String) request.getAttribute("d__mode");
	String d__ky = (String) request.getAttribute("d__ky");
	String d__auto = (String) request.getAttribute("d__auto");
	String level2 = (String) request.getAttribute("level2");
	if (d__mode == null || d__mode.equals(""))
		d__mode = "";
	//System.out.println("baselayout: "+d__ky);
%>
<SCRIPT LANGUAGE=javascript>
var Req='<%=Rpt%>';
var level2=<%=level2%>;
var d__mode="<%=d__mode%>";
if(d__mode!="")Req=d__mode;
var op="<%=op%>";
var d__ky='<%=d__ky%>';
var d__auto='<%=d__auto%>';
	$(document).ready(function() {
		if ($.browser.msie && $.browser.version < 11)
			$('select.wide').bind('focus mouseover', function() {
				$(this).addClass('expand').removeClass('clicked');
			}).bind('click', function() {
				$(this).toggleClass('clicked');
			}).bind('mouseout', function() {
				if (!$(this).hasClass('clicked')) {
					$(this).removeClass('expand');
				}
			}).bind('blur', function() {
				$(this).removeClass('expand clicked');
			});
	});
</script>
<script>
	$(document).ready(function() {
		$('input').each(function() {
			if (new RegExp(/Date/).exec($(this).attr('id')) != null) {
				$(this).datepicker({
					dateFormat : 'dd-mm-yy'
				});
			}

		});
	});
	
	var countClicks = 0;
</script>
</head>
<body>

	<!-- <div class="conainer-fixed">
		<div class="row"> -->

	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<tiles:insert attribute="menu" />
		</nav>

		<!-- Page Content Holder -->
		<div id="content">
			<nav class="navbar" style="background-color: #9099A2">
				<div class="container-fluid">

					<%-- <tiles:insert attribute="header" /> --%>
					<div class="row">
						<div class="btn-group center-block pull-right">

							<a href="#menuCollapse" class="btn btn-default"><span
								id="menuCollapse">Menu</span></a> <a href="#"
								class="btn btn-default">Home</a> <a href="loginAction.do"
								class="btn  btn-default">Help</a> 
								<a href="${pageContext.request.contextPath}/welcome.jsp" class="btn  btn-warning">Logout</a>

						</div>
					</div>
				</div>
			</nav>
			<div class="container-fluid center-block" style="min-height: 79%; background-color: #F2F2F2;" >
				<tiles:insert attribute="body" />
			</div>

<%-- 
			<div class="container-fluid" style="background-color: #9099A2;">
				<div class="row ">
				<div class="col-lg-2">
					<font color="white"><tiles:getAsString
							name="title" ignore="true" /></font></div>
							<p class="col-lg-2 center-block " style="color: #FFFFFF" id="td_mode"></p>
					<div class="col-lg-4 center-block">
						<tiles:insert attribute="toolbar" />
					</div>
					<div class="col-lg-4">
					<a href="changePasswordAction.do?menuId=LRD001&d__mode='ent_add'"><img
						src="images/change_password.png" height="25px" width="150px" /> </a></div>
						
				</div>
				
			</div> --%>
			
			<div class="container-fluid" style="background-color: #9099A2;">
				<div class="row ">
				<div class="col-lg-2">
					<font color="white"><tiles:getAsString
							name="title" ignore="true" /></font></div>
							<p class="col-lg-2 center-block " style="color: #FFFFFF" id="td_mode"></p>
					<div class="col-lg-4 center-block">
						<tiles:insert attribute="toolbar" />
					</div>
					<div class="col-lg-4">
					<!-- changePasswordAction.do?menuId=LRD001&d__mode='ent_add' -->
					<a href="#" class="btn btn-success text-center center-block" style="width:150px;">Change Password </a></div>
						
				</div>
				
			</div>
			 <div class="container-fluid" style="background-color: #646b71">
				<tiles:insert attribute="footer" />
			</div>

		</div>
	</div>

	<!-- 
	<tr bgcolor="#3586BE" height="5px" style="color: #FFFFFF">
		<td>
		<table width="100%">
			<tr>
				<td width="20%" nowrap="nowrap"><font color="white"><tiles:getAsString
					name="title" ignore="true" /></font></td>
				<td style="width: 60%;">
				<center><tiles:insert attribute="toolbar" /></center>
				</td>
				<td width="10%"><a
					href="changePasswordAction.do?menuId=LRD001&d__mode='ent_add'">
				<img src="images/change_password.png" height="25px" width="150px" />
				</a></td>
				<td width="10%" id="td_mode" style="color: white;"></td>
			</tr>
		</table>

		</td>
	</tr>
	<tr>
		<td
			style="top: 95%; width: 100%; bottom: 0%; height: 5%; background-color: #00A2E2; border-bottom-style: inset; border-bottom-width: medium; border-bottom-color: #FF9933; color: #FFFFFF;"
			align="center"></td>
	</tr>

 -->
	<!-- jQuery CDN -->
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});

			$('#closemenu').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
			//menuCollapse
			$('#menuCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
		});
	</script>

</body>

</html>