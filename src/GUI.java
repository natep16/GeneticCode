import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUI extends Application {
    private GeneticCode code;
    private Stage stage;
    private File codeFile;
    @FXML
    private Text dnaString;
    @FXML
    private Text rnaString;
    @FXML
    Text dnaAFreq,dnaCFreq,dnaGFreq,dnaTFreq;
    @FXML
    Text rnaAFreq,rnaCFreq,rnaGFreq,rnaUFreq;
    @FXML
    private Button button;
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("gui.fxml")));
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @FXML
    public void chooseFile(){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        codeFile = chooser.showOpenDialog(stage);
        button.setText("Analyze");


    }
    @FXML
    public void analyzeCode() throws FileNotFoundException {
        if(codeFile == null){
            button.setText("Seriously, Choose a File First");
        }
        else{
            double progress = 0;
            Scanner in = new Scanner(codeFile);
            StringBuilder seq = new StringBuilder();
            while (in.hasNext()) {
                seq.append(in.next().toUpperCase());
                progress++;

            }
            in.close();
            code = new GeneticCode(seq.toString());
            dnaCFreq.setText(Integer.toString(code.getDnaBases()[0]));
            dnaGFreq.setText(Integer.toString(code.getDnaBases()[1]));
            dnaAFreq.setText(Integer.toString(code.getDnaBases()[2]));
            dnaTFreq.setText(Integer.toString(code.getDnaBases()[3]));

            rnaCFreq.setText(Integer.toString(code.getRnaBases()[0]));
            rnaGFreq.setText(Integer.toString(code.getRnaBases()[1]));
            rnaAFreq.setText(Integer.toString(code.getRnaBases()[2]));
            rnaUFreq.setText(Integer.toString(code.getRnaBases()[3]));

            dnaString.setText(code.getDna());
            rnaString.setText(code.getRna());

        }
    }

}
