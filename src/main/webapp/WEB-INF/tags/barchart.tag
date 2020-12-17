<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="salary" required="true" rtexprvalue="true" %>
<%@attribute name="one" required="true" rtexprvalue="true" %>
<%@attribute name="two" required="true" rtexprvalue="true" %>
<%@attribute name="three" required="true" rtexprvalue="true" %>
<%@attribute name="four" required="true" rtexprvalue="true" %>
<%@attribute name="name1" required="true" rtexprvalue="true" %>
<%@attribute name="name2" required="true" rtexprvalue="true" %>
<%@attribute name="name3" required="true" rtexprvalue="true" %>
<%@attribute name="name4" required="true" rtexprvalue="true" %>
<%@tag body-content="scriptless" %> <!-- scriptless(預設)、empty、tagdependent -->
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
     google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = google.visualization.arrayToDataTable([
        ['薪資', '${salary}'],
        ['${name1}',${one} ],
        ['${name2}', ${two}],
        ['${name3}', ${three}],
        ['${name4}', ${four}],

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
  <body>
      <h1><jsp:doBody /></h1>  
    <div id="barchart" style="width: 500px; height: 500px;" ></div>
  </body>
</html>