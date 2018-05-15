package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginControllerServlet", urlPatterns = "/login")
public class LoginControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btn_login") != null){
            String username = request.getParameter("txt_username");
            String password = request.getParameter("txt_password");

            User user = new User();

            user.setUserName(username);
            user.setPassword(password);

            UserService loginDAO = new UserService();

            String authorizeLogin = loginDAO.authorizeLogin(user);

            if (authorizeLogin.equals("Success Login")) {
                HttpSession session = request.getSession();
                session.setAttribute("login", user.getUserName());
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("WrongLoginMsg", authorizeLogin);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.include(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
