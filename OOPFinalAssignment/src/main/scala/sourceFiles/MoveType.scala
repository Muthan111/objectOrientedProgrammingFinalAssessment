package sourceFiles

 trait MoveType{
  var type1: String = "None"
  var type2: String = "Normal"
  var type3: String = "kill"
  var selectType: String = null
  def determinetype(number: Int): Unit = {
     if(number == 1){
      selectType = type1
      return selectType
     }
     else if(number == 2){
      selectType = type2

     }
     else if(number == 3){
      selectType = type3
       return selectType
     }
     else{
      selectType = null
     }
     return selectType
  }
}
