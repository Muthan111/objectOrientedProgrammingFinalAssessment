package sourceFiles
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.scene.input.MouseEvent
import scala.util.control.Breaks._
object checkersApp extends JFXApp {
  //  Global Variables Initialized
  private val sizeOfTile: Int = 100
  private val heightOfTile: Int = 8
  private val widthOfTile: Int = 8
  def tileSize = sizeOfTile
  def height  = heightOfTile
  def width  = widthOfTile
  val boardTile: Array[Array[Tile]] = Array.ofDim[Tile](width, height)
  var groupForTile: Group = null
  var groupForPiece: Group = null
  var tile: Tile = null
  var newpieceType: PieceType = null
  var mypiece: Piece = null

  val rootResource = getClass.getResource("/FXML_files/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)

  // Load root layout from fxml file.
  loader.load();
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  stage = new PrimaryStage {
    title = "CheckerStorm"
    scene = new Scene {
      root = roots
      stylesheets = Seq(getClass.getResource("/FXML_files/CheckersTheme.css").toString)

    }
  }

  //  Function to Display main page
  def displayMainPage() = {
    // Calling MainPage.fxml file
    val mainPageResource = getClass.getResource("/FXML_files/MainPage.fxml")
    if (mainPageResource == null) {
      throw new RuntimeException("Cannot load resource: /FXML_files/MainPage.fxml")
    }
    val mainPageloader = new FXMLLoader(mainPageResource, NoDependencyResolver)
    mainPageloader.load()
    val mainPageRoots = mainPageloader.getRoot[jfxs.layout.AnchorPane]
    if (mainPageRoots == null) {
      throw new RuntimeException("Root layout could not be loaded from FXML")
    }
    this.roots.setCenter(mainPageRoots)
    this.roots.setPrefSize(tileSize * width, tileSize * height + 30)
  }
//  displayMainPage()
  //  function to display instructions
  def displayInstructionsPage() = {
    val InstructionsResource = getClass.getResource("/FXML_files/instructions.fxml")
    if (InstructionsResource == null) {
      throw new RuntimeException("Cannot load resource: /FXML_files/instructions.fxml")
    }

    val Instructionsloader = new FXMLLoader(InstructionsResource, NoDependencyResolver)
    val InstructionsRoots = Instructionsloader.load().asInstanceOf[jfxs.layout.AnchorPane]

    if (InstructionsRoots == null) {
      throw new RuntimeException("Root layout could not be loaded from FXML")
    }

    this.roots.setCenter(InstructionsRoots)
    this.roots.setPrefSize(tileSize * width, tileSize * height + 30)
  }

  // Ensure the method is called within a valid scope

    displayMainPage()
  //   valueOfHeight is y and valueOfWidth is x

//  displayInstructionsPage()

  def conversionForBoard(pixel: Double): Int = {
    ((pixel + tileSize / 2) / tileSize).toInt
  }

  def attemptMove(pieceType: PieceType, piece: Piece, newX: Int, newY: Int): MoveResult = {
    val x0 = conversionForBoard(piece.oldPieceWidth)
    val y0 = conversionForBoard(piece.oldPieceHeight)


    // Check for normal move
    if (!(Math.abs(newX - x0) == 0 && newY - y0 == piece.pieceType.directionToMove)) {

       new MoveResult(2)

    }
    else if (Math.abs(newX - x0) == 2 && newY - y0 == pieceType.directionToMove * 2) {
      val x1 = x0 + (newX - x0) / 2
      val y1 = y0 + (newY - y0) / 2
      if (boardTile(x1)(y1).isTileOccupied() && boardTile(x1)(y1).mypiece.pieceType != pieceType.piece1) {
        return new MoveResult(boardTile(x1)(y1).mypiece, 3) // Capture move
      } else {
        new MoveResult(2)
      }
    }
    // Check for normal move
//    if (Math.abs(newX - x0) == 1 && newY - y0 == pieceType.directionToMove) {
//      return new MoveResult(2)
//    }
    // Check for capture move

//    }else{
//      return new MoveResult(2)
//    }
//    new MoveResult(2)

    // Invalid move
    new MoveResult(2)
  }
  var otherPiece: Piece = null
  def createPiece(pieceType: PieceType, pieceColor: Int, x: Int, y: Int): Piece = {
    val piece = new Piece(pieceType, pieceColor, x, y)
    piece.onMouseReleased = (event: MouseEvent) => {
      val newX: Int = conversionForBoard(piece.getLayoutX)
      val newY: Int = conversionForBoard(piece.getLayoutY)
      val x0: Int = conversionForBoard(piece.oldPieceWidth)
      val y0: Int = conversionForBoard(piece.oldPieceHeight)
      var result: MoveResult = null
      if (newX < 0 || newY < 0 || newX >= width || newY >= height || !boardTile(x0)(y0).isTileOccupied() == true || (newX + newY) % 2 == 0) {
        result = new MoveResult(1)
      }

//      else if (boardTile(newX)(newY).isTileEmpty() == false) {
//        result = new MoveResult(1)
//      }
       else {
        result = attemptMove(pieceType, piece, newX, newY)
      }



      breakable  {
        var x : String = result.selectType
        x match{
          case "None" =>
            println("Invalid Move")
            piece.stopMove()
            println(boardTile(newX)(newY).isTileOccupied())
            println(boardTile(x0)(y0).isTileOccupied())
          case "Normal" =>
            println(boardTile(newX)(newY).isTileEmpty())

            piece.move(newX, newY)
            boardTile(x0)(y0).setPiece(null)
            boardTile(newX)(newY).setPiece(piece)
            println(s"newX: $newX, newY: $newY, x0: $x0, y0: $y0, directionToMove: ${pieceType.directionToMove}")
            println(boardTile(newX)(newY).isTileOccupied())
            println(boardTile(x0)(y0).isTileOccupied())






        }
      }

    }
//
    piece
  }


  // Function to display board
  def displayBoard() = {
    val boardResource = {
      getClass.getResource("/FXML_files/checkerBoard.fxml")
    }
    val boardLoader = new FXMLLoader(boardResource, NoDependencyResolver)
    boardLoader.load();

    val boardRoots = boardLoader.getRoot[jfxs.layout.Pane]
    this.roots.setCenter(boardRoots)

    this.roots.setPrefSize(tileSize * width, tileSize * height)
    groupForTile = new Group();
    groupForPiece = new Group();
    this.roots.getChildren.addAll(groupForTile, groupForPiece)


    //
    for (valueOfHeight <- 0 until height) {
      for (valueOfWidth <- 0 until width) {
        tile = new Tile((valueOfHeight + valueOfWidth) % 2 == 0, valueOfWidth, valueOfHeight)
        groupForTile.getChildren().add(tile)
        boardTile(valueOfWidth)(valueOfHeight) = tile
        newpieceType = new PieceType()

        if (valueOfHeight <= 2 && (valueOfHeight + valueOfWidth) % 2 != 0) {
          mypiece = createPiece(newpieceType, newpieceType.piece2, valueOfWidth, valueOfHeight)
          tile.setPiece(mypiece)
          groupForPiece.getChildren().add(mypiece)
        }
        if (valueOfHeight >= 5 && (valueOfHeight + valueOfWidth) % 2 != 0) {
          val mypiece = createPiece(newpieceType, newpieceType.piece1, valueOfWidth, valueOfHeight)
          tile.setPiece(mypiece)
          groupForPiece.getChildren().add(mypiece)


        }
      }
    }


  }

//    displayBoard()
  //function to close the board
  def closeBoard(): Unit = {
    displayMainPage()
    groupForTile.getChildren.clear()
    groupForPiece.getChildren.clear()
  }

}
