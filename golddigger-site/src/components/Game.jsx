import { useState } from "react";
import NewGame from "./NewGame";

function Game(props) {
    const [player, setPlayer] = useState({
            playerName: ""
        });

    const handleFieldChange = (fieldName, fieldValue) => {
        setPlayer({ ...player, [fieldName]: fieldValue });
    };

    async function handleSubmit (e) {
        e.preventDefault();
        if ( player.playerName ) {
            await fetch('/api/players/create', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(player),
            });
        }
    }

    async function handleSubmit (e) {
        e.preventDefault();
        if ( player.playerId ) {
            await fetch('/api/players/' + player.playerId + '/dig', {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        }
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