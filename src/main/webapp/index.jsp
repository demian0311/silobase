<html>
<body>
<h2><a href="index.jsp">SiloBase</a> | Queries</h2>
<hr/>
<ul>
<% java.util.List<String> queryNames = com.neidetcher.silobase.ServiceImpl.createServiceImpl("src/test/resources/silobase.xml").getQueryNames(); %>
<% for(String queryName : queryNames){ %>
	<li><a href="query.jsp?name=<%=queryName%>"><%=queryName%></a></li>
<% } %>
</ul>
</body>
</html>
