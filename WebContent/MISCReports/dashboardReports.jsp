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
                 document.dashboardReportsForm.action = "dashboardReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=RPT004";
                 document.dashboardReportsForm.submit();
            }
            function de_filePrint() {
				document.dashboardReportsForm.action = "dashboardReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=RPT004";
				document.dashboardReportsForm.submit();              
            }
            function de_filePDF() {
				document.dashboardReportsForm.action = "dashboardReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=RPT004";
                document.dashboardReportsForm.submit();              				
	        }
            function de_fileExcel() {
				document.dashboardReportsForm.action = "dashboardReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=RPT004";
                document.dashboardReportsForm.submit();              				
            }
		

</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
--></head>
<body>
			
                    <html:form action="dashboardReportsAction" styleId="dashboardReportsForm">
                     <fieldset>
                      <legend>Dash-Boards</legend>
                      	<center>
                      <table>                        
 							<tr> 
						<td>Please Select Dash-Boards</td>
						<td colspan="2">
							<html:select property="selectReport" styleId="selectReport" style="width: 455px" styleClass="ci5" onchange="return createChooseFields(this);">
								<html:option value="">Select Dash-Boards</html:option>
								<html:option value="DBRPT001">Habitations Survey-Freezed-April, 2008</html:option>
								<html:option value="DBRPT002">Program to be Implemented in Villages/ Main Habitations (MH)</html:option>
								<html:option value="DBRPT003">Program to be Implemented in Other Habitations (OH)</html:option>						
							</html:select>
						</td>
						<script type="text/javascript">
							document.getElementById("selectReport").selectedIndex=0;
						</script>
					</tr>
                      </table>
                      </center>
         	        </fieldset>            
                    </html:form>
               	

</body>
</html>