<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>文章上传(医生)</title>
</head>
<style type="text/css">
	#content{}
</style>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>文章上传(医生)</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
				<div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form class="form-horizontal" id="articleform" method="post" action="${ctx }/back/article/doctor/update" enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${id }"/>
                                <div class="form-group">
                                	<label class="col-lg-2 control-label">文章分类</label>
                                    <div class="col-lg-2">
	                                    <div class="form-group">
		                                    <c:if test="${article.category == 1 }">不跳转</c:if>
	                                    	<c:if test="${article.category == 2 }">首页轮播图</c:if>
	                                    	<c:if test="${article.category == 3 }">医疗动态</c:if>
	                                    	<c:if test="${article.category == 4 }">轻松一刻</c:if>
	                                    	<c:if test="${article.category == 5 }">首页H6连接</c:if>
                                    	</div>
                                    	</div>
                                	<label class="col-lg-1 control-label">发布时间</label>
                                    <div class="col-lg-2">
                                    	<div class="form-group">
			                                ${article.postTime }
			                            </div>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">文章来源</label>
                                    <div class="col-lg-6">${article.resource }</div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">封面图</label>
                                    <div class="col-lg-6">
                                    	<img width="100" height="100" src="${article.coverImageUrl }">
                                    </div>
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">文章标题</label>
                                    <div class="col-lg-6">${article.title }</div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">视频地址</label>
                                    <div class="col-lg-6">${article.videoUrl }</div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">首页H5链接</label>
                                    <div class="col-lg-6">${article.h5Url }</div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">链接地址</label>
                                   	<div class="col-lg-6">
                                   			<c:if test="${article.linkType == 1 }">不跳转</c:if>
	                                    	<c:if test="${article.linkType == 2 }">H5小游戏</c:if>
	                                    	<c:if test="${article.linkType == 3 }">链接优酷视频</c:if>
	                                 </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">正文</label>
                                   	<div class="col-lg-6" id="contentDiv">
                                   		${article.content}
                                   	</div>
                                </div>
                            </form>
                        </div>
                    </div>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
	
</script>
</html>