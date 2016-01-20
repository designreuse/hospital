<%@page import="com.dpc.utils.memcached.MemSession"%>
<%@ page import="com.dpc.web.mybatis3.domain.Admin" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row border-bottom">
	<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header" style="height: 60px;line-height: 60px;margin-left: 35px;font-size: 20px;font-weight: bold;">
			<span class="m-r-sm text-muted welcome-message">心血管预防服务平台</span>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li>
				<span class="m-r-sm text-muted welcome-message">
				<%
				Admin admin = (Admin) session.getAttribute("admin");
				String name = admin.getName();
				%>
				当前登录用户：<%=name%></span>
			</li>
			<li>
               	<a href="#" onclick="loginout()">
                   	<i class="fa fa-sign-out"></i> 退出
               	</a>
           	</li>
		</ul>
	</nav>
</div>
<script type="text/javascript">
	function loginout(){
		window.location.href = "${ctx}/back/manager/logout";
	}
</script>