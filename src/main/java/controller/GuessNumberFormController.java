package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import dto.NumberGuessAndGenerator;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.Random;

public class GuessNumberFormController {
    Random r = new Random();
    int generatedNumber = r.nextInt(101);
    int buttonClick = 0;
    int points;
    @FXML
    private JFXTextField txtGuess;

    @FXML
    private Label txtOutput;

    @FXML
    private Label txtPoints;

    @FXML
    private Label txtMaxScore;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    void exit(ActionEvent ignoredEvent) {
        System.exit(0);
    }

    @FXML
    void guessTheNumber(ActionEvent ignoredEvent) {
        NumberGuessAndGenerator numberGuessAndGenerator = new NumberGuessAndGenerator();
        while (true) {
            try {
                if (Integer.parseInt(txtGuess.getText()) > 100 || Integer.parseInt(txtGuess.getText()) < 0) {
                    new Alert(Alert.AlertType.ERROR, "Number should be greater than -1 and less than 101!").show();
                }
                points = Integer.parseInt(txtPoints.getText());
                numberGuessAndGenerator.setGuessedNumber(Integer.parseInt(txtGuess.getText()));
                numberGuessAndGenerator.setGeneratedNumber(generatedNumber);
                txtOutput.setTextFill(Color.rgb(255, 255, 255));
                txtOutput.setAlignment(Pos.CENTER);
                buttonClick++;
                if (buttonClick != 6) {
                    checkTheGuessNumber(numberGuessAndGenerator);
                } else {
                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Sorry, you have exhausted 5 trials. The number was " + generatedNumber + ". \nDo you want to retry the game?", ButtonType.YES, ButtonType.NO).showAndWait();
                    if (buttonType.isPresent() && (buttonType.get() == ButtonType.YES)) {
                        boolean bool = false;
                        refreshTheWindow(bool);
                        generatedNumber = r.nextInt(101);
                        buttonClick = 0;
                    }
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "You didn't enter a number!").show();
            }
            break;
        }

    }

    private void checkTheGuessNumber(NumberGuessAndGenerator numberGuessAndGenerator) {
        if (numberGuessAndGenerator.getGuessedNumber() == numberGuessAndGenerator.getGeneratedNumber()) {
            txtOutput.setStyle("-fx-background-color: #347740; -fx-font-weight: BOLD");
            txtOutput.setText("Great! You guessed the number! Now, try to guess the next one.");
            generatedNumber = r.nextInt(101);
            points = points + 3;
            txtPoints.setText(String.valueOf(points));
            txtMaxScore.setText(String.valueOf(points));
            buttonClick = 0;
        } else {
            txtOutput.setStyle("-fx-background-color: #e35c5c; -fx-font-weight: BOLD");
            if (numberGuessAndGenerator.getGeneratedNumber() > numberGuessAndGenerator.getGuessedNumber()) {
                txtOutput.setText("The number you tried to guess is too low. Please try again.");
            } else {
                txtOutput.setText("The number you tried to guess is too high. Please try again.");
            }
        }
    }

    @FXML
    void refreshTheWindow(ActionEvent ignoredEvent) {
        boolean b = btnRefresh.isHover();
        System.out.println(b);
        refreshTheWindow(b);
    }

    private void refreshTheWindow(boolean isPressed) {
        if(!isPressed){
            txtPoints.setText("0");
            txtGuess.setText("");
            txtOutput.setStyle("-fx-background-color: #fff; -fx-font-weight: BOLD");
            txtOutput.setText("");
        }
    }
}
