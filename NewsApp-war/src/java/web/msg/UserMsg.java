/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.BankAccount;
import ejb.ItemFacade;
import ejb.ManageStatefulBean;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBean;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "UserMsg", urlPatterns = {"/UserMsg"})
public class UserMsg extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private UserFacade userFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String login = "";
        login = request.getParameter("login");
        String password = "";
        password = request.getParameter("password");
        String loginRegister = "";
        loginRegister = request.getParameter("loginRegister");
        Boolean userExist = false;

        System.out.println("------ login: " + login);
        System.out.println("------ pwd  : " + password);
        System.out.println("-------------- reg  : " + loginRegister);

//        if ((login != "") && (password != "")) {
        List users = userFacade.findAll();
        for (Iterator it = users.iterator(); it.hasNext();) {
            User elem = (User) it.next();
            if (elem.getLogin().equals(login) && elem.getPassword().equals(password)) {
                msb.storeUser(elem);
                userExist = true;
                response.sendRedirect("StartPage"); //StartPage
                break;

            } else if ((elem.getLogin().equals(loginRegister)) || (elem.getLogin().equals(login))) {
                userExist = true;
                response.sendRedirect("login/login.html");
                break;
            }
        }

        if ((!userExist) && (!"".equals(loginRegister))) {

            try {

                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                User e = new User();
                e.setLogin(loginRegister);
                e.setPassword(loginRegister); // inital pwd same as login

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();

                response.sendRedirect("StartPage");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        } else if (!userExist) {
            response.sendRedirect("login/login.html");
        }

        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error Message</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p> [InputException handled]/p>");
            out.println("<p> Empty or incorrect input</p>");
            out.println("<a href='/NewsApp-war/StartPage'>Return</a>");
            out.println("<br><br>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
