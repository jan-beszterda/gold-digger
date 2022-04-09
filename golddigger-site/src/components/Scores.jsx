import {useEffect, useState} from "react";

function Scores() {
    const [players, setPlayers] = useState([]);

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
        await fetch('/api/players').then(response => response.json()).then(data => {
            for (const player of data) {
                allPlayers.push(player);
            }
        })
        return allPlayers;
    }

    return (
        <div className="container-sm d-flex justify-content-center m-4 p-4">
            <table className="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Total gold</th>
                </tr>
                </thead>
                <tbody>
                { players && players.sort((player1, player2) => player2.goldAmount - player1.goldAmount).map((player, index) => (
                    <tr key={index}>
                        <th scope="row">{index+1}</th>
                        <td>{player.playerName}</td>
                        <td>{player.goldAmount}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Scores;