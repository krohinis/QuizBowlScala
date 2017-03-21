package model

import questionData.TF

/**
  * Created by synerzip on 1/3/17.
  */
class QuestionTF(tfObject: TF, var qtype: String) extends Question(tfObject.getQuestion, qtype) {
  this.setAnswers(tfObject.getAnswer)
}
