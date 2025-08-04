package proj01.controllers;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import proj01.Car;
import proj01.InfoSys;
import proj01.Operation;
import proj01.RealEstate;

public class HomeController {

	@FXML
	private LineChart<?, ?> lineChart;

	@FXML
	private AreaChart<?, ?> areaChart;

	@FXML
	private Label carsRevenueLabel, totalRevenueLabel, realEstateRevenueLabel, numberOfRentedRealEstatesLabel,
			numberOfRentedCarsLabel;

	private PrintStream x = System.out;
	private PrintStream y = System.err;

	private Stage stage;

	public void setStage(Stage stage){
		this.stage=stage;
	}

	@FXML
	public void initilize() {
		if (!InfoSys.operations.isEmpty()) {
			// Initialize main screen lables and charts
			if (carsListInfo() != null && realEstateListInfo() != null) {// if true then infosys.operations is not empty
				int carsRevenue = carsListInfo()[0];
				int realEstatesRevenue = realEstateListInfo()[0];
				int totalRevenue = carsRevenue + realEstatesRevenue;

				carsRevenueLabel.setText("$" + String.valueOf(carsRevenue));
				realEstateRevenueLabel.setText("$" + String.valueOf(realEstatesRevenue));
				totalRevenueLabel.setText("Revenue $" + String.valueOf(totalRevenue));

				numberOfRentedCarsLabel.setText(carsListInfo()[1] + " out of " + carsListInfo()[2]);
				numberOfRentedRealEstatesLabel.setText(realEstateListInfo()[1] + " out of " + realEstateListInfo()[2]);

				XYChart.Series realEstatesSeries = new XYChart.Series();
				XYChart.Series carsSeries = new XYChart.Series();
				realEstatesSeries.setName("RealEstate");
				carsSeries.setName("Car");

				// calculate revenue per month for the past 12 months
				int[] carsRevenuePerMonth = new int[12];
				int[] realEstateRevenuePerMonth = new int[12];
				String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
						"Dec" };
				int lastOperationDateYear = InfoSys.operations.getLast().getOperationDate().getYear();
				int pastYear = lastOperationDateYear - 1;
				int lastOperationDateMonth = InfoSys.operations.getLast().getOperationDate().getMonth();
				int i = 1;// counter/index
				int pointerCurrentMonth = 0, pointerPreviousMonth = 0;
				int carRevenueSumPerMonth = 0, realEstateRevenueSumPerMonth = 0;
				boolean f = true;
				boolean empty = isEmpty();
				boolean end = isEndOfList(i);
				boolean TwelveMonths = isTwelveMonths(lastOperationDateYear, pastYear, pointerCurrentMonth,
						lastOperationDateMonth);
				for (Operation operation : InfoSys.operations.reversed()) {

					if (TwelveMonths || end || empty) {
						break;
					}
					System.out.println(operation);
					lastOperationDateYear = operation.getOperationDate().getYear();
					pointerCurrentMonth = operation.getOperationDate().getMonth();

					if (operation.getRentable()instanceof Car car) {

						if (pointerCurrentMonth == pointerPreviousMonth) {
							carRevenueSumPerMonth += car.getMonthlyPrice();
						} else {
							carRevenueSumPerMonth = car.getMonthlyPrice();
						}
						carsRevenuePerMonth[pointerCurrentMonth] = carRevenueSumPerMonth;
						pointerPreviousMonth = pointerCurrentMonth;

						i++;
					} else if (operation.getRentable()instanceof RealEstate realEstate) {

						if (pointerCurrentMonth == pointerPreviousMonth) {
							realEstateRevenueSumPerMonth += realEstate.getMonthlyPrice();
						} else {
							realEstateRevenueSumPerMonth = realEstate.getMonthlyPrice();
						}
						realEstateRevenuePerMonth[pointerCurrentMonth] = realEstateRevenueSumPerMonth;
						pointerPreviousMonth = pointerCurrentMonth;
						i++;
					}

					TwelveMonths = isTwelveMonths(lastOperationDateYear, pastYear, pointerCurrentMonth,
							lastOperationDateMonth);
					end = isEndOfList(i);
					empty = isEmpty();
				}

				i = 0;
				for (; 12 > i; i++) {
					carsSeries.getData().add(new XYChart.Data(months[i], carsRevenuePerMonth[i]));
					realEstatesSeries.getData().add(new XYChart.Data(months[i], realEstateRevenuePerMonth[i]));
				}
				i = 1;// reset counter
				lineChart.getData().addAll(realEstatesSeries);
				lineChart.getData().addAll(carsSeries);
//				lineChart.setTitle("Revenue For The Past 12 Months");
			}
		} else {
			carsRevenueLabel.setText("$0");
			realEstateRevenueLabel.setText("$0");
			totalRevenueLabel.setText("Revenue $0");
			numberOfRentedCarsLabel.setText("0 out of 0");
			numberOfRentedRealEstatesLabel.setText("0 out of 0");
		}
	}

	boolean isEmpty() {
		return InfoSys.operations.isEmpty();
	}

	boolean isEndOfList(int i) {
		return i > InfoSys.operations.size();
	}

	boolean isTwelveMonths(int lastOperationDateYear, int pastYear, int pointerCurrentMonth,
			int lastOperationDateMonth) {
		return (lastOperationDateYear == pastYear && pointerCurrentMonth == lastOperationDateMonth);
	}

	/**
	 * calculates the total generated revenue by cars from all operations. and
	 * calculates the number of rented cars out of the total cars.
	 *
	 * @return a list index: 0)total cars revenue. 2) unavailable cars count. 3)
	 *         total cars count.
	 */
	private int[] carsListInfo() {
		if (!(InfoSys.rentables.isEmpty())) {
			List<Car> carsList = InfoSys.rentables.stream().filter(x -> x.getClass().getSimpleName().equals("Car"))
					.map(x -> (Car) x).collect(Collectors.toList());
			List<Car> unavailableCarsList = carsList.stream().filter(x -> !(x.isStatus())).collect(Collectors.toList());

			int carsRevenue = unavailableCarsList.stream().map(x -> x.getMonthlyPrice()).reduce(0, Integer::sum);// map(x->((RealEstate)x).getMonthlyPrice())
			int unavailableCarsCount = unavailableCarsList.size();
			int[] carsListInfo = { carsRevenue, unavailableCarsCount, carsList.size() };
			return carsListInfo;
		} else {
			return null;
		}
	}

	private int[] realEstateListInfo() {
		if (!(InfoSys.rentables.isEmpty())) {
			List<RealEstate> realEstatesList = InfoSys.rentables.stream()
					.filter(x -> x.getClass().getSimpleName().equals("RealEstate")).map(x -> (RealEstate) x)
					.collect(Collectors.toList());
			List<RealEstate> unavailableRealEstateList = realEstatesList.stream().filter(x -> !(x.isStatus()))
					.collect(Collectors.toList());
			int realEstateRevenue = unavailableRealEstateList.stream().map(x -> x.getMonthlyPrice()).reduce(0,
					Integer::sum);
			int unavailableRealEstateCount = unavailableRealEstateList.size();
			int[] realEstateListInfo = { realEstateRevenue, unavailableRealEstateCount, realEstatesList.size() };
			return realEstateListInfo;
		} else {
			return null;
		}
	}

}
