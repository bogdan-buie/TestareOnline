import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { request } from '../axios_helper';

export default function AddQuiz() {
    let navigate = useNavigate();
    const [quiz, setQuiz] = useState({
        Title: "",
        numQ: "",
        category: ""

    });
    const { Title, numQ, category } = quiz;

    const onInputChange = (e) => {
        setQuiz({ ...quiz, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        request(
            "POST",
            `/quiz/create?category=${category}&numQ=${numQ}&title=${Title}`,
            {

            }).then(
                (response) => {
                    console.log(response.data);
                }).catch(
                    (error) => {

                    }
                );
        navigate("/quizzes");
    }
    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>
                        Adauga Quiz
                    </h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor="QuizTitle" className='form-label'>
                                Titlul Quiz-ului
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti titlul quiz-ului'
                                name='Title'
                                value={Title}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="NumQ" className='form-label'>
                                Nr. de intrebari
                            </label>
                            <input
                                type="text"
                                className='form-control'
                                placeholder='Introduceti optiunea 1'
                                name='numQ'
                                value={numQ}
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
                                placeholder='Introduceti categoria quizului'
                                name='category'
                                value={category}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <button type="submit" className='btn btn-outline-primary'>Submit</button>
                        <Link type="submit" className='btn btn-outline-danger mx-2' to="/quizzes">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
