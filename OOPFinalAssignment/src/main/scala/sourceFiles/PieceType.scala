package sourceFiles
import scala.collection.mutable.Map

case class PieceType() {
  var piece1: Int = 1
  var piece2: Int = -1

  var directionToMove: Int = _

  def determinedirectionToMove(selected: Int): Unit = {
    if (selected == piece1) {
      directionToMove = 1
    } else {
      directionToMove = -1
    }
    return directionToMove
  }

  def this(moveDirection: Int) = {
    this()
    directionToMove = moveDirection
  }


}
