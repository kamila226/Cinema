package cinema;
import java.util.Scanner;
public class Cinema {
	
	public static int showMenu(Scanner scan){
		System.out.println();
		System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
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
        System.out.println("Enter a seat number in that row:");
        int  seatNum = scan.nextInt();
        while (rowNum > numRows || rowNum < 1 || seatNum > numCols || seatNum < 1) {
        	System.out.println("Wrong input!");
        	System.out.println("\nEnter a row number:");
            rowNum = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scan.nextInt();
        }
        
        while (seats[rowNum-1][seatNum-1] == "B") {
        	System.out.println("\nThat ticket has already been purchased!");
        	System.out.println("\nEnter a row number:");
            rowNum = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scan.nextInt();
        }
        
        seats[rowNum-1][seatNum-1] = "B";
        
        int ticketPrice = calculatePrice(seats, rowNum);
        System.out.printf("\n\nTicket price: $%d\n\n", ticketPrice);
        System.out.println(calculateCurrentIncome(seats));
        return seats;
	}
	
	public static int calculatePrice(String[][] seats, int row) {
		int ticketPrice;
		if (seats.length * seats[0].length <= 60) {
			ticketPrice = 10;
		} else {
			if (row <= (seats.length / 2)) {
				ticketPrice = 10;
			} else {
				ticketPrice = 8;
			}
		} 
		return ticketPrice;
	}
	
	public static void showStatistics(String[][] seats) {
		int ticketsNumber = purchasedTickets(seats);
		System.out.printf("Number of purchased tickets: %d%n", ticketsNumber);
		double percentage = calculatePercentage(seats);
		System.out.printf("Percentage: %.2f%%\n", percentage);
		int currentIncome = calculateCurrentIncome(seats);
		System.out.printf("Current income: $%d%n", currentIncome);
		int totalIncome = calculateTotalIncome(seats);
		System.out.printf("Total income: $%d%n", totalIncome);
	}
	
	public static int purchasedTickets(String[][] seats) {
		int num = 0;
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].equals("B")) {
					num++;
				}
			}
		}
		return num;
	}
	
	public static double calculatePercentage(String[][] seats) {
		double busySeatsPercentage = (double)purchasedTickets(seats) / (double)seats.length / (double)seats[0].length * 100.00;
		return busySeatsPercentage;
	}
	
	public static int calculateCurrentIncome(String[][] seats) {
		int currIncome = 0;
		int rowNum;
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].equals("B")) {
					rowNum = i+1;
					currIncome += calculatePrice(seats, rowNum);
				}
			}
		}
		return currIncome;
	}
	
	public static int calculateTotalIncome(String[][] seats) {
		int totalIncome = 0;
		if (seats.length * seats[0].length >= 60) {
			totalIncome = (seats.length / 2) * seats[0].length * 10 + (seats.length - seats.length / 2) * seats[0].length * 8;
		} else {
			totalIncome = seats.length * seats[0].length * 10;
		}
		return totalIncome;
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
			} else if (choice == 3) {
				showStatistics(seats);
				choice = showMenu(scan);
			} else {
				System.out.println("ERROR. Please try again");
				choice = showMenu(scan);
			}
		}
		
		System.out.println("Program is successfully closed.");
		scan.close();
	}

}
