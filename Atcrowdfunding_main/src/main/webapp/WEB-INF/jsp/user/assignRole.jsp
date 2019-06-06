<%--
  Created by IntelliJ IDEA.
  User: 10369
  Date: 2019/6/4
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="/WEB-INF/jsp/common/main.jsp"></jsp:include>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
               <jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select id="leftAssign" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                               <c:forEach items="${leftRole}" var="role">
                                   <option value="${role.id}">${role.name}</option>
                               </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select id="rightAssign" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${rightRole}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
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
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
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
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
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
    });

    $("#leftToRightBtn").click(function () {
        var left = $("#leftAssign option:selected");
        var jsonObj = {};
        jsonObj.userid = "${param.id}" ;
        $.each(left,function (i,n) {
            jsonObj["ids["+i+"]"] =this.value  ;
            //alert(jsonObj);
        })

        var index = -1 ;
        $.ajax({
            type:"POST",
            data:jsonObj,
            url:"${APP_PATH}/user/doAddAssign.do",
            beforeSend:function(){
                index = layer.load(2, {time: 10*1000});
                return true ;
            },
            success:function (result) {
                if (result.successful){
                  layer.close(index) ;
                    $("#rightAssign").append(left.clone());
                    left.remove();
                } else {
                    layer.msg(result.message, {time:1000, icon:5, shift:6});
                }
            },
            error:function () {
                layer.msg("分配失败!", {time:1000, icon:5, shift:6});
            }
        });
    });
    $("#rightToLeftBtn").click(function () {
        var right = $("#rightAssign option:selected");

        var jsonObj = {} ;
        jsonObj.userid = "${param.id}" ;
        $.each(right,function (i,n) {
            jsonObj["ids["+i+"]"] = this.value;
        })

        var index = -1 ;
        $.ajax({
            type:"POST",
            data:jsonObj,
            url:"${APP_PATH}/user/deleteAssign.do",
            beforeSend:function () {
                index = layer.load(2, {time: 10*1000});
                return true ;
            },
            success:function (result) {
                if (result.successful){
                    layer.close(index) ;
                    $("#leftAssign").append(right.clone());
                    right.remove();
                } else {
                    layer.msg(result.message, {time:1000, icon:5, shift:6});
                }
            },
            error:function () {
                layer.msg("分配失败!", {time:1000, icon:5, shift:6});
            }
        });

    });


</script>
</body>
</html>

