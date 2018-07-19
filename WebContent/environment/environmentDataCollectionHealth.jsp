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
				var sucessurl = "environmentDataCollectionWaterSupplyAction.do";
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

<html:form action="environmentDataCollectionHealthAction" method="post"
	styleId="environmentDataCollectionHealthForm">


<!-- Section III -->
	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Public Health Issues</h4>
			<div class="line"></div>
			
			
			<!-- Water Borne Desease  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterBourne" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="waterBourne" styleId="waterBourne"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterBourneName" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select  multiple="multiple" property="waterBourneName" styleId="waterBourneName"
					 style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>



			</div>
			<!-- Water Borne Desease  -->
			
			<!-- Vector Borne Desease  -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.vectorBourne" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select  property="vectorBourne" styleId="vectorBourne"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.vectorBourneText" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select multiple="multiple" property="vectorBourneText" styleId="vectorBourneText"
					 style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>



			</div>
			<!-- Vector Borne Desease  -->
			
				<div class="col-lg-12" style="margin-top: 5px;">
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="edsId" styleId="edsId"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

					</html:text>
				</div>
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="eds_pblc_hlth_id" styleId="eds_pblc_hlth_id"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

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

	<!-- Section III -->




</html:form>

</body>
<script type='text/javascript'> 
	$(window).load(
			function() {
				
				
				
				refreshData();
				
				var appId = localStorage.getItem("edsId");
				//alert("App Id Inside Other Page"+appId);
				document.getElementById("edsId").value = appId;
				
				
				
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'waterBourne');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'vectorBourne'); 
				ajaxFunction('GetFilterValues.to?parentComboId=2049&method=getCombo', 'waterBourneName');
				ajaxFunction('GetFilterValues.to?parentComboId=2052&method=getCombo', 'vectorBourneText'); 
				
				
				//Run Ajax to Check weather the data exis or not
				//Call Ajax and send Random Number to the corresponding Mobile Number
				 $.ajax({
			type : "POST",
			async: true,
			url : "environmentDataCollectionHealthAction.do?method=checkData",
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
		
		
		document.getElementById("waterBourne").value= "";
		document.getElementById("waterBourneName").value= "";
		document.getElementById("vectorBourne").value= "";
		document.getElementById("vectorBourneText").value= "";
		
		document.getElementById("edsId").value= "";
		document.getElementById("eds_pblc_hlth_id").value= "";
		
	}
	
	
	function setDataToFom(data){
		
		
		document.getElementById("waterBourne").value= data.waterBourne;
		document.getElementById("waterBourneName").value= data.waterBourneName;
		document.getElementById("vectorBourne").value= data.vectorBourne;
		document.getElementById("vectorBourneText").value= data.vectorBourneText;
		document.getElementById("edsId").value= data.edsId;
		document.getElementById("eds_pblc_hlth_id").value= data.eds_pblc_hlth_id;
	
	}
	
	function de_add() {
		
		//var jsonObject
		if(jsonObject==null){
			//add
			if (true) {
			
			document.environmentDataCollectionHealthForm.action = "environmentDataCollectionHealthAction.do?method=save&edsID="
					+ appId ;
			document.environmentDataCollectionHealthForm.submit();
	
				}
		}else{
			
			 if (true) {
				 
				document.environmentDataCollectionHealthForm.action = "environmentDataCollectionHealthAction.do?method=update&edsID="
						+ appId ;
				document.environmentDataCollectionHealthForm.submit();
		
					}
			 
		}
		
		
}

	
</script>
</html:html>