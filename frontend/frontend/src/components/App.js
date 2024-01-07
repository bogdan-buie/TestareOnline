import logo from '../logo.svg';
import './App.css';

import LoginContent from './LoginContent';
import Header from './Header';
import Home from './Home';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AuthContent from './AuthContent';
import Menu from './Menu';
import ViewQuestions from './ViewQuestions';
import AddQuestion from './AddQuestion';
import AddQuiz from './AddQuiz';
import QuestionDetail from './QuestionDetail';
import EditQuestion from './EditQuestion';
import ViewQuizzes from './ViewQuizzes';
import ViewQuizQuestions from './ViewQuizQuestions';
import PublicContent from './PublicContent';
import QuizComponent from './QuizComponent';
import ViewSubmissions from './ViewSubmissions';
import RealTimeNotifications from './NotificationComponent';
function App() {

  return (
    <div className="App">
      <Router>
        <Header pageTitle="Quiz Online" logoSrc={logo} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<LoginContent />} />
          <Route path="/secret" element={<AuthContent />} />
          <Route path="/public" element={<PublicContent />} />
          <Route exact path="/getQuiz/:id" element={<QuizComponent />} ></Route>
          <Route path="/menu" element={<Menu />} />
          <Route path="/questions" element={<ViewQuestions />} />
          <Route path="/note" element={<ViewSubmissions />} />
          <Route path="/addQuestion" element={<AddQuestion />} />
          <Route path="/addQuiz" element={<AddQuiz />} />
          <Route exact path="/viewQuestion/:id" element={<QuestionDetail />} ></Route>
          <Route exact path="/viewQuizQuestions/:id" element={<ViewQuizQuestions />} ></Route>
          <Route exact path="/editQuestion/:id" element={<EditQuestion />} ></Route>
          <Route path="/quizzes" element={<ViewQuizzes />} />
          <Route path="/notification" element={<RealTimeNotifications />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
