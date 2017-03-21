package model

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class Player {
  @BeanProperty var lname: String = null
  @BeanProperty var fname: String = null
  var scores: Int = 0

  def this(lname: String, fname: String) {
    this()
    this.lname = lname
    this.fname = fname
    this.scores = 0
  }

  def getScores(): Int = {
    scores
  }

  def setScores(scores: Int): Unit = {
    this.scores = this.scores + scores
  }
}
