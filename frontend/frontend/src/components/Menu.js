import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Card, Button, Row, Col } from 'react-bootstrap';

export default function Menu(props) {
    let navigate = useNavigate();

    const handle = () => {
        navigate('/login');
    };

    return (
        <div style={{ margin: '20px' }}>
            <Row>
                {/* Flashcard 1 */}
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Gestiune întrebări</Card.Title>
                            <Card.Text>
                                Adaugă, modifică, șterge, actualizează întrebări
                            </Card.Text>
                            <Button as={Link} to="/questions" variant="primary">
                                Go to
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>

                {/* Flashcard 2 */}
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Gestiune quiz-uri</Card.Title>
                            <Card.Text>
                                Text text text
                            </Card.Text>
                            <Button as={Link} to="/quizzes" variant="primary">
                                Go to
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>

                {/* Adaugă altele după nevoie */}
            </Row>
        </div>
    );
}
