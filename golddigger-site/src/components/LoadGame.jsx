import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom"

function LoadGame(props) {
    const [players, setPlayers] = useState([]);
    const [chosenPlayer, setChosenPlayer] = useState({});

    useEffect(() => {
        setPlayers(props.players);
    }, [props.players]);

    const onChosenPlayerChange = (e) => {
        let selectedPlayer;
        for (const player of players) {
            if (player.playerId === +e.currentTarget.value) {
                selectedPlayer = player;
            }
        }
        setChosenPlayer(selectedPlayer);
    }

    const navigate = useNavigate();

    const loadGame = () => {
        console.log(chosenPlayer);
        navigate("/game", {state: {currentPlayer: chosenPlayer}})
    }

    return (
        <div className="container-sm m-4 d-flex flex-column align-items-center">
            <div className="btn-group-vertical" role="group">
                {players.map((player) => (
                    <div className="my-2" key={player.playerId}>
                        <input
                            type="radio"
                            className="btn-check"
                            name="btnradio"
                            id={player.playerId}
                            autoComplete="off"
                            value={player.playerId}
                            onChange={onChosenPlayerChange}
                        />
                        <label className="btn btn-primary btn-lg" htmlFor={player.playerId}>{player.playerName}, Health: {player.health},
                            Gold: {player.goldAmount}, Actions remaining: {player.actionsRemaining}
                        </label>
                    </div>
                ))}
            </div>
            <button className="btn btn-primary btn-lg mt-5" onClick={loadGame}>Load game</button>
        </div>
    );
}

export default LoadGame;