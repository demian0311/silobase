<%
String queryName = request.getParameter("name");
%>
<html>
<body>
<h2><a href="index.jsp">SiloBase</a> | <%=queryName%></h2>
<hr/>
<pre>
<% 
com.neidetcher.silobase.Query query = com.neidetcher.silobase.ServiceImpl.createServiceImpl("src/test/resources/silobase.xml").getQuery(queryName);
%>
<%=query%>
</pre>
</body>
</html>
