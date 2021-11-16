package DbProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {
	
	private Connection conn;
	private Statement stat;
	private ResultSet res ; 
	private PreparedStatement pstat;
	
	private static BookDAO dao = new BookDAO();
	
	private BookDAO() {}
	
	public static BookDAO getInstance() {
		return dao;
	}
	
	
	public void insertBook(BookDTO bookDTO) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/book?useUnicode=true&serverTimezone=UTC";
			String user = "root";
			String pwd = "1234";
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = String.format("insert into book values(?,?,?,?,?,?);", 
					bookDTO.getBookNo(),bookDTO.getBookTitle(),bookDTO.getBookAuthor(),
					bookDTO.getBookYear(),bookDTO.getPrice(),bookDTO.getBookPublisher());
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bookDTO.getBookNo());
			pstat.setString(2, bookDTO.getBookTitle());
			pstat.setString(3, bookDTO.getBookAuthor());
			pstat.setInt(4, bookDTO.getBookYear());
			pstat.setInt(5, bookDTO.getPrice());
			pstat.setString(6, bookDTO.getBookPublisher());
			pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void selectBook() {
		try {
			String url = "jdbc:mysql://localhost:3306/book?useUnicode=true&serverTimezone=UTC";
			String user = "root";
			String pwd = "1234";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,user,pwd);
			stat = conn.createStatement();
			
			res = stat.executeQuery("select * from book");
			
			while(res.next()) {
				System.out.printf("%10s %10s %10s %10d %10d %10s\n", res.getString("bookNo"),
						res.getString("bookTitle"),res.getString("bookAuthor"),
						res.getInt("bookYear"),res.getInt("bookPrice"),res.getString("bookPublisher"));
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(res != null) {
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
