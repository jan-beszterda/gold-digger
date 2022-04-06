import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Header from "./components/Header";
import NewGame from "./components/NewGame";
import Game from "./components/Game";
import Home from "./components/Home";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Home />
      {/*<Game />*/}
    </BrowserRouter>
  );
}

export default App;
