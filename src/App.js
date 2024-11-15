// import React, {useState} from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './Components/Home';
import CarList from './Components/CarList';
import './Styles/App.css';
import ShowroomList from "./Components/ShowroomList";
import LogIn from "./Components/LogIn";
import DataManagement from "./Components/DataManagement";
import CarHistory from "./Components/IstoricMasini";
import ShowroomManagement from "./Components/ShowroomManagement";
import ClientManagement from "./Components/ClientManagement";
import CarManagement from "./Components/CarManagement";
import {useState} from "react";



function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [userRole, setUserRole] = useState('');

    return (
        <Router>
            <div className="App">
                <main>
                    <Routes>
                        <Route path="/" element={<Home isAuthenticated={isAuthenticated} userRole={userRole} />}/>
                        <Route path="/cars" element={<CarList/>}/>
                        <Route path="/show-room-list" element={<ShowroomList/>}/>
                        <Route path="/login" element={<LogIn setAuthenticated={setIsAuthenticated} setUserRole={setUserRole} />}/>
                        <Route path="/data-management" element={<DataManagement/>}/>
                        <Route path="/carHistory" element={<CarHistory/>}/>
                        <Route path="/management-showrooms" element={<ShowroomManagement/>}/>
                        <Route path="/management-clients" element={<ClientManagement/>}/>
                        <Route path="/management-cars" element={<CarManagement/>}/>
                        {/*<Route*/}
                        {/*    path="/login"*/}
                        {/*    element={<LogIn setAuthenticated={setIsAuthenticated} />}*/}
                        {/*/>*/}
                    </Routes>
                </main>
            </div>
        </Router>
    );
}

export default App;
