import React, {useEffect, useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Header from "./components/Header";
import NewGame from "./components/NewGame";
import Home from "./components/Home";
import LoadGame from "./components/LoadGame";
import Game from "./components/Game";

function App() {
    const [players, setPlayers] = useState([]);
    const [shop, setShop] = useState({});

    useEffect(() => {
        getPlayers();
        getShop();
    }, []);

    async function getPlayers() {
        let allPlayers = [];
        await fetch('/api/players').then(response => response.json()).then(data => {
            for (const player of data) {
                allPlayers.push(player);
            }
        })
        setPlayers(allPlayers);
    }

    async function getShop() {
        let aShop;
        await fetch('/api/shops').then(response => response.json()).then(data => {
            for (const shop of data) {
                aShop = shop;
            }
        })
        setShop(aShop);
    }

    return (
        <BrowserRouter>
            <Header />
            <main className="container-fluid">
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route path="/new" element={<NewGame />} />
                    <Route path="/load" element={<LoadGame players={players}/>} />
                    <Route path="/game" element={<Game />} />
                    <Route path="/scores" element={<div>TODO</div>} />
                    <Route path="/about" element={<div>TODO</div>} />
                </Routes>
            </main>
        </BrowserRouter>
    );
}

export default App;
