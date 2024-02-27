
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Servlet implementation class AuthenticateUser
 */
public class AuthenticateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (!checkAuthentication(username, password)) {
			response.getWriter().append("Could not authenticate user");
			return;
		}
		
		String type = request.getParameter("username");
		if (type.equals("root")) {
			response.sendRedirect("rootHome.jsp");
		}
		else if (type.equals("client1")) {
			response.sendRedirect("clientHome.jsp");
		}
		else if (type.equals("accountant")) {
			response.sendRedirect("accountantHome.jsp");
		}
		else if (type.equals("dataentry")) {
			response.sendRedirect("dataEntryHome.jsp");
		}
		else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}
	boolean checkAuthentication(String username, String password) throws FileNotFoundException {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current absolute path is: " + s);
		// The deployment system that i was working made it so the current working director was my home folder (linux)
		//	I did include the .csv file in lib where it's supposed to be. However if you run into any trouble
		//	getting the servlet to locate the credentials file, I included code to print out the current working 
		//	path in case you have any trouble. Putting the .csv file there will resolve the issue.
		File file = new File("credentials.csv");
		Scanner scanner = new Scanner(file);
        scanner.useDelimiter(", |\n");
        boolean foundUser = false;
        String currentString;
        while(scanner.hasNext()) {
        	currentString = scanner.next();
        	System.out.println(currentString);
        	
        	if (!foundUser) {
        		if(currentString.equals(username)) {
        			foundUser = true;
        			continue;
        		}
        	}
        	else {
        		if(currentString.equals(password)) {
        			return true;
        		}
        		else {
        			return false;
        		}
        	}
        	if (scanner.hasNext())
        		scanner.next();
        }
		
		return false;
	}

}
