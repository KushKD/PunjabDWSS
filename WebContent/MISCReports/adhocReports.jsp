<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>

	<script type="text/javascript" src="js/popup_report.js"></script>

<script type="text/javascript">
			function de_view() {
                 document.adhocReportsForm.action = "adhocReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=RPT003";
                 document.adhocReportsForm.submit();
            }
            function de_filePrint() {
				document.adhocReportsForm.action = "adhocReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=RPT003";
				document.adhocReportsForm.submit();              
            }
            function de_filePDF() {
				document.adhocReportsForm.action = "adhocReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=RPT003";
                document.adhocReportsForm.submit();              				
	        }
            function de_fileExcel() {
				document.adhocReportsForm.action = "adhocReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=RPT003";
                document.adhocReportsForm.submit();              				
            }
			function listbox_selectall(listID, isSelect) {         
				var listbox = document.getElementById(listID);         
				for(var count=0; count < listbox.options.length; count++) {             
					listbox.options[count].selected = isSelect;     
				} 
			} 

            function listbox_moveacross(sourceID, destID) {     
            	var src = document.getElementById(sourceID);     
            	var dest = document.getElementById(destID);       
            	for(var count=0; count < src.options.length; count++) {           
            		if(src.options[count].selected == true) {                 
            			var option = src.options[count];                   
            			var newOption = document.createElement("option");                 
            			newOption.value = option.value;                 
            			newOption.text = option.text;                 
            			newOption.selected = true;                 
            			try {                          
            				dest.add(newOption, null); //Standard                          
							src.remove(count, null);                  
            			}catch(error) {                          
            				dest.add(newOption); // IE only                          
            				src.remove(count);                  
            			}                 
            			count--;         
            		}     
            	} 
            }
			function listbox_movetocriteria() {     
            	var src = document.getElementById('select_fields');
            	var dest1 = document.getElementById('cField4');
            	var dest2 = document.getElementById('criteria');
				if(dest1.value!='' && dest1.value!='undefined'){
					dest2.value=dest2.value+ " " +src.value.split("@")[1];
				}else{
					for(var count=1; count<5; count++){
						var dest = document.getElementById('cField'+count);
						if(dest.value=='' || dest.value=='undefined'){
							dest.value=dest.value+ " " +src.value.split("@")[1];
							break;
						}
					} 
				}
			}
			function createChooseFields(obj){
				var src = document.getElementById('choose_fields');					
				var dest = document.getElementById('select_fields');				
				select_innerHTML(src,'');
				select_innerHTML(dest,'');
				if(obj.value=='vw_adhoc_scheme_village_details_14_sep_2011') {					
					select_innerHTML(src,'<option value="District Id@district_id@String@50">District Id(String)</option><option value="District Name@district_name@String@50">District Name(String)</option><option value="Constituency Id@constituency_id@String@50">Constituency Id(String)</option><option value="Constituency Name@constituency_name@String@50">Constituency Name(String)</option>											<option value="Block Name@block_name@String@50">Block Name(String)</option><option value="Block Id@block_id@String@50">Block Id(String)</option><option value="Location Id@location_id@String@50">Location Id(String)</option><option value="Location Name@location_name@String@50">Location Name(String)</option><option value="Program Id@program_id@String@50">Program Id(String)</option><option value="Program Name@program_name@String@50">Program Name(String)</option><option value="Scheme Name@scheme_name@String@50">Scheme Name(String)</option><option value="Water Works Existing / New@water_works_existing_new@String@50">Water Works Existing / New(String)</option><option value="Admin Approval No@admin_approval_no@String@50">Admin Approval No(String)</option><option value="Admin Approval Date@admin_approval_date@Date@50">Admin Approval Date Name(Date)</option><option value="Admin Approval Amount@admin_approval_amount@String@50">Admin Approval Amount(Numeric)</option><option value="Commissioned Village NC@village_commissioned_nc@String@50">Commissioned Village NC(String)</option><option value="Commissioned Village PC@village_commissioned_pc@String@50">Commissioned Village PC(String)</option><option value="Commissioned Village SB@village_commissioned_sb@String@50" >Commissioned Village SB(String)</option><option value="Commissioned Village OH@village_commissioned_oh@String@50" >Commissioned Village OH(String)</option><option value="Village Id@village_id@String@50">Village Id(String)</option><option value="Month of Commissioning@month_of_commissioning@Date@50">Month of Commissioning(Date)</option><option value="Scheme Source@source_of_scheme@String@50">Scheme Source(String)</option><option value="Scheme Id@scheme_id@String@50">Scheme Id(String)</option><option value="No Of Water Connection@no_of_water_connection@String@50">No Of Water Connection(Numeric)</option><option value="Scheme Operated By@scheme_operated_by@String@50">Scheme Operated By(String)</option><option value="Ro Plant@ro_plant@String@50">Ro Status(Boolean)</option>');
				}
				if(obj.value=='vw_master_info'){
					select_innerHTML(src,'<option value="Zone Id@zone_id@String@50">Zone Id(String)</option><option value="Zone Name@zone_name@String@50">Zone Name(String)</option><option value="Circle Id@circle_id@String@50">Circle Id(String)</option><option value="Circle Name@circle_name@String@50" >Circle Name(String)</option><option value="District Id@district_id@String@50" >District Id(String)</option><option value="District Name@district_name@String@50" >District Name(String)</option><option value="Division Id@divisional_id@String@50" >Division Id(String)</option><option value="Division Name@divisional_name@String@50" >Division Name(String)</option>');
				}
				if(obj.value=='vw_mst_village_info'){
					select_innerHTML(src,'<option value="Zone Id@zone_id@String@50">Zone Id(String)</option><option value="Zone Name@zone_name@String@50">Zone Name(String)</option><option value="Circle Id@circle_id@String@50">Circle Id(String)</option><option value="Circle Name@circle_name@String@50" >Circle Name(String)</option><option value="District Id@district_id@String@50" >District Id(String)</option><option value="District Name@district_name@String@50" >District Name(String)</option><option value="Division Id@divisional_id@String@50" >Division Id(String)</option><option value="Division Name@divisional_name@String@50" >Division Name(String)</option><option value="Block Id@block_id@String@50">Block Id(String)</option><option value="Block Name@block_name@String@50">Block Name(String)</option><option value="Constituency Id@constituency_id@String@50">Constituency Id(String)</option><option value="Constituency Name@constituency_name@String@50">Constituency Name(String)</option><option value="Village Id@village_id@String@50">Village Id(String)</option><option value="Village Name@village_name@String@50">Village Name(String)</option><option value="Habitaion Type@habitation_type@String@50">Habitaion Type(String)</option><option value="Parent Habitaion@parent_habitation@String@50">Parent Habitaion(String)</option><option value="Parliament Constituency Name@parliament_constituency_name@String@50">Parliament Constituency Name(String)</option><option value="Name of Gram Panchayat@name_of_gram_panchayat@String@50">Name of Gram Panchaya(String)</option><option value="MC/Bechirag/etc@spl_flags@String@50">MC/Bechirag/etc(String)</option><option value="No Of Water Connection@no_of_water_connection@String@50">No Of Water Connection(Numeric)</option><option value="HouseHolds@households@String@50">HouseHolds(Numeric)</option><option value="Total Population@total_population@String@50">Total Population(Numeric)</option>');
				}
			}

</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
--></head>
<body>

                        <html:form action="adhocReportsAction" styleId="adhocReportsForm">
                        <fieldset>
                        <legend>Ad-hoc Query Reports</legend>
                        <center>
                        <table>                        
   						<tr> 
								<td>Please Select Template</td>
								<td colspan="2">
									<html:select property="selectReport" styleId="selectReport" style="width: 255px" styleClass="ci5" onchange="return createChooseFields(this);">
										<html:option value="">Select Template</html:option>
										<html:option value="vw_adhoc_scheme_village_details_14_sep_2011">Habitations Commissioning</html:option>	
										<html:option value="vw_master_info">Master Information</html:option>	
										<html:option value="vw_mst_village_info">Village Master Information</html:option>				
									</html:select>
								</td>
<script type="text/javascript">
document.getElementById("selectReport").selectedIndex=0;
</script>
							</tr>
                        </table>
							<table>
								<tr>
									<td>Choose Fields</td>
									<td></td>
									<td>Select Fields</td>
								</tr>
								<tr>									
									<td>
										<select id="choose_fields" name="choose_fields" class="cs3" size="10" multiple style="width: 355px"  ondblclick="return listbox_moveacross('choose_fields', 'select_fields');">
											
										</select>
									</td>
									<td>
										<input type="button" value=">" onclick="return listbox_moveacross('choose_fields', 'select_fields');" style="width: 100px">
										<br>
										<input type="button" value=">>" onclick="listbox_selectall('choose_fields', true);listbox_moveacross('choose_fields', 'select_fields');" style="width: 100px">
										<br>
										<input type="button" value="<" onclick="return listbox_moveacross('select_fields','choose_fields');" style="width: 100px">
										<br>
										<input type="button" value="<<" onclick="listbox_selectall('select_fields', true);listbox_moveacross('select_fields','choose_fields');" style="width: 100px">
										<br>
										<input type="button" value="Add to Criteria" onclick="listbox_movetocriteria();" style="width: 100px">
									</td>
									<td>										
										<html:select property="select_fields" styleId="select_fields" style="width: 355px" size="10" multiple="multiple" styleClass="ci5"  ondblclick="return listbox_moveacross('select_fields', 'choose_fields');">
																		
										</html:select>
									</td>
                            </tr> 	
							<tr>
								<td> Set Criteria</td>
								<td></td>
							</tr>
							<tr>
									<td>Criteria 1<html:text property="cField1"
									styleId="cField1" styleClass="ci5" style="float:right"></html:text></td>
									<td><html:select property="opt1"
									styleId="opt1" styleClass="cs2" style="width:100px">
									<html:option value="=">Equal To</html:option>
									<html:option value="<>">Not Equal To</html:option>
									<html:option value=">">Greater Than</html:option>
									<html:option value="<">Less Than</html:option>
									<html:option value=">=">Greater Than or Equal To</html:option>
									<html:option value="<=">Less Than or Equal To</html:option>
									<html:option value="like">Like</html:option>
									<html:option value="notlike">Not Like</html:option>
									<html:option value="blank">Blank</html:option>
									<html:option value="notblank">Not Blank</html:option>
									</html:select></td>
									<td><html:text property="cValue1"
									styleId="cValue1" styleClass="ci5" ></html:text></td>
							</tr>
							<tr><td></td>
								<td><html:radio property="rad1" value="AND" />AND
								<html:radio property="rad1" value="OR"/>OR</td>
							</tr>
							<tr>
									<td>Criteria 2<html:text property="cField2"
									styleId="cField2" styleClass="ci5" style="float:right"></html:text></td>
									<td><html:select property="opt2"
									styleId="opt2" styleClass="cs2" style="width:100px">
									<html:option value="=">Equal To</html:option>
									<html:option value="<>">Not Equal To</html:option>
									<html:option value=">">Greater Than</html:option>
									<html:option value="<">Less Than</html:option>
									<html:option value=">=">Greater Than or Equal To</html:option>
									<html:option value="<=">Less Than or Equal To</html:option>
									<html:option value="like">Like</html:option>
									<html:option value="notlike">Not Like</html:option>
									<html:option value="blank">Blank</html:option>
									<html:option value="notblank">Not Blank</html:option>
									</html:select></td>
									<td><html:text property="cValue2"
									styleId="cValue2" styleClass="ci5" ></html:text></td>
							</tr>
							<tr><td></td>
								<td><html:radio property="rad2" value="AND"/>AND
								<html:radio property="rad2" value="OR"/>OR</td>
							</tr>
							<tr>
									<td>Criteria 3<html:text property="cField3"
									styleId="cField3" styleClass="ci5" style="float:right"></html:text></td>
									<td><html:select property="opt3"
									styleId="opt3" styleClass="cs2" style="width:100px">
									<html:option value="=">Equal To</html:option>
									<html:option value="<>">Not Equal To</html:option>
									<html:option value=">">Greater Than</html:option>
									<html:option value="<">Less Than</html:option>
									<html:option value=">=">Greater Than or Equal To</html:option>
									<html:option value="<=">Less Than or Equal To</html:option>
									<html:option value="like">Like</html:option>
									<html:option value="notlike">Not Like</html:option>
									<html:option value="blank">Blank</html:option>
									<html:option value="notblank">Not Blank</html:option>
									</html:select></td>
									<td><html:text property="cValue3"
									styleId="cValue3" styleClass="ci5" ></html:text></td>
							</tr>
							<tr><td></td>
								<td><html:radio property="rad3" value="AND" />AND
								<html:radio property="rad3" value="OR"/>OR</td>
							</tr>
							<tr>
									<td>Criteria 4<html:text property="cField4"
									styleId="cField4" styleClass="ci5" style="float:right"></html:text></td>
									<td><html:select property="opt4"
									styleId="opt4" styleClass="cs2" style="width:100px">
									<html:option value="=">Equal To</html:option>
									<html:option value="<>">Not Equal To</html:option>
									<html:option value=">">Greater Than</html:option>
									<html:option value="<">Less Than</html:option>
									<html:option value=">=">Greater Than or Equal To</html:option>
									<html:option value="<=">Less Than or Equal To</html:option>
									<html:option value="like">Like</html:option>
									<html:option value="notlike">Not Like</html:option>
									<html:option value="blank">Blank</html:option>
									<html:option value="notblank">Not Blank</html:option>
									</html:select></td>
									<td><html:text property="cValue4"
									styleId="cValue4" styleClass="ci5" ></html:text></td>
							</tr>
							<tr><td></td>
								<td><html:radio property="rad4" value="AND"/>AND
								<html:radio property="rad4" value="OR"/>OR</td>
							</tr>
							
							<tr>
								<td colspan="3">							
									
									<html:textarea styleId="criteria" property="criteria" cols="104" rows="3" styleClass="ci5">

									</html:textarea>
								</td>
							</tr>
							</table>	
							</center>					
						</fieldset>            
                            
                        </html:form>
	
 </body>
