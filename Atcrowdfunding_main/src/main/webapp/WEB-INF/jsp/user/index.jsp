<%--
  Created by IntelliJ IDEA.
  User: 10369
  Date: 2019/5/30
  Time: 13:29
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
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" id="queryText" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" id="queryBtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteBathById()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="allChecked"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <tbody id="tobody">


                            </tbody>

                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination" id="ul">


                                    </ul>
                                </td>
                            </tr>
                            </tfoot>

                        </table>
                    </div>
                </div>
            </div>
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
        queryUser(1);
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });

    function pageChange(pageno) {
        //window.location.href="${APP_PATH}/user/index.do?pageno="+pageno ;
        queryUser(pageno);
    }

    var loadingIndex = -1;
    var jsonContent = {
        "pageno":1,
        "pagesize":10
    };
    function queryUser(pageno) {
        jsonContent.pageno = pageno;
        $.ajax({
            type:"POST",
            data:jsonContent,
            url:"${APP_PATH}/user/doIndex.do",
            beforeSend:function(){
                loadingIndex = layer.load(2,{time:10*1000});
                return true;
            },
            success:function(result){
                layer.close(loadingIndex);

                if ( result.successful){
                    var page = result.page;
                    var data = page.datas;

                    var content = "";

                    //i是索引,从0开始,n是data中的每一项
                    $.each(data,function (i,n) {
                        content+='<tr>';
                        content+='<td>'+(i+1)+'</td>';
                        content+='<td><input type="checkbox" id="'+n.id+'" name="'+n.loginacct+'"></td>';
                        content+='<td>'+n.loginacct+'</td>';
                        content+='<td>'+n.username+'</td>';
                        content+=' <td>'+n.email+'</td>';
                        content+='<td>';
                        content+='<button type="button" onclick="window.location.href=\'${APP_PATH}/user/assignRole.htm?id='+n.id+' \'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content+='<button type="button" onclick="window.location.href=\'${APP_PATH}/user/toUpdate.htm?id='+n.id+' \'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content+='<button type="button" onclick="DeleteUserById('+n.id+',\''+n.loginacct+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content+='</td>';
                        content+='</tr>';
                    });
                    $("#tobody").html(content);

                    var contentBar = "";
                    if (page.pageno==1){
                        contentBar+='<li class="disabled"><a href="#">上一页</a></li>';
                    } else {
                        contentBar+='<li><a href="javascript:void(0)" onclick="pageChange('+(page.pageno-1)+')">上一页</a></li>';
                    }
                    for (var i = 1;i<=page.totalno;i++){
                        contentBar+='<li ';
                        if (page.pageno==i){
                            contentBar+=' class="active" ';
                        }
                        contentBar+=' ><a href="javascript:void(0)" onclick="pageChange('+i+')">'+i+'</a></li> ';
                    }
                    if (page.pageno==page.totalno) {
                        contentBar+=' <li class="disabled"><a href="#">下一页</a></li>';
                    }else {
                        contentBar+='<li><a href="javascript:void(0)" onclick="pageChange('+(page.pageno+1)+')">下一页</a></li>';
                    }
                    $("#ul").html(contentBar);
                }
            },
            error:function(){
                layer.msg(result.messages, {time:1000, icon:5, shift:6});
            },
            dataType:"json"
        });
    }

    $("#queryBtn").click(function () {
        var queryText = $("#queryText").val();
        jsonContent.queryText = queryText;
        //alert(queryText)
        queryUser(1);
    })


    function DeleteUserById(id,loginacct) {

        layer.confirm("您确定删除["+loginacct+"]用户吗?",  {icon: 3, title:'提示'}, function(cindex){
            $.ajax({
                type:"POST",
                data:{
                    id:id
                },
                url:"${APP_PATH}/user/deleteUserById.do?id="+id,
                before:function () {
                    return true ;
                },
                success:function (result) {
                    if (result.successful) {
                        window.location.href="${APP_APTH}/user/index.htm" ;
                    }
                }

            });
            layer.close(cindex);
        }, function(cindex){
            layer.close(cindex);
        });



    }

    $("#allChecked").click(function () {
        var checkBoxStatus = this.checked ;
        //alert(checkBoxStatus) ;
       // $("tbody tr td input[type='checkbox']").attr("checked",checkBoxStatus) ;
        //$("tbody tr td input[type='checkbox']").prop("checked",checkBoxStatus) ;
        var checked = $("tbody tr td input[type='checkbox']");
        $.each(checked,function (i,n) {
            n.checked=checkBoxStatus;
        })
    });

    function deleteBathById() {
        var ids = $("tbody tr td input:checked") ;

        if (ids.length==0){
            layer.msg("至少勾选一位用户!", {time:1000, icon:5, shift:6});
            return false;
        }

       /* var content = "" ;
        $.each(ids,function (i,n) {
            //alert(n.id)
            //?id=1&id=2&id=3
            if (i!=0){
                content+="&"
            }
            content+="id="+n.id
        }) ;*/
       // alert(content) ;
        var dataObj = {};
        $.each(ids,function (i,n) {
            dataObj["datas["+i+"].id"] = n.id;
            dataObj["datas["+i+"].loginacct"] = n.name;
        })

        layer.confirm("您确定删除这些用户吗?",  {icon: 3, title:'提示'}, function(cindex){
            $.ajax({
                type:"POST",
                //data:content,
                data:dataObj,
                url:"${APP_PATH}/user/deleteBathById.do" ,
                beforeSend:function () {
                    return true ;
                },
                success:function (result) {
                    if (result.successful){
                        window.location.href="${APP_PATH}/user/index.htm" ;
                    }else {
                        layer.msg(result.messages, {time:1000, icon:5, shift:6});
                    }
                },
                error:function () {
                    layer.msg(result.messages, {time:1000, icon:5, shift:6});
                }
            });
            layer.close(cindex);
        }, function(cindex){
            layer.close(cindex);
        });

    }






   /* var loadingIndex = -1;
    function queryUser(pageno) {
        $.ajax({
            type:"POST",
            data:{
                "pageno":pageno,
                "pagesize":10
            },
            url:"${APP_PATH}/user/index.do",
            beforeSend:function(){
                loadingIndex = layer.load(2,{time:10*1000});
                return true;
            },
            success:function(result){
                layer.close(loadingIndex);

                if ( result.successful){
                    var page = result.page;
                    var data = page.datas;

                    var content = "";



                    <%--分页查询--%>
                    <%-- <c:forEach items="${page.datas}" var="user" varStatus="status">
                         <tr>
                             <td>${status.count}</td>
                             <td><input type="checkbox"></td>
                             <td>${user.loginacct}</td>
                             <td>${user.username}</td>
                             <td>${user.email}</td>
                             <td>
                                 <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                 <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                 <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                             </td>
                         </tr>
                     </c:forEach>--%>
                    //i是索引,从0开始,n是data中的每一项
                    $.each(data,function (i,n) {
                        content+='<tr>';
                        content+='<td>'+(i+1)+'</td>';
                        content+='<td><input type="checkbox"></td>';
                        content+='<td>'+n.loginacct+'</td>';
                        content+='<td>'+n.username+'</td>';
                        content+=' <td>'+n.email+'</td>';
                        content+='<td>';
                        content+='<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content+='<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content+='<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content+='</td>';
                        content+='</tr>';
                    });
                    $("#tobody").html(content);

                    var contentBar = "";
                    if (page.pageno==1){
                        contentBar+='<li class="disabled"><a href="#">上一页</a></li>';
                    } else {
                        contentBar+='<li><a href="javascript:void(0)" onclick="pageChange('+(page.pageno-1)+')">上一页</a></li>';
                    }
                    for (var i = 1;i<=page.totalno;i++){
                        contentBar+='<li ';
                        if (page.pageno==i){
                            contentBar+=' class="active" ';
                        }
                        contentBar+=' ><a href="javascript:void(0)" onclick="pageChange('+i+')">'+i+'</a></li> ';
                    }
                    if (page.pageno==page.totalno) {
                        contentBar+=' <li class="disabled"><a href="#">下一页</a></li>';
                    }else {
                        contentBar+='<li><a href="javascript:void(0)" onclick="pageChange('+(page.pageno+1)+')">下一页</a></li>';
                    }
                    $("#ul").html(contentBar);
                    <%--分页导航条--%>
                    <%--     <c:if test="${page.pageno==1}">
                             <li class="disabled"><a href="#">上一页</a></li>
                         </c:if>
                         <c:if test="${page.pageno!=1}">
                             <li><a href="javascript:void(0)" onclick="pageChange(${page.pageno-1})">上一页</a></li>
                         </c:if>




                        <c:forEach begin="1" end="${page.totalno}" var="num">
                            <li
                                <c:if test="${page.pageno==num}">
                                    class="active"
                                </c:if>
                            ><a href="javascript:void(0)" onclick="pageChange(${num})">${num}</a></li>
                        </c:forEach>

                        <c:if test="${page.pageno==page.totalno}">
                            <li class="disabled"><a href="#">下一页</a></li>
                        </c:if>
                       <c:if test="${page.pageno!=page.totalno}">
                           <li><a href="javascript:void(0)" onclick="pageChange(${page.pageno+1})">下一页</a></li>
                       </c:if>--%>

                }
            },
            error:function(){
                layer.msg(result.messages, {time:1000, icon:5, shift:6});
            },


            dataType:"json"
        });
    }*/

</script>
</body>
</html>

