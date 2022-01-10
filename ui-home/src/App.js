import { Routes, Route, Link } from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import Home from './Home'
import React from 'react';

const App1 = React.lazy(
  () => import('app1/App')
);

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <nav>
          <Link to="/">Homew</Link>
          <Link to="/about">Aboutw</Link>
        </nav>
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="about" element={<React.Suspense fallback='Loading App'><App1/></React.Suspense>} />
        </Routes>
      </header>
    </div>
  );
}

export default App;
