<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<script type="text/javascript" src="js/popup_report.js"></script>
<script type="text/javascript">
<!--
	var isPDF = false;
	hide_ctrl('modalPeriod', true);
	hide_ctrl('modalUpto', true);
	hide_ctrl('modalSwap', true);
	hide_ctrl('modalPeriodSwap', true);
	hide_ctrl('modalPeriod1', true);
	hide_ctrl('modalUptoSwap', true);
	hide_ctrl('modalPeriod2', true);
	hide_ctrl('modalScheme', true);
	hide_ctrl('modalIBRD', true);
	hide_ctrl('modalDetailIBRD', true);
	hide_ctrl('modalPeriodSwap111', true);
	//	hide_ctrl('modalPeriodSwap222', true);

	disable_enable('swap_nonswap_abstract');

	function de_view() {
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method=view&d__mode="
				+ d__mode + "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	function de_filePrint() {
		document.committedLiabilityReportForm.action = "estimatesAwardContractsReportAction.do?method=filePrint&d__mode="
				+ d__mode + "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	// FILE PDF	
	function de_filePDF() {
		isPDF = true;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		var periodReport = habitationDataReport();

		var listPeriod = "RPT001_3,RPT001_3_blk,RPT001_3_consti,RPT001_3_div,PMMRPT001_1,PMMRPT001_2,PMMRPT001_3,PMMRPT001_1_dtl,PMMRPT001_2_dtl,PMMRPT001_3_dtl,PMMRPT001_6,PMMRPT001_7,PMMRPT001_8,PMMRPT001_9,PMMRPT001_10,PMMRPT001_11,PMMRPT001_12,PMMRPT001_13,PMMRPT001_14,PMMRPT001_15,PMMRPT001_16,PMMRPT001_36,PMMRPT001_F4";
		/*var listUpto="RPT001_4,RPT001_4_div,RPT001_4_consti,RPT001_6,RPT001_6_div,RPT001_6_consti,status_MH,status_OH,RPT001_5,RPT001_5_div,RPT001_5_consti,PMMRPT001_5_abstr_np_swap,PMMRPT001_5_abstr_np_nonswap,PMMRPT001_5_dtl_np_swap,PMMRPT001_5_dtl_np_nonswap,PMMRPT001_5_abstr_p_ida,PMMRPT001_5_abstr_p_nonida,PMMRPT001_5_dtl_p_ida,PMMRPT001_5_dtl_p_nonida,PMMRPT001_5_abstr70,PMMRPT001_5_detail,PMMRPT001_32,PMMRPT001_24_details,RPT001_4_water_teriff_cell_div,PMMRPT001_24_blocks,PMMRPT001_24_consi";*/

		var listUpto = "RPT001_4,RPT001_4_div,RPT001_4_consti,RPT001_6,RPT001_6_div,RPT001_6_consti,status_MH,status_OH,RPT001_5,RPT001_5_div,RPT001_5_consti,PMMRPT001_5_abstr_np_swap,PMMRPT001_5_abstr_np_nonswap,PMMRPT001_5_dtl_np_swap,PMMRPT001_5_dtl_np_nonswap,PMMRPT001_5_abstr_p_ida,PMMRPT001_5_abstr_p_nonida,PMMRPT001_5_dtl_p_ida,PMMRPT001_5_dtl_p_nonida,PMMRPT001_5_abstr70,PMMRPT001_5_detail,PMMRPT001_32,PMMRPT001_5_dtl_sew";

		var listSwap = 'PMMRPT001_27,PMMRPT001_28P1,PMMRPT001_28P2,PMMRPT001_28P3,PMMRPT001_17,PMMRPT001_33P1,PMMRPT001_33P2';
		/* var listSwap='PMMRPT001_27,PMMRPT001_28P1,PMMRPT001_28P2,PMMRPT001_28P3,PMMRPT001_17,PMMRPT001_33P1,PMMRPT001_33P2'; */

		var listPeriodSwap = "PMMRPT001_20";
		var listPeriodSwapC = "PMMRPT001_5";

		var listPeriod1 = "PMMRPT001_21,PMMRPT001_21_all,PMMRPT001_21_all_const,PMMRPT001_21_const,PMMRPT001_34,PMMRPT001_34_dtl";
		var listUptoSwap = "PMMRPT001_G4_abs,PMMRPT001_G5_det";
		var listPeriod2 = "PMMRPT001_35";
		var listScheme = "PMMRPT001_A2.4_imp,PMMRPT001_A2.5_imp";
		var listVillage = "PMMRPT001_A12_abs,PMMRPT001_24_details";
		var listSchemeIDA = "PMMRPT001_A2.5_dis,PMMRPT001_A2.5_sew,PMMRPT001_A2.5_wm,";
		var listPeriodSchemeY = "PMMRPT001_33P3";

		var listIBRDProgMgt = "PMMRPT001_A2.4_A1,PMMRPT001_A2.4_1AWL,PMMRPT001_A2.4_2A,PMMRPT001_A2.4_2B";
		var listIBRDProgMgtDetails = "PMMRPT001_A2.5_A1,PMMRPT001_A2.5_1AWL,PMMRPT001_A2.5_2A,PMMRPT001_A2.5_2B";

		//var listScheme = "Abstract of Status of Estimates and Award of Contracts of IDA Water Supply Schemes and Villages covered/to be covered up to Dec 2013,PMMRPT001_001_details,PMMRPT001_A2.4_dis,PMMRPT001_A2.5_dis";

		//alert(111);
		var listPeriodSwap111 = "Abs_MH";
		//alert(listPeriodSwap111);
		var listPeriodSwap222 = "Dtl_MH";

		if (inList(periodReport, listIBRDProgMgt)) {
			// By Ranjay
			hide_ctrl('modalIBRD', false);
			//document.getElementById("selectPeriod_ibrd_a_upto").checked=true;
			document.getElementById("ibrd_ok").focus();
		}

		else if (inList(periodReport, listPeriodSwap111)) {
			hide_ctrl('modalPeriodSwap111', false);
			document.getElementById("selectPeriod_a_period111").checked = true;
			var rdb = document.getElementById("selectPeriod_s_period111");
			rdb.checked = 'true';
			//alert(rdb.id);
			rdb.style.display = 'none';
		} else if (inList(periodReport, listPeriodSwap222)) {
			hide_ctrl('modalPeriodSwap111', false);
			var rdb = document.getElementById("selectPeriod_s_period111");
			rdb.checked = 'true';
			rdb.style.display = 'none';
			document.getElementById("period_ok11").focus();
		}

		else if (inList(periodReport, listIBRDProgMgtDetails)) {
			// By Ranjay
			hide_ctrl('modalDetailIBRD', false);
			//document.getElementById("selectPeriod_ibrd_a_upto").checked=true;
			document.getElementById("ibrd_ok").focus();
		} else if (inList(periodReport, listPeriod)) {
			hide_ctrl('modalPeriod', false);
			document.getElementById("selectPeriod_a_period").checked = true;
			document.getElementById("period_ok").focus();
		} else if (inList(periodReport, listPeriod2)) {
			hide_ctrl('modalPeriod2', false);
			document.getElementById("selectPeriod_a_period3").checked = true;
			document.getElementById("period_ok2").focus();
		} else if (inList(periodReport, listScheme)) {
			hide_ctrl('modalIda', false);
			document.getElementById("selectPeriod_ida_a_upto2").checked = true;
			document.getElementById("ida_ok").focus();
		} else if (inList(periodReport, listVillage)) {
			hide_ctrl('modalSM', false);
			document.getElementById("selectPeriod_SM_a_upto2").checked = true;
			document.getElementById("SM_ok").focus();
		} else if (inList(periodReport, listSchemeIDA)) {
			hide_ctrl('modalIda2', false);
			document.getElementById("selectPeriod_ida_a_upto5").checked = true;
			document.getElementById("ida_ok2").focus();
		} else if (inList(periodReport, listPeriodSchemeY)) {
			hide_ctrl('modalCST', false);
			document.getElementById("selectPeriod_CST_a_upto").checked = true;
			document.getElementById("CST_ok2").focus();
		} else if (inList(periodReport, listUpto)) {
			hide_ctrl('modalUpto', false);
			document.getElementById("selectPeriod_a_upto").checked = true;
			document.getElementById("upto_ok").focus();
		} else if (inList(periodReport, listSwap)) {
			hide_ctrl('modalSwap', false);
			document.getElementById("swap_ok").focus();
		}

		else if (inList(periodReport, listPeriodSwap)) {
			hide_ctrl('modalPeriodSwap', false);
			document.getElementById("selectPeriod_a_period1").checked = true;
			document.getElementById("period_ok1").focus();

		}

		else if (inList(periodReport, listPeriodSwapC)) {
			hide_ctrl('modalPeriodSwapC', false);
			document.getElementById("selectPeriod_a_period9").checked = true;
			document.getElementById("period_ok1").focus();
		} else if (inList(periodReport, listPeriod1)) {
			hide_ctrl('modalPeriod1', false);
			document.getElementById("selectPeriod_a_period2").checked = true;
			document.getElementById("period_ok1").focus();
		} else if (inList(periodReport, listUptoSwap)) {
			hide_ctrl('modalUptoSwap', false);
			document.getElementById("selectPeriod_a_upto1").checked = true;
			document.getElementById("period_ok1").focus();
		} else {
			document.getElementById("fromDate").value = "01-04-2008";
			document.getElementById("toDate").value = today;
			document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method=filePDF&d__mode="
					+ d__mode + "&menuId=PMMRPT001";
			document.estimatesAwardContractsReportForm.submit();

		}
	}
	//selectPeriod_s_period  ,  selectPeriod_s_period3  ,  fromDate_pop  ,  toDate_pop  ,  fromDate_pop3  ,  toDate_pop3
	function de_file_period() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		if (document.getElementById("selectPeriod_s_period").checked) {
			var fromDate_pop = document.getElementById("fromDate_pop").value;
			var toDate_pop = document.getElementById("toDate_pop").value;
			document.getElementById("fromDate").value = (fromDate_pop == null
					|| fromDate_pop == '' || fromDate_pop == undefined) ? '01-04-2008'
					: fromDate_pop;
			document.getElementById("toDate").value = (toDate_pop == null
					|| toDate_pop == '' || toDate_pop == undefined) ? today
					: toDate_pop;
		} else if (document.getElementById("selectPeriod_s_period3").checked) {
			var fromDate_pop3 = document.getElementById("fromDate_pop3").value;
			var toDate_pop3 = document.getElementById("toDate_pop3").value;
			document.getElementById("fromDate").value = (fromDate_pop3 == null
					|| fromDate_pop3 == '' || fromDate_pop3 == undefined) ? '01-04-2008'
					: fromDate_pop3;
			document.getElementById("toDate").value = (toDate_pop3 == null
					|| toDate_pop3 == '' || toDate_pop3 == undefined) ? today
					: toDate_pop3;
		} else {
			document.getElementById("fromDate").value = "01-04-2008";
			document.getElementById("toDate").value = today;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}
	//selectPeriod_s_upto  ,  selectPeriod_s_upto1  ,  selectPeriod_s_upto2  ,  month  ,  month1  ,  month2  ,  year  ,  year1  ,  year2
	function de_file_upto() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		if (document.getElementById("selectPeriod_s_upto").checked) {
			if (document.getElementById("month").value == '01'
					|| document.getElementById("month").value == '03'
					|| document.getElementById("month").value == '05'
					|| document.getElementById("month").value == '07'
					|| document.getElementById("month").value == '08'
					|| document.getElementById("month").value == '10'
					|| document.getElementById("month").value == '12') {
				document.getElementById("toDate").value = '31-'
						+ document.getElementById("month").value + "-"
						+ document.getElementById("year").value;
			} else if (document.getElementById("month").value == '04'
					|| document.getElementById("month").value == '06'
					|| document.getElementById("month").value == '09'
					|| document.getElementById("month").value == '11') {
				document.getElementById("toDate").value = '30-'
						+ document.getElementById("month").value + "-"
						+ document.getElementById("year").value;
			} else if (document.getElementById("month").value == '02') {
				if (isleap(document.getElementById("year").value)) {
					document.getElementById("toDate").value = '29-'
							+ document.getElementById("month").value + "-"
							+ document.getElementById("year").value;
				} else {

					document.getElementById("toDate").value = '28-'
							+ document.getElementById("month").value + "-"
							+ document.getElementById("year").value;
				}
			}
		} else if (document.getElementById("selectPeriod_s_upto1").checked) {
			if (document.getElementById("month1").value == '01'
					|| document.getElementById("month1").value == '03'
					|| document.getElementById("month1").value == '05'
					|| document.getElementById("month1").value == '07'
					|| document.getElementById("month1").value == '08'
					|| document.getElementById("month1").value == '10'
					|| document.getElementById("month1").value == '12') {
				document.getElementById("toDate").value = '31-'
						+ document.getElementById("month1").value + "-"
						+ document.getElementById("year1").value;
			} else if (document.getElementById("month1").value == '04'
					|| document.getElementById("month1").value == '06'
					|| document.getElementById("month1").value == '09'
					|| document.getElementById("month1").value == '11') {
				document.getElementById("toDate").value = '30-'
						+ document.getElementById("month1").value + "-"
						+ document.getElementById("year1").value;
			} else if (document.getElementById("month1").value == '02') {

				if (isleap(document.getElementById("year1").value)) {
					document.getElementById("toDate").value = '29-'
							+ document.getElementById("month1").value + "-"
							+ document.getElementById("year1").value;
				} else {

					document.getElementById("toDate").value = '28-'
							+ document.getElementById("month1").value + "-"
							+ document.getElementById("year1").value;
				}
			}
		} else if (document.getElementById("selectPeriod_s_upto2").checked) {

			if (document.getElementById("month2").value == '01'
					|| document.getElementById("month2").value == '03'
					|| document.getElementById("month2").value == '05'
					|| document.getElementById("month2").value == '07'
					|| document.getElementById("month2").value == '08'
					|| document.getElementById("month2").value == '10'
					|| document.getElementById("month2").value == '12') {
				document.getElementById("toDate").value = '31-'
						+ document.getElementById("month2").value + "-"
						+ document.getElementById("year2").value;
			} else if (document.getElementById("month2").value == '04'
					|| document.getElementById("month2").value == '06'
					|| document.getElementById("month2").value == '09'
					|| document.getElementById("month2").value == '11') {
				document.getElementById("toDate").value = '30-'
						+ document.getElementById("month2").value + "-"
						+ document.getElementById("year2").value;
			} else if (document.getElementById("month2").value == '02') {

				if (isleap(document.getElementById("year1").value)) {
					document.getElementById("toDate").value = '29-'
							+ document.getElementById("month2").value + "-"
							+ document.getElementById("year2").value;
				} else {

					document.getElementById("toDate").value = '28-'
							+ document.getElementById("month2").value + "-"
							+ document.getElementById("year2").value;
				}
			}
		} else {
			document.getElementById("toDate").value = today;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	function de_file_swap() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		document.getElementById("toDate").value = today;
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	function de_file_periodSwapFRP() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		if (document.getElementById("selectPeriod_s_period111").checked) {
			var toDate_pop111 = document.getElementById("toDate_pop111").value = today;
			document.getElementById("toDate").value = (toDate_pop111 == null
					|| toDate_pop111 == '' || toDate_pop111 == undefined) ? today
					: toDate_pop111;
		} else {
			document.getElementById("toDate").value = today;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";

		document.estimatesAwardContractsReportForm.submit();
	}

	/* 
	function de_file_periodSwapFRP() {
			var currentTime = new Date();
			var month = currentTime.getMonth() + 1;
			var day = currentTime.getDate();
			var year = currentTime.getFullYear();
			var today = (day + "-" + month + "-" + year);
			//alert(today);
			if (document.getElementById("selectPeriod_s_period111").checked) 
			{
				//var toDate_pop = document.getElementById("toDate_pop111").value;
				
				var toDate_pop=document.getElementById("toDate").value = today;
				alert(toDate_pop);
				document.getElementById("toDate").value = (toDate_pop == null || toDate_pop == '' || toDate_pop == undefined) ? today : toDate_pop;
			}
			else {
				document.getElementById("toDate").value = today;
			}
			document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
					+ (isPDF ? "filePDF" : "fileExcel")
					+ "&d__mode="
					+ d__mode
					+ "&menuId=PMMRPT001";
			document.estimatesAwardContractsReportForm.submit();
		}
	 */

	//selectPeriod_s_period1  ,  selectPeriod_s_period2  ,  fromDate_pop9  ,  toDate_pop1
	function de_file_periodSwap() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();

		var today = (day + "-" + month + "-" + year);

		if (document.getElementById("selectPeriod_s_period1").checked) {
			var fromDate_pop = document.getElementById("fromDate_pop1").value;

			var toDate_pop = document.getElementById("toDate_pop1").value;
			document.getElementById("fromDate").value = (fromDate_pop == null
					|| fromDate_pop == '' || fromDate_pop == undefined) ? '01-04-2008'
					: fromDate_pop;
			document.getElementById("toDate").value = (toDate_pop == null
					|| toDate_pop == '' || toDate_pop == undefined) ? today
					: toDate_pop;
		} else if (document.getElementById("selectPeriod_s_period2").checked) {
			var fromDate_pop = document.getElementById("fromDate_pop2").value;
			var toDate_pop = document.getElementById("toDate_pop2").value;
			document.getElementById("fromDate").value = (fromDate_pop == null
					|| fromDate_pop == '' || fromDate_pop == undefined) ? '01-04-2008'
					: fromDate_pop;
			document.getElementById("toDate").value = (toDate_pop == null
					|| toDate_pop == '' || toDate_pop == undefined) ? today
					: toDate_pop;
		} else {
			document.getElementById("fromDate").value = "01-04-2008";
			document.getElementById("toDate").value = today;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}
	//selectPeriod_s_period9  ,  fromDate_pop9  ,  toDate_pop9
	function de_file_periodSwapC() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		if (document.getElementById("selectPeriod_s_period9").checked) {
			var fromDate_pop = document.getElementById("fromDate_pop9").value;
			var toDate_pop = document.getElementById("toDate_pop9").value;
			document.getElementById("fromDate").value = (fromDate_pop == null
					|| fromDate_pop == '' || fromDate_pop == undefined) ? '01-04-2008'
					: fromDate_pop;
			document.getElementById("toDate").value = (toDate_pop == null
					|| toDate_pop == '' || toDate_pop == undefined) ? today
					: toDate_pop;
		} else {
			document.getElementById("fromDate").value = "01-04-2008";
			document.getElementById("toDate").value = today;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}
	//selectPeriod_ida_s_upto2  ,  phase1  ,  phase2  ,  All  ,  IDAType
	function de_file_ida_upto() {
		if (document.getElementById("selectPeriod_ida_s_upto2").checked) {
			var phase = document.getElementById("IDAType_pop").value;
			if (phase == "phase1") {
				document.getElementById("IDAType").value = phase;
			}
			if (phase == "phase2") {
				document.getElementById("IDAType").value = phase;
			}
			if (phase == "All") {
				document.getElementById("IDAType").value = "All";
			}
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}
	//selectPeriod_SM_s_upto2  ,  single_village  ,  multi_village  ,  All  ,  SMType
	function de_file_SM_upto() {
		if (document.getElementById("selectPeriod_SM_s_upto2").checked) {
			var phase = document.getElementById("SMType_pop").value;
			if (phase == "single_village") {
				document.getElementById("SMType").value = phase;
			}
			if (phase == "multi_village") {
				document.getElementById("SMType").value = phase;
			}
			if (phase == "All") {
				document.getElementById("SMType").value = "All";
			}
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	//selectPeriod_ida_s_upto5  ,  IDA  ,  IDA2  ,  ALL  ,   IDA5
	function de_file_IDAS_upto() {
		if (document.getElementById("selectPeriod_ida_s_upto5").checked) {
			var phase_ida = document.getElementById("IDA_pop5").value;
			if (phase_ida == "IDA") {
				document.getElementById("IDA5").value = phase_ida;
			}
			if (phase_ida == "IDA2") {
				document.getElementById("IDA5").value = phase_ida;
			}
			if (phase_ida == "All") {
				document.getElementById("IDA5").value = "All";
			}
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	function de_file_IBRD_upto() {
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();

		document.getElementById("IBRD_pop").value = "PMMRPT001_A2.4_A1";
		document.getElementById("IBRD_pop_detail").value = "PMMRPT001_A2.5_A1";
		//alert("Final call");

	}

	function de_file_CST_upto() {
		var year_type = document.getElementById("Year_Type").value;
		var mntr_dtl1 = document.getElementById("progress_monitoring_dtl").value;
		var schetype = document.getElementById("schemeType77").value;
		if (mntr_dtl1 == "PMMRPT001_33P3") {
			document.getElementById('selectReport7').value = schetype;
		}
		if (year_type != "") {
			document.getElementById("yearType").value = year_type;
		}
		document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel")
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMMRPT001";
		document.estimatesAwardContractsReportForm.submit();
	}

	function de_fileExcel() {
		isPDF = false;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		var periodReport = habitationDataReport();
		var listPeriod = "RPT001_3,RPT001_3_blk,RPT001_3_consti,RPT001_3_div,PMMRPT001_1,PMMRPT001_2,PMMRPT001_3,PMMRPT001_6,PMMRPT001_7,PMMRPT001_8,PMMRPT001_9,PMMRPT001_10,PMMRPT001_11,PMMRPT001_12,PMMRPT001_13,PMMRPT001_14,PMMRPT001_15,PMMRPT001_16,PMMRPT001_36,PMMRPT001_F4";
		/*var listUpto="RPT001_4,RPT001_4_div,RPT001_4_consti,RPT001_6,RPT001_6_div,RPT001_6_consti,status_MH,status_OH,RPT001_5,RPT001_5_div,RPT001_5_consti,PMMRPT001_5_abstr_np_swap,PMMRPT001_5_abstr_np_nonswap,PMMRPT001_5_dtl_np_swap,PMMRPT001_5_dtl_np_nonswap,PMMRPT001_5_abstr_p_ida,PMMRPT001_5_abstr_p_nonida,PMMRPT001_5_dtl_p_ida,PMMRPT001_5_dtl_p_nonida,PMMRPT001_5_abstr70,PMMRPT001_5_detail,PMMRPT001_32,PMMRPT001_24_details,RPT001_4_water_teriff_cell_div,PMMRPT001_24_blocks,PMMRPT001_24_consi";*/

		var listUpto = "RPT001_4,RPT001_4_div,RPT001_4_consti,RPT001_6,RPT001_6_div,RPT001_6_consti,status_MH,status_OH,RPT001_5,RPT001_5_div,RPT001_5_consti,PMMRPT001_5_abstr_np_swap,PMMRPT001_5_abstr_np_nonswap,PMMRPT001_5_dtl_np_swap,PMMRPT001_5_dtl_np_nonswap,PMMRPT001_5_abstr_p_ida,PMMRPT001_5_abstr_p_nonida,PMMRPT001_5_dtl_p_ida,PMMRPT001_5_dtl_p_nonida,PMMRPT001_5_abstr70,PMMRPT001_5_detail,PMMRPT001_32,PMMRPT001_5_dtl_sew";

		//var listSwap='PMMRPT001_27,PMMRPT001_28P1,PMMRPT001_28P2,PMMRPT001_28P3,PMMRPT001_17,PMMRPT001_33P1,PMMRPT001_33P2';
		var listSwap = 'PMMRPT001_27,PMMRPT001_28P1,PMMRPT001_28P2,PMMRPT001_28P3,PMMRPT001_17,PMMRPT001_33P1,PMMRPT001_33P2';
		var listPeriodSwap = "PMMRPT001_20";
		var listPeriodSwapC = "PMMRPT001_5";

		var listPeriod1 = "PMMRPT001_21,PMMRPT001_21_all,PMMRPT001_21_all_const,PMMRPT001_21_const,PMMRPT001_34,PMMRPT001_34_dtl";
		var listUptoSwap = "PMMRPT001_G4_abs,PMMRPT001_G5_det";
		var listPeriod2 = "PMMRPT001_35";
		//var listScheme="PMMRPT001_001,PMMRPT001_001_details,PMMRPT001_A2.4_dis,PMMRPT001_A2.5_dis,PMMRPT001_A2.4_imp";
		var listScheme = "PMMRPT001_A2.4_imp,PMMRPT001_A2.5_imp";
		var listVillage = "PMMRPT001_A12_abs,PMMRPT001_24_details";
		var listSchemeIDA = "PMMRPT001_A2.5_dis,PMMRPT001_A2.5_sew,PMMRPT001_A2.5_wm";
		var listPeriodSchemeY = "PMMRPT001_33P3";

		var listIBRDProgMgt = "PMMRPT001_A2.4_A1,PMMRPT001_A2.4_1AWL,PMMRPT001_A2.4_2A,PMMRPT001_A2.4_2B";
		var listIBRDProgMgtDetails = "PMMRPT001_A2.5_A1,PMMRPT001_A2.5_1AWL,PMMRPT001_A2.5_2A,PMMRPT001_A2.5_2B";

		//alert(1);
		var listPeriodSwap111 = "Abs_MH";
		//alert(listPeriodSwap111);
		var listPeriodSwap222 = "Dtl_MH";
		//alert(listPeriodSwap222);

		if (inList(periodReport, listIBRDProgMgt)) {
			hide_ctrl('modalIBRD', false);
			//document.getElementById("selectPeriod_ibrd_a_upto").checked=true;
			document.getElementById("ibrd_ok").focus();
		} else if (inList(periodReport, listPeriodSwap111)) {
			hide_ctrl('modalPeriodSwap111', false);
			document.getElementById("toDate_pop111").defaultValue = today;
			var rdb = document.getElementById("selectPeriod_s_period111");
			rdb.checked = 'true';
			//alert(rdb.id);
			rdb.style.display = 'none';

		} else if (inList(periodReport, listPeriodSwap222)) {
			hide_ctrl('modalPeriodSwap111', false);
			document.getElementById("toDate_pop111").defaultValue = today;
			var rdb = document.getElementById("selectPeriod_s_period111");
			rdb.checked = 'true';
			//alert(rdb.id);
			rdb.style.display = 'none';
		} else if (inList(periodReport, listIBRDProgMgtDetails)) {
			hide_ctrl('modalDetailIBRD', false);
			//document.getElementById("selectPeriod_ibrd_a_upto").checked=true;
			document.getElementById("ibrd_ok").focus();
		} else if (inList(periodReport, listPeriod)) {
			hide_ctrl('modalPeriod', false);
			document.getElementById("selectPeriod_a_period").checked = true;
			document.getElementById("period_ok").focus();
		} else if (inList(periodReport, listPeriod2)) {
			hide_ctrl('modalPeriod2', false);
			document.getElementById("selectPeriod_a_period3").checked = true;
			document.getElementById("period_ok2").focus();
		} else if (inList(periodReport, listPeriodSchemeY)) {
			hide_ctrl('modalCST', false);
			document.getElementById("selectPeriod_CST_a_upto").checked = true;
			document.getElementById("CST_ok2").focus();
		} else if (inList(periodReport, listSchemeIDA)) {
			hide_ctrl('modalIda2', false);
			document.getElementById("selectPeriod_ida_a_upto5").checked = true;
			document.getElementById("ida_ok2").focus();
		} else if (inList(periodReport, listScheme)) {
			hide_ctrl('modalIda', false);
			document.getElementById("selectPeriod_ida_a_upto2").checked = true;
			document.getElementById("ida_ok").focus();
		} else if (inList(periodReport, listVillage)) {
			hide_ctrl('modalSM', false);
			document.getElementById("selectPeriod_SM_a_upto2").checked = true;
			document.getElementById("SM_ok").focus();
		} else if (inList(periodReport, listUpto)) {
			hide_ctrl('modalUpto', false);
			document.getElementById("selectPeriod_a_upto").checked = true;
			document.getElementById("upto_ok").focus();
		} else if (inList(periodReport, listSwap)) {
			hide_ctrl('modalSwap', false);
			document.getElementById("swap_ok").focus();
		}

		else if (inList(periodReport, listPeriodSwap)) {
			hide_ctrl('modalPeriodSwap', false);
			document.getElementById("selectPeriod_a_period1").checked = true;
			document.getElementById("period_ok1").focus();
		}

		else if (inList(periodReport, listPeriodSwapC)) {
			hide_ctrl('modalPeriodSwapC', false);
			document.getElementById("selectPeriod_a_period9").checked = true;
			document.getElementById("period_ok1").focus();
		} else if (inList(periodReport, listPeriod1)) {
			hide_ctrl('modalPeriod1', false);
			document.getElementById("selectPeriod_a_period2").checked = true;
			document.getElementById("period_ok1").focus();
		} else if (inList(periodReport, listUptoSwap)) {
			hide_ctrl('modalUptoSwap', false);
			document.getElementById("selectPeriod_a_upto1").checked = true;
			document.getElementById("period_ok1").focus();
		} else {
			document.getElementById("fromDate").value = "01-04-2008";
			document.getElementById("toDate").value = today;
			document.estimatesAwardContractsReportForm.action = "estimatesAwardContractsReportAction.do?method=fileExcel&d__mode="
					+ d__mode + "&menuId=PMMRPT001";
			document.estimatesAwardContractsReportForm.submit();
		}
	}

	function setEnableDisablePeriod(flag) {
		if (flag == 'NO') {
			document.getElementById('td1').style.visibility = 'hidden';
		} else {
			document.getElementById('td1').style.visibility = 'visible';
		}
	}

	function disable_enable(ele) {
		var select = [ 'swap_nonswap_abstract', 'swap_nonswap_detail',
				'prwss_abstract', 'prwss_detail', 'program_management',
				'beneficiary_share', 'progress_monitoring_dtl',
				'rslt_indicator', 'infra_structure_develop', 'data_monitoring',
				'phy_fin_monitoring', 'prwssip_abstract', 'prwssip_detail' ];
		for ( var i = 0; i < select.length; i++) {
			if (ele == select[i]) {
				document.getElementById(ele).disabled = false;
				document.getElementById("selectReport" + (i + 1)).checked = true;
			} else {
				document.getElementById(select[i]).disabled = true;
				document.getElementById(select[i]).selectedIndex = 0;
			}
		}
	}

	function hideControl(value) {
		if (value == 'CONTRACTUAL') {
			hide_ctrl('permamentDiv', true);
			hide_ctrl('contractDiv', false);
		} else {
			hide_ctrl('contractDiv', true);
			hide_ctrl('permamentDiv', false);
		}
	}

	function IBRDDataReport() {
		var val = "";
		var rptDisp = document.getElementById("ABCD").value;
		var prgm_mgt = document.getElementById("IBRD_pop").value;
		if (prgm_mgt == "PMMRPT001_A2.4_A1"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_A1";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_A1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_A1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_A1";//need to change

		}
		
		if (prgm_mgt == "PMMRPT001_A2.4_1AWL"
			&& document.getElementById("selectReport5").checked) {

		if (rptDisp == "ALL")
			val = "PMMRPT001_A2.4_1AWL";
		if (rptDisp == "BW")
			val = "PMMRPT001_A2.4_1AWL";//need to change
		if (rptDisp == "CNW")
			val = "PMMRPT001_A2.4_1AWL";//need to change
		if (rptDisp == "DW")
			val = "PMMRPT001_A2.4_1AWL";//need to change

	}

		if (prgm_mgt == "PMMRPT001_A2.4_2A"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_2A";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_2A";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_2A";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_2A";//need to change
		}
		if (prgm_mgt == "PMMRPT001_A2.4_2B"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_2B";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_2B";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_2B";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_2B";//need to change
		}
		//alert("Ranjay IBRD Inside - " + val);
		return val;
	}

	function IBRDDataDetailReport() {
		var val = "";
		var rptDisp = document.getElementById("ABCD").value;
		var prgm_mgt = document.getElementById("IBRD_pop_detail").value;

		if (prgm_mgt == "PMMRPT001_A2.5_A1"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_A1";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_A1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_A1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_A1";//need to change
		}
		
		if (prgm_mgt == "PMMRPT001_A2.5_1AWL"
			&& document.getElementById("selectReport5").checked) {

		if (rptDisp == "ALL")
			val = "PMMRPT001_A2.5_1AWL";
		if (rptDisp == "BW")
			val = "PMMRPT001_A2.5_1AWL";//need to change
		if (rptDisp == "CNW")
			val = "PMMRPT001_A2.5_1AWL";//need to change
		if (rptDisp == "DW")
			val = "PMMRPT001_A2.5_1AWL";//need to change
	}

		if (prgm_mgt == "PMMRPT001_A2.5_2A"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_2A";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_2A";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_2A";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_2A";//need to change
		}
		if (prgm_mgt == "PMMRPT001_A2.5_2B"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_2B";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_2B";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_2B";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_2B";//need to change
		}
		//alert("Ranjay IBRD Detail Inside - " + val);
		return val;
	}

	function habitationDataReport() {
		var val = "";
		var rptDisp = document.getElementById("ABCD").value;
		var swap_nonswap_abstract = document
				.getElementById("swap_nonswap_abstract").value;
		var swap_nonswap_detail = document
				.getElementById("swap_nonswap_detail").value;

		var prwss_abstract = document.getElementById("prwss_abstract").value;
		var prwss_detail = document.getElementById("prwss_detail").value;

		//alert(1);
		var prwssip_abstract = document.getElementById("prwssip_abstract").value;
		//alert(prwssip_abstract);

		var prwssip_detail = document.getElementById("prwssip_detail").value;
		//alert(prwssip_detail);

		var prgm_mgt = document.getElementById("program_management").value;
		var bnfcry_shr = document.getElementById("beneficiary_share").value;
		var mntr_dtl = document.getElementById("progress_monitoring_dtl").value;
		var rslt_indicator = document.getElementById("rslt_indicator").value;
		var infra_develop = document.getElementById("infra_structure_develop").value;
		var data_monitoring = document.getElementById("data_monitoring").value;
		var phy_fin_monitoring = document.getElementById("phy_fin_monitoring").value;
		var schType = document.getElementById("schemeType").value;
		//var sch_Type = document.getElementById("scheme_Type").value;
		//alert("scheme type==="+schType);

		//alert(rptDisp+":"+swap_nonswap_abstract+":"+swap_nonswap_detail+":"+prwss_abstract+":"+prwss_detail);
		//-----------------------------Start 16-09-2011---------------------------------------------------------------------------------------
		//-----Start Select 1---------------------------------------------------------
		if (swap_nonswap_abstract == "HSA2008_2"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_18_districtwise";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_18";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_18_districtwise"//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_18_districtwise"; //need to change
		}
		if (swap_nonswap_abstract == "HSA2008_1"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_1";//alert("In: "+val);}
			if (rptDisp == "BW")
				val = "RPT001_1_1_blk";
			if (rptDisp == "CNW")
				val = "RPT001_1_2_consti";
			if (rptDisp == "DW")
				val = "RPT001_1_3_div";
		}
		if (swap_nonswap_abstract == "CSA2008_2"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "status_MH";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "status_MH";//need to change
			if (rptDisp == "CNW")
				val = "status_MH"//need to change
			if (rptDisp == "DW")
				val = "status_MH"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_3"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "status_OH";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "status_OH";//need to change
			if (rptDisp == "CNW")
				val = "status_OH"//need to change
			if (rptDisp == "DW")
				val = "status_OH"; //need to change
		}
		if (swap_nonswap_abstract == "PMMRPT001_5_AA1"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_abstr_np_swap";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_abstr_np_swap";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_abstr_np_swap";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_abstr_np_swap";//need to change
		}
		if (swap_nonswap_abstract == "PMMRPT001_5_AA2"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_abstr_np_nonswap";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_abstr_np_nonswap";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_abstr_np_nonswap";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_abstr_np_nonswap";//need to change
		}
		if (swap_nonswap_abstract == "PMMRPT001_5_AA3"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_abstr70";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_abstr70";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_abstr70";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_abstr70";//need to change
		}
		if (swap_nonswap_abstract == "CSA2008_5"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_25";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_25";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_25";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_25"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_A12"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_A12_abs";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_A12_abs";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A12_abs";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A12_abs"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_A13"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_A12_13";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_A12_13";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A12_13";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A12_13"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_A14"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_A14";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_A14";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A14";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A14"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_A15"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_A15";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_A15";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A15";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A15"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_A16"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_A16";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_A16";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A16";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A16"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_6"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_29";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_29";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_29"//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_29"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_7"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_30";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_30";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_30"//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_30"; //need to change
		}
		if (swap_nonswap_abstract == "CSA2008_1"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_21";
			if (rptDisp == "BW")
				val = "PMMRPT001_21";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_21_const";
			if (rptDisp == "DW")
				val = "PMMRPT001_21";
		}
		if (swap_nonswap_abstract == "CSA2008_4"
				&& document.getElementById("selectReport1").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_21_all";
			if (rptDisp == "BW")
				val = "PMMRPT001_21_all";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_21_all_const";
			if (rptDisp == "DW")
				val = "PMMRPT001_21_all";
		}

		//-----End Select 1---------------------------------------------------------
		//-----Start Select 2---------------------------------------------------------	
		if (swap_nonswap_detail == "HSD2008_3"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24";
			if (rptDisp == "BW")
				val = "PMMRPT001_24";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_24";//need to change
		}
		if (swap_nonswap_detail == "HSD2008_1"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_4";
			if (rptDisp == "BW")
				val = "RPT001_4";//need to change
			if (rptDisp == "CNW")
				val = "RPT001_4_consti";
			if (rptDisp == "DW")
				val = "RPT001_4_div";
		}
		if (swap_nonswap_detail == "HSD2008_2"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_5";
			if (rptDisp == "BW")
				val = "RPT001_5";//need to change
			if (rptDisp == "CNW")
				val = "RPT001_5_consti";
			if (rptDisp == "DW")
				val = "RPT001_5_div";
		}

		if (swap_nonswap_detail == "CSD2008_1"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_20";
			if (rptDisp == "BW")
				val = "PMMRPT001_20";//need to change		
			if (rptDisp == "CNW")
				val = "PMMRPT001_20";
			if (rptDisp == "DW")
				val = "PMMRPT001_20";
		}

		if (swap_nonswap_detail == "PMMRPT001_5_DA1"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_dtl_np_swap";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_dtl_np_swap";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_dtl_np_swap";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_dtl_np_swap";//need to change
		}
		if (swap_nonswap_detail == "PMMRPT001_5_DA2"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_dtl_np_nonswap";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_dtl_np_nonswap";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_dtl_np_nonswap";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_dtl_np_nonswap";//need to change
		}
		if (swap_nonswap_detail == "PMMRPT001_5_DA3"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_detail";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_detail";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_detail";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_detail";//need to change
		}
		if (swap_nonswap_detail == "CSD2008_2"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_27";
			if (rptDisp == "BW")
				val = "PMMRPT001_27";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_27";
			if (rptDisp == "DW")
				val = "PMMRPT001_27";
		}
		if (swap_nonswap_detail == "CSA2008_8"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_31";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_31";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_31"//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_31"; //need to change
		}
		if (swap_nonswap_detail == "WTC2014_4"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24_details";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_24_blocks";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24_consi"; //need to change
			if (rptDisp == "DW")
				val = "RPT001_4_water_teriff_cell_div"; //need to change
		}
		if (swap_nonswap_detail == "WTC2014_41"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24_41";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_24_41";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24_41"; //need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_24_41"; //need to change
		}
		if (swap_nonswap_detail == "WTC2014_42"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24_42";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_24_42";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24_42"; //need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_24_42"; //need to change
		}
		if (swap_nonswap_detail == "WTC2014_43"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24_43";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_24_43";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24_43"; //need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_24_43"; //need to change
		}
		if (swap_nonswap_detail == "WTC2014_44"
				&& document.getElementById("selectReport2").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_24_44";//alert("In2: "+val);}
			if (rptDisp == "BW")
				val = "PMMRPT001_24_44";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_24_44"; //need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_24_44"; //need to change
		}

		//-----End Select 2---------------------------------------------------------

		//-----Start Select 3---------------------------------------------------------	
		if (prwss_abstract == "HSPWA_2"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_19";
			if (rptDisp == "BW")
				val = "PMMRPT001_19";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_19";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_19";//need to change
		}

		if (prwssip_abstract == "IBRD1"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD1";
			if (rptDisp == "BW")
				val = "IBRD1";//need to change
			if (rptDisp == "CNW")
				val = "IBRD1";//need to change
			if (rptDisp == "DW")
				val = "IBRD1";//need to change
		}
		if (prwssip_abstract == "IBRD2"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD2";
			if (rptDisp == "BW")
				val = "IBRD2";//need to change
			if (rptDisp == "CNW")
				val = "IBRD2";//need to change
			if (rptDisp == "DW")
				val = "IBRD2";//need to change
		}
		if (prwssip_abstract == "IBRD3"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD3";
			if (rptDisp == "BW")
				val = "IBRD3";//need to change
			if (rptDisp == "CNW")
				val = "IBRD3";//need to change
			if (rptDisp == "DW")
				val = "IBRD3";//need to change
		}
		if (prwssip_abstract == "IBRD4"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD4";
			if (rptDisp == "BW")
				val = "IBRD4";//need to change
			if (rptDisp == "CNW")
				val = "IBRD4";//need to change
			if (rptDisp == "DW")
				val = "IBRD4";//need to change
		}
		if (prwssip_abstract == "IBRD5"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD5";
			if (rptDisp == "BW")
				val = "IBRD5";//need to change
			if (rptDisp == "CNW")
				val = "IBRD5";//need to change
			if (rptDisp == "DW")
				val = "IBRD5";//need to change
		}
		if (prwssip_abstract == "IBRD6"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD6";
			if (rptDisp == "BW")
				val = "IBRD6";//need to change
			if (rptDisp == "CNW")
				val = "IBRD6";//need to change
			if (rptDisp == "DW")
				val = "IBRD6";//need to change
		}
		if (prwssip_abstract == "IBRD7"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD7";
			if (rptDisp == "BW")
				val = "IBRD7";//need to change
			if (rptDisp == "CNW")
				val = "IBRD7";//need to change
			if (rptDisp == "DW")
				val = "IBRD7";//need to change
		}
		if (prwssip_abstract == "IBRD8"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD8";
			if (rptDisp == "BW")
				val = "IBRD8";//need to change
			if (rptDisp == "CNW")
				val = "IBRD8";//need to change
			if (rptDisp == "DW")
				val = "IBRD8";//need to change
		}
		if (prwssip_abstract == "IBRD9"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD9";
			if (rptDisp == "BW")
				val = "IBRD9";//need to change
			if (rptDisp == "CNW")
				val = "IBRD9";//need to change
			if (rptDisp == "DW")
				val = "IBRD9";//need to change
		}
		if (prwssip_abstract == "IBRD10"
				&& document.getElementById("selectReport12").checked) {
			if (rptDisp == "ALL")
				val = "IBRD10";
			if (rptDisp == "BW")
				val = "IBRD10";//need to change
			if (rptDisp == "CNW")
				val = "IBRD10";//need to change
			if (rptDisp == "DW")
				val = "IBRD10";//need to change
		}

		if (prwssip_abstract == "IBRD111"
				&& document.getElementById("selectReport12").checked) {
			//alert(2);
			if (rptDisp == "ALL")
				val = "Abs_MH";
			if (rptDisp == "BW")
				val = "Abs_MH";//need to change		
			if (rptDisp == "CNW")
				val = "Abs_MH";
			if (rptDisp == "DW")
				val = "Abs_MH";
		}

		if (prwssip_detail == "IBRD11"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD11";
			if (rptDisp == "BW")
				val = "IBRD11";//need to change
			if (rptDisp == "CNW")
				val = "IBRD11";//need to change
			if (rptDisp == "DW")
				val = "IBRD11";//need to change
		}
		if (prwssip_detail == "IBRD12"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD12";
			if (rptDisp == "BW")
				val = "IBRD12";//need to change
			if (rptDisp == "CNW")
				val = "IBRD12";//need to change
			if (rptDisp == "DW")
				val = "IBRD12";//need to change
		}
		if (prwssip_detail == "IBRD13"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD13";
			if (rptDisp == "BW")
				val = "IBRD13";//need to change
			if (rptDisp == "CNW")
				val = "IBRD13";//need to change
			if (rptDisp == "DW")
				val = "IBRD13";//need to change
		}
		if (prwssip_detail == "IBRD14"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD14";
			if (rptDisp == "BW")
				val = "IBRD14";//need to change
			if (rptDisp == "CNW")
				val = "IBRD14";//need to change
			if (rptDisp == "DW")
				val = "IBRD14";//need to change
		}
		if (prwssip_detail == "IBRD15"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD15";
			if (rptDisp == "BW")
				val = "IBRD15";//need to change
			if (rptDisp == "CNW")
				val = "IBRD15";//need to change
			if (rptDisp == "DW")
				val = "IBRD15";//need to change
		}
		if (prwssip_detail == "IBRD16"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD16";
			if (rptDisp == "BW")
				val = "IBRD16";//need to change
			if (rptDisp == "CNW")
				val = "IBRD16";//need to change
			if (rptDisp == "DW")
				val = "IBRD16";//need to change
		}
		if (prwssip_detail == "IBRD17"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD17";
			if (rptDisp == "BW")
				val = "IBRD17";//need to change
			if (rptDisp == "CNW")
				val = "IBRD17";//need to change
			if (rptDisp == "DW")
				val = "IBRD17";//need to change
		}
		if (prwssip_detail == "IBRD18"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD18";
			if (rptDisp == "BW")
				val = "IBRD18";//need to change
			if (rptDisp == "CNW")
				val = "IBRD18";//need to change
			if (rptDisp == "DW")
				val = "IBRD18";//need to change
		}
		if (prwssip_detail == "IBRD19"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD19";
			if (rptDisp == "BW")
				val = "IBRD19";//need to change
			if (rptDisp == "CNW")
				val = "IBRD19";//need to change
			if (rptDisp == "DW")
				val = "IBRD19";//need to change
		}
		if (prwssip_detail == "IBRD20"
				&& document.getElementById("selectReport13").checked) {
			if (rptDisp == "ALL")
				val = "IBRD20";
			if (rptDisp == "BW")
				val = "IBRD20";//need to change
			if (rptDisp == "CNW")
				val = "IBRD20";//need to change
			if (rptDisp == "DW")
				val = "IBRD20";//need to change
		}
		if (prwssip_detail == "IBRD222"
				&& document.getElementById("selectReport13").checked) {
			//alert(2);
			if (rptDisp == "ALL")
				val = "Dtl_MH";
			if (rptDisp == "BW")
				val = "Dtl_MH";//need to change		
			if (rptDisp == "CNW")
				val = "Dtl_MH";
			if (rptDisp == "DW")
				val = "Dtl_MH";
		}

		if (prwss_abstract == "HSPWA_1"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_2";
			if (rptDisp == "BW")
				val = "RPT001_2_blk";
			if (rptDisp == "CNW")
				val = "RPT001_2_consti";
			if (rptDisp == "DW")
				val = "RPT001_2_div";
		}
		if (prwss_abstract == "CSPWA_1"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_3";
			if (rptDisp == "BW")
				val = "RPT001_3_blk";
			if (rptDisp == "CNW")
				val = "RPT001_3_consti";
			if (rptDisp == "DW")
				val = "RPT001_3_div";
		}
		if (prwss_abstract == "PMMRPT001_5_AP1"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_abstr_p_ida";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_abstr_p_ida";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_abstr_p_ida";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_abstr_p_ida";//need to change
		}
		if (prwss_abstract == "PMMRPT001_5_AP2"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_abstr_p_nonida";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_abstr_p_nonida";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_abstr_p_nonida";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_abstr_p_nonida";//need to change
		}
		if (prwss_abstract == "CSPWA_2"
				&& document.getElementById("selectReport3").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_26";
			if (rptDisp == "BW")
				val = "PMMRPT001_26";
			if (rptDisp == "CNW")
				val = "PMMRPT001_26";
			if (rptDisp == "DW")
				val = "PMMRPT001_26";
		}
		//-----End Select 3---------------------------------------------------------

		//-----Start Select 4---------------------------------------------------------	
		if (prwss_detail == "HSPWD_1"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "rpt_3141_list";
			if (rptDisp == "BW")
				val = "rpt_3141_list";//need to change
			if (rptDisp == "CNW")
				val = "rpt_3141_list";//need to change
			if (rptDisp == "DW")
				val = "rpt_3141_list";//need to change
		}
		if (prwss_detail == "HSPWD_2"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "RPT001_6";
			if (rptDisp == "BW")
				val = "RPT001_6";//need to change
			if (rptDisp == "CNW")
				val = "RPT001_6_consti";
			if (rptDisp == "DW")
				val = "RPT001_6_div";
		}
		if (prwss_detail == "CSPWD_1"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "commissioning_IDA_RPT";
			if (rptDisp == "BW")
				val = "commissioning_IDA_RPT";//need to change
			if (rptDisp == "CNW")
				val = "commissioning_IDA_RPT";//need to change
			if (rptDisp == "DW")
				val = "commissioning_IDA_RPT";//need to change
		}
		if (prwss_detail == "PMMRPT001_5_DP1"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_dtl_p_ida";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_dtl_p_ida";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_dtl_p_ida";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_dtl_p_ida";//need to change
		}
		if (prwss_detail == "PMMRPT001_5_DP1_A"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_dtl_sew";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_dtl_sew";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_dtl_sew";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_dtl_sew";//need to change
		}
		if (prwss_detail == "PMMRPT001_5_DP2"
				&& document.getElementById("selectReport4").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5_dtl_p_nonida";
			if (rptDisp == "BW")
				val = "PMMRPT001_5_dtl_p_nonida";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5_dtl_p_nonida";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5_dtl_p_nonida";//need to change
		}
		//-----End Select 4---------------------------------------------------------
		//-----------------------------END 16-09-2011---------------------------------------------------------------------------------------

		if (prgm_mgt == "PMMRPT001_10"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_10";
			if (rptDisp == "BW")
				val = "PMMRPT001_10";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_10";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_10";//need to change
		}
		if (prgm_mgt == "PMMRPT001_6"
				&& document.getElementById("selectReport5").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_6";
			if (rptDisp == "BW")
				val = "PMMRPT001_6";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_6";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_6";//need to change
		}
		if (prgm_mgt == "PMMRPT001_7"
				&& document.getElementById("selectReport5").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_7";
			if (rptDisp == "BW")
				val = "PMMRPT001_7";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_7";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_7";//need to change
		}
		if (prgm_mgt == "PMMRPT001_8"
				&& document.getElementById("selectReport5").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_8";
			if (rptDisp == "BW")
				val = "PMMRPT001_8";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_8";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_8";//need to change
		}
		if (prgm_mgt == "PMMRPT001_9"
				&& document.getElementById("selectReport5").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_9";
			if (rptDisp == "BW")
				val = "PMMRPT001_9";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_9";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_9";//need to change
		}
		if (rslt_indicator == "PMMRPT001_11"
				&& document.getElementById("selectReport8").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_11";
			if (rptDisp == "BW")
				val = "PMMRPT001_11";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_11";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_11";//need to change
		}
		if (rslt_indicator == "PMMRPT001_12"
				&& document.getElementById("selectReport8").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_12";
			if (rptDisp == "BW")
				val = "PMMRPT001_12";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_12";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_12";//need to change
		}
		if (rslt_indicator == "PMMRPT001_13"
				&& document.getElementById("selectReport8").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_13";
			if (rptDisp == "BW")
				val = "PMMRPT001_13";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_13";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_13";//need to change
		}
		if (infra_develop == "PMMRPT001_14"
				&& document.getElementById("selectReport9").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_14";
			if (rptDisp == "BW")
				val = "PMMRPT001_14";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_14";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_14";//need to change
		}
		if (infra_develop == "PMMRPT001_15"
				&& document.getElementById("selectReport9").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_15";
			if (rptDisp == "BW")
				val = "PMMRPT001_15";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_15";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_15";//need to change
		}
		if (infra_develop == "PMMRPT001_16"
				&& document.getElementById("selectReport9").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_16";
			if (rptDisp == "BW")
				val = "PMMRPT001_16";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_16";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_16";//need to change
		}
		if (mntr_dtl == "PMMRPT001_17"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_17";
			if (rptDisp == "BW")
				val = "PMMRPT001_17";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_17";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_17";//need to change
		}

		if (bnfcry_shr == "PMMRPT001_1"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_1";
			if (rptDisp == "BW")
				val = "PMMRPT001_1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_1";//need to change
		}
		if (bnfcry_shr == "PMMRPT001_2"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_2";
			if (rptDisp == "BW")
				val = "PMMRPT001_2";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_2";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_2";//need to change
		}
		if (bnfcry_shr == "PMMRPT001_3"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_3";
			if (rptDisp == "BW")
				val = "PMMRPT001_3";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_3";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_3";//need to change
		}
		if (mntr_dtl == "PMMRPT001_5"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_5";
			if (rptDisp == "BW")
				val = "PMMRPT001_5";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_5";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_5";//need to change
		}
		if (mntr_dtl == "PMMRPT001_28P1"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_28P1";
			if (rptDisp == "BW")
				val = "PMMRPT001_28P1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_28P1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_28P1";//need to change
		}
		if (mntr_dtl == "PMMRPT001_28P2"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_28P2";
			if (rptDisp == "BW")
				val = "PMMRPT001_28P2";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_28P2";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_28P2";//need to change
		}
		if (mntr_dtl == "PMMRPT001_28P3"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_28P3";
			if (rptDisp == "BW")
				val = "PMMRPT001_28P3";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_28P3";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_28P3";//need to change
		}
		if (bnfcry_shr == "PMMRPT001_1_dtl"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_1_dtl";
			if (rptDisp == "BW")
				val = "PMMRPT001_1_dtl";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_1_dtl";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_1_dtl";//need to change
		}
		if (bnfcry_shr == "PMMRPT001_2_dtl"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_2_dtl";
			if (rptDisp == "BW")
				val = "PMMRPT001_2_dtl";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_2_dtl";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_2_dtl";//need to change
		}
		if (bnfcry_shr == "PMMRPT001_3_dtl"
				&& document.getElementById("selectReport6").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_3_dtl";
			if (rptDisp == "BW")
				val = "PMMRPT001_3_dtl";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_3_dtl";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_3_dtl";//need to change
		}
		if (data_monitoring == "PMMRPT001_32"
				&& document.getElementById("selectReport10").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_32";
			if (rptDisp == "BW")
				val = "PMMRPT001_32";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_32";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_32";//need to change
		}
		if (phy_fin_monitoring == "PMMRPT001_34"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_34";
			if (rptDisp == "BW")
				val = "PMMRPT001_34";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_34";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_34";//need to change
		}
		if (phy_fin_monitoring == "PMMRPT001_34_dtl"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_34_dtl";
			if (rptDisp == "BW")
				val = "PMMRPT001_34_dtl";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_34_dtl";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_34_dtl";//need to change
		}
		if (data_monitoring == "PMMRPT001_35"
				&& document.getElementById("selectReport10").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_35";
			if (rptDisp == "BW")
				val = "PMMRPT001_35";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_35";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_35";//need to change
		}
		if (mntr_dtl == "PMMRPT001_33P1"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_33P1";
			if (rptDisp == "BW")
				val = "PMMRPT001_33P1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_33P1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_33P1";//need to change
		}
		if (mntr_dtl == "PMMRPT001_33P2"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_33P2";
			if (rptDisp == "BW")
				val = "PMMRPT001_33P2";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_33P2";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_33P2";//need to change
		}
		if (mntr_dtl == "PMMRPT001_33P3"
				&& document.getElementById("selectReport7").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_33P3";
			if (rptDisp == "BW")
				val = "PMMRPT001_33P3";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_33P3";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_33P3";//need to change
		}
		if (data_monitoring == "PMMRPT001_36"
				&& document.getElementById("selectReport10").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_36";
			if (rptDisp == "BW")
				val = "PMMRPT001_36";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_36";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_36";//need to change
		}
		if (prgm_mgt == "PMMRPT001_001"
				&& document.getElementById("selectReport5").checked) {

			if (schType == "ws") {
				val = "PMMRPT001_001";
			}//need to change
			if (schType == "sw")
				val = "PMMRPT001_A2.4_sew";//need to change
			if (schType == "imp")
				val = "PMMRPT001_A2.4_imp";//need to change
			if (schType == "wm")
				val = "PMMRPT001_A2.4_wm";//need to change

			if (schType == "ws" && rptDisp == "ALL") {
				val = "PMMRPT001_A2.4_dis";
			}
			if (rptDisp == "All")
				val = "PMMRPT001_A2.4_dis";
		}
		if (prgm_mgt == "PMMRPT001_A2.4_dis_vill"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_dis_vill";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_dis_vill";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_dis_vill";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_001_vill";//need to change
		}

		if (prgm_mgt == "PMMRPT001_A2.4_A1"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_A1";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_A1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_A1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_A1";//need to change
			//	alert("Ranjay Test- " + val);
		}

		if (prgm_mgt == "PMMRPT001_A2.4_2A"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_2A";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_2A";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_2A";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_2A";//need to change
		}
		if (prgm_mgt == "PMMRPT001_A2.4_2B"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.4_2B";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.4_2B";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.4_2B";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.4_2B";//need to change
		}

		if (prgm_mgt == "PMMRPT001_A2.5_A1"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_A1";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_A1";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_A1";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_A1";//need to change
		}

		if (prgm_mgt == "PMMRPT001_A2.5_2A"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_2A";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_2A";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_2A";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_2A";//need to change
		}
		if (prgm_mgt == "PMMRPT001_A2.5_2B"
				&& document.getElementById("selectReport5").checked) {

			if (rptDisp == "ALL")
				val = "PMMRPT001_A2.5_2B";
			if (rptDisp == "BW")
				val = "PMMRPT001_A2.5_2B";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_A2.5_2B";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_A2.5_2B";//need to change
		}
		if (prgm_mgt == "PMMRPT001_001_details"
				&& document.getElementById("selectReport5").checked) {
			if (schType == "ws")
				val = "PMMRPT001_001_details";//need to change
			if (schType == "sw")
				val = "PMMRPT001_A2.5_sew";//need to change
			if (schType == "imp")
				val = "PMMRPT001_A2.5_imp";//need to change
			if (schType == "wm")
				val = "PMMRPT001_A2.5_wm";//need to change

			if (schType == "ws" && rptDisp == "ALL")
				val = "PMMRPT001_A2.5_dis";

		}
		if (phy_fin_monitoring == "PMMRPT001_G3"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_G3";
			if (rptDisp == "BW")
				val = "PMMRPT001_G3";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_G3";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_G3";//need to change
		}
		if (data_monitoring == "PMMRPT001_F4"
				&& document.getElementById("selectReport10").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_F4";
			if (rptDisp == "BW")
				val = "PMMRPT001_F4";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_F4";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_F4";//need to change
		}

		if (data_monitoring == "PMMRPT001_F5"
				&& document.getElementById("selectReport10").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_F5";
			if (rptDisp == "BW")
				val = "PMMRPT001_F5";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_F5";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_F5";//need to change
		}

		if (phy_fin_monitoring == "PMMRPT001_G4_abs"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_G4_abs";
			if (rptDisp == "BW")
				val = "PMMRPT001_G4_abs";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_G4_abs";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_G4_abs";//need to change
		}
		if (phy_fin_monitoring == "PMMRPT001_G5_det"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_G5_det";
			if (rptDisp == "BW")
				val = "PMMRPT001_G5_det";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_G5_det";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_G5_det";//need to change
		}
		if (phy_fin_monitoring == "PMMRPT001_QT"
				&& document.getElementById("selectReport11").checked) {
			if (rptDisp == "ALL")
				val = "PMMRPT001_QT";
			if (rptDisp == "BW")
				val = "PMMRPT001_QT";//need to change
			if (rptDisp == "CNW")
				val = "PMMRPT001_QT";//need to change
			if (rptDisp == "DW")
				val = "PMMRPT001_QT";//need to change
		}
		//	alert("val: "+val);
		//	if(val=="")	alert("Please select atleast one report");
		return val;
	}
	-->
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
</head>
<body onload="disable_enable('aa');">

	<html:form action="estimatesAwardContractsReportAction"
		styleId="estimatesAwardContractsReportForm">
		<fieldset>
			<legend>Selection Criteria</legend>
			<center>
				<table>
				<%-- 	<%@include file="../../JSPF/pmmReportConversation.jspf"%> --%>
					<tr>
						<td>Please Select Report Format</td>
						<td></td>
						<td><select id="ABCD" style="width: 255px" styleClass="ci5"
							onChange="
											document.getElementById('selectReport1').value = habitationDataReport();
											document.getElementById('selectReport2').value = habitationDataReport();
											document.getElementById('selectReport3').value = habitationDataReport();
											document.getElementById('selectReport4').value = habitationDataReport();	
											document.getElementById('selectReport5').value = habitationDataReport();										
										">
								<option value="">Select Format</option>
								<option value="DW">Division-Wise</option>
								<option value="ALL" selected>District-Wise</option>
								<option value="BW">Block-Wise</option>
								<option value="CNW">Constituency-Wise</option>
						</select></td>
					</tr>
					<tr>
						<td>Scheme Type</td>
						<td></td>
						<td><select id="schemeType" style="width: 255px"
							styleClass="ci5"
							onChange="
											document.getElementById('selectReport5').value = habitationDataReport();
											">
								<option value="">Select</option>
								<option value="ws">Water Supply</option>
								<option value="sw">Sewerage</option>
								<option value="imp">Performance Improvement</option>
								<!-- <optgroup label="Water Meter As" style="background-color:0000CC">
										 -->
								<option value="wm">Water Meter</option>
								<!-- </optgroup> -->



						</select></td>
					</tr>


					<!--<tr>
								<td nowrap>Report for Month   </td>
								<td><html:select property="monthId">
										<option value="">Select</option>
										<option value="January">JAN</option>
										<option value="Febuary">FEB</option>
										<option value="March">MAR</option>
										<option value="April">APR</option>
										<option value="May">MAY</option>
										<option value="June">JUN</option>
										<option value="July">JULY</option>
										<option value="August">AUG</option>
										<option value="September">SEP</option>
										<option value="October">OCT</option>
										<option value="November">NOV</option>
										<option value="December">DEC</option>
										</html:select></td>
								<td nowrap>Financial Year</td>
										<td><html:select property ="finYearId">
										<option value="">Select</option>
										<option value="2008">2008</option>
										<option value="2009">2009</option>
										<option value="2010">2010</option>
										<option value="2011">2011</option>
										<option value="2012">2012</option>
										<option value="2013">2013</option>
										<option value="2014">2014</option>

										<option value="2015">2015</option>
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										
										</html:select></td>
							</tr>
                        -->
				</table>
			</center>
		</fieldset>
		<fieldset>
			<legend>Reports-SWAp / Non-SWAp Program</legend>
			<center>
				<table>
					<tr>
						<td><html:radio property="selectReport"
								value="swap_nonswap_abstract"
								onclick="disable_enable('swap_nonswap_abstract');"
								styleId="selectReport1"> Abstracts</html:radio></td>
						<td><select id="swap_nonswap_abstract" class="cs3"
							onchange="document.getElementById('selectReport1').value = habitationDataReport();disable_enable('swap_nonswap_abstract');">
								<option value="">Select Report</option>
								<option value="HSA2008_2">A-1 Freezed Habitation Status
									(as on 01-04-2008)</option>
								<option value="HSA2008_1">A-2 Habitation Status (as on
									Date)</option>
								<option value="CSA2008_1">A-3 Schemes/ Habitations
									Commissioned (during a period)</option>
								<option value="CSA2008_2">A-4 Main Habitations (MH)
									Commissioned (as on date)</option>
								<option value="CSA2008_3">A-5 Other Habitations(OH)
									Commissioned (as on date)</option>
								<option value="PMMRPT001_5_AA1">A-6.1 Private
									Connections- SWAp</option>
								<option value="PMMRPT001_5_AA2">A-6.2 Private
									Connections- Non-SWAp</option>
								<option value="PMMRPT001_5_AA3">A-6.3 Private
									Connections- 70% or more than 70%</option>
								<option value="CSA2008_4">A-7 Total Schemes/
									Habitations Commissioned (as on date)</option>
								<option value="CSA2008_5">A-8 Status of Coverage of
									Main Habitations (as on date)</option>
								<option value="CSA2008_6">A-9 Abstract of Service
									Level, Operating Arrangement & RO Plants(MH)</option>
								<option value="CSA2008_7">A-10 Abstract of Service
									Level, Operating Arrangement & RO Plants(OH)</option>
								<option value="CSA2008_A11">A-11 Abstract Breif status
									of MIS Reports</option>
								<option value="CSA2008_A12">A-12 Water Tariff Cell:
									Abstract of Habitations and Private Connections (District Wise)</option>
								<option value="CSA2008_A13">A1.1 Habitation Status (as
									on 01-04-2015)</option>
								<option value="CSA2008_A14">A-13 Population Report</option>
								<option value="CSA2008_A15">A-14 Households Report</option>
								<option value="CSA2008_A16">A-15 PDO Report</option>

						</select></td>
						<td><html:radio property="selectReport"
								value="swap_nonswap_detail"
								onclick="disable_enable('swap_nonswap_detail');"
								styleId="selectReport2"> Details</html:radio></td>
						<td><select id="swap_nonswap_detail" class="cs3"
							onchange="document.getElementById('selectReport2').value = habitationDataReport();disable_enable('swap_nonswap_detail');"
							disabled="true">
								<option value="">Select Report</option>
								<option value="HSD2008_3">D-1 List of Freezed
									Habitations Survey (as on 01.04.2008)</option>
								<option value="HSD2008_1">D-2 Name/Status of Main
									Habitations(MH) (as on date)</option>
								<option value="HSD2008_2">D-3 Name/Status of Other
									Habitations(OH) (as on date)</option>
								<option value="CSD2008_1">D-4 List of
									Schemes/Habitations Commissioned (during a period)</option>
								<option value="PMMRPT001_5_DA1">D-5.1 Private
									Connections- SWAp</option>
								<option value="PMMRPT001_5_DA2">D-5.2 Private
									Connections- Non-SWAp</option>
								<option value="PMMRPT001_5_DA3">D-5.3 Private
									Connections- 70% or more than 70%</option>
								<option value="CSD2008_2">D-6 List of
									Schemes/Habitations yet to be Commissioned (as on date)</option>
								<option value="CSA2008_8">D-7 Status of Main Habitation
									where Performance Improvement of Existing Scheme has been taken
									up</option>
								<option value="WTC2014_4">D.8 WTC report - Scheme wise
									Detail of Habitation and Private connections</option>
								<option value="WTC2014_41">D1.2 List of Habitation
									Status (as on 01-04-2015)</option>
								<option value="WTC2014_42">D9.1 Population
									Report(District Wise)</option>
								<option value="WTC2014_43">D9.2 Household
									Report(District Wise)</option>
								<option value="WTC2014_44">D-10 PDO Report(District
									Wise)</option>
						</select></td>
					</tr>
				</table>
			</center>
		</fieldset>
		<fieldset >
			<legend>Reports-PRWSS Project</legend>
			<center>
				<table>
					<tr>
						<td><html:radio property="selectReport"
								value="prwss_abstract"
								onclick="disable_enable('prwss_abstract');"
								styleId="selectReport3"> Abstracts</html:radio></td>
						<td><select id="prwss_abstract" class="cs3"
							onchange="document.getElementById('selectReport3').value = habitationDataReport();disable_enable('prwss_abstract');">
								<option value="">Select Report</option>
								 <option value="HSPWA_2">A-1 Action Plan for Coverage
									(as on 01-04-2008)</option>
								<option value="HSPWA_1">A-2 Habitation Status (as on
									date)</option>
								<option value="CSPWA_1">A-3 Habitations Commissioned
									(IDA & Non-IDA) (during a period)</option>
								<option value="PMMRPT001_5_AP1">A-4.1 Private
									Connections- IDA</option>
								<option value="PMMRPT001_5_AP2">A-4.2 Private
									Connections- Non-IDA</option>
								<option value="CSPWA_2">A-5 Status of Coverage of Main
									Habitations(IDA)</option> 

						</select></td>
						<td><html:radio property="selectReport" value="prwss_detail"
								onclick="disable_enable('prwss_detail');"
								styleId="selectReport4"> Details</html:radio></td>
						<td><select id="prwss_detail" class="cs3"
							onchange="document.getElementById('selectReport4').value = habitationDataReport();disable_enable('prwss_detail');"
							disabled="true">
								<option value="">Select Report</option>
								 <option value="HSPWD_1">D-1 List of Habitations(as on
									01.04.2008)</option>
								<option value="HSPWD_2">D-2 Name/Status of Habitations
									(during a period)</option>
								<option value="CSPWD_1">D-3 List of Habitations
									Commissioned (as on date)</option>
								<option value="PMMRPT001_5_DP1">D-4.1 Private
									Connections- IDA</option>
									<option value="PMMRPT001_5_DP1_A">D-4.1 A Household
									sewer Connections- IDA</option>
									<option value="PMMRPT001_5_DP2">D-4.2 Private
									Connections- Non-IDA</option> 
								

						</select></td>
					</tr>
				</table>
			</center>
		</fieldset>


		<fieldset>
			<legend>M & E Reports</legend>
			<center>
				<br>
				<table>
					<tr>
						<td><html:radio property="selectReport"
								value="program_management"
								onclick="disable_enable('program_management');"
								styleId="selectReport5">A. Program Management</html:radio></td>
						<td><select id="program_management" class="cs3"
							onchange="document.getElementById('selectReport5').value = habitationDataReport();disable_enable('program_management');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_10">A.1 Result Framework
									Matrix: Table-1 (Status of FC Habitation)</option>
								<option value="PMMRPT001_6">A.2 Table-2 (Habitations
									covered under SWAp and Non-SWAp)</option>
								<option value="PMMRPT001_7">A.2.1 Table-2a (Habitations
									covered under Non-IDA SWAp)</option>
								<option value="PMMRPT001_8">A.2.2 Table-2b (Habitations
									covered under IDA SWAp)</option>
								<option value="PMMRPT001_9">A.2.3 Table-2c (Habitations
									covered under Non-SWAp)</option>
								<option value="PMMRPT001_001">A.2.4 Abstract of Status
									of Estimates and Contracts of IBRD WS/ covered up to Dec 2015</option>

								<option value="PMMRPT001_001_details">A.2.5 Detail of
									Status of Estimates and Award of Contracts of IDA WS/
									Sew/Performance Imp. Schemes covered/to be covered up to Dec
									2014</option>

								<option value="PMMRPT001_A2.4_A1">A2.6 Abstract of
									Status of IBRD</option>

								<option value="PMMRPT001_A2.5_A1">A2.7 Detail of Status
									of IBRD</option>

						</select></td>
						<td><html:radio property="selectReport"
								value="beneficiary_share"
								onclick="disable_enable('beneficiary_share');"
								styleId="selectReport6">B. Beneficiary Share Collection</html:radio></td>
						<td><select id="beneficiary_share" class="cs3"
							onchange="document.getElementById('selectReport6').value = habitationDataReport();disable_enable('beneficiary_share');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_1">B.1 Ranging From 90 to 100%
									(Status of Estimates)</option>
								<option value="PMMRPT001_2">B.2 Ranging from 50 to 89%
									(No. of Schemes/Habitations)</option>
								<option value="PMMRPT001_3">B.3 Ranging from 1 to 49%
									(No. of Schemes/Habitations)</option>
								<option value="PMMRPT001_1_dtl">B.4(Detail) Ranging
									From 90 to 100% (Status of Estimates)</option>
								<option value="PMMRPT001_2_dtl">B.5(Detail) Ranging
									from 50 to 89% (No. of Schemes/Habitations)</option>
								<option value="PMMRPT001_3_dtl">B.6(Detail) Ranging
									from 1 to 49% (No. of Schemes/Habitations)</option>
						</select></td>


					</tr>
					<tr></tr>
					<tr>
						<td><html:radio property="selectReport"
								value="progress_monitoring_dtl"
								onclick="disable_enable('progress_monitoring_dtl');"
								styleId="selectReport7">C. Progress Monitoring (Details)</html:radio></td>
						<td><select id="progress_monitoring_dtl" class="cs3"
							onchange="document.getElementById('selectReport7').value = habitationDataReport();disable_enable('progress_monitoring_dtl');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_28P1">C.1 Pre Planning
									Activities</option>
								<option value="PMMRPT001_28P2">C.2 Planning &
									Implementation Activities</option>
								<option value="PMMRPT001_28P3">C.3 Physical & Financial
									Status</option>
								<option value="PMMRPT001_17">C.4 Operational
									Sustainability for schemes implemented under (Non-IDA & IDA)
									SWAp</option>
								<option value="PMMRPT001_5">C.5 Status of Private Water
									Connection for commissioned villages</option>
								<option value="PMMRPT001_33P1">C.6 Fact Sheet Part 1</option>
								<option value="PMMRPT001_33P2">C.7 Fact Sheet Part 2</option>
								<option value="PMMRPT001_33P3">C.8 Scheme Wise Physical
									Progress</option>

						</select></td>
						<td><html:radio property="selectReport"
								value="rslt_indicator"
								onclick="disable_enable('rslt_indicator');"
								styleId="selectReport8">D. Result Indicator(Community Development)</html:radio></td>
						<td><select id="rslt_indicator" class="cs3"
							onchange="document.getElementById('selectReport8').value = habitationDataReport();disable_enable('rslt_indicator');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_11">D.1 Table-3: For schemes
									implemented under Non-IDA & IDA SWAp</option>
								<option value="PMMRPT001_12">D.1.1 Table-3a: For
									schemes implemented under Non-IDA SWAp</option>
								<option value="PMMRPT001_13">D.1.2 Table-3b: For
									schemes implemented under IDA SWAp</option>
						</select></td>
					</tr>
					<tr></tr>
					<tr>
						<td><html:radio property="selectReport"
								value="infra_structure_develop"
								onclick="disable_enable('infra_structure_develop');"
								styleId="selectReport9">E.ResultIndicator(Infrastructure Development)</html:radio></td>
						<td><select id="infra_structure_develop" class="cs3"
							onchange="document.getElementById('selectReport9').value = habitationDataReport();disable_enable('infra_structure_develop');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_14">E.1 Table-4: For schemes
									implemented under Non-IDA & IDA SWAp</option>
								<option value="PMMRPT001_15">E.1.1 Table-4a: For
									schemes implemented under Non-IDA SWAp</option>
								<option value="PMMRPT001_16">E.1.2 Table-4b: For
									schemes implemented under IDA SWAp</option>
						</select></td>

						<td><html:radio property="selectReport"
								value="data_monitoring"
								onclick="disable_enable('data_monitoring');"
								styleId="selectReport10">F. Data Entry Monitoring Report</html:radio></td>
						<td><select id="data_monitoring" class="cs3"
							onchange="document.getElementById('selectReport10').value = habitationDataReport();disable_enable('data_monitoring');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_32">F.1 Data Entry Monitoring
									Report</option>
								<option value="PMMRPT001_35">F.2 List of
									complaints/suggestions received through WWW.pbdwss.gov.in</option>
								<option value="PMMRPT001_36">F.3 Exception Report</option>
								<option value="PMMRPT001_F4">F.4 Monitoring of User
									Logged In to the System</option>
								<option value="PMMRPT001_F5">F.5 Service Delivery
									Improvement data combination of MIS & SBM</option>
						</select></td>


					</tr>
					<tr></tr>
					<tr>
						<td><html:radio property="selectReport"
								value="phy_fin_monitoring"
								onclick="disable_enable('phy_fin_monitoring');"
								styleId="selectReport11">G. Physical/Financial Monitoring Report</html:radio></td>
						<td><select id="phy_fin_monitoring" class="cs3"
							onchange="document.getElementById('selectReport11').value = habitationDataReport();disable_enable('phy_fin_monitoring');"
							style="width: 250px">
								<option value="">Select Report</option>
								<option value="PMMRPT001_34">G.1 Information pertaining
									achievements of departments during the period (Abstract)</option>
								<option value="PMMRPT001_34_dtl">G.2 Information
									pertaining achievements of departments during the period
									(Detail)</option>
								<option value="PMMRPT001_G3">G.3 Financial Monitoring
									of Project Cost</option>
								<option value="PMMRPT001_G4_abs">G.4 Monthly Review of
									Progress of water connections of villages covered under IDA/NON
									IDA/Non SWAP(Abstract)</option>
								<option value="PMMRPT001_G5_det">G.5 Monthly Review of
									Progress of water connections of villages covered under IDA/NON
									IDA/Non SWAP(Detail)</option>
								<option value="PMMRPT001_QT">G.6 Questionaire Report</option>
						</select></td>
					</tr>

					<tr id="tr1" style="visibility: hidden;">
						<td>Select Program <html:radio property="selectProgram"
								title="Selection" value="S" styleId="selectProgram">Selection</html:radio>
							<html:radio property="selectProgram" title="All" value="A"
								styleId="selectProgram">All</html:radio>
						</td>
						<td colspan="2"><html:select property="programId"
								styleId="programId" style="width: 255px" styleClass="ci5">
								<html:options collection="programs" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
					</tr>
					<tr id="td1" style="visibility: hidden;">
						<td>Period</td>
						<td><html:radio property="selectPeriod" title="Selection"
								value="S" styleId="selectPeriod_ida_s">Selection</html:radio> <html:radio
								property="selectPeriod" title="All" value="All"
								styleId="selectPeriod_ida_s">All</html:radio></td>
						<td>From:&nbsp;&nbsp;&nbsp; <html:text property="IDAType"
								styleId="IDAType" styleClass="ci3"></html:text>
						</td>
						<%-- <td>From:&nbsp;&nbsp;&nbsp;
					<html:text property="IDA" styleId="IDA"	styleClass="ci3"></html:text> 
				</td>  --%>

					</tr>
					<tr id="td1" style="visibility: hidden;">
						<td>Period</td>
						<td><html:radio property="selectPeriod" title="Selection"
								value="S" styleId="selectPeriod_SM_s">Selection</html:radio> <html:radio
								property="selectPeriod" title="All" value="All"
								styleId="selectPeriod_SM_s">All</html:radio></td>
						<td>From:&nbsp;&nbsp;&nbsp; <html:text property="SMType"
								styleId="SMType" styleClass="ci3"></html:text>
						</td>

					</tr>
					<tr id="td1" style="visibility: hidden;">
						<td>Period</td>
						<td><html:radio property="selectPeriod" title="Selection"
								value="S" styleId="selectPeriod_IA3_s">Selection</html:radio> <html:radio
								property="selectPeriod" title="All" value="All"
								styleId="selectPeriod_IA3_s">All</html:radio></td>
						<td>From:&nbsp;&nbsp;&nbsp; <html:text property="IDA5"
								styleId="IDA5" styleClass="ci3"></html:text>
						</td>

					</tr>

					<tr id="td1" style="visibility: hidden;">
						<td>Period</td>
						<td><html:radio property="selectPeriod" title="Selection"
								value="S" styleId="selectPeriod_TSc_s">Selection</html:radio> <html:radio
								property="selectPeriod" title="All" value="All"
								styleId="selectPeriod_TSc_a">All</html:radio></td>
						<td><html:text property="yearType" styleId="yearType"
								styleClass="ci3"></html:text></td>
						<%-- <td>
					<html:text property="schemeTypeRRR" styleId="schemeTypeRRR"	styleClass="ci3"></html:text> 
				</td> --%>

					</tr>
					<tr id="td1" style="visibility: hidden;">
						<td>Period <html:radio property="selectPeriod"
								title="Selection" value="S" styleId="selectPeriod_s">Selection</html:radio>
							<html:radio property="selectPeriod" title="All" value="A"
								styleId="selectPeriod_a">All</html:radio>
						</td>
						<td>From:&nbsp;&nbsp;&nbsp; <html:text property="fromDate"
								styleId="fromDate" styleClass="ci3"></html:text> <!--<input class=ci4 type=button onclick="c1.innerpopup('fromDate','calendar_frame');" value="..." />-->
						</td>
						<td>To:&nbsp;&nbsp;&nbsp; <html:text property="toDate"
								styleId="toDate" styleClass="ci3"></html:text> <!--<input class=ci4 type=button onclick="c1.innerpopup('toDate','calendar_frame');" value="..." />-->
						</td>
					</tr>
				</table>
			</center>
		</fieldset>

		<fieldset>
			<legend>Reports-PRWSSIP Project</legend>
			<center>
				<table>
					<tr>
						<td><html:radio property="selectReport"
								value="prwssip_abstract"
								onclick="disable_enable('prwssip_abstract');"
								styleId="selectReport12"> Abstracts</html:radio></td>
						<td><select id="prwssip_abstract" class="cs3"
							onchange="document.getElementById('selectReport12').value = habitationDataReport();disable_enable('prwssip_abstract');">
								<option value="">Select Report</option>
								<option value="IBRD1">A-1 Baseline Service Level-lpcd</option>
								<option value="IBRD2">A-2 Baseline Service Level
									Connection</option>
								<option value="IBRD3">A-3 Hservice Level-network
									coverage</option>
								<option value="IBRD4">A-4 Service Delivery- Hours of
									Supply</option>
								<option value="IBRD5">A-5 Service
									Delivery-Institutional</option>
								<option value="IBRD6">A-6 Service Delivery- Cost
									Recovery</option>
								<option value="IBRD7">A-7 Service Delivery- Qualitative</option>
								<option value="IBRD8">A-8 Service Delivery- Management</option>
								<option value="IBRD9">A-9 Service Delivery- Energy
									Efficiency-Surface Water Supply Schemes</option>
								<option value="IBRD10">A-10 Service Delivery- Energy
									Efficiency-Ground Water(Bore well) Supply Schemes</option>
								<option value="IBRD111">A-11 Abstract of progress &
									delivery of scheme under PRWSSIP</option>

						</select></td>
						<td><html:radio property="selectReport"
								value="prwssip_detail"
								onclick="disable_enable('prwssip_detail');"
								styleId="selectReport13"> Details</html:radio></td>
						<td><select id="prwssip_detail" class="cs3"
							onchange="document.getElementById('selectReport13').value = habitationDataReport();disable_enable('prwssip_detail');"
							disabled="true">
								<option value="">Select Report</option>
								<option value="IBRD11">D-1 Baseline Service Level-lpcd</option>
								<option value="IBRD12">D-2 Baseline Service Level
									Connection</option>
								<option value="IBRD13">D-3 Hservice Level-network
									coverage</option>
								<option value="IBRD14">D-4 Service Delivery- Hours of
									Supply</option>
								<option value="IBRD15">D-5 Service
									Delivery-Institutional</option>
								<option value="IBRD16">D-6 Service Delivery- Cost
									Recovery</option>
								<option value="IBRD17">D-7 Service Delivery-
									Qualitative</option>
								<option value="IBRD18">D-8 Service Delivery- Management</option>
								<option value="IBRD19">D-9 Service Delivery- Energy
									Efficiency-Surface Water Supply Schemes</option>
								<option value="IBRD20">D-10 Service Delivery- Energy
									Efficiency-Ground Water(Bore well) Supply Schemes</option>
								<option value="IBRD222">D-11 Details of progress &
									delivery of scheme under PRWSSIP</option>
						</select></td>
					</tr>
				</table>
			</center>
		</fieldset>


		<div id="modalPeriod"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period1</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period">All</html:radio></td>
				</tr>
				<tr>
					<td>From:&nbsp;&nbsp;&nbsp; <input type="text"
						Id="fromDate_pop" Class="ci3">
					</td>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop"
						Class="ci3">
					</td>
				</tr>
			</table>
			<input type="button" value="OK" id="period_ok"
				onClick="de_file_period();hide_ctrl('modalPeriod',true);return false;">
			<input type="button" value="Cancel" id="period_cancel"
				onClick="hide_ctrl('modalPeriod',true);return false;">
		</div>
		<div id="modalPeriod2"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period3">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period3">All</html:radio></td>
				</tr>
				<tr>
					<td>From:&nbsp;&nbsp;&nbsp; <input type="text"
						Id="fromDate_pop3" Class="ci3">
					</td>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop3"
						Class="ci3">
					</td>
				</tr>
				<tr>
					<td>Status</td>
					<td><html:select property="status" styleId="status"
							styleClass="cs3">
							<html:option value="A">All</html:option>
							<html:option value="Open">Open</html:option>
							<html:option value="Close">Close</html:option>

						</html:select></td>
				</tr>
			</table>
			<input type="button" value="OK" id="period_ok2"
				onClick="de_file_period();hide_ctrl('modalPeriod2',true);return false;">
			<input type="button" value="Cancel" id="period_cancel2"
				onClick="hide_ctrl('modalPeriod2',true);return false;">
		</div>

		<div id="modalPeriodSwap111"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop111"
						Class="ci3">
					</td>
					<html:radio property="selectPeriod" title="Selection" value="S"
						styleId="selectPeriod_s_period111"></html:radio>

				</tr>
				<tr>
					<td>Please select PRWSSIP Component</td>
					<td><html:select property="village_category"
							styleId="village_category">
							<option value="">Select Component</option>
							<html:option value="ALL">All</html:option>
							<html:option value="NONSWAP">1A</html:option>
							<html:option value="SWAP-NON IDA">2A</html:option>
							<html:option value="SWAP-IDA">2B</html:option>
							<html:option value="OH">OTHERS</html:option>
						</html:select></td>
				</tr>
			</table>
			<input type="button" value="OK" id="period_ok11"
				onClick="de_file_periodSwapFRP();hide_ctrl('modalPeriodSwap111',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalPeriodSwap111',true);return false;">
		</div>


		<%--  		<div id="modalPeriodSwap222"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; 
			background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period222">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period222">All</html:radio></td>
				</tr>
				<tr>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" 
					Id="toDate_pop2" Class="ci3"></td>
				</tr>
				<tr>					
					<td>Please select Village Category</td>
					<td><html:select property="village_category" styleId="village_category"	styleClass="cs3">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">1A</html:option>
							<html:option value="SWAP-NON IDA">2A</html:option>
							<html:option value="SWAP-IDA">2B</html:option>
							<html:option value="OH">OH</html:option>
						</html:select></td>
				
				</tr>
		
			</table>
			<input type="button" value="OK" id="period_ok1"
				onClick="de_file_periodSwapFRP();hide_ctrl('modalPeriodSwap222',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalPeriodSwap222',true);return false;">
		</div>  --%>


		<div id="modalUpto"
			style="position: absolute; left: 325px; top: 375px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
				<tr>
					<td colspan="3">Please select the Report Period (upto)</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_upto">Selection</html:radio> <html:radio
							property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_upto">All</html:radio></td>
				</tr>
				<tr>
					<td nowrap>Month</td>
					<td><select id="month" style="width: 150px" styleClass="ci5">
							<option value="">Select Month</option>
							<option value="01">JAN</option>
							<option value="02">FEB</option>
							<option value="03">MAR</option>
							<option value="04">APR</option>
							<option value="05">MAY</option>
							<option value="06">JUN</option>
							<option value="07">JUL</option>
							<option value="08">AUG</option>
							<option value="09" selected>SEP</option>
							<option value="10">OCT</option>
							<option value="11">NOV</option>
							<option value="12">DEC</option>
					</select></td>
					<td nowrap>Year</td>
					<td><select id="year" style="width: 150px" styleClass="ci5">
							<option value="">Select Year</option>
							<option value="1970">1970</option>
							<option value="1971">1971</option>
							<option value="1972">1972</option>
							<option value="1973">1973</option>
							<option value="1974">1974</option>
							<option value="1975">1975</option>
							<option value="1976">1976</option>
							<option value="1977">1977</option>
							<option value="1978">1978</option>
							<option value="1979">1979</option>
							<option value="1981">1981</option>
							<option value="1982">1982</option>
							<option value="1983">1983</option>
							<option value="1984">1984</option>
							<option value="1985">1985</option>
							<option value="1986">1986</option>
							<option value="1987">1987</option>
							<option value="1988">1988</option>
							<option value="1989">1989</option>
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
							<option value="2003">2003</option>
							<option value="2004">2004</option>
							<option value="2005">2005</option>
							<option value="2006">2006</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011" selected>2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
					</select></td>
				</tr>

			</table>
			<input type="button" value="OK" id="upto_ok"
				onClick="de_file_upto();hide_ctrl('modalUpto',true);return false;">
			<input type="button" value="Cancel" id="upto_cancel"
				onClick="hide_ctrl('modalUpto',true);return false;">
		</div>
		<div id="modalSwap"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td>Please select SWAp/NON-SWAp</td>
					<td><html:select property="swap" styleId="swap"
							styleClass="cs3" style="width: 150px">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">NONSWAP</html:option>
							<html:option value="SWAP-IDA">SWAP-IDA</html:option>
							<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
							<html:option value="IBRD">IBRD</html:option>
						</html:select></td>
				</tr>
			</table>
			<input type="button" value="OK" id="swap_ok"
				onClick="de_file_swap();hide_ctrl('modalSwap',true);return false;">
			<input type="button" value="Cancel" id="swap_cancel"
				onClick="hide_ctrl('modalSwap',true);return false;">
		</div>
		<!-- Filter for C-5 -->
		<div id="modalPeriodSwapC"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period9">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period9">All</html:radio></td>
				</tr>
				<tr>
					<td>From:&nbsp;&nbsp;&nbsp; <input type="text"
						Id="fromDate_pop9" Class="ci3">
					</td>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop9"
						Class="ci3">
					</td>
				</tr>
				<tr>
					<td>Please select SWAp/NON-SWAp</td>
					<td><html:select property="swap4" styleId="swap4"
							styleClass="cs3">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">NONSWAP</html:option>
							<html:option value="SWAP-IDA">SWAP-IDA</html:option>
							<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
							<html:option value="IBRD">IBRD</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Please select NC/PC/FC Upgraded</td>
					<td><html:select property="ncfc" styleId="ncfc"
							styleClass="cs3">
							<html:option value="A">All</html:option>
							<html:option value="NC">NC</html:option>
							<html:option value="PC">PC</html:option>
							<html:option value="FC">FC Upgraded</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Please Select Scheme Type</td>
					<td><html:select property="schemeTypeR" styleId="schemeType"
							styleClass="cs3">
							<html:option value="">--Please Select--</html:option>
							<html:option value="WATERSUPPLY">WATER SUPPLY</html:option>
							<html:option value="IMPROVEMENT">IMPROVEMENT</html:option>
							<%--  <html:option value="SEWERAGE">SEWERAGE</html:option> --%>

						</html:select></td>
				</tr>

			</table>
			<input type="button" value="OK" id="period_ok1"
				onClick="de_file_periodSwapC();hide_ctrl('modalPeriodSwapC',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalPeriodSwapC',true);return false;">
		</div>

		<div id="modalPeriodSwap"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period1">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period1">All</html:radio></td>
				</tr>
				<tr>

					<td>From:&nbsp;&nbsp;&nbsp; <input type="text"
						Id="fromDate_pop1" Class="ci3">
					</td>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop1"
						Class="ci3">
					</td>

				</tr>
				<tr>
					<td>Please select SWAp/NON-SWAp</td>
					<td><html:select property="swap1" styleId="swap1"
							styleClass="cs3">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">NONSWAP</html:option>
							<html:option value="SWAP-IDA">SWAP-IDA</html:option>
							<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
							<html:option value="IBRD">IBRD</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Please Select Scheme Type</td>
					<td><html:select property="schemeTypeRR" styleId="schemeType"
							styleClass="cs3">
							<html:option value="">--Please Select--</html:option>
							<html:option value="WATERSUPPLY">WATER SUPPLY</html:option>
							<html:option value="IMPROVEMENT">IMPROVEMENT</html:option>
							<html:option value="SEWERAGE">SEWERAGE</html:option>
						</html:select></td>
				</tr>

			</table>
			<input type="button" value="OK" id="period_ok1"
				onClick="de_file_periodSwap();hide_ctrl('modalPeriodSwap',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalPeriodSwap',true);return false;">
		</div>
		<div id="modalPeriod1"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_period2">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_period2">All</html:radio></td>
				</tr>
				<tr>
					<td>From:&nbsp;&nbsp;&nbsp; <input type="text"
						Id="fromDate_pop2" Class="ci3">
					</td>
					<td>To:&nbsp;&nbsp;&nbsp; <input type="text" Id="toDate_pop2"
						Class="ci3">
					</td>
				</tr>
				<tr>
					<td>Please select SWAp/NON-SWAp</td>
					<td><html:select property="swap2" styleId="swap2"
							styleClass="cs3" style="width: 150px">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">NONSWAP</html:option>
							<html:option value="SWAP-IDA">SWAP-IDA</html:option>
							<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
							<html:option value="IBRD">IBRD</html:option>
						</html:select></td>
				</tr>

			</table>
			<input type="button" value="OK" id="period_ok1"
				onClick="de_file_periodSwap();hide_ctrl('modalPeriod1',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalPeriod1',true);return false;">
		</div>
		<div id="modalUptoSwap"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_upto1">Selection</html:radio> <html:radio
							property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_upto1">All</html:radio></td>
				</tr>
				<tr>
					<td nowrap>Month</td>
					<td><select id="month1" style="width: 150px" styleClass="ci5">
							<option value="">Select Month</option>
							<option value="01">JAN</option>
							<option value="02">FEB</option>
							<option value="03">MAR</option>
							<option value="04">APR</option>
							<option value="05">MAY</option>
							<option value="06">JUN</option>
							<option value="07">JUL</option>
							<option value="08">AUG</option>
							<option value="09" selected>SEP</option>
							<option value="10">OCT</option>
							<option value="11">NOV</option>
							<option value="12">DEC</option>
					</select></td>
					<td nowrap>Year</td>
					<td><select id="year1" style="width: 150px" styleClass="ci5">
							<option value="">Select Year</option>
							<option value="1970">1970</option>
							<option value="1971">1971</option>
							<option value="1972">1972</option>
							<option value="1973">1973</option>
							<option value="1974">1974</option>
							<option value="1975">1975</option>
							<option value="1976">1976</option>
							<option value="1977">1977</option>
							<option value="1978">1978</option>
							<option value="1979">1979</option>
							<option value="1981">1981</option>
							<option value="1982">1982</option>
							<option value="1983">1983</option>
							<option value="1984">1984</option>
							<option value="1985">1985</option>
							<option value="1986">1986</option>
							<option value="1987">1987</option>
							<option value="1988">1988</option>
							<option value="1989">1989</option>
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
							<option value="2003">2003</option>
							<option value="2004">2004</option>
							<option value="2005">2005</option>
							<option value="2006">2006</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011" selected>2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
					</select></td>
				</tr>
				<tr>
					<td>Please select SWAp/NON-SWAp</td>
					<td><html:select property="swap3" styleId="swap3"
							styleClass="cs3" style="width: 150px">
							<html:option value="A">All</html:option>
							<html:option value="NONSWAP">NONSWAP</html:option>
							<html:option value="SWAP-IDA">SWAP-IDA</html:option>
							<html:option value="SWAP-NON IDA">SWAP-NON IDA</html:option>
							<html:option value="IBRD">IBRD</html:option>
						</html:select></td>
				</tr>

			</table>
			<input type="button" value="OK" id="period_ok1"
				onClick="de_file_upto();hide_ctrl('modalUptoSwap',true);return false;">
			<input type="button" value="Cancel" id="period_cancel1"
				onClick="hide_ctrl('modalUptoSwap',true);return false;">
		</div>
		<div id="modalScheme"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td colspan="2">Please select the Report Period</td>
				</tr>
				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_s_upto2">Selection</html:radio> <html:radio
							property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_a_upto2">All</html:radio></td>
				</tr>
				<tr>
					<td nowrap>Month</td>
					<td><select id="month2" style="width: 150px" styleClass="ci5">
							<option value="">Select Month</option>
							<option value="01">JAN</option>
							<option value="02">FEB</option>
							<option value="03">MAR</option>
							<option value="04">APR</option>
							<option value="05">MAY</option>
							<option value="06">JUN</option>
							<option value="07">JUL</option>
							<option value="08">AUG</option>
							<option value="09" selected>SEP</option>
							<option value="10">OCT</option>
							<option value="11">NOV</option>
							<option value="12">DEC</option>
					</select></td>
					<td nowrap>Year</td>
					<td><select id="year2" style="width: 150px" styleClass="ci5">
							<option value="">Select Year</option>
							<option value="1970">1970</option>
							<option value="1971">1971</option>
							<option value="1972">1972</option>
							<option value="1973">1973</option>
							<option value="1974">1974</option>
							<option value="1975">1975</option>
							<option value="1976">1976</option>
							<option value="1977">1977</option>
							<option value="1978">1978</option>
							<option value="1979">1979</option>
							<option value="1981">1981</option>
							<option value="1982">1982</option>
							<option value="1983">1983</option>
							<option value="1984">1984</option>
							<option value="1985">1985</option>
							<option value="1986">1986</option>
							<option value="1987">1987</option>
							<option value="1988">1988</option>
							<option value="1989">1989</option>
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
							<option value="2003">2003</option>
							<option value="2004">2004</option>
							<option value="2005">2005</option>
							<option value="2006">2006</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011" selected>2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
					</select></td>
				</tr>
				<tr>
				<tr>
					<td>Please select Scheme Type</td>
					<td><html:select property="schemeType" styleId="schemeType"
							styleClass="cs3" style="width: 150px">
							<html:option value="WS">Water Supply</html:option>
							<html:option value="SEW">Sewerage</html:option>
							<html:option value="IMP">Improvement</html:option>
						</html:select></td>
				</tr>
			</table>
			<input type="button" value="OK" id="scheme_ok"
				onClick="de_file_upto();hide_ctrl('modalScheme',true);return false;">
			<input type="button" value="Cancel" id="scheme_cancel"
				onClick="hide_ctrl('modalScheme',true);return false;">
		</div>
		<!-- filter for Please select Report Period of  IDA WS/Sew/Performance Imp-->

		<div id="modalIda"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_ida_s_upto2">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_ida_a_upto2">All</html:radio></td>
				</tr>

				<tr>
					<td>Please select Report Period of IDA Performance Imp</td>
					<td><html:select property="IDAType" styleId="IDAType_pop"
							styleClass="cs3" style="width: 150px"
							onchange="
													if(this.value=='All'){
														document.getElementById('selectPeriod_ida_a_upto2').checked=true;
														this.value=null;
														}
													else if(this.value!='All'){
													document.getElementById('selectPeriod_ida_s_upto2').checked=true;
													}
													">

							<html:option value="All">All</html:option>
							<html:option value="phase1">Phase1</html:option>
							<html:option value="phase2">Phase2</html:option>
						</html:select></td>
				</tr>
				<%-- <tr>
												<td>Please select Report Period of  IDA Type</td>
												<td>
												<html:select property="IDA" styleId="IDA2_pop" styleClass="cs3" style="width: 150px" onchange="
													if(this.value=='All'){
														document.getElementById('selectPeriod_ida_a_upto2').checked=true;
														}
													else if(this.value!='All'){
													document.getElementById('selectPeriod_ida_s_upto2').checked=true;
													}
													">
													
														<html:option value="All">All</html:option>
														<html:option value="IDA">IDA</html:option>
														<html:option value="IDA2">IDA2</html:option>
													</html:select>
												</td>	
										</tr> --%>
			</table>


			<input type="button" value="OK" id="ida_ok"
				onClick="de_file_ida_upto();hide_ctrl('modalIda',true);return false;">
			<input type="button" value="Cancel" id="ida_cancel"
				onClick="hide_ctrl('modalIda',true);return false;">
		</div>
		<div id="modalSM"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_SM_s_upto2">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_SM_a_upto2">All</html:radio></td>
				</tr>

				<tr>
					<td>Please select Report Period of IDA Performance Imp</td>
					<td><html:select property="SMType" styleId="SMType_pop"
							styleClass="cs3" style="width: 150px"
							onchange="
													if(this.value=='All'){
														document.getElementById('selectPeriod_SM_a_upto2').checked=true;
														}
													else if(this.value!='All'){
													document.getElementById('selectPeriod_SM_s_upto2').checked=true;
													}
													">

							<html:option value="All">All</html:option>
							<html:option value="single_village">Single Village</html:option>
							<html:option value="multi_village">Multi Village</html:option>
						</html:select></td>
				</tr>
			</table>


			<input type="button" value="OK" id="SM_ok"
				onClick="de_file_SM_upto();hide_ctrl('modalSM',true);return false;">
			<input type="button" value="Cancel" id="SM_cancel"
				onClick="hide_ctrl('modalSM',true);return false;">
		</div>

		<!-- filter for IDA/IDA2 -->


		<div id="modalIda2"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_ida_s_upto5">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_ida_a_upto5">All</html:radio></td>
				</tr>

				<tr>
					<td>Please select Report Period of IDA Type</td>
					<td><html:select property="IDA5" styleId="IDA_pop5"
							styleClass="cs3" style="width: 150px"
							onchange="
													if(this.value=='All'){
														document.getElementById('selectPeriod_ida_a_upto5').checked=true;
														}
													else if(this.value!='All'){
													document.getElementById('selectPeriod_ida_s_upto5').checked=true;
													}
													">

							<html:option value="All">All</html:option>
							<html:option value="IDA">IDA</html:option>
							<html:option value="IDA2">IDA2</html:option>
						</html:select></td>
				</tr>
			</table>


			<input type="button" value="OK" id="ida_ok2"
				onClick="de_file_IDAS_upto();hide_ctrl('modalIda2',true);return false;">
			<input type="button" value="Cancel" id="ida_cancel2"
				onClick="hide_ctrl('modalIda2',true);return false;">
		</div>

		<!-- filter for Program Management by  Ranjay-->


		<div id="modalIBRD"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
					<td>Please select Abstract of status of IBRD</td>
					<td><select id="IBRD_pop" class="cs3" style="width: 150px"
						onchange="document.getElementById('selectReport5').value = IBRDDataReport();disable_enable('program_management');">

							<option value="PMMRPT001_A2.4_A1">1A</option>
							<option value="PMMRPT001_A2.4_1AWL">1A (WL)</option>
							<option value="PMMRPT001_A2.4_2A">2A</option>
							<option value="PMMRPT001_A2.4_2B">2B</option>
					</select></td>
				</tr>
			</table>


			<input type="button" value="OK" id="ibrd_ok"
				onClick="de_file_IBRD_upto();  hide_ctrl('modalIBRD',true);return false;">
			<input type="button" value="Cancel" id="ibrd_cancel"
				onClick="hide_ctrl('modalIBRD',true);return false;">
		</div>
		<!-- filter for Program Management by  Ranjay-->


		<div id="modalDetailIBRD"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>
				<tr>
					<td>Please select Detail of status of IBRD</td>
					<td><select id="IBRD_pop_detail" class="cs3"
						style="width: 150px"
						onchange="document.getElementById('selectReport5').value = IBRDDataDetailReport();disable_enable('program_management');">

							<option value="PMMRPT001_A2.5_A1">1A</option>
							<option value="PMMRPT001_A2.5_1AWL">1A (WL)</option>  
							<option value="PMMRPT001_A2.5_2A">2A</option>
							<option value="PMMRPT001_A2.5_2B">2B</option>

					</select></td>
				</tr>
			</table>


			<input type="button" value="OK" id="ibrd_ok"
				onClick="de_file_IBRD_upto();  hide_ctrl('modalDetailIBRD',true);return false;">
			<input type="button" value="Cancel" id="ibrd_cancel"
				onClick="hide_ctrl('modalDetailIBRD',true);return false;">
		</div>

		<!-- Physical status of canal ,sewerage,tubewell -->
		<div id="modalCST"
			style="position: absolute; left: 325px; top: 375px; width: 650px; border: 3px solid black; background-color: #00A2E2; padding: 25px; font-size: 150%; text-align: center; display: none;">
			<table>

				<tr>
					<td>Period</td>
					<td><html:radio property="selectPeriod" title="Selection"
							value="S" styleId="selectPeriod_CST_s_upto">Selection</html:radio>
						<html:radio property="selectPeriod" title="All" value="A"
							styleId="selectPeriod_CST_a_upto">All</html:radio></td>
				</tr>

				<tr>
					<td>Please select Financial Year</td>
					<td><html:select property="yearType" styleId="Year_Type"
							styleClass="cs3" style="width: 150px"
							onchange="if(this.value=='All'){
														document.getElementById('selectPeriod_CST_a_upto').checked=true;
														}
													else if(this.value!='All'){
													document.getElementById('selectPeriod_CST_s_upto').checked=true;
													} ">

							<option value=" ">Please Select</option>
							<option value="2008-2009">2008-2009</option>
							<option value="2009-2010">2009-2010</option>
							<option value="2010-2011">2010-2011</option>
							<option value="2011-2012">2011-2012</option>
							<option value="2012-2013">2012-2013</option>
							<option value="2013-2014">2013-2014</option>
							<option value="2014-2015">2014-2015</option>
							<option value="2015-2016">2015-2016</option>
							<option value="2016-2017">2016-2017</option>
							<option value="2017-2018">2017-2018</option>
							<option value="2018-2019">2018-2019</option>
							<option value="2019-2020">2019-2020</option>
							<option value="2020-2021">2020-2021</option>
							<option value="2020-2021">2021-2022</option>


						</html:select></td>
				</tr>
				<tr>
					<td>Please select Scheme Type</td>
					<td><select id="schemeType77" styleClass="cs3"
						style="width: 150px">
							<option value="">Please Select</option>
							<option value="PMMRPT001_50">TUBEWELL</option>
							<option value="PMMRPT001_51">CANAL</option>
							<option value="PMMRPT001_52">SEWERAGE</option>
					</select></td>
				</tr>
			</table>


			<input type="button" value="OK" id="CST_ok2"
				onClick="de_file_CST_upto();hide_ctrl('modalCST',true);return false;">
			<input type="button" value="Cancel" id="CST_cancel2"
				onClick="hide_ctrl('modalCST',true);return false;">
		</div>

	</html:form>
	<script>
		document.getElementById("fromDate_pop").value = "";
		document.getElementById("toDate_pop").value = "";
		document.getElementById("month").selectIndex = 0;
		document.getElementById("year").selectIndex = 0;
		//							document.getElementById('HSA2008').checked=true;
		//							triggerEvent(document.getElementById('HSA2008'), 'onclick');
	</script>
</body>
</html>
