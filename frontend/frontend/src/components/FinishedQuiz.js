import React from "react";
import { Link } from 'react-router-dom';
export default class FinishedQuiz extends React.Component {
    render() {

        return (

            <div className="row justify-contend-md-center"
                style={{
                    margin: '100px'
                }}>
                <div className="jumbo jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-6">Quiz-ul s-a terminat</h1>
                        <p className="lead">Vei primi în scurt timp nota de la profesorul tău</p>
                        <Link className='btn btn-primary mx-2' to={`/`}
                            style={{ margin: '20px' }}>
                            Go to home
                        </Link>
                    </div>
                </div>
            </div>

        );
    }
}
