package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	String [] [] login = { {"Anas" , "anas" }, {"Raghad","raghad"} , {"Shahd", "shahd"} ,{"Maryam","maryam"} };
	AccountManeger Account = new AccountManeger();
	ArrayList<AccountManeger> daily = new ArrayList<AccountManeger>();
	ArrayList<GenralLedger> geralLedjer = new ArrayList<GenralLedger>();
	ArrayList<AccountManeger> JournalVoucherdata = new ArrayList<AccountManeger>();
	ArrayList<Revenue> revenueData = new ArrayList<Revenue>();
	ArrayList<Puchases> purchasesData = new ArrayList<Puchases>();
	Scene welcomeSceneArab,welcomeSceneEng;
	@Override
	public void start(Stage primaryStage) {
		try {
			readFiles();
			ArabicLang(primaryStage);
		} catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	private void ArabicLang(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(500);
		logo.setFitHeight(400);

		Label welcome = new Label("أهلا بعودتك !");
		welcome.setStyle("-fx-font-size: 50;");
		welcome.setTextFill(Color.web("silver"));

		VBox v1 = new VBox(40,welcome,logo);
		v1.setAlignment(Pos.CENTER);

		Label user = new Label("اسم المستخدم");
		user.setPadding(new Insets(7));
		Label pass = new Label("كلمة المرور     ");
		pass.setPadding(new Insets(7));
		TextField usert = new TextField();
		PasswordField passt = new PasswordField();
		IconedTextFieledArb(user, usert);
		IconedTextFieledArb(pass, passt);

		HBox h1 = new HBox(usert,user);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(passt,pass);
		h2.setAlignment(Pos.CENTER);

		Button logIN = new Button("تسجيل الدخول");
		logIN.setEffect(new DropShadow());
		icons(logIN);
		butoonEffect(logIN); 

		Label langt = new Label("Choice language | إختر اللغة");
		langt.setStyle("-fx-font-size: 14;\n" +
				"-fx-font-weight: Bold;");
		langt.setTextFill(Color.web("silver"));
		langt.setEffect(new DropShadow());
		langt.setFont(new Font(13));
		langt.setPadding(new Insets(7));

		Button arabic = new Button("Arabic");
		arabic.setEffect(new DropShadow());
		icons(arabic);
		arabic.setStyle("-fx-font-size: 13;");
		arabic.setOnAction(e ->{
			ArabicLang(primaryStage);
		});
		Button english = new Button("English");
		english.setEffect(new DropShadow());
		english.setStyle("-fx-font-size: 13;");
		icons(english);
		english.setOnAction(e ->{
			EnglishLang(primaryStage);
		});

		icons(arabic);
		butoonEffect(arabic);
		icons(english);
		butoonEffect(english);

		Label l = new Label(" | ");
		l.setTextFill(Color.web("silver"));
		l.setFont(new Font(20));

		HBox h3 = new HBox(5,english,l,arabic);
		VBox v2 = new VBox(2,langt,h3);
		v2.setPadding(new Insets(30));
		h3 .setAlignment(Pos.CENTER);
		v2.setAlignment(Pos.TOP_CENTER);

		Label wrongPass = new Label();
		VBox v = new VBox(30,wrongPass, h1,h2,logIN);
		v.setAlignment(Pos.CENTER);

		HBox hh = new HBox(200,v,v1);

		hh.setAlignment(Pos.CENTER);
		pane.setCenter(hh);
		logIN.setOnAction(e ->{
			boolean flag = false;
			for (int j = 0; j < login.length; j++) {
				if(usert.getText().equals(login[j][0]) && passt.getText().equals(login[j][1]) ) {
					flag= true;
					wrongPass.setText("");
					ArabicAction(primaryStage);
					usert.clear();
					passt.clear();
				}				
			}
			if(!flag) {
				wrongPass.setText("! اسم المستخدم أو كلمة المرور خاطئة !");
				wrongPass.setStyle("-fx-font-size: 15;\n" +
						"-fx-text-fill: silver;");
				usert.clear();
				passt.clear();
			}
		});


		Button help = new Button("كيف أستخدم البرنامج !");
		icons(help);
		butoonEffect(help);

		help.setOnAction(e ->{

			HowToUseArbic(primaryStage);			
		});

		v.getChildren().add(help);
		pane.setCenter(hh);
		pane.setBottom(v2);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		welcomeSceneArab = new Scene(pane,1535,800);
		primaryStage.setScene(welcomeSceneArab);
		primaryStage.show();
	}


	private void HowToUseArbic(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		BorderPane centerPane =  new BorderPane();
		centerPane.setPadding(new Insets(15));
		Label title = new Label("كيف استخدم البرنامج !");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.CENTER);

		File file = new File("C:\\Users\\Anas Taleeb\\Desktop\\BZU\\Comp231\\Lab5\\ARSI\\src\\ARSM.mp4");
		Media media = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView();
		mediaPlayer.setAutoPlay(true);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(1000);
		mediaView.setFitHeight(1000);

		VBox vv = new VBox(mediaView);
		vv.setStyle("-fx-border-radius:  0 0 0 0;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  4;");
		vv.setMaxWidth(1000);
		vv.setMaxHeight(1000);
		vv.setAlignment(Pos.CENTER);


		Button play = new Button("تشغيل");
		icons(play);
		butoonEffect(play);
		Button pause = new Button("توقف");
		icons(pause);
		butoonEffect(pause);
		Button reset = new Button("اعادة تشغيل");
		icons(reset);
		butoonEffect(reset);
		HBox h = new HBox(10, reset,pause,play);
		h.setAlignment(Pos.CENTER);
		play.setOnAction(e ->{
			mediaPlayer.play();
		});

		pause.setOnAction(e ->{
			mediaPlayer.pause();
		});

		reset.setOnAction(e ->{
			if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
				mediaPlayer.seek(Duration.seconds(0.0));
			}
		});

		VBox  v= new VBox(30 , vv, h);
		v.setAlignment(Pos.CENTER);


		centerPane.setCenter(v);

		Button back = new Button("عودة");
		icons(back);
		butoonEffect(back);

		back.setOnAction(e ->{
			ArabicLang(primaryStage);
		});

		centerPane.setBottom(back);
		centerPane.setPadding(new Insets(30));
		pane.setCenter(centerPane);


		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}

	private void HowToUseEnglish(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		BorderPane centerPane =  new BorderPane();
		centerPane.setPadding(new Insets(15));
		Label title = new Label("How to use program !");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.CENTER);

		File file = new File("C:\\Users\\Anas Taleeb\\Desktop\\BZU\\Comp231\\Lab5\\ARSI\\src\\ARSME.mp4");
		Media media = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView();
		mediaPlayer.setAutoPlay(true);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(1000);
		mediaView.setFitHeight(1000);

		VBox vv = new VBox(mediaView);
		vv.setStyle("-fx-border-radius:  0 0 0 0;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  4;");
		vv.setMaxWidth(1000);
		vv.setMaxHeight(1000);
		vv.setAlignment(Pos.CENTER);


		Button play = new Button("Play");
		icons(play);
		butoonEffect(play);
		Button pause = new Button("Pause");
		icons(pause);
		butoonEffect(pause);
		Button reset = new Button("Reset");
		icons(reset);
		butoonEffect(reset);
		HBox h = new HBox(10, reset,pause,play);
		h.setAlignment(Pos.CENTER);
		play.setOnAction(e ->{
			mediaPlayer.play();
		});

		pause.setOnAction(e ->{
			mediaPlayer.pause();
		});

		reset.setOnAction(e ->{
			if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
				mediaPlayer.seek(Duration.seconds(0.0));
			}
		});

		VBox  v= new VBox(30 , vv, h);
		v.setAlignment(Pos.CENTER);


		centerPane.setCenter(v);

		Button back = new Button("Back");
		icons(back);
		butoonEffect(back);

		back.setOnAction(e ->{
			EnglishLang(primaryStage);
		});

		centerPane.setBottom(back);
		centerPane.setPadding(new Insets(30));
		pane.setCenter(centerPane);


		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}


	private void EnglishLang(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(500);
		logo.setFitHeight(400);

		Label welcome = new Label("Welcome Back !");
		welcome.setStyle("-fx-font-size: 50;");
		welcome.setTextFill(Color.web("silver"));

		VBox v1 = new VBox(40,welcome,logo);
		v1.setAlignment(Pos.CENTER);

		Label user = new Label("USER NAME");
		user.setPadding(new Insets(7));
		Label pass = new Label("PASSWORD ");
		pass.setPadding(new Insets(7));
		TextField usert = new TextField();
		PasswordField passt = new PasswordField();
		IconedTextFieled(user, usert);
		IconedTextFieled(pass, passt);

		HBox h1 = new HBox(user,usert);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(pass,passt);
		h2.setAlignment(Pos.CENTER);

		Button logIN = new Button("lOG IN");
		logIN.setEffect(new DropShadow());

		icons(logIN);
		butoonEffect(logIN);

		Label langt = new Label("Choose language | إختر اللغة");
		langt.setStyle("-fx-font-size: 14;\n" +
				"-fx-font-weight: Bold;");
		langt.setTextFill(Color.web("silver"));
		langt.setEffect(new DropShadow());
		langt.setFont(new Font(13));
		langt.setPadding(new Insets(7));

		Button arabic = new Button("Arabic");
		arabic.setEffect(new DropShadow());
		icons(arabic);
		arabic.setStyle("-fx-font-size: 13;");
		arabic.setOnAction(e ->{
			ArabicLang(primaryStage);
		});
		Button english = new Button("English");
		english.setEffect(new DropShadow());
		english.setStyle("-fx-font-size: 13;");
		icons(english);
		english.setOnAction(e ->{
			EnglishLang(primaryStage);
		});

		icons(arabic);
		butoonEffect(arabic);
		icons(english);
		butoonEffect(english);

		Label l = new Label(" | ");
		l.setTextFill(Color.web("silver"));
		l.setFont(new Font(20));

		HBox h3 = new HBox(5,english,l,arabic);
		VBox v2 = new VBox(2,langt,h3);
		v2.setPadding(new Insets(30));
		h3 .setAlignment(Pos.CENTER);
		v2.setAlignment(Pos.TOP_CENTER);

		Label wrongPass = new Label();


		VBox v = new VBox(30,wrongPass, h1,h2,logIN);
		v.setAlignment(Pos.CENTER);


		HBox hh = new HBox(200,v1,v);

		hh.setAlignment(Pos.CENTER);
		pane.setCenter(hh);
		logIN.setOnAction(e ->{
			boolean flag = false;
			for (int j = 0; j < login.length; j++) {
				if(usert.getText().equals(login[j][0]) && passt.getText().equals(login[j][1]) ) {
					flag= true;
					EnglishAction(primaryStage);
					wrongPass.setText("");
					usert.clear();
					passt.clear();
				}				
			}
			if(!flag) {
				wrongPass.setText("! Wrong Username or Password !");
				wrongPass.setStyle("-fx-font-size: 15;\n" +
						"-fx-text-fill: silver;");
				usert.clear();
				passt.clear();
			}
		});

		Button help = new Button("How to use program! ");
		icons(help);
		butoonEffect(help);
		
		help.setOnAction(e ->{
			HowToUseEnglish(primaryStage);			
		}); 
		
		VBox v3 = new VBox(10,help,v2);
		v3.setAlignment(Pos.CENTER);
		
		pane.setCenter(hh);
		pane.setBottom(v3);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		welcomeSceneEng = new Scene(pane,1535,800);
		primaryStage.setScene(welcomeSceneEng);
		primaryStage.show();
	}

	private void EnglishAction(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void financialAnalysisOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		BorderPane ceBorderPane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Financial Analysis");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);


		Label quick = new Label("                           Liquidity Ratio                                ");
		quick.setPadding(new Insets(7));
		quick.setMinWidth(180);
		TextField quickt = new TextField();
		finEffect(quick, quickt);
		VBox hquick = new VBox(quick,quickt);
		hquick.setAlignment(Pos.CENTER);

		quickt.setText( Account.LiquidityRatio() + " % " );
		quickt.setEditable(false);


		Label debt= new Label("                                Debt Ratio                                ");
		debt.setPadding(new Insets(7));
		debt.setMinWidth(180);
		TextField debtt = new TextField();
		finEffect(debt, debtt);
		VBox hdepit = new VBox(debt,debtt);
		hdepit.setAlignment(Pos.CENTER);

		debtt.setText(Account.DeiptEquityRatio() + " % ");
		debtt.setEditable(false);


		Label activity = new Label("                              Activity Ratio                             ");
		activity.setPadding(new Insets(7));
		activity.setMinWidth(180);
		TextField activityt = new TextField();
		finEffect(activity, activityt);
		VBox hactivity = new VBox(activity,activityt);
		hactivity.setAlignment(Pos.CENTER);

		activityt.setText( Account.ActivityRatio() + "");
		activityt.setEditable(false);


		Label perftbility = new Label("                         Profitability Ratio                         ");
		perftbility.setPadding(new Insets(7));
		perftbility.setMinWidth(180);
		TextField perftbilityt = new TextField();
		finEffect(perftbility, perftbilityt);
		VBox hperftbility = new VBox(perftbility,perftbilityt);
		hperftbility.setAlignment(Pos.CENTER);

		perftbilityt.setText( Account.GrossProfit() + " % "); 
		perftbilityt.setEditable(false);


		Label lquick = new Label("By Quick Ratio");
		lquick.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label ldebt = new Label("By Debt-Equity Ratio");
		ldebt.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label lactivity = new Label("By Inventory Turenover Ratio");
		lactivity.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label lperf = new Label("By Profit Margin Ratio");
		lperf.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		HBox h1 = new HBox(20,hquick,lquick);
		h1.setAlignment(Pos.CENTER_LEFT);
		h1.setPadding(new Insets(15));

		HBox h2 = new HBox(20,hdepit,ldebt);
		h2.setAlignment(Pos.CENTER_LEFT);
		h2.setPadding(new Insets(15));

		HBox h3 = new HBox(20,hactivity,lactivity);
		h3.setAlignment(Pos.CENTER_LEFT);
		h3.setPadding(new Insets(15));

		HBox h4 = new HBox(20,hperftbility,lperf);
		h4.setAlignment(Pos.CENTER_LEFT);
		h4.setPadding(new Insets(15));

		VBox mix = new VBox(30,h1,h2,h3,h4);
		mix.setAlignment(Pos.CENTER_LEFT);

		ceBorderPane.setCenter(mix);
		pane.setCenter(ceBorderPane);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void financialStatementOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("Financial Statement");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("Company Name");
		Label date = new Label("Year        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField();


		IconedTextFieled(date, datet);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(name,namet);
		h2.setAlignment(Pos.CENTER_LEFT);

		RadioButton type1  = new RadioButton(" Income Statment ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton(" Balance Sheet Statment ");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" Owner Equity Statment ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label(" Statment ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);
		HBox types = new HBox(tybePayment,typePay);
		types.setAlignment(Pos.CENTER_LEFT);

		Button move = new Button(" Move to statment ");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Income Statment "))
				incomeSatamentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Balance Sheet Statment "))
				balanceSheetStatmentEng(namet.getText(),datet.getText(),primaryStage);
			else if(((RadioButton) tg.getSelectedToggle()).getText().equals(" Owner Equity Statment "))
				ownerEquityStatmentEng(namet.getText(),datet.getText(),primaryStage);
		});

		VBox v = new VBox(10,h2,h1,types,move);
		v.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void generalLedgerOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("General Ledger");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);


		TableView<GenralLedger> table = new TableView<GenralLedger>();

		TableColumn <GenralLedger, String> date = new TableColumn<GenralLedger, String>("Date");
		date.setMinWidth(218);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <GenralLedger, String>num = new TableColumn<GenralLedger, String>("Voucher's \n Number");
		num.setMinWidth(130);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, String>account = new TableColumn<GenralLedger, String>("Account");
		account.setMinWidth(300);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> depitPrice = new TableColumn<GenralLedger, Double>("Debit ");
		depitPrice.setMinWidth(220);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(((GenralLedger)p.getValue()).getDepit()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> creditPrice = new TableColumn<GenralLedger, Double>("Credit ");
		creditPrice.setMinWidth(220);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(((GenralLedger)p.getValue()).getCredit()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> price = new TableColumn<GenralLedger, Double>("Price");
		price.setMinWidth(440);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<GenralLedger> data = FXCollections.observableArrayList();

		table.getColumns().addAll(num,date,account,price);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Button revenueButton = new Button("Revenue");
		revenueButton.setPrefWidth(200);
		revenueButton.setPrefHeight(40);
		revenueButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );

		revenueButton.setEffect(new DropShadow());
		revenueButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getRevenueGenral().size(); i++) {
				table.getItems().add( Account.getRevenueGenral().get(i));
			}
		});

		Button purchasesButton = new Button("Purchases");
		purchasesButton.setPrefWidth(200);
		purchasesButton.setPrefHeight(40);
		purchasesButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		purchasesButton.setEffect(new DropShadow());
		purchasesButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getPurchaseGenral().size(); i++) {
				table.getItems().add( Account.getPurchaseGenral().get(i));
			}
		});


		Button cashButton = new Button("Cash");
		cashButton.setPrefWidth(200);
		cashButton.setPrefHeight(40);
		cashButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		cashButton.setEffect(new DropShadow());

		cashButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getCashGenral().size(); i++) {
				table.getItems().add(Account.getCashGenral().get(i));
			}
		});

		Button accountPayableButton = new Button("Account Payable");
		accountPayableButton.setPrefWidth(200);
		accountPayableButton.setPrefHeight(40);
		accountPayableButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		accountPayableButton.setEffect(new DropShadow());

		accountPayableButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getAccountPayableGenral().size(); i++) {
				table.getItems().add( Account.getAccountPayableGenral().get(i));
			}
		});


		Button accountReceivableButton = new Button("Account Receivable");
		accountReceivableButton.setPrefWidth(200);
		accountReceivableButton.setPrefHeight(40);
		accountReceivableButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		accountReceivableButton.setEffect(new DropShadow());

		accountReceivableButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getAccountReceivableGenral().size(); i++) {
				table.getItems().add( Account.getAccountReceivableGenral().get(i));
			}
		});

		Button expenseButton = new Button("Expense");
		expenseButton.setPrefWidth(200);
		expenseButton.setPrefHeight(40);
		expenseButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		expenseButton.setEffect(new DropShadow());
		expenseButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getExpenseGenral().size(); i++) {
				table.getItems().add( Account.getExpenseGenral().get(i));
			}
		});

		Button vat = new Button("VAT");
		vat.setPrefWidth(200);
		vat.setPrefHeight(40);
		vat.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		vat.setEffect(new DropShadow());

		vat.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getVatGenral().size(); i++) {
				table.getItems().add( Account.getVatGenral().get(i));
			}
		});

		Button owner = new Button("Capital");
		owner.setPrefWidth(200);
		owner.setPrefHeight(40);
		owner.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		owner.setEffect(new DropShadow());

		owner.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getOwnerGenral().size(); i++) {
				table.getItems().add( Account.getOwnerGenral().get(i));
			}
		});

		HBox hBoxButton = new HBox(revenueButton,purchasesButton,expenseButton,cashButton,accountPayableButton,accountReceivableButton,vat,owner);
		table.setMinHeight(555);
		VBox v = new VBox(hBoxButton,table);
		v.setAlignment(Pos.CENTER);
		ceBorderPane.setCenter(v);

		ceBorderPane.setAlignment(table, Pos.CENTER);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void generaljournalOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("General Journal");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);

		TableView<AccountManeger> table = new TableView<AccountManeger>();

		TableColumn <AccountManeger, String> date = new TableColumn<AccountManeger, String>("Date ");
		date.setMinWidth(128);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <AccountManeger, String>num = new TableColumn<AccountManeger, String>("Voucher's \n Number");
		num.setMinWidth(80);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String>account = new TableColumn<AccountManeger, String>("Account ");
		account.setMinWidth(150);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> depitPrice = new TableColumn<AccountManeger, Double>("Debit ");
		depitPrice.setMinWidth(140);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> creditPrice = new TableColumn<AccountManeger, Double>("Credit ");;
		creditPrice.setMinWidth(140);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");


		TableColumn<AccountManeger, String> depitPriceJournal = new TableColumn<AccountManeger, String>("Debit ");
		depitPriceJournal.setMinWidth(225);
		depitPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCriditWhthVat()));
		depitPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> creditPriceJournal = new TableColumn<AccountManeger, String>("Credit ");
		creditPriceJournal.setMinWidth(225);
		creditPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDepiteWhthVat()));
		creditPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> journal = new TableColumn<AccountManeger, String>("Journal");
		journal.setMinWidth(450);
		TableColumn<AccountManeger, Double> price = new TableColumn<AccountManeger, Double>("Value");
		price.setMinWidth(280);
		journal.getColumns().addAll(creditPriceJournal,depitPriceJournal);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<AccountManeger> data = FXCollections.observableArrayList(daily);

		table.getColumns().addAll(date,num,account,journal,price);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Button save = new Button("Save");
		butoonEffect(save);
		icons(save);

		save.setOnAction(e ->{
			for (int i = 0; i < daily.size(); i++) {
				if(daily.get(i).getAccount().contains("مبيعات") | daily.get(i).getAccount().contains("Revenue")) {
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,daily.get(i).getTotal());
					boolean flag = true,flag2 = true,flag3 = true;
					for (int j = 0; j < Account.getRevenueGenral().size(); j++) {
						if(Account.getRevenueGenral().get(j).equals(general)) {
							flag= false;							
						}
					}
					if(flag) {						
						Account.getRevenueGenral().add(general);
					}
					if(daily.get(i).getCriditWhthVat().contains("النقد") | daily.get(i).getCriditWhthVat().contains("Cash")) {
						GenralLedger generalss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(), daily.get(i).getFinalTotal(),0);
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generalss)) {								
								flag2= false;
							}
						}
						if(flag2) {
							Account.getCashGenral().add(generalss);
						}						
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,((Revenue)daily.get(i)).getLiability());
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generals)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getVatGenral().add(generals);
						}
					}else
						if(daily.get(i).getCriditWhthVat().contains("ذمم مدينة") | daily.get(i).getCriditWhthVat().contains("Account receivable")) {
							GenralLedger generalsss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
									daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal() ,0);
							for (int j = 0; j < Account.getAccountReceivableGenral().size(); j++) {
								if(Account.getAccountReceivableGenral().get(j).equals(generalsss)) {									
									flag2= false;
								}
							}
							if(flag2) {
								Account.getAccountReceivableGenral().add(generalsss);								
							}
							GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
									daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,((Revenue)daily.get(i)).getLiability());
							for (int j = 0; j < Account.getVatGenral().size(); j++) {
								if(Account.getVatGenral().get(j).equals(generalssss)) {									
									flag3= false;
								}
							}
							if(flag3) {
								Account.getVatGenral().add(generalssss);								
							}
						}
				}else if(daily.get(i).getAccount().contains("مشتريات") | daily.get(i).getAccount().contains("Purcahse")) {
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getTotal(),0);
					boolean flag = true,flag2 = true,flag3 = true;
					for (int j = 0; j < Account.getPurchaseGenral().size(); j++) {
						if(Account.getPurchaseGenral().get(j).equals(general)) {
							flag= false;							
						}
					}
					if(flag) {						
						Account.getPurchaseGenral().add(general);
					} 
					if(daily.get(i).getDepiteWhthVat().contains("النقد") | daily.get(i).getDepiteWhthVat().contains("Cash")) {
						GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),((Puchases)daily.get(i)).getPrePaiedTax(),0);
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generalssss)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getVatGenral().add(generalssss);	
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getCashGenral().add(generals);
						}
					}else if(daily.get(i).getDepiteWhthVat().contains("ذمم دائنة") | daily.get(i).getDepiteWhthVat().contains("Account payable")) {
						GenralLedger generalss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getAccountPayableGenral().size(); j++) {
							if(Account.getAccountPayableGenral().get(j).equals(generalss)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getAccountPayableGenral().add(generalss);
						}
						GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),((Puchases)daily.get(i)).getPrePaiedTax(),0);
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generalssss)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getVatGenral().add(generalssss);
						}
					}
				}else if(daily.get(i).getAccount().contains("سند قبض") | daily.get(i).getAccount().contains("Cash Recepts") ) {
					boolean flag = true,flag2 = true;
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
					for (int j = 0; j < Account.getCashGenral().size(); j++) {
						if(Account.getCashGenral().get(j).equals(general)) {							
							flag= false;
						}
					}
					if(flag) {						
						Account.getCashGenral().add(general);
					}
					GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
					for (int j = 0; j < Account.getAccountReceivableGenral().size(); j++) {
						if(Account.getAccountReceivableGenral().get(j).equals(generals)) {							
							flag2= false;
						}
					}
					if(flag2) {						
						Account.getAccountReceivableGenral().add(generals);
					}
				}else if(daily.get(i).getAccount().contains("سند صرف") | daily.get(i).getAccount().contains("Payment Voucher")) {
					boolean flag = true,flag2 = true;
					String [] x = daily.get(i).getDate().split("-");
					if(daily.get(i).getCriditWhthVat().contains("مصروف") | daily.get(i).getCriditWhthVat().contains("Expense")) {
						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
						for (int j = 0; j < Account.getExpenseGenral().size(); j++) {
							if(Account.getExpenseGenral().get(j).equals(general)) {								
								flag= false;
							}
						}
						if(flag) {							
							Account.getExpenseGenral().add(general);
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getCashGenral().add(generals);
						}
					}
					if(daily.get(i).getCriditWhthVat().contains("ذمم دائنة") | daily.get(i).getCriditWhthVat().contains("Cash")) {
						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
						for (int j = 0; j < Account.getExpenseGenral().size(); j++) {
							if(Account.getExpenseGenral().get(j).equals(general)) {								
								flag= false;
							}
						}
						if(flag) {							
							Account.getExpenseGenral().add(general);
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getAccountPayableGenral().size(); j++) {
							if(Account.getAccountPayableGenral().get(j).equals(generals)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getAccountPayableGenral().add(generals);
						}
					}
				}else if(daily.get(i).getAccount().contains("رأس المال مدفوع") | daily.get(i).getAccount().contains("Capital")) {
					boolean flag = true,flag2 = true;;
					String [] x = daily.get(i).getDate().split("-");
					if(daily.get(i).getCriditWhthVat().contains("النقد") | daily.get(i).getCriditWhthVat().contains("Cash")) {
						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getOwnerGenral().size(); j++) {
							if(Account.getOwnerGenral().get(j).equals(general)) {								
								flag= false;
							}
						}
						if(flag) {							
							Account.getOwnerGenral().add(general);
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);

						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getCashGenral().add(generals);
						}
					}
				}
			}
			daily.removeAll(daily);
			table.getItems().clear();
		});

		table.setMinHeight(555);
		VBox v = new VBox(15,table,save);
		v.setAlignment(Pos.CENTER);
		ceBorderPane.setCenter(v);

		ceBorderPane.setAlignment(table, Pos.CENTER);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void paymentVoucherOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("Payment Voucher");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("Date                     ");
		date.setPadding(new Insets(7)); 
		Label marje3 = new Label("Reference            ");
		marje3.setPadding(new Insets(7));
		Label name = new Label("Name                   ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		TextField namet = new TextField();
		TextField marje3t = new TextField();
		Label voucher = new Label("Voucher Number");
		voucher.setPadding(new Insets(7));
		TextField vouchert = new TextField();

		IconedTextFieled(voucher, vouchert);
		IconedTextFieled(date, datet);
		IconedTextFieled(name, namet);
		IconedTextFieled(marje3, marje3t);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(marje3,marje3t);
		h2.setAlignment(Pos.CENTER_LEFT);
		HBox h3 = new HBox(name,namet);
		h3.setAlignment(Pos.CENTER_LEFT);
		HBox h4 = new HBox(voucher,vouchert);
		h4.setAlignment(Pos.CENTER_LEFT);

		RadioButton cash  = new RadioButton(" Cash ");
		cash.setPadding(new Insets(5));
		RadioButton shik  = new RadioButton(" Check ");
		shik.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		cash.setToggleGroup(tg);
		shik.setToggleGroup(tg);
		Label tybePayment = new Label("Receiving way   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(35,cash,shik);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);

		Label sal = new Label("value            ");
		sal.setPadding(new Insets(7));
		TextField salary = new TextField();
		IconedTextFieled(sal,salary);
		HBox sala = new HBox(sal,salary);
		sala.setAlignment(Pos.CENTER_LEFT);

		HBox mex = new HBox(tybePayment,typePay);
		mex.setAlignment(Pos.CENTER_LEFT);

		HBox mex2 = new HBox(30,mex,sala);
		mex2.setAlignment(Pos.CENTER_LEFT);

		VBox v = new VBox(20 , h1,h4,h3,h2);
		v.setAlignment(Pos.CENTER_LEFT);


		Label numOfshik = new Label("Number of check     ");
		numOfshik.setPadding(new Insets(7));
		TextField numofshikt = new TextField();

		IconedTextFieled(numOfshik, numofshikt);
		HBox hnum = new HBox(numOfshik,numofshikt);

		Label DateOfshik = new Label("Date of Check");
		DateOfshik.setPadding(new Insets(7));
		TextField DateOfshikt = new TextField();
		DateOfshikt.setPromptText("Day - Mounth - Year");

		IconedTextFieled(DateOfshik, DateOfshikt);
		HBox hdate= new HBox(DateOfshik, DateOfshikt);

		Label nameOfBank = new Label("Name of bank   ");
		nameOfBank.setPadding(new Insets(7));
		TextField nameOfBankt = new TextField();

		IconedTextFieled(nameOfBank,nameOfBankt);
		HBox hname= new HBox(nameOfBank,nameOfBankt);

		HBox mex3 = new HBox(30,hname,hdate,hnum);
		mex3.setAlignment(Pos.CENTER_LEFT);

		Label nameOfCustomer = new Label("Name Of payee ");
		nameOfCustomer.setPadding(new Insets(7));
		TextField nameOfCustomert = new TextField();
		IconedTextFieled(nameOfCustomer, nameOfCustomert);
		HBox hnameC= new HBox(nameOfCustomer, nameOfCustomert);
		hnameC.setAlignment(Pos.CENTER_LEFT);

		VBox v2 = new VBox(20,mex2,mex3);
		v2.setAlignment(Pos.TOP_CENTER);

		Button save = new Button("Save");
		butoonEffect(save);
		icons(save);

		cash.setOnAction(e ->{
			nameOfBankt.setEditable(false);
			numofshikt.setEditable(false);
			DateOfshikt.setEditable(false);
		});

		shik.setOnAction(e ->{
			nameOfBankt.setEditable(true);
			numofshikt.setEditable(true);
			DateOfshikt.setEditable(true);
		});

		save.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" Cash ") ) {
				PaymentVoucher PaymentVoucher =new PaymentVoucher(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1),Integer.parseInt(x[0])), namet.getText(),
						nameOfCustomert.getText(),Double.parseDouble(salary.getText()), true); 
				PaymentVoucher.setVoucher(vouchert.getText());
				PaymentVoucher.setAccount("Payment Voucher");
				PaymentVoucher.setDepiteWhthVat(" Cash "); 
				PaymentVoucher.setCriditWhthVat("Expense");
				PaymentVoucher.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getPaymentVoucher().add(PaymentVoucher);
				daily.add(PaymentVoucher);
			}
			else if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" Check ") ) {
				String [] z = DateOfshikt.getText().split("-");
				PaymentVoucher PaymentVoucher = new PaymentVoucher(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
						new Date(Integer.parseInt(z[2]),Integer.parseInt(z[1]), Integer.parseInt(z[0])), namet.getText(), nameOfBankt.getText(),
						nameOfCustomert.getText(),Double.parseDouble(salary.getText()),Integer.parseInt(numofshikt.getText()), false);
				PaymentVoucher.setVoucher(vouchert.getText());
				PaymentVoucher.setAccount("Payment Voucher (Check)");
				PaymentVoucher.setDepiteWhthVat(" Cash ");
				PaymentVoucher.setCriditWhthVat("Expense");
				PaymentVoucher.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getPaymentVoucher().add(PaymentVoucher);
				daily.add(PaymentVoucher);
			}
			datet.clear();
			namet.clear();
			nameOfCustomert.clear();
			tg.selectToggle(null);
			salary.clear();
			nameOfBankt.clear();
			DateOfshikt.clear();
			numofshikt.clear();
			vouchert.clear();
			marje3t.clear();
		});

		VBox v1 = new VBox(50,v,v2,hnameC);
		v1.setAlignment(Pos.CENTER_LEFT);
		v1.setPadding(new Insets(30));

		VBox v3 = new VBox(70,v1,save);
		v3.setAlignment(Pos.CENTER);
		save.setAlignment(Pos.CENTER_LEFT);

		centerPane.setCenter(v3);

		VBox buttons = menueEng(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}


	private void cashReceiptsOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("Cash Receipts");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("Date                   ");
		date.setPadding(new Insets(7));
		Label marje3 = new Label("Reference          ");
		marje3.setPadding(new Insets(7));
		Label name = new Label("Name                 ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		datet.setPromptText("Day-Mounth-Year");
		TextField namet = new TextField();

		ObservableList<String> data = FXCollections.observableArrayList();
		for (int i = 0; i < Account.getRevenueList().size(); i++) {
			data.addAll(Account.getRevenueList().get(i).getVoucher());
		}

		ComboBox<String> marje3t = new ComboBox<>();
		marje3t.setPrefSize(195,20);
		marje3t.setStyle("-fx-font-size: 14;");
		marje3t.setPromptText("Choose reference");

		marje3t.getItems().addAll(data);

		Label voucher = new Label("Voucher Number");
		voucher.setPadding(new Insets(7));
		TextField vouchert = new TextField();

		IconedTextFieled(voucher, vouchert);

		IconedTextFieled(date, datet);
		IconedTextFieled(marje3, marje3t);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(marje3,marje3t);
		h2.setAlignment(Pos.CENTER_LEFT);
		HBox h3 = new HBox(name,namet);
		h3.setAlignment(Pos.CENTER_LEFT);
		HBox h4 = new HBox(voucher,vouchert);
		h4.setAlignment(Pos.CENTER_LEFT);


		RadioButton cash  = new RadioButton(" Cash ");
		cash.setPadding(new Insets(5));
		RadioButton shik  = new RadioButton(" Check ");
		shik.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		cash.setToggleGroup(tg);
		shik.setToggleGroup(tg);
		Label tybePayment = new Label("Receiving way   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(50,cash,shik);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);

		Label sal = new Label("value            ");
		sal.setPadding(new Insets(7));
		TextField salary = new TextField();
		IconedTextFieled(sal,salary);
		HBox sala = new HBox(sal,salary);
		sala.setAlignment(Pos.CENTER_LEFT);

		HBox mex = new HBox(tybePayment,typePay);
		mex.setAlignment(Pos.CENTER_LEFT);

		HBox mex2 = new HBox(30,mex,sala);
		mex2.setAlignment(Pos.CENTER_LEFT);

		VBox v = new VBox(20 , h1,h4,h3,h2);
		v.setAlignment(Pos.CENTER_LEFT);


		Label numOfshik = new Label("Number of check     ");
		numOfshik.setPadding(new Insets(7));
		TextField numofshikt = new TextField();

		IconedTextFieled(numOfshik, numofshikt);
		HBox hnum = new HBox(numOfshik,numofshikt);

		Label DateOfshik = new Label("Date of Check");
		DateOfshik.setPadding(new Insets(7));
		TextField DateOfshikt = new TextField();
		DateOfshikt.setPromptText("Day-Mounth-Year");

		IconedTextFieled(DateOfshik, DateOfshikt);
		HBox hdate= new HBox(DateOfshik, DateOfshikt);

		Label nameOfBank = new Label("Name of bank   ");
		nameOfBank.setPadding(new Insets(7));
		TextField nameOfBankt = new TextField();

		IconedTextFieled(nameOfBank,nameOfBankt);
		HBox hname= new HBox(nameOfBank,nameOfBankt);

		HBox mex3 = new HBox(30,hname,hdate,hnum);
		mex3.setAlignment(Pos.CENTER_LEFT);

		Label nameOfCustomer = new Label("Received from ");
		nameOfCustomer.setPadding(new Insets(7));
		TextField nameOfCustomert = new TextField();
		IconedTextFieled(nameOfCustomer, nameOfCustomert);
		HBox hnameC= new HBox(nameOfCustomer, nameOfCustomert);
		hnameC.setAlignment(Pos.CENTER_LEFT);

		VBox v2 = new VBox(20,mex2,mex3);
		v2.setAlignment(Pos.TOP_CENTER);

		Button save = new Button("Save");
		butoonEffect(save);
		icons(save);

		cash.setOnAction(e ->{
			nameOfBankt.setEditable(false);
			numofshikt.setEditable(false);
			DateOfshikt.setEditable(false);
		});

		shik.setOnAction(e ->{
			nameOfBankt.setEditable(true);
			numofshikt.setEditable(true);
			DateOfshikt.setEditable(true);
		});

		save.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" Cash ") ) {
				CashRecepts cashRecepts= new CashRecepts(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1),Integer.parseInt(x[0])), namet.getText(),
						nameOfCustomert.getText(), marje3t.getSelectionModel().getSelectedItem().toString() ,Double.parseDouble(salary.getText()), true);
				cashRecepts.setVoucher(vouchert.getText());
				cashRecepts.setAccount("Cash Recepts");
				cashRecepts.setDepiteWhthVat("Account Receivable");
				cashRecepts.setCriditWhthVat(" Cash ");
				cashRecepts.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getCashReceptsList().add(cashRecepts);
				daily.add(cashRecepts);


			}
			else if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" Check ") ) {
				String [] z = DateOfshikt.getText().split("-");
				CashRecepts cashRecepts = new CashRecepts(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
						new Date(Integer.parseInt(z[2]),Integer.parseInt(z[1]), Integer.parseInt(z[0])), namet.getText(), nameOfBankt.getText(),
						nameOfCustomert.getText(), marje3t.getSelectionModel().getSelectedItem().toString() 
						,Double.parseDouble(salary.getText()),Integer.parseInt(numofshikt.getText()), false);
				cashRecepts.setVoucher(vouchert.getText());
				cashRecepts.setAccount("Cash Recepts (Check)");
				cashRecepts.setDepiteWhthVat("Account Receivable");
				cashRecepts.setCriditWhthVat(" Cash ");
				cashRecepts.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getCashReceptsList().add(cashRecepts);
				daily.add(cashRecepts);

			}
			datet.clear();
			namet.clear();
			nameOfCustomert.clear();
			tg.selectToggle(null);
			salary.clear();
			nameOfBankt.clear();
			DateOfshikt.clear();
			numofshikt.clear();
			vouchert.clear();
		});

		VBox v1 = new VBox(50,v,v2,hnameC);
		v1.setAlignment(Pos.CENTER_LEFT);
		v1.setPadding(new Insets(30));

		VBox v3 = new VBox(70,v1,save);
		v3.setAlignment(Pos.CENTER);
		save.setAlignment(Pos.CENTER_LEFT);

		centerPane.setCenter(v3);

		VBox buttons = menueEng(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void purchasesOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("Purchases");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("Date      ");
		date.setPadding(new Insets(7));
		Label vaucher = new Label("Vaucher");
		vaucher.setPadding(new Insets(7));
		Label name = new Label("Name    ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		datet.setPromptText("Day-Mounth-Year");
		TextField vauchert = new TextField();
		TextField namet = new TextField();

		IconedTextFieled(date, datet);
		IconedTextFieled(vaucher, vauchert);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(vaucher,vauchert);
		h2.setAlignment(Pos.CENTER_LEFT);
		HBox h3 = new HBox(name,namet);
		h3.setAlignment(Pos.CENTER_LEFT);
		VBox v = new VBox(10 , h1,h2,h3);
		v.setAlignment(Pos.CENTER_LEFT);

		TableView<Puchases> table = new TableView<Puchases>();


		TableColumn <Puchases, String>item = new TableColumn<Puchases, String>("Item ");
		item.setMinWidth(200);
		item.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getItem()));
		item.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Double>price = new TableColumn<Puchases, Double>("Price");
		price.setMinWidth(150);
		price.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
		price.setStyle("-fx-alignment: CENTER;");

		TableColumn<Puchases, Long>quantity = new TableColumn<Puchases, Long>("Quantity");
		quantity.setMinWidth(200);
		quantity.setCellValueFactory(p -> new SimpleLongProperty(p.getValue().getQuantity()).asObject());
		quantity.setStyle("-fx-alignment: CENTER;");

		TableColumn<Puchases, Double> total = new TableColumn<Puchases, Double>("Total ");
		total.setMinWidth(150);
		total.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getTotal()).asObject());
		table.setStyle("-fx-alignment: CENTER;");

		TableColumn<Puchases, Double> Vat = new TableColumn<Puchases, Double>("Vat % ");
		Vat.setMinWidth(198);
		Vat.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getVat()).asObject());
		Vat.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Double>finalTotals = new TableColumn<Puchases, Double>("Final Total ");
		finalTotals.setMinWidth(200);
		finalTotals.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		finalTotals.setStyle("-fx-alignment: CENTER;");

		ObservableList<Puchases> data = FXCollections.observableArrayList(purchasesData);

		table.getColumns().addAll(item, price, quantity,total,Vat,finalTotals);
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");


		TextField items = new TextField();
		icons(items);
		items.setEffect(new DropShadow());
		items.setPromptText("Enter item");
		TextField prices = new TextField();
		icons(prices);
		prices.setEffect(new DropShadow());
		prices.setPromptText("Enter price");
		TextField quantities = new TextField();
		icons(quantities);
		quantities.setEffect(new DropShadow());
		quantities.setPromptText("Enter quantity");

		ComboBox<String> typeOfPay = new ComboBox<>();
		typeOfPay.setPrefSize(250,20);
		typeOfPay.setStyle("-fx-font-size: 14;");
		typeOfPay.setPromptText("Choose the payment way");
		typeOfPay.getItems().addAll("Cash","Account payable");
		icons(typeOfPay);


		Label finalTotal = new Label("Net Purchases");
		finalTotal.setPadding(new Insets(7));
		TextField finalTotalt = new TextField();
		IconedTextFieled(finalTotal, finalTotalt);

		HBox h4 = new HBox(finalTotal, finalTotalt);
		h4.setAlignment(Pos.CENTER_RIGHT);
		Button tr7eel = new Button("Save");
		tr7eel.setOnAction(e ->{
			for (int i = 0; i < Account.getPuchasesList().size(); i++) {
				Boolean flag = true , flag2 = true;
				if(Account.getPuchasesList().get(i).getType().equals("Account payable")) {
					for (int j = 0; j < JournalVoucherdata.size(); j++) {
						if(Account.getPuchasesList().get(i).equals(JournalVoucherdata.get(j))) {
							flag = false;
						}
					}
					if(flag)
						JournalVoucherdata.add(Account.getPuchasesList().get(i));
				}
				else if(Account.getPuchasesList().get(i).getType().equals("Cash")) {
					for (int j = 0; j < daily.size(); j++) {
						if(Account.getPuchasesList().get(i).equals(daily.get(j))) {
							flag2 = false;
						}
					}
					if(flag2)
						daily.add(Account.getPuchasesList().get(i));
				}
			}
			writeFiles();
		});
		butoonEffect(tr7eel);
		icons(tr7eel);
		tr7eel.setEffect(new DropShadow());

		VBox v3 = new VBox(10 , h4,tr7eel);
		v3.setAlignment(Pos.CENTER_RIGHT);

		Button add = new Button("Add");
		butoonEffect(add);
		icons(add);

		if(Account.getPuchasesList().size() > 0 ) {
			finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
		}
		table.getItems().clear();
		for (int i = 0; i < Account.getPuchasesList().size(); i++) {
			table.getItems().addAll(Account.getPuchasesList().get(i));
		}

		typeOfPay.setOnAction(r ->{
			if(typeOfPay.getValue().equals("Cash")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Puchases puchases =  new Puchases(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					puchases.ManagePurcahse();
					purchasesData.add(puchases);
					Account.getPuchasesList().add(puchases);
					table.getItems().add(puchases);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
				});
			}
			else if(typeOfPay.getValue().equals("Account payable")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Puchases puchases =  new Puchases(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					puchases.ManagePurcahse();
					purchasesData.add(puchases);
					Account.getPuchasesList().add(puchases);
					table.getItems().add(puchases);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
				});
			}
		});

		add.setEffect(new DropShadow());

		Button delete = new Button("Delete");
		butoonEffect(delete);
		icons(delete);
		delete.setEffect(new DropShadow());

		delete.setOnAction(e ->{
			ObservableList<Puchases> allPuchases , selectedPuchases;
			allPuchases = table.getItems();
			selectedPuchases= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getPuchasesList().size(); i++) {
				if(Account.getPuchasesList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getPuchasesList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					JournalVoucherdata.remove(i);
				}
			}
			for (int i = 0; i < purchasesData.size(); i++) {
				if(purchasesData.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					purchasesData.remove(i);
				}
			}
			selectedPuchases.forEach(allPuchases::remove);
			finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");

		});

		HBox addDelete = new HBox(10,items,prices,quantities,typeOfPay,add,delete);
		addDelete.setAlignment(Pos.CENTER);

		VBox v2 = new VBox(20 , v, table,addDelete,v3);



		centerPane.setCenter(v2);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);

		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void revenueOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("Revenue");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("Date      ");
		date.setPadding(new Insets(7));
		Label vaucher = new Label("Vaucher");
		vaucher.setPadding(new Insets(7));
		Label name = new Label("Name    ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		datet.setPromptText("Day-Mounth-Year");
		TextField vauchert = new TextField();
		TextField namet = new TextField();

		IconedTextFieled(date, datet);
		IconedTextFieled(vaucher, vauchert);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(vaucher,vauchert);
		h2.setAlignment(Pos.CENTER_LEFT);
		HBox h3 = new HBox(name,namet);
		h3.setAlignment(Pos.CENTER_LEFT);
		VBox v = new VBox(10 , h1,h2,h3);
		v.setAlignment(Pos.CENTER_LEFT);

		TableView<Revenue> table = new TableView<Revenue>();

		TableColumn <Revenue, String>item = new TableColumn <Revenue, String>("Item ");
		item.setMinWidth(200);
		item.setCellValueFactory(  p -> new SimpleStringProperty(p.getValue().getItem()));
		item.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>price = new TableColumn <Revenue, Double>("Price");
		price.setMinWidth(150);
		price.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
		price.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Long>quantity = new TableColumn<Revenue, Long>("Quantity");
		quantity.setMinWidth(200);
		quantity.setCellValueFactory( p -> new SimpleLongProperty(p.getValue().getQuantity()).asObject());
		quantity.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>total = new TableColumn<Revenue, Double>("Total ");
		total.setMinWidth(150);
		total.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getTotal()).asObject());
		total.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>Vat = new TableColumn<Revenue, Double>("Vat % ");
		Vat.setMinWidth(198);
		Vat.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getVat()).asObject());
		Vat.setStyle("-fx-alignment: CENTER;");


		TableColumn <Revenue, Double>finalTotals = new TableColumn<Revenue, Double>("Final Total ");
		finalTotals.setMinWidth(200);
		finalTotals.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		finalTotals.setStyle("-fx-alignment: CENTER;");

		ObservableList<Revenue> data = FXCollections.observableArrayList(revenueData);

		table.getColumns().addAll(item, price, quantity,total,Vat,finalTotals);
		table.setItems(data);
		table.setEditable(true);

		TextField items = new TextField();
		icons(items);
		items.setEffect(new DropShadow());
		items.setPromptText("Enter item");
		TextField prices = new TextField();
		icons(prices);
		prices.setEffect(new DropShadow());
		prices.setPromptText("Enter price");
		TextField quantities = new TextField();
		icons(quantities);
		quantities.setEffect(new DropShadow());
		quantities.setPromptText("Enter quantity");

		ComboBox<String> typeOfPay = new ComboBox<>();
		typeOfPay.setPrefSize(250,20);
		typeOfPay.setStyle("-fx-font-size: 14;");
		typeOfPay.setPromptText("Choose the receiving way");
		typeOfPay.getItems().addAll("Cash","Account receivable");

		icons(typeOfPay);

		Label finalTotal = new Label("Net revenue");
		finalTotal.setPadding(new Insets(7));
		TextField finalTotalt = new TextField();

		IconedTextFieled(finalTotal, finalTotalt);

		HBox h4 = new HBox(finalTotal, finalTotalt);
		h4.setAlignment(Pos.CENTER_RIGHT);
		Button tr7eel = new Button("Save");
		butoonEffect(tr7eel);
		icons(tr7eel);
		tr7eel.setEffect(new DropShadow());
		tr7eel.setOnAction(e ->{
			for (int i = 0; i < Account.getRevenueList().size(); i++) {
				Boolean flag = true,flag2 = true ;
				if(Account.getRevenueList().get(i).getType().equals("Account receivable")) {
					for (int j = 0; j < JournalVoucherdata.size(); j++) {
						if(Account.getRevenueList().get(i).equals(JournalVoucherdata.get(j))) {
							flag2 = false;
						}						
					}
					if(flag2)
						JournalVoucherdata.add(Account.getRevenueList().get(i));
				}
				else if(Account.getRevenueList().get(i).getType().equals("Cash")) {
					for (int j = 0; j < daily.size(); j++) {
						if(Account.getRevenueList().get(i).equals(daily.get(j))) {
							flag = false;
						}
					}
					if(flag)
						daily.add(Account.getRevenueList().get(i));
				}
			}
			writeFiles();
		});

		VBox v3 = new VBox(10 , h4,tr7eel);
		v3.setAlignment(Pos.CENTER_RIGHT);

		Button add = new Button("Add");
		butoonEffect(add);
		icons(add);

		if(Account.getRevenueList().size() >= 0 ) {
			finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
		}
		table.getItems().clear();
		for (int i = 0; i < Account.getRevenueList().size(); i++) {
			table.getItems().addAll(Account.getRevenueList().get(i));
		}

		typeOfPay.setOnAction(r ->{
			if(typeOfPay.getValue().equals("Cash")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Revenue revenue = new Revenue(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					revenue.ManageRevenue();
					revenueData.add(revenue);
					Account.getRevenueList().add(revenue);	
					table.getItems().addAll(revenue);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");

				});
			}
			else if(typeOfPay.getValue().equals("Account receivable")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Revenue revenue = new Revenue(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					revenue.ManageRevenue();
					revenueData.add(revenue);
					Account.getRevenueList().add(revenue);
					table.getItems().addAll(revenue);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
				});
			}
		});

		add.setEffect(new DropShadow());

		Button delete = new Button("Delete");
		butoonEffect(delete);
		icons(delete);
		delete.setEffect(new DropShadow());

		delete.setOnAction(e ->{
			ObservableList<Revenue> allPuchases , selectedPuchases;
			allPuchases = table.getItems();
			selectedPuchases= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getRevenueList().size(); i++) {
				if(Account.getRevenueList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getRevenueList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					JournalVoucherdata.remove(i);
				}
			}
			for (int i = 0; i < revenueData.size(); i++) {
				if(revenueData.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					revenueData.remove(i);
				}
			}
			selectedPuchases.forEach(allPuchases::remove);
			finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
		});

		HBox addDelete = new HBox(10,items,prices,quantities,typeOfPay,add,delete);
		addDelete.setAlignment(Pos.CENTER);


		VBox v2 = new VBox(20 , v, table,addDelete,v3);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		centerPane.setCenter(v2);

		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);

		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private VBox menueEng(Stage primaryStage) {
		Button revenue = new Button("Revenue");
		revenue.setFont(new Font("Times New Roman",13));
		revenue.setMinWidth(170);
		butoonEffect(revenue);

		icons(revenue);
		revenue.setEffect(new DropShadow());
		revenue.setOnAction(e ->{
			revenueOnActionEng(primaryStage);			
		});

		Button purchases = new Button("Purchases");
		purchases.setFont(new Font("Times New Roman",13));
		icons(purchases);
		purchases.setEffect(new DropShadow());
		purchases.setMinWidth(170);
		butoonEffect(purchases);

		purchases.setOnAction(e ->{
			purchasesOnActionEng(primaryStage);
		});

		Button  cashReceipts = new Button("Cash receipts");
		cashReceipts.setFont(new Font("Times New Roman",13));
		icons(cashReceipts);
		cashReceipts.setEffect(new DropShadow());
		cashReceipts.setMinWidth(170);
		butoonEffect(cashReceipts);

		cashReceipts.setOnAction(e ->{
			cashReceiptsOnActionEng(primaryStage);
		});

		Button  paymentVoucher = new Button("Payment voucher");
		paymentVoucher.setFont(new Font("Times New Roman",13));
		icons(paymentVoucher);
		paymentVoucher.setEffect(new DropShadow());
		paymentVoucher.setMinWidth(170);
		butoonEffect(paymentVoucher);

		paymentVoucher.setOnAction(e ->{
			paymentVoucherOnActionEng(primaryStage);
		});

		Button  journalVoucher = new Button("Journal voucher");
		journalVoucher.setFont(new Font("Times New Roman",13));
		icons(journalVoucher);
		journalVoucher.setEffect(new DropShadow());
		journalVoucher.setMinWidth(170);
		butoonEffect(journalVoucher);

		journalVoucher.setOnAction(e ->{
			journalVoucherrOnActionEng(primaryStage);
		});


		Button  generaljournal = new Button("General Journal");
		generaljournal.setFont(new Font("Times New Roman",13));
		icons(generaljournal);
		generaljournal.setEffect(new DropShadow());
		generaljournal.setMinWidth(170);
		butoonEffect(generaljournal);

		generaljournal.setOnAction(e ->{
			generaljournalOnActionEng(primaryStage);
		});

		Button generalLedger = new Button("General ledger");
		generalLedger.setFont(new Font("Times New Roman",13));
		icons(generalLedger);
		generalLedger.setEffect(new DropShadow());
		generalLedger.setMinWidth(170);
		butoonEffect(generalLedger);

		generalLedger.setOnAction(e ->{
			generalLedgerOnActionEng(primaryStage);
		});

		Button financialStatement = new Button("Financial statements");
		financialStatement.setFont(new Font("Times New Roman",13));
		icons(financialStatement);
		financialStatement.setEffect(new DropShadow());
		financialStatement.setMinWidth(170);
		butoonEffect(financialStatement);

		financialStatement.setOnAction(e ->{
			financialStatementOnActionEng(primaryStage);
		});

		Button financialAnalysis = new Button("Financial analysis");
		financialAnalysis.setFont(new Font("Times New Roman",13));
		icons(financialAnalysis);
		financialAnalysis.setEffect(new DropShadow());
		financialAnalysis.setMinWidth(170);
		butoonEffect(financialAnalysis);

		financialAnalysis.setOnAction(e ->{
			financialAnalysisOnActionEng(primaryStage);
		});

		Button back = new Button("Log Out");
		icons(back);
		back.setEffect(new DropShadow());
		back.setMinWidth(170);
		butoonEffect(back);

		back.setOnAction(e -> {
			primaryStage.setScene(welcomeSceneEng);
			primaryStage.show();
		});

		Label langt = new Label("Choose language | إختر اللغة");
		langt.setStyle("-fx-font-size: 14;\n" +
				"-fx-font-weight: Bold;");
		langt.setStyle("-fx-font-weight: Bold;");
		langt.setStyle("-fx-text-fill: #f6f6f6");
		langt.setEffect(new DropShadow());
		langt.setFont(new Font(13));

		Button arabic = new Button("Arabic");
		arabic.setMinWidth(80);
		arabic.setOnAction(e ->{
			ArabicAction(primaryStage);
		});

		Button english = new Button("English");
		english.setMinWidth(80);
		english.setOnAction(e ->{
			EnglishAction(primaryStage);
		});

		icons(arabic);
		arabic.setEffect(new DropShadow());
		icons(english);
		english.setEffect(new DropShadow());
		butoonEffect(arabic);
		butoonEffect(english);

		Label l = new Label("|");
		l.setStyle("-fx-text-fill: #f6f6f6");
		l.setFont(new Font(20));

		HBox h3 = new HBox(5,english,l,arabic);
		VBox v2 = new VBox(2,langt,h3);
		h3 .setAlignment(Pos.CENTER);
		v2.setAlignment(Pos.CENTER);

		VBox buttons = new VBox(30,revenue,purchases,cashReceipts,paymentVoucher,journalVoucher,generaljournal,generalLedger,financialStatement,financialAnalysis,back,v2);
		buttons.setAlignment(Pos.CENTER);
		buttons.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-background-color: transparent;");
		return buttons;
	}


	private void butoonEffect(Button b) {
		b.setOnMouseMoved(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 15;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					" -fx-text-fill: #CE2029;\n"+
					"-fx-background-color: #d8d9e0;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 15;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					" -fx-text-fill: #f2f3f4;\n"+
					"-fx-background-color: transparent;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});

	}


	private void journalVoucherrOnActionEng(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("Journal Voucher");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);
		TableView<AccountManeger> table = new TableView<AccountManeger>();

		TableColumn <AccountManeger, String> date = new TableColumn<AccountManeger, String>("Date ");
		date.setMinWidth(128);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <AccountManeger, String>num = new TableColumn<AccountManeger, String>("Voucher's \n Number");
		num.setMinWidth(90);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String>account = new TableColumn<AccountManeger, String>("Account ");
		account.setMinWidth(140);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> depitPrice = new TableColumn<AccountManeger, Double>("Debit ");
		depitPrice.setMinWidth(140);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> creditPrice = new TableColumn<AccountManeger, Double>("Credit ");
		creditPrice.setMinWidth(140);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");


		TableColumn<AccountManeger, String> depitPriceJournal = new TableColumn<AccountManeger, String>("Debit ");
		depitPriceJournal.setMinWidth(225);
		depitPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCriditWhthVat()));
		depitPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> creditPriceJournal = new TableColumn<AccountManeger, String>("Credit ");
		creditPriceJournal.setMinWidth(225);
		creditPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDepiteWhthVat()));
		creditPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> journal = new TableColumn<AccountManeger, String>("Journal");
		journal.setMinWidth(450);
		TableColumn<AccountManeger, Double> price = new TableColumn<AccountManeger, Double>("Value");
		price.setMinWidth(280);
		journal.getColumns().addAll(creditPriceJournal,depitPriceJournal);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<AccountManeger> data = FXCollections.observableArrayList(JournalVoucherdata);

		table.getColumns().addAll(date,num,account,journal,price);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");


		Label tataldebit = new Label("              Total Debit               ");
		tataldebit.setPadding(new Insets(7));
		TextField tataldebitt = new TextField();
		tataldebitt.setMinWidth(180);

		tataldebit.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		tataldebitt.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hdepit = new VBox(tataldebit,tataldebitt);
		hdepit.setAlignment(Pos.CENTER_RIGHT);

		Label totalCredit = new Label("               Total Credit            ");
		totalCredit.setPadding(new Insets(7));
		TextField totalCreditt = new TextField();
		totalCreditt.setMinWidth(180);

		totalCredit.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		totalCreditt.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hcredit = new VBox(totalCredit, totalCreditt);
		table.setMinHeight(350);
		hcredit.setAlignment(Pos.CENTER);
		Label difference = new Label("             Difference            ");
		difference.setPadding(new Insets(7)); 
		TextField differencet = new TextField();
		differencet.setMaxWidth(181.5);

		difference.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		differencet.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hdifference= new VBox(difference,differencet);
		hdifference.setAlignment(Pos.CENTER);
		HBox mix = new HBox(15,hcredit,hdepit,hdifference);
		mix.setAlignment(Pos.CENTER);
		Button save = new Button("Save");
		butoonEffect(save);
		icons(save);
		save.setEffect(new DropShadow());	

		save.setOnAction(e ->{
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				boolean flag = true;
				for (int k = 0; k < daily.size(); k++) {
					if(JournalVoucherdata.get(i).equals(daily.get(k))) {
						flag = false;
					}
				}
				if(flag)
					daily.add(JournalVoucherdata.get(i));
			}
		});

		Button add = new Button("Add");
		icons(add);
		butoonEffect(add);
		add.setEffect(new DropShadow());
		Button delete = new Button("Delete");
		icons(delete);
		butoonEffect(delete);
		delete.setEffect(new DropShadow());
		TextField datet = new TextField();
		icons(datet );
		datet.setEffect(new DropShadow());
		datet.setPromptText("Add date -> day-mounth-year");
		datet.setPrefSize(240, 20);
		TextField number= new TextField();
		icons(number);
		number.setEffect(new DropShadow());
		number.setPromptText("Add voucher number");
		number.setPrefSize(200, 20);
		TextField accountt = new TextField();
		icons(accountt);
		accountt.setEffect(new DropShadow());
		accountt.setPromptText("Add Account");
		accountt.setPrefSize(160, 20);
		TextField value = new TextField();
		icons(value);
		value.setEffect(new DropShadow());
		value.setPromptText("Add Value");
		value.setPrefSize(250, 20);

		VBox mix2 = new VBox(15,value);
		mix.setAlignment(Pos.CENTER);

		HBox addDelete = new HBox(10,datet,number,accountt,mix2,delete,add);
		addDelete.setAlignment(Pos.CENTER);

		add.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			String item="النقد";
			JournalVoucher journalVoucher =  new JournalVoucher(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , number.getText(),Double.parseDouble(value.getText()),accountt.getText(),item);
			journalVoucher.setCriditWhthVat("Cash");
			journalVoucher.setDepiteWhthVat("Capital");
			journalVoucher.setAccountJournal("Capital");
			Account.getJournalVoucherList().add(journalVoucher);
			JournalVoucherdata.add(journalVoucher);
			table.getItems().add(journalVoucher);
			datet.clear();
			number.clear();
			value.clear();
			accountt.clear();
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
		});

		if(JournalVoucherdata.size() >= 0 ) {
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
		}

		delete.setOnAction(e ->{
			ObservableList<AccountManeger> allJournal , selectedJournal;
			allJournal = table.getItems();
			selectedJournal= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getJournalVoucherList().size(); i++) {
				if(Account.getJournalVoucherList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getJournalVoucherList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {
					JournalVoucherdata.remove(i);
				}
			}
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
			selectedJournal.forEach(allJournal::remove);
		});

		VBox v1 = new VBox(20,table,mix,addDelete,save);
		v1.setAlignment(Pos.CENTER);

		ceBorderPane.setCenter(v1);
		ceBorderPane.setAlignment(v1, Pos.CENTER);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);


		VBox buttons = menueEng(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}


	private void ArabicAction(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private VBox menueArb(Stage primaryStage) {
		Button revenue = new Button("المبيعات");
		revenue.setFont(new Font("Times New Roman",13));
		revenue.setMinWidth(170);
		icons(revenue);
		revenue.setEffect(new DropShadow());
		butoonEffect(revenue);

		revenue.setOnAction(e ->{
			revenueOnActionArb(primaryStage);			
		});

		Button purchases = new Button("المشتريات");
		purchases.setFont(new Font("Times New Roman",13));
		icons(purchases);
		purchases.setEffect(new DropShadow());
		purchases.setMinWidth(170);
		butoonEffect(purchases);

		purchases.setOnAction(e ->{
			purchasesOnActionArb(primaryStage);
		});

		Button  cashReceipts = new Button("سند القبض");
		cashReceipts.setFont(new Font("Times New Roman",13));
		icons(cashReceipts);
		cashReceipts.setEffect(new DropShadow());
		cashReceipts.setMinWidth(170);
		butoonEffect(cashReceipts);

		cashReceipts.setOnAction(e ->{
			cashReceiptsOnActionArb(primaryStage);
		});

		Button  paymentVoucher = new Button("سند الصرف");
		paymentVoucher.setFont(new Font("Times New Roman",13));
		icons(paymentVoucher);
		paymentVoucher.setEffect(new DropShadow());
		paymentVoucher.setMinWidth(170);
		butoonEffect(paymentVoucher);

		paymentVoucher.setOnAction(e ->{
			paymentVoucherOnActionArb(primaryStage);
		});


		Button  journalVoucher = new Button("سند القيد");
		journalVoucher.setFont(new Font("Times New Roman",13));
		icons(journalVoucher);
		journalVoucher.setEffect(new DropShadow());
		journalVoucher.setMinWidth(170);
		butoonEffect(journalVoucher);

		journalVoucher.setOnAction(e ->{
			journalVoucherrOnActionArb(primaryStage);
		});

		Button  generaljournal = new Button("دفتر اليومية");
		generaljournal.setFont(new Font("Times New Roman",13));
		icons(generaljournal);
		generaljournal.setEffect(new DropShadow());
		generaljournal.setMinWidth(170);
		butoonEffect(generaljournal);

		generaljournal.setOnAction(e ->{
			generaljournalOnActionArb(primaryStage);
		});

		Button generalLedger = new Button("دفتر الأستاذ العام");
		generalLedger.setFont(new Font("Times New Roman",13));
		icons(generalLedger);
		generalLedger.setEffect(new DropShadow());
		generalLedger.setMinWidth(170);
		butoonEffect(generalLedger);

		generalLedger.setOnAction(e ->{
			generalLedgerOnActionArb(primaryStage);
		});

		Button financialStatement = new Button("القوائم المالية");
		financialStatement.setFont(new Font("Times New Roman",13));
		icons(financialStatement);
		financialStatement.setEffect(new DropShadow());
		financialStatement.setMinWidth(170);
		butoonEffect(financialStatement);

		financialStatement.setOnAction(e ->{
			financialStatementOnActionArb(primaryStage);
		});

		Button financialAnalysis = new Button("التحليل المالي");
		financialAnalysis.setFont(new Font("Times New Roman",13));
		icons(financialAnalysis);
		financialAnalysis.setEffect(new DropShadow());
		financialAnalysis.setMinWidth(170);
		butoonEffect(financialAnalysis);

		financialAnalysis.setOnAction(e ->{
			financialAnalysisOnActionArb(primaryStage);
		});

		Button back = new Button("تسجيل الخروج");
		icons(back);
		back.setEffect(new DropShadow());
		back.setMinWidth(170);
		butoonEffect(back);

		back.setOnAction(e -> {
			primaryStage.setScene(welcomeSceneArab);
			primaryStage.show();
		});

		Label langt = new Label("Choose language | إختر اللغة");
		langt.setStyle("-fx-font-size: 12;\n" +
				"-fx-font-weight: Bold;");
		langt.setStyle("-fx-text-fill: #f6f6f6");
		langt.setEffect(new DropShadow());
		langt.setFont(new Font(13));

		Button arabic = new Button("Arabic");
		arabic.setMinWidth(80);
		arabic.setOnAction(e ->{
			ArabicAction(primaryStage);
		});

		Button english = new Button("English");
		english.setMinWidth(80);
		english.setOnAction(e ->{
			EnglishAction(primaryStage);
		});
		
		icons(arabic);
		arabic.setEffect(new DropShadow());
		icons(english);
		english.setEffect(new DropShadow());
		butoonEffect(arabic);
		butoonEffect(english);

		Label l = new Label("|");
		l.setStyle("-fx-text-fill: #f6f6f6");
		l.setFont(new Font(20));

		HBox h3 = new HBox(5,english,l,arabic);
		VBox v2 = new VBox(2,langt,h3);
		h3 .setAlignment(Pos.CENTER);
		v2.setAlignment(Pos.CENTER);

		VBox buttons = new VBox(30,revenue,purchases,cashReceipts,paymentVoucher,journalVoucher,generaljournal,generalLedger,financialStatement,financialAnalysis,back,v2);
		buttons.setAlignment(Pos.CENTER);
		buttons.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-background-color: transparent;");
		return buttons;
	}


	private void journalVoucherrOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("سند القيد");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);

		TableView<AccountManeger> table = new TableView<AccountManeger>();

		TableColumn <AccountManeger, String> date = new TableColumn<AccountManeger, String>("التاريخ ");
		date.setMinWidth(128);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <AccountManeger, String>num = new TableColumn<AccountManeger, String>("رقم السند");
		num.setMinWidth(80);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String>account = new TableColumn<AccountManeger, String>("الحساب ");
		account.setMinWidth(150);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> depitPrice = new TableColumn<AccountManeger, Double>("المبلغ المدين ");
		depitPrice.setMinWidth(140);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> creditPrice = new TableColumn<AccountManeger, Double>("المبلغ الدائن ");
		creditPrice.setMinWidth(140);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");


		TableColumn<AccountManeger, String> depitPriceJournal = new TableColumn<AccountManeger, String>("المبلغ المدين ");
		depitPriceJournal.setMinWidth(225);
		depitPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCriditWhthVat()));
		depitPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> creditPriceJournal = new TableColumn<AccountManeger, String>("المبلغ الدائن ");
		creditPriceJournal.setMinWidth(225);
		creditPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDepiteWhthVat()));
		creditPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> journal = new TableColumn<AccountManeger, String>("القيد");
		journal.setMinWidth(450);
		TableColumn<AccountManeger, Double> price = new TableColumn<AccountManeger, Double>("المبلغ");
		price.setMinWidth(280);
		journal.getColumns().addAll(creditPriceJournal,depitPriceJournal);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<AccountManeger> data = FXCollections.observableArrayList(JournalVoucherdata);

		table.getColumns().addAll(price,journal,account,num,date);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label tataldebit = new Label("              مجموع المدين            ");
		tataldebit.setPadding(new Insets(7));
		TextField tataldebitt = new TextField();
		tataldebitt.setMinWidth(180);

		tataldebit.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		tataldebitt.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hdepit = new VBox(tataldebit,tataldebitt);
		hdepit.setAlignment(Pos.CENTER_RIGHT);

		Label totalCredit = new Label("               مجموع الدائن            ");
		totalCredit.setPadding(new Insets(7));
		TextField totalCreditt = new TextField();
		totalCreditt.setMinWidth(180);

		totalCredit.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		totalCreditt.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hcredit = new VBox(totalCredit, totalCreditt);
		hcredit.setAlignment(Pos.CENTER);
		Label difference = new Label("                  الفرق                 ");
		difference.setPadding(new Insets(7));
		TextField differencet = new TextField();
		differencet.setMaxWidth(182.6);

		difference.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		differencet.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 20 20");

		VBox hdifference= new VBox(difference,differencet);
		hdifference.setAlignment(Pos.CENTER);
		HBox mix = new HBox(15,hdifference,hdepit,hcredit);
		mix.setAlignment(Pos.CENTER);
		table.setMinHeight(350);
		Button save = new Button("احفظ");
		butoonEffect(save);
		icons(save);
		save.setEffect(new DropShadow());	

		save.setOnAction(e ->{
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				boolean flag = true;
				for (int k = 0; k < daily.size(); k++) {
					if(JournalVoucherdata.get(i).equals(daily.get(k))) {
						flag = false;
					}
				}
				if(flag)
					daily.add(JournalVoucherdata.get(i));
			}
		});

		Button add = new Button("أضف");
		icons(add);
		butoonEffect(add);
		add.setEffect(new DropShadow());
		Button delete = new Button("إحذف");
		icons(delete);
		butoonEffect(delete);
		delete.setEffect(new DropShadow());
		TextField datet = new TextField();
		icons(datet );
		datet.setEffect(new DropShadow());
		datet.setPromptText("أضف التاريخ -> يوم-شهر-سنة");
		datet.setPrefSize(220, 20);
		TextField number= new TextField();
		icons(number);
		number.setEffect(new DropShadow());
		number.setPromptText("أضف الرقم");
		number.setPrefSize(100, 20);
		TextField accountt = new TextField();
		icons(accountt);
		accountt.setEffect(new DropShadow());
		accountt.setPromptText("أضف الحساب");
		accountt.setPrefSize(160, 20);
		TextField value = new TextField();
		icons(value);
		value.setEffect(new DropShadow());
		value.setPromptText("أضف المبلغ");
		value.setPrefSize(250, 20);


		VBox mix2 = new VBox(15,value);
		mix.setAlignment(Pos.CENTER);

		HBox addDelete = new HBox(10,delete,add,mix2,accountt,number,datet);
		addDelete.setAlignment(Pos.CENTER);

		add.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			String item="النقد";
			JournalVoucher journalVoucher =  new JournalVoucher(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , number.getText(),Double.parseDouble(value.getText()),accountt.getText(),item);
			journalVoucher.setCriditWhthVat("النقد لدى البنك");
			journalVoucher.setDepiteWhthVat("رأس المال");
			journalVoucher.setAccountJournal("رأس المال مدفوع");
			Account.getJournalVoucherList().add(journalVoucher);
			JournalVoucherdata.add(journalVoucher);
			table.getItems().add(journalVoucher);
			datet.clear();
			number.clear();
			value.clear();
			accountt.clear();
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
		});

		if(JournalVoucherdata.size() >= 0 ) {
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
		}

		delete.setOnAction(e ->{
			ObservableList<AccountManeger> allJournal , selectedJournal;
			allJournal = table.getItems();
			selectedJournal= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getJournalVoucherList().size(); i++) {
				if(Account.getJournalVoucherList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getJournalVoucherList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {
					JournalVoucherdata.remove(i);
				}
			}
			totalCreditt.setText(totalCreditJournalVoucher()+"");
			tataldebitt.setText(totalDepitJournalVoucher()+"");
			differencet.setText(defferentDepitCredit()+"");
			selectedJournal.forEach(allJournal::remove);
		});


		VBox v1 = new VBox(20,table,mix,addDelete,save);
		v1.setAlignment(Pos.CENTER);

		ceBorderPane.setCenter(v1);
		ceBorderPane.setAlignment(v1, Pos.CENTER);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);


		VBox buttons = menueArb(primaryStage);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);


		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}


	@SuppressWarnings("unchecked")
	private void purchasesOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("المشتريات");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");

		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("التاريخ        ");
		date.setPadding(new Insets(7));
		Label vaucher = new Label("رقم الفاتورة");
		vaucher.setPadding(new Insets(7));
		Label name = new Label("الإسم         ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		datet.setPromptText("اليوم-الشهر-السنة");
		TextField vauchert = new TextField();
		TextField namet = new TextField();

		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(vaucher, vauchert);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(vauchert,vaucher);
		h2.setAlignment(Pos.CENTER_RIGHT);
		HBox h3 = new HBox(namet,name);
		h3.setAlignment(Pos.CENTER_RIGHT);
		VBox v = new VBox(10 , h1,h2,h3);
		v.setAlignment(Pos.CENTER_RIGHT);

		TableView<Puchases> table = new TableView<Puchases>();


		TableColumn <Puchases, String>item = new TableColumn <Puchases, String>("البند ");
		item.setMinWidth(200);
		item.setCellValueFactory(  p -> new SimpleStringProperty(p.getValue().getItem()));
		item.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Double>price = new TableColumn <Puchases, Double>("السعر");
		price.setMinWidth(150);
		price.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
		price.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Long>quantity = new TableColumn<Puchases, Long>("الكمية");
		quantity.setMinWidth(200);
		quantity.setCellValueFactory( p -> new SimpleLongProperty(p.getValue().getQuantity()).asObject());
		quantity.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Double>total = new TableColumn<Puchases, Double>("المجموع ");
		total.setMinWidth(150);
		total.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getTotal()).asObject());
		total.setStyle("-fx-alignment: CENTER;");

		TableColumn <Puchases, Double>Vat = new TableColumn<Puchases, Double>("Vat % ");
		Vat.setMinWidth(198);
		Vat.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getVat()).asObject());
		Vat.setStyle("-fx-alignment: CENTER;");


		TableColumn <Puchases, Double>finalTotals = new TableColumn<Puchases, Double>("المجموع النهائي ");
		finalTotals.setMinWidth(200);
		finalTotals.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		finalTotals.setStyle("-fx-alignment: CENTER;");

		ObservableList<Puchases> data = FXCollections.observableArrayList(purchasesData);


		table.getColumns().addAll(finalTotals,Vat,total, quantity,price,item );
		table.setItems(data);
		table.setEditable(true);

		TextField items = new TextField();
		icons(items);
		items.setEffect(new DropShadow());
		items.setPromptText("أضف البند");
		TextField prices = new TextField();
		icons(prices);
		prices.setEffect(new DropShadow());
		prices.setPromptText("أضف السعر");
		TextField quantities = new TextField();
		icons(quantities);
		quantities.setEffect(new DropShadow());
		quantities.setPromptText("أضف الكمية");

		ComboBox<String> typeOfPay = new ComboBox<>();
		typeOfPay.setPrefSize(200,20);
		typeOfPay.setStyle("-fx-font-size: 14;");
		typeOfPay.setPromptText("اختر طريقة القبض");
		typeOfPay.getItems().addAll("نقداً","ذمم دائنة");
		icons(typeOfPay);

		Label finalTotal = new Label("صافي المشتريات");
		finalTotal.setPadding(new Insets(7));
		TextField finalTotalt = new TextField();

		IconedTextFieledArb(finalTotal, finalTotalt);

		HBox h4 = new HBox(finalTotalt, finalTotal);
		Button tr7eel = new Button("حفظ");
		tr7eel.setOnAction(e ->{
			for (int i = 0; i < Account.getPuchasesList().size(); i++) {
				Boolean flag = true , flag2 = true;
				if(Account.getPuchasesList().get(i).getType().equals("ذمم دائنة")) {
					for (int j = 0; j < JournalVoucherdata.size(); j++) {
						if(Account.getPuchasesList().get(i).equals(JournalVoucherdata.get(j))) {
							flag = false;
						}
					}
					if(flag)
						JournalVoucherdata.add(Account.getPuchasesList().get(i));
				}
				else if(Account.getPuchasesList().get(i).getType().equals("نقداً")) {
					for (int j = 0; j < daily.size(); j++) {
						if(Account.getPuchasesList().get(i).equals(daily.get(j))) {
							flag2 = false;
						}
					}
					if(flag2)
						daily.add(Account.getPuchasesList().get(i));
				}
			}
			writeFiles();
		});
		butoonEffect(tr7eel);
		icons(tr7eel);
		tr7eel.setEffect(new DropShadow());

		VBox v3 = new VBox(10 , h4,tr7eel);
		v3.setAlignment(Pos.CENTER_LEFT);

		Button add = new Button("أضف");
		butoonEffect(add);
		icons(add);

		if(Account.getPuchasesList().size() > 0 ) {
			finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
		}
		table.getItems().clear();
		for (int i = 0; i < Account.getPuchasesList().size(); i++) {
			table.getItems().addAll(Account.getPuchasesList().get(i));
		}

		typeOfPay.setOnAction(r ->{
			if(typeOfPay.getValue().equals("نقداً")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Puchases puchases =  new Puchases(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					puchases.ManagePurcahse();
					purchasesData.add(puchases);
					Account.getPuchasesList().add(puchases);
					table.getItems().add(puchases);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
				});
			}
			else if(typeOfPay.getValue().equals("ذمم دائنة")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Puchases puchases =  new Puchases(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					puchases.ManagePurcahse();
					purchasesData.add(puchases);
					Account.getPuchasesList().add(puchases);
					table.getItems().add(puchases);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");
				});
			}
		});

		add.setEffect(new DropShadow());

		Button delete = new Button("إحذف");
		butoonEffect(delete);
		icons(delete);
		delete.setEffect(new DropShadow());

		delete.setOnAction(e ->{
			ObservableList<Puchases> allPuchases , selectedPuchases;
			allPuchases = table.getItems();
			selectedPuchases= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getPuchasesList().size(); i++) {
				if(Account.getPuchasesList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getPuchasesList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					JournalVoucherdata.remove(i);
				}
			}
			for (int i = 0; i < purchasesData.size(); i++) {
				if(purchasesData.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					purchasesData.remove(i);
				}
			}
			selectedPuchases.forEach(allPuchases::remove);
			finalTotalt.setText(Account.allFinalTotalallFinalTotalPurchases()+"");

		});

		HBox addDelete = new HBox(10,delete,add,items,prices,quantities,typeOfPay);
		addDelete.setAlignment(Pos.CENTER);


		VBox v2 = new VBox(20 , v, table,addDelete,v3);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		centerPane.setCenter(v2);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void cashReceiptsOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("سند قبض ");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("التاريخ           ");
		date.setPadding(new Insets(7));
		Label marje3 = new Label("المرجع           ");
		marje3.setPadding(new Insets(7));
		Label name = new Label("الإسم            ");
		name.setPadding(new Insets(7));
		Label voucher = new Label("رقم السند       ");
		voucher.setPadding(new Insets(7));
		TextField vouchert = new TextField();
		TextField datet = new TextField();
		TextField namet = new TextField();

		ObservableList<String> data = FXCollections.observableArrayList();
		for (int i = 0; i < Account.getRevenueList().size(); i++) {
			data.addAll(Account.getRevenueList().get(i).getVoucher());
		}

		ComboBox<String> marje3t = new ComboBox<>();
		marje3t.setPrefSize(195,20);
		marje3t.setStyle("-fx-font-size: 14;");
		marje3t.setPromptText("اختر المرجع");

		marje3t.getItems().addAll(data);



		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(marje3, marje3t);
		IconedTextFieledArb(name, namet);
		IconedTextFieledArb(voucher, vouchert);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(marje3t,marje3);
		h2.setAlignment(Pos.CENTER_RIGHT);
		HBox h3 = new HBox(namet,name);
		h3.setAlignment(Pos.CENTER_RIGHT);
		HBox h4 = new HBox(vouchert,voucher);
		h4.setAlignment(Pos.CENTER_RIGHT);


		RadioButton cash  = new RadioButton(" نقداً ");
		cash.setPadding(new Insets(5));
		RadioButton shik  = new RadioButton(" شيك ");
		shik.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		cash.setToggleGroup(tg);
		shik.setToggleGroup(tg);
		Label tybePayment = new Label("طريقة الدفع   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(50,cash,shik);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);

		Label sal = new Label("المبلغ            ");
		sal.setPadding(new Insets(7));
		TextField salary = new TextField();
		IconedTextFieledArb(sal, salary);
		HBox sala = new HBox(salary,sal);
		sala.setAlignment(Pos.CENTER_RIGHT);

		HBox mex = new HBox(typePay,tybePayment);
		mex.setAlignment(Pos.CENTER_RIGHT);

		HBox mex2 = new HBox(30,mex,sala);
		mex2.setAlignment(Pos.CENTER_RIGHT);

		VBox v = new VBox(20 , h1,h4,h3,h2);
		v.setAlignment(Pos.CENTER_RIGHT);


		Label numOfshik = new Label("رقم الشيك     ");
		numOfshik.setPadding(new Insets(7));
		TextField numofshikt = new TextField();

		IconedTextFieledArb(numOfshik, numofshikt);
		HBox hnum = new HBox(numofshikt,numOfshik);

		Label DateOfshik = new Label("تاريخ الشيك");
		DateOfshik.setPadding(new Insets(7));
		TextField DateOfshikt = new TextField();
		DateOfshikt.setPromptText("اليوم - الشهر - السنة");

		IconedTextFieledArb(DateOfshik, DateOfshikt);
		HBox hdate= new HBox(DateOfshikt,DateOfshik);

		Label nameOfBank = new Label("اسم البنك");
		nameOfBank.setPadding(new Insets(7));
		TextField nameOfBankt = new TextField();

		IconedTextFieledArb(nameOfBank,nameOfBankt);
		HBox hname= new HBox(nameOfBankt,nameOfBank);

		HBox mex3 = new HBox(30,hname,hdate,hnum);
		mex3.setAlignment(Pos.CENTER_RIGHT);

		Label nameOfCustomer = new Label("إستلمنا من");
		nameOfCustomer.setPadding(new Insets(7));
		TextField nameOfCustomert = new TextField();
		IconedTextFieledArb(nameOfCustomer, nameOfCustomert);
		HBox hnameC= new HBox(nameOfCustomert,nameOfCustomer);
		hnameC.setAlignment(Pos.CENTER_RIGHT);

		VBox v2 = new VBox(20,mex2,mex3);
		v2.setAlignment(Pos.TOP_CENTER);

		Button save = new Button("إحفظ");
		butoonEffect(save);
		icons(save);

		cash.setOnAction(e ->{
			nameOfBankt.setEditable(false);
			numofshikt.setEditable(false);
			DateOfshikt.setEditable(false);
		});

		shik.setOnAction(e ->{
			nameOfBankt.setEditable(true);
			numofshikt.setEditable(true);
			DateOfshikt.setEditable(true);
		});

		save.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" نقداً ") ) {
				CashRecepts cashRecepts= new CashRecepts(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1),Integer.parseInt(x[0])), namet.getText(),
						nameOfCustomert.getText(), marje3t.getSelectionModel().getSelectedItem().toString() ,Double.parseDouble(salary.getText()), true);
				cashRecepts.setVoucher(vouchert.getText());
				cashRecepts.setAccount("سند قبض");
				cashRecepts.setDepiteWhthVat("ذمم مدينة");
				cashRecepts.setCriditWhthVat("النقد");
				cashRecepts.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getCashReceptsList().add(cashRecepts);
				daily.add(cashRecepts);


			}
			else if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" شيك ") ) {
				String [] z = DateOfshikt.getText().split("-");
				CashRecepts cashRecepts = new CashRecepts(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
						new Date(Integer.parseInt(z[2]),Integer.parseInt(z[1]), Integer.parseInt(z[0])), namet.getText(), nameOfBankt.getText(),
						nameOfCustomert.getText(), marje3t.getSelectionModel().getSelectedItem().toString() 
						,Double.parseDouble(salary.getText()),Integer.parseInt(numofshikt.getText()), false);
				cashRecepts.setVoucher(vouchert.getText());
				cashRecepts.setAccount("سند قبض (بشيك)");
				cashRecepts.setDepiteWhthVat("ذمم مدينة");
				cashRecepts.setCriditWhthVat("النقد");
				cashRecepts.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getCashReceptsList().add(cashRecepts);
				daily.add(cashRecepts);

			}
			datet.clear();
			namet.clear();
			nameOfCustomert.clear();
			tg.selectToggle(null);
			salary.clear();
			nameOfBankt.clear();
			DateOfshikt.clear();
			numofshikt.clear();
			vouchert.clear();
		});

		VBox v1 = new VBox(50,v,v2,hnameC);
		v1.setAlignment(Pos.CENTER_RIGHT);
		v1.setPadding(new Insets(30));

		VBox v3 = new VBox(70,v1,save);
		v3.setAlignment(Pos.CENTER);
		save.setAlignment(Pos.CENTER_LEFT);

		centerPane.setCenter(v3);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}


	private void paymentVoucherOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("سند صرف");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("التاريخ           ");
		date.setPadding(new Insets(7));
		Label marje3 = new Label("صرف على     ");
		marje3.setPadding(new Insets(7));
		Label name = new Label("الإسم            ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		TextField namet = new TextField();
		TextField marje3t = new TextField();
		Label voucher = new Label("رقم السند       ");
		voucher.setPadding(new Insets(7));
		TextField vouchert = new TextField();

		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(name, namet);
		IconedTextFieledArb(marje3, marje3t);
		IconedTextFieledArb(voucher, vouchert);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(marje3t,marje3);
		h2.setAlignment(Pos.CENTER_RIGHT);
		HBox h3 = new HBox(namet,name);
		h3.setAlignment(Pos.CENTER_RIGHT);
		HBox h4 = new HBox(vouchert,voucher);
		h4.setAlignment(Pos.CENTER_RIGHT);

		RadioButton cash  = new RadioButton(" نقداً ");
		cash.setPadding(new Insets(5));
		RadioButton shik  = new RadioButton(" شيك ");
		shik.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		cash.setToggleGroup(tg);
		shik.setToggleGroup(tg);
		Label tybePayment = new Label("طريقة الدفع   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(50,cash,shik);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);

		Label sal = new Label("المبلغ            ");
		sal.setPadding(new Insets(7));
		TextField salary = new TextField();
		IconedTextFieledArb(sal, salary);
		HBox sala = new HBox(salary,sal);
		sala.setAlignment(Pos.CENTER_RIGHT);

		HBox mex = new HBox(typePay,tybePayment);
		mex.setAlignment(Pos.CENTER_RIGHT);

		HBox mex2 = new HBox(30,mex,sala);
		mex2.setAlignment(Pos.CENTER_RIGHT);

		VBox v = new VBox(20 , h1,h4,h3,h2);
		v.setAlignment(Pos.CENTER_RIGHT);


		Label numOfshik = new Label("رقم الشيك     ");
		numOfshik.setPadding(new Insets(7));
		TextField numofshikt = new TextField();

		IconedTextFieledArb(numOfshik, numofshikt);
		HBox hnum = new HBox(numofshikt,numOfshik);

		Label DateOfshik = new Label("تاريخ الشيك");
		DateOfshik.setPadding(new Insets(7));
		TextField DateOfshikt = new TextField();
		DateOfshikt.setPromptText("اليوم - الشهر - السنة");

		IconedTextFieledArb(DateOfshik, DateOfshikt);
		HBox hdate= new HBox(DateOfshikt,DateOfshik);

		Label nameOfBank = new Label("اسم البنك");
		nameOfBank.setPadding(new Insets(7));
		TextField nameOfBankt = new TextField();

		IconedTextFieledArb(nameOfBank,nameOfBankt);
		HBox hname= new HBox(nameOfBankt,nameOfBank);

		HBox mex3 = new HBox(30,hname,hdate,hnum);
		mex3.setAlignment(Pos.CENTER_RIGHT);

		Label nameOfCustomer = new Label("اسم المستفيد ");
		nameOfCustomer.setPadding(new Insets(7));
		TextField nameOfCustomert = new TextField();
		IconedTextFieledArb(nameOfCustomer, nameOfCustomert);
		HBox hnameC= new HBox(nameOfCustomert,nameOfCustomer);
		hnameC.setAlignment(Pos.CENTER_RIGHT);

		VBox v2 = new VBox(20,mex2,mex3);
		v2.setAlignment(Pos.TOP_CENTER);

		Button save = new Button("إحفظ");
		butoonEffect(save);
		icons(save);

		cash.setOnAction(e ->{
			nameOfBankt.setEditable(false);
			numofshikt.setEditable(false);
			DateOfshikt.setEditable(false);
		});

		shik.setOnAction(e ->{
			nameOfBankt.setEditable(true);
			numofshikt.setEditable(true);
			DateOfshikt.setEditable(true);
		});

		save.setOnAction(e ->{
			String [] x = datet.getText().split("-");
			if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" نقداً ") ) {
				PaymentVoucher PaymentVoucher =new PaymentVoucher(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1),Integer.parseInt(x[0])), namet.getText(),
						nameOfCustomert.getText(),Double.parseDouble(salary.getText()), true); 
				PaymentVoucher.setVoucher(vouchert.getText());
				PaymentVoucher.setAccount("سند صرف");
				PaymentVoucher.setDepiteWhthVat("النقد");
				PaymentVoucher.setCriditWhthVat("مصروف");
				PaymentVoucher.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getPaymentVoucher().add(PaymentVoucher);
				daily.add(PaymentVoucher);
			}
			else if(  ((RadioButton) tg.getSelectedToggle()).getText().equals(" شيك ") ) {
				String [] z = DateOfshikt.getText().split("-");
				PaymentVoucher PaymentVoucher = new PaymentVoucher(new Date(((Integer.parseInt(x[2])-1900)),(Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
						new Date(Integer.parseInt(z[2]),Integer.parseInt(z[1]), Integer.parseInt(z[0])), namet.getText(), nameOfBankt.getText(),
						nameOfCustomert.getText(),Double.parseDouble(salary.getText()),Integer.parseInt(numofshikt.getText()), false);
				PaymentVoucher.setVoucher(vouchert.getText());
				PaymentVoucher.setAccount("سند صرف (بشيك)");
				PaymentVoucher.setDepiteWhthVat("النقد");
				PaymentVoucher.setCriditWhthVat("ذمم دائنة");
				PaymentVoucher.setFinalTotal(Double.parseDouble(salary.getText()));
				Account.getPaymentVoucher().add(PaymentVoucher);
				daily.add(PaymentVoucher);
			}
			datet.clear();
			namet.clear();
			nameOfCustomert.clear();
			tg.selectToggle(null);
			salary.clear();
			nameOfBankt.clear();
			DateOfshikt.clear();
			numofshikt.clear();
			vouchert.clear();
			marje3t.clear();
		});

		VBox v1 = new VBox(50,v,v2,hnameC);
		v1.setAlignment(Pos.CENTER_RIGHT);
		v1.setPadding(new Insets(30));

		VBox v3 = new VBox(70,v1,save);
		v3.setAlignment(Pos.CENTER);
		save.setAlignment(Pos.CENTER_LEFT);

		centerPane.setCenter(v3);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void generaljournalOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("دفتر اليومية");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);


		TableView<AccountManeger> table = new TableView<AccountManeger>();

		TableColumn <AccountManeger, String> date = new TableColumn<AccountManeger, String>("التاريخ ");
		date.setMinWidth(128);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <AccountManeger, String>num = new TableColumn<AccountManeger, String>("رقم السند");
		num.setMinWidth(80);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String>account = new TableColumn<AccountManeger, String>("الحساب ");
		account.setMinWidth(150);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> depitPrice = new TableColumn<AccountManeger, Double>("المبلغ المدين ");
		depitPrice.setMinWidth(140);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, Double> creditPrice = new TableColumn<AccountManeger, Double>("المبلغ الدائن ");
		creditPrice.setMinWidth(140);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");


		TableColumn<AccountManeger, String> depitPriceJournal = new TableColumn<AccountManeger, String>("المبلغ المدين ");
		depitPriceJournal.setMinWidth(225);
		depitPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCriditWhthVat()));
		depitPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> creditPriceJournal = new TableColumn<AccountManeger, String>("المبلغ الدائن ");
		creditPriceJournal.setMinWidth(225);
		creditPriceJournal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDepiteWhthVat()));
		creditPriceJournal.setStyle("-fx-alignment: CENTER;");

		TableColumn<AccountManeger, String> journal = new TableColumn<AccountManeger, String>("القيد");
		journal.setMinWidth(450);
		TableColumn<AccountManeger, Double> price = new TableColumn<AccountManeger, Double>("المبلغ");
		price.setMinWidth(280);
		journal.getColumns().addAll(creditPriceJournal,depitPriceJournal);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<AccountManeger> data = FXCollections.observableArrayList(daily);

		table.getColumns().addAll(price,journal,account,num,date);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Button save = new Button("ترحيل");
		butoonEffect(save);
		icons(save);

		save.setOnAction(e ->{
			for (int i = 0; i < daily.size(); i++) {
				if(daily.get(i).getAccount().contains("مبيعات") | daily.get(i).getAccount().contains("Revenue")) {
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,daily.get(i).getTotal());
					boolean flag = true,flag2 = true,flag3 = true;
					for (int j = 0; j < Account.getRevenueGenral().size(); j++) {
						if(Account.getRevenueGenral().get(j).equals(general)) {
							flag= false;							
						}
					}
					if(flag) {						
						Account.getRevenueGenral().add(general);
					}
					if(daily.get(i).getCriditWhthVat().contains("النقد") | daily.get(i).getCriditWhthVat().contains("Cash")) {
						GenralLedger generalss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(), daily.get(i).getFinalTotal(),0);
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generalss)) {								
								flag2= false;
							}
						}
						if(flag2) {
							Account.getCashGenral().add(generalss);
						}						
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,((Revenue)daily.get(i)).getLiability());
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generals)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getVatGenral().add(generals);
						}
					}else
						if(daily.get(i).getCriditWhthVat().contains("ذمم مدينة") | daily.get(i).getCriditWhthVat().contains("Account receivable")) {
							GenralLedger generalsss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
									daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal() ,0);
							for (int j = 0; j < Account.getAccountReceivableGenral().size(); j++) {
								if(Account.getAccountReceivableGenral().get(j).equals(generalsss)) {									
									flag2= false;
								}
							}
							if(flag2) {
								Account.getAccountReceivableGenral().add(generalsss);								
							}
							GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
									daily.get(i).getVoucher(), daily.get(i).getAccount(), 0,((Revenue)daily.get(i)).getLiability());
							for (int j = 0; j < Account.getVatGenral().size(); j++) {
								if(Account.getVatGenral().get(j).equals(generalssss)) {									
									flag3= false;
								}
							}
							if(flag3) {
								Account.getVatGenral().add(generalssss);								
							}
						}
				}else if(daily.get(i).getAccount().contains("مشتريات") | daily.get(i).getAccount().contains("Purcahse")) {
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getTotal(),0);
					boolean flag = true,flag2 = true,flag3 = true;
					for (int j = 0; j < Account.getPurchaseGenral().size(); j++) {
						if(Account.getPurchaseGenral().get(j).equals(general)) {
							flag= false;							
						}
					}
					if(flag) {						
						Account.getPurchaseGenral().add(general);
					} 
					if(daily.get(i).getDepiteWhthVat().contains("النقد") | daily.get(i).getDepiteWhthVat().contains("Cash")) {
						GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),((Puchases)daily.get(i)).getPrePaiedTax(),0);
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generalssss)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getVatGenral().add(generalssss);	
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getCashGenral().add(generals);
						}
					}else if(daily.get(i).getDepiteWhthVat().contains("ذمم دائنة") | daily.get(i).getDepiteWhthVat().contains("Account payable")) {
						GenralLedger generalss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getAccountPayableGenral().size(); j++) {
							if(Account.getAccountPayableGenral().get(j).equals(generalss)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getAccountPayableGenral().add(generalss);
						}
						GenralLedger generalssss = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),((Puchases)daily.get(i)).getPrePaiedTax(),0);
						for (int j = 0; j < Account.getVatGenral().size(); j++) {
							if(Account.getVatGenral().get(j).equals(generalssss)) {								
								flag3= false;
							}
						}
						if(flag3) {							
							Account.getVatGenral().add(generalssss);
						}
					}
				}else if(daily.get(i).getAccount().contains("سند قبض") | daily.get(i).getAccount().contains("Cash Recepts") ) {
					boolean flag = true,flag2 = true;
					String [] x = daily.get(i).getDate().split("-");
					GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
					for (int j = 0; j < Account.getCashGenral().size(); j++) {
						if(Account.getCashGenral().get(j).equals(general)) {							
							flag= false;
						}
					}
					if(flag) {						
						Account.getCashGenral().add(general);
					}
					GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
							daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
					for (int j = 0; j < Account.getAccountReceivableGenral().size(); j++) {
						if(Account.getAccountReceivableGenral().get(j).equals(generals)) {							
							flag2= false;
						}
					}
					if(flag2) {						
						Account.getAccountReceivableGenral().add(generals);
					}
				}else if(daily.get(i).getAccount().contains("سند صرف") | daily.get(i).getAccount().contains("Payment Voucher")) {
					boolean flag = true,flag2 = true;
					String [] x = daily.get(i).getDate().split("-");
					if(daily.get(i).getCriditWhthVat().contains("مصروف") | daily.get(i).getCriditWhthVat().contains("Expense")) {
						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
						for (int j = 0; j < Account.getExpenseGenral().size(); j++) {
							if(Account.getExpenseGenral().get(j).equals(general)) {								
								flag= false;
							}
						}
						if(flag) {							
							Account.getExpenseGenral().add(general);
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getCashGenral().add(generals);
						}
					}
					//					if(daily.get(i).getCriditWhthVat().contains("ذمم دائنة") | daily.get(i).getCriditWhthVat().contains(" Cash ")) {
					//						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
					//								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);
					//						for (int j = 0; j < Account.getExpenseGenral().size(); j++) {
					//							if(Account.getExpenseGenral().get(j).equals(general)) {								
					//								flag= false;
					//							}
					//						}
					//						if(flag) {							
					//							Account.getExpenseGenral().add(general);
					//						}
					//						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
					//								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
					//						for (int j = 0; j < Account.getAccountPayableGenral().size(); j++) {
					//							if(Account.getAccountPayableGenral().get(j).equals(generals)) {								
					//								flag2= false;
					//							}
					//						}
					//						if(flag2) {							
					//							Account.getAccountPayableGenral().add(generals);
					//						}
					//					}
				}else if(daily.get(i).getAccount().contains("رأس المال مدفوع") | daily.get(i).getAccount().contains("Capital")) {
					boolean flag = true,flag2 = true;;
					String [] x = daily.get(i).getDate().split("-");
					if(daily.get(i).getCriditWhthVat().contains("النقد") | daily.get(i).getCriditWhthVat().contains("Cash")) {
						GenralLedger general = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),0,daily.get(i).getFinalTotal());
						for (int j = 0; j < Account.getOwnerGenral().size(); j++) {
							if(Account.getOwnerGenral().get(j).equals(general)) {								
								flag= false;
							}
						}
						if(flag) {							
							Account.getOwnerGenral().add(general);
						}
						GenralLedger generals = new GenralLedger(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])),
								daily.get(i).getVoucher(), daily.get(i).getAccount(),daily.get(i).getFinalTotal(),0);

						for (int j = 0; j < Account.getCashGenral().size(); j++) {
							if(Account.getCashGenral().get(j).equals(generals)) {								
								flag2= false;
							}
						}
						if(flag2) {							
							Account.getCashGenral().add(generals);
						}
					}
				}
			}
			daily.removeAll(daily);
			table.getItems().clear();
		});

		table.setMinHeight(555);
		VBox v = new VBox(15,table,save);
		v.setAlignment(Pos.CENTER);

		ceBorderPane.setCenter(v);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void generalLedgerOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("دفتر الأستاذ العام");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);


		TableView<GenralLedger> table = new TableView<GenralLedger>();

		TableColumn <GenralLedger, String> date = new TableColumn<GenralLedger, String>("التاريخ ");
		date.setMinWidth(218);
		date.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate()));
		date.setStyle("-fx-alignment: CENTER;");

		TableColumn <GenralLedger, String>num = new TableColumn<GenralLedger, String>("رقم السند");
		num.setMinWidth(130);
		num.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVoucher()));
		num.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, String>account = new TableColumn<GenralLedger, String>("الحساب ");
		account.setMinWidth(300);
		account.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccount()));
		account.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> depitPrice = new TableColumn<GenralLedger, Double>("المبلغ المدين ");
		depitPrice.setMinWidth(220);
		depitPrice.setCellValueFactory(p -> new SimpleDoubleProperty(((GenralLedger)p.getValue()).getDepit()).asObject());
		depitPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> creditPrice = new TableColumn<GenralLedger, Double>("المبلغ الدائن ");
		creditPrice.setMinWidth(220);
		creditPrice.setCellValueFactory(p -> new SimpleDoubleProperty(((GenralLedger)p.getValue()).getCredit()).asObject());
		creditPrice.setStyle("-fx-alignment: CENTER;");

		TableColumn<GenralLedger, Double> price = new TableColumn<GenralLedger, Double>("المبلغ");
		price.setMinWidth(440);
		price.getColumns().addAll(creditPrice,depitPrice);

		ObservableList<GenralLedger> data = FXCollections.observableArrayList();

		table.getColumns().addAll(price,account,date,num);	
		table.setItems(data);
		table.setEditable(false);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Button revenueButton = new Button("المبيعات");
		revenueButton.setPrefWidth(200);
		revenueButton.setPrefHeight(40);
		revenueButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		revenueButton.setEffect(new DropShadow());

		revenueButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getRevenueGenral().size(); i++) {
				table.getItems().add( Account.getRevenueGenral().get(i));
			}
		});

		Button purchasesButton = new Button("المشتريات");
		purchasesButton.setPrefWidth(200);
		purchasesButton.setPrefHeight(40);
		purchasesButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		purchasesButton.setEffect(new DropShadow());

		purchasesButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getPurchaseGenral().size(); i++) {
				table.getItems().add( Account.getPurchaseGenral().get(i));
			}
		});

		Button cashButton = new Button("النقد");
		cashButton.setPrefWidth(200);
		cashButton.setPrefHeight(40);
		cashButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		cashButton.setEffect(new DropShadow());
		cashButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getCashGenral().size(); i++) {
				table.getItems().add(Account.getCashGenral().get(i));
			}
		});


		Button accountPayableButton = new Button("ذمم دائنة");
		accountPayableButton.setPrefWidth(200);
		accountPayableButton.setPrefHeight(40);
		accountPayableButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		accountPayableButton.setEffect(new DropShadow());

		accountPayableButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getAccountPayableGenral().size(); i++) {
				table.getItems().add( Account.getAccountPayableGenral().get(i));
			}
		});


		Button accountReceivableButton = new Button("ذمم مدينة");
		accountReceivableButton.setPrefWidth(200);
		accountReceivableButton.setPrefHeight(40);
		accountReceivableButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		accountReceivableButton.setEffect(new DropShadow());

		accountReceivableButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getAccountReceivableGenral().size(); i++) {
				table.getItems().add( Account.getAccountReceivableGenral().get(i));
			}
		});

		Button expenseButton = new Button("المصروف");
		expenseButton.setPrefWidth(200);
		expenseButton.setPrefHeight(40);
		expenseButton.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		expenseButton.setEffect(new DropShadow());

		expenseButton.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getExpenseGenral().size(); i++) {
				table.getItems().add( Account.getExpenseGenral().get(i));
			}
		});

		Button vat = new Button("ضريبة ق . م");
		vat.setPrefWidth(200);
		vat.setPrefHeight(40);
		vat.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		vat.setEffect(new DropShadow());

		vat.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getVatGenral().size(); i++) {
				table.getItems().add( Account.getVatGenral().get(i));
			}
		});

		Button owner = new Button("رأس المال");
		owner.setPrefWidth(200);
		owner.setPrefHeight(40);
		owner.setStyle("-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-border-width:  1.1;" +
				"-fx-border-color:#d8d9e0;" );
		owner.setEffect(new DropShadow());

		owner.setOnAction(e ->{
			data.clear();
			for (int i = 0; i < Account.getOwnerGenral().size(); i++) {
				table.getItems().add( Account.getOwnerGenral().get(i));
			}
		});

		HBox hBoxButton = new HBox(revenueButton,purchasesButton,expenseButton,cashButton,accountPayableButton,accountReceivableButton,vat,owner);
		hBoxButton.setAlignment(Pos.CENTER);
		table.setMinHeight(580);
		VBox v = new VBox(hBoxButton,table);
		v.setAlignment(Pos.CENTER);


		ceBorderPane.setCenter(v);

		ceBorderPane.setAlignment(table, Pos.CENTER);
		ceBorderPane.setPadding(new Insets(20));
		pane.setCenter(ceBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void financialStatementOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("القوائم المالية");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		



		Label name = new Label("اسم الشركة");
		Label date = new Label("السنة        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField();


		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(namet,name);
		h2.setAlignment(Pos.CENTER_RIGHT);

		RadioButton type1  = new RadioButton(" قائمة الدخل ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton("حقوق الملكية");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" الميزانية العمومية ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("القائمة   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);
		HBox types = new HBox(typePay,tybePayment);
		types.setAlignment(Pos.CENTER_RIGHT);

		Button move = new Button("انتقل الى القائمة");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" قائمة الدخل "))
				incomeSatamentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals("حقوق الملكية"))
				balanceSheetStatmentArab(namet.getText(),datet.getText(),primaryStage);
			else if(((RadioButton) tg.getSelectedToggle()).getText().equals(" الميزانية العمومية "))
				ownerEquityStatmentArab(namet.getText(),datet.getText(),primaryStage);
		});


		VBox v = new VBox(10,h2,h1,types,move);
		v.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png")); 
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void ownerEquityStatmentArab(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("القوائم المالية");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("اسم الشركة");
		Label date = new Label("السنة        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField(); 


		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(namet,name);
		h2.setAlignment(Pos.CENTER_RIGHT);

		RadioButton type1  = new RadioButton(" قائمة الدخل ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton("حقوق الملكية");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" الميزانية العمومية ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("القائمة   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);
		HBox types = new HBox(typePay,tybePayment);
		types.setAlignment(Pos.CENTER_RIGHT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("انتقل الى القائمة");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" قائمة الدخل "))
				incomeSatamentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals("حقوق الملكية"))
				balanceSheetStatmentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" الميزانية العمومية "))
				ownerEquityStatmentArab(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("النقد", Account.getFinalTotalCashForFinStat()),
				new financialStatement("ذمم مدينة", Account.getFinalTotalAccRecevForFinStat()),
				new financialStatement("بضاعة اخر المدة", ( 0.5 *Account.getFinalTotalPurshasesForFinStat())),
				new financialStatement("ضريبة القيمة المضافة", Account.getFinalTotalVatForFinStat()),
				new financialStatement("مجموع الأصول", (Account.getFinalTotalCashForFinStat() + Account.getFinalTotalAccRecevForFinStat()
				+( 0.5 *Account.getFinalTotalPurshasesForFinStat()) +  Account.getFinalTotalVatForFinStat())),
				new financialStatement("ذمم دائنة", Account.getFinalTotalAccPayabelForFinStat()),
				new financialStatement("مجموع الالتزامات", Account.getFinalTotalAccPayabelForFinStat()),
				new financialStatement("رأس المال",(Account.getFinalTotalOwnerForFinStat() + ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()))),
				new financialStatement("مجموع الالتزامات ورأس المال", ( Account.getFinalTotalAccPayabelForFinStat() + (Account.getFinalTotalOwnerForFinStat() + 
						((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment())) ))

				);

		table.getColumns().addAll(num,acc);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label("قائمة الميزانية العمومية");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("شركة" + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("قائمة الميزانية العمومية كما هي في " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void ownerEquityStatmentEng(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("Financial Statement");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("Company Name");
		Label date = new Label("Year        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField(); 


		IconedTextFieled(date, datet);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(name,namet);
		h2.setAlignment(Pos.CENTER_LEFT);

		RadioButton type1  = new RadioButton(" Income Statment ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton(" Balance Sheet Statment ");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" Owner Equity Statment ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("Statment");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);
		HBox types = new HBox(tybePayment,typePay);
		types.setAlignment(Pos.CENTER_LEFT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("Move to statment");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Income Statment "))
				incomeSatamentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Balance Sheet Statment "))
				balanceSheetStatmentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Owner Equity Statment "))
				ownerEquityStatmentEng(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("Cash", Account.getFinalTotalCashForFinStat()),
				new financialStatement("Account recevable", Account.getFinalTotalAccRecevForFinStat()),
				new financialStatement("Inventory", ( 0.5 *Account.getFinalTotalPurshasesForFinStat())),
				new financialStatement("Vat", Account.getFinalTotalVatForFinStat()),
				new financialStatement("Total assets", (Account.getFinalTotalCashForFinStat() + Account.getFinalTotalAccRecevForFinStat()
				+( 0.5 *Account.getFinalTotalPurshasesForFinStat()) +  Account.getFinalTotalVatForFinStat())),
				new financialStatement("Account Payable", Account.getFinalTotalAccPayabelForFinStat()),
				new financialStatement("Total liabilities", Account.getFinalTotalAccPayabelForFinStat()),
				new financialStatement("Owner's equity",(Account.getFinalTotalOwnerForFinStat() + ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()))),
				new financialStatement("Liabilities & Owner's equity",  ( Account.getFinalTotalAccPayabelForFinStat() + (Account.getFinalTotalOwnerForFinStat() + 
						((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment())) ))

				);

		table.getColumns().addAll(acc,num);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label(" Owner Equity Statment ");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("Company " + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("Balance sheet list as it is in " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueEng(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void balanceSheetStatmentEng(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("Financial Statement");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("Company Name");
		Label date = new Label("Year        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField(); 


		IconedTextFieled(date, datet);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(name,namet);
		h2.setAlignment(Pos.CENTER_LEFT);

		RadioButton type1  = new RadioButton(" Income Statment ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton(" Balance Sheet Statment ");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" Owner Equity Statment ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("Statment   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);
		HBox types = new HBox(tybePayment,typePay);
		types.setAlignment(Pos.CENTER_LEFT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("Move to statment");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Income Statment "))
				incomeSatamentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Balance Sheet Statment "))
				balanceSheetStatmentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Owner Equity Statment "))
				ownerEquityStatmentEng(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("Capital in 1/1", Account.getFinalTotalOwnerForFinStat()),
				new financialStatement("Additions (+)", 0.0),
				new financialStatement("Reductions (-)", 0.0),
				new financialStatement("Withdraws (-)", 0.0),
				new financialStatement("Profit (+) / Loss (-)", ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()) ),
				new financialStatement("Capital in 31/12", (Account.getFinalTotalOwnerForFinStat() + ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment())))
				);

		table.getColumns().addAll(acc,num);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label(" Balance Sheet Statment ");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("Company " + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("Equity for the fiscal year ending on 31 December " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueEng(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void balanceSheetStatmentArab(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("القوائم المالية");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("اسم الشركة");
		Label date = new Label("السنة        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField(); 


		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(namet,name);
		h2.setAlignment(Pos.CENTER_RIGHT);

		RadioButton type1  = new RadioButton(" قائمة الدخل ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton("حقوق الملكية");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" الميزانية العمومية ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("القائمة   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);
		HBox types = new HBox(typePay,tybePayment);
		types.setAlignment(Pos.CENTER_RIGHT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("انتقل الى القائمة");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" قائمة الدخل "))
				incomeSatamentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals("حقوق الملكية"))
				balanceSheetStatmentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" الميزانية العمومية "))
				ownerEquityStatmentArab(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("رأس المال في ١/١ ", Account.getFinalTotalOwnerForFinStat()),
				new financialStatement("(+) اضافات", 0.0),
				new financialStatement("(-) تخفيضات", 0.0),
				new financialStatement("(-) مسحوبات", 0.0),
				new financialStatement("(+) ربح / (-) خسارة ", ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()) ),
				new financialStatement("رأس المال في ٣١/١٢ ", (Account.getFinalTotalOwnerForFinStat() + ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment())))
				);

		table.getColumns().addAll(num,acc);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label("قائمة حقوق الملكية");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("شركة" + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("قائمة حقوق الملكية عن السنة المالية المنتهية في ٣١ ديسمبر " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void incomeSatamentEng(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("Financial Statement");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("Company Name");
		Label date = new Label("Year        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField();


		IconedTextFieled(date, datet);
		IconedTextFieled(name, namet);

		HBox h1 = new HBox(date,datet);
		h1.setAlignment(Pos.CENTER_LEFT);
		HBox h2 = new HBox(name,namet);
		h2.setAlignment(Pos.CENTER_LEFT);

		RadioButton type1  = new RadioButton(" Income Statment ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton(" Balance Sheet Statment ");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" Owner Equity Statment ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("Statment");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieled(tybePayment,typePay);
		HBox types = new HBox(tybePayment,typePay);
		types.setAlignment(Pos.CENTER_LEFT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("Move to Statment");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Income Statment "))
				incomeSatamentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Balance Sheet Statment "))
				balanceSheetStatmentEng(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" Owner Equity Statment "))
				ownerEquityStatmentEng(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("Sales Revenue", Account.getFinalTotalRevenueForFinStat()),
				new financialStatement("Cost of goods sold", Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat())),
				new financialStatement("Gross profit", (Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat())))),
				new financialStatement("Expenses", Account.getFinalTotalPayment()),
				new financialStatement("Net income (Loss)", ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()) )
				);

		table.getColumns().addAll(acc,num);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 25;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label(" Income Statment ");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("Company " + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("Income statement for the fiscal year ending on 31 December " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setLeft(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setRight(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	private void incomeSatamentArab(String text, String text2, Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		BorderPane centerBorderPane = new BorderPane();

		Label welcome = new Label("القوائم المالية");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;"); 
		welcome.setPadding(new Insets(20));
		centerBorderPane.setTop(welcome);
		centerBorderPane.setAlignment(welcome, Pos.CENTER);		

		Label name = new Label("اسم الشركة");
		Label date = new Label("السنة        ");
		date.setPadding(new Insets(7));
		name.setPadding(new Insets(7));
		TextField namet = new TextField();
		TextField datet = new TextField();


		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(namet,name);
		h2.setAlignment(Pos.CENTER_RIGHT);

		RadioButton type1  = new RadioButton(" قائمة الدخل ");
		type1.setPadding(new Insets(5));
		RadioButton type2  = new RadioButton("حقوق الملكية");
		type2.setPadding(new Insets(5));
		RadioButton type3  = new RadioButton(" الميزانية العمومية ");
		type3.setPadding(new Insets(5));
		ToggleGroup tg = new ToggleGroup();
		type1.setToggleGroup(tg);
		type2.setToggleGroup(tg);
		type3.setToggleGroup(tg);
		Label tybePayment = new Label("القائمة   ");
		tybePayment.setPadding(new Insets(7));
		HBox typePay = new HBox(10,type1,type2,type3);
		typePay.setAlignment(Pos.CENTER);
		IconedTextFieledArb(tybePayment,typePay);
		HBox types = new HBox(typePay,tybePayment);
		types.setAlignment(Pos.CENTER_RIGHT);

		VBox v = new VBox(10,h2,h1,types);
		v.setAlignment(Pos.CENTER);

		Button move = new Button("انتقل الى القائمة");
		icons(move);
		butoonEffect(move);
		move.setOnAction(e ->{
			if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" قائمة الدخل "))
				incomeSatamentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals("حقوق الملكية"))
				balanceSheetStatmentArab(namet.getText(),datet.getText(),primaryStage);
			else if( ((RadioButton) tg.getSelectedToggle()).getText().equals(" الميزانية العمومية "))
				ownerEquityStatmentArab(namet.getText(),datet.getText(),primaryStage);
		});

		TableView<financialStatement> table = new TableView<financialStatement>();

		TableColumn <financialStatement, String> acc = new TableColumn<financialStatement, String>("");
		acc.setMinWidth(300);
		acc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAaccount()));
		acc.setStyle("-fx-alignment: CENTER;");

		TableColumn <financialStatement, Double>num = new TableColumn<financialStatement, Double>("");
		num.setMinWidth(300);
		num.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
		num.setStyle("-fx-alignment: CENTER;");

		ObservableList<financialStatement> data = FXCollections.observableArrayList(
				new financialStatement("المبيعات", Account.getFinalTotalRevenueForFinStat()),
				new financialStatement("تكلفة المبيعات", Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat())),
				new financialStatement("الربح الاجمالي", (Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat())))),
				new financialStatement("المصروفات", Account.getFinalTotalPayment()),
				new financialStatement("الدخل الصافي (الخسارة)", ((Account.getFinalTotalRevenueForFinStat() - (Account.getFinalTotalPurshasesForFinStat() - (0.5 * Account.getFinalTotalPurshasesForFinStat()))) - Account.getFinalTotalPayment()) )
				);

		table.getColumns().addAll(num,acc);	
		table.setItems(data);
		table.setEditable(false);
		table.setMaxHeight(300);
		table.setMaxWidth(610);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 25;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		Label x = new Label("قائمة الدخل");
		x.setFont(new Font("Times New Roman",25));
		x.setStyle(" -fx-text-fill: #ffffff;"); 
		x.setPadding(new Insets(5));
		Label comp = new Label("شركة" + text);
		comp.setFont(new Font("Times New Roman",25));
		comp.setStyle(" -fx-text-fill: #ffffff;"); 
		comp.setPadding(new Insets(5));
		Label year = new Label("قائمة الدخل عن السنة المالية المنتهية في ٣١ ديسمبر " + text2);
		year.setFont(new Font("Times New Roman",25));
		year.setStyle(" -fx-text-fill: #ffffff;"); 
		year.setPadding(new Insets(5));
		VBox v2 = new VBox(10,x,comp,year,table);
		v2.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(20,v,v2,move);
		v1.setAlignment(Pos.CENTER);

		centerBorderPane.setCenter(v1);
		centerBorderPane.setPadding(new Insets(20));
		pane.setCenter(centerBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();

	}

	private void financialAnalysisOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane ceBorderPane =  new BorderPane();
		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label welcome = new Label("التحليل المالي");
		welcome.setFont(new Font("Times New Roman",50));
		welcome.setStyle(" -fx-text-fill: #ffffff;");
		welcome.setPadding(new Insets(20));

		ceBorderPane.setTop(welcome);
		ceBorderPane.setAlignment(welcome, Pos.CENTER);


		Label quick = new Label("                                  نسبة السيولة                               ");
		quick.setPadding(new Insets(7));
		quick.setMinWidth(180);
		TextField quickt = new TextField();
		finEffect(quick, quickt);
		VBox hquick = new VBox(quick,quickt);
		hquick.setAlignment(Pos.CENTER);

		quickt.setText( Account.LiquidityRatio() + " % " );
		quickt.setEditable(false);

		Label debt= new Label("                                نسبة المديونية                               ");
		debt.setPadding(new Insets(7));
		debt.setMinWidth(180);
		TextField debtt = new TextField();
		finEffect(debt, debtt);
		VBox hdepit = new VBox(debt,debtt);
		hdepit.setAlignment(Pos.CENTER);

		debtt.setText(Account.DeiptEquityRatio() + " % ");
		debtt.setEditable(false);

		Label activity = new Label("                           نسبة النشاط الانتاجي                           ");
		activity.setPadding(new Insets(7));
		activity.setMinWidth(180);
		TextField activityt = new TextField();
		finEffect(activity, activityt);
		VBox hactivity = new VBox(activity,activityt);
		hactivity.setAlignment(Pos.CENTER);

		activityt.setText( Account.ActivityRatio() + "");
		activityt.setEditable(false);

		Label perftbility = new Label("                                 نسبة الربحية                                  ");
		perftbility.setPadding(new Insets(7));
		perftbility.setMinWidth(180);
		TextField perftbilityt = new TextField();
		finEffect(perftbility, perftbilityt);
		VBox hperftbility = new VBox(perftbility,perftbilityt);
		hperftbility.setAlignment(Pos.CENTER);

		perftbilityt.setText(Account.GrossProfit() + " % "); 
		perftbilityt.setEditable(false);

		Label lquick = new Label("By Quick Ratio");
		lquick.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label ldebt = new Label("By Debt-Equity Ratio");
		ldebt.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label lactivity = new Label("By Inventory Turenover Ratio");
		lactivity.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		Label lperf = new Label("By Profit Margin Ratio");
		lperf.setStyle("-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n"+
				"-fx-text-fill: silver;");

		HBox h1 = new HBox(20,lquick,hquick);
		h1.setAlignment(Pos.CENTER_RIGHT);
		h1.setPadding(new Insets(15));

		HBox h2 = new HBox(20, ldebt,hdepit);
		h2.setAlignment(Pos.CENTER_RIGHT);
		h2.setPadding(new Insets(15));

		HBox h3 = new HBox(20,lactivity,hactivity);
		h3.setAlignment(Pos.CENTER_RIGHT);
		h3.setPadding(new Insets(15));

		HBox h4 = new HBox(20,lperf,hperftbility);
		h4.setAlignment(Pos.CENTER_RIGHT);
		h4.setPadding(new Insets(15));

		VBox mix = new VBox(30,h1,h2,h3,h4);
		mix.setAlignment(Pos.CENTER_RIGHT);

		ceBorderPane.setCenter(mix);
		pane.setCenter(ceBorderPane);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}


	private void revenueOnActionArb(Stage primaryStage) {
		BorderPane pane =  new BorderPane();
		BorderPane centerPane = new BorderPane();
		pane.setCenter(centerPane);

		centerPane.setPadding(new Insets(15));
		Label title = new Label("المبيعات");
		title.setFont(new Font("Times New Roman",50));
		title.setStyle(" -fx-text-fill: silver;");


		centerPane.setTop(title);
		centerPane.setAlignment(title, Pos.BOTTOM_CENTER);

		pane.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");

		Label date = new Label("التاريخ        ");
		date.setPadding(new Insets(7));
		Label vaucher = new Label("رقم الفاتورة");
		vaucher.setPadding(new Insets(7));
		Label name = new Label("الإسم         ");
		name.setPadding(new Insets(7));
		TextField datet = new TextField();
		datet.setPromptText("اليوم-الشهر-السنة");

		TextField vauchert = new TextField();
		TextField namet = new TextField();

		IconedTextFieledArb(date, datet);
		IconedTextFieledArb(vaucher, vauchert);
		IconedTextFieledArb(name, namet);

		HBox h1 = new HBox(datet,date);
		h1.setAlignment(Pos.CENTER_RIGHT);
		HBox h2 = new HBox(vauchert,vaucher);
		h2.setAlignment(Pos.CENTER_RIGHT);
		HBox h3 = new HBox(namet,name);
		h3.setAlignment(Pos.CENTER_RIGHT);
		VBox v = new VBox(10 , h1,h2,h3);
		v.setAlignment(Pos.CENTER_RIGHT);


		TableView<Revenue> table = new TableView<Revenue>();

		table.setEditable(false);

		TableColumn <Revenue, String>item = new TableColumn <Revenue, String>("البند ");
		item.setMinWidth(200);
		item.setCellValueFactory(  p -> new SimpleStringProperty(p.getValue().getItem()));
		item.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>price = new TableColumn <Revenue, Double>("السعر");
		price.setMinWidth(150);
		price.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
		price.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Long>quantity = new TableColumn<Revenue, Long>("الكمية");
		quantity.setMinWidth(200);
		quantity.setCellValueFactory( p -> new SimpleLongProperty(p.getValue().getQuantity()).asObject());
		quantity.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>total = new TableColumn<Revenue, Double>("المجموع ");
		total.setMinWidth(150);
		total.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getTotal()).asObject());
		total.setStyle("-fx-alignment: CENTER;");

		TableColumn <Revenue, Double>Vat = new TableColumn<Revenue, Double>("Vat % ");
		Vat.setMinWidth(198);
		Vat.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getVat()).asObject());
		Vat.setStyle("-fx-alignment: CENTER;");


		TableColumn <Revenue, Double>finalTotals = new TableColumn<Revenue, Double>("المجموع النهائي ");
		finalTotals.setMinWidth(200);
		finalTotals.setCellValueFactory( p -> new SimpleDoubleProperty(p.getValue().getFinalTotal()).asObject());
		finalTotals.setStyle("-fx-alignment: CENTER;");

		ObservableList<Revenue> data = FXCollections.observableArrayList(revenueData);

		table.setItems(data);

		table.getColumns().addAll(finalTotals,Vat,total, quantity,price,item );


		TextField items = new TextField();
		icons(items);
		items.setEffect(new DropShadow());
		items.setPromptText("أضف البند");
		TextField prices = new TextField();
		icons(prices);
		prices.setEffect(new DropShadow());
		prices.setPromptText("أضف السعر");
		TextField quantities = new TextField();
		icons(quantities);
		quantities.setEffect(new DropShadow());
		quantities.setPromptText("أضف الكمية");

		ComboBox<String> typeOfPay = new ComboBox<>();
		typeOfPay.setPrefSize(200,20);
		typeOfPay.setStyle("-fx-font-size: 14;");
		typeOfPay.setPromptText("اختر طريقة القبض");
		typeOfPay.getItems().addAll("نقداً","ذمم مدينة");

		icons(typeOfPay);

		Label finalTotal = new Label("صافي المبيعات");
		finalTotal.setPadding(new Insets(7));
		TextField finalTotalt = new TextField();

		IconedTextFieledArb(finalTotal, finalTotalt);

		HBox h4 = new HBox(finalTotalt, finalTotal);
		h4.setAlignment(Pos.CENTER_LEFT);

		Button tr7eel = new Button("حفظ");
		butoonEffect(tr7eel);
		icons(tr7eel);
		tr7eel.setEffect(new DropShadow());

		tr7eel.setOnAction(e ->{
			for (int i = 0; i < Account.getRevenueList().size(); i++) {
				Boolean flag = true,flag2 = true ;
				if(Account.getRevenueList().get(i).getType().equals("ذمم مدينة")) {
					for (int j = 0; j < JournalVoucherdata.size(); j++) {
						if(Account.getRevenueList().get(i).equals(JournalVoucherdata.get(j))) {
							flag2 = false;
						}						
					}
					if(flag2)
						JournalVoucherdata.add(Account.getRevenueList().get(i));
				}
				else if(Account.getRevenueList().get(i).getType().equals("نقداً")) {
					for (int j = 0; j < daily.size(); j++) {
						if(Account.getRevenueList().get(i).equals(daily.get(j))) {
							flag = false;
						}
					}
					if(flag)
						daily.add(Account.getRevenueList().get(i));
				}
			}
			writeFiles();
		});

		VBox v3 = new VBox(10 , h4,tr7eel);
		v3.setAlignment(Pos.CENTER_LEFT);

		Button add = new Button("أضف");
		butoonEffect(add);
		icons(add);
		if(Account.getRevenueList().size() >= 0 ) {
			finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
		}
		table.getItems().clear();
		for (int i = 0; i < Account.getRevenueList().size(); i++) {
			table.getItems().addAll(Account.getRevenueList().get(i));
		}

		typeOfPay.setOnAction(r ->{
			if(typeOfPay.getValue().equals("نقداً")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Revenue revenue = new Revenue(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					revenue.ManageRevenue();
					revenueData.add(revenue);
					Account.getRevenueList().add(revenue);	
					table.getItems().addAll(revenue);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");

				});
			}
			else if(typeOfPay.getValue().equals("ذمم مدينة")) {
				add.setOnAction(e ->{
					String [] x = datet.getText().split("-");
					String type = typeOfPay.getSelectionModel().getSelectedItem();
					Revenue revenue = new Revenue(new Date(((Integer.parseInt(x[2])-1900)), (Integer.parseInt(x[1])-1), Integer.parseInt(x[0])) , namet.getText(),vauchert.getText(),items.getText(),Long.parseLong(quantities.getText()), Integer.parseInt(prices.getText()),type);
					revenue.ManageRevenue();
					revenueData.add(revenue);
					Account.getRevenueList().add(revenue);
					table.getItems().addAll(revenue);
					datet.clear();
					namet.clear();
					vauchert.clear();
					items.clear();
					quantities.clear();
					prices.clear();
					finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
				});
			}
		});


		add.setEffect(new DropShadow());

		Button delete = new Button("إحذف");
		butoonEffect(delete);
		icons(delete);
		delete.setEffect(new DropShadow());

		delete.setOnAction(e ->{
			ObservableList<Revenue> allPuchases , selectedPuchases;
			allPuchases = table.getItems();
			selectedPuchases= table.getSelectionModel().getSelectedItems();	
			for (int i = 0; i < Account.getRevenueList().size(); i++) {
				if(Account.getRevenueList().get(i).equals(table.getSelectionModel().getSelectedItem())) {
					Account.getRevenueList().remove(i);
				}
			}
			for (int i = 0; i < JournalVoucherdata.size(); i++) {
				if(JournalVoucherdata.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					JournalVoucherdata.remove(i);
				}
			}
			for (int i = 0; i < revenueData.size(); i++) {
				if(revenueData.get(i).equals(table.getSelectionModel().getSelectedItem())) {					
					revenueData.remove(i);
				}
			}
			selectedPuchases.forEach(allPuchases::remove);
			finalTotalt.setText(Account.allFinalTotalallFinalTotalRevenue()+"");
		});

		HBox addDelete = new HBox(10,delete,add,items,prices,quantities,typeOfPay);
		addDelete.setAlignment(Pos.CENTER);

		VBox v2 = new VBox(20 , v, table,addDelete,v3);

		table.setStyle("-fx-border-radius: 0 0 0 0;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: silver;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 0 0 0");

		centerPane.setCenter(v2);

		VBox buttons = menueArb(primaryStage);

		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		pane.setRight(buttons);

		ImageView logo = new ImageView(new Image("arsm.png"));
		logo.setFitWidth(200);
		logo.setFitHeight(150);
		pane.setLeft(logo);
		pane.setAlignment(logo, Pos.TOP_CENTER);

		Scene engilshScene = new Scene(pane,1535,800);
		primaryStage.setMaximized(true);
		primaryStage.setScene(engilshScene);
		primaryStage.show();
	}

	public double totalCreditJournalVoucher() {
		double sum =0.0;
		for (int i = 0; i < JournalVoucherdata.size(); i++) {
			sum += JournalVoucherdata.get(i).getFinalTotal();
		}
		return sum;
	}

	public double totalDepitJournalVoucher() {
		double sum =0.0;
		for (int i = 0; i < JournalVoucherdata.size(); i++) {
			sum += JournalVoucherdata.get(i).getFinalTotal();
		}
		return sum;
	}

	public double defferentDepitCredit() {
		double def = totalCreditJournalVoucher() - totalDepitJournalVoucher();
		return Math.abs(def);
	}

	public static void main(String[] args) {
		launch(args);
	}


	private void IconedTextFieled(Node l, Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 50;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 50 50 0");
	}

	private void IconedTextFieledArb(Node l, Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 50;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 0 50 50 0");

		t.setStyle("-fx-border-radius: 50 0 0 50;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 50 0 0 50");
	}

	private void icons(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" +
				"-fx-font-size: 15;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				" -fx-text-fill: #f2f3f4;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 25 25 25 25");
	}

	private void finEffect(Node n,Node t){
		n.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 20;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 20 20 0 0;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 20 20 0 0");

		t.setStyle("-fx-border-radius: 0 0 20 20;\n" +
				"-fx-font-size: 19;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-alignment: CENTER;"+
				"-fx-background-radius: 0 0 20 20");
	}

	private void writeFiles() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("Revenue.txt");
			for (int i = 0; i < Account.getRevenueList().size(); i++) {
				output.println(Account.getRevenueList().get(i).toString());				
			}
			output.close();

		}catch (FileNotFoundException e) {
			System.out.println("| "+ e+ " Error!! : File Revenue.txt doesn't found. ");
		}
		try {
			output = new PrintWriter("Purchases.txt");
			for (int i = 0; i < Account.getPuchasesList().size(); i++) {
				output.println(Account.getPuchasesList().get(i).toString());				
			}
			output.close();	
		}catch (FileNotFoundException e) {
			System.out.println("| "+ e+ " Error!! : File Purchases.txt doesn't found. ");
		}
	}

	private void readFiles() {
		//define files
		File revnueInput = new File("Revenue.txt");
		File purchasesInput = new File("Purchases.txt");
		String tok;
		// TO READ FROM FILES
		try {
			Scanner in = new Scanner(revnueInput);
			while(in.hasNext()) {
				tok = in.nextLine();
				String [] index = tok.split(",");
				String [] date = index[0].split("-");
				Account.addRevenueRead((new Date(((Integer.parseInt(date[2])-1900)),(Integer.parseInt(date[1])-1),Integer.parseInt(date[0]))),
						index[1],index[2],index[3],Long.parseLong(index[4]),Double.parseDouble(index[5]),index[6],Double.parseDouble(index[7]),
						Double.parseDouble(index[8]),Double.parseDouble(index[9]),Double.parseDouble(index[10]));
			}
			in.close();

		}catch (FileNotFoundException e) {
			System.out.println("| "+ e+ "\n| Error!! : File Revenue.txt doesn't found. ");
		}
		try {
			Scanner in = new Scanner(purchasesInput);
			while(in.hasNext()) {
				tok = in.nextLine();
				String [] index = tok.split(",");
				String [] date = index[0].split("-");
				Account.addPuechsesRead((new Date(((Integer.parseInt(date[2])-1900)),(Integer.parseInt(date[1])-1),Integer.parseInt(date[0]))),
						index[1],index[2],index[3],Long.parseLong(index[4]),Double.parseDouble(index[5]),index[6],Double.parseDouble(index[7]),
						Double.parseDouble(index[8]),Double.parseDouble(index[9]),Double.parseDouble(index[10]));
			}
			in.close();
		}catch (FileNotFoundException e) {
			System.out.println("| "+ e+ "\n| Error!! : File Purshases.txt doesn't found. ");
		}
	}


}
