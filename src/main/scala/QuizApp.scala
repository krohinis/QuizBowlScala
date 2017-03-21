import java.io.{BufferedReader, InputStreamReader}

import controller.QuizController
import model.{Player, QuestionMCQ, QuestionSA, QuestionTF}
import questionData.{MCQ, Question, SA, TF}

/**
  * Created by synerzip on 1/3/17.
  */

object QuizApp {
  var questionNum: Int = 0
  var br: BufferedReader = null
  var quizController = new QuizController
  var player: Player = null

  /**
    * Main Defination to start the Application
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    getInput
  }

  /**
    * To take input from user & to initialize the game
    */
  def getInput {
    br = new BufferedReader(new InputStreamReader(System.in))
    print("First Name : ")
    val fname: String = br.readLine.toUpperCase
    print("Last Name : ")
    val lname: String = br.readLine.toUpperCase
    print("Filename : ")
    val filename: String = br.readLine.toLowerCase
    initializeGame(fname, lname, filename)
  }

  /**
    * Assigns the player name & loads the questions
    *
    * @param fname    - players first name
    * @param lname    - players last name
    * @param filename - input file name
    */
  def initializeGame(fname: String, lname: String, filename: String) {
    player = new Player(lname, fname)
    quizController.getFile(filename)
    if (getNumberOfQuestions) quizBowl
  }

  /**
    * Take question count from user which he/she wants to attend
    *
    * @return
    */
  private def getNumberOfQuestions: Boolean = {
    var flag: Boolean = false
    do {
      print("How many questions would you like (out of " + quizController.getQuestionCount + " ) ? : ")
      val number: Int = br.readLine.toInt
      if (quizController.getQuestionCount >= number) {
        questionNum = number
        flag = false
      }
      else {
        flag = true
        println("Question count must be less than " + quizController.getQuestionCount)
      }
    } while (flag)
    true
  }

  /**
    * Checks users answers with the answers saved in file & to set the score
    */
  def quizBowl {
    val sa: String = "SA"
    val mc: String = "MC"
    val tf: String = "TF"
    var object1: Question = null
    while (questionNum > 0) {
      object1 = quizController.getRandomQuestion
      println(object1.getQuestionType)
      object1.getQuestionType match {
        case "TF" =>
          checkTFQuestion(object1)
        case "MC" =>
          checkMCQquestion(object1)
        case "SA" =>
          checkSAQuestion(object1)
        case _ =>
          println("Wrong choice")
      }
      questionNum -= 1
    }
    val displayScore = new DisplayScore
    displayScore.showScore(player)
  }

  /**
    * Display the question to user & call the check function which checks answer & assign the points
    *
    * @param object1 - randomly generated Question
    */
  def checkTFQuestion(object1: Question) {
    val tfData: TF = object1.getQuestion.asInstanceOf[TF]
    val tfobj: model.Question = new QuestionTF(tfData, "TF")
    println("Points : " + object1.getQuestion.asInstanceOf[TF].getPoint + "\n" + tfobj.getQuestion)
    checkAnswerAndSetPoint(tfData.getPoint, tfobj)
  }

  /**
    * Display the question to user & call the check function which checks answer & assign the points
    *
    * @param object1 - randomly generated Question
    */
  def checkMCQquestion(object1: Question) {
    val mcqData: MCQ = object1.getQuestion.asInstanceOf[MCQ]
    val mcqobj: model.Question = new QuestionMCQ(mcqData, "MCQ")
    println("Points : " + object1.getQuestion.asInstanceOf[MCQ].getPoints + "\n" + mcqobj.getQuestion)
    val choices: Array[String] = mcqData.getChoice
    for (chval <- choices)
      println(chval)
    checkAnswerAndSetPoint(mcqData.getPoints, mcqobj)
  }

  /**
    * Check answers & to set the points
    *
    * @param points      - points assigned for that question
    * @param questionObj - type of question & the actual question
    */
  def checkAnswerAndSetPoint(points: Int, questionObj: model.Question) {
    var answer: String = null
    answer = br.readLine.toUpperCase
    if (answer == "SKIP") {
      println("You have elected to skip that question.")
      return
    }
    if (questionObj.checkAnswer(answer, points)) {
      player.setScores(questionObj.getPoints)
      println("Correct Answer.. \nPoints Gain : " + questionObj.getPoints + "\n")
    }
    else {
      player.setScores(questionObj.getPoints)
      println("Wrong Answer.. \nPoints Lose : " + questionObj.getPoints + "\n")
    }
  }

  /**
    * Display the question to user & call the check function which checks answer & assign the points
    *
    * @param object1 - randomly generated Question
    */
  def checkSAQuestion(object1: Question) {
    val saData: SA = object1.getQuestion.asInstanceOf[SA]
    val saobj: model.Question = new QuestionSA(saData, "SA")
    println("Points : " + object1.getQuestion.asInstanceOf[SA].getPoint + "\n" + saobj.getQuestion)
    checkAnswerAndSetPoint(saData.getPoint, saobj)
  }
}
