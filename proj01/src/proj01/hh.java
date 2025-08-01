package proj01;

import java.util.InputMismatchException;

public class hh {

	
	
	
	public static Rentable selectRentableMenu(String outId) throws CancelException {
		try {
//			boolean result=false;
			String id = outId;
			Rentable rentable;
			int selection = 1;
			while (selection != 0) {
				if (id.equals("-1")) {
					System.out.println("\n*************************************************");
					System.out.println("4 select a Rentable...");
					System.out.println("*************************************************");
					System.out.println("1) enter rentable id");
					System.out.println("2) list all available rentables");
					System.out.println("0) go back");
					System.out.println("select an option: ");
					selection = in.nextInt();
				} else {
					rentable = searchRentableByID(id, true);
					System.out.println("select rentable :" + rentable);
					return rentable;
				}
				switch (selection) {
				case 1:
					System.out.print("enter rentable id: ");
					id = String.valueOf(in.nextInt());
					rentable = searchRentableByID(id, true);
					System.out.println("select rentable :" + rentable);
					return typeOfRentable(rentable, false);
				case 2:
					reportMenu(6);
					break;
				case 0:
					System.out.println("going back...");
					return null;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return null;
	}
	
	public static Rentable searchRentable(String outId, boolean print) {
//		if (print) {
		System.out.println("\n*************************************************");
		System.out.println("search Rentable");
		System.out.println("*************************************************");
//		}
		boolean found = false;
		for (Rentable rentable : rentables) {
			if (rentable.getNumber().equals(outId)) {
//				if (print)
//					System.out.println("rentable found: " + typeOfRentable(rentable, false));
				found = true;
				if (found)
					System.out.println("search rentable:" + found);
				return rentable;
			}
		}

		if (!found && print)
			alertWindow("rentable not found");

		return null;
	}
	
	
	
	
	
	
}
