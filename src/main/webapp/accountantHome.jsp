<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>

<%
	ArrayList<String[]> records = (ArrayList<String[]>) session.getAttribute("records");
%>
<html>
<head>
<meta charset="UTF-8">
    <title>Project #4 - Accountant Home</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
  <body>
	<p>You are connected to the Project 4 Enterprise system as a accountant-level user.</p>
	<p>Enter the data values in a form below to add a new record to the corresponding database table</p>
	<br>
	<br>
	<form action="AccountantUser" method="post">
	<fieldset>
		<input type="radio" name="rad" value="option1">
		<b>Get The Maximum Status Value of All Suppliers</b>(Returns a maximum value)
		<br><br><input type="radio" name="rad" value="option2">
		<b>Get The Total Weight Of All Parts</b>(Returns a sum)
		<br><br><input type="radio" name="rad" value="option3">
		<b>Get The Total Number of Shipments</b>(Returns the current number of shipments in total)
		<br><br><input type="radio" name="rad" value="option4">
		<b>Get The Name And Number Of Workers Of The Job With The Most Workers</b>(Returns two values)
		<br><br><input type="radio" name="rad" value="option5">
		<b>List The Name And Status Of Every Supplier</b>(Returns a list of supplier names with status)
		<br><br><br>
		<input type="submit" value="Execute Command">
		<input type="submit" value="Clear Results" name="clear">
	</fieldset>
	</form>
	<p>Execution results:</p>
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