<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<style type="text/css">
.nav > li.active {
   border-left:none;
}
</style>
<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">
<c:if test="${iItem.method != 'multipart' }">
<form id="interfaceForm" action="${ctx }${iItem.name}" method="${iItem.method }">
  <input type="hidden" id="url" value="${ctx }${iItem.name}">
  <p>说明：${iItem.itemDesc}</p>
  <div class="form-group">
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="get" <c:if test="${iItem.method =='get'}"> checked </c:if> > get
    </label>
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="post" <c:if test="${iItem.method =='post'}"> checked </c:if> > post
    </label>
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="post" <c:if test="${iItem.method =='multipart'}"> checked </c:if> > multipart
    </label>
  </div>
  
  <c:forEach var="field" items="${fields }">
    <div class="form-group">
      <label for="mobile">
      	<c:if test="${field.isRequired == '1' }">＊</c:if>
      	${field.label }（${field.name }）：${field.description }</label>
      	
	  <input type="text" class="input-sm form-control <c:if test="${field.isRest == 1 }">rest</c:if>" id="${field.name }" name="${field.name }" >
    </div>
  </c:forEach>
  
  <button type="button" class="btn btn-primary" id="submitButton" onclick="submitParameters();" >调用接口</button><br/><br/>
  
  <p class="bg-info">错误返回值说明</p>
  <p>${iItem.errorDesc }</p>
</form>
</c:if>
<%-- <c:if test="${iItem.method == 'multipart' }">
  <p>说明：${iItem.itemDesc}</p>
  <div class="form-group">
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="get" <c:if test="${iItem.method =='get'}"> checked </c:if> > get
    </label>
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="post" <c:if test="${iItem.method =='post'}"> checked </c:if> > post
    </label>
    <label class="radio-inline">
	  <input name="requestMethod" type="radio" value="post" <c:if test="${iItem.method =='multipart'}"> checked </c:if> > multipart
    </label>
  </div>
  <p class="bg-info">错误返回值说明</p>
  <p>${iItem.errorDesc }</p>
</c:if> --%>