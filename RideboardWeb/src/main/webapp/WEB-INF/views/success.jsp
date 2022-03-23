<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="header.jsp"%>
<body>
	<%@ include file="title.jsp"%>
	<%@ include file="navigation.jsp"%>

	<main>
		<div align="center">
		<table style="background-color:#EEEEEE">
			<tr>
				<td colspan="2">
					<legend>Profile</legend>
				</td>
			</tr>
			<tr>
				<td>Role</td>
				<td>${userObj.roleName}</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>${userObj.userName}</td>
			</tr>
			<tr>
				<td>Last Login</td>
				<td>${userObj.lastLoginDate}</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<form:form name="logoutForm" action="logout" method="POST">
							<input type="submit" value="Logout" />
						</form:form>
					</div>
				</td>
				<td>
					<div align="center">
						<form:form name="nextForm" action="next" method="POST">
							<input type="submit" value="Next >" />
						</form:form>
					</div>
				</td>
			</tr>
		</table></div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>