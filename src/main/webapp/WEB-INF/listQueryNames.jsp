<html>
<body>
<h2><a href="index.jsp">SiloBase</a> | Queries</h2>
<hr/>
<ul>
<% for(String currQueryName : (java.util.List<String>)session.getAttribute("queryNames")){ %>
	<li><a href="query?name=<%=currQueryName%>"><%=currQueryName%></a></li>
<% } %>
</ul>

*
</body>
</html>

