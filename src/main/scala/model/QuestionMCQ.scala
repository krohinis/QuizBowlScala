package model

import questionData.MCQ

import scala.beans.BeanProperty

/**
  * Created by synerzip on 1/3/17.
  */
class QuestionMCQ(mcqObject: MCQ, qtype: String) extends Question(mcqObject.getQuestion, qtype) {

  @BeanProperty var choice: Array[String] = null
  @BeanProperty var answer: String = null


  this.answer = mcqObject.getAnswer
  this.choice = mcqObject.getChoice
  this.setAnswers(this.answer)

}
