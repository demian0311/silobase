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
</FORM>

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


<h1>Graph</h1>
<div id="placeholder" style="width:600px;height:300px;"></div>

<!--script id="source" language="javascript" type="text/javascript">
$(function () {

   var tableData =
   [
      {label: "local", data: [[-5, 985999],  [-4, 972364],  [-3, 805317],  [-2, 1135220], [-1, 1289827], [0, 1092281]]},
      {label: "inter", data: [[-5, 1414667], [-4, 1370103], [-3, 1155128], [-2, 1100655], [-1, 992027],  [0, 827948	]]},
      {label: "intra", data: [[-5, 1026509], [-4, 998948],  [-3, 801735],  [-2, 981336],  [-1, 1056067], [0, 891378	]]},
      {label: "intl", data: [[-5, 20900], [-4, 21569], [-3, 16906], [-2, 11859], [-1, 16194], [0, 10615 ]]},
      {label: "tf", data: [[-5, 350681], [-4, 552260], [-3, 446819], [-2, 326578], [-1, 371403], [0, 263582]]}
   ];

   $.plot
   (
      $("#placeholder"),
      tableData,
      {
         series:
         {
            lines: { show: true },
            points: { show: true }
         },
         legend: { position: 'sw' }
      }
   )
});
</script-->







<br/>
<br/>
<!--
DEBUG INFO
<%=query%>
-->
</body>
</html>
