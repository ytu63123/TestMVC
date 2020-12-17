<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="task" required="true" rtexprvalue="true" %>
<%@attribute name="it" required="true" rtexprvalue="true" %>
<%@attribute name="sales" required="true" rtexprvalue="true" %>
<%@tag body-content="scriptless" %> <!-- scriptless(預設)、empty、tagdependent -->
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', '${task}'],
          ['IT',     ${it}],
          ['Sales',   ${sales}]
        ]);

        var options = {
          is3D: true
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
      <h1><jsp:doBody /></h1>  
    <div id="piechart" style="width: 500px; height: 500px;"></div>
  </body>
</html>
