package DbProject;

import java.util.Scanner;

public class BookTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		BookDAO dao = BookDAO.getInstance();
		BookDTO dto = null;
		
		System.out.print("입력 데이터 : ");
		String[] arr = sc.nextLine().split("[\\s]+");
		System.out.println(arr.length);
		dto = new BookDTO();
		dto.setBookNo(arr[0]);
		dto.setBookTitle(arr[1]);
		dto.setBookAuthor(arr[2]);
		dto.setBookYear(Integer.parseInt(arr[3]));
		dto.setPrice(Integer.parseInt(arr[4]));
		dto.setBookPublisher(arr[5]);
		
		dao.insertBook(dto);
		dao.selectBook();
	}
}
