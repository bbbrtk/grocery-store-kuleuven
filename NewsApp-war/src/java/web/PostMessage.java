/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.BankAccount;
import ejb.Basket;
import ejb.NewsEntity;
import ejb.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
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
 * @author nb
 */
@WebServlet(name = "PostMessage", urlPatterns = {"/PostMessage"})
public class PostMessage extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Add the following code to send the JMS message
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        if ((title != null) && (body != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                
                // basket
                Basket basket = new Basket();
                List<Basket> list = new ArrayList();
                list.add(basket);
                basket.setName("MainBasket");
                
                
                //bank account
                BankAccount bankAccount = new BankAccount();
                List<BankAccount> listBank = new ArrayList();
                listBank.add(bankAccount);                
                bankAccount.setBankName("ING");
                
                //user
                User e = new User();
                List<User> listUser = new ArrayList();
                listUser.add(e);    
                e.setLogin(title);
                e.setPassword(body);
                
                e.setListOfMyBaskets(list);
                basket.setUser(e);
                
                e.setBankAccounts(listBank);
                bankAccount.setListOfUsers(listUser);
                
                
                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
                response.sendRedirect("ListNews");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }


        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostMessage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostMessage at " + request.getContextPath() + "</h1>");

            // The following code adds the form to the web page
            out.println("<form>");
            out.println("Title: <input type='text' name='title'><br/>");
            out.println("Message: <textarea name='body'></textarea><br/>");
            out.println("<input type='submit'><br/>");
            out.println("</form>");


            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
