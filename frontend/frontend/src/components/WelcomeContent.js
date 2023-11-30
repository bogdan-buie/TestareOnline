import React from "react";

export default class WelcomeContent extends React.Component {
    render() {
        return (
            <div className="row justify-contend-md-center">
                <div className="jumbo jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Welcome</h1>
                        <p className="lead">Login to see protected content</p>
                    </div>
                </div>
            </div>
        );
    }
}