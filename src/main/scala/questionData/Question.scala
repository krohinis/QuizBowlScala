package questionData

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class Question {
  @BeanProperty var question: Any = null
  @BeanProperty var questionType: String = null

  def this(question: Any, questionType: String) {
    this()
    this.question = question
    this.questionType = questionType
  }

}
