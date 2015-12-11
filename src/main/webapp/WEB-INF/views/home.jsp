<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>TimePuncher WebApp</title>
	
	<style>
		table {
			algin: center;
			border: 1px solid black;
			text-align: center;
		}
		th {
			color: navy;
			width: 300px;
		}
		
		b {
			color: red;
		}
	
	</style>
	
</head>
<body>
	<h1>
		${title}
	</h1>

	<table border=1 style="float:left;">
		<tr>
			<th style="width:100px;">REQUEST</th>
		</tr>
		<c:forEach items="${requestList}" var="request">
			<tr>
				<td><b>${request}</b></td>
			</tr>
		</c:forEach>
	</table>
	<table border=1 style="float:left;">
		<tr>
			<th>DESCRIPTON</th>
		</tr>
		<c:forEach items="${descList}" var="desc">
			<tr>
				<td>${desc}</td>
			</tr>
		</c:forEach>
	</table>
	<table border=1 style="float:left;">
		<tr>
			<th>URL</th>
		</tr>
		<c:forEach items="${urlList}" var="url">
			<tr>
				<td>${url}</td>
			</tr>
		</c:forEach>
	</table>
	<br style="clear:left;"><br>
	
	<div>
		<h3>Examples:</h3>
		<p><ins>GET User:</ins>              /api/user/1</p>
		<p><ins>GET Time Report:</ins>       /api/report/2015-10-25/2015-11-25/1</p>
		<p><ins>PUT Update User:</ins>       /api/users/1</p>
		<p><ins>DELETE User:</ins>			  /api/users/1</p>
	</div>

</body>
</html>
