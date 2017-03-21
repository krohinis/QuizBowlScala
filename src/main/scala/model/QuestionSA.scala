package model

import questionData.SA

/**
  * Created by synerzip on 1/3/17.
  */
class QuestionSA(saObject: SA, qtype: String) extends Question(saObject.getQuestion, qtype) {

  this.setAnswers(saObject.getAnswer)

}
