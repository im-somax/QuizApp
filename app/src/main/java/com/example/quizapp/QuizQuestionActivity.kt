package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import java.util.*
import kotlin.collections.ArrayList

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {


    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String ?= null
    private lateinit var countDownTimer : CountDownTimer
    private var timeLeftInMillis: Long = 0
    private val COUNTDOWN_IN_MILLIS: Long = 60000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)


        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun setQuestion(){

        val question = mQuestionList!!.get(mCurrentPosition-1)

        defaultOptionView()

        if(mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progess.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        btn_submit.setOnClickListener(this)
        timeLeftInMillis = COUNTDOWN_IN_MILLIS
        startCountDown()
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3,tv_option_four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.defult_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionList!!.size
                        ->{
                            setQuestion()
                        }else->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME,mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                        startActivity(intent)
                        finish()
                        }
                    }
                }
                else{
                    checkanswer()
                }
            }
        }
    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()
                checkanswer()
            }
        }.start()
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun checkanswer(){
        val question = mQuestionList?.get(mCurrentPosition - 1)
        countDownTimer.cancel()
        if(question!!.correctAnswer != mSelectedOptionPosition){
            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
        }else{
            mCorrectAnswers++
        }
        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "GO TO NEXT QUESTION"
        }

        mSelectedOptionPosition = 0
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeFormatted =
            java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        timer_text.setText(timeFormatted)
        if (timeLeftInMillis < 10000) {
            timer_text.setTextColor(Color.RED)
        } else {
            timer_text.setTextColor(Color.parseColor("#363A43"))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null) {
            countDownTimer.cancel()
        }
    }
}


