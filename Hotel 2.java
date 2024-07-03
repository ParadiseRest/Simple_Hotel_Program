import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Hotel {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int roomNum = 0;
		Room[] hotel = new Room[9];
		for (int i = 0; i < 9; i++)
			hotel[i] = new Room();
		while (true) {
			System.out.println("A: Add customer\r\n" + "V: View rooms\r\n" + "E: Display Empty rooms\r\n"
					+ "D: Delete customer from room\r\n" + "F: Find room from customer name\r\n"
					+ "S: Store program data into file\r\n" + "L: Load program data from file\r\n"
					+ "O: View guests Ordered alphabetically by name\r\n" + "Any other key: End\r\n" + "Enter Choice");
			char ch = input.next().charAt(0);
			boolean flag = false;
			switch (ch) {
			case 'A':
				System.out.println("Enter room number (0-7) :");
				roomNum = input.nextInt();
				add_customer(hotel, roomNum);
				break;
			case 'V':
				view_rooms(hotel);
				break;
			case 'E':
				disp_emp_rooms(hotel);
				break;
			case 'D':
				System.out.println("Enter room number (0-7) :");
				roomNum = input.nextInt();
				delt_cust(hotel, roomNum);
				break;
			case 'F':
				System.out.println("Enter name:");
				String roomName = input.next();
				roomNum = find_cust(hotel, roomName);
				if (roomNum == -1)
					System.out.println("Name not present");
				else
					System.out.println("Room :" + roomNum);
				break;
			case 'O':
				view_cust_order(hotel);
				break;
			case 'S':
				store_file(hotel);
				break;
			case 'L':
				load_file();
				break;
			default:
				flag = true;
			}
			if (flag)
				break;

		}
	}

	private static void view_rooms(Room hotelRef[]) {
		for (int x = 0; x < 8; x++) {
			if (hotelRef[x].get_roomName() == ("e"))
				System.out.println("room " + x + " is empty");
			else
				System.out.println("room " + x + " occupied by " + hotelRef[x].get_roomName());

		}
		System.out.println();
	}

	private static void store_file(Room hotelRef[]) throws IOException {

		File myObj = new File("hotel_db.txt");
		myObj.createNewFile();
		FileWriter myWriter = new FileWriter(myObj.getName());
		for (int x = 0; x < 8; x++)
			myWriter.write(x + "\n" + hotelRef[x].get_roomName() + "\n");
		myWriter.close();
		System.out.println("Added Successfully");

	}

	private static void load_file() throws IOException {

		File myObj = new File("hotel_db.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String data1 = myReader.nextLine();
			System.out.println(data + "   " + data1);
		}
		myReader.close();

	}

	private static void disp_emp_rooms(Room hotelRef[]) {
		for (int x = 0; x < 8; x++) {
			if (hotelRef[x].get_roomName().equals("e"))
				System.out.println("room " + x + " is empty");
		}
		System.out.println();
	}

	private static void view_cust_order(Room hotelRef[]) {
		boolean[] visited = new boolean[9];
		for (int x = 0; x < 8; x++) {
			String name = "" + (char) (123);
			int p = -1;
			for (int y = 0; y < 8; y++) {
				if (visited[y] == false && hotelRef[y].get_roomName().compareTo("e") != 0
						&& hotelRef[y].get_roomName().compareTo(name) < 0) {
					name = hotelRef[y].get_roomName();
					p = y;
				}
			}
			if (p != -1) {
				visited[p] = true;
				System.out.println(name);
			}
		}

	}

	private static int find_cust(Room hotelRef[], String name) {
		for (int x = 0; x < 8; x++) {
			if (hotelRef[x].get_roomName().equals(name))
				return x;
		}
		return -1;
	}

	private static void delt_cust(Room hotelRef[], int n) {
		hotelRef[n].set_roomName("e");
	}

	private static void add_customer(Room hotelRef[], int n) {
		if (n > 7)
			return;
		Scanner input = new Scanner(System.in);
		String roomName;
		System.out.println("Enter name for room " + n + " :");
		roomName = input.next();
		hotelRef[n].set_roomName(roomName);
	}

}

class Room {
	String roomName;

	Room() {
		roomName = "e";
	}

	void set_roomName(String str) {
		roomName = str;
	}

	String get_roomName() {
		return roomName;
	}

}