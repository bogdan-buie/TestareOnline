import * as React from 'react';
import { Link, useNavigate } from "react-router-dom"

export default function Header(props) {
    let navigate = useNavigate();

    const handleLogin = () => {
        // Deschide fereastra de login sau executați logică relevantă
        // De exemplu, poți utiliza window.open() pentru a deschide o nouă fereastră.
        navigate('/login');
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-primary d-flex justify-content-between">
            <Link className="navbar-brand" to="/">
                <img
                    src={props.logoSrc}
                    className="App-logo"
                    alt="logo"
                    style={{ maxWidth: '150px', maxHeight: '50px' }}
                />
                {props.pageTitle}

            </Link>
            <div className="navbar-nav">
                <button
                    className="btn btn-info"
                    onClick={handleLogin}
                    style={{ margin: '20px' }}>
                    Login
                </button>
            </div>
        </nav>

    );
};