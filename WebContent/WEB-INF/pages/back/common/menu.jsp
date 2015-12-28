<%@ page language="java" pageEncoding="utf-8"%>
        <div id="wrapper">
	        <nav class="navbar-default navbar-static-side" role="navigation">
	        	<div class="sidebar-collapse">
	                <ul class="nav metismenu" id="side-menu">
	                    <li class="active">
	                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">文章管理</span> <span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="${ctx }/back/article/view/patient/add">文章上传(患者)</a></li>
	                            <li><a href="${ctx }/back/article/list/2">文章列表(患者)</a></li>
	                            <li><a href="${ctx }/back/article/view/doctor/add">文章上传(医生)</a></li>
	                            <li><a href="${ctx }/back/article/list/1">文章列表(医生)</a></li>
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
	                    <li>
	                        <a href="#"><i class="fa fa-flask"></i><span class="nav-label">接口管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="${ctx }/back/interface/category/list">接口分类</a></li>
	                            <li><a href="${ctx }/back/interface/view/list">接口管理</a></li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-flask"></i><span class="nav-label">更多</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level collapse">
	                            <li><a href="${ctx }/back/other/bannerSlide/list">轮播图</a></li>
	                        </ul>
	                    </li>
	                </ul>
	            </div>
	        </nav>
        </div>
