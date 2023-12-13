import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { request } from '../axios_helper';

export default function QuestionDetail() {
    const [question, setQuestion] = useState({
        questionTitle: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        rightAnswer: "",
        difficultyLevel: "",
        category: ""
    });
    const { id } = useParams();
    useEffect(() => {
        loadQuestion();
    }, []);
    const loadQuestion = async () => {
        request(
            "GET",
            `/question/${id}`,
            {

            }).then(
                (response) => {
                    console.log(response.data);
                    setQuestion(response.data);


                }).catch(
                    (error) => {
                        console.error(error);
                    }
                );

    }
    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>
                        Detalii întrebare
                    </h2>
                    <div className='card'>
                        <div className='card-header'>
                            <b>ID: </b> {question.id}
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>
                                    <b>Titlu: </b>
                                    {question.questionTitle}
                                </li>
                                <li className='list-group-item'>
                                    <b>Optiune 1: </b>
                                    {question.option1}
                                </li>
                                <li className='list-group-item'>
                                    <b>Optiune 2: </b>
                                    {question.option2}
                                </li>
                                <li className='list-group-item'>
                                    <b>Optiune 3: </b>
                                    {question.option3}
                                </li>
                                <li className='list-group-item'>
                                    <b>Optiune 4: </b>
                                    {question.option4}
                                </li>
                                <li className='list-group-item'>
                                    <b>Raspuns Corect: </b>
                                    {question.rightAnswer}
                                </li>
                                <li className='list-group-item'>
                                    <b>Dificultate: </b>
                                    {question.difficultyLevel}
                                </li>
                                <li className='list-group-item'>
                                    <b>Categorie: </b>
                                    {question.category}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Link className="btn btn-primary my-2" to={"/questions"}>Back to home</Link>
                </div>
            </div>
        </div>
    )
}
