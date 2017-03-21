package questionData

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class TF {
  @BeanProperty var question: String = null
  @BeanProperty var answer: String = null
  @BeanProperty var point: Int = 0

  def this(question: String, answer: String, points: Int) {
    this
    this.question = question
    this.answer = answer
    this.point = points
  }


}
