package com.english.myapp.utils;

import java.util.ArrayList;

/**
 * Created by Ramakrishna on 05-Aug-20.
 */
public class Level2 {



  public ArrayList<QuestAns> questAnsList = new ArrayList<>();

    public void updateQuestions(){


    String ques = "I ..... tennis every Sunday morning.";
    String ans ="play";
    questAnsList.add(new QuestAns(ques,ans,getOptions("playing","play","am playing")));

    String ques1 = "Don't make so much noise. Noriko ..... to study for her ESL test!";
    String ans1 ="is trying";
    questAnsList.add(new QuestAns(ques1,ans1,getOptions("tries","is trying","tried")));



    String ques2 = "Jun-Sik........his teeth before breakfast every morning.\n";
    String ans2 ="cleans";
    questAnsList.add(new QuestAns(ques2,ans2,getOptions("will cleaned","is cleaning","cleans")));


    String ques3 = "Sorry, she can't come to the phone. She ..... a bath!";
    String ans3 ="is having";
    questAnsList.add(new QuestAns(ques3,ans3,getOptions("is having","having","have")));



    String ques4 = "..... many times every winter in Frankfurt.";
    String ans4 ="It snows";
    questAnsList.add(new QuestAns(ques4,ans4,getOptions("It snows","It snowed","It is snowing")));


    String ques5 = "How many students in your class ..... from Korea?";
    String ans5 ="come";
    questAnsList.add(new QuestAns(ques5,ans5,getOptions("comes","come","came")));

    String ques6 = "Weather report: \"It's seven o'clock in Frankfurt and ..... .\"";
    String ans6 ="it`s snowing";
    questAnsList.add(new QuestAns(ques6,ans6,getOptions("there is snow","it`s snowing","it snows")));


    String ques7 = "Babies ..... when they are hungry";
    String ans7 ="cry";
    questAnsList.add(new QuestAns(ques7,ans7,getOptions("cry","cries","cried")));


    String ques8 = "Jane: \"What ..... in the evenings?\"";
    String ans8 ="do you do";
    questAnsList.add(new QuestAns(ques8,ans8,getOptions("you doing","you doing","do you do")));

    String ques9 = "Sorry, you can't borrow my pencil. I ..... it myself.";
    String ans9 ="am using";
    questAnsList.add(new QuestAns(ques9,ans9,getOptions("use","was using","am using")));


    String ques10 = "The phone ...... Can you answer it, please?";
    String ans10 ="is ringing";
    questAnsList.add(new QuestAns(ques10,ans10,getOptions("rings","ring","is ringing")));


        String ques11 = "I ..... tennis every Sunday morning.";
        String ans11 ="play";
        questAnsList.add(new QuestAns(ques11,ans11,getOptions("playing","play","am playing")));

        String ques12 = "Don't make so much noise. Noriko ..... to study for her ESL test!";
        String ans12 ="is trying";
        questAnsList.add(new QuestAns(ques12,ans12,getOptions("tries","is trying","tried")));



        String ques13 = "Jun-Sik........his teeth before breakfast every morning.\n";
        String ans13 ="cleans";
        questAnsList.add(new QuestAns(ques13,ans13,getOptions("will cleaned","is cleaning","cleans")));


        String ques14 = "Sorry, she can't come to the phone. She ..... a bath!";
        String ans14 ="is having";
        questAnsList.add(new QuestAns(ques14,ans14,getOptions("is having","having","have")));



        String ques15 = "..... many times every winter in Frankfurt.";
        String ans15 ="It snows";
        questAnsList.add(new QuestAns(ques15,ans15,getOptions("It snows","It snowed","It is snowing")));




}


private ArrayList<Options> getOptions(String option1, String option2, String option3){

    Options options1 = new Options(option1);
    Options options2 = new Options(option2);
    Options options3 = new Options(option3);

    ArrayList<Options> optList = new ArrayList<Options>();
    optList.add(options1);
    optList.add(options2);
    optList.add(options3);
    return optList;

}



}
