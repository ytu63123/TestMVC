<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
    <head>
        <!-- Head -->
        <%@include file="include/head.jspf"  %>
    </head>
    <body style="padding: 10px">

        <div id="layout">
            <!-- Toggle -->
            <%@include file="include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>員工</h1>
                    <h2></h2>
                </div>
                <table class="pure-table" style="border: none;">
                    <td valign="top">
                        <!-- 表單 -->
                        <form:form class="pure-form" 
                                   modelAttribute="emp" 
                                   method="post" 
                                   action="${pageContext.request.contextPath}/mvc/" >

                        </form:form>
                    </td>
                    <td valign="top">
                        <!-- 列表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>Employee list</legend>
                                <table class="pure-table pure-table-bordered" width="100%">
                                    <thead>
                                        <tr>
                                            <th>序號</th>
                                            <th>姓名</th>
                                            <th>部門</th>
                                            <th>薪資</th>
                                            <th>社團</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="emp" items="${emp_list}">            
                                            <tr>
                                                <td>${emp.id}</td>
                                                <td>${emp.name}</td>
                                                <td>${emp.department.name}</td>
                                                <td>${emp.salary.money}</td>                                              
                                                <td>
                                                    <c:forEach var="club" items="${emp.clubs}">
                                                        ${club.name}
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </fieldset>
                        </form>
                        <c:forEach var="emp_count" items="${empCount_list}">
                            <p style="text-align:right;">員工總人數:${emp_count}</p>
                        </c:forEach> 
                    </td>
                    <td valign="top">
                        <!-- 圖表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>OOO chart</legend>

                            </fieldset>
                        </form>
                    </td>
                </table>   


            </div>
        </div>

        <!-- Foot -->
        <%@include file="include/foot.jspf"  %>

        <script src="${pageContext.servletContext.contextPath}/js/ui.js"></script>
    </body>
</html>