<%--
    Created by IntelliJ IDEA.
    User: Taiho
    Date: 2018/11/15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="MyFirstTag" prefix="mytag" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
<!--调用自定义标签-->
<mytag:DepartmentReader></mytag:DepartmentReader>
<!--利用JSTL生成一个数组-->
<c:set var="departmentList" scope="page" value="${departmentInformation}"/>
<head>
    <title>更新员工信息</title>
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
                    <li><a href="AdminHomepage.jsp" class="active"><i class="active"></i> <span>个人信息</span></a></li>
                    <li><a href="#subPages1" data-toggle="collapse" class="collapsed"><i class=""></i>
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
                                <li><a href="" class="">查询工资</a></li>
                                <li><a href="" class="">添加工资信息</a></li>
                            </ul>
                        </div>
                    </li>

                    <li><a href="" class="collapsed"><i class="collapsed"></i><span>部门管理</span></a></li>
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
                <h3 class="page-title">更改员工信息</h3>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">新信息</h3>
                            </div>
                            <form method="POST" action="UpdateEmployeeForAdmin">
                                <div class="panel-body">
                                    <input type="text" class="form-control" name="id"
                                           placeholder="员工ID" readonly="true " value="${Employee.getEmployeeID()}">
                                    <br>
                                    <input type="text" class="form-control" name="name"
                                           placeholder="员工姓名" value="${Employee.getName()}">
                                    <br>
                                    <input type="text" class="form-control" name="workAge"
                                           placeholder="员工工龄" value="${Employee.getWorkAge()}">
                                    <br>
                                    <input type="text" class="form-control" name="age"
                                           placeholder="员工年龄" value="${Employee.getAge()}">
                                    <br>
                                    <input type="text" class="form-control" name="tele"
                                           placeholder="电话号码" value="${Employee.getPhoneNumber()}">
                                    <br>
                                    <input type="text" class="form-control" name="email"
                                           placeholder="邮箱" value="${Employee.getEmail()}">
                                    <br>
                                    <input type="text" class="form-control" name="job"
                                           placeholder="职位" value="${Employee.getJob()}">
                                    <br>
                                    <input type="text" class="form-control" name="password"
                                           placeholder="密码" value="${Employee.getPassword()}">
                                    <br>
                                    <select id="部门" name="department" size="value" style="width: 100px;">
                                        <c:forEach var="department" items="${departmentList}">
                                            <option name="department"
                                                    value="${department.getDepartmentName()}">${department.getDepartmentName()}</option>
                                        </c:forEach>
                                    </select>
                                    <label class="fancy-radio">
                                        <input name="gender" value="男" type="radio">
                                        <span><i></i>男</span>
                                    </label>
                                    <label class="fancy-radio">
                                        <input name="gender" value="女" type="radio">
                                        <span><i></i>女</span>
                                    </label>
                                    <p class="demo-button">
                                        <button type="submit" class="btn btn-success">更新</button>
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