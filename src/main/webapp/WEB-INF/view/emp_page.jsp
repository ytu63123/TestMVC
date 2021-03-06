<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
    <head>
        <!-- Head -->
        <%@include file="include/head.jspf"  %>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawSalary);
            google.charts.setOnLoadCallback(drawClub);
            google.charts.setOnLoadCallback(drawDept);

            function drawSalary() {

                var data = google.visualization.arrayToDataTable([
                    ['name', '薪資'],
            <c:forEach var="emp" items="${emp_list}">
                    ['${emp.name}',${emp.salary.money}],
            </c:forEach>
                ]);

                var options = {
                    chartArea: {width: '50%'},
                    hAxis: {
                        title: '員工x薪資',
                        minValue: 0
                    },
                };

                var chart = new google.visualization.BarChart(document.getElementById('salarychart'));

                chart.draw(data, options);
            }
            function drawClub() {

                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Name');
                data.addColumn('number', '參加多少社團');

                data.addRows([
            <c:forEach var="emp" items="${emp_list}">
                    ['${emp.name}',${fn:length(emp.clubs)}],
            </c:forEach>
                ]);


                var options = {
                    chartArea: {width: '50%'},
                    hAxis: {
                        title: '員工x社團'
                    },
                    is3D: true
                };

                var chart = new google.visualization.ColumnChart(
                        document.getElementById('chart_club'));

                chart.draw(data, options);
            }

            function drawDept() {

                var data = google.visualization.arrayToDataTable([
                    ['name', '人數'],
            <c:forEach var="dept" items="${dept_list}">
                    ['${dept.name}',${fn:length(dept.employees)}],
            </c:forEach>

                ]);

                var options = {
                    title: '員工x部門',
                    is3D: true
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart_Dept'));

                chart.draw(data, options);
            }
        </script>
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
                                   action="${pageContext.request.contextPath}/mvc/emp/" >
                            <fieldset>
                                <legend>Emp Form</legend>
                                <form:input path="id" readonly="true" /><p />
                                <form:input path="name" placeholder="請輸入員工名稱" /><p />
                                <form:errors path="name" style="color:red" /><p />
                                <form:input path="salary.money" placeholder="請輸入薪資" /><p />
                                <form:errors path="salary.money" style="color:red" /><p />
                                <form:select path="department.id">
                                    <form:option value="0" label="請選擇" />
                                    <form:options items="${ dept_list }" itemValue="id" itemLabel="name" />
                                </form:select><p />
                                <c:forEach var="club" items="${ club_list }">
                                    <input name="clubIds" type="checkbox" value="${ club.id }" 
                                           <c:forEach var="eclub" items="${ emp.clubs }">
                                               <c:if test="${ eclub.id eq club.id }">
                                                   checked
                                               </c:if>
                                           </c:forEach>
                                           > ${ club.name }
                                </c:forEach><p />
                                <form:errors path="*" style="color:red" /><p />
                                <input type="text" id="_method" name="_method" readonly="true" value="${_method}" /><p />
                                <button type="sumbit" class="pure-button pure-button-primary">Submit</button>
                            </fieldset>

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
                                            <th>修改</th>
                                            <th>刪除</th>
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
                                                <td><a href="${pageContext.request.contextPath}/mvc/emp/${emp.id}">修改</a></td>
                                                <td><a href="${pageContext.request.contextPath}/mvc/emp/delete/${emp.id}">刪除</a></td>

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
                                <legend>員工資料圖表</legend>
                                <div id="piechart_Dept" style="width: 450px; height: 450px;" ></div>
                                <div id="salarychart" style="width: 400px; height: 400px;" ></div>
                                <div id="chart_club" style="width: 500px; height: 500px;"></div>                                
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