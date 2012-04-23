/*
 * Copyright 2012 gilberto.andrade.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.constructor.lab01.quiz_card;

/**
 *
 * @author gilberto.andrade
 */
class QuizCard {
    private String question;
    private String answer;
    
    public QuizCard(){}
    public QuizCard(String q, String a){
        question = q;
        answer = a;
   } 
    public void setQuestion(String q){
        question = q;
    }
    public void setAnswer(String a){
        answer = a;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}
