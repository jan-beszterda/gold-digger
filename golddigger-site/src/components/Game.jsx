import { useState } from "react";
import NewGame from "./NewGame";

function Game(props) {
    const [player, setPlayer] = useState({
        playerName: ""
    });
    const [currentPlayer, setCurrentPlayer] = useState({});

    const handleFieldChange = (fieldName, fieldValue) => {
        setPlayer({...player, [fieldName]:fieldValue});
    };

    async function handleSubmit (e) {
        e.preventDefault();
        if ( player ) {
            let response = await fetch('/api/players/create', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(player),
            });
            let newPlayer = await response.json();
            setCurrentPlayer((prevState) => (newPlayer));
        }
        setPlayer({playerName: ""});
    }

    return (
        <div>
            <NewGame
                data={player}
                onChange={handleFieldChange}
                submit={handleSubmit}
            />
        </div>
    );
}

export default Game;