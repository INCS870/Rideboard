<div id="divProfile">
	<table border="0">
		<tr>
			<td align="left"><b>${userObj.profileName}</b></td>
			<td align="right">
				<button onclick="editProfile()">Edit</button>
			</td>
		</tr>
		<tr>
		<tr>
			<td colspan="2">
				<hr />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="readonlyProfileDiv">
					<table border="0">
						<tr>
							<td>User Id:</td>
							<td>${userObj.userName}</td>
						</tr>
						<tr>
							<td>Role:</td>
							<td>${userObj.roleName}</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td>
								<div id='emailReadOnly'>${userObj.email}</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="updateProfileDiv" style="display: none">
					<form id="frmProfileDetail" action="updateProfile" method="post">
						<table>
							<tr>
								<td>User Id:</td>
								<td>${userObj.userName}</td>
							</tr>
							<tr>
								<td>Role:</td>
								<td>${userObj.roleName}</td>
							</tr>
							<tr>
								<td>Email:</td>
								<td>
									<input type="text" id="txtEditEmail" name="email" value="${userObj.email}" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input id="updatePwdChk" type="checkbox" onchange="showUpdatePwd()" /><i>&nbsp;Update Password</i>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="updatePwdDiv" style="display: none">
										<table>
											<tr>
												<td colspan="2"><legend>Update Password</legend></td>
											</tr>
											<tr>
												<td>Current Password:</td>
												<td><input type="password" id="password" name="password" /></td>
											</tr>
											<tr>
												<td>New Password:</td>
												<td><input type="password" id="newPassword" name="newPassword" /></td>
											</tr>
											<tr>
												<td>Confirm Password:</td>
												<td><input type="password" id="confirmPwdTxt" /></td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</form>
					<table>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td align="left">
								<button id="updateProfileBtn" onclick="updateProfile()">Update</button>
							</td>
							<td align="right">
								<button id="revertProfileBtn" onclick="revertProfile()">Cancel</button>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<script>
		function showUpdatePwd() {
			$("#password").val("");
			$("#newPassword").val("");
			$("#confirmPwdTxt").val("");
			
			if($("#updatePwdChk").prop("checked"))
				$("#updatePwdDiv").show();
			else {
				$("#updatePwdDiv").hide();
			}
		}
		function editProfile() {
			//$("#updateProfileBtn").attr("disabled", "false");
			$("#txtEditEmail").val($("#emailReadOnly").html());
			$("#updateProfileDiv").show();
			$("#readonlyProfileDiv").hide();
		}
		function revertProfile() {
			//$("#updateProfileBtn").attr("disabled", "true");
			$("#txtEditEmail").val($("#emailReadOnly").html());
			$("#updateProfileDiv").hide();
			$("#readonlyProfileDiv").show();
		}
		function updateProfile() {
			//$("#updateProfileBtn").attr("disabled", "true");			
			if($("#updatePwdChk").prop("checked")) {
				if($("#password").val() == "" || $("#newPassword").val() == "" || $("#confirmPwdTxt").val() == "") {
					alert("There is blank password value");
					//$("#updateProfileBtn").attr("disabled", "false");
					return;
				}
				if($("#newPassword").val() != $("#confirmPwdTxt").val()) {
					alert("New password not match");
					//$("#updateProfileBtn").attr("disabled", "false");
					return;
				}
			}
			$("form#frmProfileDetail").submit();
		}
	</script>
</div>