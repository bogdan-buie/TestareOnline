import React, { useEffect, useState } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { getAuthToken } from '../axios_helper';

const NotificationComponent = () => {
    const [notifications, setNotifications] = useState([]);

    useEffect(() => {
        // Conectare la serverul WebSocket utilizând SockJS și Stomp
        const mytoken = getAuthToken();
        const socket = new SockJS('http://localhost:8080/ws');
        const stompClient = Stomp.over(socket);

        stompClient.connect(
            { Authorization: `Bearer ${mytoken}` },
            () => {
                console.log('Conectat la WebSocket');
                // Abonare la canalul 'quizNotification'
                stompClient.subscribe('/topic/quizNotification', (message) => {
                    // Parsare și actualizare notificări
                    const data = JSON.parse(message.body);
                    setNotifications((prevNotifications) => [...prevNotifications, data]);
                });
            },
            (error) => {
                console.error('Eroare de conectare la WebSocket:', error);
            }
        );

        // Eliberare resurse la demontare
        return () => {
            stompClient.disconnect();
        };
    }, []); // Asigură-te că acest efect se execută doar o dată, la montarea componentei

    return (
        <div className='container'>
            <h2>Notificări</h2>
            <table className="table border shadow">
                <thead>
                    <tr>
                        <th scope="col">Nr. crt</th>
                        <th scope="col">Message</th>
                        <th scope="col">Nume</th>
                        <th scope="col">Prenume</th>
                        <th scope="col">Specializare</th>
                        <th scope="col">Nota</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        notifications.map((notification, index) => (
                            < tr >
                                <td scope="row" key={index}>{index + 1}</td>
                                <th>{notification.message}</th>
                                <td>{notification.nume}</td>
                                <td>{notification.prenume}</td>
                                <td>{notification.specializare}</td>
                                <td>{notification.nota}</td>


                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    );
};

export default NotificationComponent;
