<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
    $( "#datepicker1" ).datepicker();
    $( "#datepicker2" ).datepicker();
    $( "#datepicker3" ).datepicker();
    $( "#datepicker4" ).datepicker();
    $( "#datepicker5" ).datepicker();
    $( "#datepicker6" ).datepicker();
    $( "#datepicker11" ).datepicker();
    $( "#datepicker12" ).datepicker();
    $( "#datepicker19" ).datepicker();
  } );
  
  
  function validateDate(selectedDate,elmnt){
	
		
		var d4 = new Date();
		var x = selectedDate.split("/");
		var dt1 = d4.getDate();
		var mon1 = d4.getMonth() + 1;
		var yr1 = d4.getFullYear();
		var ndate = new Date(x[2], (x[0] - 1), x[1]);
		var ldate = new Date(yr1, (mon1-1), dt1);

		var pattern =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
		var status=pattern.test(selectedDate);
		if(status){
			if(ndate>ldate){
				document.getElementById(elmnt).value='';
				alert("Date should be less than current date");
				return false;
			}
		}
		if(!status){
			document.getElementById(elmnt).value='';
			alert("Please enter date in MM/dd/yyyy format");
			return false;
		}
		return true;
  	}
	  
  </script>
<style>
input[type=text] {
	width: 130px;
}

/* td { */
/* 	text-align: left; */
/* 	vertical-align: top; */
/* 	width: auto; */
/* } */
/* tr{
	margin-left: 15px;
	width:auto;
} */
label {
	display: inline-block;
	width: 160px;
	padding: 5px;
	margin: 2px;
	text-align: left;
	vertical-align: top;
	padding-top: 0px;
}
</style>
<script type="text/javascript">
	function de_find() {
		document.performaMasterForm.action = "performaMasterAction.do?method=find&d__mode="
				+ d__mode + "&menuId=TENRPT080";
		document.performaMasterForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			
			 var result=validateSections();
			
			var result1=validateWorkProposedtobeExecuted();

			if (result && result1) { 
				var result2=validateOhsrDismantling();
				if(result2){
					var result3=validateWaterSupplyStatus();
					if(result3){
						document.performaMasterForm.action = "performaMasterAction.do?method=update&d__mode="
								+ d__mode + "&menuId=TENRPT080";
							document.performaMasterForm.submit();
					}
				}
			}
		}
	}
	function displayGrid() {
		document.getElementById("sourcegrid").style.display = "";
	}
	
	 //document.addEventListener('load', function(){ 
		//window.onload=function(){
		// alert("njjhhj");
		// var x = document.forms[0]['schemeSource'].value;
		$(window).load(function(){
			
		 if(document.getElementById("multipleSource").value===''||document.getElementById("multipleSource").value==='No'){
			document.getElementById("sourcegrid").style.display='none';
		}
		if(document.getElementById("multipleSource").value==='Yes'){
			document.getElementById("sourcegrid").style.display ='';
		} 
		//schemeOperatedBy
		if(document.getElementById('schemeOperatedBy')==='DWSS'){
			de_kyenable(true,'staffScheme');
		}else{
			document.getElementById('staffScheme').value='';
			disable_ctrl('staffScheme',true);
		}
		 /* if(document.getElementById("noOfOhsr").value==="0"){
			   disable_ctrl("datepicker2~oHSRCapcity~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~oHSRCT~oHSRUGSR",true); 
		}if(document.getElementById("noOfOhsr").value!="0"){
		  	de_kyenable(true,"datepicker2@oHSRCapcity@oHSRFullSuplyLvl@oHSRWorkingCondition@oHSRCT@oHSRUGSR"); 
		}  */
		
		  var x = document.getElementById('schemeSource').value;
		  	disable_ctrl("schemeExpenditure~Metered_connection~Metered_supply_recovery~Flat_rate_charges_per_month~scheme_NonFunctional~datepicker11~Household_water_connection",true);
			disable_ctrl('Ground_water_potable_No~Preventive_measures_adopted~Capacity_of_plant~datepicker4~Being_operated_by~Volume_of_Water_Daily_basis~disposal_of_reject_water~Penetration_in_percentage~present_rate_of_user_charges~Seperate_Sanctioned_Load~Seperate_Pending_eletric_bill31032017~Average_monthly_bill_of_Treatment_plant',true);                                                                                                                                                                                      
		   if(x === 'TUBEWELL'){
			   disable_ctrl("inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater~oHSRCondition~oHSRDismantling",true);	
			   }
		   else if(x=='CANAL'){
				  	 disable_ctrl('depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery~oHSRCondition~oHSRDismantling',true);
				   }
		   else if(x=='HANDPUMP'){
			   disable_ctrl('depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery',true);
			    disable_ctrl("inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater",true);	
			   disable_ctrl("sanctionedLoad~pendingBill3103~pendingBill3006~avgMonthBillofWSS",true);
			   disable_ctrl("Household_water_connection~Watered_connection~Metered_connection~Metered_supply_recovery~Flat_rate_charges_per_month~Arrear_of_recovery_with_consumers_as01042017",true);
			   disable_ctrl('Pipeline_AC~Pipeline_MS_DI_Ci~Pipeline_GI~Pipeline_PVC~Functional_Distribution_percentage',true);
			   disable_ctrl("noOfOhsr~datepicker2~oHSRCapcity~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~oHSRCT~oHSRUGSR",true); 
			}
		   disable_ctrl("disinfectionType~datepicker3~disinfectionPrStatus",true);
		   disable_ctrl("canal_based~oHSR_Capacity1~otherStructure~distrNetwork1~mtrConnection~disincUnit~insWaterPlant1~otherWork~Driling_of_new_tubewell_machinery22",true);

		   disable_ctrl("Independent_New_WSS~Upgradation_of_existing_WSS~Instltion_Wtr_Treatment~source_of_WSS~shifted_to_canal_from_Other",true);
		   disable_ctrl("Driling_of_new_tubewell_machinery_size~Driling_of_new_tubewell_machinery_depth~Driling_of_new_tubewell_machinery_capacity~Driling_of_new_tubewell_machinery_cost",true);
		   disable_ctrl('canal_based_Inlet_channel_Size_of_pipe~canal_based_Inlet_channel_length~canal_based_s_and_s_Capacity~canal_based_Filteration_Plan_type~canal_based_Filteration_Plan_capacity~canal_based_Cost',true);
		   disable_ctrl('oHSR_Capacity~OHSR_Full_Supply_Level~OHSR_Cost',true);
		   disable_ctrl('Other_structures_at_waterworks~Other_structures_at_waterworks_Cost',true);
		   disable_ctrl('Distribution_WSS_to_village_type~Distribution_WSS_to_village_length~Distribution_WSS_within_village_type~Distribution_WSS_within_village_length~distribution_Cost',true);
		   disable_ctrl('No_of_connections_100_mtr~No_of_mtrs_100_mtr~Distribution_WSS_to_village_length~Cost_100_mtr',true);
		   disable_ctrl('Disinfection_Unit_Type~Disinfection_Unit_Cost',true);
		   disable_ctrl('Water_Treatment_plant_in_case_of_quality_village_type~Water_Treatment_plant_in_case_of_quality_village_capacity~Water_Treatment_plant_in_case_of_quality_village_Cost',true);
		   disable_ctrl('Bulk_Water_meter_no~Bulk_Water_meter_cost~Extension_Sanction_of_new_electric_connection_cost',true);

	   });
	 
	
	 
	  function validateVillageGrid(){
							var status;
							status=validateFiveYearPlann();
							if(status){
								StrutsLayout.addDatagridLine('fiveYearPlanGridBean','villaggesId~Independent_New_WSS~Upgradation_of_existing_WSS~Instltion_Wtr_Treatment~source_of_WSS~shifted_to_canal_from_Other~Driling_of_new_tubewell_machinery_size~Driling_of_new_tubewell_machinery_depth~Driling_of_new_tubewell_machinery_capacity~Driling_of_new_tubewell_machinery_cost~canal_based_Inlet_channel_Size_of_pipe~canal_based_Inlet_channel_length~canal_based_s_and_s_Capacity~canal_based_Filteration_Plan_type~canal_based_Filteration_Plan_capacity~canal_based_Cost~clear_water_tank~oHSR_Capacity~oHSRCTDiameter~oHSR_CT_Depth~oHSR_UGS_RDia~oHSR_UGSR_Depth~OHSR_Full_Supply_Level~OHSR_Cost~Other_structures_at_waterworks~Other_structures_at_waterworks_Cost~Distribution_WSS_to_village_type~Distribution_WSS_to_village_length~distribution_WSS_within_village_type_pvc~distribution_WSS_within_village_type_di_ms~distribution_WSS_within_village_type_gi~distribution_Cost~No_of_connections_100_mtr~No_of_mtrs_100_mtr~Cost_100_mtr~Disinfection_Unit_Type~Disinfection_Unit_Cost~Water_Treatment_plant_in_case_of_quality_village_type~Water_Treatment_plant_in_case_of_quality_village_capacity~Water_Treatment_plant_in_case_of_quality_village_Cost~Bulk_Water_meter_no~Bulk_Water_meter_cost~Extension_Sanction_of_new_electric_connection_cost');  
							}
					}
	  
	  function validateSourceGrid(){
			 
					if(document.getElementById('schemeSrc').value===''){
						 var result=validateSectionssss();
						if(result){
								document.getElementById('villId').value='';
								//document.getElementById('schemeSrc').value=document.getElementById('schemeSource').value;
								 StrutsLayout.addDatagridLine('villageSourceGrid','villId~schemeSource~schemeType~dateOfComm~progId~serviceLevel~datepicker19~schemeExpenditure~depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery~inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater~noOfOhsr~datepicker2~oHSRCapcity~oHSRCTDia~oHSRCTDepth~oHSRUGSRDia~oHSRUGSRDepth~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~Ground_water_potable_status~Ground_water_potable_No~Preventive_measures_adopted~Capacity_of_plant~datepicker4~Being_operated_by~Volume_of_Water_Daily_basis~disposal_of_reject_water~Penetration_in_percentage~present_rate_of_user_charges~Seperate_Sanctioned_Load~Seperate_Pending_eletric_bill31032017~Average_monthly_bill_of_Treatment_plant~disinfectionType~datepicker3~disinfectionPrStatus~schemeOperatedBy~staffScheme~sanctionedLoad~pendingBill3103~pendingBill3006~avgMonthBillofWSS');
								 de_kyenable(true,'villId@schemeSrc');
						}
					}
					if(document.getElementById('schemeSrc').value!=''){
						var result1=validateSectionsss();
						if(result1){
							StrutsLayout.addDatagridLine('villageSourceGrid','villId~schemeSrc~schemeType~dateOfComm~progId~serviceLevel~datepicker19~schemeExpenditure~depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery~inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater~noOfOhsr~datepicker2~oHSRCapcity~oHSRCTDia~oHSRCTDepth~oHSRUGSRDia~oHSRUGSRDepth~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~Ground_water_potable_status~Ground_water_potable_No~Preventive_measures_adopted~Capacity_of_plant~datepicker4~Being_operated_by~Volume_of_Water_Daily_basis~disposal_of_reject_water~Penetration_in_percentage~present_rate_of_user_charges~Seperate_Sanctioned_Load~Seperate_Pending_eletric_bill31032017~Average_monthly_bill_of_Treatment_plant~disinfectionType~datepicker3~disinfectionPrStatus~schemeOperatedBy~staffScheme~sanctionedLoad~pendingBill3103~pendingBill3006~avgMonthBillofWSS');
						}
				}
			
	  }
	  
	 
	  function disableField(){
		  var x = document.getElementById('schemeSrc').value;
			 
			disable_ctrl('Ground_water_potable_No~Preventive_measures_adopted~Capacity_of_plant~datepicker4~Being_operated_by~Volume_of_Water_Daily_basis~disposal_of_reject_water~Penetration_in_percentage~present_rate_of_user_charges~Seperate_Sanctioned_Load~Seperate_Pending_eletric_bill31032017~Average_monthly_bill_of_Treatment_plant',true);                                                                                                                                                                                      
			
		   if(x === 'TUBEWELL'){
			   disable_ctrl("inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater",true);	
			   de_kyenable(true,'depthOfTubewell@sizeOfTubewell@datepicker6@dischargeComm@presentDis@presentSpringLevel@datepicker@capacity_of_machinery');
				de_kyenable(true,"sanctionedLoad@pendingBill3103@pendingBill3006@avgMonthBillofWSS");
			  	de_kyenable(true,"Household_water_connection@Watered_connection@Arrear_of_recovery_with_consumers_as01042017");
			  	de_kyenable(true,'Pipeline_AC@Pipeline_MS_DI_Ci@Pipeline_GI@Pipeline_PVC@Functional_Distribution_percentage');
			  	de_kyenable(true,"noOfOhsr@datepicker2@oHSRCapcity@oHSRFullSuplyLvl@oHSRWorkingCondition@oHSRCT@oHSRUGSR"); 
				null_ctrl('inletType$inlet_channel_Lenght$pipe_type$capacitySSTank$capacityHLTank$capacityCWTank$filtertion_type$filtertionNo$capacityRawWater$capacityClearWater');	
		   }
		   else if(x=='CANAL'){
				  	disable_ctrl('depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery',true);
				  	de_kyenable(true,"inletType@inlet_channel_Lenght@pipe_type@capacitySSTank@capacityHLTank@capacityCWTank@filtertion_type@filtertionNo@capacityRawWater@capacityClearWater");	
				  	de_kyenable(true,"sanctionedLoad@pendingBill3103@pendingBill3006@avgMonthBillofWSS");
				  	de_kyenable(true,"Household_water_connection@Watered_connection@Metered_connection@Metered_supply_recovery@Flat_rate_charges_per_month@Arrear_of_recovery_with_consumers_as01042017");
				  	de_kyenable(true,'Pipeline_AC@Pipeline_MS_DI_Ci@Pipeline_GI@Pipeline_PVC@Functional_Distribution_percentage');
				  	de_kyenable(true,"noOfOhsr@datepicker2@oHSRCapcity@oHSRFullSuplyLvl@oHSRWorkingCondition@oHSRCT@oHSRUGSR"); 
					 null_ctrl('depthOfTubewell$sizeOfTubewell$datepicker6$dischargeComm$presentDis$presentSpringLevel$datepicker$capacity_of_machinery');

					
		   }
		   else if(x=='HANDPUMP'){
			   disable_ctrl('depthOfTubewell~sizeOfTubewell~datepicker6~dischargeComm~presentDis~presentSpringLevel~datepicker~capacity_of_machinery',true);
			    disable_ctrl("inletType~inlet_channel_Lenght~pipe_type~capacitySSTank~capacityHLTank~capacityCWTank~filtertion_type~filtertionNo~capacityRawWater~capacityClearWater",true);	
			   disable_ctrl("sanctionedLoad~pendingBill3103~pendingBill3006~avgMonthBillofWSS",true);
			   disable_ctrl("Household_water_connection~Watered_connection~Metered_connection~Metered_supply_recovery~Flat_rate_charges_per_month~Arrear_of_recovery_with_consumers_as01042017",true);
			   disable_ctrl('Pipeline_AC~Pipeline_MS_DI_Ci~Pipeline_GI~Pipeline_PVC~Functional_Distribution_percentage',true);
			   disable_ctrl("noOfOhsr~datepicker2~oHSRCapcity~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~oHSRUGSR~oHSRCT",true); 
			   null_ctrl('depthOfTubewell$sizeOfTubewell$datepicker6$dischargeComm$presentDis$presentSpringLevel$datepicker$capacity_of_machinery');
			    null_ctrl('inletType$inlet_channel_Lenght$pipe_type$capacitySSTank$capacityHLTank$capacityCWTank$filtertion_type$filtertionNo$capacityRawWater$capacityClearWater');	
			    null_ctrl('sanctionedLoad$pendingBill3103$pendingBill3006$avgMonthBillofWSS');
			    null_ctrl('Household_water_connection$Watered_connection$Metered_connection$Metered_supply_recovery$Flat_rate_charges_per_month$Arrear_of_recovery_with_consumers_as01042017');
			    null_ctrl('Pipeline_AC$Pipeline_MS_DI_Ci$Pipeline_GI$Pipeline_PVC$Functional_Distribution_percentage');
			    null_ctrl('noOfOhsr$datepicker2$oHSRCapcity$oHSRFullSuplyLvl$oHSRWorkingCondition$oHSRCondition$oHSRDismantling'); 
				
			}
	  }
	 function validateintField(inputtxt){
		 var rexp =/^[0-9.]+$/i;
		 if(rexp.test(inputtxt)){
			 return true; 
			}
		 else  
		  {   
		   alert("Please enter Number");   
		   return false;   
		  }  
	 }
	
	 function enable_ExistingWaterQuality(valueBox){
		 if(valueBox === '1' || valueBox === ''){
				disable_ctrl('Ground_water_potable_No~Preventive_measures_adopted~Capacity_of_plant~datepicker4~Being_operated_by~Volume_of_Water_Daily_basis~disposal_of_reject_water~Penetration_in_percentage~present_rate_of_user_charges~Seperate_Sanctioned_Load~Seperate_Pending_eletric_bill31032017~Average_monthly_bill_of_Treatment_plant',true);                                                                                                                                                                                      
				null_ctrl('Ground_water_potable_No$Preventive_measures_adopted$Capacity_of_plant$datepicker4$Being_operated_by$Volume_of_Water_Daily_basis$disposal_of_reject_water$Penetration_in_percentage$present_rate_of_user_charges$Seperate_Sanctioned_Load$Seperate_Pending_eletric_bill31032017$Average_monthly_bill_of_Treatment_plant');                                                                                                                                                                                      
		 }if(valueBox === '0'){
				 de_kyenable(true,'Ground_water_potable_No@Preventive_measures_adopted@Preventive_measures_adopted@Capacity_of_plant@datepicker4@Being_operated_by@Volume_of_Water_Daily_basis@disposal_of_reject_water@Penetration_in_percentage@present_rate_of_user_charges@Seperate_Sanctioned_Load@Seperate_Pending_eletric_bill31032017@Average_monthly_bill_of_Treatment_plant');
			 }
		}
	 
	 function enableFiveYearGridVillage(obj){
		 
		 if(obj!=null && obj!=""){
			 de_kyenable(true,'Independent_New_WSS@Upgradation_of_existing_WSS@source_of_WSS@shifted_to_canal_from_Other@Instltion_Wtr_Treatment');
		 }
		 if(obj===""){
			 null_ctrl('Independent_New_WSS$Upgradation_of_existing_WSS$source_of_WSS$shifted_to_canal_from_Other$Instltion_Wtr_Treatment');
			 disable_ctrl('Independent_New_WSS~Upgradation_of_existing_WSS~source_of_WSS~shifted_to_canal_from_Other~Instltion_Wtr_Treatment',true);
		 }
	 }
 	
 	function validateFiveYearPlan1(obj){
 		if(obj==="Yes" && obj==="1"){
 			de_kyenable(true,"canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22");
 		}
 	}
 	function validateFiveYearPlan11(obj){
 		if(obj==="Yes"){
 			de_kyenable(true,"insWaterPlant1@otherWork");
 		}
 	}
 	
 	function checkOhsr(obj){
 		if(obj==='0'){
 			de_kyenable(true,'oHSRCondition');
 			
 		}
 		if(obj==='Dismantling'){
				de_kyenable(true,'oHSRDismantling');
		}
 		if(obj==='1'){
 			document.getElementById('oHSRCondition').value="";
 			document.getElementById('oHSRDismantling').value="";
 			
 			disable_ctrl('oHSRCondition',true);
 		}
 		if(obj!='Dismantling'){
 			disable_ctrl('oHSRDismantling',true);
 		}
 		
 	}
 	
 	function validateWaterSupplyStatus(){
 		var status=true;
 		/* if(document.getElementById("Scheme_Functional").value===""){
			alert("Please select Functional");
			return false;
		} */
 		if(document.getElementById("Scheme_Functional").value==="0"){
			if(document.getElementById("datepicker11").value===""){
	 			alert("Please select Time since non-functional");
	 			status=false;
			}
			if(document.getElementById("scheme_NonFunctional").value===""){
	 			alert("Please select If Non Functional (Reason)");
	 			status=false;
			}
		}
 		return status;
 	}
 	function hideUnhideWaterSupplyStatus(){
 		
 		//Scheme_Functional
 		
 		if(document.getElementById('Scheme_Functional').value==='1' ||document.getElementById('Scheme_Functional').value==='0'){
 				document.getElementById('datepicker11').value='';
 				document.getElementById('scheme_NonFunctional').value='';
 				
 				disable_ctrl('datepicker11~scheme_NonFunctional',true);
 			}
 		if(document.getElementById('Scheme_Functional').value==='0'){
 				de_kyenable(true,'datepicker11@scheme_NonFunctional');
		}
 		
 	}
 	function validateFiveYearPlan(){
 		
 		
 		//alert("validateFiveYearPlan()");
 		
 		if(document.getElementById("Independent_New_WSS").value==="1" || document.getElementById("Upgradation_of_existing_WSS").value==="1"
 				|| document.getElementById("Instltion_Wtr_Treatment").value==="Yes"){
 			
 			
 			if(document.getElementById("Driling_of_new_tubewell_machinery22").value==="Yes"){
 				de_kyenable(true,'Driling_of_new_tubewell_machinery_size@Driling_of_new_tubewell_machinery_depth@Driling_of_new_tubewell_machinery_capacity@Driling_of_new_tubewell_machinery_cost');
 			 } 
 			 if(document.getElementById("Driling_of_new_tubewell_machinery22").value==="No"){
 				disable_ctrl('Driling_of_new_tubewell_machinery_size~Driling_of_new_tubewell_machinery_depth~Driling_of_new_tubewell_machinery_capacity~Driling_of_new_tubewell_machinery_cost',true);
 				null_ctrl('Driling_of_new_tubewell_machinery_size$Driling_of_new_tubewell_machinery_depth$Driling_of_new_tubewell_machinery_capacity$Driling_of_new_tubewell_machinery_cost');
 			 } 
  			 
 			if(document.getElementById("canal_based").value==="Yes"){
 				de_kyenable(true,'canal_based_Inlet_channel_Size_of_pipe@canal_based_Inlet_channel_length@canal_based_s_and_s_Capacity@canal_based_Filteration_Plan_type@canal_based_Filteration_Plan_capacity@canal_based_Cost');
 			}
 			if(document.getElementById("canal_based").value==="No"){
 				disable_ctrl('canal_based_Inlet_channel_Size_of_pipe~canal_based_Inlet_channel_length~canal_based_s_and_s_Capacity~canal_based_Filteration_Plan_type~canal_based_Filteration_Plan_capacity~canal_based_Cost',true);
 				null_ctrl('canal_based_Inlet_channel_Size_of_pipe$canal_based_Inlet_channel_length$canal_based_s_and_s_Capacity$canal_based_Filteration_Plan_type$canal_based_Filteration_Plan_capacity$canal_based_Cost');
 			}
 			
 			if(document.getElementById("oHSR_Capacity1").value==="Yes"){
 				de_kyenable(true,'oHSR_Capacity@OHSR_Full_Supply_Level@OHSR_Cost');
 			}
 			
 			if(document.getElementById("oHSR_Capacity1").value==="No"){
 				disable_ctrl('oHSR_Capacity~OHSR_Full_Supply_Level~OHSR_Cost',true);
 				null_ctrl('oHSR_Capacity$OHSR_Full_Supply_Level$OHSR_Cost');
			
 			}
 			if(document.getElementById("otherStructure").value==="Yes"){
 				de_kyenable(true,'Other_structures_at_waterworks@Other_structures_at_waterworks_Cost');
 			}
 			if(document.getElementById("otherStructure").value==="No"){
 				disable_ctrl('Other_structures_at_waterworks~Other_structures_at_waterworks_Cost',true);

 				null_ctrl('Other_structures_at_waterworks$Other_structures_at_waterworks_Cost');
 			}
 			
 			if(document.getElementById("distrNetwork1").value==="Yes"){
 				de_kyenable(true,'Distribution_WSS_to_village_type@Distribution_WSS_to_village_length@distribution_WSS_within_village_type_pvc@distribution_WSS_within_village_type_di_ms@distribution_WSS_within_village_type_gi@distribution_Cost');
 			}
 			
 			if(document.getElementById("distrNetwork1").value==="No"){
 				disable_ctrl('Distribution_WSS_to_village_type~Distribution_WSS_to_village_length~distribution_WSS_within_village_type_pvc~distribution_WSS_within_village_type_di_ms~distribution_WSS_within_village_type_gi~distribution_Cost',true);
 				null_ctrl('Distribution_WSS_to_village_type$Distribution_WSS_to_village_length$Distribution_WSS_within_village_type$Distribution_WSS_within_village_length$distribution_Cost');
 			}
 			
 			
 			if(document.getElementById("mtrConnection").value==="Yes"){
 				de_kyenable(true,'No_of_connections_100_mtr@No_of_mtrs_100_mtr@Cost_100_mtr');  //No_of_mtrs_100_mtr
 			}
 			if(document.getElementById("mtrConnection").value==="No"){
 				disable_ctrl('No_of_connections_100_mtr~No_of_mtrs_100_mtr~Cost_100_mtr',true);  //No_of_mtrs_100_mtr

 				null_ctrl('No_of_connections_100_mtr$No_of_mtrs_100_mtr$Cost_100_mtr');  //No_of_mtrs_100_mtr

 			}
 			
 			if(document.getElementById("disincUnit").value==="Yes"){
 				de_kyenable(true,'Disinfection_Unit_Type@Disinfection_Unit_Cost');
 			}
 			if(document.getElementById("disincUnit").value==="No"){
 				disable_ctrl('Disinfection_Unit_Type~Disinfection_Unit_Cost',true);

 				null_ctrl('Disinfection_Unit_Type$Disinfection_Unit_Cost');

 			}
 			if(document.getElementById("insWaterPlant1").value==="Yes"){
 				de_kyenable(true,'Water_Treatment_plant_in_case_of_quality_village_type@Water_Treatment_plant_in_case_of_quality_village_capacity@Water_Treatment_plant_in_case_of_quality_village_Cost');
 			}
 			
 			if(document.getElementById("insWaterPlant1").value==="No"){
 				disable_ctrl('Water_Treatment_plant_in_case_of_quality_village_type~Water_Treatment_plant_in_case_of_quality_village_capacity~Water_Treatment_plant_in_case_of_quality_village_Cost',true);

 				null_ctrl('Water_Treatment_plant_in_case_of_quality_village_type$Water_Treatment_plant_in_case_of_quality_village_capacity$Water_Treatment_plant_in_case_of_quality_village_Cost');
 			}
 			if(document.getElementById("otherWork").value==="Yes"){
 				de_kyenable(true,'Bulk_Water_meter_no@Bulk_Water_meter_cost@Extension_Sanction_of_new_electric_connection_cost');
 			}
 			if(document.getElementById("otherWork").value==="No"){
 				disable_ctrl('Bulk_Water_meter_no~Bulk_Water_meter_cost~Extension_Sanction_of_new_electric_connection_cost',true);

 				null_ctrl('Bulk_Water_meter_no$Bulk_Water_meter_cost$Extension_Sanction_of_new_electric_connection_cost');
 			}
 		}
 	}
 	
 	function validatedisinfecDetails(){
 		var obj=document.getElementById("disinfecReq").value;
 		if(obj==="Yes"){
				de_kyenable(true,'disinfectionType@datepicker3@disinfectionPrStatus');
 		}if(obj==="No"||obj===""){
 			document.getElementById("disinfectionType").value="";
 			document.getElementById("datepicker3").value="";
 			document.getElementById("disinfectionPrStatus").value="";
 			
 			disable_ctrl('disinfectionType~datepicker3~disinfectionPrStatus',true);
				
 		}
 	}
 	
</script>
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
-->

</head>
<body bgcolor="#6699FF">
	<logic:messagesPresent>
		<div id="modalPeriod"
			style="position: absolute; width: 600px; border: 3px solid black; background-color: #00A2E2; font-size: 150%; text-align: Left; display: none;">
			<table width="600px">
				<tr>
					<td><font size="3" color="white"><b><html:errors
									bundle="pmm" /></b></font></td>
				</tr>
				<tr>
					<td align="center"><input type="button" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;"></td>
				</tr>
			</table>

		</div>
		<script type="text/javascript">
var alertObj=document.getElementById("modalPeriod");
	// center the alert box
if(document.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop+50 + "px";
alertObj.style.left = (document.documentElement.scrollWidth - alertObj.offsetWidth)/4 + "px";
//alert(alertObj.style.left+":"+document.documentElement.scrollWidth+":"+ alertObj.offsetWidth);
hide_ctrl('modalPeriod',false);</script>
	</logic:messagesPresent>
<body bgcolor="#3586BE">

	<html:form action="performaMasterAction" method="POST">

		<%-- 	<logic:messagesPresent>
			<div id="errorDiv" class="error displaynone"
				style="width: 470px%; margin-bottom: 7px; height: 13px;">
				<html:errors bundle="pmm" />
			</div>
		</logic:messagesPresent> --%>
		<logic:messagesPresent message="true">
			<div id="successDiv" class="success diplaynone" style="width: 470px;">
				<html:messages id="message" message="true" bundle="tender">
					<li><bean:write name="message" /></li>
				</html:messages>
			</div>
		</logic:messagesPresent>
		<fieldset>
			<legend>Selection Criteria</legend>
			<center>
				<table>
					<!-- Working Code -->
					<!-- Block ID -->
					<!-- Sub Divisional ID -->

					<!-- Working Code -->
					<!-- Block ID -->
					<!-- Sub Divisional ID -->
					<tr>
						<td><label>Location</label></td>
						<td><html:select property="locationId" styleId="locationId"
								styleClass="cs2"
								onchange="ajaxFunction('GetFilterValues.to?locationId='+this.value, 'blockId');">
								<html:option value="NOT SPECIFIED">Select Location</html:option>
								<html:options collection="userLocations" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
					</tr>

					<tr>
						<td><label>Select Block</label></td>
						<td><html:select property="blockId" styleId="blockId"
								styleClass="cs2"
								onchange="ajaxFunction('GetFilterValues.to?blockId='+this.value, 'villgId');">
								<html:option value="">Select Block</html:option>
							</html:select></td>
					</tr>


					<tr>
						<td><label>Select Village</label></td>
						<td><html:select property="villgId" styleId="villgId"
								styleClass="cs2"
								onchange="ajaxFunction('performaMasterAction.do?villgId='+this.value+'&method=fetchScheme', 'schemeId');">
							</html:select></td>
					</tr>
					<!-- Sub Division  KD WORK -->
					<tr>
						<td><label>Select Scheme</label></td>
						<td><html:select property="schemeId" styleId="schemeId"
								styleClass="cs2">
							</html:select></td>
					</tr>
					<!-- Sub Division  KD Work -->
				</table>


				<!--  Report Starts Updated-->
				<!--  Report Starts  -->
				<table>
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID"
									property="villagePerformaGrid" selectionAllowed="true"
									multipleSelectionAllowed="false" model="datagrid">

									<layout:datagridColumn property="villageId" title="Village Id"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="villageName"
										title="Village Name" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="hadBastNo" title="Had Bast No"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfHabitation"
										title="no of Habitation" mode="I,I,I"></layout:datagridColumn>
									<!-- Starts Here -->
									<layout:datagridColumn property="nameOfHabitation"
										title="Name of Habitation" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="typeOfHabitation"
										title="Type of Habitation" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="misCode" title="MIS Code"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="assemblyConstituency"
										title="Assembly Constituency" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="poputlationTotal"
										title="Population Total" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="populationSc"
										title="Population SC" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfHouseholdsTotal"
										title="No Of Households Total" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="noOfHouseholdsSc"
										title="No Of Households SC" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="panchayatGhr"
										title="No of Panchayat Ghar"></layout:datagridColumn>

									<layout:datagridColumn property="commCenterDhar"
										title=" No of Community center/Dharamshala"></layout:datagridColumn>

									<layout:datagridSelect property="isAngarwaries"
										title="Angarwaries">
										<layout:option value="No" />
										<layout:option value="Yes" />
									</layout:datagridSelect>

									<layout:datagridColumn
										title="In case of Yes, No. of Angarwaries"
										property="angarwaries"></layout:datagridColumn>


									<layout:datagridColumn property="privateBuildings"
										title="No of Private building for Angarwaries"></layout:datagridColumn>

									<layout:datagridColumn property="govtBuildings"
										title="No of Govt building for Angarwaries"></layout:datagridColumn>

									<layout:datagridSelect property="isGovtSchools"
										title="Govt Schools">
										<layout:option value="No" />
										<layout:option value="Yes" />
									</layout:datagridSelect>

									<layout:datagridColumn property="govtSchools"
										title="In case of Yes, No. of Govt. Schools"></layout:datagridColumn>
									<layout:datagridSelect property="isHealthCntr"
										title="Health Centres">
										<layout:option value="No" />
										<layout:option value="Yes" />
									</layout:datagridSelect>
									<layout:datagridColumn property="healthCntr"
										title="In case of Yes, No. of Health Centres"></layout:datagridColumn>
									<layout:datagridSelect property="isVillagePonds"
										title="Village Ponds">
										<layout:option value="No" />
										<layout:option value="Yes" />
									</layout:datagridSelect>
									<layout:datagridColumn property="villagePonds"
										title="In case of Yes, No. of Village Ponds"></layout:datagridColumn>
									<layout:datagridColumn property="areaSqrMtr"
										title="Area in Square meter"></layout:datagridColumn>
									<layout:datagridSelect property="sewerageScheme"
										title="Sewerage Scheme">
										<layout:option value="No" />
										<layout:option value="Yes" />
									</layout:datagridSelect>

									<layout:datagridColumn property="sewerageConSc"
										title="Number of Sewer Connection in SC households"></layout:datagridColumn>
									<layout:datagridColumn property="femaleScPop"
										title="Number of Female in SC population"></layout:datagridColumn>
									<layout:datagridColumn property="femaleGnPop"
										title="Number of Female in Gen population"></layout:datagridColumn>
									<layout:datagridColumn property="waterConSc"
										title="Number of Water Connection in SC households"></layout:datagridColumn>

									<layout:datagridColumn property="habitationWaterSupply"
										title="Distance of habitation/village from Water supply schemes in KM"></layout:datagridColumn>

								</layout:datagrid>
							</div>
						</td>
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>
			<center>
				<table>
					<tr>
						<td><label>Water Supply scheme under which
								habitations has been covered</label> <html:text property="schemeName"
								styleId="schemeName" /></td>

						<td><label>Source of WSS</label> <html:text
								property="schemeSource" styleId="schemeSource" /></td>
						<td><label>Type of Scheme</label> <html:text
								property="schemeType" styleId="schemeType" /></td>
					</tr>
					<tr>
						<td><label>Original Date of Commissioning of WSS</label> <html:text
								property="dateOfComm" styleId="dateOfComm" /></td>
						<td><label>Program under which WSS was commissioned</label> <html:text
								property="progId" styleId="progId" /></td>
						<td><label>In case of Upgradation and Improvement
								(Enter Date)</label> <html:text property="schemeUpgraded"
								styleId="datepicker19"
								onchange="validateDate(this.value,'datepicker19');
								if(this.value!=''){
									de_kyenable(true,'schemeExpenditure');
								}else{
								document.getElementById('schemeExpenditure').value='';
									disable_ctrl('schemeExpenditure',true);
								}
								
								" /></td>
					</tr>
					<tr>

						<td><label>Cost Expenditure Carried Out on scheme(Rs.
								In Lacs)</label> <html:text property="schemeExpenditure"
								styleId="schemeExpenditure"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>


						<td><label>Service Level in LPCD</label> <html:text
								property="serviceLevel" styleId="serviceLevel" /></td>

						<!-- new -->

					</tr>
					<tr>
						<td><label style="padding-top: 0px">Operating
								Arrangement</label> <html:text property="schemeOperatedBy"
								styleId="schemeOperatedBy"
								/>
							</td>
						<td><label style="padding-top: 0px">In case of DWSS
								operated, Details of arrangement of staff in 2017</label> <html:select
								property="staffScheme" styleId="staffScheme">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Regular">Regular</html:option>
								<html:option value="Outsourced">OutSourced</html:option>
								<html:option value="Contractor">Contractor</html:option>

							</html:select></td>
					</tr>
				</table>
			</center>
		</fieldset>


		<div id="tubewell_">
			<fieldset>
				<legend>Present Status of Water Supply in 2017 &nbsp;
					&nbsp; In case of Tubewell based schemes details of latest tubewell
					drilled at Site.</legend>
				<center>
					<table>
						<tr>
							<td><label>Depth of Tubewell</label> <html:text
									property="depthOfTubewell" styleId="depthOfTubewell"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Size of Tubewell</label> <html:text
									styleClass="ci5 " property="sizeOfTubewell"
									styleId="sizeOfTubewell" /></td>
							<td><label>Year of Drilling</label> <html:text
									property="yearOfDrilling" styleId="datepicker6"
									onchange="validateDate(this.value,'datepicker6')" /></td>
						</tr>
						<tr>
							<td><label>Original Discharge at the time of
									commissioning per hour(in cubic meter)</label> <html:text
									property="dischargeComm" styleId="dischargeComm"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Present Discharge per hour(in cubic
									meter)</label> <html:text property="presentDis" styleId="presentDis"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Present Spring Level in meters</label> <html:text
									property="presentSpringLevel" styleId="presentSpringLevel"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						</tr>
						<tr>
							<td><label>Time of Installation of New Machinery</label> <html:text
									styleId="datepicker" property="installation_of_new_machinery"
									onchange="validateDate(this.value,'datepicker')" /></td>
							<td><label>Capacity of Machinery in BPH</label> <html:text
									property="capMachinery" styleId="capacity_of_machinery"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						</tr>
					</table>
				</center>
			</fieldset>
		</div>

		<div id="canal_">
			<fieldset>
				<legend>Present Status of Water Supply in 2017 &nbsp;
					&nbsp; In case of Canal based wss details of Structures</legend>
				<center>
					<table>
						<tr>
							<td><label>Type of Inlet channel</label> <html:select
									property="inletType" styleId="inletType">
									<html:option value="">--Please Select--</html:option>
									<html:option value="Open">Open</html:option>
									<html:option value="Drain">Drain Piped</html:option>
								</html:select></td>
							<td><label>Length of Inlet channel (KM)</label> <html:text
									styleClass="ci5 " property="inletLenght"
									styleId="inlet_channel_Lenght"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>In case of Piped type of Pipe</label> <html:select
									property="pipeType" styleId="pipe_type">
									<html:option value="">--Please Select--</html:option>
									<html:option value="RCC">RCC</html:option>
									<html:option value="PVC">PVC</html:option>
								</html:select></td>
						</tr>
						<tr>
							<td><label>Capacity of SS Tank in cubic meter</label> <html:text
									property="capacitySSTank" styleId="capacitySSTank"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Capacity of HL Tank in cubic meter</label> <html:text
									property="capacityHLTank" styleId="capacityHLTank"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Capacity of SW Tank in cubic meter</label> <html:text
									property="capacityCWTank" styleId="capacityCWTank"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						</tr>
						<tr>
							<td><label>Filteration Units (Type)</label> <html:select
									property="filtertionType" styleId="filtertion_type">
									<html:option value="">--Please Select--</html:option>
									<html:option value="slow">Slow Sand</html:option>
									<html:option value="rapid">Rapid Sand</html:option>
									<html:option value="pressure">Pressure Filter</html:option>
									<html:option value="compact">Compact Unit</html:option>
					</</html:select></td>
							<td><label>Filteration Units (Nos)</label> <html:text
									property="filtertionNo" styleId="filtertionNo"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<%-- <td><label>Filteration Units (Capacity in Cubic
								meter)</label> <html:text  property="filtertionCapacity" /></td> --%>
						</tr>
						<tr>
							<td><label>Capacity of Raw Water Motor (BHP)</label> <html:text
									property="capacityRawWater" styleId="capacityRawWater"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
							<td><label>Capacity of Clear Water Motor (BHP)</label> <html:text
									property="capacityClearWater" styleId="capacityClearWater"
									onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						</tr>
					</table>
				</center>
			</fieldset>
		</div>

		<fieldset>
			<legend>Present Status of Water Supply in 2017 &nbsp; &nbsp;
				Details of OHSR's </legend>
			<center>
				<table>
					<tr>
						<td><label>Nos</label> <html:text property="noOfOhsr"
								styleId="noOfOhsr"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(document.getElementById('noOfOhsr').value==='0'){
			 					  	null_ctrl('datepicker2$oHSRCapcity$oHSRFullSuplyLvl$oHSRWorkingCondition$oHSRCondition$oHSRDismantling$oHSRCTDia$oHSRCTDepth$oHSRUGSRDia$oHSRUGSRDepth');
			 					  	disable_ctrl('datepicker2~oHSRCapcity~oHSRFullSuplyLvl~oHSRWorkingCondition~oHSRCondition~oHSRDismantling~oHSRCTDia~oHSRCTDepth~oHSRUGSRDia~oHSRUGSRDepth',true); 
									}if(document.getElementById('noOfOhsr').value!='0'){
		  							de_kyenable(true,'datepicker2@oHSRCapcity@oHSRFullSuplyLvl@oHSRWorkingCondition@oHSRCT@oHSRUGSR@oHSRCTDia@oHSRCTDepth@oHSRUGSRDia@oHSRUGSRDepth'); 
								}" /></td>
						<td><label>Time of Construction</label> <html:text
								styleId="datepicker2" property="oHSR_construction_date"
								onchange="validateDate(this.value,'datepicker2');" /></td>
						<td><label>Capacity in Litres(in lacs)</label> <html:text
								property="oHSRCapcity" styleId="oHSRCapcity"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCTDia~oHSRCTDepth~oHSRUGSRDia~oHSRUGSRDepth',true);
									null_ctrl('oHSRCTDia$oHSRCTDepth$oHSRUGSRDia$oHSRUGSRDepth');
								}if(this.value===''||this.value==='0'||this.value==='undefined'||this.value==='null'){
									de_kyenable(true,'oHSRCTDia@oHSRCTDepth@oHSRUGSRDia@oHSRUGSRDepth');
								}" /></td>


					</tr>
					<tr>
						<td><label>C.T Diameter(Meter)</label> <html:text property="oHSRCTDia"
								styleId="oHSRCTDia"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCapcity',true);
									null_ctrl('oHSRCapcity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDepth').value===''&& document.getElementById('oHSRUGSRDia').value===''&& document.getElementById('oHSRUGSRDepth').value===''||(this.value=='0' && document.getElementById('oHSRCTDepth').value==='0'&&document.getElementById('oHSRUGSRDia').value==='0'&&document.getElementById('oHSRUGSRDepth').value==='0')){
									de_kyenable(true,'oHSRCapcity');
								}" /></td>
								<td><label>C.T Depth(Meter)</label> <html:text property="oHSRCTDepth"
								styleId="oHSRCTDepth"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCapcity',true);
									null_ctrl('oHSRCapcity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDia').value===''&& document.getElementById('oHSRUGSRDia').value===''&& document.getElementById('oHSRUGSRDepth').value===''||(this.value=='0' && document.getElementById('oHSRCTDia').value==='0'&&document.getElementById('oHSRUGSRDia').value==='0'&&document.getElementById('oHSRUGSRDepth').value==='0')){
								{
									de_kyenable(true,'oHSRCapcity');
								}" /></td>
					
						<td><label>UGSR Diameter(Meter)</label> <html:text property="oHSRUGSRDia"
								styleId="oHSRUGSRDia"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCapcity',true);
									null_ctrl('oHSRCapcity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDia').value===''&& document.getElementById('oHSRCTDepth').value===''&& document.getElementById('oHSRUGSRDepth').value===''||(this.value=='0' && document.getElementById('oHSRCTDia').value==='0'&&document.getElementById('oHSRCTDepth').value==='0'&&document.getElementById('oHSRUGSRDepth').value==='0')){
									de_kyenable(true,'oHSRCapcity');
								}" /></td>

					</tr>
	<tr>	
								
						<td><label>UGSR Depth(Meter)</label> <html:text property="oHSRUGSRDepth"
								styleId="oHSRUGSRDepth"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCapcity',true);
									null_ctrl('oHSRCapcity');
								}if(this.value===''&& document.getElementById('oHSRCTDia').value===''&& document.getElementById('oHSRCTDepth').value===''&& document.getElementById('oHSRUGSRDia').value===''||(this.value=='0' && document.getElementById('oHSRCTDia').value==='0'&&document.getElementById('oHSRCTDepth').value==='0'&&document.getElementById('oHSRUGSRDia').value==='0')){
									de_kyenable(true,'oHSRCapcity');
								}" /></td>
						<td><label>Full supply level(in mtrs)</label> <html:text
								property="oHSRFullSuplyLvl" styleId="oHSRFullSuplyLvl"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
								<td><label>Is OHSR in Working Condition</label> <html:select
								property="oHSRWorkingCondition" styleId="oHSRWorkingCondition"
								onchange="checkOhsr(this.value);">
								<html:option value="">Please select</html:option>
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select></td>


	</tr>
					<tr>
						
						<td><label>If no, Does it needs Repair</label> <html:select
								property="oHSRCondition" styleId="oHSRCondition"
								onchange="checkOhsr(this.value);">
								<html:option value="">Please select</html:option>
								<html:option value="Repair">Repair</html:option>
								<html:option value="Dismantling">Dismantling</html:option>
							</html:select></td>
						<!--  New Field -->
						<td><label>In case of dismantling whether technical
								report from any institution has been received </label> <html:select
								property="oHSRDismantling" styleId="oHSRDismantling">
								<html:option value="">Please select</html:option>
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>

						<!-- New Field -->
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>
			<legend>Existing Water Quality Status </legend>
			<center>
				<table>
					<tr>
						<td><label>Is ground water potable</label> <html:select
								property="ground_water_potable_status"
								styleId="Ground_water_potable_status"
								onchange="enable_ExistingWaterQuality(this.value)">
								<html:option value=" ">Please select</html:option>
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>

							</html:select></td>
						<td><label>if No, type of contamination</label> <html:select
								styleClass="ci5 " property="ground_water_potable_No"
								styleId="Ground_water_potable_No">

								<html:option value="">--Please Select--</html:option>
								<html:option value="Arsenic">Arsenic</html:option>
								<html:option value="Flouride">Flouride </html:option>
								<html:option value="Uranium">Uranium</html:option>
								<html:option value="Aluminum">Aluminum</html:option>
								<html:option value="Cadium">Cadium</html:option>
								<html:option value="Lead">Lead</html:option>
								<html:option value="Iron">Iron</html:option>
								<html:option value="Salinity">Salinity or TDS</html:option>
								<html:option value="Others">Others</html:option>
							</html:select></td>
						<td><label>Preventive measures adopted</label> <html:select
								property="preventive_measures_adopted"
								styleId="Preventive_measures_adopted">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Canal">Canal Water</html:option>
								<html:option value="Supply">Supply </html:option>
								<html:option value="RO">RO</html:option>
								<html:option value="Other">Any Other</html:option>



							</html:select></td>
					</tr>


					<tr>
						<td><label>Capacity of Plant in LPH</label> <html:text
								property="capacity_of_plant" styleId="Capacity_of_plant"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Date of Installation</label> <html:text
								property="date_of_installation" styleId="datepicker4"
								onchange="validateDate(this.value,'datepicker4')" /></td>
						<td><label>Being Operated by</label> <html:select
								property="being_operated_by" styleId="Being_operated_by">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Contractor">Contractor</html:option>
								<html:option value="Department">Department </html:option>
								<html:option value="GPWSC">GPWSC</html:option>
								<html:option value="District">District Administration</html:option>




							</html:select></td>
					</tr>
					<tr>
						<td><label>Volume of water being treated on regular
								basis</label> <html:text property="volume_of_Water_Daily_basis"
								styleId="Volume_of_Water_Daily_basis"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Arrangement for disposal of reject water</label> <html:select
								styleClass="ci5 " property="disposal_of_reject_water"
								styleId="disposal_of_reject_water">

								<html:option value="">--Please Select--</html:option>
								<html:option value="open_drain">Open Drain</html:option>
								<html:option value="others">Any Other Technology </html:option>


							</html:select></td>
						<td><label>Penetration in percentage</label> <html:text
								property="penetration_in_percentage"
								styleId="Penetration_in_percentage"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
					<tr>
						<td><label>Present rate of user charges</label> <html:text
								property="present_rate_of_user_charges"
								styleId="present_rate_of_user_charges"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

					<tr>
						<td><label>In case of separate electric connection
								(Sanctioned Load in KWH)</label> <html:text
								property="seperate_Sanctioned_Load"
								styleId="Seperate_Sanctioned_Load"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>In case of separate electric connection
								(Pending Electric Bill(Rs. In Lacs))</label> <html:text
								property="seperate_Pending_eletric_bill31032017"
								styleId="Seperate_Pending_eletric_bill31032017"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>In case of separate electric connection
								(Average Monthly bill((Rs. In Lacs)) )</label> <html:text
								property="average_monthly_bill_of_Treatment_plant"
								styleId="Average_monthly_bill_of_Treatment_plant"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Details of disinfection unit if installed </legend>
			<center>
				<table>
					<tr>
						<td><label>Select Details of disinfection unit if
								installed </label> <html:select property="disinfecReq"
								styleId="disinfecReq" onchange="validatedisinfecDetails()">

								<html:option value="">Please Select</html:option>
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>

							</html:select></td>
					</tr>
					<tr>
						<td><label>Type</label> <html:select
								property="disinfectionType" styleId="disinfectionType"
								style="width:130px;">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Electronic">Electronic/Cholrinator</html:option>
								<html:option value="Dosatron">Dosatron</html:option>
								<html:option value="Silveriornzation">Silveriornzation Plants</html:option>

							</html:select></td>
						<td><label>Time of Installation</label> <html:text
								styleId="datepicker3" property="disinfection_Instalation_time"
								onchange="validateDate(this.value,'datepicker3')" /></td>
						<td><label>Present Status in 2017</label> <html:select
								property="disinfectionPrStatus" styleId="disinfectionPrStatus"
								style="margin-left:21px;">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Functional">Functional</html:option>
								<html:option value="Non_Functional">Non-Functional</html:option>

							</html:select></td>
					</tr>



				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Electric connection details </legend>
			<center>
				<table>

					<tr>
						<td><label>Sanctioned Load in KWH</label> <html:text
								property="sanctionedLoad" styleId="sanctionedLoad"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Pending Electic Bill as on 31-03-2017((Rs.
								In Lacs))</label> <html:text styleClass="ci5 "
								property="pendingBill3103" styleId="pendingBill3103"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Pending Electic Bill as on 30-06-2017((Rs.
								In Lacs))</label> <html:text property="pendingBill3006"
								styleId="pendingBill3006"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
					<tr>
						<td><label> Average Monthly bill of WSS((Rs. In
								Lacs))</label> <html:text property="avgMonthBillofWSS"
								styleId="avgMonthBillofWSS"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Scheme has more than one source</legend>
			<center>
				<table>
					<tr>

						<td><label>Scheme has more than one source</label> <html:select
								property="multipleSource" styleId='multipleSource'
								onchange=" if(this.value==='Yes'){
									 	
									    displayGrid();
									    disable_ctrl('villId~schemeSrc',true);
									    
									    alert('Please click on plus(+) button to save first source');
		 							 }">
								<html:option value="">Please Select</html:option>
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>
						<td><label>&nbsp;&nbsp;&nbsp;Select Village</label> <html:select
								property="villId" styleId="villId" style="width: 150px;">
								<html:option value="">Please Select</html:option>
								<html:options collection="villages" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
						<td><label>Select type of second source</label> <html:select
								property="schemeSrc" styleId="schemeSrc" style="width: 150px"
								onchange="disableField(); if(this.value!=''){
											alert('Please enter appropriate detail in above required section and then press plus(+) sign to save the detail of additional source');
										}
										">
								<html:option value="">Select Scheme Source</html:option>
								<html:option value="TUBEWELL">TUBEWELL</html:option>
								<html:option value="CANAL">CANAL</html:option>
								<html:option value="HANDPUMP">HANDPUMP</html:option>
							</html:select></td>
					</tr>
				</table>
				<table>
					<tr id="sourcegrid">
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID"
									property="villageSourceGrid" selectionAllowed="true"
									multipleSelectionAllowed="false" model="datagrid">
									<layout:datagridColumn property="vilIds" title="Village Id"></layout:datagridColumn>
									<layout:datagridColumn property="schmSource"
										title="Scheme Source"></layout:datagridColumn>
									<layout:datagridColumn property="schmType" title="Scheme Type"></layout:datagridColumn>
									<layout:datagridColumn property="dateComm"
										title="Date of commissioning"></layout:datagridColumn>
									<layout:datagridColumn property="programId" title="Program id"></layout:datagridColumn>
									<layout:datagridColumn property="srvcLevel"
										title="Service Level"></layout:datagridColumn>
									<layout:datagridColumn property="schemeUpgrd"
										title="scheme Upgraded"></layout:datagridColumn>
									<layout:datagridColumn property="schemeExpen"
										title="Scheme Expenditure"></layout:datagridColumn>
									<layout:datagridColumn property="depthTubewell"
										title="Depth of tubewell"></layout:datagridColumn>
									<layout:datagridColumn property="sizeTubewell"
										title="Size of tubewell"></layout:datagridColumn>
									<layout:datagridColumn property="yearDrilling"
										title="Year of Drilling"></layout:datagridColumn>
									<layout:datagridColumn property="dischargeCommun"
										title="Discharge at time of commissioning"></layout:datagridColumn>
									<layout:datagridColumn property="presentDischrg"
										title="Present discharge"></layout:datagridColumn>
									<layout:datagridColumn property="presentSpringLvl"
										title="Present Sprng level(Meter)"></layout:datagridColumn>
									<layout:datagridColumn property="installationNewMachinery"
										title="Installation of new mechinary"></layout:datagridColumn>
									<layout:datagridColumn property="capacityMachinery"
										title="Capacity of machinery in BHP"></layout:datagridColumn>
									<layout:datagridColumn property="inltType"
										title="Type of Inlet channel"></layout:datagridColumn>
									<layout:datagridColumn property="inletLnght"
										title="Length of Inlet Channel in Kms"></layout:datagridColumn>
									<layout:datagridColumn property="pipType" title="type of pipe"></layout:datagridColumn>
									<layout:datagridColumn property="capacitySTank"
										title="Capacity of SS Tank in cubic meter"></layout:datagridColumn>
									<layout:datagridColumn property="capacityHTank"
										title="Capacity of HL Tank in cubic meter"></layout:datagridColumn>
									<layout:datagridColumn property="capacityCTank"
										title="Capacity of CW Tank in cubic meter"></layout:datagridColumn>
									<layout:datagridColumn property="filterType"
										title="Filtertion Units"></layout:datagridColumn>
									<layout:datagridColumn property="filterNo"
										title="Filtertion  Nos"></layout:datagridColumn>
									<layout:datagridColumn property="capacityRawWatr"
										title="Capacity of Raw water Motor"></layout:datagridColumn>
									<layout:datagridColumn property="capacityClrWater"
										title="Capacity of clear water Motor"></layout:datagridColumn>
									<layout:datagridColumn property="noOhsrOhsr" title="Ohsr Nos" />

									<layout:datagridColumn property="oHSRConstructionDate"
										title="OHSR Time of construction"></layout:datagridColumn>
									<layout:datagridColumn property="oHSRCap" title="OHSR Capacity"></layout:datagridColumn>
									<layout:datagridColumn property="oHSRCtDia1" title="OHSR C.T Dia"></layout:datagridColumn>
									<layout:datagridColumn property="oHSRCtDepth1" title="OHSR C.T Depth"></layout:datagridColumn>
									
									<layout:datagridColumn property="oHSRUgsrDia1" title="OHSR UGSR Dia"></layout:datagridColumn>

									<layout:datagridColumn property="oHSRUgsrDepth1" title="OHSR UGSR Depth"></layout:datagridColumn>

									<layout:datagridColumn property="oHSRFullSuplyLvl1"
										title="OHSR Full Supply Service Level"></layout:datagridColumn>

									<layout:datagridColumn property="oHSRWorkingCond"
										title="OHSR in working condition"></layout:datagridColumn>
									<layout:datagridColumn property="oHSRCond"
										title="OHSR Condition"></layout:datagridColumn>

									<layout:datagridColumn property="oHSRDismantling1"
										title="OHSR Dismantling"></layout:datagridColumn>



									<layout:datagridColumn property="ground_water_potablestatus"
										title="Is ground water potable"></layout:datagridColumn>
									<layout:datagridColumn property="ground_water_potableNo"
										title="if No, time of contimation"></layout:datagridColumn>

									<layout:datagridColumn property="preventive_measuresadopted"
										title="Preventive measures adopted"></layout:datagridColumn>

									<layout:datagridColumn property="capacity_ofplant"
										title="Capacity of Plant in LPH"></layout:datagridColumn>

									<layout:datagridColumn property="dateInstallation"
										title="Date of Installation"></layout:datagridColumn>

									<layout:datagridColumn property="being_operatedby"
										title="Being Operated by"></layout:datagridColumn>

									<layout:datagridColumn property="volume_of_Water_Dailybasis"
										title="Volume of water being treated on regular basis"></layout:datagridColumn>

									<layout:datagridColumn property="disposal_of_rejectwater"
										title="Arrangement for disposal of reject water"></layout:datagridColumn>

									<layout:datagridColumn property="penetration_inpercentage"
										title="Penetration in percentage"></layout:datagridColumn>

									<layout:datagridColumn property="present_rate_of_usercharges"
										title="Present rate of user charges"></layout:datagridColumn>

									<layout:datagridColumn property="seperate_SanctionedLoad"
										title="In case of seperate electic connection (Sanctioned Load)"></layout:datagridColumn>

									<layout:datagridColumn
										property="seperate_Pending_eletricbill31032017"
										title="In case of seperate electic connection (Pending Electic Bill)"></layout:datagridColumn>

									<layout:datagridColumn
										property="average_monthly_bill_of_Treatmentplant"
										title="In case of seperate electic connection (Average Monthly bill )"></layout:datagridColumn>

									<layout:datagridColumn property="disinfType"
										title="Disinfection Type"></layout:datagridColumn>
									<layout:datagridColumn property="disInstallationTime"
										title="Disinfection Installation Time"></layout:datagridColumn>
									<layout:datagridColumn property="disinPrStatus"
										title="Disinfection Present status"></layout:datagridColumn>
									<layout:datagridColumn property="schemeOperatBy"
										title="Scheme operated by"></layout:datagridColumn>
									<layout:datagridColumn property="stafScheme"
										title="In case of DWSS operated, details of arrangement of staff "></layout:datagridColumn>
									<layout:datagridColumn property="sanctionLoad"
										title="Sanctioned Load "></layout:datagridColumn>
									<layout:datagridColumn property="pendingBill3103"
										title="Pending eletric bill as on 31.03.2017"></layout:datagridColumn>
									<layout:datagridColumn property="pendingBill3006"
										title="Pending eletric bill as on 30.06.2017"></layout:datagridColumn>
									<layout:datagridColumn property="avgMonthBillWSS"
										title="Average monthly bill of WSS"></layout:datagridColumn>
								</layout:datagrid>
							</div>
						</td>
						<td><img src="images/add.png"
							onclick=" validateSourceGrid();" width="20px" /><br> <img
							src="images/delete.png"
							onclick="StrutsLayout.setDatagridLineState('villageSourceGrid', 'removed')"
							width="20px" /></td>
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>
			<legend>Details of Distribution Pipe Line (in KM) </legend>
			<center>
				<table>
					<tr>
						<td><label>AC</label> <html:text property="pipelineAC"
								styleId="Pipeline_AC"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>MS/DI/CI</label> <html:text styleClass="ci5 "
								property="pipelineMSDICi" styleId="Pipeline_MS_DI_Ci"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>GI</label> <html:text property="pipelineGI"
								styleId="Pipeline_GI"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
					<tr>
						<td><label>PVC</label> <html:text property="pipelinePVC"
								styleId="Pipeline_PVC"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label> Percentage of Functional Distribution
								network</label> <html:text property="functionalDistributionPercentage"
								styleId="Functional_Distribution_percentage"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
				</table>
			</center>
		</fieldset>


		<fieldset>

			<legend>Water Connection Details </legend>
			<center>
				<table>
					<tr>
						<td><label>No of Household water connection</label> <html:text
								property="householdWaterConnection"
								styleId="Household_water_connection"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Whether connections are metered</label> <html:select
								styleClass="ci5 " property="watered_connection"
								styleId="Watered_connection"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value==='1'){
									de_kyenable(true,'Metered_connection');
								}else if(this.value==='0'||this.value===''){
								document.getElementById('Metered_connection').value='';
								document.getElementById('Metered_supply_recovery').value='';
								document.getElementById('Flat_rate_charges_per_month').value='';
								disable_ctrl('Metered_connection~Metered_supply_recovery~Flat_rate_charges_per_month',true);
								}
							">
								<html:option value="">Please select</html:option>
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select></td>
						<td><label>In case of metered connections then nos</label> <html:text
								property="metered_connection" styleId="Metered_connection"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''){
									de_kyenable(true,'Metered_supply_recovery');
								}else{
										document.getElementById('Metered_supply_recovery').value='';
										document.getElementById('Flat_rate_charges_per_month').value='';
										disable_ctrl('Metered_supply_recovery~Flat_rate_charges_per_month',true);
								}" /></td>
					</tr>


					<tr>
						<td><label>In case of metered supply whether recovery
								on</label> <html:select property="metered_supply_recovery"
								styleId="Metered_supply_recovery"
								onchange="if(this.value==='Flat'){
									de_kyenable(true,'Flat_rate_charges_per_month');
								}else{
									document.getElementById('Flat_rate_charges_per_month').value='';
									disable_ctrl('Flat_rate_charges_per_month',true);
								}">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Flat">Flat Rate</html:option>
								<html:option value="Volumetric">Volumetric Rate</html:option>


							</html:select></td>
						<td><label>If flat rate then charges per month(in
								Rs.)</label> <html:text property="flat_rate_charges_per_month"
								styleId="Flat_rate_charges_per_month"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Arrear of recovery with consumers as on
								01-04-2017(Rs. In Lacs)</label> <html:text
								property="arrear_of_recovery_with_consumers_as01042017"
								styleId="Arrear_of_recovery_with_consumers_as01042017"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Functionality Status of Water supply schemes </legend>
			<center>
				<table>
					<tr>
						<td><label>Functional</label> <html:select
								property="scheme_Functional" styleId="Scheme_Functional"
								onchange="hideUnhideWaterSupplyStatus();">
								<html:option value="">Please select</html:option>
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>

							</html:select></td>

						<td><label>Time since non-functional</label> <html:text
								property="scheme_NonFunctional_date" styleId="datepicker11"
								onchange="validateDate(this.value,'datepicker11')" /></td>
						<td><label>If Non Functional (Reason)</label> <html:select
								property="scheme_NonFunctional" styleId="scheme_NonFunctional">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Source">Source Faliure</html:option>
								<html:option value="Machinery">Machinery Faliure</html:option>
								<html:option value="Electric">Electric Supply Disconnection</html:option>
								<html:option value="Distibution">Distibution Faliure</html:option>
								<html:option value="Others">Others</html:option>

							</html:select></td>
					</tr>
				</table>
			</center>
		</fieldset>
		<fieldset>
			<center>
				<legend style="background-color: #00008B"> Works proposed
					to be executed in next five years </legend>
			</center>

			<center>


				<table>
					<tr></tr>
					<tr>
						<td><label>Select Village</label> <html:select
								property="villagesId" styleId="villaggesId" style="width:200px;"
								onchange="enableFiveYearGridVillage(this.value);">
								<html:option value="">Please Select</html:option>
								<html:options collection="villages" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
					</tr>
				</table>

				<table>

					<tr>
						<td><label>Independent new WSS</label> <html:select
								property="independent_New_WSS" styleId="Independent_New_WSS"
								style="width:90px;"
								onchange="validateUpgrade(this.value); if((this.value==='1')&&  document.getElementById('Instltion_Wtr_Treatment').value==='Yes'){
									de_kyenable(true,'canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22');
								}
								<%-- if((this.value==='0')&&  document.getElementById('Instltion_Wtr_Treatment').value==='Yes'){
								de_kyenable(true,'insWaterPlant1@otherWork');
								disable_ctrl('canal_based~oHSR_Capacity1~otherStructure~distrNetwork1~mtrConnection~disincUnit~Driling_of_new_tubewell_machinery22',true);
								} --%>
								
								">
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select></td>

						<%-- <td><label>Independent new WSS Cost(Rs in lacs)</label>
						<html:text property="independent_New_WSS_Cost"
								styleId="Independent_New_WSS_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td> --%>

						<td><label>Upgradation of Existing WSS</label> <html:select
								property="upgradation_of_existing_WSS"
								styleId="Upgradation_of_existing_WSS" style="width:90px;"
								onchange="validatett(this.value); 
								if((this.value==='1')&& document.getElementById('Instltion_Wtr_Treatment').value==='Yes'){
									de_kyenable(true,'canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22');
								}
								<%-- if((this.value==='0')&&  document.getElementById('Instltion_Wtr_Treatment').value==='Yes'){
								de_kyenable(true,'insWaterPlant1@otherWork');
								disable_ctrl('canal_based~oHSR_Capacity1~otherStructure~distrNetwork1~mtrConnection~disincUnit~Driling_of_new_tubewell_machinery22',true);
								} --%>
								">
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select></td>
						<%-- <td><label>Upgradation of Existing WSS Cost(Rs in lacs)</label>
						<html:text styleClass="ci5 "
								property="upgradation_of_existing_WSS_Cost"
								styleId="Upgradation_of_existing_WSS_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td> --%>

						<td><label>Installation of Water treatment plant </label> <html:select
								property="instltion_Wtr_Treatment"
								styleId="Instltion_Wtr_Treatment" style="width:90px;"
								onchange=" if((document.getElementById('Independent_New_WSS').value==='1'||document.getElementById('Upgradation_of_existing_WSS').value==='1')&& this.value==='Yes'){
									
									de_kyenable(true,'canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22');
								}
								else if((document.getElementById('Independent_New_WSS').value==='0'||document.getElementById('Upgradation_of_existing_WSS').value==='0')&& this.value==='Yes'){
								
								de_kyenable(true,'canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22');

								}
								">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>

						<%-- <td><label>Installation of Water treatment plant
								cost (Rs in lacs)</label>
						<html:text property="instltion_Wtr_Treatment_plant"
								styleId="Instltion_Wtr_Treatment_plant"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td> --%>
					</tr>

					<tr>

						<td><label>Source of WSS</label> <html:select
								property="source_of_WSS" styleId="source_of_WSS"
								onchange="if((document.getElementById('Independent_New_WSS').value==='1'||document.getElementById('Upgradation_of_existing_WSS').value==='1') && (document.getElementById('Instltion_Wtr_Treatment').value==='No'))
								{
								if(this.value==='Tubewell'){
 									de_kyenable(true,'oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork@Driling_of_new_tubewell_machinery22');
									null_ctrl('canal_based_Inlet_channel_Size_of_pipe$canal_based_Inlet_channel_length$canal_based_s_and_s_Capacity$canal_based_Filteration_Plan_type$canal_based_Filteration_Plan_capacity$canal_based_Cost');
									disable_ctrl('canal_based~canal_based_Inlet_channel_Size_of_pipe~canal_based_Inlet_channel_length~canal_based_s_and_s_Capacity~canal_based_Filteration_Plan_type~canal_based_Filteration_Plan_capacity~canal_based_Cost~clear_water_tank',true);
 									de_kyenable(true,'Driling_of_new_tubewell_machinery22~Driling_of_new_tubewell_machinery_size@Driling_of_new_tubewell_machinery_depth@Driling_of_new_tubewell_machinery_capacity@Driling_of_new_tubewell_machinery_cost');
	
								}
								else if(this.value==='canal'){
												de_kyenable(true,'canal_based@oHSR_Capacity1@otherStructure@distrNetwork1@mtrConnection@disincUnit@insWaterPlant1@otherWork');
								 				de_kyenable(true,'canal_based~canal_based_Inlet_channel_Size_of_pipe@canal_based_Inlet_channel_length@canal_based_s_and_s_Capacity@canal_based_Filteration_Plan_type@canal_based_Filteration_Plan_capacity@canal_based_Cost@clear_water_tank');
								 				null_ctrl('Driling_of_new_tubewell_machinery_size$Driling_of_new_tubewell_machinery_depth$Driling_of_new_tubewell_machinery_capacity$Driling_of_new_tubewell_machinery_cost');
												disable_ctrl('Driling_of_new_tubewell_machinery22~Driling_of_new_tubewell_machinery_size~Driling_of_new_tubewell_machinery_depth~Driling_of_new_tubewell_machinery_capacity~Driling_of_new_tubewell_machinery_cost',true);
									
							    	}
								}
								">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Tubewell">Tubewell</html:option>
								<html:option value="canal">canal </html:option>
							</html:select></td>

						<td><label>Whether being shifed to canal from
								tubewell/HP due to water quality</label> <html:select
								property="shifted_to_canal_from_Other"
								styleId="shifted_to_canal_from_Other" style="width:90px;">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>

							</html:select></td>

					</tr>

				</table>
			</center>
		</fieldset>

		<fieldset>
			<legend>Drilling of New Tubewell and Machinery </legend>
			<center>
				<table>
					<tr>

						<td colspan="5"><label>Drilling of New Tubewell and
								Machinery</label> <html:select
								property="driling_of_new_tubewell_machinery"
								styleId="Driling_of_new_tubewell_machinery22"
								onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>


					</tr>


					<tr>
						<td><label>Size</label> <html:text
								property="driling_of_new_tubewell_machinery_size"
								styleId="Driling_of_new_tubewell_machinery_size"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Depth in meters</label> <html:text
								styleClass="ci5 "
								property="driling_of_new_tubewell_machinery_depth"
								styleId="Driling_of_new_tubewell_machinery_depth"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Capacity of Machinery in BPH</label> <html:text
								property="driling_of_new_tubewell_machinery_capacity"
								styleId="Driling_of_new_tubewell_machinery_capacity"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>


					</tr>

					<tr>
						<td><label>Cost(Rs in lacs)</label> <html:text
								property="driling_of_new_tubewell_machinery_cost"
								styleId="Driling_of_new_tubewell_machinery_cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>



		<fieldset>
			<legend>In case of canal based ,types of structures along
				with cost </legend>
			<center>
				<table>
					<tr>

						<td colspan="5"><label>In case of canal based ,types
								of structures</label> <html:select property="canal_based"
								styleId="canal_based" onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>
					</tr>

					<tr>
						<td><label>Inlet channel (Size of pipes)</label> <html:text
								property="canal_based_Inlet_channel_Size_of_pipe"
								styleId="canal_based_Inlet_channel_Size_of_pipe"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Inlet channel (lengh in meters)</label> <html:text
								styleClass="ci5 " property="canal_based_Inlet_channel_length"
								styleId="canal_based_Inlet_channel_length"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>

						<td><label>S&S (Capacity in cubic meters)</label> <html:text
								property="canal_based_s_and_s_Capacity"
								styleId="canal_based_s_and_s_Capacity"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>


					<tr>


						<td><label>Filteration Plan (Type)</label> <html:select
								property="canal_based_Filteration_Plan_type"
								styleId="canal_based_Filteration_Plan_type">

								<html:option value="">--Please Select--</html:option>
								<html:option value="Slow">Slow Sand</html:option>
								<html:option value="Rapid">Rapid Sand </html:option>
								<html:option value="Pressure">Pressure Filer</html:option>
								<html:option value="Compact">Compact Unit</html:option>


							</html:select></td>
						<td><label>Filteration Plan (capacity in cubic meter)</label>
							<html:text property="canal_based_Filteration_Plan_capacity"
								styleId="canal_based_Filteration_Plan_capacity"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>

						<td><label>Cost(Rs. In Lacs)</label> <html:text
								property="canal_based_Cost" styleId="canal_based_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

					<tr>
						<td><label>Clear Water Tank(Capacity in cubic meter)</label>
							<html:text property="clear_water_tank" styleId="clear_water_tank"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>OSHR</legend>
			<center>
				<table>
					<tr>
						<td colspan="5"><label>Select OSHR</label> <html:select
								property="ohsr" styleId="oHSR_Capacity1"
								onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>

								<html:option value="Yes">Yes</html:option>
							</html:select></td>
					</tr>
					<tr>
						<td><label>Capacity(Lac Litre)</label> <html:text
								property="oHSR_Capacity" styleId="oHSR_Capacity"
								onkeypress="return validateKey1(event,	this,'9@20@2')" 
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSRCTDiameter~oHSR_CT_Depth~oHSR_UGS_RDia~oHSR_UGSR_Depth',true);
									null_ctrl('oHSRCTDiameter$oHSR_CT_Depth$oHSR_UGS_RDia$oHSR_UGSR_Depth');
								}if(this.value===''||this.value==='0'||this.value==='undefined'||this.value==='null'){
									de_kyenable(true,'oHSRCTDiameter@oHSR_CT_Depth@oHSR_UGS_RDia@oHSR_UGSR_Depth');
								}"
								
								
								
								/></td>
								
					<td><label>C.T Diameter(Meter)</label> <html:text property="oHSRCTDiameter"
								styleId="oHSRCTDiameter"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSR_Capacity',true);
									null_ctrl('oHSR_Capacity');
								}
								if(this.value===''&& document.getElementById('oHSR_CT_Depth').value===''&& document.getElementById('oHSR_UGS_RDia').value===''&& document.getElementById('oHSR_UGSR_Depth').value===''||(this.value=='0' && document.getElementById('oHSR_CT_Depth').value==='0'&&document.getElementById('oHSR_UG_SRDia').value==='0'&&document.getElementById('oHSRUGSRDepth').value==='0')){
									de_kyenable(true,'oHSR_Capacity');
								}" /></td>
					<td><label>C.T Depth(Meter)</label> <html:text property="oHSR_CT_Depth"
								styleId="oHSR_CT_Depth"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSR_Capacity',true);
									null_ctrl('oHSR_Capacity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDiameter').value===''&& document.getElementById('oHSR_UGS_RDia').value===''&& document.getElementById('oHSR_UGSR_Depth').value===''||(this.value=='0' && document.getElementById('oHSRCTDiameter').value==='0'&&document.getElementById('oHSR_UGS_RDia').value==='0'&&document.getElementById('oHSR_UGSR_Depth').value==='0')){
								{
									de_kyenable(true,'oHSR_Capacity');
								}" /></td>
								</tr>
						<tr>	
						<td><label>UGSR Diameter(Meter)</label> <html:text property="oHSR_UGS_RDia"
								styleId="oHSR_UGS_RDia"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSR_Capacity',true);
									null_ctrl('oHSR_Capacity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDiameter').value===''&& document.getElementById('oHSR_UGS_RDia').value===''&& document.getElementById('oHSR_UGSR_Depth').value===''||(this.value=='0' && document.getElementById('oHSRCTDiameter').value==='0'&&document.getElementById('oHSR_UGS_RDia').value==='0'&&document.getElementById('oHSR_UGSR_Depth').value==='0')){
								
									de_kyenable(true,'oHSR_Capacity');
								}" /></td>
								
								<td><label>UGSR Depth(Meter)</label> <html:text property="oHSR_UGSR_Depth"
								styleId="oHSR_UGSR_Depth"
								onkeypress="return validateKey1(event,	this,'9@20@2')"
								onchange="if(this.value!=''||this.value!='0'){
									disable_ctrl('oHSR_Capacity',true);
									null_ctrl('oHSR_Capacity');
								}
								if(this.value===''&& document.getElementById('oHSRCTDiameter').value===''&& document.getElementById('oHSR_UGS_RDia').value===''&& document.getElementById('oHSR_UGSR_Depth').value===''||(this.value=='0' && document.getElementById('oHSRCTDiameter').value==='0'&&document.getElementById('oHSR_UGS_RDia').value==='0'&&document.getElementById('oHSR_UGSR_Depth').value==='0')){
								
									de_kyenable(true,'oHSR_Capacity');
								}" /></td>
							
						<td><label>Full supply Level(in mtrs) </label> <html:text
								styleClass="ci5 " property="oHSR_Full_Supply_Level"
								styleId="OHSR_Full_Supply_Level"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						
						
					</tr>
					
					<tr>
					<td><label>Cost(Rs in lacs)</label> <html:text
								property="oHSR_Cost" styleId="OHSR_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Other Structures at water works</legend>
			<center>
				<table>

					<tr>
						<td><label>Other Structures at water works</label> <html:select
								property="otherStructure" styleId="otherStructure"
								style="width:120px" onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>
						<td><label>Pump Chamber/Dev. of water works</label> <html:text
								property="other_structures_at_waterworks"
								styleId="Other_structures_at_waterworks" onkeypress="return validateKey1(event,	this,'9@20@2')"/></td>
						<td><label>Cost(Rs in lacs)</label> <html:text
								property="other_structures_at_waterworks_Cost"
								styleId="Other_structures_at_waterworks_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
				</table>
			</center>
		</fieldset>
		<fieldset>
			<legend>Distribution Network</legend>
			<center>
				<table>
					<tr>

						<td colspan="5"><label>Select Distribution Network</label> <html:select
								property="distrNetwork" styleId="distrNetwork1"
								onchange="validateFiveYearPlan();">

								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>

					</tr>

					<tr>
						<td><label>Distributed Main from WSS to Village
								(Type)</label> <html:select property="distribution_WSS_to_village_type"
								styleId="Distribution_WSS_to_village_type">
								<html:option value="">--Please Select--</html:option>
								<html:option value="PVC">PVC</html:option>
								<html:option value="DI">DI </html:option>
								<html:option value="MS">MS</html:option>
								<html:option value="CI">CI</html:option>
								<html:option value="GI">GI</html:option>


							</html:select></td>
						<td><label>Distribution Main from WSS to Village
								(length In Kms)</label> <html:text
								property="distribution_WSS_to_village_length"
								styleId="Distribution_WSS_to_village_length"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>

						<%-- <td><label>Distribution within village (Type)</label> <html:select
								property="distribution_WSS_within_village_type"
								styleId="Distribution_WSS_within_village_type">

								<html:option value="">--Please Select--</html:option>
								<html:option value="PVC">PVC</html:option>
								<html:option value="DI">DI </html:option>
								<html:option value="MS">MS</html:option>
								<html:option value="CI">CI</html:option>
								<html:option value="GI">GI</html:option>

							</html:select></td> --%>
						<td><label>Distribution within village(Type PVC)
								(length In Kms)</label> <html:text
								property="distribution_WSS_within_village_type_pvc"
								styleId="distribution_WSS_within_village_type_pvc"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>


					</tr>

					<tr>
						<td><label>Distribution within village(Type DI/MS/CI)
								(length In Kms)</label> <html:text
								property="distribution_WSS_within_village_type_di_ms"
								styleId="distribution_WSS_within_village_type_di_ms"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>

						<td><label>Distribution within village(Type GI)
								(length In Kms)</label> <html:text
								property="distribution_WSS_within_village_type_gi"
								styleId="distribution_WSS_within_village_type_gi"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Cost(Rs in lacs)</label> <html:text
								property="distribution_Cost" styleId="distribution_Cost" /></td>
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>

			<legend>Provision of 100% metered connection</legend>
			<center>
				<table>
					<tr>
						<td colspan="5"><label>Select 100% metered connection</label>
							<html:select property="mtrConnection" styleId="mtrConnection"
								onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>
								<html:option value="Yes">Yes</html:option>
							</html:select></td>

					</tr>

					<tr>
						<td><label>No of connections</label> <html:text
								property="no_of_connections_100_mtr"
								styleId="No_of_connections_100_mtr"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>No of mtrs</label> <html:text styleClass="ci5 "
								property="no_of_mtrs_100_mtr" styleId="No_of_mtrs_100_mtr"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Cost (Rs in Lacs)</label> <html:text
								property="cost_100_mtr" styleId="Cost_100_mtr"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>

			<legend>Disinfection Unit</legend>
			<center>
				<table>
					<tr>
						<td><label>Select Disinfection Unit</label> <html:select
								property="disincUnit" styleId="disincUnit" style="width:120px"
								onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>

								<html:option value="Yes">Yes</html:option>
							</html:select></td>

						<td><label>Type</label> <html:select
								property="disinfection_Unit_Type"
								styleId="Disinfection_Unit_Type">
								<html:option value="">--Please Select--</html:option>
								<html:option value="Electronic">Electronic</html:option>
								<html:option value="Chlorinators">
								</html:option>
								<html:option value="Others">Others</html:option>
							</html:select></td>
						<td><label>Cost(Rs in lacs)</label> <html:text
								styleClass="ci5 " property="disinfection_Unit_Cost"
								styleId="Disinfection_Unit_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>

						<td>&nbsp;</td>

					</tr>


				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>Installation of water treatment plan , in case of
				quality village</legend>
			<center>
				<table>
					<tr>

						<td colspan="5"><label>Select installation of water
								treatment plan</label> <html:select property="insWaterPlant"
								styleId="insWaterPlant1" onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>

								<html:option value="Yes">Yes</html:option>
							</html:select></td>

					</tr>

					<tr>
						<td><label>Type</label> <html:select
								property="water_Treatment_plant_in_case_of_quality_village_type"
								styleId="Water_Treatment_plant_in_case_of_quality_village_type">
								<html:option value="">--Please Select--</html:option>
								<html:option value="RO">RO</html:option>
								<html:option value="Arsenic">Arsenic Removal </html:option>
								<html:option value="Deflorination">Deflorination</html:option>
								<html:option value="Othes">Others</html:option>
							</html:select></td>
						<td><label>Capacity</label> <html:text
								property="water_Treatment_plant_in_case_of_quality_village_capacity"
								styleId="Water_Treatment_plant_in_case_of_quality_village_capacity"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Cost(Rs in lacs)</label> <html:text
								styleClass="ci5 "
								property="water_Treatment_plant_in_case_of_quality_village_Cost"
								styleId="Water_Treatment_plant_in_case_of_quality_village_Cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<fieldset>

			<legend>Any Other Work</legend>
			<center>
				<table>
					<tr>
						<td colspan="5"><label>Select Any Other Work</label> <html:select
								property="otherWork" styleId="otherWork"
								onchange="validateFiveYearPlan();">
								<html:option value="No">No</html:option>

								<html:option value="Yes">Yes</html:option>
							</html:select></td>

					</tr>

					<tr>
						<td><label>Bulk water meter (Nos)</label> <html:text
								property="bulk_Water_meter_no" styleId="Bulk_Water_meter_no"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Bulk water meter (Cost in Lacs)</label> <html:text
								property="bulk_Water_meter_cost" styleId="Bulk_Water_meter_cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
						<td><label>Extension/Sanction of New electric
								connection (Cost in Lacs)</label> <html:text styleClass="ci5 "
								property="extension_Sanction_of_new_electric_connection_cost"
								styleId="Extension_Sanction_of_new_electric_connection_cost"
								onkeypress="return validateKey1(event,	this,'9@20@2')" /></td>
					</tr>
				</table>
			</center>

			<fieldset>

				<legend>In case purposed work is to be carried out in other
					villages attached with scheme select village from</legend>
				<center>
					<table>
						<tr>
							<td>
								<div class="divgrid">
									<layout:datagrid styleClass="DATAGRID"
										property="fiveYearPlanGridBean" selectionAllowed="true"
										multipleSelectionAllowed="false" model="datagrid">
										<layout:datagridColumn property="villlgId" title="VillageId"
											mode="I,I,I"></layout:datagridColumn>

										<layout:datagridColumn property="independentNewWSS"
											title="Independent New WSS "></layout:datagridColumn>

										<layout:datagridColumn property="upgradationexistingWSS"
											title="Upgradation of existing WSS"></layout:datagridColumn>

										<layout:datagridColumn
											property="instltion_Wtr_Treatment_plant1"
											title="Installation of Water Treatment plant"></layout:datagridColumn>
										<layout:datagridColumn property="source_of_WSS1"
											title="Source of WSS "></layout:datagridColumn>
										<layout:datagridColumn property="shifted_to_canal_from_Other1"
											title="Whether being shifted to canal from tubewell / HP due to water quality"></layout:datagridColumn>
										<layout:datagridColumn
											property="driling_of_new_tubewell_machinery_size1"
											title="Driling of new tubewell & machinery size"></layout:datagridColumn>
										<layout:datagridColumn
											property="driling_of_new_tubewell_machinery_depth1"
											title="Driling of new tubewell & machinery depth"></layout:datagridColumn>
										<layout:datagridColumn
											property="driling_of_new_tubewell_machinery_capacity1"
											title="Driling of new tubewell & machinery  capacity"></layout:datagridColumn>
										<layout:datagridColumn
											property="driling_of_new_tubewell_machinery_cost1"
											title="Driling of new tubewell & machinery cost"></layout:datagridColumn>
										<layout:datagridColumn
											property="canal_based_Inlet_channel_Size_of_pipe1"
											title="Canal based Inlet channel  pipe size"></layout:datagridColumn>
										<layout:datagridColumn
											property="canal_based_Inlet_channel_length1"
											title="Canal based Inlet channel  pipe length"></layout:datagridColumn>
										<layout:datagridColumn
											property="canal_based_s_and_s_Capacity1"
											title="Canal based S & S Tank capacity"></layout:datagridColumn>
										<layout:datagridColumn
											property="canal_based_Filteration_Plan_type1"
											title="Canal based Filteration Plan Type"></layout:datagridColumn>
										<layout:datagridColumn
											property="canal_based_Filteration_Plan_capacity1"
											title="Canal based Filteration Plan capacity"></layout:datagridColumn>
										<layout:datagridColumn property="canal_based_Cost1"
											title="Canal based cost"></layout:datagridColumn>
										<layout:datagridColumn property="clear_water_tank1"
											title="Canal based clear tank tank"></layout:datagridColumn>
										<layout:datagridColumn property="oHSR_Capacity1"
											title="OHSR Capacity "></layout:datagridColumn>
											
											<layout:datagridColumn property="oHSRCTDiameter1"
											title="C.T Diameter(Meter)"></layout:datagridColumn>
											<layout:datagridColumn property="oHSR_CT_Depth1"
											title="C.T Depth(Meter)"></layout:datagridColumn>
											<layout:datagridColumn property="oHSR_UGS_RDia1"
											title="UGSR Diameter(Meter)"></layout:datagridColumn>
											<layout:datagridColumn property="oHSR_UGSR_Depth1"
											title="UGSR Depth(Meter)"></layout:datagridColumn>
											
										<layout:datagridColumn property="oHSR_Full_Supply_Level1"
											title="OHSR Full Supply Level "></layout:datagridColumn>
										<layout:datagridColumn property="OHSR_Cost" title="OHSR Cost"></layout:datagridColumn>
										<layout:datagridColumn
											property="other_structures_at_waterworks1"
											title="Other structures at water works"></layout:datagridColumn>
										<layout:datagridColumn
											property="other_structures_at_waterworks_Cost1"
											title="Other structures at water works cost"></layout:datagridColumn>
										<layout:datagridColumn
											property="distribution_WSS_to_village_type1"
											title="Distribution network WSS village type"></layout:datagridColumn>
										<layout:datagridColumn
											property="distribution_WSS_to_village_length1"
											title="Distribution network Village length"></layout:datagridColumn>

										<layout:datagridColumn
											property="distribution_WSS_within_village_type_pvc1"
											title="Distribution network within village(Type PVC) length"></layout:datagridColumn>

										<layout:datagridColumn
											property="distribution_WSS_within_village_type_di_ms1"
											title="Distribution network within village(Type DI/MS/CI) length"></layout:datagridColumn>

										<layout:datagridColumn
											property="distribution_WSS_within_village_type_gi1"
											title="Distribution network within village(Type GI) length"></layout:datagridColumn>

										<layout:datagridColumn property="distribution_Cost1"
											title="Distribution cost"></layout:datagridColumn>
										<layout:datagridColumn property="no_of_connections_100_mtr1"
											title="No of connections"></layout:datagridColumn>
										<layout:datagridColumn property="no_of_mtrs_100_mtr1"
											title="No of mtrs"></layout:datagridColumn>
										<layout:datagridColumn property="cost_100_mtr1"
											title="100%  metered connection cost"></layout:datagridColumn>
										<layout:datagridColumn property="disinfection_Unit_Type1"
											title="Disinfection Unit type"></layout:datagridColumn>
										<layout:datagridColumn property="disinfection_Unit_Cost1"
											title="Disinfection Unit cost"></layout:datagridColumn>
										<layout:datagridColumn
											property="water_Treatment_plant_in_case_of_quality_village_type1"
											title="water treatment plant in case of quality village type"></layout:datagridColumn>
										<layout:datagridColumn
											property="water_Treatment_plant_in_case_of_quality_village_capacity1"
											title="water treatment plant in case of quality village capacity"></layout:datagridColumn>
										<layout:datagridColumn
											property="water_Treatment_plant_in_case_of_quality_village_Cost1"
											title="water treatment plant in case of quality village cost"></layout:datagridColumn>
										<layout:datagridColumn property="bulk_Water_meter_no1"
											title="Bulk Water meter nos"></layout:datagridColumn>
										<layout:datagridColumn property="bulk_Water_meter_cost1"
											title="Bulk Water meter cost"></layout:datagridColumn>
										<layout:datagridColumn
											property="extension_Sanction_of_new_electric_connection_cost1"
											title="Extension / Sanction of new electric connection cost"></layout:datagridColumn>
									</layout:datagrid>
								</div>
							</td>
							<td><img src="images/add.png"
								onclick="validateVillageGrid()" width="20px" /><br> <img
								src="images/delete.png"
								onclick="StrutsLayout.setDatagridLineState('fiveYearPlanGridBean', 'removed')"
								width="20px" /></td>

						</tr>
					</table>
				</center>
			</fieldset>
	</html:form>
	<script type="text/javascript">
	
	function validateFiveYearPlann(){

		var status=false;
		
		if(document.getElementById("villaggesId").value!="" && document.getElementById("villaggesId").value!=null){
		
					if(document.getElementById("Driling_of_new_tubewell_machinery22").value=="Yes"){
	 				 
						status=validateDrillingofTubewellandMachinery();
					}
				
				 	if(document.getElementById("canal_based").value=="Yes"){
			 				 
							status=validatecanalBasedType_Structures();
					}
				 	
					if(document.getElementById("oHSR_Capacity1").value=="Yes"){
		 				 
						status=validateOhsrFiveYear();
					}
					
					if(document.getElementById("otherStructure").value=="Yes"){
		 				 
						status=validateOtherStructuresatwaterworks();
					}
					if(document.getElementById("distrNetwork1").value=="Yes"){
		 				 
						status=validateDistributed_Network();;
					}
					if(document.getElementById("mtrConnection").value=="Yes"){
		 				 
						status=validateProvisionMeteredConnection();
					}
					if(document.getElementById("disincUnit").value=="Yes"){
		 				 
						status=validateDisinfectionUnit();
					}
					if(document.getElementById("insWaterPlant1").value=="Yes"){
		 				 
						status=validateQualityVillage();
					}
					if(document.getElementById("otherWork").value=="Yes"){
		 				 
						status=validateAnyOtheWork();
					}
		
		
	    }
		
		return status;
	}
	
	//Any Other Work
	function validateAnyOtheWork(){
	
		if(document.getElementById("Bulk_Water_meter_no").value==""){
			alert("please enter Bulk Water meter no");
			return false;
		}
		if(document.getElementById("Bulk_Water_meter_cost").value==""){
			alert("please enter Bulk Water meter cost");
			return false;
		}
		if(document.getElementById("Extension_Sanction_of_new_electric_connection_cost").value==""){
			alert("please enter Extension Sanction of new electric connection cost");
			return false;
		}
		
		return true;
	
	}
	//Installation of water treatmen plan , in case of quality village
	function validateQualityVillage(){
	
		if(document.getElementById("Water_Treatment_plant_in_case_of_quality_village_type").value==""){
			alert("please enter Water Treatment plant in case of quality village type");
			return false;
		}
		if(document.getElementById("Water_Treatment_plant_in_case_of_quality_village_capacity").value==""){
			alert("please enter Water Treatment plant in case of quality village capacity");
			return false;
		}
		if(document.getElementById("Water_Treatment_plant_in_case_of_quality_village_Cost").value==""){
			alert("please enter Water Treatment plant in case of quality village Cost");
			return false;
		}
		return true;
	
	}
	//Disinfection Unit
	function validateDisinfectionUnit(){
	
		if(document.getElementById("Disinfection_Unit_Type").value==""){
			alert("please enter Disinfection Unit Type");
			return false;
		}
		if(document.getElementById("Disinfection_Unit_Cost").value==""){
			alert("please enter Disinfection Unit Cost");
			return false;
		}
		return true;
	
	}
	//Provision of 100% metered connection
	function validateProvisionMeteredConnection(){
	
		if(document.getElementById("No_of_connections_100_mtr").value==""){
			alert("please enter No of connections 100 mtr");
			return false;
		}
		if(document.getElementById("No_of_mtrs_100_mtr").value==""){
			alert("please enter No of mtrs 100 mtr");
			return false;
		}
		if(document.getElementById("Cost_100_mtr").value==""){
			alert("please enter Cost 100 mtr");
			return false;
		}
	 return true;
	}
	
	//In case of Distributed Network
	function validateDistributed_Network(){
	
		if(document.getElementById("Distribution_WSS_to_village_type").value==""){
			alert("Please Select Distributed Main from WSS to Village (Type)");
			return false;
		}
		if(document.getElementById("Distribution_WSS_to_village_length").value==""){
			alert("Please enter Distributed Main from WSS to Village (Length)");
			return false;
		}
		/* if(document.getElementById("Distribution_WSS_within_village_type").value==""){
			alert("please Select Distributed within village (Type)");
			return false;
		}
		if(document.getElementById("Distribution_WSS_within_village_length").value==""){
			alert("please enter Distributed within village (length)");
			return false;
		} */

		if(document.getElementById("distribution_WSS_within_village_type_pvc").value==""){
			alert("Please Distribution within village(Type PVC) (length In Kms)");
			return false;
		}

		if(document.getElementById("distribution_WSS_within_village_type_di_ms").value==""){
			alert("Distribution within village(Type DI/MS/CI) (length In Kms)");
			return false;
		}

		if(document.getElementById("distribution_WSS_within_village_type_gi").value==""){
			alert("Distribution within village(Type GI) (length In Kms)");
			return false;
		}
		
		
		if(document.getElementById("distribution_Cost").value==""){
			alert("please enter Cost");
			return false;
		}
	return true;
	}
	
	//Other Structures at water works   
	function validateOtherStructuresatwaterworks(){
	if(document.getElementById("Other_structures_at_waterworks").value==""){
			alert("please enter (Pump Chamber / Dev. of Water Works)");
			return false;
		}
		
		if(document.getElementById("Other_structures_at_waterworks_Cost").value==""){
			alert("please enter cost (Other Structure at water Works)");
			return false;
		}
		return true;
	}
	
	//Second Section New
function validateSectionTwo(){
	
	/* if(document.getElementById("schemeType").value==""){
			alert("please select Scheme Type");
			return false;
		} */

	if(document.getElementById("datepicker19").value!=""){
			
			if(document.getElementById("schemeExpenditure").value===""){
				alert("please enter Cost Expenditure Carried Out on scheme(Rs. In Lacs)");
				document.getElementById("schemeExpenditure").style.borderColor="red";
				return false;
			}
		}
	
	/* if(document.getElementById("schemeOperatedBy").value==""){
		alert("please select Operating Arrangement");
		return false;
	} */
	if(document.getElementById("schemeOperatedBy").value==="DWSS"){
		if(document.getElementById("staffScheme").value==""){
			alert("Please select Details of arrangement of staff in 2017 In case of DWSS Opeated");
			return false;
		}
	}
	return true;

}
	
	//OHSR
	function validateOhsrFiveYear(){
		
		
	if(document.getElementById("oHSRCTDiameter").value==""&&document.getElementById("oHSR_CT_Depth").value==""&&document.getElementById("oHSR_UGS_RDia").value==""&&document.getElementById("oHSR_UGSR_Depth").value==""){
	
		if(document.getElementById("oHSR_Capacity").value==""){
			alert("please enter OSHR Capacity");
			return false;
		}
	}
	if(document.getElementById("oHSR_Capacity").value==="" || document.getElementById("oHSR_Capacity").value==="0"){
		
		if(document.getElementById("oHSRCTDiameter").value==""){
			alert("please enter C.T Diameter(Meter)");
			return false;
		}
		if(document.getElementById("oHSR_CT_Depth").value==""){
			alert("please enter C.T Depth(Meter)");
			return false;
		}
		if(document.getElementById("oHSR_UGS_RDia").value==""){
			alert("please enter UGSR Diameter(Meter)");
			return false;
		}
		if(document.getElementById("oHSR_UGSR_Depth").value==""){
			alert("please enter UGSR Depth(Meter)");
			return false;
		}
	}
		
		if(document.getElementById("OHSR_Full_Supply_Level").value==""){
			alert("please enter  OSHR Full Supply Level");
			return false;
		}
		
		if(document.getElementById("OHSR_Cost").value==""){
			alert("please enter OSHR cost ");
			return false;
		}
		return true;
		
	}
	function validatecanalBasedType_Structures(){
		if(document.getElementById("canal_based_Inlet_channel_Size_of_pipe").value==""){
				alert("please enter canal based Inlet channel Size of pipe");
				return false;
			}
			
			if(document.getElementById("canal_based_Inlet_channel_length").value==""){
				alert("please enter  canal based Inlet channel length");
				return false;
			}
			
			if(document.getElementById("canal_based_s_and_s_Capacity").value==""){
				alert("please enter canal based ss Capacity");
				return false;
			}
			
			if(document.getElementById("canal_based_Filteration_Plan_type").value==""){
				alert("please enter canal based Filteration Plan type ");
				return false;
			}
			
			if(document.getElementById("canal_based_Filteration_Plan_capacity").value==""){
				alert("please enter canal based Filteration Plan capacity ");
				return false;
			}
			
			if(document.getElementById("canal_based_Cost").value==""){
				alert("please enter Canal Based cost ");
				return false;
			}
			if(document.getElementById("clear_water_tank").value==""){
				alert("please enter clear water tank");
				return false;
			}
			return true;
			
		}
	function validateDrillingofTubewellandMachinery(){
		if(document.getElementById("Driling_of_new_tubewell_machinery_size").value==""){
				alert("please enter Driling of new tubewell machinery size");
				return false;
			}
			
			if(document.getElementById("Driling_of_new_tubewell_machinery_depth").value==""){
				alert("please enter  Driling of new tubewell machinery depth");
				return false;
			}
			
			if(document.getElementById("Driling_of_new_tubewell_machinery_capacity").value==""){
				alert("please enter Driling  of new tubewell machinery capacity");
				return false;
			}
			
			if(document.getElementById("Driling_of_new_tubewell_machinery_cost").value==""){
				alert("please enter Driling of new tubewell machinery cost ");
				return false;
			}
			return true;
		}
	//schemeSrc
	
	
	
	function validateSectionsss(){
		var status;
		    status=validateSectionTwo();
		    if(status){
			if(document.getElementById("schemeSrc").value=="TUBEWELL"){
				var status;
				status=validateTubewell();
				if(status===true){
					status=validateOshr();
					if(status){
						if(document.getElementById("Ground_water_potable_status").value === "0"){
							status=validateWaterqualitystatus();
						}
							if(status){
							if(document.getElementById("disinfecReq").value!=""){
									status=validatedisinfection_unit_installed();
								}
								 if(status){
									status=validateElectric_connection_details();
								} 
							}
					}
				}
				
			}
			if(document.getElementById("schemeSrc").value=="CANAL"){
				
				var status;
				status=validateCanal();
				if(status===true){
					status=validateOshr();
					if(status){
						if(document.getElementById("Ground_water_potable_status").value === "0"){
							status=validateWaterqualitystatus();
						}
							if(status){
								if(document.getElementById("disinfecReq").value!=""){
									status=validatedisinfection_unit_installed();
								}
								 if(status){
									status=validateElectric_connection_details();
									
								} 
							}
					}
				}
				
			}
			if(document.getElementById("schemeSrc").value=="HANDPUMP"){
						if(document.getElementById("Ground_water_potable_status").value === "0"){
							status=validateWaterqualitystatus();
						}
							if(status){
								if(document.getElementById("disinfecReq").value!=""){

								status=validatedisinfection_unit_installed();
										/* if(status){
												status=validateFunctionalityStatus();
											} */
											}
										}
								}
			
			    }
					
					return status;
		}
	
		function validateSectionssss(){
			var status;
			    status=validateSectionTwo();
			    if(status){
				if(document.getElementById("schemeSource").value=="TUBEWELL"){
					var status;
					status=validateTubewell();
					if(status===true){
						status=validateOshr();
						if(status){
							if(document.getElementById("Ground_water_potable_status").value === "0"){
								status=validateWaterqualitystatus();
							}
								if(status){
								if(document.getElementById("disinfecReq").value!=""){
										status=validatedisinfection_unit_installed();
									}
									 if(status){
										status=validateElectric_connection_details();
									} 
								}
						}
					}
					
				}
				if(document.getElementById("schemeSource").value=="CANAL"){
					
					var status;
					status=validateCanal();
					if(status===true){
						status=validateOshr();
						if(status){
							if(document.getElementById("Ground_water_potable_status").value === "0"){
								status=validateWaterqualitystatus();
							}
								if(status){
									if(document.getElementById("disinfecReq").value!=""){
										status=validatedisinfection_unit_installed();
									}
									 if(status){
										status=validateElectric_connection_details();
										
									} 
								}
						}
					}
					
				}
				if(document.getElementById("schemeSource").value=="HANDPUMP"){
							if(document.getElementById("Ground_water_potable_status").value === "0"){
								status=validateWaterqualitystatus();
							}
								if(status){
									if(document.getElementById("disinfecReq").value!=""){

									status=validatedisinfection_unit_installed();
											/* if(status){
													status=validateFunctionalityStatus();
												} */
												}
											}
									}
				
				    }
						
						return status;
			}
		
	function validateSections(){
	var status;
	    status=validateSectionTwo();
	    if(status){
		if(document.getElementById("schemeSource").value=="TUBEWELL"){
			if(document.getElementById('multipleSource').value===''||document.getElementById('multipleSource').value==='No'){
				status=validateTubewell();
			}
			if(status===true){
				status=validateOshr();
				if(status){
					if(document.getElementById("Ground_water_potable_status").value === "0"){
						status=validateWaterqualitystatus();
					}
						if(status){
						if(document.getElementById("disinfecReq").value!=""){
								status=validatedisinfection_unit_installed();
							}
							 if(status){
								status=validateElectric_connection_details();
								if(status){
									status=validateDistributionPipeLine();
									if(status){

										status=validateWaterConnectionDetails();
										/* if(status){
											status=validateFunctionalityStatus();
										} */
									}
								}
							} 
						}
				}
			}
			
		}
		if(document.getElementById("schemeSource").value=="CANAL"){
			
			var status;
			if(document.getElementById('multipleSource').value===''||document.getElementById('multipleSource').value==='No'){
				status=validateCanal();
			}
			if(status===true){
				status=validateOshr();
				if(status){
					if(document.getElementById("Ground_water_potable_status").value === "0"){
						status=validateWaterqualitystatus();
					}
						if(status){
							if(document.getElementById("disinfecReq").value!=""){
								status=validatedisinfection_unit_installed();
							}
							 if(status){
								status=validateElectric_connection_details();
								if(status){
									status=validateDistributionPipeLine();
									if(status){

										status=validateWaterConnectionDetails();
										/* if(status){

											status=validateFunctionalityStatus();
										} */
									}
								}
							} 
						}
				}
			}
			
		}
		if(document.getElementById("schemeSource").value=="HANDPUMP"){
					if(document.getElementById("Ground_water_potable_status").value === "0"){
						status=validateWaterqualitystatus();
					}
						if(status){
							if(document.getElementById("disinfecReq").value!=""){

							status=validatedisinfection_unit_installed();
									/* if(status){
											status=validateFunctionalityStatus();
										} */
										}
									}
							}
		
		    }
				
				return status;
	}
	
	function validatett(){
		if(document.getElementById("Upgradation_of_existing_WSS").value==="1"){
			document.getElementById("Independent_New_WSS").value="0";
	 
	}
	}
	function validateUpgrade(obj){
		
		if(document.getElementById("Independent_New_WSS").value==="1"){
				document.getElementById("Upgradation_of_existing_WSS").value="0";
		 }
		
	}
	function validateWorkProposedtobeExecuted(){
		
		/* if(document.getElementById("Independent_New_WSS").value=="Yes"){
				if(document.getElementById("Independent_New_WSS_Cost").value==""){
				alert("please enter  Independent New WSS Cost");
				return false;
			 }
			} */
			
			/* if(document.getElementById("Upgradation_of_existing_WSS").value=="Yes"){
				if(document.getElementById("Upgradation_of_existing_WSS_Cost").value==""){
				alert("please enter Upgradation of existing WSS Cost");
				return false;
				}
			} */
			
			/* if(document.getElementById("Instltion_Wtr_Treatment").value=="Yes"){

			if(document.getElementById("Instltion_Wtr_Treatment_plant").value==""){
				alert("please enter Installation water Treatment plant ");
				return false;
			 }
			} */
			return true;
			
		}
	
	
	
	
	
	function validateTubewell(){
		if(document.getElementById("depthOfTubewell").value==""){
			alert("please enter depth of Tubewell");
			return false;
		}
		if(document.getElementById("sizeOfTubewell").value==""){
			alert("please enter size of tubewell");
			return false;
		}
		if(document.getElementById("datepicker6").value==""){
			alert("please select Year of Drilling date");
			return false;
		}
		if(document.getElementById("dischargeComm").value==""){
			alert("Please enter Original Discharge at the time of commissioning");
			return false;
		}
		if(document.getElementById("presentDis").value==""){
			alert("please select Present Discharge");
			return false;
		}
		if(document.getElementById("presentSpringLevel").value==""){
			alert("Please enter Present Spring Level in meters");
			return false;
		}
		if(document.getElementById("datepicker").value==""){
			alert("please select Time of Installation of New Machinery date");
			return false;
		}
		if(document.getElementById("capacity_of_machinery").value==""){
			alert("please enter Capacity of Machinery in BPH");
			return false;
		}
		
		return true;
	}
	
	//In case of OHSR
	function validateOshr(){
	
		if(document.getElementById("noOfOhsr").value!=""&&document.getElementById("noOfOhsr").value!="0"){
			
		if(document.getElementById("datepicker2").value==""){
			alert("please Select Time of Construction");
			return false;
		}
		if(document.getElementById("oHSRCTDia").value===''&& document.getElementById("oHSRCTDepth").value===''&&document.getElementById("oHSRUGSRDia").value===''&& document.getElementById("oHSRUGSRDepth").value===''){
			if(document.getElementById("oHSRCapcity").value===""){
				alert("please enter Capacity in Litres");
				document.getElementById("oHSRCapcity").style.borderColor='red';
				return false;
			}
		}
		if(document.getElementById("oHSRCapcity").value===""||document.getElementById("oHSRCapcity").value==="0"){
				if(document.getElementById("oHSRCTDia").value===''){
					alert("please enter C.T Diameter(Meter)");
					document.getElementById("oHSRCTDia").style.borderColor='red';
					return false;
				}
				if(document.getElementById("oHSRCTDepth").value===''){
					alert("please enter C.T Depth(Meter)");
					document.getElementById("oHSRCTDepth").style.borderColor='red';
					return false;
				}
				if(document.getElementById("oHSRUGSRDia").value===''){
					alert("please enter UGSR Diameter(Meter)");
					document.getElementById("oHSRUGSRDia").style.borderColor='red';
					return false;
				}
				if(document.getElementById("oHSRUGSRDepth").value===''){
					alert("please enter UGSR Depth(Meter)");
					document.getElementById("oHSRUGSRDepth").style.borderColor='red';
					return false;
				}
		}
		if(document.getElementById("oHSRWorkingCondition").value==""){
			alert("please select Ohsr working condition");
			return false;
		}
		
		
		//oHSRCondition
		/* if(document.getElementById("oHSRCondition").value==""){
			alert("please select Ohsr Condition");
			return false;
		} */
		
		

		if(document.getElementById("oHSRFullSuplyLvl").value==""){
			alert("please enter Ohsr full supply level");
			document.getElementById("oHSRFullSuplyLvl").style.borderColor='red';

			return false;
		}
		/* if(document.getElementById("Ground_water_potable_status").value==""){
			alert("please select ground water potable status");
			return false;
		} */
		}if(document.getElementById("noOfOhsr").value===""){
			alert("please enter No of Ohsr");
			return false;
		}
	return true;
	}
	
	
	//In case of Details of disinfection unit if installed

	function validateOhsrDismantling(){
		var flag=true;
		if(document.getElementById("oHSRWorkingCondition").value==='0'){
			if(document.getElementById('oHSRCondition').value===''){
				alert("Please select If no, Does it needs Repair");
				flag=false;
			}
		
			if(document.getElementById('oHSRCondition').value==="Dismantling"){
				if(document.getElementById('oHSRDismantling').value===''){
					alert("please select In case of dismantling whether technical report from any institution has been received ");
					flag=false;
				}
			}
		}
		return flag;
		
	}
		
		function validatedisinfection_unit_installed(){
		
			if(document.getElementById('disinfecReq').value==='Yes'){
			if(document.getElementById("disinfectionType").value==""){
				alert("please Select Type of disinfection unit if installed");
				return false;
			}
			if(document.getElementById("datepicker3").value==""){
				alert("please Select Time of Installation");
				return false;
			}
			if(document.getElementById("disinfectionPrStatus").value==""){
				alert("please select Pesent Status of disinfection unit in 2017");
				return false;
			}
			}
			
		return true;
		}
	
		//Existing Water Quality Status


		function validateWaterqualitystatus(){
		
			/* if(document.getElementById("Ground_water_potable_status").value==""){
				alert("please Select Is ground water potable");
				return false;
			} */
			if(document.getElementById("Ground_water_potable_No").value==""){
				alert("please Select Type of contimation");
				return false;
			}
			if(document.getElementById("Preventive_measures_adopted").value==""){
				alert("please Select Preventive measures adopted");
				return false;
			}
			if(document.getElementById("Capacity_of_plant").value==""){
				alert("please enter Capacity of Plant in LPH");
				return false;
			}
			if(document.getElementById("datepicker4").value==""){
				alert("Please Select Date of Installation");
				return false;
			}
			if(document.getElementById("Being_operated_by").value==""){
				alert("please Select Being Operated by");
				return false;
			}
			if(document.getElementById("Volume_of_Water_Daily_basis").value==""){
				alert("please enter Volume of water being treated on regular basis");
				return false;
			}
			if(document.getElementById("disposal_of_reject_water").value==""){
				alert("please Select Arrangement for disposal of reject water");
				return false;
			}
			if(document.getElementById("Penetration_in_percentage").value==""){
				alert("please enter Penetration in percentage");
				return false;
			}
			if(document.getElementById("present_rate_of_user_charges").value==""){
				alert("Please enter Present rate of user charges");
				return false;
			}
			if(document.getElementById("Seperate_Sanctioned_Load").value==""){
				alert("please enter Sanctioned Load In case of seperate electic connection ");
				return false;
			}
			if(document.getElementById("Seperate_Pending_eletric_bill31032017").value==""){
				alert("please enter Pending Electic Bill In case of seperate electic connection ");
				return false;
			}
			if(document.getElementById("Average_monthly_bill_of_Treatment_plant").value==""){
				alert("Please enter Average Monthly bill In case of seperate electic connection");
				return false;
			}
			return true;
		}
	
		function validateElectric_connection_details(){
			
			if(document.getElementById("sanctionedLoad").value==""){
				alert("please enter Sanctioned Load in KWH");
				return false;
			}
			if(document.getElementById("pendingBill3103").value==""){
				alert("please enter Pending Electic Bill as on 31-03-2017");
				return false;
			}
			if(document.getElementById("pendingBill3006").value==""){
				alert("please enter Pending Electic Bill as on 31-06-2017");
				return false;
			}
			if(document.getElementById("avgMonthBillofWSS").value==""){
				alert("please enter Average Monthly bill of WSS");
				return false;
			}
		return true;
		}
		
		//Details of Distribution Pipe Line (in KM)
		function validateDistributionPipeLine(){
		if(document.getElementById("Pipeline_AC").value==""){
				alert("please enter Pipeline AC");
				return false;
			}
			
			if(document.getElementById("Pipeline_MS_DI_Ci").value==""){
				alert("please enter Pipeline MS DI Ci");
				return false;
			}
			
			if(document.getElementById("Pipeline_GI").value==""){
				alert("please enter  Pipeline GI");
				return false;
			}
			
			if(document.getElementById("Pipeline_PVC").value==""){
				alert("please enter Pipeline PVC");
				return false;
			}
			
			if(document.getElementById("Functional_Distribution_percentage").value==""){
				alert("please enter functional Distribution Percentage");
				return false;
			}
			return true;
			
		}
		
		//Water Connection Details
		function validateWaterConnectionDetails(){
		if(document.getElementById("Household_water_connection").value==""){
				alert("please enter Household Water Connection");
				return false;
			}
			
			if(document.getElementById("Watered_connection").value==='1'){
				if(document.getElementById("Metered_connection").value==""){
					alert("please enter  Metered connection");
					return false;
				}
			}
			
			
			//Metered_supply_recovery
			 if(document.getElementById("Metered_supply_recovery").value==="Flat"){
				if(document.getElementById("Flat_rate_charges_per_month").value==""){
					alert("Please enter Flat rate charges per month");
					return false;
				}
			}
			 
		
			if(document.getElementById("Arrear_of_recovery_with_consumers_as01042017").value==""){
				alert("please enter  Arrear of recoverywith consumers as01042017");
				return false;
			}
			
			return true;
		}
		
		function validateCanal(){
			if(document.getElementById("inletType").value==""){
				alert("please Select Type of Inlet channel");
				return false;
			}
			if(document.getElementById("inlet_channel_Lenght").value==""){
				alert("please enter Inlet Channel Lenght");
				return false;
			}
			if(document.getElementById("pipe_type").value==""){
				alert("please select Pipe Type");
				return false;
			}
			if(document.getElementById("capacitySSTank").value==""){
				alert("Please enter Capacity of SS Tank in cubic meter");
				return false;
			}
			if(document.getElementById("capacityHLTank").value==""){
				alert("please enter Capacity of HL Tank in cubic meter");
				return false;
			}
			if(document.getElementById("capacityCWTank").value==""){
				alert("Please enter Capacity of SW Tank in cubic meter");
				return false;
			}
			if(document.getElementById("filtertion_type").value==""){
				alert("please select Filteration Units (Type)");
				return false;
			}
			if(document.getElementById("filtertionNo").value==""){
				alert("please enter Filteration Units (Nos)");
				return false;
			}
			if(document.getElementById("capacityRawWater").value==""){
				alert("please enter Capacity of Raw Water Motor ");
				return false;
			}
			if(document.getElementById("capacityClearWater").value==""){
				alert("please enter Capacity of Clear Water Motor ");
				return false;
			}
			return true;
		}
	
	
	 
	
	
	triggerEvent(document.getElementById('locationId'), 'onchange');
	<%if (MisUtility.ifEmpty(request.getAttribute("blockId"))) {%>
			document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		<%}%>
			triggerEvent(document.getElementById('blockId'), 'onchange');
			<%if (request.getAttribute("villgId") != null) {%>
			document.getElementById("villgId").value="<%=request.getAttribute("villgId")%>";
		<%}%>
			triggerEvent(document.getElementById('villgId'), 'onchange');
		<%if (MisUtility.ifEmpty(request.getAttribute("schemeId"))) {%>
			document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
	<%}%>
		
	</script>
</body>
</html>