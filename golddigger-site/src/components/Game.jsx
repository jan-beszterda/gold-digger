import {useState} from "react";
import NewGame from "./NewGame";

function Game(props) {
    const [player, setPlayer] = useState({
        playerName: ""
    });

    const [currentPlayer, setCurrentPlayer] = useState({});

    const handleFieldChange = (fieldName, fieldValue) => {
        setPlayer({...player, [fieldName]:fieldValue});
    };

    async function handleSubmit(e) {
        e.preventDefault();
        if (player.playerName) {
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

    async function handleSubmit(e) {
        e.preventDefault();
        if (player.playerId) {
            await fetch('/api/players/' + player.playerId + '/dig', {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        if (player.playerId, item.itemId) {
            await fetch('/api/players/' + player.playerId + '/eat/' + item.itemId, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        if (player.playerId) {
            await fetch('/api/players/' + player.playerId + '/sleep', {
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