<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PeopleSoft | TWS Calendar Generator</title>

<link rel="shortcut icon"
	href="<c:url value="/resources/favicon_oh.ico" />">

<!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap.theme.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"/>

   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<%-- 	<script src="<c:url value="/resources/js/angular.min.js" />"></script>
	<script src="<c:url value="/resources/js/app.js" />"></script> --%>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-validate-min.js" />"></script>
	<script src="<c:url value="/resources/js/additional-methods.min.js" />"></script>
	

</head>

<body >
	<div id="main-box">
		<header><img alt="Optum Logo"
		src="<c:url value="/resources/images/optumlogo.png" />">
		<span> PeopleSoft | TWS Calendar Generator</span></header>
	
		<div id="content-box">
		<form id="file_gen_form" action="/TWSCalendar/generateFile" method="post">
		<fieldset>
			<span class="formInput"><label>Job Name : </label> 
			<select	class="form-control" id="job_name" name="jobname" required>
				<!-- 	<option ng-repeat="job in main.jobCode" value="{{job.jobCode}}">{{job.jobCode}}
						({{job.jobDescription}})</option> -->
			</select> </span> 
			<span class="formInput"><label for="holiday_list">Holiday List : </label> 
			<input class="form-control" type="file" id="holiday_list" name="holidayList" required/> </span>
			<span class="formInput"><label>Year : </label> 
			<select class="form-control" name="year" id="year-input" required></select></span> 
			<input name="fileType" id="file_type" type="hidden" name="fileType"/>
			</fieldset>
		</form>
		<div class="spacer">
		<button type="button" class="btn btn-primary" id="generate_btn" >Generate .xls</button>
		<button type="button" class="btn btn-info" id="gen_txt_btn">Generate .txt</button>
		</div>
		
		</div>

		<footer>&copy; Optum, Inc. All rights reserved.</footer>
	</div>	
</body>
</html>