package controller

import java.io.{BufferedReader, File, FileReader}
import java.net.URL
import java.util.Random

import questionData.{MCQ, Question, SA, TF}

import scala.collection.mutable.ListBuffer

/**
  * Created by synerzip on 1/3/17.
  */
class QuizController {
  var obj = new ListBuffer[Question]
  var randomNum: Array[Int] = _

  /**
    * Opens the input file
    *
    * @param filename - Name of the input file which contains questions & answers
    */
   def getFile(filename: String): Unit =
  {
      var file: File=null
      val resource: URL = getClass.getClassLoader.getResource(filename + ".txt")
      val filenameTemp: String = resource.getFile
      file= new File(filenameTemp)
      val fr: FileReader = new FileReader(file)
      val br: BufferedReader = new BufferedReader(fr)
      obj = openFile(br)
      randomNum = new Array[Int](obj.size)
  }

  /**
    * Open & reads the data from file & also puts that data with respect to their objects
    *
    * @param br - object of BufferReader used for the input file
    * @return a List of questions objects
    */
  def openFile(br: BufferedReader): ListBuffer[Question] = {
    val data = new ListBuffer[Question]
    val TrueFalse: String = "TF"
    val MultipleChoice: String = "MC"
    val ShortAnswer: String = "SA"

    var number: Int = br.readLine.toInt
    while (number > 0) {
      val choice: Array[String] = br.readLine.split(" ")
      val points: Int = choice(1).toInt
      val question: String = br.readLine
      var obj: Question = null
      choice(0) match {
        case TrueFalse =>
          val answertf: String = br.readLine.toUpperCase
          val tfData: TF = new TF(question, answertf, points)
          obj = new Question(tfData, TrueFalse)
          data += obj
        case MultipleChoice =>
          var ChoiceNumber: Int = br.readLine.toInt
          val choices: Array[String] = new Array[String](ChoiceNumber)
          var i: Int = 0
          var ch: Int = 'A'
            while (ChoiceNumber > 0) {
              choices(i) = ch.toChar + ") " + br.readLine
              ch += 1
              i += 1
              ChoiceNumber-=1
            }
          val answerMCQ: String = br.readLine.toUpperCase
          val mcqData: MCQ = new MCQ(question, answerMCQ, choices, points)
          obj = new Question(mcqData, MultipleChoice)
          data += obj
        case ShortAnswer =>
          val answerSA: String = br.readLine.toUpperCase
          val saData: SA = new SA(question, answerSA, points)
          obj = new Question(saData, ShortAnswer)
          data += obj
        case _ =>
          println("Wrong Choice.")
      }
      number -= 1
    }
    data
  }

  /**
    * Calculates the size of the list buffer to know the count of question
    *
    * @return - count of  questions stored in ListBuffer
    */
  def getQuestionCount: Int = this.obj.size

  /**
    * Generates the random number to get the random questions
    * @return a randomly generated question
    */
  def getRandomQuestion: Question = {
    val random: Random = new Random
    var object1: Question = null
    var flag: Int = 0
    do {
      val temp: Int = random.nextInt(obj.size)
      if (randomNum(temp) == 0) {
        object1 = obj(temp)
        randomNum(temp) = 1
        return object1
      }
      else flag = 1
    } while (flag == 1)
    object1
  }
}


