import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public boolean isValid(String name, String pwd) {
        boolean nameStatus = Pattern.compile("[A-Z]{1}[a-z]{2,15}").matcher(name).matches();

        boolean pwdStatus = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&*]).{8,}$").matcher(pwd).matches();
        if (nameStatus == true && pwdStatus == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (isValid(name, pwd)) {
            request.setAttribute("name", name);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Enter valid details</font>");
            rd.include(request, response);
        }
    }
}
