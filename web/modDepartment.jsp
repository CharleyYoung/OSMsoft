<%--
  Created by IntelliJ IDEA.
  User: YocLu
  Date: 2018/11/18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="OSMsoft.DAO.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Modify Department</title>
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
                                <li><a href="" class="">查询工资</a></li>
                                <li><a href="" class="">添加工资信息</a></li>
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

    <div class="tab-content">
        <div class="tab-pane active" id="panel-1">

            <!-- MAIN -->
            <div class="main">
                <!-- MAIN CONTENT -->
                <div class="main-content">
                    <div class="container-fluid">
                        <h3 class="page-title">Modify Department</h3>
                        <div class="row">
                            <div class="col-md-12">

                                <!-- INPUTS -->
                                <form method="POST" action="SearchDepartment">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Input Department ID</h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <input class="form-control" type="text" name="depid" placeholder="Department ID">
                                                    <span class="input-group-btn"><button
                                                            class="btn btn-primary" type="SUBMIT">Go!</button></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Result Of Finding</h3>
                                    </div>
                                    <div class="panel-body no-padding">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>Department ID</th>
                                                <th>Department Name</th>
                                                <th>Department Parent ID</th>
                                                <th>Operate</th>
                                            </tr>
                                            </thead>

                                            <%
                                                if(request.getAttribute("depid") != null){
                                            %>
                                            <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td><%=request.getAttribute("depid")%></td>
                                                <td><%=request.getAttribute("depname")%></td>
                                                <td><%=request.getAttribute("parentdepid")%></td>

                                                <th><button type="button" class="btn btn-primary" onclick="update()">
                                                    <i class="fa fa-refresh"></i> Update
                                                </button></th>
                                            </tr>
                                            </tbody>
                                            <%
                                                }
                                            %>

                                        </table>
                                    </div>
                                </div>


                                <%
                                    if(request.getAttribute("depid") != null){
                                %>
                                <form method="POST" action="modDepartment">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">New information</h3>
                                        </div>
                                        <div class="panel-body">
                                            <input type="text" class="form-control" id="depid" readonly="readonly"
                                                   placeholder="Department ID"> <br>
                                            <input type="text" class="form-control" id="name" readonly="readonly"
                                                   placeholder="book name"> <br>
                                            <input type="text" class="form-control" id="publisher" readonly="readonly"
                                                   placeholder="publisher"> <br>
                                            <input type="text" class="form-control" id="edition" readonly="readonly"
                                                   placeholder="edition"> <br>
                                            <input type="text" class="form-control" id="catagory" readonly="readonly"
                                                   placeholder="catagory"> <br>
                                            <input type="text" class="form-control" id="location" name="location"
                                                   placeholder="location"> <br>
                                            <input class="form-control" type="hidden" name="bookID" value=<%=request.getAttribute("bookID") %>>
                                            <p class="demo-button">
                                                <button type="SUBMIT" class="btn btn-success">Update</button>
                                            </p>
                                        </div>
                                    </div>
                                </form>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN CONTENT -->
    <div class="clearfix"></div>
    <footer>
        <div class="container-fluid">
            <p class="copyright">
                Copyright &copy; 2018.Eagle Jump All rights reserved.OSMsoft - Collect from Software
            </p>
        </div>
    </footer>
    <!-- END WRAPPER -->
    <!-- Javascript -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/vendor/chartist/js/chartist.min.js"></script>
    <script src="assets/scripts/klorofil-common.js"></script>
    <script>
        function logout() {
            var result = confirm("Please make sure.Logout?");
            if (result == true) {
                window.location.href = "DestroySession";
            } else {

            }
        }
    </script>

</div>
</body>
</html>
