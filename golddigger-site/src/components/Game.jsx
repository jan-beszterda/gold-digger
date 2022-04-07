import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function Game(props) {
    const [currentShop, setCurrentShop] = useState({});
    const [currentPlayer, setCurrentPlayer] = useState();
    const [showShop, setShowShop] = useState(false);
    const [showBackpack, setShowBackpack] = useState(false);
    const [chosenItemId, setChosenItemId] = useState(0);

    const params = useParams();

    useEffect(() => {
        const getCurrentPlayer = async () => {
            let response = await fetch('/api/players/' + params.playerId);
            let result = await response.json();
            setCurrentPlayer(result);
        }
        getCurrentPlayer();
        setCurrentShop(props.shop);
        console.log(currentShop);
    },[]);

    /*const handleFieldChange = (fieldName, fieldValue) => {
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
    }*/

    /*async function handleSubmit(e) {
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
    }*/

    const onChosenItemChange = (e) => {
        let itemId = +e.currentTarget.value;
        setChosenItemId(itemId);
    }

    function openShop() {
        setShowShop(true);
        const actionButtonsHolder = document.getElementById("actionButtons");
        const buttons = actionButtonsHolder.querySelectorAll('button');
        for (let button in buttons) {
            button.disabled = true;
        }
    }

    function closeShop() {
        setShowShop(false);
        const actionButtonsHolder = document.getElementById("actionButtons");
        const buttons = actionButtonsHolder.querySelectorAll('button');
        for (let button in buttons) {
            button.disabled = false;
        }
    }

    async function buyItem() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/buyItem/' + chosenItemId, {
            method: 'PUT'
        });
        console.log(response);
        let result = await response.json();
        console.log(result);
        setCurrentPlayer(result);
        closeShop();
    }

    return (
        <div className="container-sm m-4">
            <div className="row border">
                <div className="col border p-4">
                    { currentPlayer && (
                        <>
                            <span className="d-block border-2 border-bottom my-2">Name: {currentPlayer.playerName}</span>
                            <span className="d-block">Health: {currentPlayer.health}</span>
                            <span className="d-block">Remaining actions: {currentPlayer.actionsRemaining}</span>
                            <span className="d-block">Gold: {currentPlayer.goldAmount}</span>
                            <div className="border-top border-bottom my-2">
                                Mine:
                                <span className="d-block">Name: {currentPlayer.currentMine.mineName}</span>
                                <span className="d-block">Gold: {currentPlayer.currentMine.totalGold}</span>
                                <span className="d-block">Difficulty: {currentPlayer.currentMine.difficulty}</span>
                            </div>
                            <div className="border-bottom my-2">
                                Pickaxe:
                                <span className="d-block">Name: {currentPlayer.pickaxe.itemName}</span>
                                <span className="d-block">Strength: {currentPlayer.pickaxe.strength}</span>
                                <span className="d-block">Condition: {currentPlayer.pickaxe.condition}</span>
                            </div>
                            <div className="my-2">
                                Backpack:
                                <span className="d-block">Weight: {currentPlayer.backpack.maxWeight}</span>
                                Items:
                                {currentPlayer.backpack.foodItems.map(item => (
                                        <span className="d-block" key={item.itemId}>{item.itemName}, Effect: +{item.healthEffect} health, Weight: {item.weight}</span>
                                ))}
                            </div>
                        </>
                    )}
                </div>
                <div className="col border p-4">
                    { showShop && (
                        <>
                            <h3 className="mb-3">Welcome to {currentShop.shopName}</h3>
                            { currentShop.shopInventory.map( (item) => (
                                <div className="my-2" key={item.itemId}>
                                    <input
                                        type="radio"
                                        className="btn-check"
                                        name="btnradio"
                                        id={item.itemId}
                                        autoComplete="off"
                                        value={item.itemId}
                                        onChange={onChosenItemChange}
                                    />
                                    <label className="btn btn-primary btn-lg" htmlFor={item.itemId}>
                                        {item.itemName}, Price: {item.itemPrice}
                                        {item.healthEffect && (<>, Health effect: {item.healthEffect}</>)}
                                        {item.weight && (<>, Weight: {item.weight}</>)}
                                        {item.strength && (<>, Strength: {item.strength}</>)}
                                    </label>
                                </div>))
                            }
                            <button className="btn btn-primary btn-lg mt-4" onClick={buyItem}>BUY ITEM</button>
                        </>
                    )}
                </div>
            </div>
            <div className="row border">
                <div className="col border d-flex justify-content-evenly p-4" id="actionButtons">
                    <button className="btn btn-primary btn-lg">DIG</button>
                    <button className="btn btn-primary btn-lg">EAT</button>
                    <button className="btn btn-primary btn-lg" onClick={openShop}>VISIT SHOP</button>
                    <button className="btn btn-primary btn-lg">MOVE</button>
                    <button className="btn btn-primary btn-lg">SLEEP</button>
                </div>
            </div>
            <div className="row border">
                <div className="col border p-4">
                    TODO
                </div>
            </div>
        </div>
    );
}

export default Game;