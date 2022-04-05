import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Header from "./components/Header";
import NewGame from "./components/NewGame";
import Game from "./components/Game";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Game />
    </BrowserRouter>
  );
}

export default App;
