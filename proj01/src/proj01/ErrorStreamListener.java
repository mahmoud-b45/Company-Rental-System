//package proj01;
//
//import java.io.OutputStream;
//
//import javafx.application.Platform;
//import javafx.scene.control.Alert;
//
//public class ErrorStreamListener extends OutputStream {
//	private StringBuilder buffer = new StringBuilder();
//
//	@Override
//	public void write(int b) {
////		if (b == '\n') {
//			if (InfoSys.flush) {
//				handleMessage(buffer.toString());
//				buffer.setLength(0);
//				InfoSys.flush = false;
//				System.err.flush();
////			}
//		} else {
//			buffer.append((char) b);
//			System.out.println(buffer.toString());
//		}
//
//	}
//
////    private void handleMessage(String message) {
////        // Do something with the error message
////        System.out.println("Handled error: " + message); // Replace with alert, logger, etc.
////    }
//
//	private void handleMessage(String message) {
//		Platform.runLater(() -> {
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//			alert.setTitle("Error");
//			alert.setHeaderText("An error occurred");
//			alert.setContentText(message);
//			alert.showAndWait();
//		});
//	}
//}
