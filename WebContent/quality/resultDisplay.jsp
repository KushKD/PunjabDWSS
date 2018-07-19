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


<!DOCTYPE html>
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/waterquality.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript">
	/* Req = "ent_frwrd";
	de_init('ent_frwrd', "test"); */
	var testResultId = localStorage.getItem("resultEntry");
	function de_display() {

		document.resultDisplayForm.action = "resultDisplayAction.do?method=displayTestResult&d__mode="
				+ d__mode + "&menuId=WQ002&testResultId=" + testResultId;
		document.resultDisplayForm.submit();
	}
	hide_ctrl('modalPeriod', true);
</script>

<style type="text/css">
/* th { */
/* 	background: #d2c8c8; */
/* } */
.modal-header {
	background-color: #000000;
	padding: 6px 6px;
	color: #FFF;
	border-bottom: 2px dashed #000000;
}

.modal-title {
	text-align: center;
}
/* .close{ */
/* 	color:#ffffff */
/* } */
.close {
	color: #fff;
	opacity: 1;
}

/* Important part */
.modal-dialog {
	overflow-y: initial !important
}

.modal-body {
	max-height: 500px;
	overflow-y: auto;
}

.modal-footer {
	margin: -10px;
}
</style>

</head>
<html:html>
<html:form action="resultDisplayAction" method="post"
	styleId="resultDisplayForm">




	<div class="panel panel-danger" id="revalidate">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Test Details</h4>
			<div class="line"></div>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign">Sample Number.</label> <label
					class="text-right" id='sampleNumber'> <c:if
						test="${sampleNumber!=null}">
						<%=request.getAttribute("sampleNumber")%>
					</c:if>

				</label>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign">Sample PartNo.</label> <label
					class="text-right" id='samplePart'></label>
				<c:if test="${samplePartNumber!=null}">
					<%=request.getAttribute("samplePartNumber")%>
				</c:if>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">Test Done By.</label> <label
					class="text-right" id='testDone'> <c:if
						test="${testDoneBy!=null}">
						<%=request.getAttribute("testDoneBy")%>
					</c:if>
				</label>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">Lab Name</label> <label
					class="text-right" id='labName'> <c:if
						test="${labName!=null}">
						<%=request.getAttribute("labName")%>
					</c:if>

				</label>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		</div>

	</div>
	<c:if test="${not empty resultEntryDtos}">
		<div class="panel panel-danger" id="revalidate">
			<div class="panel-body">
				<h4
					class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
					Test Details</h4>
				<div class="line"></div>
				<logic:iterate name="resultEntryDtos" id="resultEntryLst"
					property="resultEntryDtos" indexId="rowindex"
					type="com.prwss.min.quality.ResultEntryDto">

					<div>
						<div class="col-lg-1">&nbsp;</div>
						<div class='col-lg-3 ' style="height: 45px;">
							<bean:write name="resultEntryLst" property="parameterName" />
						</div>

						<div class='col-lg-2 ' style="height: 45px;">
							<bean:write name="resultEntryLst" property="actualvalue" />
						</div>
					</div>

				</logic:iterate>
				<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
					id='comments'>
					<label class="text-right labledesign">Comments</label>
					<html:textarea property='comments' styleClass="form-control ci5"
						styleId='comment'></html:textarea>
				</div>
			</div>
		</div>
	</c:if>
	<!-- comments -->
	
	<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
	<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12">
		<input type='hidden' id='testresultId'>
		<div class="container">
			<!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-info btn-sm" data-toggle="modal"
				data-target="#myModal">Click here to check all comments</button>

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Previous Comments</h4>
						</div>
						<div class="modal-body">
							<c:forEach items="${testResultCommentBean}" var="commentBean">
								<c:if test="${commentBean.commentBy==0}">
									<div
										class=" form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12"
										id='previousComments'>
										<label class="text-left labledesign" style="width: 500px;">Comment
											Given by Initiator on ${commentBean.commentDate} </label>
									</div>
								</c:if>
								<c:if test="${commentBean.commentBy ==13001}">
									<div
										class=" form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12"
										id='previousComments'>
										<label class="text-left labledesign" style="width: 500px;">Comment
											Given by Reviewer on ${commentBean.commentDate}</label>
									</div>
								</c:if>
								<c:if test="${commentBean.commentBy==11001}">
									<div
										class=" form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12"
										id='previousComments'>
										<label class="text-left labledesign" style="width: 500px;">Comment
											Given by Approver on ${commentBean.commentDate}</label>
									</div>
								</c:if>
								<div
									class=" form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12"
									id='previousComments23'>

									<html:textarea property='previousComments'
										styleClass="form-control ci5" style="width:500px;"
										styleId='previousComment' value='${commentBean.commentText}'></html:textarea>
								</div>

							</c:forEach>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="col-lg-6 col-md-6 sm-hidden xs-hidden">&nbsp; &nbsp;</div>
	<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div>
	<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div>
	<div
		class="form-group form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp; &nbsp;</div>
		<input type='submit' value='Return' class="btn btn-primary"
			id='backword' onclick='resultEntry.updateResultEntry(this.value)' />
		<input type='submit' value='Forward' class="btn btn-success"
			id='forward' onclick='resultEntry.updateResultEntry(this.value)' />
		<input type='submit' value='Approve' class="btn btn-info" id='approve'
			onclick='resultEntry.updateResultEntry(this.value)'></input> <input
			type='submit' value='ReturnToInitiator' class="btn btn-info"
			id='initiator' onclick='resultEntry.updateResultEntry(this.value)' />
	</div>
	<br>
</html:form>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		var teststatus =
<%=request.getAttribute("teststatus")%>
	var requestlevel =
<%=request.getAttribute("requestlevel")%>
	if (requestlevel === 1 && teststatus == 10) {
			$('#backword').hide();
			$('#initiator').hide();
			$('#approve').hide();
			$('#forward').hide();
		}
		if (requestlevel === 1 && teststatus != 10) {
			$('#backword').hide();
			$('#initiator').hide();
			$('#approve').hide();

			$('#forward').show();

		}
		if (requestlevel == 2) {
			//$('#backword').show();
			$('#forward').show();
			$('#approve').hide();
			$('#initiator').show();

		}
		if (requestlevel == 3) {
			$('#backword').show();
			$('#approve').show();
			$('#initiator').show();
			$('#forward').hide();

		}
		Req = "ent_frwrd";
		de_init('ent_frwrd', "test");
	});
	if (localStorage.getItem('resultEntry')) {
		de_display();
	}
	localStorage.removeItem('resultEntry');
</script>
</html:html>