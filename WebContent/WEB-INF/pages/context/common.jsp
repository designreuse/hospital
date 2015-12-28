<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/static/font-awesome/css/font-awesome.css" rel="stylesheet">
<!-- Toastr style -->
<link href="${ctx }/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Gritter -->
<link href="${ctx }/static/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
<link href="${ctx }/static/css/animate.css" rel="stylesheet">
<link href="${ctx }/static/css/style.css" rel="stylesheet">

<link href="${ctx }/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

<!-- Mainly scripts -->
<script src="${ctx }/static/js/jquery-2.1.1.js"></script>
<script src="${ctx }/static/js/bootstrap.min.js"></script>
<script src="${ctx }/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx }/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Flot -->
<script src="${ctx }/static/js/plugins/flot/jquery.flot.js"></script>
<script src="${ctx }/static/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${ctx }/static/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="${ctx }/static/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="${ctx }/static/js/plugins/flot/jquery.flot.pie.js"></script>
<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${ctx }/static/js/demo/peity-demo.js"></script>
<!-- Custom and plugin javascript -->
<script src="${ctx }/static/js/inspinia.js"></script>
<script src="${ctx }/static/js/plugins/pace/pace.min.js"></script>
<!-- jQuery UI -->
<script src="${ctx }/static/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- GITTER -->
<script src="${ctx }/static/js/plugins/gritter/jquery.gritter.min.js"></script>
<!-- Sparkline -->
<script src="${ctx }/static/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- Sparkline demo data  -->
<script src="${ctx }/static/js/demo/sparkline-demo.js"></script>
<!-- ChartJS-->
<script src="${ctx }/static/js/plugins/chartJs/Chart.min.js"></script>
<!-- Toastr -->
<script src="${ctx }/static/js/plugins/toastr/toastr.min.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/static/css/plugins/jqGrid/ui.jqgrid.css" >
<script src="${ctx }/static/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx }/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx }/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${ctx }/static/js/common.js"></script>


<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/plugins/UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/plugins/UEditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/plugins/UEditor/lang/zh-cn/zh-cn.js"></script>
    