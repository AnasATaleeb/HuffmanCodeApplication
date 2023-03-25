package application;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
	String filePath;
	static File file;
	File compressedFile;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.getIcons().add(new Image("rar-format.png"));
			primaryStage.setTitle("Huffman Code Project - Anas Taleeb - 1203051");
			BorderPane pane = new BorderPane();

			ImageView d = new ImageView(new Image("data-compression.png"));
			d.setFitHeight(400);
			d.setFitWidth(400);

			Label l = new Label("Anas Taleeb - 1203051 \n    Huffman Project");
			l.setStyle("-fx-fount-family: Bell MT;\n" + "-fx-font-size: 20;\n" + "-fx-font-weight: Bold;\n"
					+ " -fx-text-fill: #CE2029;\n" + "-fx-text-fill: #f6f6f6;\n");

			VBox v1 = new VBox(30, d, l);
			v1.setAlignment(Pos.CENTER);

			ImageView c = new ImageView(new Image("compression.png"));
			c.setFitHeight(100);
			c.setFitWidth(100);
			Button compretion = new Button("Compretion", c);
			icons(compretion);
			butoonEffect(compretion);

			ImageView dc = new ImageView(new Image("distributed.png"));
			dc.setFitHeight(100);
			dc.setFitWidth(100);
			Button decompretion = new Button("Decompretion", dc);
			icons(decompretion);
			butoonEffect(decompretion);

			VBox v = new VBox(25, compretion, decompretion);
			v.setAlignment(Pos.CENTER);

			HBox h = new HBox(120, v1, v);
			h.setAlignment(Pos.CENTER);

			pane.setCenter(h);

			compretion.setOnAction(e -> {
				compretionsAction(primaryStage);
			});

			decompretion.setOnAction(e -> {
				decompretionsAction(primaryStage);
			});

			Scene scene = new Scene(pane, 1535, 800);
			pane.setStyle("-fx-background-color: linear-gradient(to right, #ffc749, #ff6800);");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void decompretionsAction(Stage primaryStage) {
		try {
			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(10));
			Label title = new Label("Decompretions");
			title.setStyle("-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-text-fill: #f6f6f6;\n");
			pane.setTop(title);
			BorderPane.setAlignment(title, Pos.CENTER);

			Label fileName = new Label("File Name");
			fileName.setPadding(new Insets(7));
			TextField tfile = new TextField();
			tfile.setMinWidth(350);
			tfile.setEditable(false);
			tfile.setStyle("-fx-border-radius: 0 0 0 0;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-text-fill: #ff6800;" + "-fx-background-radius: 0 0 0 0");

			ImageView f = new ImageView(new Image("folder.png"));
			f.setFitHeight(20);
			f.setFitWidth(20);
			Button fileb = new Button("", f);
			fileb.setOnMouseMoved(e -> {
				fileb.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n"
						+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
						+ " -fx-text-fill: #CE2029;\n" + "-fx-background-color: #d8d9e0;\n"
						+ "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
						+ "-fx-background-radius: 0 50 50 0");
			});

			fileb.setOnMouseExited(e -> {
				fileb.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n"
						+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
						+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
						+ "-fx-border-width:  3.5;" + "-fx-background-radius: 0 50 50 0");
			});

			HBox h1 = new HBox(tfile, fileb);
			h1.setAlignment(Pos.CENTER);

			IconedTextFieled(fileName, fileb);

			HBox h = new HBox(fileName, h1);
			h.setAlignment(Pos.CENTER);

			ImageView b = new ImageView(new Image("left.png"));
			b.setFitHeight(50);
			b.setFitWidth(50);
			Button back = new Button("Back", b);
			back.setOnAction(e -> {
				start(primaryStage);
			});
			icons(back);
			butoonEffect(back);

			ImageView c = new ImageView(new Image("compressed-file.png"));
			c.setFitHeight(50);
			c.setFitWidth(50);
			Button decomp = new Button("Decompretions", c);

			icons(decomp);
			butoonEffect(decomp);

			HBox button = new HBox(20, decomp, back);
			button.setAlignment(Pos.CENTER);

			VBox mix = new VBox(20, h, button);
			mix.setAlignment(Pos.CENTER);

			fileb.setOnAction(e -> {
				FileChooser fileChooser = new FileChooser();

				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("huf files(*.huf)", "*.huf");
				fileChooser.getExtensionFilters().add(extFilter);

				compressedFile = fileChooser.showOpenDialog(null);
				if (compressedFile == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error while choosing the file");
					alert.setContentText("Please choose a file ");
					alert.showAndWait();
				} else
					tfile.setText(compressedFile.getName());
				decomp.setDisable(false);
			});

			decomp.setOnAction(e -> {
				if (compressedFile == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error while choosing the file");
					alert.setContentText("Please choose a file ");
					alert.showAndWait();
				} else {

					try {
						fileb.setDisable(true);

						mix.getChildren().add(1, decompressingScene(Decompress.DecompressMainMethod(compressedFile)));
					} catch (Exception ex) {
						// TODO: handle exception
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Error while choosing the file");
						alert.setContentText("Please choose a file ");
						alert.showAndWait();
					}
				}
				decomp.setDisable(true);
			});

			pane.setCenter(mix);
			Scene scene = new Scene(pane, 1535, 800);
			scene.getStylesheets().add("application.css");
			pane.setStyle("-fx-background-color: linear-gradient(to right, #ffc749, #ff6800);");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// this scene displays the table value:code
	// after being decompressed
	public VBox decompressingScene(DecompressStatusObject[] ar) {
		Group group = new Group();

		Label headerLabel = new Label("Huffman Table");
		headerLabel.setFont(new Font(25));

		TableView<DecompressStatusObject> table = new TableView<DecompressStatusObject>();
		TableColumn<DecompressStatusObject, String> codeColumn = new TableColumn<DecompressStatusObject, String>(
				"Code");
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeColumn.setMinWidth(200);

		TableColumn<DecompressStatusObject, Character> valueColumn = new TableColumn<DecompressStatusObject, Character>(
				"value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		valueColumn.setMinWidth(200);
		table.setItems(getObservable(ar));
		table.getColumns().addAll(valueColumn, codeColumn);

		table.setMaxSize(450, 500);
		VBox v = new VBox();

		v.setSpacing(20);
		v.setAlignment(Pos.CENTER);

		v.getChildren().addAll(headerLabel, table);
		group.getChildren().addAll(v);
		return v;

	}

	private void compretionsAction(Stage primaryStage) {
		try {
			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(15));
			Label title = new Label("Compretions");
			title.setStyle("-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-text-fill: #f6f6f6;\n");
			pane.setTop(title);
			BorderPane.setAlignment(title, Pos.CENTER);

			Label fileName = new Label("File Name");
			fileName.setPadding(new Insets(7));
			TextField tfile = new TextField();
			tfile.setMinWidth(350);
			tfile.setEditable(false);
			tfile.setStyle("-fx-border-radius: 0 0 0 0;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-text-fill: #ff6800;" + "-fx-background-radius: 0 0 0 0");

			ImageView f = new ImageView(new Image("folder.png"));
			f.setFitHeight(20);
			f.setFitWidth(20);
			Button fileb = new Button("", f);
			fileb.setOnMouseMoved(e -> {
				fileb.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n"
						+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
						+ " -fx-text-fill: #CE2029;\n" + "-fx-background-color: #d8d9e0;\n"
						+ "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
						+ "-fx-background-radius: 0 50 50 0");
			});

			fileb.setOnMouseExited(e -> {
				fileb.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n"
						+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
						+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
						+ "-fx-border-width:  3.5;" + "-fx-background-radius: 0 50 50 0");
			});

			HBox h1 = new HBox(tfile, fileb);
			h1.setAlignment(Pos.CENTER);

			IconedTextFieled(fileName, fileb);

			HBox h = new HBox(fileName, h1);
			h.setAlignment(Pos.CENTER);

			ImageView b = new ImageView(new Image("left.png"));
			b.setFitHeight(50);
			b.setFitWidth(50);
			Button back = new Button("Back", b);
			back.setOnAction(e -> {
				start(primaryStage);
			});
			icons(back);
			butoonEffect(back);

			ImageView c = new ImageView(new Image("compression.png"));
			c.setFitHeight(50);
			c.setFitWidth(50);
			Button comp = new Button("Compretions", c);
			icons(comp);
			butoonEffect(comp);

			HBox button = new HBox(20, comp, back);
			button.setAlignment(Pos.CENTER);

			VBox v = new VBox(15, h, button);
			v.setAlignment(Pos.CENTER);
			pane.setCenter(v);
			Scene scene = new Scene(pane, 1535, 800);
			pane.setStyle("-fx-background-color: linear-gradient(to right, #ffc749, #ff6800);");
			scene.getStylesheets().add("application.css");

			fileb.setOnAction(e -> {
				FileChooser fileChooser = new FileChooser();

				file = fileChooser.showOpenDialog(null);

				if (file == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error while choosing the file");
					alert.setContentText("Please choose a file ");
					alert.showAndWait();
				} else {
					if (FileMethods.getFileExtension(file).equals("huf")) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Error while choosing the file");
						alert.setContentText("You cann't compress a compressed file");

						alert.showAndWait();
					}
				}
				tfile.setText(file.getName());

			});

			comp.setOnAction(e -> {
				try {
//					ProgressBar bar = new ProgressBar();
//					bar.setMinSize(200, 30);
//					Label l = new Label("Compressing...");
//					l.setFont(new Font(20));
//					VBox vb = new VBox(10, l, bar);
//					vb.setAlignment(Pos.CENTER);
//
//					Scene s = new Scene(vb, 200, 100);
//					Stage stage = new Stage();
//					stage.setScene(s);
//					stage.show();
					compressing(primaryStage);
				} catch (Exception ex) {
					// TODO: handle exception
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error while choosing the file");
					alert.setContentText("Please choose a file ");
					alert.showAndWait();
				}
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// this scene displays a tab pane to navigate easily between the views
	public Group compressScene(Stage primaryStage, StatusObject ar[], int nonZero, Header header, String headerTree) {
		Group group = new Group();
		TabPane mainpane = new TabPane();
		mainpane.setMinSize(1000, 750);
		mainpane.setId("mainPane");
		Tab headerTab = new Tab("Header", headerPane(primaryStage, header, headerTree));
		headerTab.setStyle("-fx-pref-width: 350;");
		headerTab.setClosable(false);
		TabPane statusPane = new TabPane();

		Tab tab1 = new Tab("Statstics Table ", tableStatusPane(ar, primaryStage));
		tab1.setStyle("-fx-pref-width: 250;");

		Tab tab2 = new Tab("Size comparison ", fileSizespane(header.getCompSize(), (int) file.length(), primaryStage));
		tab2.setStyle("-fx-pref-width: 250;\n" + "-fx-alignment: CENTER;");

		Tab tab3 = new Tab("Ascii occurance", asciiStatus(nonZero, primaryStage));
		tab3.setStyle("-fx-pref-width: 250;");
		statusPane.getTabs().addAll(tab1, tab2, tab3);
		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);
		Tab statusTab = new Tab("Status", statusPane);
		statusTab.setStyle("-fx-pref-width: 350;");
		statusTab.setClosable(false);
		mainpane.getTabs().addAll(headerTab, statusTab);
		group.getChildren().addAll(mainpane);
		return group;
	}

	// this view shows a chart which displays a comparison between the compressed
	// file size and the real file size
	public VBox fileSizespane(int compressedFileSize, int realFileSize, Stage primaryStage) {
		Label headerLabel = new Label("Files sizes");
		headerLabel.setFont(new Font(25));
		ImageView b = new ImageView(new Image("left.png"));
		b.setFitHeight(50);
		b.setFitWidth(50);
		Button backButton = new Button("Back to main menu", b);
		backButton.setOnAction(e -> {
			start(primaryStage);
		});
		icons(backButton);
		butoonEffect(backButton);
		Group group = new Group();
		VBox v = new VBox();
		v.setAlignment(Pos.CENTER);
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Files sizes");
		xAxis.setStyle("-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Size in bytes");
		yAxis.setStyle("-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");

		BarChart barChart = new BarChart(xAxis, yAxis);

		XYChart.Series dataSeries1 = new XYChart.Series();

		barChart.setLegendVisible(false);
		dataSeries1.getData().add(new XYChart.Data("Compressed file size", compressedFileSize));
		dataSeries1.getData().add(new XYChart.Data("File size ", realFileSize));

		barChart.getData().add(dataSeries1);
		barChart.setMinWidth(400);
		barChart.setMaxWidth(550);
		barChart.setMinHeight(450);

		Label percentage = new Label("Compression Percentage :");
		percentage.setPadding(new Insets(7));
		DecimalFormat df = new DecimalFormat("#.000");
		TextField tper = new TextField();
		tper.setStyle("-fx-text-fill: #ff6800;");
		double compressionPercentage = (1 - (Double.parseDouble(Integer.toString(compressedFileSize))
				/ Double.parseDouble(Integer.toString(realFileSize)))) * 100;
		tper.setText(df.format(compressionPercentage) + " %");
		tper.setEditable(false);
		IconedTextFieled(percentage, tper);
		HBox h1 = new HBox(percentage, tper);
		h1.setAlignment(Pos.CENTER);

		v.getChildren().addAll(headerLabel, barChart, h1, backButton);
		v.setMinHeight(600);
		v.setMinWidth(600);
		v.setSpacing(10);
		v.setAlignment(Pos.CENTER);

		group.getChildren().addAll(v);
		group.getStylesheets().add("application.css");

		return v;

	}

	// this pane shows the header of the compressed file in a text area
	public VBox headerPane(Stage primaryStage, Header headerToShow, String headerTree) {
		TextArea TA = new TextArea();
		Label headerLabel = new Label("Header");
		headerLabel
				.setStyle("-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");

		Label note = new Label(
				"*Note the ones and zeros are represented as bits in the huffman tree in the compressed file*");
		note.setStyle("-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");
		String inTextArea = "";
		inTextArea += "Signeture : " + headerToShow.getSig() + "\n\nExtensionSize :" + headerToShow.getExtensionSize()
				+ "\n\nExtension : " + headerToShow.getExtension() + "\n\nTotalSize :" + headerToShow.getTotalSize()
				+ "\n\nHeaderSize : " + headerToShow.getHeaderSize() + "\n\nHeaderTree : " + headerTree;

		TA.setEditable(false);
		TA.setText(inTextArea);
		TA.setStyle("-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
				+ " -fx-text-fill: #ff6800;\n" + "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;");

		ImageView b = new ImageView(new Image("left.png"));
		b.setFitHeight(50);
		b.setFitWidth(50);
		Button backButton = new Button("Back to main menu", b);
		backButton.setOnAction(e -> {
			start(primaryStage);
		});
		icons(backButton);
		butoonEffect(backButton);
		TA.setMinWidth(500);
		TA.setMinHeight(400);

		VBox box = new VBox(15, headerLabel, TA, note, backButton);
		box.setAlignment(Pos.CENTER);
		box.setMinWidth(550);
		box.setMinHeight(750);

		return box;
	}

	// this view is used to display the table value:code:freq:length
	public HBox tableStatusPane(StatusObject[] ar, Stage primaryStage) {
		Label headerLabel = new Label("Statstics Table");
		headerLabel.setFont(new Font(25));
		ImageView b = new ImageView(new Image("left.png"));
		b.setFitHeight(50);
		b.setFitWidth(50);
		Button backButton = new Button("Back to main menu", b);
		backButton.setOnAction(e -> {
			start(primaryStage);
		});
		icons(backButton);
		butoonEffect(backButton);
		Group group = new Group();
		TableView<StatusObject> table = new TableView<StatusObject>();
		TableColumn<StatusObject, String> codeColumn = new TableColumn<StatusObject, String>("Code");
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeColumn.setMinWidth(120);

		TableColumn<StatusObject, Integer> freqColumn = new TableColumn<StatusObject, Integer>("Frequency");
		freqColumn.setCellValueFactory(new PropertyValueFactory<>("freq"));
		freqColumn.setMinWidth(120);

		TableColumn<StatusObject, Character> valueColumn = new TableColumn<StatusObject, Character>("value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		valueColumn.setMinWidth(120);

		TableColumn<StatusObject, Byte> lenghtColumn = new TableColumn<StatusObject, Byte>("Length");
		lenghtColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
		lenghtColumn.setMinWidth(120);
		table.getColumns().addAll(valueColumn, codeColumn, freqColumn, lenghtColumn);
		table.setItems(getObservable(ar));
		table.setMaxWidth(500);
		table.setMinHeight(450);

		VBox v = new VBox();
		v.setMinWidth(600);
		v.setMinHeight(700);

		v.setSpacing(20);
		v.setAlignment(Pos.CENTER);

		v.getChildren().addAll(headerLabel, table, backButton);
		group.getChildren().addAll(v);

		HBox h = new HBox(v);
		h.setAlignment(Pos.CENTER);
		return h;
	}

	// this view displays the number of chars found in the file from the 256 and the
	// ones who wasn't found
	// nonzero vs zero
	public VBox asciiStatus(int nonZero, Stage primaryStage) {

		Group group = new Group();
		VBox v = new VBox();
		ImageView b = new ImageView(new Image("left.png"));
		b.setFitHeight(50);
		b.setFitWidth(50);
		Button backButton = new Button("Back to main menu", b);
		backButton.setOnAction(e -> {
			start(primaryStage);
		});
		icons(backButton);
		butoonEffect(backButton);
		Label headerLabel = new Label("Bytes in the file");
		headerLabel.setFont(new Font(25));
		v.setAlignment(Pos.CENTER);
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Frequencies");
		xAxis.setStyle("-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Occurence");
		yAxis.setStyle("-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");

		BarChart barChart = new BarChart(xAxis, yAxis);

		XYChart.Series dataSeries1 = new XYChart.Series();

		barChart.setLegendVisible(false);
		dataSeries1.getData().add(new XYChart.Data("Occured", nonZero));
		dataSeries1.getData().add(new XYChart.Data("Didn't occur", 256 - nonZero));

		barChart.getData().add(dataSeries1);
		barChart.setMinWidth(400);
		barChart.setMaxWidth(550);
		barChart.setMinHeight(450);
		v.setSpacing(10);

		v.getChildren().addAll(headerLabel, barChart, backButton);
		v.setMinHeight(700);
		v.setMinWidth(600);

		group.getChildren().addAll(v);
		group.getStylesheets().add("style.css");

		return v;

	}

	// This method makes the heap after calling the readFromfile method
	public void compressing(Stage primaryStage) {
		try {
			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(15));
			Label title = new Label("Compretions");
			title.setStyle("-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-text-fill: #f6f6f6;\n");
			pane.setTop(title);

			BorderPane.setAlignment(title, Pos.CENTER);

			int nonZero = 0;
			int ar[] = FileMethods.readToCompress();
			for (int i = 0; i < ar.length; i++) {
				if (ar[i] != 0) {
					nonZero++;
				}
			}
			int counter = 1;

			// getting all the nonzero in array
			HeapNode heapNodes[] = new HeapNode[nonZero + 1];
			for (int i = 0; i < ar.length; i++) {
				if (ar[i] != 0) {
					if (i >= 128)
						heapNodes[counter] = new HeapNode(ar[i], (byte) (i - 256));
					else
						heapNodes[counter] = new HeapNode(ar[i], (byte) i);
					heapNodes[counter].setData(true);
					counter++;
				}
			}

			// making heap of the nonZero array
			Heap heap = new Heap(heapNodes.length, heapNodes, heapNodes.length - 1);

			// building the tree inside the heap
			// after this method the root of the tree will be heap.ar[1]
			for (int i = 1; i < heapNodes.length - 1; i++) {
				HeapNode z = new HeapNode();
				HeapNode x = heap.deleteMin();
				HeapNode y = heap.deleteMin();
				z.setLeft(x);
				z.setRight(y);
				z.setFreq(x.getFreq() + y.getFreq());
				heap.insert(z);
			}

			if (nonZero != 0) {
				heap.getAr()[1].prepairingCode("", (byte) 0);
				HeapNode rootNode = heap.getAr()[1];
				StatusObject statusArray[] = new StatusObject[256];

				rootNode.preparingStatusTable(statusArray);
				int nOfLeaves = 0;
				for (int i = 0; i < statusArray.length; i++) {
					if (statusArray[i] == null) {
//						statusArray[i] = new StatusObject(); // if the object is null an empty one is made

					} else {
						nOfLeaves++;
					}

				}

				String treeHeader = rootNode.postOrderCodingInHeaderTextArea();
				int nOfNonLeaves = rootNode.countingTheRoots();
				Header header = compressMainMethod(nOfLeaves, rootNode, statusArray, nOfNonLeaves);
				pane.setCenter(compressScene(primaryStage, statusArray, nonZero, header, treeHeader));
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error");
				alert.setContentText("The choosen file is empty");
				alert.showAndWait();
			}
			Scene scene = new Scene(pane, 1535, 800);
			pane.setStyle("-fx-background-color: linear-gradient(to right, #ffc749, #ff6800);");
			scene.getStylesheets().add("application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// this method writes the new representation of the chars on the compressed file
	// after creating the compressed file
	public static Header compressMainMethod(int nOfLeaves, HeapNode rootNode, StatusObject ar[], int nOfNonLeaves) {

		int headerSize2 = getHeaderSize(nOfNonLeaves, nOfLeaves);
		int totalSize = (int) Main.file.length(); // getting the file size

		String fileExtension = FileMethods.getFileExtension(Main.file); // getting the extension
		Header header = new Header(FileMethods.getFileExtension(Main.file), (byte) fileExtension.length(), headerSize2);
		header.setTotalSize(totalSize);
		String directory = Main.file.getParent();

		File compressedFile = new File(directory + "\\" + FileMethods.getFileAbsName(Main.file) + ".huf");

		// creating a new file if it wasn't already created
		try {
			compressedFile.createNewFile();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str = null;
		try {
			// creating a new writer
			MyWriter writer = new MyWriter(compressedFile);

			str = rootNode.postOrderCodingInFile();
			str = ConversionsAndCompletions.completeHeaderTreeString(str);
			System.out.println(str);
			writer.printHeaderStatus(header, str);
			FileMethods.readToEncode(writer, ar);
			System.out.println();
			writer.cleanTheCurrentBits();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		header.setCompSize((int) compressedFile.length());
		return header;
	}

	private static int getHeaderSize(int nOfNonLeaves, int nOfLeaves) {
		return (int) ((double) Math.ceil((nOfNonLeaves + nOfLeaves) / 8.0)) + nOfLeaves; // getting the
		// headerSize
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static ObservableList<StatusObject> getObservable(StatusObject ar[]) {
		ObservableList<StatusObject> observableList = FXCollections.observableArrayList();

		for (StatusObject ob : ar) {
			if (ob != null)
				observableList.add(ob);
		}

		return observableList;

	}

	public static ObservableList<DecompressStatusObject> getObservable(DecompressStatusObject ar[]) {
		ObservableList<DecompressStatusObject> observableList = FXCollections.observableArrayList();

		for (DecompressStatusObject ob : ar) {
			if (ob != null)
				observableList.add(ob);
		}

		return observableList;

	}

	private void IconedTextFieled(javafx.scene.Node l, javafx.scene.Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" + "-fx-font-size: 14;\n" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 50;" + "-fx-font-weight: Bold;\n" + "-fx-background-color:#d8d9e0;"
				+ "-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-text-fill: #ff6800;" + "-fx-background-radius: 0 50 50 0");
	}

	private void icons(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-background-radius: 25 25 25 25");
	}

	private void butoonEffect(Node b) {
		b.setOnMouseMoved(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #ff6800;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});
	}
}
