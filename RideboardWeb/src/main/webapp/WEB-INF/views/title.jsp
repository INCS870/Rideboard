<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<form:form id="frmLogout" name="frmLogout" action="logout"
		method="POST">
	</form:form>
	<script>
		function logout() {
			document.getElementById("frmLogout").submit(
				function( event ) {
					if (!confirm('Are you sure to logout Rideboard ?')) {
			    	return;
				}
				event.preventDefault();
			});
		}
	</script>
	<table>
		<tr>
			<td width="5%" align="left"><img src='resources/images/icon.png'
				width='56' height='56' /></td>
			<td>&nbsp;</td>
			<td><b><font size="6">Rideboard</font></b></td>
			<td>&nbsp;</td>
			<td width="90%"><font size="4">Integrated social platform
					for race industry</font></td>
			<td width="5%" align="right"><c:if test="${userProfile!=null}">
					<div class="header__avatar">
						<div class="dropdown">
							<ul class="dropdown__list">
								<li class="dropdown__list-item"><span
									class="dropdown__icon"><i class="far fa-user"></i></span> <span
									class="dropdown__title">my profile</span></li>
								<li class="dropdown__list-item"><span
									class="dropdown__icon"><i class="fas fa-sign-out-alt"></i></span>
									<span class="dropdown__title" onclick="logout()">log out</span>
								</li>
							</ul>
						</div>
					</div>
				</c:if></td>
		</tr>
	</table>
</header>