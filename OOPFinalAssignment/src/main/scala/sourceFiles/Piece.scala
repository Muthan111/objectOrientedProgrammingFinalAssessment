package sourceFiles

import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.StackPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Ellipse
import scalafx.Includes._

class Piece() extends StackPane {
  private var mouseForWidth: Double = _
  private var mouseForHeight: Double = _
   var oldPieceWidth: Double = _
   var oldPieceHeight: Double = _
   var pieceType: PieceType = null
  private var color: Int = _

  def this(pieceType: PieceType) = {
    this()
    this.pieceType = pieceType
  }

  def this(pieceType: PieceType, pieceColor: Int, num1: Int, num2: Int) = {
    this()
    this.color = pieceColor
    move(num1, num2)
    pieceType.determinedirectionToMove(pieceColor)
    val piecebackground: Ellipse = new Ellipse {
      radiusX = checkersApp.tileSize * 0.3125
      radiusY = checkersApp.tileSize * 0.26
    }

    if (pieceColor == 1) {
      piecebackground.fill = Color.White
    } else {
      piecebackground.fill = Color.Black
    }

    relocate(num1 * checkersApp.tileSize, num2 * checkersApp.tileSize + 21)
    piecebackground.stroke = Color.Black
    piecebackground.strokeWidth = checkersApp.tileSize * 0.03
    piecebackground.translateX = (checkersApp.tileSize - checkersApp.tileSize * 0.3125 * 2) / 2
    piecebackground.translateY = (checkersApp.tileSize - checkersApp.tileSize * 0.26 * 2) / 2 + checkersApp.tileSize * 0.07

    val piece2background: Ellipse = new Ellipse {
      radiusX = checkersApp.tileSize * 0.3125
      radiusY = checkersApp.tileSize * 0.26
    }

    if (pieceColor == -1) {
      piece2background.fill = Color.Black
    } else {
      piece2background.fill = Color.White
    }

    piece2background.stroke = Color.Black
    piece2background.strokeWidth = checkersApp.tileSize * 0.03
    piece2background.translateX = (checkersApp.tileSize - checkersApp.tileSize * 0.3125 * 2) / 2
    piece2background.translateY = (checkersApp.tileSize - checkersApp.tileSize * 0.26 * 2) / 2
    this.getChildren.addAll(piecebackground, piece2background)
  }

  this.onMousePressed = (event: MouseEvent) => {

    mouseForWidth = event.getSceneX
    mouseForHeight = event.getSceneY
    mouseForHeight = mouseForHeight + 21
  }

  this.onMouseDragged = (event: MouseEvent) => {
    val offsetX = event.getSceneX - mouseForWidth
    val offsetY = event.getSceneY - (mouseForHeight)
    this.relocate(oldPieceWidth + offsetX, oldPieceHeight + offsetY)
  }

  def move(width: Int, height: Int) = {
    oldPieceWidth = width * checkersApp.tileSize
    oldPieceHeight = height * checkersApp.tileSize
    relocate(oldPieceWidth, oldPieceHeight)
  }

  def stopMove() = {
    relocate(oldPieceWidth, oldPieceHeight)
  }

}