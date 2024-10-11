package sourceFiles

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class Tile() extends Rectangle {
   var piece = new Piece()
  var mypiece = new Piece()

  def isTileEmpty(): Boolean = {
    piece == null
  }
  def isTileOccupied(): Boolean = {
    piece != null
  }

  def this(color: Boolean, x: Int, y: Int) = {
    this()
    width = checkersApp.tileSize
    height = checkersApp.tileSize
    relocate(x * checkersApp.tileSize, y * checkersApp.tileSize+31)
    fill = if (color) Color.White else Color.DarkGray
  }

  def setPiece(piece: Piece): Unit = {
    this.piece = piece
  }


}