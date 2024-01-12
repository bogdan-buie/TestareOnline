import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Form, Button, Card } from 'react-bootstrap';
import { request2 } from '../axios_helper';
import { useNavigate } from 'react-router-dom';
const QuizComponent = () => {
    const { id } = useParams();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [specialization, setSpecialization] = useState('');
    const [questions, setQuestions] = useState([]);
    const [userAnswers, setUserAnswers] = useState(Array(questions.length).fill(null));
    const navigate = useNavigate();
    useEffect(() => {
        // Funcția pentru a aduce întrebările de pe backend
        const fetchQuizQuestions = async () => {
            try {
                const response = await request2('GET', `/quiz/get/${id}`, {});
                setQuestions(response.data);
            } catch (error) {
                console.error('Eroare la aducerea întrebărilor:', error);
            }
        };
        fetchQuizQuestions();
    }, [id]);

    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value);
    };

    const handleLastNameChange = (event) => {
        setLastName(event.target.value);
    };

    const handleSpecializationChange = (event) => {
        setSpecialization(event.target.value);
    };

    const handleAnswerChange = (questionIndex, selectedOption) => {
        const newAnswers = [...userAnswers];
        newAnswers[questionIndex] = selectedOption;
        setUserAnswers(newAnswers);
    };

    const handleToQuestions = () => {
        var myDiv = document.getElementById("intrebari");
        if (firstName !== '' && lastName !== '' && specialization !== '') {
            myDiv.style.display = "block";
        }
        else {
            alert("Trebuie sa compleți toate cîmpurile!");
        }
    };

    const submitQuiz = async () => {
        try {
            const dateTimeObj = new Date();
            const options = { timeZone: 'Europe/Bucharest' };
            const dateTime = dateTimeObj.toLocaleString('ro-RO', options);

            const responses = questions.map((question, index) => ({
                questionId: question.id,
                response: question[`option${userAnswers[index] + 1}`],
            }));

            const payload = {
                firstName,
                lastName,
                specializare: specialization,
                dateTime,
                responses,
                nota: -10,  // Nota poate fi setată în funcție de logica aplicației dvs.
            };
            console.log(payload);

            const response = await request2('POST', `/quiz/submit3/${id}`, payload);
            console.log('Răspunsul la submit:', response.data);
            navigate('/finishedQuiz');

        } catch (error) {
            console.error('Eroare la trimiterea quiz-ului:', error);
            // Tratați eroarea într-un mod corespunzător
        }
    };
    return (
        <div style={{ margin: '20px' }}>
            {/* Formular pentru nume, prenume și specializare */}
            <Card>
                <Card.Body>
                    <Card.Title>Informații student</Card.Title>
                    <Form>
                        <Form.Group controlId="formFirstName">
                            <Form.Label>Prenume:</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Introduceți prenumele"
                                value={firstName}
                                onChange={handleFirstNameChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="formLastName">
                            <Form.Label>Nume:</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Introduceți numele"
                                value={lastName}
                                onChange={handleLastNameChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="formSpecialization">
                            <Form.Label>Specializare:</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Introduceți specializarea"
                                value={specialization}
                                onChange={handleSpecializationChange}
                            />
                        </Form.Group>
                        <Button variant="primary" onClick={handleToQuestions}>
                            Continuă la întrebări
                        </Button>
                    </Form>
                </Card.Body>
            </Card>

            {/* Afisarea intrebarilor */}
            {userAnswers[0] !== null && (
                <div id="intrebari" style={{ display: "none" }}>
                    <h2>Quiz ID: {id}</h2>
                    {questions.map((question, index) => (
                        <Card key={question.id} style={{ margin: '20px' }}>
                            <Card.Body>
                                <Card.Title>Întrebarea {index + 1}</Card.Title>
                                <Card.Text>{question.questionTitle}</Card.Text>
                                <Form>
                                    <Form.Check
                                        type="radio"
                                        label={question.option1}
                                        checked={userAnswers[index] === 0}
                                        onChange={() => handleAnswerChange(index, 0)}
                                    />
                                    <Form.Check
                                        type="radio"
                                        label={question.option2}
                                        checked={userAnswers[index] === 1}
                                        onChange={() => handleAnswerChange(index, 1)}
                                    />
                                    <Form.Check
                                        type="radio"
                                        label={question.option3}
                                        checked={userAnswers[index] === 2}
                                        onChange={() => handleAnswerChange(index, 2)}
                                    />
                                    <Form.Check
                                        type="radio"
                                        label={question.option4}
                                        checked={userAnswers[index] === 3}
                                        onChange={() => handleAnswerChange(index, 3)}
                                    />
                                </Form>
                            </Card.Body>
                        </Card>
                    ))}
                    <Button variant="primary" onClick={submitQuiz}>
                        Finalizează quiz-ul
                    </Button>
                </div>
            )}
        </div>
    );
};

export default QuizComponent;
