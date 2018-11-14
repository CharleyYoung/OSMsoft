<%--
  Created by IntelliJ IDEA.
  User: Taiho
  Date: 2018/11/14
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Test jsp</title>
    <c:set var="str" scope="session" value="Hello JSP"></c:set>
    ${str}<br>
  </head>
  <body>
  Hello World!
  </body>
</html>
