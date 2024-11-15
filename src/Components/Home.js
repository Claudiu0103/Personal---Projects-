import React from 'react';
import {useNavigate} from 'react-router-dom';
import '../Styles/Home.css';

function Home({isAuthenticated,userRole}) {
    const navigate = useNavigate();

    const handleGoToCarList = () => {
        navigate('/cars');
    };
    const handleGoToShowRoomList = () => {
        navigate('/show-room-list');
    }
    const handleGoToLogIn = () => {
        navigate('/login');
    }
    const handleGoToDataManagement = () =>{
        navigate('/data-management');
    }
    const handleGoToCarHistory= () =>{
        navigate('/carHistory');
    }
    const handleGoToManagementShowrooms= () =>{
        navigate('/management-showrooms');
    }
    const handleGoToManagementClients= () =>{
        navigate('/management-clients');
    }
    const handleGoToManagementCars= () =>{
        navigate('/management-cars');
    }
    return (
        <div className="home">
            <div className="home-title">
                <h1>Dealer Auto</h1>
                <p1>Cele mai bune oferte de mașini noi și rulate!</p1>
            </div>
            <main className="home-content">

                <p>La Dealer Auto, oferim o selecție variată de vehicule pentru toate gusturile și bugetele.</p>
                <p>Vizitează secțiunea noastră de oferte pentru a găsi mașina perfectă pentru tine!</p>
                <div className="button-group"></div>
                <div className="button-group">
                    <button onClick={handleGoToCarList}>Vezi Lista de Mașini</button>
                    <button onClick={handleGoToShowRoomList}>Vezi Showroom uri</button>
                    {isAuthenticated && userRole === 'USER' && (
                        <>
                            <button onClick={handleGoToDataManagement}>Vezi Date Personale</button>
                            <button onClick={handleGoToCarHistory}>Istoric Mașini</button>
                        </>
                    )}
                    {!isAuthenticated && (
                        <button onClick={handleGoToLogIn}>Creare Cont/Autentificare</button>
                    )}
                    {isAuthenticated && userRole === 'ADMIN' && (
                        <>
                            <button onClick={handleGoToManagementShowrooms}>Management Showroom-uri</button>
                            <button onClick={handleGoToManagementClients}>Management Clienți</button>
                            <button onClick={handleGoToManagementCars}>Management Mașini</button>
                        </>
                    )}
                    {/*{isAuthenticated ? (*/}
                    {/*    <button onClick={handleGoToDataManagement}>*/}
                    {/*        Gestionare Date Personale*/}
                    {/*    </button>*/}
                    {/*) : (*/}
                    {/*    <button onClick={handleGoToLogIn}>*/}
                    {/*        Creare Cont/Autentificare*/}
                    {/*    </button>*/}
                    {/*)}*/}
                </div>
            </main>
            <footer>
                <p>&copy; 2024 Dealer Auto. Toate drepturile rezervate.</p>
            </footer>
        </div>
    );
}

export default Home;
