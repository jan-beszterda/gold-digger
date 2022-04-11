import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function LoadGame() {
    const [players, setPlayers] = useState([]);
    const [chosenPlayer, setChosenPlayer] = useState({});

    useEffect(() => {
        let isCancelled = false;
        const fetchData = async () => {
            const tmpPlayers = await getPlayers();
            if (!isCancelled) {
                setPlayers(tmpPlayers);
            }
        }
        if (!isCancelled) {
            fetchData();
        }
        return () => {
            isCancelled = true;
        }
    }, [])

    async function getPlayers() {
        let allPlayers = [];
        await fetch('/api/players/available').then(response => response.json()).then(data => {
            for (const player of data) {
                allPlayers.push(player);
            }
        })
        return allPlayers;
    }

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
        if (chosenPlayer.playerId === undefined) {
            alert("You need to select a  player!");
            return -1;
        }
        navigate(`/game/${chosenPlayer.playerId}`)
    }

    return (
        <div className="container-sm m-4 d-flex flex-column align-items-center">
            <div className="btn-group-vertical" role="group">
                {players.map((player) => (
                    <div className="form-check my-2 border-bottom" key={player.playerId}>
                        <input
                            type="radio"
                            className="form-check-input"
                            name="btnradio"
                            id={player.playerId}
                            autoComplete="off"
                            value={player.playerId}
                            onChange={onChosenPlayerChange}
                        />
                        <label className="form-check-label" htmlFor={player.playerId}>{player.playerName}, Health: {player.health},
                            Gold: {player.goldAmount}, Actions remaining: {player.actionsRemaining}<br/>
                            Mine: {player.currentMine.mineName}, Gold: {player.currentMine.totalGold}, Difficulty: {player.currentMine.difficulty}<br />
                            Pickaxe: {player.pickaxe.itemName}, Strength: {player.pickaxe.strength}, Condition: {player.pickaxe.condition}<br/>
                            Backpack: Weight: {player.backpack.foodItems.reduce((previousWeight, item) => previousWeight + item.weight, 0.0)}/{player.backpack.maxWeight}, Items: {player.backpack.foodItems.map(item => (<span key={item.itemId}>{item.itemName}, </span>))}
                        </label>
                    </div>
                ))}
            </div>
            <button className="btn btn-primary btn-lg mt-5 w-50" type="button" onClick={loadGame} id="loadButton">Load game</button>
        </div>
    );
}

export default LoadGame;