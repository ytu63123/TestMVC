<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="club" required="true" rtexprvalue="true" %>
<%@attribute name="name1" required="true" rtexprvalue="true" %>
<%@attribute name="name2" required="true" rtexprvalue="true" %>
<%@attribute name="one" required="true" rtexprvalue="true" %>
<%@attribute name="two" required="true" rtexprvalue="true" %>

<%@tag body-content="scriptless" %> <!-- scriptless(預設)、empty、tagdependent -->
<html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawBasic);

            function drawBasic() {

                var data = new google.visualization.DataTable();
                data.addColumn('string', '${club}');
                data.addColumn('number', '${club}');

                data.addRows([
                    ['${name1}', ${one}],
                    ['${name2}', ${two}],
                ]);

                var options = {
                    chartArea: {width: '30%'},
                    hAxis: {
                        title: '社團名稱'
                    },                    
                    vAxis: {
                        minValue: 0.5
                    },
                    is3D: true
                };

                var chart = new google.visualization.ColumnChart(
                        document.getElementById('chart_div'));

                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
        <h1><jsp:doBody /></h1>  
        <div id="chart_div" style="width: 500px; height: 500px;" ></div>
    </body>
</html>