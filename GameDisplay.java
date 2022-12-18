import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameDisplay extends Application{
	private GameState g1 = new GameState();
	private boolean winner = false;
	private Image[] uno = new Image[108];
	private TextField title = new TextField();
	public void start(Stage primaryStage) {
		//Creates an array with all UNO cards in it
		for(int i = 0; i < 108; i++) {
			uno[i] = new Image("UNO/img" + i + ".jpg");
		}
		//Creates basic GridPane
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, 800, 1200);
		//Creates and applies buttons
		Button btStart = new Button("Start");
		Button btNext = new Button("Next Turn");
		pane.add(btStart, 0, 2);
		//Sets gaps
		pane.setHgap(5);
		pane.setVgap(5);
		//Sets button actions
		btStart.setOnAction(e -> {
			//Replaces the start button with the next button and performs initial setup
			pane.getChildren().remove(btStart);
			pane.add(btNext, 0, 2);
			startGame();
			pane.add(new ImageView(uno[g1.getCard()]), 0, 1);
			pane.add(title, 0, 0);
			title.setText("Starting Card");
		});
		btNext.setOnAction(e -> {
			nextTurn();
			pane.add(new ImageView(uno[g1.getCard()]), 0, 1);
			//Checks if there is a winner and fixes a bug for skip cards
			if(winner == true) {
				title.setText("Player " + g1.getTurn() + " wins!");
				pane.getChildren().remove(btNext);
			}
			else if(g1.drew() == true) {
				title.setText("Player " + g1.getTurn() + " drew a card");
			}
			else if(g1.getNumber(g1.getCard()) == 10) {
				if(g1.getOrder() == true) {
					if (g1.getTurn()>1){
						title.setText("Player " + (g1.getTurn() - 1) + " played:");
				    }
				    else{
				    	title.setText("Player 4 played:");
				    }
				}
				else{
				    if(g1.getTurn()<4){
				    	title.setText("Player " + (g1.getTurn() + 1) + " played:");
				    }
				    else{
				    	title.setText("Player 1 played:");
				    }
				}
			}
			else {
				title.setText("Player " + g1.getTurn() + " played:");
			}
		});
		//Constructs and displays scene
		primaryStage.setTitle("0 Player Uno");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//Performs initial setup
	public void startGame() {
		g1.gameStart();
	}
	//Progresses the turn
	public void nextTurn() {
		g1.skip();
		g1.checkSpecial();
		g1.playableCard(g1.getPlayer());
		winner = g1.checkWinner();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}