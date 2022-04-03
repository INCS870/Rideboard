<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<form:form id="frmLogout" name="frmLogout" action="logout" method="POST">
	</form:form>
	<form:form id="frmProfile" name="frmProfile" action="profile" method="GET">
	</form:form>
	<script>
		function logout() {
			if (confirm('Are you sure to logout Rideboard ?')) {
				showLoading();
				document.getElementById("frmLogout").submit();
			}
		}
		function showProfile() {
			//openProfileDiv();
			var frm = $("form#frmProfile");
		    openAjaxDialog(frm.attr("action"), "get", "ProfilePopupDiv", "User Profile", 410, 450, true, frm.attr("id"), function () {
		        $('div#ProfilePopupDiv').on('dialogclose', function (event) {
		            $(this).detach();
		        });
		    });	
		}
	</script>
	<table>
		<tr>
			<td width="5%" align="left">
			<a href="/Rideboard">
			<img src='resources/images/icon.png'
				width='56' height='56' /></a></td>
			<td>&nbsp;</td>
			<td><b><font size="6">Rideboard</font></b></td>
			<td>&nbsp;</td>
			<td width="90%"><font size="4">Integrated social platform
					for race industry</font></td>
			<td width="5%" align="right"><c:if test="${userProfileId!=null}">
					<div class="header__avatar">
						<div class="dropdown">
							<ul class="dropdown__list">
								<li class="dropdown__list-item"><span
									class="dropdown__icon"><i class="far fa-user"></i></span> 
<!-- 									<span class="dropdown__title">my profile</span> -->
									<span id="spanProfile" class="dropdown__title" onclick="showProfile()">My profile</span>
								</li>
								<li class="dropdown__list-item"><span
									class="dropdown__icon"><i class="fas fa-sign-out-alt"></i></span>
									<span id="spanLogout" class="dropdown__title" onclick="logout()">Log out</span>
								</li>
							</ul>
						</div>
					</div>
				</c:if></td>
		</tr>
	</table>
</header>
<div id="errorDiv" style="text-align: center; color: red; background-color: #FFFFFF">${error}</div>
<div id="loadingPage" class="popupLoading">
    <div id="innerLoadingDiv" class="popupLoadingDiv">
        <img src="resources/images/loading1.gif" />
    </div>
</div>