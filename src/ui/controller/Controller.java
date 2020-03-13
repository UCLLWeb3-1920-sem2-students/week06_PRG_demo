package ui.controller;

import domain.db.DbException;
import domain.db.PersonDbInMemory;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command==null || command.isEmpty())
            command="";
        switch (command) {
            case "Register":
                registerUser(request,response);
                break;
            case "RegisterRedirect":
                registerUserPRG(request,response);
                break;
            case "SignUp":
                signUp(request,response);
                break;
            case "SignUpRedirect":
                signUpPRG(request,response);
                break;
            case "Confirmation":
                confirmUser(request, response);
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request,response);
        }
     }

    private void confirmUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("confirmation.jsp").forward(request,response);
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signUp.jsp").forward(request,response);
    }

    private void signUpPRG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signUpPRG.jsp").forward(request,response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String firstName = request.getParameter("firstName");
        if (userid.isEmpty() || firstName.isEmpty()) {
            List<String> errors = new ArrayList<>();
            errors.add("Fill out all fields");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
            //response.sendRedirect("Controller?command=Confirmation&firstName="+firstName);
        }
    }

        private void registerUserPRG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userid = request.getParameter("userid");
            String firstName = request.getParameter("firstName");
            if (userid.isEmpty() || firstName.isEmpty()) {
                List<String> errors = new ArrayList<>();
                errors.add("Fill out all fields");
                request.setAttribute("errors",errors);
                request.getRequestDispatcher("signUpPRG.jsp").forward(request,response);
            }
            else {
                //request.getRequestDispatcher("confirmation.jsp").forward(request,response);
                response.sendRedirect("Controller?command=Confirmation&firstName="+firstName);
            }

        }




}
