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
                            <form class="form-horizontal" id="articleform" method="post" action="${ctx }/back/article/update" enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${id }"/>
                                <div class="form-group">
                                	<label class="col-lg-2 control-label">文章分类</label>
                                    <div class="col-lg-2">
                                    	<select class="form-control m-b" name="category" id="category">
	                                        <option value="0">-请选择-</option>
	                                        <option value="1">不跳转</option>
	                                        <option value="2">首页轮播图</option>
	                                        <option value="3">医疗动态</option>
	                                        <option value="4">轻松一刻</option>
	                                        <option value="5">首页H6连接</option>
	                                    </select>
                                    </div>
                                	<label class="col-lg-1 control-label">发布时间</label>
                                    <div class="col-lg-2">
                                    	<div class="form-group" id="datePick">
			                                <div class="input-group date">
			                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			                                    <input type="text" class="form-control" name="postTime" id="postTime" value="${article.postTime }">
			                                </div>
			                            </div>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">文章来源</label>
                                    <div class="col-lg-6"><input type="text" name="resource" class="form-control" value="${article.resource }"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">封面图</label>
                                    <div class="col-lg-6">
                                    	<img width="100" height="100" src="${article.coverImageUrl }">
                                   	 	<input type="file" name="coverImage" class="form-control">
                                    </div>
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">文章标题</label>
                                    <div class="col-lg-6"><input type="text" name="title" class="form-control" value="${article.title }"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">视频地址</label>
                                    <div class="col-lg-6"><input type="text" name="videoUrl" class="form-control" value="${article.videoUrl }"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">首页H5链接</label>
                                    <div class="col-lg-6"><input type="text" name="H5Url" class="form-control" value="${article.h5Url }"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">链接地址</label>
                                   	<div class="col-lg-6">
	                                   	<select class="form-control m-b" name="linkType" id="linkType">
	                                        <option value="0">-请选择-</option>
	                                        <option value="1">不做跳转</option>
	                                        <option value="2">H5小游戏</option>
	                                        <option value="3">链接优酷视频</option>
	                                    </select>
	                                 </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">正文</label>
                                   	<div class="col-lg-6" id="contentDiv">
                                   	
                                   	</div>
                                   	<input type="hidden" id="content" name="content"/>
                                   	<input type="hidden" id="type" name="type"/>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button class="btn btn-success col-lg-2" type="button" id="btnPost">提交</button>
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
	$(function(){
		
		UE.getEditor('contentDiv',{
	         //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
	         toolbars:[['FullScreen', 'Undo', 'Redo','Bold','underline','fontsize','fontfamily','forecolor','insertimage','indent','lineheight','ertorderedlist','insertunorderedlist','link','unlink','rowspacing','date','time']],
	         //focus时自动清空初始化时的内容
	         autoClearinitialContent:true,
	         //关闭字数统计
	         wordCount:true,
	         //关闭elementPath
	         elementPathEnabled:false,
	         //默认的编辑区域高度
	         initialFrameHeight:500
	     });
		$('#datePick .input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: true,
            forceParse: true,
            calendarWeeks: true,
            autoclose: true
        });
		
		$("#btnPost").on("click",function(){
			$("#content").val(UE.getEditor('contentDiv').getContent());
			$("#type").val("1");
			$("#articleform").submit();
		});
		var linkType = '${article.linkType}';
		var category = '${article.category}';
		$("#linkType").find("option").each(function(){
			if($(this).val()==linkType){
				$(this).attr("selected",true);
			}
		});
		$("#category").find("option").each(function(){
			if($(this).val()==linkType){
				$(this).attr("selected",true);
			}
		});
	});
</script>
</html>