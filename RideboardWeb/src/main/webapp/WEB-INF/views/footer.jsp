<footer>
	<div align="center">
		<table>
			<tr>
				<td>
					<h6>All rights reserved 2022</h6>
				</td>
				<td> | </td>
				<td>
					<h6>${host_ip}</h6>
				</td>
				<td> | </td>
				<td>
					<h6 id="realTimeClock"></h6>
				</td>
				<td> | </td>
				<td>
					<h6>v1.1</h6>
				</td>
			</tr>
		</table>
	</div>
</footer>
<script>
    $(document).ready(function () {
        $("#realTimeClock").jclock({
            format: '%d-%m-%Y %H:%M:%S'
        });
    });
</script>