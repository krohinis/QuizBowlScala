package model

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class Question {

  @BeanProperty var question: String = null
  @BeanProperty var questionType: String = null
  @BeanProperty var answers: String = null
  @BeanProperty var points: Int = 0

  def this(question: String, questionType: String) {
    this
    this.question = question
    this.questionType = questionType
  }

  /**
    * Checks the answer & to calculate the scores
    *
    * @param answer - answer entered by user
    * @param points - points assigned to that question
    * @return boolean value
    */
  def checkAnswer(answer: String, points: Int): Boolean = {
    if (answer == this.answers) {
      this.points = points
      true
    }
    else {
      this.points = this.points - points
      false
    }
  }
}

