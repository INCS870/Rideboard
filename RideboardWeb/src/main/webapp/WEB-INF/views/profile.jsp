<div id="divProfile" class="profile">
	<table border="0">
		<tr>
			<td colspan="2">
				<legend>User Profile</legend>			
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<b>${userObj.profileName}</b>			
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr />			
			</td>
		</tr>
		<tr>
			<td>
			User Id:
			</td>
			<td>
			${userObj.userName}
			</td>
		</tr>
		<tr>
			<td>
			Role:
			</td>
			<td>
			${userObj.roleName}
			</td>
		</tr>	
		<tr>
			<td>
			Email:
			</td>
			<td>
			${userObj.email}
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<a href="" onclick="closeProfileDiv()">Close</a>
			</td>
		</tr>	
	</table>
</div>