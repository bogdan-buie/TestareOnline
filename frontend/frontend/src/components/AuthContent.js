import * as React from 'react';
import { request } from '../axios_helper';
export default class AuthContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        };
    }
    componentDidMount() {
        request(
            "GET",
            "/test/messages",
            {}
        ).then((response) => {
            if (Array.isArray(response.data)) {
                this.setState({ data: response.data });
            } else {
                console.error("Invalid data format. Expected an array.");
            }
        }).catch((error) => {
            console.error("Error fetching data:", error);
        });
    }


    render() {
        return (
            <div className='row justify-content-md-center'>
                <div className='col-4'>
                    <div className='card' style={{ width: "18rem" }}>
                        <div className='card-body'>
                            <h5 className='card-title'>Backend Response</h5>
                            <p className='card-text'>Content:</p>
                            <ul>
                                {this.state.data.length > 0 ? (
                                    this.state.data.map((line, index) => <li key={index}>{line}</li>)
                                ) : (
                                    <p>No data available</p>
                                )}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}