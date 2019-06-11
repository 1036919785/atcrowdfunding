<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
	<link rel="stylesheet" href="${APP_PATH }/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
           <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
			<%@include file="/WEB-INF/jsp/common/main.jsp"%>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="panel panel-default">
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
                  <button id="assignPermissionBtn" class="btn btn-success">分配许可</button>
                  <br><br>
                  <ul id="treeDemo" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});

			/*	var setting = {
					view : {
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
							}
						},
						addHoverDom: function(treeId, treeNode){   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							aObj.attr("href", "javascript:;"); // 取消当前链接事件.
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level == 0 ) { //根节点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'" title="增加权限信息" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 1 ) { //分支节点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length == 0) {
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 2 ) { //叶子节点
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"  onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}

							s += '</span>';
							aObj.after(s);
						},
						removeHoverDom: function(treeId, treeNode){
							$("#btnGroup"+treeNode.tId).remove();
						}

					}
				};

				var zNodes =[
					{ name:"父节点1 - 展开", open:true,
						children: [
							{ name:"父节点11 - 折叠",
								children: [
									{ name:"叶子节点111"},
									{ name:"叶子节点112"},
									{ name:"叶子节点113"},
									{ name:"叶子节点114"}
								]},
							{ name:"父节点12 - 折叠",
								children: [
									{ name:"叶子节点121"},
									{ name:"叶子节点122"},
									{ name:"叶子节点123"},
									{ name:"叶子节点124"}
								]},
							{ name:"父节点13 - 没有子节点", isParent:true}
						]},
					{ name:"父节点2 - 折叠",
						children: [
							{ name:"父节点21 - 展开", open:true,
								children: [
									{ name:"叶子节点211"},
									{ name:"叶子节点212"},
									{ name:"叶子节点213"},
									{ name:"叶子节点214"}
								]},
							{ name:"父节点22 - 折叠",
								children: [
									{ name:"叶子节点221"},
									{ name:"叶子节点222"},
									{ name:"叶子节点223"},
									{ name:"叶子节点224"}
								]},
							{ name:"父节点23 - 折叠",
								children: [
									{ name:"叶子节点231"},
									{ name:"叶子节点232"},
									{ name:"叶子节点233"},
									{ name:"叶子节点234"}
								]}
						]},
					{ name:"父节点3 - 没有子节点", isParent:true}

				];
				$.ajax({
					type:"POST",
					url:"${APP_PATH}/permission/initZtree.do",
					success:function (result) {

						//alert("hello")
						var zNodes = result;
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					},
					error:function () {
						alert("查询失败!")
					}
				});
*/







					var setting = {
                                check : {
                                    enable : true  //在树节点前显示复选框
                                },
                                view: {
                                    selectedMulti: false,
                                    addDiyDom: function(treeId, treeNode){
                                        var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                                        if ( treeNode.icon ) {
                                            icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
                                        }
                                    },
                                },
                                async: {
                                    enable: true, //采用异步
                                    url:"${APP_PATH}/role/loadDataAsync.do?roleId=${param.roleId}", // ?id=1&n=xxx&lv=2
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick : function(event, treeId, json) {

						}
					}
				};
				
				//异步加载树:注意问题,服务器端返回的结果必须是一个数组.
				$.fn.zTree.init($("#treeDemo"), setting); //异步加载树的数据.
				//$.fn.zTree.init($("#treeDemo"), setting , ztreeJSON);//同步加载树的数据.
            });
         /*
            $("#assignPermissionBtn").click(function(){
            	
    				var jsonObj = {
    					roleid : ${param.roleid}
    				};
    				
    				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

    				var checkedNodes = treeObj.getCheckedNodes(true); // 获取被选中的节点

    				
    				$.each(checkedNodes,function(i,n){
    					jsonObj["ids["+i+"]"] = n.id;
    				});
    				
    				
    				if(checkedNodes.length == 0){
    					layer.msg("请选择分配许可,至少分配一个许可!", {time:1000, icon:5, shift:6}); 
    				}else{
    					var loadingIndex = -1 ;
    		            $.ajax({
    		            	type : "POST",
    		            	url : "${APP_PATH}/role/doAssignPermission.do",    			     
    		            	data : jsonObj,  
    		            	beforeSend : function(){
    		            		loadingIndex = layer.msg('正在分配许可...', {icon: 16});
    		            		return true ;
    		            	},
    		            	success : function(result){
    		            		
    		            		layer.close(loadingIndex);
    		            		
    		            		if(result.success){	            			
    		            			layer.msg("分配成功", {time:1000, icon:6}); 

    		            		}else{
    		            			layer.msg("分配失败", {time:1000, icon:5, shift:6}); 
    		            		}
    		            		
    		            	},
    		            	error : function(){
    		            		layer.msg("操作失败!", {time:1000, icon:5, shift:6}); 
    		            	}
    		            });

            });
            	}*/
            
        </script>
  </body>
</html>
