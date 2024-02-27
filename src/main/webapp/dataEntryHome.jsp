<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String message = (String) session.getAttribute("message");

	if (message == null) message = "";
%>

<html>
<head>
<meta charset="UTF-8">
<title>Project #4 - Data Entry Home</title>
</head>
<body>
	<p>You are connected to the Project 4 Enterprise system as a data-entry-level user.</p>
	<p>Enter the data values in a form below to add a new record to the corresponding database table</p>
	<br>
	<br>
	<form action="DataEntryUser" method="post">
		<fieldset>
			<legend>Suppliers Record Insert</legend>
			<table >
				<tr>
					<td>
						snum
					</td>
					<td>
						sname
					</td>
					<td>
						status
					</td>
					<td>
						city
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="snum_s"/>
					</td>
					<td>
						<input type="text" name="sname_s"/>
					</td>
					<td>
						<input type="text" name="status_s"/>
					</td>
					<td>
						<input type="text" name="city_s"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Enter Supplier Record Into Database" name="suppliers">
			<input type="submit" value="Clear Data and Results" name="clear">
		</fieldset>
		<fieldset>
			<legend>Parts Record Insert</legend>
			<table >
				<tr>
					<td>
						pnum
					</td>
					<td>
						pname
					</td>
					<td>
						color
					</td>
					<td>
						weight
					</td>
					<td>
						city
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="pnum_p"/>
					</td>
					<td>
						<input type="text" name="pname_p"/>
					</td>
					<td>
						<input type="text" name="color_p"/>
					</td>
					<td>
						<input type="text" name="weight_p"/>
					</td>
					<td>
						<input type="text" name="city_p"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Enter Part Record Into Database" name="parts">
			<input type="submit" value="Clear Data and Results" name="clear">
		</fieldset>
	
		<fieldset>
			<legend>Jobs Record Insert</legend>
			<table >
				<tr>
					<td>
						jnum
					</td>
					<td>
						jname
					</td>
					<td>
						numworkers
					</td>
					<td>
						city
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="jnum_j"/>
					</td>
					<td>
						<input type="text" name="jname_j"/>
					</td>
					<td>
						<input type="text" name="numworkers_j"/>
					</td>
					<td>
						<input type="text" name="city_j"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Enter Job Record Into Database" name="jobs">
			<input type="submit" value="Clear Data and Results" name="clear">
		</fieldset>
	
		<fieldset>
			<legend>Shipments Record Insert</legend>
			<table >
				<tr>
					<td>
						snum
					</td>
					<td>
						pnum
					</td>
					<td>
						jnum
					</td>
					<td>
						quantity
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="snum_sh"/>
					</td>
					<td>
						<input type="text" name="pnum_sh"/>
					</td>
					<td>
						<input type="text" name="jnum_sh"/>
					</td>
					<td>
						<input type="text" name="quantity_sh"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Enter Shipment Record Into Database" name="shipments">
			<input type="submit" value="Clear Data and Results" name="clear">
		</fieldset>
	</form>
	<p>Execution results:</p>
	<h2>
      	<%-- JSP expression to access the message sent from the servlet --%>
      	<%=message%>
      	<br>
   	</h2>
	
</body>
</html>