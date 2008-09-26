<%@ page import="com.neidetcher.silobase.*, java.util.*" %>

<% Query query = (Query)session.getAttribute("query");%>
<html>
<head>
	<title></title>
	<LINK REL="stylesheet" TYPE="text/css" HREF="style.css"/>
</head>
<body>
<h2><a href="list">SiloBase</a> | <%=query.getName()%></h2>

<FORM action="query" method="get">
<input type="hidden" name="name" value="<%=query.getName()%>"/>
<input type="hidden" name="form" value="yes"/>
<table border="0">

<% if (!query.getInputFields().isEmpty()) { %>
<% for(InputField currField : query.getInputFields()) { %>
	<tr><td><%=currField.getPrettyName()%></td>
	<td><input type="text" name="<%=currField.getName()%>" value="<%=currField.getValue()%>" /></td></tr>
<% } %>

<% } %>
</table>
	<input type="submit"/>
</form>

<% if (!query.getResults().isEmpty()) { %>
<table border="1">
	<tr>
	<% for(String columnName : query.getResults().get(0).keySet()) { %>
		<th><%=columnName%></th>
	<% } %>
	</tr>
	
	<% for(Map currRow : query.getResults()) { %>
		<tr>
			<% for(Object columnName : currRow.keySet()) { %>
				<td><%=""+currRow.get(columnName.toString())%></td>
			<% } %>
		</tr>
	<% } %>
</table>
<% } %>

<br/>
<br/>
<!--
DEBUG INFO
<%=query%>
-->
</body>
</html>
