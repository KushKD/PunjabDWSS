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

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>

<script type="text/javascript">

	
	function de_filePDF() {
		document.testResultReportForm.action = "testResultReportAction.do?method=filePDF&d__mode="
				+ d__mode + "&menuId=WQ010";
		document.testResultReportForm.submit();
	}
	
	function listbox_selectall(listID, isSelect) {
		var listbox = document.getElementById(listID);
		for (var count = 0; count < listbox.options.length; count++) {
			listbox.options[count].selected = isSelect;
		}
	}
</script>
</head>

<html:html>
<html:form action="testResultReportAction" method="post"
	styleId="testResultReportForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Test Result Report</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="Waterquality" key="lab.name" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="lab" style="width: 150px;" styleId='lab'
					styleClass="ci5 form-control"
					onchange="ajaxFunction('testResultReportAction.do?labId='+this.value+'&method=fetchSample','choose_fields')">
					<html:option value="">Select Lab</html:option>
					<html:options collection="labNames" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">Choose Field </label> <select
					id="choose_fields" name="choose_fields" class="cs3" size="10"
					multiple style="width: 355px"
					ondblclick="return listbox_moveacross('choose_fields', 'select_fields');">
				</select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">
				<br> <br> <br> <input type="button" value=">"
					onclick="return listbox_moveacross('choose_fields', 'select_fields');"
					style="width: 100px"> <br> <input type="button"
					value=">>"
					onclick="listbox_selectall('choose_fields', true);listbox_moveacross('choose_fields', 'select_fields');"
					style="width: 100px" /> <br> <input type="button"
					value="<" onclick=" return	listbox_moveacross('select_fields','choose_fields');" style="width: 100px" />
				<br> <input type="button" value="<<" onclick="
					listbox_selectall('select_fields', true);listbox_moveacross('select_fields','choose_fields');" style="width: 100px">
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">Choose Field </label>
				<html:select property="select_fields" styleId="select_fields"
					style="width: 355px" size="10" multiple="multiple" styleClass="ci5"
					ondblclick="return listbox_moveacross('select_fields', 'choose_fields');">
				</html:select>
			</div>
		</div>
	</div>
	<input type='hidden' name='labId' id='labId'>

	<!-- Table -->


</html:form>

</body>
<script type='text/javascript'>
	function listbox_selectall(listID, isSelect) {
		var listbox = document.getElementById(listID);
		for (var count = 0; count < listbox.options.length; count++) {
			listbox.options[count].selected = isSelect;
		}
	}

	function listbox_moveacross(sourceID, destID) {
		
		var src = document.getElementById(sourceID);
		var dest = document.getElementById(destID);
		for (var count = 0; count < src.options.length; count++) {
			if (src.options[count].selected == true) {
				var option = src.options[count];
				var newOption = document.createElement("option");
				newOption.value = option.value;
				newOption.text = option.text;
				newOption.selected = true;
				try {
					dest.add(newOption, null); //Standard                          
					src.remove(count, null);
				} catch (error) {
					dest.add(newOption); // IE only                          
					src.remove(count);
				}
				count--;
			}
		}
	}
</script>

</html:html>