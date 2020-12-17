<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <my:barchart salary="員工薪資"
                    one="50000" 
                    two="40000" 
                    three="30000" 
                    four="20000" >                     
        </my:barchart>
    </body>
</html>
