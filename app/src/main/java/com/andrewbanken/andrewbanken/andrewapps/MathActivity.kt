package com.andrewbanken.andrewbanken.andrewapps
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_1_mathgame.*


class MathActivity :  AppCompatActivity(){
  override  fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.layout_1_mathgame)
         supportActionBar!!.title ="Math Game"
         supportActionBar!!.setDisplayHomeAsUpEnabled(true)

         val fab = findViewById<View>(R.id.fab) as FloatingActionButton
         fab.show()
         fab.setOnClickListener { view ->
             Snackbar.make(view, "This takes you back to the Home Menu", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show()

             val intent = Intent(this@MathActivity,MainActivity::class.java)

             startActivity(intent)

             finish()
         }




        val  HighScores =  findViewById<TextView>(R.id.HighScores)
         val number1 = findViewById<TextView>(R.id.number1)
         val number2 = findViewById<TextView>(R.id.number2)
         val operatorSign = findViewById<TextView>(R.id.operatorSign)
         val answerBtn = findViewById<Button>(R.id.answerBtn)
         val NewGameBtnAfterLosing = findViewById<Button>(R.id.NewGameBtn)
         val answer = findViewById<EditText>(R.id.answer)
         val incorrectAnswers = findViewById<TextView>(R.id.incorrectAnswers)
         val scores = findViewById<TextView>(R.id.scores)
         var start1 = (0..15).shuffled().last()
         var start2 = (0..15).shuffled().last()
         var sum = 0
         var strike = 0
         var score = 0
         var c = 0

         number1.text = "" + start1
         number2.text = "" + start2
         answerBtn.text = "Submit Answer"
         operatorSign.text = "+"
        retrieveData()
         answerBtn.setOnClickListener{
             answer.hint = ""
             answerBtn.text = "Submit Answer"
             if (answer.text.length <= 0) {
                 //EditText is empty

                 Toast.makeText(this, "Input error, Answer Field empty", Toast.LENGTH_SHORT).show()
                 answer.setText("")
                 answer.text= answer.editableText

             } else {
                 //EditText is not empty
                 answer.text= answer.editableText

                 if (strike != 4) {
                     if (c == 0) {


                         sum= start1 + start2

                         if (answer.text.toString().toInt().equals(sum)) {
                             Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                             score++

                             scores.text = "" + score
                             answer.setText("")

                         } else {
                             Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                             strike++
                             incorrectAnswers.text = "" + strike
                             answer.setText("")
                         }
                         c++
                         lastMathQuestion.text ="Previous Solution: " + start1 + " " + operatorSign.text + " " + start2  + " =" + +sum
                         operatorSign.text = "-"

                     } else if (c == 1) {
                      //   sum.toString().toDouble()
                         sum = start1 - start2
                         if (answer.text.toString().toInt().equals(sum))  {
                             Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                             score++
                             scores.text = "" + score
                             answer.setText("")
                         } else {
                             Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                             strike++
                             incorrectAnswers.text = "" + strike
                             answer.setText("")
                         }
                         c++
                         lastMathQuestion.text = "Previous Solution: " + start1 + " " + operatorSign.text + " " + start2  + " = " +sum
                         operatorSign.text = "*"

                     } else if (c == 2) {

                        sum = start1 * start2
                         if (answer.text.toString().toInt().equals(sum)) {
                             Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                             score++
                             scores.text = "" + score
                             answer.setText("")
                         } else {
                             Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                             strike++
                             incorrectAnswers.text = "" + strike
                             answer.setText("")
                         }
                         c = 0
                         lastMathQuestion.text = "Previous Solution: " + start1 + " " + operatorSign.text + " " + start2  + " = " +sum
                         operatorSign.text = "+"

                     }

                     start1 = (0..15).shuffled().last()
                     start2 = (0..15).shuffled().last()
                     number1.text = "" + start1
                     number2.text = "" + start2
                     answerBtn.visibility = View.VISIBLE
                     NewGameBtnAfterLosing.visibility =  View.INVISIBLE

                     if(score.toString().toInt() > HighScores.text.toString().toInt()) {
                         HighScores.text = score.toString()
                     }
                     saveData()

                 } else {
                     saveData()
                     Toast.makeText(this, "Game Over, You Lose", Toast.LENGTH_SHORT).show()
                     lastMathQuestion.text = "Previous Solution: " + start1 + " " + operatorSign.text + " " + start2  + " = " +sum
                     NewGameBtnAfterLosing.visibility = View.VISIBLE
                     answerBtn.visibility = View.INVISIBLE



                 }
             }
         }

         NewGameBtnAfterLosing.setOnClickListener{
             answer.setText("")
             answerBtn.visibility = View.VISIBLE
             NewGameBtnAfterLosing.visibility = View.INVISIBLE
             val start1 = (0..15).shuffled().last()
             val start2 = (0..15).shuffled().last()
             number1.text = "" + start1
             number2.text = "" + start2
             c = 0
             strike = 0

             incorrectAnswers.text = "" + strike
             score = 0
             scores.text = "" + score

         }


     }

    private fun saveData() {
        val highscorePref = getSharedPreferences("highscorePref", Context.MODE_PRIVATE)
        val editor = highscorePref.edit()
        editor.putString("highscore", HighScores.text.toString())
                editor.apply()

    }
    private  fun retrieveData(){

        val highscorePref = getSharedPreferences("highscorePref", Context.MODE_PRIVATE)
        val highscore = highscorePref.getString("highscore","0")
        HighScores.setText(highscore)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->{
                return true}
            android.R.id.home -> {
                val intent = Intent(this@MathActivity,MainActivity::class.java)

                startActivity(intent)

                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onBackPressed() {

        super.onBackPressed()
        val intent = Intent(this@MathActivity,MainActivity::class.java)

      startActivity(intent)
        finish()

    }
 }


