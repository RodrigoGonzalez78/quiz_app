package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView

    private lateinit var btOption1: Button
    private lateinit var btOption2: Button
    private lateinit var btOption3: Button
    private lateinit var btOption4: Button

    private lateinit var quizModelArrayList: ArrayList<QuizModel>

    private var currentScore: Int = 0
    private var currentPos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizModelArrayList = ArrayList()
        addQuizQuestion(quizModelArrayList)

        setViews()
        setDataToViews(currentPos)
        setButtonsAction()

    }

    private fun showButtonSheet() {
        val buttonSheetDialog = BottomSheetDialog(this)
        val buttonSheetView: View = LayoutInflater.from(applicationContext)
            .inflate(R.layout.score_bottom_sheet, findViewById(R.id.escoreView))

        val scoreTextView: TextView = buttonSheetView.findViewById(R.id.tv_score)
        val restartButton: Button = buttonSheetView.findViewById(R.id.btn_restart)

        "Tu puntuacion es $currentScore/${quizModelArrayList.size}".also { scoreTextView.text = it }

        restartButton.setOnClickListener {
            currentScore = 0
            currentPos = 0
            setDataToViews(currentPos)
            buttonSheetDialog.dismiss()
        }

        buttonSheetDialog.setCancelable(false)
        buttonSheetDialog.setContentView(buttonSheetView)
        buttonSheetDialog.show()

    }

    private fun setButtonsAction() {
        btOption1.setOnClickListener {
            actionButton(btOption1)
        }
        btOption2.setOnClickListener {
            actionButton(btOption2)
        }
        btOption3.setOnClickListener {
            actionButton(btOption3)
        }
        btOption4.setOnClickListener {
            actionButton(btOption4)
        }
    }

    private fun actionButton(btn: Button) {
        if (quizModelArrayList.get(currentPos).answer.trim() == btn.text.trim()) {
            currentScore++
        }
        currentPos++
        if (currentPos >= quizModelArrayList.size) {
            showButtonSheet()
            return
        }
        setDataToViews(currentPos)
    }

    private fun setDataToViews(currentPos: Int) {
        "Pregunta Numero: ${currentPos + 1} / ${quizModelArrayList.size}".also {
            tvQuestionNumber.text = it
        }

        tvQuestion.text = quizModelArrayList[currentPos].question

        btOption1.text = quizModelArrayList[currentPos].option1
        btOption2.text = quizModelArrayList[currentPos].option2
        btOption3.text = quizModelArrayList[currentPos].option3
        btOption4.text = quizModelArrayList[currentPos].option4

    }

    private fun setViews() {
        //Texviews
        tvQuestionNumber = findViewById(R.id.tv_questionAttempted)
        tvQuestion = findViewById(R.id.tv_question)
        //Buttons
        btOption1 = findViewById(R.id.btn_option1)
        btOption2 = findViewById(R.id.btn_option2)
        btOption3 = findViewById(R.id.btn_option3)
        btOption4 = findViewById(R.id.btn_option4)


    }

    private fun addQuizQuestion(quizList: ArrayList<QuizModel>) {
        quizList.addAll(
            listOf(
                QuizModel(
                    "Cuantos es 3*2+2?",
                    "12",
                    "8",
                    "1",
                    "9",
                    "8"
                ),
                QuizModel(
                    "En que continente esta china:",
                    "Asia",
                    "Europa",
                    "Antartida",
                    "America",
                    "Asia"
                ),
                QuizModel(
                    "Cuantos mundiales gano Argentina?",
                    "1",
                    "2",
                    "3",
                    "5",
                    "3"
                )
            )
        )
    }
}