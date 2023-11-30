import logo from '../logo.svg';
import './App.css';
import AppContent from './AppContent';
import Header from './Header';


function App() {
  return (
    <div className="App">
      <Header pageTitle="Quiz Online" logoSrc={logo} />
      <div className='container-fluid'>
        <div className='row'>
          <div className='col'>
            <AppContent />
          </div>
        </div>
      </div>

    </div>
  );
}

export default App;
