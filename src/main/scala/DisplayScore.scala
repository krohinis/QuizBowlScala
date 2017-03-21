import model.Player

/**
  * Created by synerzip on 1/3/17.
  */
class DisplayScore {
  /**
    * Displays the Player Name & the Score gained by player
    *
    * @param playerObject
    */
  def showScore(playerObject: Player) {
    println("\n" + playerObject.getFname + " " + playerObject.getLname + ", your game is over!" + "\nYou final score is " + playerObject.getScores + " Points \nBetter luck next time!")

  }
}



