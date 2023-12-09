import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { request } from '../axios_helper';

export default function ViewQuizQuestions() {
    const [questions, setQuestion] = useState([]);
    const { id } = useParams();
    useEffect(() => {
        loadQuestions();
    }, []);
    const loadQuestions = async () => {
        request(
            "GET",
            `/quiz/getSecret/${id}`,
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
    const deleteQuestion = async (id) => {
        try {
            const response = await request("DELETE", `/question/delete/${id}`, {});
            console.log(response.data);
            loadQuestions();
        } catch (error) {
            console.error(error);
        }
    };
    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">Nr. crt.</th>
                            <th scope="col">Conținutul întrebări</th>
                            <th scope="col">Nivel de dificultate </th>
                            <th scope="col">Categorie</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            questions.map((question, index) => (
                                < tr >
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{question.questionTitle}</td>
                                    <td>{question.difficultyLevel}</td>
                                    <td>{question.category}</td>

                                    <td>
                                        <Link className='btn btn-primary mx-2' to={`/viewQuestion/${question.id}`}>View</Link>
                                        <Link className='btn btn-outline-primary mx-2' to={`/editQuestion/${question.id}`}>Edit</Link>
                                        <button className='btn btn-danger mx-2' onClick={() => deleteQuestion(question.id)}>Delete</button>
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
