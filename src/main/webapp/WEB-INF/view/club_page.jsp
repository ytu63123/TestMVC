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

                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Name');
                data.addColumn('number', '社團人數');

                data.addRows([
            <c:forEach var="club" items="${club_list}">
               ['${club.name}',${fn:length(club.employees)}], 
            </c:forEach>
                ]);


                var options = {
                    chartArea: {width: '40%'},
                    hAxis: {
                        title: '社團名稱'
                    },
                    is3D: true
                };

                var chart = new google.visualization.ColumnChart(
                        document.getElementById('chart_div'));

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
                    <h1>社團</h1>
                    <h2></h2>
                </div>
                <table class="pure-table" style="border: none;">
                    <td valign="top">
                        <!-- 表單 -->
                        <form:form class="pure-form" 
                                   modelAttribute="club" 
                                   method="post" 
                                   action="${pageContext.request.contextPath}/mvc/club/" >
                            <fieldset>
                                <legend>Club Form</legend>
                                <form:input path="id" readonly="true" /><p />
                                <form:input path="name" placeholder="請輸入社團名稱" /><p />
                                <button type="sumbit" class="pure-button pure-button-primary">Submit</button>
                            </fieldset>

                        </form:form>
                    </td>
                    <td valign="top">
                        <!-- 列表 -->

                        <form class="pure-form">
                            <fieldset>
                                <legend>Club list</legend>
                                <table class="pure-table pure-table-bordered" width="100%">
                                    <thead>
                                        <tr>
                                            <th>序號</th>
                                            <th>名稱</th>
                                            <th>人數</th>
                                            <th>修改</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="club" items="${club_list}">                                    
                                            <tr>
                                                <td>${club.id}</td>
                                                <td>${club.name}</td>
                                                <td>${fn:length(club.employees)}</td>
                                                 <td><a href="${pageContext.request.contextPath}/mvc/club/${ club.id }">修改</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </fieldset>
                        </form>
                    </td>
                    <td valign="top">
                        <!-- 圖表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>社團人數長條圖</legend>
                                <div id="chart_div" style="width: 300px; height: 300px;"></div>                        
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