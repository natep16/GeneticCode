import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
public class GUI extends Application {
    Button btn;
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage){
        btn = new Button();
        btn.setText("Analyze Genetic Code");
        BorderPane pane = new BorderPane();
        pane.setCenter(btn);
        btn.setOnAction(e -> buttonClick());
        Scene scene = new Scene(pane,480,720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Genetic Code App");
        primaryStage.show();

    }
    public void buttonClick(){
        if(btn.getText().equals("Click me Please")){
            btn.setText("You clicked me!");
        }
        else{
            btn.setText("Click me Please");
        }
    }

}
