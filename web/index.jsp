<%
  if(session.getAttribute("login")!=null) {
    response.sendRedirect("welcome.jsp");
  }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Java MVC Login & Register Script Using MySql</title>
  <script language="javascript">
      function validate() {
          var username = document.LoginForm.txt_username;
          var password = document.LoginForm.txt_password;

          if (username.value == null || username.value == "") {
              window.alert("please enter username ?");
              username.style.background = '#f08080';
              username.focus();
              return false;
          }
          if (password.value == null || password.value == "") {
              window.alert("please enter password ?");
              password.style.background = '#f08080';
              password.focus();
              return false;
          }
      }
  </script>
</head>
<body>
<h2>Login</h2>
<form method="post" action="/login" name="LoginForm" onsubmit="return validate();">
  Username    :<input type="text" name="txt_username">
  Password    :<input type="password" name="txt_password">
  <input type="submit" name="btn_login" value="Login">
  <h3>Your don't have a account? <a href="register.jsp">Register</a></h3>
</form>

<h3 style="color:green">
  <%
    if(request.getAttribute("RegiseterSuccessMsg")!=null)
    {
      out.println(request.getAttribute("RegiseterSuccessMsg"));
    }
  %>
</h3>

<h3 style="color:red">
  <%
    if(request.getAttribute("WrongLoginMsg")!=null)
    {
      out.println(request.getAttribute("WrongLoginMsg"));
    }
  %>
</h3>
</body>
</html