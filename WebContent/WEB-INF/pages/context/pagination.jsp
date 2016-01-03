<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="pull-right" style="margin:0 20px 10px 0;" id="page-right">
<span>	
		<a href="javascript:void('0')" style="font-weight: bolder;">共<c:if test="${!empty page.total and page.total ne 0}">${page.total}</c:if>条</a>
	</span>
	<c:if test="${page.pageNo eq 1 or page.pageNo eq 0 or empty page.pageNo}">
		<span><a href="javascript:void('0')" style="color: gray; font-weight: bolder;">上一页</a></span>
	</c:if>
	<c:if test="${page.pageNo gt 1}">
		<span><a href="javascript:void('0')" style="font-weight: bolder;" onclick="pagination('${param.url}','${page.pageNo-1}','${page.pageSize}')">上一页</a></span>
	</c:if>
	<c:forEach begin="${page.pageData.startPageNo}" end="${page.pageData.endPageNo }" varStatus="stat">
		<span><c:if test="${stat.index eq page.pageNo }">
				<a href="javascript:void('0')" style="color: red; font-weight: bold;">${stat.index }</a>
			</c:if> <c:if test="${stat.index ne page.pageNo and !empty page.pageNo}">
				<a href="javascript:void('0')" onclick="pagination('${param.url}','${stat.index}','${page.pageSize}')">${stat.index }</a>
			</c:if></span>
	</c:forEach>
	<c:if test="${page.totalPageNo eq 0 or page.pageNo eq page.totalPageNo }">
		<span><a href="javascript:void('0')" style="color: gray; font-weight: bolder;">下一页</a></span>
	</c:if>
	<c:if test="${page.pageNo lt page.totalPageNo }">
		<span><a href="javascript:void('0')" style="font-weight: bolder;" onclick="pagination('${param.url}','${page.pageNo+1}','${page.pageSize}')">下一页</a></span>
	</c:if>
</div>
