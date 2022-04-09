import React, {useEffect, useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Header from "./components/Header";
import NewGame from "./components/NewGame";
import Home from "./components/Home";
import LoadGame from "./components/LoadGame";
import Game from "./components/Game";
import Scores from "./components/Scores";
import About from "./components/About";
import GameLost from "./components/GameLost";

function App() {
    const [shop, setShop] = useState({});

    useEffect(() => {
        let isCancelled = false;
        const fetchData = async () => {
            const tmpShop = await getShop();
            if (!isCancelled) {
                setShop(tmpShop);
            }
        }
        if (!isCancelled) {
            fetchData();
        }
        return () => {
            isCancelled = true;
        }
    }, [])

    async function getShop() {
        let aShop;
        await fetch('/api/shops').then(response => response.json()).then(data => {
            for (const shop of data) {
                aShop = shop;
            }
        })
        return aShop;
    }

    return (
        <BrowserRouter>
            <Header />
            <main className="container-fluid d-flex justify-content-center">
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route path="/new" element={<NewGame />} />
                    <Route path="/load" element={<LoadGame />} />
                    <Route path="/game/:playerId" element={<Game shop={shop} />} />
                    <Route path="/scores" element={<Scores />} />
                    <Route path="/about" element={<About />} />
                    <Route path="/gameover" element={<GameLost />} />
                </Routes>
            </main>
        </BrowserRouter>
    );
}

export default App;
