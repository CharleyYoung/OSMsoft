<%--
    Created by IntelliJ IDEA.
    User: zzh187
    Date: 2018/11/16
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>EmployeeHome</title>
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
                    <li><a href="#subPages1" data-toggle="collapse" class="collapsed"><i class=""></i>
                        <span>个人信息</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages1" class="collapse ">
                            <ul class="nav">
                                <li><a href="ManageInformation.jsp" class="">管理个人信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="" class="collapsed"><i class="collapsed"></i><span>工资记录</span></a></li>
                    <li><a href="" class="collapsed"><i class="collapsed"></i><span>部门信息</span></a></li>
                    <li><a href="#" class="collapsed"><i class="collapsed"></i><span>帮助</span></a></li>
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
                <!-- OVERVIEW -->
                <div class="panel panel-headline">
                    <div class="profile-header">
                        <div class="overlay"></div>
                        <div class="profile-main">
                            <img src="assets/img/Taiho_medium.png" width="80" height="80" class="img-circle"
                                 alt="Avatar">
                            <h3 class="name" id="name">${sessionScope.Name}</h3>
                            <span>${sessionScope.Name}</span>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="profile-detail"></div>
                        <h1 align="center" class="page-title">欢迎您，${sessionScope.Name}！</h1>
                    </div>
                </div>
                <!-- END OVERVIEW -->
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