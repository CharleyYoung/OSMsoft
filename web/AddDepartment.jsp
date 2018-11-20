<%--
    Created by IntelliJ IDEA.
    User: YocLu
    Date: 2018/11/19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
<head>
    <title>Add Department</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <!--
        <div class="brand">
            <a href="index.jsp"><img src="assets/img/BiblioSoft Logo.png" alt="BiblioSoft Logo" class="img-responsive logo"></a>
        </div>
        -->
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth">
                    <i class="lnr lnr-arrow-left-circle"></i>
                </button>
            </div>
            <form class="navbar-form navbar-left"></form>
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="assets/img/Taiho.png" class="img-circle" alt="Avatar">
                        <span>${sessionScope.Account}</span></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="AdminHomepage.jsp" class="active"><i class=""></i> <span>个人信息</span></a></li>

                    <li><a href="#subPages1" data-toggle="collapse" class="collapsed"><i class=""></i>
                        <span>员工管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages1" class="collapse ">
                            <ul class="nav">
                                <li><a href="AddEmployee.jsp" class="">添加员工</a></li>
                                <li><a href="OperateEmployeeForAdmin.jsp" class="">管理员工信息</a></li>
                            </ul>
                        </div>
                    </li>

                    <li>
                        <a href="#subPages2" data-toggle="collapse" class="collapsed"><i class=""></i>
                            <span>薪酬管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages2" class="collapse">
                            <ul class="nav">
                                <li><a href="" class="">查询工资</a></li>
                                <li><a href="" class="">添加工资信息</a></li>
                            </ul>
                        </div>
                    </li>

                    <!--Change by saulzhang，部门管理树状由saulzhang维护-->

                    <li><a href="##subPages3" data-toggle="collapse" class="collapsed"><i
                            class="images/dep.png fa-fw"></i>
                        <span>部门管理</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages3" class="collapse ">
                            <ul class="nav">
                                <li><a href="ManageDepartmentInfo.jsp" class="">管理部门信息</a></li>
                                <li><a href="AddDepartment.jsp" class="">添加部门</a></li>
                                <c:set var="depList" value="${depList}"/>
                                <c:forEach items="${depList}" var="dep" varStatus="status">
                                    <!-- 一级子菜单没有parentId,有url -->
                                    <c:if test="${ dep.parentId eq '0' and not dep.url eq 'no resources'}">
                                        <li>
                                            <a href="<c:url value='${dep.url }'/>">
                                                <i class="${dep.icon } fa-fw"></i> ${dep.name }
                                            </a>
                                        </li>
                                    </c:if>
                                    <!-- 可展开的一级菜单，没有parentId,有url -->
                                    <c:if test="${dep.parentId eq '0' and  dep.url eq 'no resources'}">
                                        <li>
                                            <a href="#subPages3-${status.count+1000}" data-toggle="collapse"
                                               class="collapsed">
                                                <i class="${dep.icon } fa-fw"></i> ${dep.name }<span
                                                    class="fa arrow"></span>
                                                <i class="icon-submenu lnr lnr-chevron-left"></i>
                                            </a>
                                            <div id="subPages3-${status.count+1000}" class="collapse ">
                                                <ul class="nav nav-second-level">

                                                    <c:forEach items="${dep.children}" var="secondChild"
                                                               varStatus="status">
                                                        <!-- 有url的没有子菜单直接输出到li中，没有url的是可扩展二级菜单 -->
                                                        <c:if test="${  secondChild.parentId != '0' and  secondChild.url != 'no resources'}">
                                                            <li>
                                                                <a href="<c:url value='${secondChild.url }'/>">${secondChild.name }</a>
                                                            </li>
                                                        </c:if>
                                                        <!-- 二级菜单url为空，表示还有三级菜单 -->
                                                        <c:if test="${secondChild.url eq 'no resources' }">
                                                            <li>
                                                                <a href="#subPages3-${status.count+500}"
                                                                   data-toggle="collapse" class="collapsed">
                                                                        ${secondChild.name }<span
                                                                        class="fa arrow"></span><i
                                                                        class="icon-submenu lnr lnr-chevron-left"></i></a>
                                                                <div id="subPages3-${status.count+500}"
                                                                     class="collapse ">
                                                                    <ul class="nav nav-third-level">
                                                                        <c:forEach items="${secondChild.children}"
                                                                                   var="thirdChild" varStatus="status">
                                                                            <li>
                                                                                <a href="<c:url value='${thirdChild.url }'/>">${thirdChild.name }</a>
                                                                            </li>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </div>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>


                    <li><a href="#" class="collapsed"><i class="collapsed"></i><span>帮助</span></a></li>
                    <li><a href="#" onclick="logout()" class="collapsed"><i class="collapsed"></i> <span>退出登录</span></a>
                    </li>
                    </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR. -->
    <!-- END MAIN -->
    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title">添加部门</h3>
                <div class="row">
                    <div class="col-md-12">

                        <!-- INPUTS -->
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">输入</h3>
                            </div>

                            <form method="POST" action="AddDepartment">
                                <div class="panel-body">
                                    <input type="text" class="form-control" placeholder="部门名称" name="depname">
                                    <br>
                                    <input type="text" class="form-control" placeholder="上级部门名称" name="parentdepname">
                                    <br>
                                    <label class="fancy-radio">
                                        <input name="haveson" value="have" type="radio">
                                        <span><i></i>含有子部门</span>
                                    </label>
                                    <br>
                                    <label class="fancy-radio">
                                        <input name="haveson" value="nothave" type="radio">
                                        <span><i></i>不含子部门</span>
                                    </label>
                                    <br>
                                    <p class="demo-button">
                                        <button id="submit" type="submit" class="btn btn-success">添加</button>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END MAIN CONTENT -->
<!-- END MAIN -->
<div class="clearfix"></div>
<footer>
    <div class="container-fluid">
        <p class="copyright">Copyright &copy; 2018.Eagle Jump All rights
            reserved.OSMsoft - Collect from Software</p>

    </div>
</footer>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="assets/vendor/chartist/js/chartist.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script>
    function logout() {
        var result = confirm("确定要登出吗？");
        if (result == true) {
            window.location.href = "DestroySession";
        } else {

        }
    }
</script>
</body>

</html>