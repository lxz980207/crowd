<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
      <script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="layer/layer.js"></script>
<%--    <script type="text/javascript" src="layer/layer.js"></script>--%>

    <script type="text/javascript">

      $(function(){
        var requestBody=[1,2,3];
        var s = JSON.stringify(requestBody);
            $("#btn1").click(function () {
                $.ajax({
                    url: "send/array/one.html",
                    type: "post",
                    contentType : "application/json;character=UTF-8",
                    data: s,
                    dataType: "text",
                    success: function (response) {
                        alert(response);
                    },
                    error: function (response) {
                        alert(response);
                    }

                });
            });
          $("#btn2").click(function () {
              layer.msg("按钮二");
          });
      });
    </script>

    <script>
        var $btn1 = $("#btn1");
        $btn1.html()

    </script>
   </head>
  <body>
  <a href="${pageContext.request.contextPath}/ssm.html">
    测试集合环境
  </a>

  </br>
  <button id="btn1">
    test Body send[1,2,3]
  </button>
  <button id="btn2">
      我是按钮二号
  </button>
  </br>
<%--  <a href="${pageContext.request.contextPath}/admin/do/login.html">--%>
<%--      登录页--%>
<%--  </a>--%>
  <a href="${pageContext.request.contextPath}/admin/to/login/page.html">登陆页面</a>
  </body>
</html>
