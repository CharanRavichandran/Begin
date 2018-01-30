import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    public static DataSource getMysqlDataSource() {
        DataSource dataSource = new MysqlDataSource();
    
        // Set dataSource Properties
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("Begin");
        dataSource.setUser("root");
        dataSource.setPassword("");
        return dataSource;
      }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int roll=Integer.parseInt(request.getParameter("rollnumber"));
        out.println(name+" "+roll);

        try{
        Connection conn = null;
        PreparedStatement ps = null;
        conn=getMysqlDataSource().getConnection();
   ps=conn.prepareStatement("insert into Register values(?,?)");
       
        ps.setString(1, name);
        ps.setInt(2,roll);
        int i=ps.executeUpdate();
        if(i>0)
        {
          out.println("You are sucessfully registered");
        }
    }
    catch(Exception e){
        out.println("You are not registered");
        out.println(e);

    }

            }
        }