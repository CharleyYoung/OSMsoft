<%--
  Created by IntelliJ IDEA.
  User: Jet Zhang
  Date: 2018-11-18
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TEST DEPTREE</title>
</head>

<body>
<c:set var="depList" value="${depList}"/>

<c:forEach items="${depList}" var="dep" varStatus="status">
    <!-- 一级子菜单没有parentId,有url -->
    <c:if test="${ dep.parentId eq '0' and not empty dep.url}">
        <li>
            <a href="<c:url value='${dep.url }'/>">
                <i class="${dep.icon } fa-fw"></i> ${dep.name }
            </a>
        </li>
    </c:if>
    <!-- 可展开的一级菜单，没有parentId,有url -->
    <c:if test="${dep.parentId eq '0' and empty dep.url}">
        <li>
            <a href="#">
                <i class="${dep.icon } fa-fw"></i> ${dep.name }<span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <!-- 没有url的是三级菜单，有url的直接输出到li中 -->
                <c:forEach items="${dep.children}" var="secondChild" varStatus="status">
                    <c:if test="${not empty secondChild.url }">
                        <li>
                            <a href="<c:url value='${secondChild.url }'/>">${secondChild.name }</a>
                        </li>
                    </c:if>
                    <!-- 二级菜单url为空，表示还有三级菜单 -->
                    <c:if test="${empty secondChild.url }">
                        <li>
                            <a href="#">${secondChild.name }<span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <c:forEach items="${secondChild.children}" var="thirdChild" varStatus="status">
                                    <li>
                                        <a href="<c:url value='${thirdChild.url }'/>">${thirdChild.name }</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
    </c:if>
</c:forEach>
</body>

</html>
