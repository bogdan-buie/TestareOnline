import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { request } from '../axios_helper';

export default function ViewSubmissions() {
    const [submissions, setSubmission] = useState([]);

    useEffect(() => {
        loadSubmissions();
    }, []);
    const loadSubmissions = async () => {
        request(
            "GET",
            "/quizSubmission/get/all",
            {

            }).then(
                (response) => {
                    console.log(response.data);
                    setSubmission(response.data);


                }).catch(
                    (error) => {
                        console.error(error);
                    }
                );

    }

    return (
        <div className='container'>
            <button className='btn btn-success' onClick={() => loadSubmissions()} >Refresh</button>
            <Link className='btn btn-primary mx-2' to={`/menu`} style={{ margin: '20px' }}>Menu</Link>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">Nr. crt.</th>
                            <th scope="col">ID</th>
                            <th scope="col">Nume</th>
                            <th scope="col">Prenume</th>
                            <th scope="col">Specializare</th>
                            <th scope="col">Data</th>
                            <th scope="col">Nota</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            submissions.map((submission, index) => (
                                < tr >
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{submission.id}</td>
                                    <td>{submission.lastName}</td>
                                    <td>{submission.firstName}</td>
                                    <td>{submission.specializare}</td>
                                    <td>{submission.dateTime}</td>
                                    <td>{submission.nota}</td>


                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div >
    );
}
