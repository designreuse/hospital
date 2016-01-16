<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>兑换记录</title>
<style>
	.show-grid [class^="col-"] {
	    border: none;
	    background-color: #F3F3F4 !important;
    }
</style>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>兑换记录</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
    <div class="col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h2>用户名：${username }</h2>
                </div>
                <div class="ibox-content">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                             <th>对换时间</th>
	                         <th>兑换积分</th>
	                         <th>等值金额</th>
	                         <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                       <c:forEach items="${list }" var="history">
                     <tr>
                         <td>${history.exchageTime }</td>
                         <td>${history.score }</td>
                         <td>${history.money }</td>
                         <td></td>
                     </tr>
                    </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>s
    </div>
</div>
</body>
</html>