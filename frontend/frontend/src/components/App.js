import logo from '../logo.svg';
import './App.css';

import AppContent from './AppContent';
import LoginForm from './LoginForm';

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
function App() {
  return (
    <div className="App">
      <Router>
        <Header pageTitle="Quiz Online" logoSrc={logo} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<LoginContent />} />
          <Route path="/secret" element={<AuthContent />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/questions" element={<ViewQuestions />} />
          <Route path="/addQuestion" element={<AddQuestion />} />
          <Route path="/addQuiz" element={<AddQuiz />} />
          <Route exact path="/viewQuestion/:id" element={<QuestionDetail />} ></Route>
          <Route exact path="/viewQuizQuestions/:id" element={<ViewQuizQuestions />} ></Route>
          <Route exact path="/editQuestion/:id" element={<EditQuestion />} ></Route>
          <Route path="/quizzes" element={<ViewQuizzes />} />
        </Routes>
      </Router>

      {/* <div className='container-fluid'>
        <div className='row'>
          <div className='col'>
            <AppContent />
          </div>
        </div>
      </div> */}

    </div>
  );
}

export default App;
