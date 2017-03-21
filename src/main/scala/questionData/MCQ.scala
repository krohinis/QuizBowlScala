package questionData

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class MCQ {
  @BeanProperty var question: String = null
  @BeanProperty var answer: String = null
  @BeanProperty var choice: Array[String] = null
  @BeanProperty var points: Int = 0

  def this(question: String, answer: String, choice: Array[String], points: Int) {
    this
    this.question = question
    this.answer = answer
    this.choice = choice
    this.points = points
  }

}
