package proj01;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Date implements Serializable {
	private static final long serialVersionUID = 1L; // Good practice for serialization

	private int day;
	private int month;
	private int year;
	private transient Scanner in = new Scanner(System.in);
	int tmp = 1;

	public Date() {
		super();
	}

	/**
	 * this constructor creates date object of the current date the flag parameter
	 * is just to differentiate it from other constructors and it's not used.
	 *
	 * @param flag =any value
	 */
	public Date(String flag) {
		if (flag.contains("/")) {
			String str[]=flag.splitWithDelimiters("/", 0);
			day=Integer.valueOf(str[0]);
			month=Integer.valueOf(str[2]);
			year=Integer.valueOf(str[4]);
		} else {
			day = LocalDate.now().getDayOfMonth();
			month = LocalDate.now().getMonthValue();
			year = LocalDate.now().getYear();
		}
	}

	public Date(Date date) {
		if (date != null) {
			this.day = date.getDay();
			this.month = date.getMonth();
			this.year = date.getYear();
		}
	}

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setDay(int day) {
		try {
			this.day = day;
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
		}
	}

	public void setMonth(int month) {
		try {
			this.month = month;
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
		}

	}

	public void setYear(int year) {
		try {
			this.year = year;
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
		}
	}

	/**
	 *
	 * @param manual true=enter date manually, false=enter automatically current
	 *               date
	 * @throws CancelException
	 */
	public Date(boolean manual) throws CancelException {
		if (manual) {
			setDay(manual);
			setMonth(manual);
			setYear(manual);
		} else {
			setDay();
			setMonth();
			setYear();
		}
		check();
	}

	public void check() {
		if (day == 0 || month == 0 || year == 0) {
			System.out.println("date not created");
			this.day = 0;
			this.month = 0;
			this.year = 0;
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay() {
		this.day = LocalDate.now().getDayOfMonth();
//		System.out.println(this.day);
	}

	public void setDay(boolean manual) throws CancelException {
		tmp = 1;
		while (tmp != 0) {
			System.out.print("enter the day or 0 to go back: ");
			try {
				tmp = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(e);
				in.next(); // <--- CRITICAL: Consume the invalid token ("e")
				continue; // Go to the beginning of the while loop to re-prompt
			}
			if (tmp == 0) {
				throw new CancelException(this.getClass() + " you entered 0 to exit");
			}

			if (tmp > 0 && tmp < 32) {
				this.day = tmp;
				break;
			} else {
				System.out.println("invalid day it must be betwwen 1-31, enter again the day. enter 0 to exit.");
			}
		}

	}

	public int getMonth() {
		return month;
	}

	public void setMonth() {
		this.month = LocalDate.now().getMonthValue();
//		System.out.println(this.month);
	}

	public void setMonth(boolean manual) throws CancelException {
		tmp = 1;
		while (tmp != 0) {
			System.out.print("enter the month or 0 to go back: ");
			try {
				tmp = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(e);
				in.next(); // <--- CRITICAL: Consume the invalid token ("e")
				continue; // Go to the beginning of the while loop to re-prompt
			}
			if (tmp == 0) {
				throw new CancelException(this.getClass() + " you entered 0 to exit");
			}
			if (tmp > 0 && tmp < 13) {
				this.month = tmp;
				break;
			} else {
				System.out.println("invalid month, it must be betwwen 1-12, enter again the month. enter 0 to exit.");
			}
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear() {
		this.year = LocalDate.now().getYear();
	}

	public void setYear(boolean manual) throws CancelException {
		tmp = 1;
		while (tmp != 0) {
			System.out.print("enter the year or 0 to go back: ");
			try {
				tmp = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(e);
				in.next(); // <--- CRITICAL: Consume the invalid token ("e")
				continue; // Go to the beginning of the while loop to re-prompt
			}
			if (tmp == 0) {
				throw new CancelException(this.getClass() + " you entered 0 to exit");
			}

			if (tmp > 1920) {
				this.year = tmp;
				break;
			} else {
				System.out.println(
						"invalid year it must be between 1920-(current year), enter again the year. \nenter 0 to exit.");
			}
		}

	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}
}
