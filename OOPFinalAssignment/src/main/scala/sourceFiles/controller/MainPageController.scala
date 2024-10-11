package sourceFiles.controller

import javafx.fxml.FXML
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import sourceFiles.checkersApp.{closeBoard, displayBoard, displayInstructionsPage}
@sfxml
class MainPageController( val buttonToDisplayBoard: Button,  val instructionsButton: Button,  val exitbutton: Button) {
  buttonToDisplayBoard.setOnAction((event) => {
    println("Button clicked!")
    displayBoard()

  });


  instructionsButton.setOnAction((event) => {
    displayInstructionsPage()

  });

  exitbutton.setOnAction((event) => {
    System.exit(0)

  });


}
