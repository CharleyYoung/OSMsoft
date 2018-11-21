<%--
    Created by IntelliJ IDEA.
    User: Taiho
    Date: 2018/11/15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
<head>
    <title>管理员工信息</title>
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
                    <li><a href="AdminHomepage.jsp" class="collapsed"><i class=""></i> <span>个人信息</span></a></li>
                    <li><a href="#subPages1" data-toggle="collapse" class="active"><i class=""></i>
                        <span>员工管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages1" class="collapse ">
                            <ul class="nav">
                                <li><a href="AddEmployee.jsp" class="">添加员工</a></li>
                                <li><a href="OperateEmployeeForAdmin.jsp" class="active">管理员工信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subPages2" data-toggle="collapse" class="collapsed"><i class=""></i>
                            <span>薪酬管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages2" class="collapse">
                            <ul class="nav">
                                <li><a href="AdminSalary.jsp" class="">查询工资</a></li>
                                <li><a href="AdminSalary2.jsp" class="">管理工资信息</a></li>
                            </ul>
                        </div>
                    </li>

                    <!--显示树状部门列表，由saulzhang维护-->
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
                                                <%--<c:out value="/depEmployee.do?depid=${dep.id}&departmentName=${dep.name}"></c:out>--%>
                                            <a href="<c:url value='/depEmployeeInfo?depid=${dep.id}&departmentName=${dep.name}'/>">
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
                                                        <c:if test="${secondChild.parentId != '0' and  secondChild.url != 'no resources'}">
                                                            <li>
                                                                <a href="<c:url value='depEmployeeInfo?depid=${secondChild.id}&departmentName=${secondChild.name}'/>">${secondChild.name }</a>
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
                                                                                <a href="<c:url value='/depEmployeeInfo?depid=${thirdChild.id}&departmentName=${thirdChild.name}'/>">${thirdChild.name }</a>
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


                    <li><a href="https://blog.csdn.net/qq_37053885/article/details/84262573" class="collapsed"><i
                            class="collapsed"></i><span>帮助</span></a></li>
                    <li><a href="#" onclick="logout()" class="collapsed"><i class="collapsed"></i> <span>退出登录</span></a>
                    </li>
                    </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title"> 管理员工信息</h3>
                <div class="row">
                    <div class="col-md-12">
                        <!-- INPUTS -->
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">搜索员工</h3>
                            </div>

                            <form method="POST" action="SearchEmployeeForOperate">
                                <div class="panel-body">
                                    <div class="col-md-12">
                                        <select id="input" name="style" title="请选择搜索模式">
                                            <option value="id">员工ID</option>
                                            <option value="name">员工姓名</option>
                                            <option value="workage">工龄</option>
                                            <option value="age">年龄</option>
                                            <option value="gender">性别</option>
                                            <option value="department">部门</option>
                                            <option value="job">职位</option>
                                        </select>
                                        <br>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                            <input class="form-control" type="text" placeholder="输入关键词" name="keyWord">
                                            <span class="input-group-btn"><button class="btn btn-primary" type="SUBMIT">搜索</button></span>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">搜索结果</h3>
                            </div>
                            <div class="panel-body no-padding">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>员工ID</th>
                                        <th>员工姓名</th>
                                        <th>工龄</th>
                                        <th>年龄</th>
                                        <th>性别</th>
                                        <th>电话号码</th>
                                        <th>电子邮箱</th>
                                        <th>职位</th>
                                        <th>所属部门</th>
                                    </tr>
                                    </thead>

                                    <!--用jstl获取查询结果-->
                                    <c:set var="employeeList" scope="session" value="${searchResult}"></c:set>

                                    <tbody>
                                    <c:forEach var="items" items="${employeeList}">
                                        <c:set var="item" scope="session" value="${items}"></c:set>
                                        <tr>
                                            <td>${item.getEmployeeID()}</td>
                                            <td>${item.getName()}</td>
                                            <td>${item.getWorkAge()}</td>
                                            <td>${item.getAge()}</td>
                                            <td>${item.getGender()}</td>
                                            <td>${item.getPhoneNumber()}</td>
                                            <td>${item.getEmail()}</td>
                                            <td>${item.getJob()}</td>
                                            <td>${item.getDepartmentName()}</td>
                                            <th>
                                                <button type="submit" class="btn btn-primary"
                                                        onclick="Update(${item.getEmployeeID()})"><i
                                                        class="fa fa-refresh"></i> 更新信息
                                                </button>
                                            </th>
                                            <th>
                                                <button type="submit" class="btn btn-danger"
                                                        onclick="Delete(${item.getEmployeeID()})"><i
                                                        class="fa fa-refresh"></i> 删除
                                                </button>
                                            </th>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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
<script type="text/javascript">
    function Delete(item) {
        var result = confirm("确定要删除这个员工吗？");
        if (result == true) {
            window.location.href = "DeleteEmployee?employee=" + item;
        } else {

        }
    }
</script>
<script type="text/javascript">
    function Update(item) {
        var result = confirm("更新该员工信息？");
        if (result == true) {
            window.location.href = "DisplayForUpdate?employee=" + item;
        } else {

        }
    }
</script>
</body>

</html>