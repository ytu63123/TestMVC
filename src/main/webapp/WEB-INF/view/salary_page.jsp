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
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawBasic);

            function drawBasic() {

                var data = google.visualization.arrayToDataTable([
                    ['name', '薪資(元)'],
            <c:forEach var="salary" items="${salary_list}">
                    ['${salary.employee.name}',${salary.money}],
            </c:forEach>
                ]);

                var options = {
                    chartArea: {width: '50%'},
                    hAxis: {
                        minValue: 0
                    },
                };

                var chart = new google.visualization.BarChart(document.getElementById('barchart'));

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
                    <h1>薪資</h1>
                    <h2></h2>
                </div>
                <table class="pure-table" style="border: none;">
                    <td valign="top">
                        <!-- 表單 -->
                        <form:form class="pure-form" 
                                   modelAttribute="salary" 
                                   method="post" 
                                   action="${pageContext.request.contextPath}/mvc/salary/" >
                            <fieldset>
                                <legend>Salary Form</legend>
                                <form:input path="id" readonly="true" /><p />
                                <form:input path="employee.name" readonly="true"  /><p />
                                <form:input path="money" placeholder="請輸入薪資" /><p />
                                <input type="text" id="_method" name="_method" readonly="true" value="${_method}" /><p />
                                <form:errors path="*" style="color:red" /><p />
                                <button type="sumbit" class="pure-button pure-button-primary">Submit</button>
                            </fieldset>
                        </form:form>
                    </td>
                    <td valign="top">
                        <!-- 列表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>Salary list</legend>
                                <table class="pure-table pure-table-bordered" width="100%">
                                    <thead>
                                        <tr>
                                            <th>序號</th>
                                            <th>姓名</th>
                                            <th>薪資</th>
                                            <th>修改</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="salary" items="${salary_list}">                                         
                                            <tr>
                                                <td>${salary.id}</td>
                                                <td>${salary.employee.name}</td>
                                                <td>${salary.money}</td>
                                                <td><a href="${pageContext.request.contextPath}/mvc/salary/${salary.id}">修改</a></td>                                               
                                            </tr>
                                        </c:forEach>                                            
                                    </tbody>
                                </table> 
                            </fieldset>
                        </form>
                        <c:forEach var="salary_sum" items="${salarySum_list}">
                            <p style="text-align:left;">薪資總額:${salary_sum}
                            </c:forEach>
                            <c:forEach var="salary_avg" items="${salaryAvg_list}">
                                平均薪資:<fmt:formatNumber type="number" maxFractionDigits="3" value="${salary_avg}" /></p>
                            </c:forEach>
                    </td>

                    <td valign="top">
                        <!-- 圖表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>員工薪資表</legend>
                                <div id="barchart" style="width: 500px; height: 500px;" ></div>
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