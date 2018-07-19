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
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />



<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<style>
.designlable {
	min-width: 200px;
	max: width: 200px;
}
</style>





</head>


<html:html>

<%-- <logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="environment" style='display: none;'>
			<html:errors bundle="env" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('environment');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent> --%>

<logic:messagesPresent>
	<body bgcolor="#6699FF">

		<p id="environment" style='display: none;'>
			<html:errors bundle="env" />
		</p>
		<script type="text/javascript">
			var para = document.getElementById('environment');
			var text = para.firstChild.nodeValue;
			//alert(text);
			if (text != "") {
				alert("Records Saved Successfully.");
				//localStorage.setItem("edsId", text);
				//alert(text+"Success");
				var sucessurl = "environmentDataCollectionHealthAction.do";
				window.location.href = sucessurl;
					
			}
		</script>
</logic:messagesPresent>
<h4
	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"
	style="visibility: hidden;">Lorem ipsum dolor sit amet,
	consectetur adipiscing elit. Suspendisse a dui erat. Vivamus in maximus
	neque, et elementum quam. Vivamus in maximus neque, et elementum quam.
</h4>

<html:form action="environmentDataCollectionSocialAction" method="post"
	styleId="environmentDataCollectionSocialForm">  

<!--  Section III Social Envronment -->
	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Social Environment</h4>
			<div class="line"></div>

			<!-- Population and  and Number of HouseHolds -->
			<div class="col-lg-12" >

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.population" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="population" styleId="population"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.numberHouseholds" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="numberHouseholds" styleId="numberHouseholds"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:text>
				</div>



			</div>
			<!-- Population and  and Number of HouseHolds -->


			<!-- Land Used Pattren, Historical Religious Importance  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.pattrenLandUsed" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="pattrenLandUsed" styleId="pattrenLandUsed"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.religiousImportance" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="religiousImportance"
						styleId="religiousImportance" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>



			</div>
			<!-- Land Used Pattren, Historical Religious Importance  -->

			<!-- Income Source  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.incomeSourse" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="incomeSourse" styleId="incomeSourse"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6" style="visibility: hidden;">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.extra" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="extra" styleId="extra" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>



			</div>
			<!-- Income Source  -->

			<!-- Natural Habits and Cultural Properties  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.naturalCultural" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="naturalCultural" styleId="naturalCultural"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.naturalCulturalText" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="naturalCulturalText"
						styleId="naturalCulturalText" rows="3" style="width: 200px;"
						styleClass="cs2 form-control">

					</html:textarea>
				</div>



			</div>
			<!-- Natural Habits and Cultural Properties  -->

			<!-- Rights Water  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.rightsWater" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="rightsWater" styleId="rightsWater"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.rightsWaterText" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="rightsWaterText" styleId="rightsWaterText"
						rows="3" style="width: 200px;" styleClass="cs2 form-control">

					</html:textarea>
				</div>



			</div>
			<!-- Rights Water -->


				
			<div class="col-lg-12" style="margin-top: 5px;">
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="edsId" styleId="edsId"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

					</html:text>
				</div>
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="eds_social_env_id" styleId="eds_social_env_id"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

					</html:text>
				</div>
				
				
				</div>
			
			
	<!-- Proceed Button -->
	<div class="row" style="margin-top: 10px;">
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
		
		<div class="col-lg-10 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning pull-right" style="width: 150px;"
				onclick="de_add()">Save and Continue</button>
		</div>

		<!-- <div class="col-lg-5 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning " style="width: 150px;"
				onclick="de_add_coninue()">Save and Continue</button>
		</div> -->



		
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>



	</div>


		</div>
	</div>
	<!--  Section II Ends -->


	

</html:form>
</body>
<script type='text/javascript'> 
	$(window).load(
			function() {
				
				
				
				refreshData();
				
				var appId = localStorage.getItem("edsId");
				//alert("App Id Inside Other Page"+appId);
				document.getElementById("edsId").value = appId;
				
				
				
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'rightsWater');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'naturalCultural');/* 
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'waterBourne');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'vectorBourne'); */
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'religiousImportance');  
				
				ajaxFunction('GetFilterValues.to?parentComboId=2039&method=getCombo', 'pattrenLandUsed');
				ajaxFunction('GetFilterValues.to?parentComboId=2042&method=getCombo', 'incomeSourse');/* 
				ajaxFunction('GetFilterValues.to?parentComboId=2049&method=getCombo', 'waterBourneName');
				ajaxFunction('GetFilterValues.to?parentComboId=2052&method=getCombo', 'vectorBourneText'); */
				
				
				//Run Ajax to Check weather the data exis or not
				//Call Ajax and send Random Number to the corresponding Mobile Number
				 $.ajax({
			type : "POST",
			async: true,
			url : "environmentDataCollectionSocialAction.do?method=checkData",
			data : {
				appId : appId
						
					},
			success : function(data) {
				
				
					if(data.length>50){
						jsonObject = JSON.parse(data);
						//Parde the object and set the object
						setDataToFom(jsonObject);
					}
						
			
				
				
				
			}
		
		});
				
				

			});
	
	
	
	
	
	function refreshData(){
		document.getElementById("population").value= "";
		document.getElementById("numberHouseholds").value= "";
		document.getElementById("pattrenLandUsed").value= "";
		document.getElementById("religiousImportance").value= "";
		document.getElementById("incomeSourse").value= "";
		document.getElementById("incomeSourse").value= "";
		document.getElementById("naturalCultural").value= "";
		document.getElementById("naturalCulturalText").value= "";
		document.getElementById("rightsWater").value= "";
		document.getElementById("rightsWaterText").value= "";
		document.getElementById("edsId").value= "";
		document.getElementById("eds_social_env_id").value= "";
		
	}
	
	
	function setDataToFom(data){
		
		document.getElementById("population").value= data.population;
		document.getElementById("numberHouseholds").value= data.numberHouseholds;
		document.getElementById("pattrenLandUsed").value= data.pattrenLandUsed;
		document.getElementById("religiousImportance").value= data.religiousImportance;
		document.getElementById("incomeSourse").value= data.incomeSourse;
		document.getElementById("naturalCultural").value= data.naturalCultural;
		document.getElementById("naturalCulturalText").value= data.naturalCulturalText;
		document.getElementById("rightsWater").value= data.rightsWater;
		document.getElementById("rightsWaterText").value= data.rightsWaterText;
		document.getElementById("edsId").value= data.edsId;
		document.getElementById("eds_social_env_id").value= data.eds_social_env_id;
		
		

		

	
	}
	
	function de_add() {
		
		//var jsonObject
		if(jsonObject==null){
			//add
			if (true) {
			
			document.environmentDataCollectionSocialForm.action = "environmentDataCollectionSocialAction.do?method=save&edsID="
					+ appId ;
			document.environmentDataCollectionSocialForm.submit();
	
				}
		}else{
			
			 if (true) {
				 
				document.environmentDataCollectionSocialForm.action = "environmentDataCollectionSocialAction.do?method=update&edsID="
						+ appId ;
				document.environmentDataCollectionSocialForm.submit();
		
					}
			 
		}
		
		
}

	
</script>
</html:html>