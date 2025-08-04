package proj01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import proj01.controllers.MainController;

public class archive {

	
	//removedOperation operationsTable.getSelectionModel().getSelectedItem();
	//operationsObservableList.remove(operation);
	//InfoSys.returnRentable(removedOperation.getCustomer(),removedOperation.getRentable(),removedOperation);

	//int row = operationsTable.getSelectionModel().getSelectedIndex();
	//if (row >= 0) {
//		Operation removedOperation = operationsTable.getItems().get(row);
//		Rentable rentable = removedOperation.getRentable();
//		Customer customer = removedOperation.getCustomer();
//		if (InfoSys.removeObject(removedOperation)) {
//			removedOperation = operationsTable.getItems().remove(row);
//			operationsTable.getSelectionModel().clearSelection();
//			operationsObservableList.remove(removedOperation);
//		}
	//}
	//operationsTable.refresh();
	//customersTable.refresh();
	//rentablesTable.refresh();
	
	@FXML
	void onGoBack(ActionEvent event) throws Exception {

		MainController controller = master("/proj01/fx/designMain.fxml").getController();
		controller.setStage(stage);
	}
	
//	String str = rentablesTable.getSelectionModel().getSelectedItem().getImage();
//	Image image= new Image(str);
//	imageView.setImage(image);
//	imageView.fitHeightProperty().bind(imageView.getParent().heightProperty());
//	imageView.fitHeightProperty();

//	rentablesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//		// 'newValue' is the Rentable object that was just selected
//		if (newValue != null) {
//			Image image = newValue.getImage(); // Get the Image from the selected Rentable
//			if (image != null) {
//				if (image.getWidth() > image.getHeight()) {
//					System.out.println("111111");
//					imageView.fitWidthProperty().bind(imageStackPane.widthProperty());
//				} else {
//					System.out.println("222222");
//					imageView.fitHeightProperty().bind(imageStackPane.heightProperty());
//				}
//
//				imageView.setImage(image);
//			} else {
//				imageView.setImage(null); // Clear image if loading failed
//			}
//		} else {
//			// No item is selected (e.g., if selection is cleared)
//			imageView.setImage(null);
//		}
//	});
	
//	// search
//	FilteredList<Rentable> rentableFilteredData = new FilteredList<>(rentablesObservableList, p -> true);
//	rentableIdField.textProperty().addListener((observable, oldValue, newValue) -> {
//		rentableFilteredData.setPredicate(rentable -> {
//			// If filter text is empty, display all persons.
//			if (newValue == null || newValue.isEmpty()) {
//				return true;
//			}
//
//			// Compare car details with filter text.
//			String lowerCaseFilter = newValue.toLowerCase();
//			if (rentable instanceof Car car) {
//				if (rentable.getNumber().toLowerCase().contains(lowerCaseFilter)) {
//					return true;
//				} else if (String.valueOf(car.getPlateNo()).contains(lowerCaseFilter)) {
//					return true;
//				} else if (car.getBrand().contains(lowerCaseFilter)) {
//					return true;
//				} else if (String.valueOf(car.getMonthlyPrice()).contains(lowerCaseFilter)) {
//					return true;
//				} else if (String.valueOf(car.isStatus()).contains(lowerCaseFilter)) {
//					return true;
//					// compare realEstate details
//				}
//			} else if (rentable instanceof RealEstate realEstate) {
//				if (realEstate.getType().contains(lowerCaseFilter)) {
//					return true;
//				} else if (String.valueOf(realEstate.getMonthlyPrice()).contains(lowerCaseFilter)) {
//					return true;
//				} else if (String.valueOf(realEstate.isStatus()).contains(lowerCaseFilter)) {
//					return true;
//				}
//			}
//			return false; // Does not match.
//		});
//	});

//	FilteredList<Customer> customersFilteredData = new FilteredList<>(customersObservableList, p -> true);
//
//	// 6. Add a listener to the text property of the search field
//	customerIdField.textProperty().addListener((observable, oldValue, newValue) -> {
//		customersFilteredData.setPredicate(customer -> {
//			// If filter text is empty, display all persons.
//			if (newValue == null || newValue.isEmpty()) {
//				return true;
//			}
//
//			// Compare person's details with filter text.
//			String lowerCaseFilter = newValue.toLowerCase();
//
//			if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
//				return true; // Filter matches name.
//			} else if (String.valueOf(customer.getNoOfCars()).contains(lowerCaseFilter)) {
//				return true; // Filter matches email.
//			} else if (String.valueOf(customer.getId()).contains(lowerCaseFilter)) {
//				return true; // Filter matches id.
//			}
//			return false; // Does not match.
//		});
//	});
//	rentablesObservableList.addListener((ListChangeListener.Change<? extends Rentable> change)-> {
//		noOfCars.setText(String.valueOf(rentablesObservableList.stream().filter(Car.class::isInstance).count()));
//
//	});
	
//	void openImageInNewWindow(ImageView originalImageView) {
//    ImageView imgV = new ImageView(originalImageView.getImage());
//
//    if (imgV.getImage() != null) {
//        imgV.setPreserveRatio(true);
//
//        // 1. Create a Group to hold the ImageView.
//        // The Group's layout bounds will automatically resize when the
//        // scale of its content changes, which the ScrollPane needs.
//        Group innerGroup = new Group(imgV);
//
//        // 2. Create a ScrollPane and place the Group inside.
//        ScrollPane scrollPane = new ScrollPane(innerGroup);
//        
//        // This is no longer necessary as the Group will handle the size.
//        // scrollPane.setFitToWidth(true);
//        // scrollPane.setFitToHeight(true);
//
//        // 3. Set the initial scale for the ImageView.
//        // You might want to remove this if you want the image to open at 100% size.
//        if (imgV.getImage().getWidth() > imgV.getImage().getHeight()) {
//            imgV.fitWidthProperty().bind(scrollPane.widthProperty());
//        } else {
//            imgV.fitHeightProperty().bind(scrollPane.heightProperty());
//        }
//
//        // 4. Add the zoom functionality to the ScrollPane.
//        // We will change the scale of the Group, not the ImageView directly.
//        scrollPane.addEventFilter(ScrollEvent.ANY, event -> {
//            double zoomFactor = 1.05;
//            if (event.getDeltaY() < 0) {
//                zoomFactor = 1 / zoomFactor;
//            }
//
//            // Get the current scale
//            double newScale = innerGroup.getScaleX() * zoomFactor;
//
//            // Apply the new scale, but clamp it to a reasonable range
//            if (newScale >= 0.1 && newScale <= 10.0) { // Example range
//                // Get the coordinates of the mouse within the ScrollPane
//                Point2D mousePosition = new Point2D(event.getX(), event.getY());
//                
//                // Adjust the pivot point for the zoom to be at the mouse cursor
//                scrollPane.setHvalue(calculateScrollValue(scrollPane.getHvalue(), scrollPane.getWidth(), mousePosition.getX(), zoomFactor));
//                scrollPane.setVvalue(calculateScrollValue(scrollPane.getVvalue(), scrollPane.getHeight(), mousePosition.getY(), zoomFactor));
//
//                innerGroup.setScaleX(newScale);
//                innerGroup.setScaleY(newScale);
//            }
//            
//            event.consume();
//        });
//        
//        // Optional: add a ZoomEvent handler for touch devices
//        scrollPane.addEventFilter(ZoomEvent.ANY, event -> {
//            double zoomFactor = event.getZoomFactor();
//            double newScale = innerGroup.getScaleX() * zoomFactor;
//
//            if (newScale >= 0.1 && newScale <= 10.0) {
//                innerGroup.setScaleX(newScale);
//                innerGroup.setScaleY(newScale);
//            }
//            event.consume();
//        });
//
//        Scene scene = new Scene(scrollPane, 600, 500);
//        Stage newWindow = new Stage();
//        newWindow.setScene(scene);
//        newWindow.show();
//    }
//}
//
//// Helper method to calculate the new scroll position to keep the content centered
//private double calculateScrollValue(double currentScrollValue, double scrollPaneSize, double mousePosition, double zoomFactor) {
//    double newScrollValue = currentScrollValue + (mousePosition / scrollPaneSize - currentScrollValue) * (1 - zoomFactor);
//    return Math.max(0.0, Math.min(1.0, newScrollValue));
//}

//void openImageInNewWindow(ImageView originalImageView) {
//
//    ImageView imgV = new ImageView(originalImageView.getImage());
//    if (imgV.getImage() != null) {
//        
//        imgV.setPreserveRatio(true);
//
//        // Use a StackPane as the content of the ScrollPane.
//        // This is a good practice to ensure centering and correct sizing.
//        StackPane container = new StackPane(imgV);
//        
//        // Use a ScrollPane and set the StackPane as its content
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setContent(container);
//
//        // Tell the ScrollPane to resize its content to fit its bounds.
//        // This handles the initial sizing of the image.
//        scrollPane.setFitToWidth(true);
//        scrollPane.setFitToHeight(true);
//
//        // Bind the ImageView's fit properties to the container's size.
//        // This ensures the image initially fills the view.
//        if (imgV.getImage().getWidth() > imgV.getImage().getHeight()) {
//            imgV.fitWidthProperty().bind(scrollPane.widthProperty());
//        } else {
//            imgV.fitHeightProperty().bind(scrollPane.heightProperty());
//        }
//
//        // Add the zoom functionality
//        scrollPane.setOnScroll(event -> {
//            double zoomFactor = 1.05;
//            if (event.getDeltaY() < 0) {
//                zoomFactor = 1 / zoomFactor;
//            }
//            
//            // Apply the zoom to the ImageView's scale
//            imgV.setScaleX(imgV.getScaleX() * zoomFactor);
//            imgV.setScaleY(imgV.getScaleY() * zoomFactor);
//            
//            event.consume();
//        });
//        
//        // Show the window with the ScrollPane as the root
//        Scene scene = new Scene(scrollPane, 600, 500);
//        newWindow.setScene(scene);
//        newWindow.show();
//    }
//}

//void openImageInNewWindow(ImageView originalImageView) {
//	
//	ImageView imgV = new ImageView(originalImageView.getImage());
//	
//	if (imgV.getImage() != null) {
//		imgV.setPreserveRatio(true);
//		StackPane anchor = new StackPane(imgV);
//		if (imgV.getImage().getWidth() > imgV.getImage().getHeight()) {
//			imgV.fitWidthProperty().bind(anchor.widthProperty());
//		} else {
//			imgV.fitHeightProperty().bind(anchor.heightProperty());
//		}
//		
//		// Handle touch-based zoom gestures
//        imgV.setOnZoom(event -> {
//        	System.out.println("zoom touch");
//            double zoomFactor = event.getZoomFactor();
//            imgV.setScaleX(imgV.getScaleX() * zoomFactor);
//            imgV.setScaleY(imgV.getScaleY() * zoomFactor);
//            event.consume(); // Mark the event as handled
//        });
//
//        // Handle mouse wheel scrolling for zoom
//        imgV.setOnScroll(event -> {
//        	System.out.println("zoom mouse");
//
//            double deltaY = event.getDeltaY();
//            // Adjust the zoom factor based on the scroll amount.
//            // A small value like 1.01 provides a smooth zoom.
//            double zoomFactor = (deltaY > 0) ? 1.05 : 1 / 1.05;
//            
//            // Check for a keyboard modifier (like CTRL) to prevent
//            // accidental zooming from a simple scroll.
//            if (event.isControlDown()) {
//                imgV.setScaleX(imgV.getScaleX() * zoomFactor);
//                imgV.setScaleY(imgV.getScaleY() * zoomFactor);
//            }
//            event.consume(); // Mark the event as handled
//        });
//		
//		imgV.setOnZoom(event -> {
//			double zoomFactor = event.getZoomFactor();
//			imgV.setScaleX(imgV.getScaleX() * zoomFactor);
//			imgV.setScaleY(imgV.getScaleY() * zoomFactor);
//		});
//
//		Scene scene = new Scene(anchor, 600, 500);
//		newWindow.setScene(scene);
//		newWindow.show();
//	}
//}

	/**
	 * if operation is at the end delete normally. if in the middle provide
	 * replacement operation.
	 *
	 * @param operationOut
	 * @param operationReplacment
	 * @return
	 */
	public static boolean deleteOperation(Operation operationOut, Operation operationReplacment)
			throws CancelException {
		System.out.println("\n*************************************************");
		System.out.println("delete operation...");
		System.out.println("*************************************************");
		boolean result = false;
		Operation operationBackup;
		Rentable rentable = operationOut.getRentable();
		Customer customer = operationOut.getCustomer();
//		Operation operation1;
		Operation operation2;
		String operation2Type;
		operation2Type = operationOut.getOperationType();

		if (operations.contains(operationOut)) {
			if (operationOut.getOperationType().equals("rent")) {// type = rent
//				if (searchOperationById(operationOut.getId()*-1).getId() == operationOut.getId()) {// 1 rent at end//i need to find weather this operation is the last for the same customer and rentable but this logic is wrong
				if (searchOperationById(operationOut.getId() * -1) == null) {// 1 this mean their is no return operation
																				// found
//					operationBackup = new Operation(operations.getLast());
					operationBackup = new Operation(operationOut);
					System.out.println("backup operation: " + operationBackup);
					if (returnOperation(operations.getLast().getCustomer(), operations.getLast().getRentable()) != null) {
						result = true;
					}// it takes care of status and customer
					if (!result) {
						operations.add(operationBackup);
						System.out.println("couldn't delete operation: " + operationBackup);
						System.out.println("deleteOperation(): " + result);
						return false;
					} else {
						System.out.println("deleteOperation(): " + result);
						operations.remove(operationOut);
						return result;
					}
				} // 1 rent at end

				else {// 3 rent in middle
					operation2Type = "return";
					operation2 = searchOperationByCustomerAndRentable(customer, rentable, null, operation2Type);
					if (operation2 != null) {
						for (Operation operation : operations) {
							if (operation2.getId() == operation.getId()) {
								operationBackup = new Operation(operationOut);
								if (returnOperation(operationOut.getCustomer(), operationOut.getRentable()) != null) {
									result = true;
								}
								if (!result) {// if failed to return then you can't delete op so add it back
									operations.add(operationBackup);
									System.out.println("couldn't delete operation: " + operationBackup);
									return false;
								}
								operationBackup = new Operation(operation2);
								if (returnOperation(operation2.getCustomer(), operation2.getRentable()) != null) {
									result = true;
								}
								if (!result) {// if failed to return then you can't delete op so add it back
									operations.add(operationBackup);
									System.out.println("couldn't delete operation: " + operationBackup);
									return false;
								} else {
									operations.remove(operationOut);
									operations.remove(operation2);
									return result;
								}
							}
						}
					} // search for op2 by id
				} // 3

			} else {// type = return

				if (operations.getLast().getId() == operationOut.getId()) {// 2 return at end
					operationBackup = new Operation(operations.getLast());
					if (returnOperation(operations.getLast().getCustomer(), operations.getLast().getRentable()) != null) {
						result = true;
					}
					if (!result) {// if failed to return then you can't delete op so add it back
						operations.add(operationBackup);
						System.out.println("couldn't delete operation: " + operationBackup);
						return false;
					} else {
						operations.remove(operationOut);
						return result;
					}
				} // 2 return at end

				else {// 4 return in middle

					if (!operations.contains(operationReplacment)) {// not contains replacement
						operationBackup = new Operation(operationOut);
						if (returnOperation(operationOut.getCustomer(), operationOut.getRentable()) != null) {
							result = true;
						}
						if (!result) {// if failed to return then you can't delete op so add it back
							operations.add(operationBackup);
							System.out.println("couldn't delete operation: " + operationBackup);
							return false;
						} else {
							operations.remove(operationOut);
							operations.add(operationReplacment);
							return result;
						}
					} // not contains replacement

				} // 4 return in middle

			} // type

		} // contains
		return result;

	}


//  private static final Logger LOGGER = Logger.getLogger(InfoSys.class.getName());

	//main
//	// Set a default uncaught exception handler for all threads
//  Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//      @Override
//      public void uncaughtException(Thread t, Throwable e) {
//          LOGGER.log(Level.SEVERE, "Uncaught exception in thread " + t.getName(), e);
//          System.err.println("An unexpected error occurred: " + e.getMessage());
//          // Here you can:
//          // 1. Log the full stack trace to a file.
//          // 2. Display a user-friendly error message (e.g., JOptionPane).
//          // 3. Attempt to save critical unsaved data (if possible and safe).
//          // 4. Potentially perform a graceful shutdown of resources.
//          // Note: The thread where the exception occurred will likely terminate.
//          // But the main application might stay alive.
//      }
//  });

//	/**
//	 * probably the one that is used
//	 *
//	 * @param operation
//	 * @param customer
//	 * @param rentable
//	 * @return return operation id or -1 if failed
//	 * @throws CancelException
//	 */
//	public static int returnOperation(Operation operation, Customer customer, Rentable rentable)
//			throws CancelException {// ?? //
//		// addOperation()
//		System.out.println("\n*************************************************");
//		System.out.println("return operation 2...");
//		System.out.println("*************************************************");
//		Operation targetOperation = operation;
//		if (rentable != null && customer != null) {
//			if (rentable.isStatus()) {
//				alertWindow("rentable is available so it can't be returned");
//				System.out.println("rentable is available so it can't be returned");
//				return -1;
//			}
//			if (targetOperation == null)
//				targetOperation = searchOperationByCustomerAndRentable(customer, rentable, null, "");
//			if (targetOperation != null) {
//				System.out.println("--------operation found--------");
//				System.out.println(targetOperation);
//				customer.returnRentable(rentable);
//				rentable.setStatus(true);
//				operations.add(new Operation(targetOperation.getId(), customer, rentable, new Date("auto"), "return"));
//				ongoingOperations.remove(targetOperation);
//				return operation.getId();
//			}
////				}
////			}
//		} else {
//			alertWindow("customer/rentable null/not found");
//			System.out.println("customer/rentable null/not found");
//		}
//		alertWindow("--------operation not found--------\n" + "--------return operation failed--------");
//		System.out.println("--------operation not found--------\n" + "--------return operation failed--------");
////		System.out.println("customer/rentable null/not found");
////		System.out.println("--------return operation failed--------");
//		return -1;
//	}

//	/**
//	 * probably not working coz i don't see add operation
//	 *
//	 * @param operationOut
//	 * @return
//	 */
//	public static boolean returnOperation(Operation operationOut) {// ??
//		System.out.println("\n*************************************************");
//		System.out.println("return operation...");
//		System.out.println("*************************************************");
//		boolean result = false;
////		Operation operationTemp;
//		if (operations.contains(operationOut)) {
//			String type = operationOut.getOperationType();
//			if (type.equals("rent")) {
//				for (Operation operationIterator : operations.reversed()) {// search
//					if (operationIterator.equals(operationOut)
//							&& operationIterator.getOperationType().equals("return")) {
////						result = operations.remove(operationIterator) && operations.remove(operationOut);
////						System.out.println(result);
////						return result;
//						System.out.println(operationIterator);
//					}
//				}
//			} else {
//
//				for (Operation operationIterator : operations.reversed()) {
//					if (operationIterator.getCustomer().equals(operationOut.getCustomer()))
//						System.out.println(operationIterator.getCustomer());
//					if (operationIterator.getRentable().equals(operationOut.getRentable()))
//						System.out.println(operationIterator.getCustomer());
//				}
////				result = operations.remove(operationOut);
////				System.out.println(result);
////				return result;
//			}
////			Customer customer = operationOut.getCustomer();
////			Rentable rentable = operationOut.getRentable();
////			if (rentable.isStatus())
////				if (customer == searchCustomerByRentable(rentable)) {// same customer=return rentable directly
////
////				} else if (customer.equals(customer))
////					customer.returnRentable(rentable);
////			rentable.setStatus(true);
////			operations.add(new Operation(customer, rentable, new Date("auto"), "return"));
//		}
//		return result;
//	}

//	private Image image;
	//rentable
//	public Image getImage() {
//	if (imagePath != null && !imagePath.isEmpty()) {
//        try {
//
//            // Ensure it's a file URI
//            File imageFile = new File(imagePath);
//            if (true) {
//                return new Image(imageFile.toURI().toString());
//            } else {
//                System.err.println("Image file not found: " + imagePath);
//                return null; // Or a placeholder image
//            }
//        } catch (Exception e) {
//            System.err.println("Error loading image from path " + imagePath + ": " + e.getMessage());
//            return null; // Or a placeholder image
//        }
//    }
//    return null; // Or a placeholder image
//}
//
//public void setImage(Image image) {
//	this.image = image;
//}

	//rentable
//	public void setImagePath(String path) {
//	// 1. Create a FileNameExtensionFilter for common image formats
//    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
//        "Image Files (JPG, PNG, GIF, BMP)", // Description for the filter
//        "jpg", "jpeg", "png", "gif", "bmp"   // Allowed extensions
//    );
//
//    // 2. Add the filter to the file chooser
//    fileChooser.addChoosableFileFilter(imageFilter);
//
//    // 3. (Optional) Set this filter as the default selected filter
//    //    This means when the dialog opens, "Image Files" will be pre-selected
//    fileChooser.setFileFilter(imageFilter);
//
//    int result = fileChooser.showOpenDialog(null); // 'parentComponent' is typically a JFrame or JPanel
//    if (result == JFileChooser.APPROVE_OPTION) {
//        File selectedFile = fileChooser.getSelectedFile();
////		this.imagePath = path;
//		String filePath = selectedFile.getAbsolutePath();
//		 this.imagePath="file:"+filePath;
//		 System.out.println(imagePath);
//        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        // Further processing of the selected file
//    } else {
//        System.out.println("File selection cancelled.");
//    }
//}

//	/**
//	 * Creates a snapshot of the item's data at a point in time. Subclasses should
//	 * override this to add their specific fields.
//	 */
//	public Map<String, Object> createSnapshot() {
//		Map<String, Object> snapshot = new HashMap<>();
//		snapshot.put("number", this.number);
//		snapshot.put("status", this.status);
//		// Add any other common fields here
//		return snapshot;
//	}


//	fillIdSet();
//	initialize();
//	System.out.println();
//	System.out.println(operations.getFirst().getRentableSnapshot());
//	System.out.println();
//	System.out.println(operations.getFirst().getCustomerSnapshot());
//	System.out.println();
//	for (int i = 0; i < operations.getFirst().getCustomerSnapshot().entrySet().toArray().length; i++)
//		System.out.println(i + " " + operations.getFirst().getCustomerSnapshot().entrySet().toArray()[i]);
//	operations.getFirst().getCustomerSnapshot().forEach((name, age) -> System.out.println(name + " is " + age + " years old."));
//	System.out.println();
//	System.out.println(operations);
//	for(Map.Entry<String,Object> item:operations)
//	System.out.println(operations.get(1));

//	System.out.println();
//	System.out.println(operations.get(1).getCustomer());
//	System.out.println();
//	System.out.println(operations.get(1).getCustomerSnapshot());
//	System.out.println();
//	String[] str = { "id", "name", "noOfCars", "noOfUnits", "customerRentables" };
//	for (int j = 0; j < operations.size(); j++) {
//		System.out.println("operation "+j+" : ");
//		for (int i = 0; i < str.length; i++) {
//			System.out.print( str[i] + ": ");
//			System.out.println(operations.get(j).getCustomerSnapshot().get(str[i]));
//		}
//	}
	// System.out.println(operations.getFirst().getRentableSnapshot().entrySet().toString());
//	System.out.println();
//	System.out.println(operations.getFirst().getRentableSnapshot().entrySet().toArray());
//	System.out.println();
//	System.out.println(operations.getFirst().getRentableSnapshot().entrySet().toArray().toString().);
//	System.out.println();

//	Citizen c = new Citizen(123, "123", 123, new Date("auto"));
//	Car cc = new Car("123",123,"123",123,123);
//	c.addRentable(cc);
//	cc.setStatus(false);
//	Operation o = new Operation(c, cc, new Date("auto"),"rent");
//	Operation2 o2 = new Operation2(c, cc, new Date("auto"),"rent");
//	operations.add(o);
//	operations2.add(o2);
//	System.out.println(operations);
//	System.out.println();
//	System.out.println(operations2);
//	cc.setMonthlyPrice(121212);
//	System.out.println();
//	System.out.println(operations);
//	System.out.println();
//	System.out.println(operations2);
//	System.out.println();
//	in.next();
//	Citizen cit = new Citizen(111, "111", 111, new Date("auto"));
//	Car car = new Car("222", 222, "222", 222, 222);
//	Car car2 = new Car("222", 222, "222", 222, 222);
//
//	cit.addRentable(car);
//	car.setStatus(false);
//	Operation o = new Operation(cit, car, new Date("auto"), "rent");
//	operations.add(o);
//
//	Operation so = searchOperationByCustomerAndRentable(cit, car);
//	System.out.println(so==o);
//	System.out.println(so);
//	System.out.println(o);
//	System.out.println("=========================");
//	Citizen cit2 = new Citizen(333, "333", 333, new Date("auto"));
//	Car car3 = new Car("444", 444, "444", 444, 444);
//	Car car4 = new Car("555", 555, "555", 555, 555);
//
//	cit.addRentable(car4);
//	car4.setStatus(false);
//	Operation o2 = new Operation(cit, car4, new Date("auto"), "rent");
//	operations.add(o2);
//
//	so = searchOperationByCustomerAndRentable(cit, car4);
//	System.out.println(so == o2);
//	System.out.println(so);
//	System.out.println(o2);


	/**
	 * uses selectCustomerMenu() and selectRentableMenu() to select a customer and
	 * rentable. and uses policyValuesOutcome() to check selected customer and
	 * rentable if eligible to make a rent operation.
	 *
	 * @param customerId
	 * @param rentableId
	 * @param operation
	 * @return
	 */
//	public static boolean rentOperation(int customerId, String rentableId, Operation operation) {// addOperation()
//		Customer customer = selectCustomerMenu(customerId);
//		Rentable rentable = selectRentableMenu(rentableId);
//		// customer.addrentable()
//		// rentable.setstatus
//		// operation.add
//		if (customer != null && rentable != null) {
//			if (rentable.isStatus()) {
//				System.out.println("rentable is available");
//				if (policyValuesOutcome(customer, rentable, operation)) {
//					System.out.println("--------rent operation succeeded--------");
//					return true;
//				}
//			} else
//				alertWindow("rentable not available");
//		} else
//			System.out.println("customer/rentable null/not found");
//		System.out.println("--------rent operation failed--------");
//		return false;
//	}

	/**
	 * uses selectCustomerMenu() and selectRentableMenu() to select a customer and
	 * rentable. and searches for operation using customer id and rentable id. then
	 * if found it create return operation and alter the properties for customer and
	 * rentable.
	 *
	 * @param customerId
	 * @param rentableId
	 * @return true= return operation successful, false= not successful do to (not
	 *         found customer/rentable/operation)/already available rentable
	 */
//	public static boolean returnOperation(int customerId, String rentableId) {// addOperation()
//		Customer customer = selectCustomerMenu(customerId);
//		Rentable rentable = selectRentableMenu(rentableId);
//		Operation targetOperation = null;
//
//		if (rentable != null && customer != null) {
//			if (rentable.isStatus()) {
//				alertWindow("rentable is available so it can't be returned");
//				return false;
//			}
//
//			for (Operation operation : operations) {
//				boolean sameRentableId = operation.getRentable().getNumber().trim()
//						.equalsIgnoreCase(String.valueOf(rentableId).trim());
//				boolean sameCustomerId = operation.getCustomer().getId() == customer.getId();
//				if (sameCustomerId && sameRentableId) {
//					targetOperation = operation;
//					System.out.println("--------operation found--------");
//					customer.returnRentable(rentable);
//					rentable.setStatus(true);
//					operations.add(new Operation(customer, rentable, new Date("auto"), "return"));
//					return true;
//				}
//			}
//		} else
//			System.out.println("customer/rentable null/not found");
//		alertWindow("--------operation not found--------\n" + "--------rent operation failed--------");
//		System.out.println("customer/rentable null/not found");
//		System.out.println("--------rent operation failed--------");
//		return false;
//	}

//	public static boolean deleteOperation2(Operation operationOut) {
//	//1 delete rent at the end = no problem=delete directly
//	//2 delete return at the end = directly
//	//3 delete rent in the middle = delete return after
//	//4 delete return in the middle = you need to provide a different return instead
//	//
//	boolean result = false;
//	Operation operationTemp;
//	if (operations.contains(operationOut)) {
//		String type = operationOut.getOperationType();
//		if (type.equals("rent")) {
//			if(operations.getLast().equals(operationOut)) {//1 rent at the end
//				operationTemp=new Operation(operations.getLast());
//				result = returnOperation(operations.getLast());
//				if(!result) {
//					operations.add(operationTemp);
//					System.out.println("couldn't delete operation: "+operationTemp);
//					return false;
//				}else
//					operations.removeLast();
//			}else {// 3 rent in the middle, search for return
//
//				for(Operation operation: operations.reversed()) {
//					if(operation.equals(operationOut) && operation.getOperationType().equals("rent")) {
//
//					}
//				}
//			}
//
//
//			for (Operation operationIterator : operations.reversed()) {
//				if (operationIterator.equals(operationOut)
//						&& operationIterator.getOperationType().equals("return")) {
////					result = operations.remove(operationIterator) && operations.remove(operationOut);
////					System.out.println(result);
////					return result;
//					System.out.println(operationIterator);
//				}
//			}
//		} else {//2 return at the end
//			if(operations.getLast().equals(operationOut)) {//1
//				operationTemp=new Operation(operations.getLast());
//				result = returnOperation(operations.getLast());
//				if(!result) {
//					operations.add(operationTemp);
//					System.out.println("couldn't delete operation: "+operationTemp);
//					return false;
//				}else
//					operations.removeLast();
//
//			for (Operation operationIterator : operations.reversed()) {
//				if (operationIterator.getCustomer().equals(operationOut.getCustomer()))
//					System.out.println(operationIterator.getCustomer());
//				if (operationIterator.getRentable().equals(operationOut.getRentable()))
//					System.out.println(operationIterator.getCustomer());
//			}
////			result = operations.remove(operationOut);
////			System.out.println(result);
////			return result;
//		}
////		Customer customer = operationOut.getCustomer();
////		Rentable rentable = operationOut.getRentable();
////		if (rentable.isStatus())
////			if (customer == searchCustomerByRentable(rentable)) {// same customer=return rentable directly
////
////			} else if (customer.equals(customer))
////				customer.returnRentable(rentable);
////		rentable.setStatus(true);
//	}}
//	return result;
//}

//	/**
//	 * replace with returnOperatio() and makeOperationMenu()
//	 * used in returnRentableOriginal and RentOperationController class. uses
//	 * selectCustomerMenu(), selectRentable()
//	 *
//	 * @param customerOut
//	 * @param rentableOut
//	 * @param operationOut
//	 * @return true= success
//	 *
//	 */
//	public static boolean returnRentableOriginal(Customer customerOut, Rentable rentableOut, Operation operationOut) {
//		Customer customer = customerOut;
//		Rentable rentable = rentableOut;
//		Operation operation = operationOut;
//		boolean result = false;
//		if (rentable != null)
//			if (rentable.isStatus()) {// if rentable available stop
//				alertWindow("rentable is already available. try again with different rentable.");
//				return false;
//			}
//		try {
//			int selection = 1;
//			while (selection != 0) {
//				// 1 RUN NORMALLY IF NO VALUES OUT
//				if (customer == null || rentable == null) {
//					System.out.println("\n*************************************************");
//					System.out.println("6 return a rentable ");
//					System.out.println("*************************************************");
//					System.out.print("enter 1 to procceed or 0 to go back: ");
//					selection = in.nextInt();
//				}
//				switch (selection) {
//				case 1: {
//
//					if (customer == null)
//						customer = selectCustomerMenu(-1);
//					if (rentable == null)
//						rentable = selectRentableMenu(-1);
//
//					if (customer != null && rentable != null) {// 3 CHECK IF FOUND CUSTOMER/RENTABLE
//						customer.returnRentable(rentable);
//						rentable.setStatus(true);
//						if (operation == null)
//							operations.add(new Operation(customer, rentable, "return"));
//						else
//							operations.add(operation);
//						System.out.println(operations.get(operations.size() - 1));
//						System.out.println("---------return operation succeeded.---------");
//						customer = null;
//						rentable = null;
//						operation = null;
//						return true;
//					} else {// 3 NOT FOUND EXIT
//						alertWindow("operation failed customer/rentable not available or not found");
//						customer = null;
//						rentable = null;
//						operation = null;
//						return false;
//					}
//				}
//				case 0:
//					System.out.println("going back..." + "\n*************************************************");
//					customer = null;
//					rentable = null;
//					operation = null;
//					break;
//				}
//			}
//
//		} catch (InputMismatchException e) {
//			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
//			String select = in.nextLine();
//			System.out.println("exiting...");
//			main(null);
//		}
//		return false;
//	}

	public static void readOld() {
		System.out.println("\n*************************************************");
		System.out.println("10 reading...");
		System.out.println("*************************************************");
		int i = 1;
		try (FileInputStream fis = new FileInputStream("Customers.ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			ArrayList<Customer> readCustomers = (ArrayList<Customer>) ois.readObject();
			System.out.println("Deserialized ArrayList<Customer>:");
			for (Customer customer : readCustomers) {
				System.out.println("\t" + (i++) + " " + customer);
				customers.add(customer);
			}
			i = 1;// reset
		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("no files found to load data from `Customers.ser`.");
		}

		try (FileInputStream fis = new FileInputStream("Rentables.ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			ArrayList<Rentable> readRentables = (ArrayList<Rentable>) ois.readObject();
			System.out.println("Deserialized ArrayList<Rentable>:");
			for (Rentable rentable : readRentables) {
				System.out.println("\t" + (i++) + " " + rentable);
				rentables.add(rentable);
			}
			i = 1;// reset
		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("no files found to load data from `Rentables.ser`.");
		}

		try (FileInputStream fis = new FileInputStream("Operations.ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			ArrayList<Operation> readOperations = (ArrayList<Operation>) ois.readObject();
			System.out.println("Deserialized ArrayList<Operation>:");
			for (Operation operation : readOperations) {
				System.out.println("\t" + (i++) + " " + operation);
				operations.add(operation);
			}
			i = 1;// reset
		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("no files found to load data from `Operations.ser`.");
		}
	}

	public static void saveOld() {
		System.out.println("\n*************************************************");
		System.out.println("11 saving...");
		System.out.println("*************************************************");

		try (FileOutputStream fos = new FileOutputStream("Customers.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(customers);
			System.out.println("ArrayList<Customer> serialized successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			alertWindow("err 1 cus \ncould not save customer");
			System.out.println("err 1 cus \ncould not save customer");
		}

		try (FileOutputStream fos = new FileOutputStream("Rentables.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(rentables);
			System.out.println("ArrayList<Rentable> serialized successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			alertWindow("err 2 ren \ncould not save rentable");
			System.out.println("err 1 cus \ncould not save customer");
		}

		try (FileOutputStream fos = new FileOutputStream("Operations.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(operations);
			System.out.println("ArrayList<Operation> serialized successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			alertWindow("err 3 com \ncould not save operation");
			System.out.println("err 3 com \ncould not save operation");
		}

	}

//	public static Operation searchOperationByRentableReference(Rentable rentable) {
//		for (Operation operation : operations)
//			if (operation.getRentable() == rentable)
//				return operation;
//		return null;
//	}

//	public static Operation searchOperationByCustomerAndRentable2(Customer customer, Rentable rentable) {
//	Citizen cit = null;
//	Resident res = null;
//	Company com = null;
//
//	Car car = null;
//	RealEstate real = null;
//
//	if (customer instanceof Citizen citizen)
//		cit = citizen;
//	else if (customer instanceof Resident resident)
//		res = resident;
//	else if (customer instanceof Company company)
//		com = company;
//
//	if (rentable instanceof Car car2)
//		car = car2;
//	else if (rentable instanceof RealEstate realEstate)
//		real = realEstate;
//
//	if (cit != null && car != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof Car car3 && operation.getCustomer()instanceof Citizen citizen)
//				if (car3.equals(car) && citizen.equals(cit))
//					return operation;
//	} else if (cit != null && real != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof RealEstate realEstate
//					&& operation.getCustomer()instanceof Citizen citizen)
//				if (realEstate.equals(real) && citizen.equals(cit))
//					return operation;
//	} else if (res != null && car != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof Car car3 && operation.getCustomer()instanceof Resident resident)
//				if (car3.equals(car) && resident.equals(res))
//					return operation;
//	} else if (res != null && real != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof RealEstate realEstate
//					&& operation.getCustomer()instanceof Resident resident)
//				if (realEstate.equals(real) && resident.equals(res))
//					return operation;
//	} else if (com != null && car != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof Car car3 && operation.getCustomer()instanceof Company company)
//				if (car3.equals(car) && company.equals(com))
//					return operation;
//	} else if (com != null && real != null) {
//		for (Operation operation : operations)
//			if (operation.getRentable()instanceof RealEstate realEstate
//					&& operation.getCustomer()instanceof Company company)
//				if (realEstate.equals(real) && company.equals(com))
//					return operation;
//	}
//
//	alertWindow("search operation by customer and rentable 22222 failed to find the operation");
//	return null;
//}

//public static Operation searchOperationMostRecent(String rentableId) {
//	for (Operation operation : operations) {
//		if (operation.getRentable().getNumber().equals(rentableId))
//			return operation;
//	}
//	return null;
//}

	/**
	 * checks customer details to determine whether the customer is eligible to rent
	 * or not. using rules/polices that are specific to each customer type.
	 *
	 * @param minAge                  citizen 16 for cars and realEstate, resident
	 *                                18 cars, 20 realEstate
	 *
	 * @param expiry                  company not expired
	 *
	 * @param maxUnits,               maxCars citizen no limit for cars and
	 *                                realEstate, resident 2 cars, 1 realEstate
	 *                                company 10 cars, 10 units
	 * @param resident
	 * @param citizen
	 * @param company
	 * @param car
	 * @param realEstate
	 * @param policyResult            xxx probably not needed and not used xxx
	 * @param policyConditionSelector selects which policy to apply
	 * @return true= eligible to rent, false=reached the limit
	 */
//	public static boolean policyTest(int minAge, boolean expiry, int maxUnits, int maxCars, Resident resident,
//			Citizen citizen, Company company, Car car, RealEstate realEstate, boolean policyResult,
//			int policyConditionSelector) {
//		boolean isAboveMinAgeResident = false;// true = above min age, false = below min age
//		boolean isBelowMaxCarsResident = false;// true = below limit, false = exceeded the limit
//		boolean isBelowMaxUnitsResident = false;
//		boolean isNotExpiredCompany = false;// true = not expired
//		boolean isBelowMaxCarsCompany = false;
//		boolean isBelowMaxUnitsCompany = false;
//		boolean isAboveMinAgeCitizen = false;
//		int age = 0;
//		// policy conditions
//		if (resident != null) {
//			age = (LocalDate.now().getYear() - resident.getbDate().getYear());
//			isAboveMinAgeResident = age > minAge;
//			isBelowMaxCarsResident = resident.getNoOfCars() < maxCars;
//			isBelowMaxUnitsResident = resident.getNoOfUnits() < maxUnits;
//		} else if (company != null) {
//			isNotExpiredCompany = !expiry; // true = not expired
//			isBelowMaxCarsCompany = company.getNoOfCars() < maxCars;
//			isBelowMaxUnitsCompany = company.getNoOfUnits() < maxUnits;
//		} else {
//			age = (LocalDate.now().getYear() - citizen.getbDate().getYear());
//			isAboveMinAgeCitizen = age > minAge;
//		}
//		// k defines which customer and rentable is used
//		if (policyConditionSelector == 0) {
//			System.out.println("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxCarsResident "
//					+ isBelowMaxCarsResident);
//			if (!(isAboveMinAgeResident && isBelowMaxCarsResident))
//				alertWindow("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxCarsResident "
//						+ isBelowMaxCarsResident);
//			return isAboveMinAgeResident && isBelowMaxCarsResident; // return true in order to pass policy
//		} else if (policyConditionSelector == 1) {
//			System.out.println("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxUnitsResident "
//					+ isBelowMaxUnitsResident);
//			if (!(isAboveMinAgeResident && isBelowMaxUnitsResident))
//				alertWindow("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxUnitsResident "
//						+ isBelowMaxUnitsResident);
//			return isAboveMinAgeResident && isBelowMaxUnitsResident;
//		} else if (policyConditionSelector == 2) {
//			System.out.println(
//					"isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxCarsCompany " + isBelowMaxCarsCompany);
//			if (!(isNotExpiredCompany && isBelowMaxCarsCompany))
//				alertWindow("isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxCarsCompany "
//						+ isBelowMaxCarsCompany);
//			return isNotExpiredCompany && isBelowMaxCarsCompany;
//		} else if (policyConditionSelector == 3) {
//			System.out.println(
//					"isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxUnitsCompany " + isBelowMaxUnitsCompany);
//			if (!(isNotExpiredCompany && isBelowMaxUnitsCompany))
//				alertWindow("isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxUnitsCompany "
//						+ isBelowMaxUnitsCompany);
//			return isNotExpiredCompany && isBelowMaxUnitsCompany;
//		} else if (policyConditionSelector == 4) {
//			System.out.println("isAboveMinAgeCitizen " + isAboveMinAgeCitizen);
//			if (!isAboveMinAgeCitizen)
//				alertWindow("isAboveMinAgeCitizen " + isAboveMinAgeCitizen);
//			return isAboveMinAgeCitizen;
//		}
//		return false;
//	}

//	public static boolean policyValuesOutcome(Object customer, Object rentable, Operation operationOut) {
//	int minAge = 0;
//	boolean expiry = false;
//	int maxUnits = 0;
//	int maxCars = 0;
//	Resident resident = null;
//	Citizen citizen = null;
//	Company company = null;
//	Car car = null;
//	RealEstate realEstate = null;
//	boolean policyResult = false;
//	int policyConditionSelector = -1; // k defines which customer and rentable is used
//
//	if (customer instanceof Resident) {
//		resident = (Resident) customer;
//	} else if (customer instanceof Citizen) {
//		citizen = (Citizen) customer;
//	} else {
//		company = (Company) customer;
//	}
//
//	if (rentable instanceof Car) {
//		car = (Car) rentable;
//	} else {
//		realEstate = (RealEstate) rentable;
//	}
//
//	if (resident != null && car != null) {
//		minAge = 18;
//		maxCars = 2;
//		policyConditionSelector = 0;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(resident, car, "rent"));
//			else
//				operations.add(operationOut);
//			System.out.println(operations.get(operations.size() - 1));
//			resident.addRentable(car);
//			car.setStatus(false);
//			System.out.println("customer: " + resident.getClass().getSimpleName() + " " + resident.getName()
//					+ ", id: " + resident.getId() + " made the operation of renting " + car);
//		} else
//			System.out.println("operation failed");
//	} else if (resident != null && realEstate != null) {
//		minAge = 20;
//		maxUnits = 1;
//		policyConditionSelector = 1;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(resident, realEstate, "rent"));
//			else
//				operations.add(operationOut);
//			System.out.println(operations.get(operations.size() - 1));
//			resident.addRentable(realEstate);
//			realEstate.setStatus(false);
//			System.out.println("customer: " + resident.getClass().getSimpleName() + " " + resident.getName()
//					+ ", id: " + resident.getId() + " made the operation of renting " + realEstate);
//		} else
//			System.out.println("operation failed");
//	} else if (company != null && car != null) {
//		expiry = company.isExpired();// >0 | >Now
//		maxCars = 10;
//		policyConditionSelector = 2;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(company, car, "rent"));
//			else
//				operations.add(operationOut);
//			System.out.println(operations.get(operations.size() - 1));
//			company.addRentable(car);
//			car.setStatus(false);
//			System.out.println("customer: " + company.getClass().getSimpleName() + " " + company.getName()
//					+ ", id: " + company.getId() + " made the operation of renting " + car);
//		} else
//			System.out.println("operation failed");
//	} else if (company != null && realEstate != null) {
//		expiry = company.isExpired();
//		maxUnits = 10;
//		policyConditionSelector = 3;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(company, realEstate, "rent"));
//			else
//				operations.add(operationOut);
//			System.out.println(operations.get(operations.size() - 1));
//			company.addRentable(realEstate);
//			realEstate.setStatus(false);
//			System.out.println("customer: " + company.getClass().getSimpleName() + " " + company.getName()
//					+ ", id: " + company.getId() + " made the operation of renting " + realEstate);
//		} else
//			System.out.println("operation failed");
//	} else if (citizen != null && car != null) {
//		minAge = 16;
//		policyConditionSelector = 4;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(citizen, car, "rent"));
//			else
//				operations.add(operationOut);
//			System.out.println(operations.get(operations.size() - 1));
//			citizen.addRentable(car);
//			car.setStatus(false);
//			System.out.println("customer: " + citizen.getClass().getSimpleName() + " " + citizen.getName()
//					+ ", id: " + citizen.getId() + " made the operation of renting " + car);
//		} else
//			System.out.println("operation failed");
//	} else if (citizen != null && realEstate != null) {
//		minAge = 16;
//		policyConditionSelector = 4;
//		policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//				policyResult, policyConditionSelector);
//		if (policyResult) {
//			if (operationOut == null)
//				operations.add(new Operation(citizen, realEstate, "rent"));
//			else
//				operations.add(operationOut);
//			citizen.addRentable(realEstate);
//			realEstate.setStatus(false);
//			System.out.println("customer: " + citizen.getClass().getSimpleName() + " " + citizen.getName()
//					+ ", id: " + citizen.getId() + " made the operation of renting " + realEstate);
//		} else
//			System.out.println("operation failed");
//	}
//	return policyResult;
//}

//	public static boolean deleteOperation(Operation operation) {// ???
//	boolean result = false;
//	if (operation != null) {
//		Customer customer = operation.getCustomer();
//		Rentable rentable = operation.getRentable();
//		returnOperation(operation, customer, rentable);
//		return true;
//	}
//	return result;
//}

	/**
	 * not used anywhere in InfoSys but used in Controller classes. uses
	 * checkCustomerId() and checkRentableId() why? to check if unique or not. if
	 * unique why? it determines the type and adds the object to the appropriate
	 * list.
	 *
	 * @param object
	 * @return
	 */
	public static boolean removeObject(Object object) {
		System.out.println("\n*************************************************");
		System.out.println(" remove object...");
		System.out.println("*************************************************");
		boolean result = false;
		if (object == null) {
			alertWindow("-------can't remove null Object-------");
			System.out.println("-------can't remove null Object-------");
			return false;
		}
		boolean isRemoved = false;
		if (object instanceof Operation operation) {
//			Rentable rentable = operation.getRentable();
//			Customer customer = operation.getCustomer();
//			if (returnOperation(operation.getCustomer(), operation.getRentable())) {
//				operations.remove(operation);
//				return true;
//			}
//			if (customer != null && rentable != null) {
//				Operation op = searchOperationByCustomerAndRentable(customer, rentable, null);
//				if (op != null) {
//					if (returnOperation(op.getCustomer(), op.getRentable())) {
//						operations.remove(op);
//						return true;
//					} else
//						return false;
//				} else {
//					return false;
//				}
//		}
//			isRemoved = operations.remove(operation);
//			if (isRemoved) {
//				rentable.setStatus(true);
//				customer.returnRentable(rentable);
//				System.out.println("\n*****************************************" + "\noperation removed: " + object
//						+ "\n*****************************************");
//			} else {
//				alertWindow("\n*****************************************"
//						+ "\ncouldn't remove operation, operation doesn't exist"
//						+ "\n*****************************************");
//			}
//			return isRemoved;
			result = deleteOperation(operation, null);
			System.out.println("removeObject() operation: " + result);
			System.out.println(operation);

			return result;
		} else if (object instanceof Customer customer) {
//			// don't remove object=false=(not found/unique), remove
//			// object=true=(found/not unique)
//			if (checkCustomerId(customer.getId())) {
//				for (Rentable rentable : customer.getCustomerRentables())
//					returnOperation(customer, rentable);
//
//				isRemoved = customersSet.remove(customer);
//			} // true=removed
//			if (isRemoved) {
//				customersIds.remove(customer.getId());
//				customers.remove(customer);
//
//				System.out.println("\n*****************************************" + "\ncustomer removed: " + object
//						+ "\n*****************************************");
//			} else {
//				alertWindow("\n*****************************************"
//						+ "\ncouldn't remove customer, customer doesn't exist"
//						+ "\n*****************************************");
//			}
//			return isRemoved;
			result = deleteCustomer(customer);
			System.out.println("removeObject() customer: " + result);
			System.out.println(customer);
			return result;
		} else if (object instanceof Rentable rentable) {// OBJECT INSTANCE OF RENTABLE
//			// don't remove object=false=(not found/unique), remove
//			// object=true=(found/not unique)
//			if (checkRentableId(rentable.getNumber())) {
//				Operation op = searchOperationByRentableReference(rentable);
//				if (op != null)
//					if (returnOperation(op.getCustomer(), rentable)) {
////				if(op.getCustomer().returnRentable(deleteRentable))
////				operations.add(new Operation(op.getCustomer(),deleteRentable,new Date("auto"),"return"));
////						rentables.remove(rentable);
//						isRemoved = rentablesSet.remove(rentable);
////						return true;
//					}
//			}
//			if (isRemoved) {
//				rentablesIds.remove(rentable.getNumber());
//				rentables.remove(rentable);
//				System.out.println("\n*****************************************" + "\nrentable removed: " + object
//						+ "\n*****************************************");
//			} else {
//				alertWindow("\n*****************************************"
//						+ "\ncouldn't remove rentable, rentable doesn't exist"
//						+ "\n*****************************************");
//			}
//			return isRemoved;
			result = deleteRentable(rentable);
			System.out.println("removeObject() rentable: " + result);
			System.out.println(rentable);
			return result;
		}
		return isRemoved;
	}

//	/**
//	 * fills ids sets from lists that are used to read and save data.
//	 */
//	public static void fillIdSet() {
//		for (Customer customer : customers)
//			customersIds.add(customer.getId());
//		for (Rentable rentable : rentables)
//			rentablesIds.add(rentable.getNumber());
//		rentablesIds.forEach(System.out::println);
//	}

//	public static boolean addCustomer() {
//	if (object == null) {
//		System.out.println("-------can't add null Object-------");
//		return false;
//	}
//	boolean uniqueId = false;
//	if (object instanceof Customer customer) {
//		// add object=true=!(false=(not found/unique)), don't add
//		// object=false=!(true=(found/not unique))
//		if (!checkCustomerId(customer.getId()))
//			uniqueId = customersSet.add(customer);
//		if (uniqueId) {
//			customersIds.add(customer.getId());
//			customers.add(customer);
//			System.out.println("\n*****************************************" + "\ncustomer added: \n"
//					+ customers.get(customers.size() - 1) + "\n*****************************************");
//		} else {
//			alertWindow("\n*****************************************"
//					+ "\ncouldn't add customer, duplicate id... \ncustomer already exist"
//					+ "\n*****************************************");
//		}
//		return uniqueId;
//	}
//}


	package proj01;

	public class check {

		void start() {}
		void typeOfCustomer() {}
		void typeOfRentable() {}
		void fillIdSet() {}
		void initialize() {}

		void checkRentableId() {}
		void checkCustomerId() {}

		void addObject() {
			checkCustomerId();
			checkRentableId();
		}
		void removeObject(){
			checkCustomerId();//?
			checkRentableId();//?
		}
		void createCustomerMenu() {
			addObject();
		}
		void createRentableMenu() {
			addObject();
		}

		void searchCustomer() {
			//typeOfCustomer();
		}
		void searchRentable() {
			//typeOfRentable();
		}

		void selectCustomerMenu() {
			searchCustomer();
		}
		void selectRentableMenu() {
			searchRentable();
		}
		void rentOperation() {
			selectCustomerMenu();
			selectRentableMenu();
		}
		void returnOperation() {
			selectCustomerMenu();
			selectRentableMenu();
		}
		void makeOperationMenu() {
			rentOperation();
			returnOperation();
		}
		void returnRentableOriginal() {
			selectCustomerMenu();
			selectRentableMenu();
		}

		void report() {}
		void listAvailUnavilRentables() { }
		void listAllRentables() { }
		void listAllOperations() {}
		void listAllCustomers() {}
		void listAllCompanies() {}
		void listAllCars() {}
		void listAllRealEstates() {}
		void listAllResidents() {}
		void listAllCitizens() {}

		void read() {}
		void readOld() {}
		void save() {}
		void saveOld() {}
		void delete() {}
		void policyTest(){}
		void policyValuesOutcome(){}
		void alertWindow() {}
	}



//	package proj01;
	//
	//import java.time.LocalDate;
	//import java.util.ArrayList;
	//import java.util.HashSet;
	//import java.util.InputMismatchException;
	//import java.util.Scanner;
	//import java.util.Set;
	//import java.util.stream.Collectors;
	//
	//import javafx.application.Application;
	//
	//public class InfoSys extends Application {
	//
//		public static ArrayList<Customer> customers = new ArrayList<>();
//		public static Set<Integer> customersIds = new HashSet<>();
//		public static Set<String> rentablesIds = new HashSet<>();
	//
//		public static ArrayList<Rentable> rentables = new ArrayList<>();
//		public static ArrayList<Operation> operations = new ArrayList<>();
//		public static Scanner in = new Scanner(System.in);
	//
//		public static Set<Customer> customersSet;
//		public static Set<Rentable> rentablesSet;
	//
	//
//		public static boolean removeObject(Object object) {
//			if (object == null) {
//				alertWindow("-------can't remove null Object-------");
//				return false;
//			}
//			boolean isRemoved = false;
//			if(object instanceof Operation operation) {
//				Rentable rentable=operation.getRentable();
//				Customer customer = operation.getCustomer();
//				isRemoved= operations.remove(operation);
//				if(isRemoved) {
////					rentable.setStatus(true);
////					customer.returnRentable(rentable);
//					System.out.println("\n*****************************************" + "\noperation removed: " + object
//							+ "\n*****************************************");
//				}else {
//					alertWindow("\n*****************************************"
//							+ "\ncouldn't remove operation, operation doesn't exist"
//							+ "\n*****************************************");
//				}
//				return isRemoved;
//			} else if (object instanceof Customer customer) {
//				if (checkCustomerId(customer.getId()))
//					isRemoved = customersSet.remove(customer);// true=removed
//				if (isRemoved) {
//					customersIds.remove(customer.getId());
//					customers.remove(customer);
//					System.out.println("\n*****************************************" + "\ncustomer removed: " + object
//							+ "\n*****************************************");
//				} else {
//					alertWindow("\n*****************************************"
//							+ "\ncouldn't remove customer, customer doesn't exist"
//							+ "\n*****************************************");
////					flush = true;
//				}
//				return isRemoved;
//			} else if (object instanceof Rentable rentable) {// OBJECT INSTANCE OF RENTABLE
//				if (checkRentableId(rentable.getNumber()))
//					isRemoved = rentablesSet.remove(rentable);
//				if (isRemoved) {
//					rentablesIds.remove(rentable.getNumber());
//					rentables.remove(rentable);
//					System.out.println("\n*****************************************" + "\nrentable removed: " + object
//							+ "\n*****************************************");
//				} else {
//					alertWindow("\n*****************************************"
//							+ "\ncouldn't remove rentable, rentable doesn't exist"
//							+ "\n*****************************************");
////					flush = true;
//				}
//				return isRemoved;
//			}
//			return isRemoved;
//		}
	//
//		public static void fillIdSet() {
//			for (Customer customer : customers)
//				customersIds.add(customer.getId());
//			for (Rentable rentable : rentables)
//				rentablesIds.add(rentable.getNumber());
//			rentablesIds.forEach(System.out::println);
//		}
	//
//		public static boolean checkCustomerId(int id) {
//			return customersIds.contains(id);
//		}
	//
//		public static boolean checkRentableId(String id) {
//			return rentablesIds.contains(id);
//		}
	//
//		public static boolean addObject(Object object) {
//			if (object == null) {
//				System.out.println("-------can't remove null Object-------");
//				return false;
//			}
//			boolean uniqueId = false;
//			if (object instanceof Customer customer) {
//				if (!checkCustomerId(customer.getId()))
//					uniqueId = customersSet.add(customer);
//				if (uniqueId) {
//					customersIds.add(customer.getId());
//					customers.add(customer);
//					System.out.println("\n*****************************************" + "\ncustomer added: "
//							+ customers.get(customers.size() - 1) + "\n*****************************************");
//				} else {
//					alertWindow("\n*****************************************"
//							+ "\ncouldn't add customer, duplicate id... \ncustomer already exist"
//							+ "\n*****************************************");
////					flush = true;
//				}
//				return uniqueId;
//			} else if (object instanceof Rentable rentable) {// OBJECT INSTANCE OF RENTABLE
//				if (!checkRentableId(rentable.getNumber()))
//					uniqueId = rentablesSet.add(rentable);
//				if (uniqueId) {
//					rentablesIds.add(rentable.getNumber());
//					rentables.add(rentable);
//					System.out.println("\n*****************************************" + "\nrentable added: "
//							+ rentables.get(rentables.size() - 1) + "\n*****************************************");
//				} else {
//					alertWindow("\n*****************************************"
//							+ "\ncouldn't add rentable, duplicate id... \nrentable already exist"
//							+ "\n*****************************************");
////					flush = true;
//				}
//				return uniqueId;
//			}
//			return uniqueId;
//		}
	//
	//
//		public static void main(String[] args) {
	//
//			read();
//			fillIdSet();
//			customersSet = customers.stream().collect(Collectors.toSet());
//			rentablesSet = new HashSet<>(rentables);
//			System.out.println("customers set:");
//			int i = 1;
//			for (Customer customer : customersSet) {
//				System.out.println("\t" + (i++) + " " + customer);
//			}
//			i = 1;// reset
	//
//			launch(args);
	//}
	//
//		public static Rentable typeOfRentable(Rentable rentable, boolean print) {
//			if (print) {
//				System.out.println("\n*************************************************");
//				System.out.println("type Of Rentable");
//				System.out.println("*************************************************");
//			}
	//
//			if (rentable == null) {
//				System.out.println("rentable==null");
//				return null;
//			}
	//
//			if (rentable instanceof Car) {
//				return rentable;
//			} else {
//				return rentable;
//			}
//		}
	//
//		public static Rentable searchRentable(int outId, boolean print) {
//			if (print) {
//				System.out.println("\n*************************************************");
//				System.out.println("search Rentable");
//				System.out.println("*************************************************");
//			}
//			boolean found = false;
//			for (Rentable rentable : rentables) {
//				if (rentable.getNumber().equals(String.valueOf(outId))) {
//					if (print)
//						System.out.println("rentable found: " + typeOfRentable(rentable, false));
//					found = true;
//					return rentable;
//				}
//			}
	//
//			if (!found && print)
//				alertWindow("rentable not found");
	//
//			return null;
//		}
	//
//		public static Rentable selectRentable(int outId) {
//			try {
//				int id = outId;
//				Rentable rentable;
//				int selection = 1;
//				while (selection != 0) {
//					if (id == -1) {
//						System.out.println("\n*************************************************");
//						System.out.println("4 select a Rentable...");
//						System.out.println("*************************************************");
//						System.out.println("1) enter rentable id");
//						System.out.println("2) list all available rentables");
//						System.out.println("0) go back");
//						System.out.println("select an option: ");
//						selection = in.nextInt();
//					} else {
//						rentable = searchRentable(id, true);
//						return rentable;
//					}
//					switch (selection) {
//					case 1:
//						System.out.print("enter rentable id: ");
//						id = in.nextInt();
//						rentable = searchRentable(id, true);
//						return typeOfRentable(rentable, false);
//					case 2:
//						report(6);
//						break;
//					case 0:
//						System.out.println("going back...");
//						return null;
//					default:
//						System.out.println("you entered wrong number");
//					}
//				}
//			} catch (InputMismatchException e) {
//				System.out.println("you enterd a string in place of integers.\npress any key to go back...");
//				String select = in.nextLine();
//				System.out.println("exiting...");
//				main(null);
//			}
//			return null;
//		}
	//
//		public static boolean rentOperation(int customerId, int rentableId,Operation operation) {// addOperation()
//			Customer customer = selectCustomer(customerId);
//			Rentable rentable = selectRentable(rentableId);
//			if (customer != null && rentable != null) {
//				if (rentable.isStatus()) {
//					System.out.println("rentable is available");
//					if (policyValuesOutcome(customer, rentable, operation)) {
//						System.out.println("--------rent operation succeeded--------");
//						return true;
//					}
//				} else
//					alertWindow("rentable not available");
//			} else
//				System.out.println("customer/rentable null/not found");
//			System.out.println("--------rent operation failed--------");
//			return false;
//		}
	//
//		public static boolean returnOperation(int customerId, int rentableId) {// addOperation()
//			Customer customer = selectCustomer(customerId);
//			Rentable rentable = selectRentable(rentableId);
//			Operation targetOperation = null;
	//
//			if (rentable != null && customer != null) {
//				if (rentable.isStatus()) {
//					alertWindow("rentable is available so it can't be returned");
//					return false;
//				}
	//
//				for (Operation operation : operations) {
//					boolean sameRentableId = operation.getRentable().getNumber().trim()
//							.equalsIgnoreCase(String.valueOf(rentableId).trim());
//					boolean sameCustomerId = operation.getCustomer().getId() == customer.getId();
//					if (sameCustomerId && sameRentableId) {
//						targetOperation = operation;
//						System.out.println("--------operation found--------");
//						customer.returnRentable(rentable);
//						rentable.setStatus(true);
//						operations.add(new Operation(customer, rentable, new Date("auto"), "return"));
//						return true;
//					}
//				}
//			} else
//				System.out.println("customer/rentable null/not found");
//			alertWindow("--------operation not found--------\n" + "--------rent operation failed--------");
//			System.out.println("customer/rentable null/not found");
//			System.out.println("--------rent operation failed--------");
//			return false;
//		}
	//
//		/**
//		 * makes an operation of renting or returning a rentable it selects a customer
//		 * and rentable then make operation
//		 *
//		 * @param customerOut
//		 * @param rentableOut
//		 * @param selectOption =-1 ask user for options,=#option selects directly any
//		 *                     option if customer and rentable not null
//		 * @return
//		 */
//		public static boolean makeOperation(Customer customerOut, Rentable rentableOut,Operation operation, int selectOption) {// addOperation()
//			try {
//				Customer customer = customerOut;
//				Rentable rentable = rentableOut;
//				int selection = 1;
//				boolean selectionOut = false;
	//
//				if (customer == null || rentable == null) {
//					System.out.println("\nmissing some information\ncustomer/rentable null");
//					selectionOut = false;
//				} else
//					selectionOut = true;
	//
//				while (selection != 0) {
//					System.out.println("\n*************************************************");
//					System.out.println("5 rent a rentable...");
//					System.out.println("*************************************************");
//					System.out.println("2)list all available rentables");
//					System.out.println("3)return");
//					System.out.println("4)rent");
//					System.out.println("5)select a customer");
//					System.out.println("6)select a rentable");
//					System.out.println("0)go back: ");
	//
//					System.out.println("selected customer: " + customer);
	//
//					System.out.println("selected rentable: " + rentable);
	//
//					if (selectionOut)
//						if (selectOption != -1)
//							selection = selectOption;
//						else
//							selection = in.nextInt();
//					else
//						selection = in.nextInt();
	//
//					switch (selection) {
//					case 2:// all ava
//						report(6);
//						break;
//					case 3:// return
//						return returnOperation(customer.getId(), Integer.valueOf(rentable.getNumber()));
	//
//					case 4:// rent
//						return rentOperation(customer.getId(), Integer.valueOf(rentable.getNumber()),operation);
//					case 5:// cus
//						customer = selectCustomer(-1);
	//
//						break;
//					case 6:// ren
//						rentable = selectRentable(-1);
//						if (rentable != null)
//							if (!rentable.isStatus()) {// if not available
//								alertWindow("rentable not available");
//								rentable = null;
//							}
//						break;
//					case 0:
//						System.out.println("going back..." + "\n*************************************************");
//						break;
//					}
//				}
//			} catch (InputMismatchException e) {
//				System.out.println("you enterd a string in place of integers.\npress any key to go back...");
//				String select = in.nextLine();
//				System.out.println("exiting...");
//				main(null);
//			}
//			return false;
//		}
	//
	//
	//
//		public static boolean policyTest(int minAge, boolean expiry, int maxUnits, int maxCars, Resident resident,
//				Citizen citizen, Company company, Car car, RealEstate realEstate, boolean x, int policyConditionSelector) {
//			boolean isAboveMinAgeResident = false;// true = above min age, false = below min age
//			boolean isBelowMaxCarsResident = false;// true = below limit, false = exceeded the limit
//			boolean isBelowMaxUnitsResident = false;
//			boolean isNotExpiredCompany = false;// true = not expired
//			boolean isBelowMaxCarsCompany = false;
//			boolean isBelowMaxUnitsCompany = false;
//			boolean isAboveMinAgeCitizen = false;
//			int age = 0;
//			// policy conditions
//			if (resident != null) {
//				age = (LocalDate.now().getYear() - resident.getbDate().getYear());
//				isAboveMinAgeResident = age > minAge;
//				isBelowMaxCarsResident = resident.getNoOfCars() < maxCars;
//				isBelowMaxUnitsResident = resident.getNoOfUnits() < maxUnits;
//			} else if (company != null) {
//				isNotExpiredCompany = !expiry; // true = not expired
//				isBelowMaxCarsCompany = company.getNoOfCars() < maxCars;
//				isBelowMaxUnitsCompany = company.getNoOfUnits() < maxUnits;
//			} else {
//				age = (LocalDate.now().getYear() - citizen.getbDate().getYear());
//				isAboveMinAgeCitizen = age > minAge;
//			}
//			// k defines which customer and rentable is used
//			if (policyConditionSelector == 0) {
//				System.out.println("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxCarsResident "
//						+ isBelowMaxCarsResident);
//				if (!(isAboveMinAgeResident && isBelowMaxCarsResident))
//					alertWindow("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxCarsResident "
//							+ isBelowMaxCarsResident);
//				return isAboveMinAgeResident && isBelowMaxCarsResident; // return true in order to pass policy
//			} else if (policyConditionSelector == 1) {
//				System.out.println("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxUnitsResident "
//						+ isBelowMaxUnitsResident);
//				if (!(isAboveMinAgeResident && isBelowMaxUnitsResident))
//					alertWindow("isAboveMinAgeResident " + isAboveMinAgeResident + " isBelowMaxUnitsResident "
//							+ isBelowMaxUnitsResident);
//				return isAboveMinAgeResident && isBelowMaxUnitsResident;
//			} else if (policyConditionSelector == 2) {
//				System.out.println(
//						"isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxCarsCompany " + isBelowMaxCarsCompany);
//				if (!(isNotExpiredCompany && isBelowMaxCarsCompany))
//					alertWindow("isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxCarsCompany "
//							+ isBelowMaxCarsCompany);
//				return isNotExpiredCompany && isBelowMaxCarsCompany;
//			} else if (policyConditionSelector == 3) {
//				System.out.println(
//						"isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxUnitsCompany " + isBelowMaxUnitsCompany);
//				if (!(isNotExpiredCompany && isBelowMaxUnitsCompany))
//					alertWindow("isNotExpiredCompany " + isNotExpiredCompany + " isBelowMaxUnitsCompany "
//							+ isBelowMaxUnitsCompany);
//				return isNotExpiredCompany && isBelowMaxUnitsCompany;
//			} else if (policyConditionSelector == 4) {
//				System.out.println("isAboveMinAgeCitizen " + isAboveMinAgeCitizen);
//				if (!isAboveMinAgeCitizen)
//					alertWindow("isAboveMinAgeCitizen " + isAboveMinAgeCitizen);
//				return isAboveMinAgeCitizen;
//			}
//			return false;
//		}
	//
//		public static boolean policyValuesOutcome(Object customer, Object rentable, Operation operationOut) {
//			int minAge = 0;
//			boolean expiry = false;
//			int maxUnits = 0;
//			int maxCars = 0;
//			Resident resident = null;
//			Citizen citizen = null;
//			Company company = null;
//			Car car = null;
//			RealEstate realEstate = null;
//			boolean policyResult = false;
//			int policyConditionSelector = -1; // k defines which customer and rentable is used
	//
//			if (customer instanceof Resident) {
//				resident = (Resident) customer;
//			} else if (customer instanceof Citizen) {
//				citizen = (Citizen) customer;
//			} else {
//				company = (Company) customer;
//			}
	//
//			if (rentable instanceof Car) {
//				car = (Car) rentable;
//			} else {
//				realEstate = (RealEstate) rentable;
//			}
	//
//			if (resident != null && car != null) {
//				minAge = 18;
//				maxCars = 2;
//				policyConditionSelector = 0;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(resident, car, "rent"));
//					else
//						operations.add(operationOut);
//					System.out.println(operations.get(operations.size() - 1));
//					resident.addRentable(car);
//					car.setStatus(false);
//					System.out.println("customer: " + resident.getClass().getSimpleName() + " " + resident.getName()
//							+ ", id: " + resident.getId() + " made the operation of renting " + car);
//				} else
//					System.out.println("operation failed");
//			} else if (resident != null && realEstate != null) {
//				minAge = 20;
//				maxUnits = 1;
//				policyConditionSelector = 1;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(resident, realEstate, "rent"));
//					else
//						operations.add(operationOut);
//					System.out.println(operations.get(operations.size() - 1));
//					resident.addRentable(realEstate);
//					realEstate.setStatus(false);
//					System.out.println("customer: " + resident.getClass().getSimpleName() + " " + resident.getName()
//							+ ", id: " + resident.getId() + " made the operation of renting " + realEstate);
//				} else
//					System.out.println("operation failed");
//			} else if (company != null && car != null) {
//				expiry = company.isExpired();// >0 | >Now
//				maxCars = 10;
//				policyConditionSelector = 2;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(company, car, "rent"));
//					else
//						operations.add(operationOut);
//					System.out.println(operations.get(operations.size() - 1));
//					company.addRentable(car);
//					car.setStatus(false);
//					System.out.println("customer: " + company.getClass().getSimpleName() + " " + company.getName()
//							+ ", id: " + company.getId() + " made the operation of renting " + car);
//				} else
//					System.out.println("operation failed");
//			} else if (company != null && realEstate != null) {
//				expiry = company.isExpired();
//				maxUnits = 10;
//				policyConditionSelector = 3;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(company, realEstate, "rent"));
//					else
//						operations.add(operationOut);
//					System.out.println(operations.get(operations.size() - 1));
//					company.addRentable(realEstate);
//					realEstate.setStatus(false);
//					System.out.println("customer: " + company.getClass().getSimpleName() + " " + company.getName()
//							+ ", id: " + company.getId() + " made the operation of renting " + realEstate);
//				} else
//					System.out.println("operation failed");
//			} else if (citizen != null && car != null) {
//				minAge = 16;
//				policyConditionSelector = 4;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(citizen, car, "rent"));
//					else
//						operations.add(operationOut);
//					System.out.println(operations.get(operations.size() - 1));
//					citizen.addRentable(car);
//					car.setStatus(false);
//					System.out.println("customer: " + citizen.getClass().getSimpleName() + " " + citizen.getName()
//							+ ", id: " + citizen.getId() + " made the operation of renting " + car);
//				} else
//					System.out.println("operation failed");
//			} else if (citizen != null && realEstate != null) {
//				minAge = 16;
//				policyConditionSelector = 4;
//				policyResult = policyTest(minAge, expiry, maxUnits, maxCars, resident, citizen, company, car, realEstate,
//						policyResult, policyConditionSelector);
//				if (policyResult) {
//					if (operationOut == null)
//						operations.add(new Operation(citizen, realEstate, "rent"));
//					else
//						operations.add(operationOut);
//					citizen.addRentable(realEstate);
//					realEstate.setStatus(false);
//					System.out.println("customer: " + citizen.getClass().getSimpleName() + " " + citizen.getName()
//							+ ", id: " + citizen.getId() + " made the operation of renting " + realEstate);
//				} else
//					System.out.println("operation failed");
//			}
//			return policyResult;
//		}
	//
	//}

}
