import React, {useState} from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './Components/Home';
import CarList from './Components/CarList';
import './Styles/App.css';
import ShowroomList from "./Components/ShowroomList";
import LogIn from "./Components/LogIn";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    return (
        <Router>
            <div className="App">
                <main>
                    <Routes>
                        <Route
                            path="/"
                            element={<Home isAuthenticated={isAuthenticated} />}
                        />
                        <Route path="/cars" element={<CarList/>}/>
                        <Route path="/show-room-list" element={<ShowroomList/>}/>
                        <Route
                            path="/login"
                            element={<LogIn setAuthenticated={setIsAuthenticated} />}
                        />
                    </Routes>
                </main>
            </div>
        </Router>
    );
}

export default App;
