

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class DataEntryUser
 */
public class DataEntryUser extends HttpServlet {
	Connection connection = null;
	private static final long serialVersionUID = 1L;
	String message = "";
	ArrayList<String[]> records = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataEntryUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("clear") != null) {
			System.out.println("clear results!!");
			message = "";
			records = null;
			session.setAttribute("message",  message);
			session.setAttribute("records",  records);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
		    dispatcher.forward(request, response);      
		    return;
		}
		try {
			connectDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "";
		if (request.getParameter("suppliers") != null) {
			query = "insert into suppliers values "
					+ "('"
					+ request.getParameter("snum_s")
					+ "','"
					+ request.getParameter("sname_s")
					+ "',"
					+ request.getParameter("status_s")
					+ ",'"
					+ request.getParameter("city_s")
					+ "');";
		}
		else if (request.getParameter("parts") != null) {
			query = "insert into parts values ('"
					+ request.getParameter("pnum_p")
					+ "','"
					+ request.getParameter("pname_p")
					+ "','"
					+ request.getParameter("color_p")
					+ "',"
					+ request.getParameter("weight_p")
					+ ",'"
					+ request.getParameter("city_p")
					+ "');";
		}
		else if (request.getParameter("jobs") != null) {
			query = "insert into jobs values ('"
					+ request.getParameter("jnum_j")
					+ "','"
					+ request.getParameter("jname_j")
					+ "',"
					+ request.getParameter("numworkers_j")
					+ ",'"
					+ request.getParameter("city_j")
					+ "');";
		}
		else if (request.getParameter("shipments") != null) {
			query = "insert into shipments values ('"
					+ request.getParameter("snum_sh")
					+ "','"
					+ request.getParameter("pnum_sh")
					+ "','"
					+ request.getParameter("jnum_sh")
					+ "',"
					+ request.getParameter("quantity_sh")
					+ ");";
		}
		System.out.println(query);
		try {
			message = queryHandler(query);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("message",  message);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
	    dispatcher.forward(request, response);      
	}
	private String queryHandler(String query) throws ClassNotFoundException, SQLException, IOException{
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
            String retMessage = "Successful Update.."+result+ " row(s) updated";
            System.out.println(retMessage);
            return retMessage;
        } catch (SQLException e) {
            String retMessage = "queryHandler ERROR: "+e.getMessage();
            System.out.println(retMessage);
            return retMessage;
        }
        //updateOperations(true, result);
        //connectDatabase();
    }
	public void connectDatabase() throws SQLException, IOException, ClassNotFoundException{// Load JDBC Driver
        Properties properties = new Properties();
		//FileInputStream fileDB = new FileInputStream("./WEB-INF/lib/root.properties");
        //properties.load(fileDB);
        MysqlDataSource dataSource = new MysqlDataSource();
        // Create connection and establish w/ database
		System.out.println("Connecting to database...");
        dataSource.setURL("jdbc:mysql://localhost:3306/project4");
        Class.forName("com.mysql.cj.jdbc.Driver");
        dataSource.setUser("dataentry");
        dataSource.setPassword("data123");
        connection = dataSource.getConnection();
        System.out.println("Database connected");

        // Print connection metadata
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("JDBC Driver name " + dbMetaData.getDriverName());
        System.out.println("JDBC Driver version " + dbMetaData.getDriverVersion());
        System.out.println("Driver Major version " + dbMetaData.getDriverMajorVersion());
        System.out.println("Driver Minor version " + dbMetaData.getDriverMinorVersion());
        System.out.println();

    }

}
