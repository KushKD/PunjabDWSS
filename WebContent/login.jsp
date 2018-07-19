<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
<body>
<head>

</head>



<html:form action="loginAction">

<div class="container-fluid">
<div class="row">


<div class="col-lg-2 hidden-xs hidden-sm col-md-2"></div>

<div class="col-lg-8 col-sm-12 col-xs-12 col-md-8" style="background-color: #FFFFFF; padding: unset;">
		<div class="row center-block">
		
				<div class="logo">
				<h3 style="text-align: center; background-color: #44c7f4; margin: 3% 0 11%"><strong>MIS-Application</strong></h3>
					<img src="images/logo.png" alt="">
				</div>



			
					<div style="margin: unset; padding: unset;" class="login row">
						<div class="login-top">
						<div class="input-group col-lg-12 col-xs-12"><h3><strong>Login</strong></h3>
						</div>
						</div>
							<div class="login-top">
								<div class="input-group col-lg-12 col-xs-12">
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
									<html:text property="userName" styleClass="form-control" style="width: inherit" />
								</div>
								<span class="help-block"></span>

								<div class="input-group col-lg-12 col-xs-12">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<html:password property="password" styleClass="form-control" style="width: inherit" />
									<span class="input-group-addon"><i class="fa fa-lock"
										data-toggle="tooltip" data-placement="top"
										title="Username Help!"></i></span>
					
						</div>
						<div class="col-lg-12 col-xs-12:">					
					<a href="#" style="float: right; font-size: 11px">Forgot Password?</a>
					<span style="float: right; font-size: 11px; padding: 0 2"> / </span>
					<a href="#" style="float: right; font-size: 11px">Change Password.</a>
					</div>					
							
								<span class="help-block"></span>



								<button
									class="col-lg-6 col-xs-6 col-md-6 col-sm-6 btn  btn-success"
									style="margin: 12px 0 2px 0;" type="submit" value="Login">Login</button>
								
								
								<button
									class="col-lg-6 col-xs-6 col-md-6 col-sm-6 btn  btn-primary"
									style="margin: 12px 0 2px 0;" type="reset" value="Reset">Reset</button>
								
								
								<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding: unset">
								<div class="col-lg-12 hidden-xs col-sm-12 col-md-12" style="min-height: 20%"></div>
									
									
									<ul style="padding: initial; padding-top: 10px">
										<h4 style="float: left"><strong>Follow Us On...</strong></h4>
										<a href="#" class="fa fa-facebook-official" style="font-size: xx-large; margin: 0 2 0 15"></a>
										<a href="#" class="fa fa-twitter" style="font-size: xx-large; margin: 0 2 0 0"></a>
										<a href="#" class="fa fa-instagram" style="font-size: xx-large; margin: 0 2 0 0"></a>
									</ul>
									
									</div>
									<div class="clear"></div>
								</div>
							</div>
						

					</div> 
				</div>
				
		
				
				<div class="clear"></div>
				
				</div>
				</div>

<div class="col-lg-2 hidden-xs hidden-sm col-md-2"></div>

</html:form>
</body>
</html:html>