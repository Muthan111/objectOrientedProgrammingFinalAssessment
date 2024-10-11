package sourceFiles
class MoveResult(number: Int) extends MoveType {
 determinetype(number)
 var movePiece: Piece = _
 def this( movePiece: Piece, number:Int ) = {
  this(0)
  determinetype(number)
  this.movePiece = movePiece
 }
}
