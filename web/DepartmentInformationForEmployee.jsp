<%--
  Created by IntelliJ IDEA.
  User: zzh187
  Date: 2018/11/19
  Time: 15:03
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>查看本部门信息</title>
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
                    <li><a href="EmployeeHomepage.jsp" class="active"><i class=""></i> <span>主页</span></a></li>
                    <li><a href="ManageInformation.jsp" class="active"><i class=""></i><span>管理个人信息</span></a></li>
                    <li><a href="PayrollRecordForEmployee.jsp" class="collapsed"><i class="collapsed"></i><span>查看工资记录</span></a></li>
                    <li><a href="DepartmentInformationForEmployee.jsp" class="collapsed"><i class="collapsed"></i><span>查看部门信息</span></a></li>
                    <li><a href="#" class="collapsed"><i class="collapsed"></i><span>帮助</span></a></li>
                    <li><a href="#" onclick="logout()" class="collapsed"><i class="collapsed"></i> <span>退出登录</span></a></li>
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
                <!-- OVERVIEW -->
                <div class="panel">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">${sessionScope.Employee.getDepartmentName()}部门信息</h3>
                    </div>
                    <div class="panel-body no-padding">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>岗位</th>
                                <th>性别</th>
                                <th>工龄</th>
                                <th>年龄</th>
                                <th>联系方式</th>
                                <th>邮箱</th>
                            </tr>
                            </thead>
                            <c:forEach items="${EmployeeList}" var="Employee">
                            <tbody>
                            <tr style="font-size:20px;">

                                    <td>${Employee.getName()}</td>
                                    <td>${Employee.getJob()}</td>
                                    <td>${Employee.getGender()}</td>
                                    <td>${Employee.getWorkAge()}</td>
                                    <td>${Employee.getAge()}</td>
                                    <td>${Employee.getPhoneNumber()}</td>
                                    <td>${Employee.getEmail()}</td>
                            </tr>
                            </tbody>
                            </c:forEach>
                        </table>
                        <a href="EmployeeHomepage.jsp" class="demo-button">
                            <button class="btn btn-success">返回</button>
                        </a>
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
            window.location.href = "DestroySessionForEmployee";
        } else {

        }
    }
</script>
</body>

</html>
