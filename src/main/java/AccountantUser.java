
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class AccountantUser
 */
public class AccountantUser extends HttpServlet {
	Connection connection = null;
	private static final long serialVersionUID = 1L;
	ArrayList<String[]> records = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountantUser() {
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
			records = null;
			session.setAttribute("records",  records);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountantHome.jsp");
		    dispatcher.forward(request, response);      
		    return;
		}
		String query = "";
		//System.out.println(request.getParameter("getTotalWeight"));
		if (request.getParameter("rad").equals("option1")) {
			query = "SELECT MAX(status) FROM suppliers;";
		}
		else if (request.getParameter("rad").equals("option2")) {
			query = "SELECT SUM(weight) FROM parts";
		}
		else if (request.getParameter("rad").equals("option3")) {
			query = "SELECT COUNT(quantity) FROM shipments;";
		}
		else if (request.getParameter("rad").equals("option4")) {
			query = "SELECT jname, numworkers FROM jobs WHERE numworkers = (SELECT MAX(numworkers) FROM jobs);";
		}
		else if (request.getParameter("rad").equals("option5")) {
			query = "SELECT sname, status FROM suppliers;";
		}
		
		try {
			connectDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			records = selectHandler(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("records",  records);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountantHome.jsp");
	    dispatcher.forward(request, response);     
	}
	private ArrayList<String[]> selectHandler(String query) throws SQLException{
		if (connection == null){
			System.out.println("You need to be connected to a database first dummy");
		}
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        int i = 1;
        ArrayList<String[]> results = new ArrayList<String[]>();
    	String columnNames[] = new String[numberOfColumns];
        for (i=1; i<=numberOfColumns;i++){
        	columnNames[i-1] = metaData.getColumnName(i);
        }
        results.add(columnNames);
        while(resultSet.next()){
        	String row[] = new String[numberOfColumns];
            for (i = 1; i<=numberOfColumns;i++){
            	System.out.print(resultSet.getObject(i)+ " ");
            	row[i-1] = resultSet.getObject(i).toString();
            }
            results.add(row);
            System.out.println();
        }
        for(int j=0;j<results.size();j++) {
        	System.out.println(Arrays.toString(results.get(j)));
        }
		return results;
		
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
        dataSource.setUser("accountant");
        dataSource.setPassword("account123");
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
