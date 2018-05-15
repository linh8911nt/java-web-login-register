package com.codegym.service;

import com.codegym.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public String authorizeLogin(User user){
        String userName = user.getUserName();
        String password = user.getPassword();

        password = encodeMD5(password);

        String databaseUserName = "";
        String databasePassword = "";

        try {
            Connection connection = ConnectionUtil.getConnection("localhost", "root", "12345678", "mydb", "3306");
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                databaseUserName = resultSet.getString("username");
                databasePassword = resultSet.getString("password");

                if (userName.equals(databaseUserName) && password.equals(databasePassword)){
                    return "Success Login";
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Wrong Username or Password";
    }

    public String authorizeRegister(User user){
        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        String username = user.getUserName();
        String password = user.getPassword();

        try {
            Connection connection = ConnectionUtil.getConnection("localhost", "root", "12345678", "mydb", "3306");

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("insert into users (firstname, lastname, username, password) values(?, ?, ?, md5(?))");
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
            return "SUCCESS REGISTER";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL REGISTER";
    }

    public String encodeMD5(String password) {
        StringBuffer sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
