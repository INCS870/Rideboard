<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="header.jsp"%>
<body>
	<%@ include file="title.jsp"%>
	<%@ include file="navigation.jsp"%>
	<main> <form:form name="submitForm" action="login" method="POST">
		<div align="center">
			<br />
			<div style="color: red; background-color: #FFFFFF">${error}</div>
			<br/>
			<table style="background-color: #EEEEEE">
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td>User Name</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><div align="center">
						<input type="submit" value="Submit" onclick="showLoading()" />
					</div></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</form:form> </main>
	<%@ include file="footer.jsp"%>
</body>
</html>