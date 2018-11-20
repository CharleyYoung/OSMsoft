<%@ page import="OSMsoft.Table.SalaryTable" %><%--
  Created by IntelliJ IDEA.
  User: 82533
  Date: 2018/11/17
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Home</title>
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
                                <li><a href="" class="">添加员工</a></li>
                                <li><a href="" class="">管理员工信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subPages2" data-toggle="collapse" class="collapsed"><i class=""></i>
                            <span>薪酬管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages2" class="collapse">
                            <ul class="nav">
                                <li><a href="AdminSalary.jsp" class="">查询工资</a></li>
                                <li><a href="AdminSalary2.jsp" class="">添加工资信息</a></li>
                            </ul>
                        </div>
                    </li>

                    <li><a href="" class="collapsed"><i class="collapsed"></i><span>部门管理</span></a></li>
                    <li><a href="#" class="collapsed"><i class="collapsed"></i><span>帮助</span></a></li>
                    <li><a href="#" onclick="logout()" class="collapsed"><i class="collapsed"></i> <span>退出登录</span></a></li>
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
                            <img src="assets/img/Taiho_medium.png" width="80" height="80" class="img-circle" alt="Avatar">
                            <h3 class="name" id="name">${sessionScope.Account}</h3>
                            <span>${sessionScope.Account}</span>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="profile-detail"></div>
                        <h1 align="center" class="page-title"><strong>查询结果</strong></h1>
                        <form class="form-auth-small" method="post" action="AdminSalary1">
                            <style type="text/css">
                                table.gridtable {
                                    font-family: verdana,arial,sans-serif;
                                    font-size:11px;
                                    color:#333333;
                                    border-width: 1px;
                                    border-color: #666666;
                                    border-collapse: collapse;
                                }
                                table.gridtable th {
                                    border-width: 1px;
                                    padding: 8px;
                                    border-style: solid;
                                    border-color: #666666;
                                    background-color: #dedede;
                                }
                                table.gridtable td {
                                    border-width: 1px;
                                    padding: 8px;
                                    border-style: solid;
                                    border-color: #666666;
                                    background-color: #ffffff;
                                }
                            </style>
                            <div align="center">
                                <table align="center">
                                    <tr>
                                        <td width=350px height=64px>
                                            <table class="gridtable">
                                                <tr>
                                                    <th>EmloyeeID</th><th>JobSalary</th><th>PerformanceSalary</th><th>WorkageSalary</th><th>SubsidyAllowance</th><th>Tax</th>
                                                    <th>Year</th><th>Month</th><th>Alter</th><th>Delete</th>
                                                </tr>
                                                    <c:forEach items ="${time}" var = "item">
                                                <tr>
                                                        <td>${item.employeeID} </td>
                                                        <td>${item.jobSalary}</td>
                                                        <td>${item.performanceSalary}</td>
                                                        <td>${item.workAgeSalary} </td>
                                                        <td>${item.subsideAllowance}</td>
                                                        <td>0</td>
                                                        <td>${item.year} </td>
                                                        <td>${item.month} </td>
                                                        <th><button type="submit" class="btn btn-primary" onclick="Update()"><i class="fa fa-refresh"></i> 更新信息</button></th>
                                                        <th><button type="submit" class="btn btn-danger" onclick="Delete()"><i class="fa fa-refresh"></i> 删除</button></th>
                                                </tr>
                                                    </c:forEach>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <button type="submit" class="btn btn-danger" onclick="DeleteAll()"><i class="fa fa-refresh"></i> 清空</button>
                            </div>
                        </form>
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
            window.location.href = "DestroySession";
        } else {

        }
    }
</script>
<script type="text/javascript">
    function Update() {
        var result = confirm("更新该工资信息？");
        if(result == true){
            window.location.href ="UpdateEmployeeForAdmin";
        }else {

        }
    }
</script>
<script type="text/javascript">
    function Delete() {
        var result = confirm("删除该工资信息？");
        if(result == true){
            window.location.href ="UpdateEmployeeForAdmin";
        }else {

        }
    }
</script>
<script type="text/javascript">
    function DeleteAll() {
        var result = confirm("清除所有工资信息？");
        if(result == true){
            window.location.href ="UpdateEmployeeForAdmin";
        }else {

        }
    }
</script>
</body>

</html>

