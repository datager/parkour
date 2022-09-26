package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button

    private val isAnswerShowedKey = "isAnswerShowedKey"

    private val quizViewModel: QuizViewModel by lazy { ViewModelProvider(this)[QuizViewModel::class.java] }
    private val cheatViewModel: CheatViewModel by lazy { ViewModelProvider(this)[CheatViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            cheatViewModel.isAnswerShown = savedInstanceState.getBoolean(isAnswerShowedKey, false)
        }

        val savedCurrentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        val quizViewModel = ViewModelProviders.of(this)[QuizViewModel::class.java]
        quizViewModel.currentIndex = savedCurrentIndex
        Log.d(TAG, "Get a QuizViewModel: $quizViewModel")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        prevButton.setOnClickListener {
            updateQuestion(-1)
        }
        nextButton.setOnClickListener {
            updateQuestion(1)
        }
        questionTextView.setOnClickListener {
            updateQuestion(1)
        }
        questionTextView.setText(quizViewModel.currentQuestionText)
        cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
//            val intent = Intent(this, CheatActivity::class.java)
//            startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
    }

    // step 步长
    private fun updateQuestion(step: Int) {
        quizViewModel.setCurrentQuestionAnswered()
        val sz = quizViewModel.questionBank.size
        var i = 0
        var idx = quizViewModel.currentIndex
        while (i < sz) {
            i++
            idx = (idx + step + sz) % sz
            if (!quizViewModel.questionBank[idx].answered) { // 未答过，符合要求
                break
            }
        }
        if (idx == quizViewModel.currentIndex) {
            Toast.makeText(this, "您已答完所有题，无题可答了！", Toast.LENGTH_LONG).show()
        } else {
            quizViewModel.currentIndex = idx
            val questionTextResId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextResId)
            var answered = 0 // 已回答的题目数量
            for (q in quizViewModel.questionBank) {
                if (q.answered) {
                    answered++
                }
            }
            Toast.makeText(this, "您已回答了 $answered/$sz 个题目", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            cheatViewModel.isAnswerShown -> R.string.judgment_toast
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        val t = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        t.setGravity(Gravity.TOP, 0, 0)
        t.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}