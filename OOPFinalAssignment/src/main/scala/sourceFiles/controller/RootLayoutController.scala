package sourceFiles.controller
import sourceFiles.checkersApp.{closeBoard, displayInstructionsPage, displayMainPage, getClass, groupForPiece, groupForTile, stage}
import scalafx.event.ActionEvent
import scalafx.scene.control.MenuItem
import scalafxml.core.macros.sfxml
@sfxml
class RootLayoutController(menuitem1: MenuItem, menuitem2: MenuItem, menuitem3: MenuItem) {

  def clearBoard(): Unit = {
    displayMainPage()
    groupForTile.getChildren.clear()
    groupForPiece.getChildren.clear()

  }

  def exitButton(): Unit = {
    System.exit(0)

  }
  menuitem1.setOnAction((event) => {
    clearBoard()
  });
  menuitem2.setOnAction((event) => {
    exitButton()
  });
  menuitem3.setOnAction((event) => {
    displayInstructionsPage()
    groupForTile.getChildren.clear()
    groupForPiece.getChildren.clear()
  });
}