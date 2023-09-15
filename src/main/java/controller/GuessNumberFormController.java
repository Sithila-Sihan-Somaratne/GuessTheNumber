package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import dto.NumberGuessAndGenerator;
import javafx.scene.paint.Color;

import java.util.Random;

public class GuessNumberFormController {

    @FXML
    private JFXTextField txtGuess;

    @FXML
    private Label txtOutput;

    @FXML
    void exit(ActionEvent ignoredEvent) {
        System.exit(0);
    }

    @FXML
    void guessTheNumber(ActionEvent ignoredEvent) {
        Random r = new Random(101);
        int generatedNumber = r.nextInt();
        NumberGuessAndGenerator numberGuessAndGenerator = new NumberGuessAndGenerator();
        try{
            numberGuessAndGenerator.setGuessedNumber(Integer.parseInt(txtGuess.getText()));
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"You didn't enter a number!").show();
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
        numberGuessAndGenerator.setGeneratedNumber(generatedNumber);
        txtOutput.setTextFill(Color.rgb(255,255,255));
        txtOutput.setAlignment(Pos.CENTER);
        if(numberGuessAndGenerator.getGuessedNumber() != numberGuessAndGenerator.getGeneratedNumber()){
            txtOutput.setStyle("-fx-background-color: #e35c5c; -fx-font-weight: BOLD");
            if(numberGuessAndGenerator.getGeneratedNumber() > numberGuessAndGenerator.getGuessedNumber()){
                txtOutput.setText("The number you tried to guess is too high. Please try again.");
            }else {
                txtOutput.setText("The number you tried to guess is too low. Please try again.");
            }
        }else{
            txtOutput.setStyle("-fx-background-color: #347740; -fx-font-weight: BOLD");
            txtOutput.setText("Great! You guessed the number!");
        }
    }

    @FXML
    void refreshTheWindow(ActionEvent ignoredEvent) {
        txtGuess.setText("");
        txtOutput.setText("");
    }

}
