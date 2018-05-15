package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterControllerServlet", urlPatterns = "/register")
public class RegisterControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btn_register") != null){
            String firstName = request.getParameter("txt_firstname");
            String lastName = request.getParameter("txt_lastname");
            String userName = request.getParameter("txt_username");
            String password = request.getParameter("txt_password");

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(userName);
            user.setPassword(password);

            UserService registerDAO = new UserService();
            String registerValidate = registerDAO.authorizeRegister(user);

            if (registerValidate.equals("Success Login")){
                request.setAttribute("RegiseterSuccessMsg", registerValidate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("RegisterErrorMsg", registerValidate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.include(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
