<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="mainMenu.title" /></title>
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>" />
<meta name="menu" content="MainMenu" />

<link rel="stylesheet" type="text/css"
	href="scripts/extjs/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css"
	href="scripts/exposure/styles/exposure.css" />

<script type="text/javascript" src="scripts/extjs/bootstrap.js"></script>
<script type="text/javascript" src="scripts/exposure/app/app.js"></script>
<script type="text/javascript" src="scripts/exposure/app/helper/ExposureUtils.js"></script>

</head>

</head>

<script type="text/javascript">

	var exposureUtils = new ExposureUtils();

	Ext.Loader.setConfig({
		enabled : true,
		paths : {
			'Ext' : '/scripts/extjs/ext-all-dev.js',
			'Pandora' : '/scripts/exposure'
		}
	});
</script>


<body>
	<div id="header">
		<div id="header-logo"></div>
		<div id="header-right">
			<div>
				<b>Welcome, ${USERNAME}.</b>&nbsp;|&nbsp;
				<script>
					exposureUtils.printDate();
				</script>
				&nbsp;|&nbsp;
				<!-- <a href="../content/start.do">GEO:underwriting</a>&nbsp;-->
				&nbsp;<a href="logout">Logout</a>
			</div>
		</div>
		<br style="clear: both" />
	</div>
</body>
