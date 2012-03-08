<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="menu.stats.events.view" /></title>
<meta name="heading"
	content="<fmt:message key='menu.stats.events.view'/>" />
<meta name="menu" content="ViewStats" />
</head>

<div id="search">
	<form method="get" action="${ctx}/stats/view" id="searchForm">
		<input type="text" size="20" name="q" id="query" value="${param.q}"
			placeholder="Enter search terms" /> <input type="submit"
			value="<fmt:message key="button.search"/>" />
	</form>
</div>


<display:table name="mapdisplayList" cellspacing="0" cellpadding="0"
	requestURI="" defaultsort="1" id="users" pagesize="25" class="table"
	export="true">
	<display:column property="username" escapeXml="true" sortable="true"
		titleKey="stats.transactionId" style="width: 25%" url="/userform?from=list"
		paramId="id" paramProperty="id" />
<%-- 	<display:column property="fullName" escapeXml="true" sortable="true" --%>
<%-- 		titleKey="activeUsers.fullName" style="width: 34%" /> --%>
<%-- 	<display:column property="email" sortable="true" titleKey="user.email" --%>
<%-- 		style="width: 25%" autolink="true" media="html" /> --%>
<%-- 	<display:column property="email" titleKey="user.email" --%>
<%-- 		media="csv xml excel pdf" /> --%>
<%-- 	<display:column sortProperty="enabled" sortable="true" --%>
<%-- 		titleKey="user.enabled" style="width: 16%; padding-left: 15px" --%>
<%-- 		media="html"> --%>
<!-- 		<input type="checkbox" disabled="disabled" -->
<%-- 			<c:if test="${users.enabled}">checked="checked"</c:if> /> --%>
<%-- 	</display:column> --%>
	<display:column property="enabled" titleKey="user.enabled"
		media="csv xml excel pdf" />

	<display:setProperty name="paging.banner.item_name" value="user" />
	<display:setProperty name="paging.banner.items_name" value="users" />

	<display:setProperty name="export.excel.filename" value="User List.xls" />
	<display:setProperty name="export.csv.filename" value="User List.csv" />
	<display:setProperty name="export.pdf.filename" value="User List.pdf" />
</display:table>

<script type="text/javascript">
	highlightTableRows("users");
</script>



