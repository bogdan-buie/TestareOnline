import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { request } from '../axios_helper';

export default function ViewQuizzes() {
    const [quizzes, setQuizzes] = useState([]);
    useEffect(() => {
        loadQuizzes();
    }, []);

    const loadQuizzes = async () => {
        request(
            "GET",
            "/quiz/get/all",
            {

            }).then(
                (response) => {
                    console.log(response.data);
                    setQuizzes(response.data);

                }).catch(
                    (error) => {
                        console.error(error);
                    }
                );

    }
    const deleteQuiz = async (id) => {
        try {
            const response = await request("DELETE", `/quiz/delete/${id}`, {});
            console.log(response.data);
            loadQuizzes();
        } catch (error) {
            console.error(error);
        }
    };
    return (
        <div className='container'>
            <Link className='btn btn-primary mx-2' to={`/addQuiz`} style={{ margin: '20px' }}>Creeaza un Quiz</Link>
            <Link className='btn btn-primary mx-2' to={`/menu`} style={{ margin: '20px' }}>Menu</Link>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">Nr. crt.</th>
                            <th scope="col">ID</th>
                            <th scope="col">Titlu</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            quizzes.map((quiz, index) => (
                                < tr >
                                    <td scope="row" key={index}>{index + 1}</td>
                                    <th>{quiz.id}</th>
                                    <td>{quiz.title}</td>


                                    <td>
                                        <Link className='btn btn-primary mx-2' to={`/viewQuizQuestions/${quiz.id}`}>View</Link>
                                        <button className='btn btn-danger mx-2' onClick={() => deleteQuiz(quiz.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div >
    );
}
