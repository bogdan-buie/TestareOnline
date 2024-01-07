import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Card, Button, Row, Col, Form } from 'react-bootstrap';

export default function PublicContent(props) {
    const navigate = useNavigate();
    const [password, setPassword] = useState('');
    const [quizId, setQuizId] = useState('');

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleQuizIdChange = (event) => {
        setQuizId(event.target.value);
    };

    const handleNavigation = () => {
        // Verificați dacă password și quizId sunt necompletate
        if (!password || !quizId) {
            // Afișați un mesaj
            alert("Vă rugăm să introduceți o parolă și un id de quiz.");
        } else {
            // Verificați dacă parola este corectă
            if (password === "utcn") {
                // Navigare către o altă pagină sau execuție a altei acțiuni
                navigate(`/getquiz/${quizId}`);
            } else {
                // Afișați un mesaj
                alert("Parolă incorectă!");
            }
        }
    };


    return (
        <div style={{ margin: '20px' }}>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Start Quiz</Card.Title>
                            <Card.Text>
                                Raspunde corect la toate intrebarile.
                            </Card.Text>
                            <Form>
                                <Form.Group controlId="formPassword">
                                    <Form.Label>Parolă:</Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Introduceți parola"
                                        value={password}
                                        onChange={handlePasswordChange}
                                    />
                                </Form.Group>
                                <Form.Group controlId="formQuizId">
                                    <Form.Label>ID Quiz:</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Introduceți ID-ul Quiz-ului"
                                        value={quizId}
                                        onChange={handleQuizIdChange}
                                    />
                                </Form.Group>
                                <Button variant="primary" onClick={handleNavigation}>
                                    Go to
                                </Button>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </div>
    );
}
