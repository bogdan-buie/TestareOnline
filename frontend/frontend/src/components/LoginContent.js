import * as React from 'react';


import { request, setAuthHeader } from '../axios_helper';

import Buttons from './Buttons';
import LoginForm from './LoginForm';
import WelcomeContent from './WelcomeContent';
import { Navigate, Link } from 'react-router-dom';
import Menu from './Menu';

export default class LoginContent extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome",
            isLoggedIn: false
        }
    };

    login = () => {
        this.setState({ componentToShow: "login" })
    };
    toMenu = () => {
        this.setState({ componentToShow: "menu" })
    };

    logout = () => {
        this.setState({ componentToShow: "welcome" })
        setAuthHeader(null);
        console.log("Linia 33");
        this.navigateToHome();
    };
    navigateToHome = () => {
        console.log("Linia 37");
        return <Navigate to="/" />;
    }
    onLogin = (e, username, password) => {
        e.preventDefault();
        request(
            "POST",
            "/login",
            {
                login: username,
                password: password
            }).then(
                (response) => {
                    setAuthHeader(response.data.token);
                    console.log(response.data);

                    this.setState({ componentToShow: "menu", isLoggedIn: true });

                }).catch(
                    (error) => {
                        setAuthHeader(null);
                        this.setState({ componentToShow: "welcome" })
                    }
                );
    };

    onRegister = (event, firstName, lastName, username, password) => {
        event.preventDefault();
        request(
            "POST",
            "/register",
            {
                firstName: firstName,
                lastName: lastName,
                login: username,
                password: password
            }).then(
                (response) => {
                    setAuthHeader(response.data.token);
                    this.setState({ componentToShow: "messages" });
                }).catch(
                    (error) => {
                        setAuthHeader(null);
                        this.setState({ componentToShow: "welcome" })
                    }
                );
    };

    render() {
        const { isLoggedIn } = this.state;

        if (isLoggedIn) {
            return <Navigate to="/menu" />;
        }
        return (
            <>
                <Buttons
                    login={this.login}
                    logout={this.logout}
                />

                {isLoggedIn && <Menu />}
                {!isLoggedIn && this.state.componentToShow === "login" && (
                    <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />
                )}
            </>
        );
    };
}