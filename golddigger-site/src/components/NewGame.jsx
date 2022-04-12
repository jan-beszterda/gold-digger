import {useState} from "react";
import {useNavigate} from "react-router-dom";

function NewGame() {
    const [player, setPlayer] = useState({
        playerName:""
    });

    const navigate = useNavigate();

    async function handleSubmit(e) {
        e.preventDefault();
        let player = await createplayer();
        navigate(`/game/${player.playerId}`);
    }

    async function createplayer() {
        if (player.playerName) {
            let response = await fetch('/api/players/create', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(player),
            });
            setPlayer({playerName: ""});
            return await response.json();
        }
    }



    const handleChange = (e) => {
        setPlayer({...player, [e.target.name]:e.target.value});
    }

    return (
        <div className="container-sm d-flex justify-content-center m-3 p-3">
            <form className="w-50" onSubmit={handleSubmit}>
                <label className="form-label mb-4" htmlFor="playerName">
                    Input your name
                </label>
                <input type="text" className="form-control mb-4" name="playerName" value={player.playerName} onChange={handleChange}/>
                <button className="btn btn-lg btn-primary mt-3 w-50f" type="submit">Start</button>
            </form>
        </div>
    );
}

export default NewGame;