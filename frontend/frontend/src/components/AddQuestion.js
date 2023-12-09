import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { request } from '../axios_helper';

export default function AddQuestion() {
    let navigate = useNavigate();
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
    const { questionTitle, option1, option2, option3, option4, rightAnswer, difficultyLevel, category } = question;

    const onInputChange = (e) => {
        setQuestion({ ...question, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        request(
            "POST",
            "/question/add",
            {
                questionTitle,
                option1,
                option2,
                option3,
                option4,
                rightAnswer,
                difficultyLevel,
                category
            }).then(
                (response) => {
                    console.log(response.data);
                }).catch(
                    (error) => {

                    }
                );
        navigate("/questions");
    }
    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>
                        Adauga intrebare
                    </h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor="QuestionTitle" className='form-label'>
                                Titlul intrebarii
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti titlul intrebarii'
                                name='questionTitle'
                                value={questionTitle}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Option1" className='form-label'>
                                Optiune 1
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti optiunea 1'
                                name='option1'
                                value={option1}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Option2" className='form-label'>
                                Optiune 2
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti optiunea 2'
                                name='option2'
                                value={option2}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Option3" className='form-label'>
                                Optiune 3
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti optiunea 3'
                                name='option3'
                                value={option3}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Option4" className='form-label'>
                                Optiune 4
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti optiunea 4'
                                name='option4'
                                value={option4}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="RightAnswer" className='form-label'>
                                Right Answer
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti raspunsul corect'
                                name='rightAnswer'
                                value={rightAnswer}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="DifficutlyLevel" className='form-label'>
                                Difficulty level
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti nivelul de dificultate'
                                name='difficultyLevel'
                                value={difficultyLevel}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Category" className='form-label'>
                                Categorie
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti categoria'
                                name='category'
                                value={category}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className='btn btn-outline-primary'>Submit</button>
                        <Link type="submit" className='btn btn-outline-danger mx-2' to="/questions">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
