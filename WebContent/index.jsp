<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<html>
<head>
    <title>首頁</title>
    <style type="text/css">
    </style>
</head>
    <body>
        <div id="wrapper">
	        <nav class="navbar-default navbar-static-side" role="navigation">
	        	<div class="sidebar-collapse">
	                <ul class="nav metismenu" id="side-menu">
	                    <li class="nav-header">
	                        <div class="dropdown profile-element"> 
	                        	<span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
	                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">ADMIN</strong>
	                             </span></span> </a>
	                        </div>
	                    </li>
	                    <li class="active">
	                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">文章管理</span> <span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li class="active"><a href="index.html">文章上传(患者)</a></li>
	                            <li><a href="dashboard_2.html">文章列表(患者)</a></li>
	                            <li><a href="dashboard_3.html">文章上传(医生)</a></li>
	                            <li><a href="dashboard_4_1.html">文章列表(医生)</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">患者管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">患者列表</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-pie-chart"></i><span class="nav-label">医生管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">医生列表</a></li>
	                            <li><a href="toastr_notifications.html">诊后心得</a></li>
	                            <li><a href="toastr_notifications.html">学术支持</a></li>
	                            <li><a href="toastr_notifications.html">新建学术活动</a></li>
	                            <li><a href="toastr_notifications.html">病例精析</a></li>
	                            <li><a href="toastr_notifications.html">新建病例精析</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-edit"></i><span class="nav-label">审核认证管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">医生认证审核</a></li>
	                            <li><a href="toastr_notifications.html">银行绑定认证</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-desktop"></i><span class="nav-label">帖子管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">帖子列表(患者)</a></li>
	                            <li><a href="toastr_notifications.html">新建帖子(患者)</a></li>
	                            <li><a href="toastr_notifications.html">帖子列表(医生)</a></li>
	                            <li><a href="toastr_notifications.html">新建帖子(医生)</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-files-o"></i><span class="nav-label">心愿管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">心愿列表</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-flask"></i><span class="nav-label">设置</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="toastr_notifications.html">修改密码</a></li>
	                            <li><a href="toastr_notifications.html">意见反馈</a></li>
	                        </ul>
	                    </li>
	                </ul>
	            </div>
	        </nav>
	        <div id="page-wrapper" class="gray-bg dashbard-1">
		        <div class="row border-bottom">
			        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
			        <div class="navbar-header">
			            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
			            <form role="search" class="navbar-form-custom" action="search_results.html">
			                <div class="form-group">
			                    <input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
			                </div>
			            </form>
			        </div>
		        </div>
	         </div>
        </div>
	</body>
</html>