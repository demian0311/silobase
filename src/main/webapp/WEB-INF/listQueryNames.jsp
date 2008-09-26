<html>
<head>
	<title></title>
	<LINK REL="stylesheet" TYPE="text/css" HREF="style.css"/>
</head>
<body>
<h2><a href="list">SiloBase</a> | Queries</h2>
<ul>
<% for(String currQueryName : (java.util.List<String>)session.getAttribute("queryNames")){ %>
	<li><a href="query?name=<%=currQueryName%>"><%=currQueryName%></a></li>
<% } %>
</ul>
</body>
</html>