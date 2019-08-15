/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.BankAccount;
import ejb.ItemFacade;
import ejb.ManageBeanLocal;
import ejb.SessionManagerBean;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBean;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
//    ManageBeanLocal msb = lookupManageBeanLocal();

//    ManagementStatefulBeanLocal managementStatefulBean = lookupManagementStatefulBeanLocal();
    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    @Inject
    ManageBeanLocal msb;

    @EJB
    private SessionManagerBean sessionManagerBean;

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

        // Add the following code to send the JMS message
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if ((login != null) && (password != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                User e = new User();
                e.setLogin(login);
                e.setPassword(password);

                sessionManagerBean.storeUserData(login, password);
//                List news = userFacade.findAll();

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
//                response.sendRedirect("ListNews");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserMsg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>user: " + sessionManagerBean.getCurrentUser() + "</h1>");
            out.println("<h1>Servlet UserMsg at " + request.getContextPath() + "</h1>");
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

    private ManageBeanLocal lookupManageBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ManageBeanLocal) c.lookup("java:global/NewsApp/NewsApp-war/ManageBean!ejb.ManageBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
