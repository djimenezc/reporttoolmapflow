<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
    
    <link rel="stylesheet" type="text/css" href="scripts/extjs/resources/css/ext-all.css">
    <script type="text/javascript" src="scripts/extjs/bootstrap.js"></script>
    <script type="text/javascript" src="scripts/exposure/app/app.js"></script>
    
</head>
    
</head>

    <script type="text/javascript">
    Ext.Loader.setConfig({
        enabled: true,
        paths: {
            'Ext': '/scripts/extjs/ext-all-dev.js',
            'Pandora': '/scripts/exposure'
        }
    });
    
    </script>

