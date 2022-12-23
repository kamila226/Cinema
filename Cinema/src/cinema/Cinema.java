package cinema;
import java.util.Scanner;
public class Cinema {
	
	public static int showMenu(Scanner scan){
		System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
        int choice = scan.nextInt();
        return choice;
    }
	
	public static void showSeats(int numRows, int numCols, String[][] seats) {
        
		System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 1; i <=numCols; i++) {
            System.out.print(" " + i);   
        }
        System.out.println();
        for (int i = 1; i <= numRows; i++) {
            System.out.print(i);
            for (int j = 0; j < numCols; j++) {
            		System.out.print(" " + seats[i-1][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
	
	public static String[][] reserveSeat(int numRows, int numCols, String[][] seats, Scanner scan) {
		
		System.out.println("\nEnter a row number:");
        int rowNum = scan.nextInt();
        while (rowNum > numRows || rowNum < 1) {
        	System.out.println("Row number is not correct. Please enter again.");
        	rowNum = scan.nextInt();
        }
        
        System.out.println("\nEnter a seat number in that row:");
        int  seatNum = scan.nextInt();
        while (seatNum > numCols || seatNum < 1) {
        	System.out.println("Column number is not correct. Please enter again.");
        	seatNum = scan.nextInt();
        }
        
        seats[rowNum-1][seatNum-1] = "B";
        printPrice(numRows, numCols, rowNum);
        return seats;
	}
	
	public static void printPrice(int numRows, int numCols, int row) {
		int ticketPrice;
        if (numRows * numCols <= 60){
            ticketPrice = 10;
        } else {
            if (row <= (numRows / 2)) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.printf("\n\nTicket price: $%d\n\n", ticketPrice);
	}
	
	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		int numRows, numCols;
        System.out.println("Enter the number of rows:");
        numRows = scan.nextInt();
        System.out.println("\nEnter the number of seats in each row:");
        numCols = scan.nextInt();
        
		int choice = showMenu(scan);
		
		// make the array of seats
		String[][] seats = new String[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				seats[i][j] = "S";
			}
			
		}
		
		while (choice != 0) {
			if (choice == 1) {
				showSeats(numRows, numCols, seats);
				choice = showMenu(scan);
			} else if (choice == 2) {
				seats = reserveSeat(numRows, numCols, seats, scan);
				choice = showMenu(scan);
			} else if (choice == 0) {
				break;
			} else {
				System.out.println("ERROR. Please try again");
				choice = showMenu(scan);
			}
		}
		
		System.out.println("Program is successfully closed.");
		scan.close();
	}

}
