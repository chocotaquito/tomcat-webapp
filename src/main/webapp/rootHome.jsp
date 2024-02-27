<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>

<%
	String message = (String) session.getAttribute("message");
	ArrayList<String[]> records = (ArrayList<String[]>) session.getAttribute("records");
	
   	if (message == null) message = "";
%>


<html>
<head>
<meta charset="UTF-8">
	<title>Project #4 - Root Home</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<p>You are connected to the Project 4 Enterprise system as a root-level user.</p>
	<p>Please enter any SQL query or update command in the box below</p>
	<form action="RootUser" method="post">
		<textarea rows="4" cols="50" name="query" id="query" spellcheck="false">SELECT * FROM jobs;</textarea>
		<br>
		<input type="submit" value="Execute Command">
		<input type="reset" value="Reset Form">
		<input type="submit" value="Clear Results" name="clear">
	</form>
	<p>Execution results:</p>
	<h2>
      	<%-- JSP expression to access the message sent from the servlet --%>
      	<%=message%>
      	<br>
   	</h2>
   	<%if (records != null){%>
   	<table><% for (int i = 0; i < records.size(); i++) { 
           String[] row = records.get(i);
        %>
        <tr>

            <% for (int j = 0; j < row.length; j++) {
               // This RHS made up due to not knowing the class of objects
               // in your map, use something equivalent
               String cell = row[j]; 
            %>
            <td>
               <%=cell.toString()%>
            </td>
            <% } %>
        </tr>
        <% } %>
   	</table>
   	<%} %>
</body>
</html>