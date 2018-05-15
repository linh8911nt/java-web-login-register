<% if (session.getAttribute("login") != null) {
    response.sendRedirect("index.jsp");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Java MVC Login & Register Script Using MySql</title>
    <script language="javascript">
        function validate() {
            var first_name = /^[a-z A-Z]+$/;
            var last_name = /^[a-z A-Z]+$/;
            var user_name = /^[a-z A-Z]+$/;
            var password_valid = /^[A-Z a-z 0-9]{6,12}$/;

            var fname = document.getElementById("fname");
            var lname = document.getElementById("lname");
            var uname = document.getElementById("uname");
            var password = document.getElementById("password");

            if (!first_name.test(fname.value) || fname.value == '')  {
                alert("Enter Firstname Alphabet Only....!");
                fname.focus();
                fname.style.background = '#f08080';
                return false;
            }
            if (!last_name.test(lname.value) || lname.value == '') {
                alert("Enter Lastname Alphabet Only....!");
                lname.focus();
                lname.style.background = '#f08080';
                return false;
            }
            if (!user_name.test(uname.value) || uname.value == '') {
                alert("Enter Username Alphabet Only....!");
                uname.focus();
                uname.style.background = '#f08080';
                return false;
            }
            if (!password_valid.test(password.value) || password.value == '') {
                alert("Password Must Be 6 to 12 character");
                password.focus();
                password.style.background = '#f08080';
                return false;
            }
        }
    </script>
</head>
<body>
<h2>Register</h2>
<form method="post" action="/register" onsubmit="return validate();">
    Firstname <input type="text" name="txt_firstname" id="fname"></br></br>
    Lastname <input type="text" name="txt_lastname" id="lname"></br></br>
    Username <input type="text" name="txt_username" id="uname"></br></br>
    Password <input type="password" name="txt_password" id="password"></br></br>
    <input type="submit" name="btn_register" value="Register">
    <h3>You have a account? <a href="index.jsp">Login</a></h3>
</form>
<h3 style="color:red">
    <%
        if (request.getAttribute("RegisterErrorMsg") != null) {
            out.println(request.getAttribute("RegisterErrorMsg"));
        }
    %>
</h3>
</body>
</html>