<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>修改病例精析</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>修改病例精析</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
              <div class="ibox float-e-margins">
                  <div class="ibox-content">
                      <form id="caseAnalysisForm" class="form-horizontal" method="post" action="${ctx }/back/doctor/updateCaseAnalysis" enctype="multipart/form-data">
                          <input type="hidden" name="id" value="${detail.id }" />
                          <div class="form-group"><label class="col-lg-2 control-label">医生名称：</label>
                              <div class="col-lg-5"><input type="text" name="doctorName" value="${detail.doctorName }"   class="form-control">  
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">专家类别：</label>
                              <div class="col-lg-5">
                              	<select class="form-control m-b" name="eliteType">
	                                 <option value="1">金牌专家</option>
	                                 <option value="2">普通专家</option>
	                             </select>
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">医院：</label>
                              <div class="col-lg-5"><input type="text" value="${detail.hospital }"  name="hospital" class="form-control">  
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">标题：</label>
                              <div class="col-lg-5"><input type="text" name="title"  value="${detail.title }"  class="form-control">  
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">发布时间：</label>
                              <div class="col-lg-5"><input type="text" name="postTime"  value="${detail.postTime }"  class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">  
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">封面图：</label>
                              <div class="col-lg-5">
                              <img alt="" src="${detail.illCaseImage }">
                              <input type="file" name="imageFile"   class="form-control">  
                              </div>
                          </div>
                          <div class="form-group"><label class="col-lg-2 control-label">解读病例：</label>
                              <div class="col-lg-9" id="contentDiv">
                                   	
                              </div>
                              <input type="hidden" id="analysis" name="analysis"/>
                          </div>
                          <div class="form-group">
                              <div class="col-lg-offset-2 col-lg-10">
                                  <button type="button" id="btnPost" class="btn btn-w-m btn-success">提交</button>
                              </div>
                          </div>
                      </form>
                  </div>
              </div>
            </div>
        </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		 var ue = UE.getEditor('contentDiv',{
	         initialFrameHeight:700
	     })
	     ue.addListener("ready", function () {
	        ue.setContent('${detail.analysis }');
	     });
	     $("#btnPost").on("click",function(){
	 		$("#analysis").val(UE.getEditor('contentDiv').getContent());
	 		$("#caseAnalysisForm").submit();
	 	});
	});
	
</script>
</html>