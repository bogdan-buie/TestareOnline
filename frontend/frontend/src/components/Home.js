import React from "react";
import { Link } from 'react-router-dom';
export default class WelcomeContent extends React.Component {
    render() {
        return (
            <div className="row justify-contend-md-center" style={{ margin: '40px' }}>
                <div className="jumbo jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-6">Ești pregătit pentru un nou test?</h1>
                        <p className="lead"> Începe un quiz acum și află cât de mult știi!</p>
                        <Link className='btn btn-primary mx-2' to={`/public`}
                            style={{ margin: '20px' }}>
                            Începe un nou Quiz
                        </Link>
                    </div>
                </div>
            </div>
        );
    }
}