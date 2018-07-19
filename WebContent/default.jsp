<%@page import="java.util.List"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page language="java" session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<head>



<script>
/* $('#masters').hover(function() { alert("Hi"); }
	    );
	    
	$(document).ready(function() {
		$("#accordion").accordion({
			collapsible : true,
			autoHeight : false,
			active : false
   
		}); */
		
	
	
	
		   
	

	//});
		
</script>

</head>
<body>
	<!-- <div
		class="col-lg-2 col-sm-10 col-md-10 col-xs-10 text-center center-block"
		style="visibility: hidden; height: 0px;">
		<br>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
			do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
			enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
			ut aliquip ex ea commodo consequat. Duis aute irure dolor in
			reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
			pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
			culpa qui officia deserunt mollit anim id est laborum.</p>


	</div> -->
	<div class="panel panel-primary">
		<div class="panel-body">
			<img src="images/logoWSS.jpg" class=" center-block col-lg-5 "
				style="max-height: 150px; max-width: 150px;"></img>
			<p
				class="img-responsive center-block col-lg-7 hidden-xs hidden-sm text-center text-primary col-lg-7"
				style="max-height: 150px;">&nbsp; &nbsp;</p>
			<p
				class="img-responsive center-block col-lg-7 hidden-xs hidden-sm text-center text-primary col-lg-3"
				style="max-height: 150px;">&nbsp; &nbsp;</p>
			<p
				class="img-responsive center-block col-lg-7 text-center text-primary col-lg-7 centrale"
				style="max-height: 150px; font-size: 16px; font-weight: bold;">
				Department of Punjab Rural Water Supply & Sanitation</p>
		</div>
	</div>



	<div class="row">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-3">
			<section class="panel panel-primary panel-body">
				<div class="col-lg-4 ">
					<img class="pull-right" alt="" src="images/safe_water.png"
						style="max-width: 50px; max-height: 50px;">
				</div>
				<div class="col-lg-8 ">
					<p class="pull-left text-primary"
						style="margin-left: -20; margin-top: 10px;">Safe Drinking
						Water</p>
				</div>

			</section>
		</div>

		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-3">
			<section class="panel panel-primary panel-body">
				<div class="col-lg-4 ">
					<img class="pull-right" alt="" src="images/awareness_water.png"
						style="max-width: 50px; max-height: 50px;">
				</div>
				<div class="col-lg-8 ">
					<p class="pull-left text-primary"
						style="margin-left: -20; margin-top: 10px;">Water Awaeness</p>
				</div>

			</section>
		</div>

		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-3">
			<section class="panel panel-primary panel-body">
				<div class="col-lg-4 ">
					<img class="pull-right" alt="" src="images/hygiene_water.png"
						style="max-width: 50px; max-height: 50px;">
				</div>
				<div class="col-lg-8 ">
					<p class="pull-left text-primary"
						style="margin-left: -20; margin-top: 10px;">Improve Hygiene</p>
				</div>

			</section>
		</div>

		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-3">
			<section class="panel panel-primary panel-body">
				<div class="col-lg-4 ">
					<img class="pull-right" alt="" src="images/security_water.png"
						style="max-width: 50px; max-height: 50px;">
				</div>
				<div class="col-lg-8 ">
					<p class="pull-left text-primary"
						style="margin-left: -20; margin-top: 10px;">Water Security</p>
				</div>

			</section>
		</div>



	</div>



	<div class="row">



		<a href="/PRWSS_MIS/locationMasterAction.do?menuId=ADM014"><div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/admin.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Admin</p>
					</div>
				
			</section>
		</div></a>
		
<a href="/PRWSS_MIS/receiveSampleAction.do?menuId=WQ001">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6" >
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/security_water.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Water Quality</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/finance.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Finance</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/hr.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">HR</p>
					</div>
				
			</section>
		</div>
</a>






<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/procurement.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Procurement</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/tender.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Tender</p>
					</div>
				
			</section>
		</div>
</a>



<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/inventory.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Inventory</p>
					</div>
				</a>
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/sdu.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">SDU</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/service.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Services</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/pms.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">PMS</p>
					</div>
				
			</section>
		</div>
		</a>

<a href="#">
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6">
			<section class="panel panel-danger panel-body">
				
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/dak_task.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Dak & Task</p>
					</div>
				
			</section>
		</div>
		</a>

	<a href="#" >
		<div class="col-lg-3 col-sm-6 col-xs-6 col-md-6 "  >

			<section class="panel panel-danger panel-body">
			
					<div class="col-lg-4 ">
						<img class="pull-right" alt="" src="images/masters.png"
							style="max-width: 50px; max-height: 50px;">
					</div>
					<div class="col-lg-8 ">
						<p class="pull-left text-primary"
							style="margin-left: -20; margin-top: 10px;">Masters</p>
					</div>
				
			</section>

		</div>
</a>
	</div>






	<div id="accordion">
		<logic:present name="NotificationApprovalBeanList" scope="request">
			<logic:notEmpty name="NotificationApprovalBeanList" scope="request">
				<h3 id="h11">
					<a href="#"><b>Pending For Approval</b></a>
				</h3>
				<div id="div1">
					<left> <logic:present name="NotificationApprovalBeanList"
						scope="request">
						<logic:notEmpty name="NotificationApprovalBeanList"
							scope="request">
							<left> <display:table name="NotificationApprovalBeanList"
								id="approval" class="mars"
								style="margin:0 0em 0em 0; width:520px" pagesize="3"
								requestURI="/homeAction.do">
								<display:column property="shortMessage" title="Subject" />
								<display:column property="openedDate" title="Since"
									decorator="com.prwss.mis.common.util.DateColumnDecorator" />
							</display:table> </left>
						</logic:notEmpty>
						<logic:empty name="NotificationApprovalBeanList" scope="request">
							<script>
								document.getElementById('h11').style.display = "none";
							</script>
						</logic:empty>
					</logic:present> </left>
				</div>
			</logic:notEmpty>
		</logic:present>
		<logic:present name="NotificationTaskBeanList" scope="request">
			<logic:notEmpty name="NotificationTaskBeanList" scope="request">
				<h3 id="h33">
					<a href="#"><b>Task</b></a>
				</h3>
				<div id="div3">
					<center>

						<center>
							<display:table name="NotificationTaskBeanList" id="taskPending"
								class="mars" pagesize="3" requestURI="/homeAction.do"
								style="margin:0 1em 1em 0; width:350px">

								<display:column property="shortMessage" title="Subject" />
								<display:column property="openedDate" title="Date"
									decorator="com.prwss.mis.common.util.DateColumnDecorator" />

							</display:table>
						</center>

						<logic:empty name="NotificationTaskBeanList" scope="request">
							<script>
								document.getElementById('h33').style.display = "none";
							</script>
						</logic:empty>
					</center>

				</div>
			</logic:notEmpty>
		</logic:present>
		<logic:present name="NotificationBeanList" scope="request">
			<logic:notEmpty name="NotificationBeanList" scope="request">
				<h3 id="h22">
					<a href="#"><b>Notification</b></a>
				</h3>
				<div id="div2">
					<center>

						<center>
							<display:table name="NotificationBeanList" id="notification"
								class="mars" pagesize="3" requestURI="/homeAction.do"
								style="margin:0 .5em .5em 0; width:400px">
								<display:column property="shortMessage" title="Subject" />
								<display:column property="openedDate" title="Date"
									decorator="com.prwss.mis.common.util.DateColumnDecorator" />
							</display:table>
						</center>

						<logic:empty name="NotificationBeanList" scope="request">
							<script>
								document.getElementById('h22').style.display = "none";
							</script>
						</logic:empty>
					</center>
				</div>
			</logic:notEmpty>
		</logic:present>
		<logic:present name="MessageBrodcastList" scope="request">
			<logic:notEmpty name="MessageBrodcastList" scope="request">
				<h3 id="h44">
					<a href="#"><b>Message Broadcast</b></a>
				</h3>
				<div id="div4">
					<center>

						<center>
							<display:table name="MessageBrodcastList" id="messageBroadcast"
								class="mars" pagesize="10" requestURI="/homeAction.do"
								style="margin:0 .5em .5em 0; width:400px">
								<display:column property="messageDetail" title="Message" />

							</display:table>
						</center>

						<logic:empty name="MessageBrodcastList" scope="request">
							<script>
								document.getElementById('h44').style.display = "none";
							</script>
						</logic:empty>
					</center>
				</div>
			</logic:notEmpty>
		</logic:present>
	</div>




</body>