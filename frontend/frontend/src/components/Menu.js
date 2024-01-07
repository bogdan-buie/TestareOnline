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
                                Gestionează și creează noi quiz-uri
                            </Card.Text>
                            <Button as={Link} to="/quizzes" variant="primary">
                                Go to
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Note</Card.Title>
                            <Card.Text>
                                Vizualizează notele studenților
                            </Card.Text>
                            <Button as={Link} to="/note" variant="primary">
                                Go to
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Notificări</Card.Title>
                            <Card.Text>
                                Notificări în timp real
                            </Card.Text>
                            <Button as={Link} to="/notification" variant="primary">
                                Go to
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

        </div>
    );
}
