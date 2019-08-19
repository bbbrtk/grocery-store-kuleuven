/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.BankAccount;
import ejb.BankAccountFacade;
import ejb.Basket;
import ejb.ManageStatefulBean;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "BankOrBasketMsg", urlPatterns = {"/BankOrBasketMsg"})
public class BankOrBasketMsg extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    @EJB
    private ManagementStatefulBeanLocal msb;

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

        String name = "";
        name = request.getParameter("bankName");
        String money = "";
        money = request.getParameter("money");
        String basketName = "";
        basketName = request.getParameter("basketName");

        if (!(name.equals("")) && !(money.equals(""))) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                //bank account
                BankAccount e = new BankAccount();
                e.setBankName(name);

                double moneyDouble = Double.parseDouble(money);
                e.setMoney(moneyDouble);

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();

                response.sendRedirect("StartPage");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        } else if (!basketName.equals("")) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                //bank account
                Basket e = new Basket();
                e.setName(basketName);
                e.setUser(msb.getCurrentUser());

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();

                response.sendRedirect("StartPage");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
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
