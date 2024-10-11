
package sourceFiles.controller


import javafx.fxml.FXML
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label, TextArea}
import scalafx.scene.layout.VBox
import scalafxml.core.macros.sfxml
import sourceFiles.checkersApp.displayMainPage
import scala.io.Source
import java.beans.EventHandler

@sfxml
class InstructionsController(
                              private var button1: Button,
                              private var textbox: Label

                            ) {





  def initialize(): Unit = {
    // Read the entire content of the file
    val source = Source.fromFile("src/main/resources/HowToPlay.txt")
    val scalaFileContents = source.getLines()
    val fileContents = scalaFileContents.mkString("\n")
    source.close()

    // Set the text in the TextArea from the message variable and file contents
    textbox.setText( "\n\n" + fileContents)
  }

  initialize()
  button1.setOnAction((event) => {
    displayMainPage()
  });
}
