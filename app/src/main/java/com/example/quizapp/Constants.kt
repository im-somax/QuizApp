package com.example.quizapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWER: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val ques1 = Question(
            1,
            "What country does this flag belongs to?",
             R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionList.add(ques1)

        val ques2 = Question(
            2,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_australia,
            "Argentina",
            "Belgium",
            "Australia",
            "India",
            3
        )
        questionList.add(ques2)

        val ques3 = Question(
            3,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_belgium,
            "India",
            "Belgium",
            "Australia",
            "Argentina",
            2
        )
        questionList.add(ques3)

        val ques4 = Question(
            4,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_brazil,
            "India",
            "Belgium",
            "Brazil",
            "Argentina",
            3
        )
        questionList.add(ques4)

        val ques5 = Question(
            5,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_denmark,
            "India",
            "Belgium",
            "Brazil",
            "Denmark",
            4
        )
        questionList.add(ques5)

        val ques6 = Question(
            6,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_fiji,
            "India",
            "fiji",
            "Brazil",
            "Argentina",
            2
        )
        questionList.add(ques6)

        val ques7 = Question(
            7,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_germany,
            "India",
            "Belgium",
            "Brazil",
            "Germany",
            4
        )
        questionList.add(ques7)

        val ques8 = Question(
            8,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_india,
            "India",
            "Belgium",
            "Brazil",
            "Argentina",
            1
        )
        questionList.add(ques8)

        val ques9 = Question(
            9,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_kuwait,
            "India",
            "Kuwait",
            "Brazil",
            "Argentina",
            2
        )
        questionList.add(ques9)


        val ques10 = Question(
            10,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_new_zealand,
            "New Zealand",
            "Kuwait",
            "Brazil",
            "Argentina",
            1
        )
        questionList.add(ques10)
        return questionList
    }
}