/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

//import com.org.bean.Registrationbean;
//import com.org.dao.registrationdao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mahesh-5
 */
@WebServlet(name = "registrationController", urlPatterns = {"/registrationController"})
public class registrationController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet registrationController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet registrationController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        exeute(request,response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
    
    
    public void exeute(HttpServletRequest request,HttpServletResponse response)
    {
        try{
//            Registrationbean bean = new Registrationbean();
            String firstname = request.getParameter("firstname");
            String middlename = request.getParameter("middlename");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password1 = request.getParameter("password");
            String contactString = request.getParameter("contact");
        //    int contact = Integer.parseInt(contactString);
            String dob = request.getParameter("dob");
//            Date dob = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(dobString);
////            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////            String mydateStr = df.format(dob1);
//            String address = request.getParameter("address");
            String group1 = request.getParameter("group1");
//            String state = request.getParameter("state");
            //String category = request.getParameter("category");

            
            String category = "aaa";
         //   String[] category = request.getParameterValues("tag1");
            String rell = "aaa";
//            String[] rel = request.getParameterValues("tag1");
//            if(category != null){
             
            
//            for(int i=0; i<rel.length;i++)
//            { 
//                //String friendname = (String)session.getAttribute("tagg");
//                
//                category = rel[i];
//                System.out.println("Category Value : "+category);
//            }
            
            
            
//            bean.setFirstName(firstname);
//            bean.setMiddleName(middlename);
//            bean.setLastName(lastname);
//            bean.setEmail(email);
//            bean.setPassword(password);
//            bean.setContact(contact);
//            bean.setDateOfBirth(dob);
//            bean.setAddress(address);
//            bean.setCity(city);
//            bean.setState(state);
//            bean.setCategory(category);
            
             ConnectionManager cm = new ConnectionManager();
            Statement st = cm.statement();
            
            HttpSession session=request.getSession();
             Statement stmt = cm.statement();
            String query = "insert into registration (fname,lname,username,dob,contact,password,group1) "
                    + "values('"+firstname+"','"+lastname+"','"+email+"',"
                    + "'"+dob+"','"+contactString+"','"+password1+"','"+group1+"')";
                    //+ "'"+rd.getCity()+"','"+rd.getState()+"','"+rd.getCategory()+"')";
            System.out.println("Query: "+query);
            stmt.execute(query);
            
            
         //   registrationdao dao = new registrationdao();
          //  dao.insert(bean);
            if(!response.isCommitted()) {
           
            	session.setAttribute("firstname", firstname);
                response.sendRedirect("Login.jsp");
            }
            else {
                System.out.println("fail");
            }
          } 
 catch(Exception e)    
        {
            e.printStackTrace();
        }
    }
}
