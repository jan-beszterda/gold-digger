import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

function Game(props) {
    const [currentShop, setCurrentShop] = useState({});
    const [currentPlayer, setCurrentPlayer] = useState();
    const [showShop, setShowShop] = useState(false);
    const [showBackpack, setShowBackpack] = useState(false);
    const [chosenItemId, setChosenItemId] = useState(0);
    const [message, setMessage] = useState("");
    const [buttonsDisabled, setButtonsDisabled] = useState(false);

    const params = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        let isCancelled = false;
        const getCurrentPlayer = async () => {
            let response = await fetch('/api/players/' + params.playerId);
            let result = await response.json();
            setCurrentPlayer(result);
        }
        if (!isCancelled) {
            getCurrentPlayer();
            setCurrentShop(props.shop)
        }
        return () => {
            isCancelled = true;
        }
    },[params.playerId, props.shop]);

    useEffect(() => {
        if (currentPlayer !== undefined && (currentPlayer.health === 0 && currentPlayer.maxActions === 0)) {
            navigate('/gameover');
        }
    }, [currentPlayer]);

    async function dig() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/dig', {
                method: 'PUT'
            });
        let resultText = await response.text();
        let result = resultText.length ? JSON.parse(resultText) : null;
        if (result === null) {
            setMessage("Error: something went wrong!")
            setTimeout(() => {
                setMessage("");
            }, 5000);
            return false;
        }
        let healthDecrease = currentPlayer.health - result.health;
        let damage = currentPlayer.pickaxe.condition - result.pickaxe.condition;
        let gold = result.goldAmount - currentPlayer.goldAmount;
        setMessage("Bang! You hit the ground with your pickaxe and... You got " + gold + " gold. You lost " + healthDecrease + " health. You damaged your pickaxe by " + damage +".");
        setCurrentPlayer(result);
        setTimeout(() => {
            setMessage("");
        }, 5000);
        return true;
    }

    async function eat() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/eat/' + chosenItemId, {
                method: 'PUT'
            });
        let resultText = await response.text();
        let result = resultText.length ? JSON.parse(resultText) : null;
        if (result === null) {
            setMessage("Error: something went wrong!")
            setTimeout(() => {
                setMessage("");
            }, 5000);
            return false;
        }
        let healthIncrease = result.health - currentPlayer.health;
        setMessage("Noms! " + healthIncrease + " health restored.");
        closeBackpack();
        setCurrentPlayer(result);
        setChosenItemId(0);
        setTimeout(() => {
            setMessage("");
        }, 5000);
        return true;
    }

    async function buyItem() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/buyItem/' + chosenItemId, {
            method: 'PUT'
        });
        let resultText = await response.text();
        let result = resultText.length ? JSON.parse(resultText) : null;
        if (result === null) {
            setMessage("Error: something went wrong! Check if you have enough money and/or space in your backpack!");
            setTimeout(() => {
                setMessage("");
            }, 5000);
            return false;
        }
        let item = currentShop.shopInventory.filter((item) => item.itemId === chosenItemId);
        let payment = currentPlayer.goldAmount - result.goldAmount;
        setMessage("Congrats! You bought " + item[0].itemName + ". You paid " + payment + ".");
        closeShop();
        setChosenItemId(0);
        setCurrentPlayer(result);
        setTimeout(() => {
            setMessage("");
        }, 5000);
        return true;
    }

    async function move() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/move', {
                method: 'PUT'
            });
        let resultText = await response.text();
        let result = resultText.length ? JSON.parse(resultText) : null;
        if (result === null) {
            setMessage("Error: something went wrong!")
            setTimeout(() => {
                setMessage("");
            }, 5000);
            return false;
        }
        setMessage("New mine '" + result.currentMine.mineName + "' discovered.");
        setCurrentPlayer(result);
        setTimeout(() => {
            setMessage("");
        }, 5000);
        return true;
    }

    async function sleep() {
        let response = await fetch('/api/players/' + currentPlayer.playerId + '/sleep', {
            method: 'PUT'
        });
        let resultText = await response.text();
        let result = resultText.length ? JSON.parse(resultText) : null;
        if (result === null) {
            setMessage("Error: something went wrong!")
            setTimeout(() => {
                setMessage("");
            }, 5000);
            return false;
        }
        let healthDecrease = currentPlayer.health - result.health;
        setMessage("Happy dreaming! You lost " + healthDecrease + " health. Remaining actions restored.");
        setCurrentPlayer(result);
        setTimeout(() => {
            setMessage("");
        }, 5000);
        return true;
    }

    const onChosenItemChange = (e) => {
        let itemId = +e.currentTarget.value;
        setChosenItemId(itemId);
    }

    function openShop() {
        let col = document.getElementById("shop-backpack-col");
        col.classList.remove("logo-background");
        setShowShop(true);
        setButtonsDisabled(true);
    }

    function openBackpack() {
        let col = document.getElementById("shop-backpack-col");
        col.classList.remove("logo-background");
        setShowBackpack(true);
        setButtonsDisabled(true);
    }

    function closeShop() {
        let col = document.getElementById("shop-backpack-col");
        col.classList.add("logo-background");
        setShowShop(false);
        setButtonsDisabled(false);
    }

    function closeBackpack() {
        let col = document.getElementById("shop-backpack-col");
        col.classList.add("logo-background");
        setShowBackpack(false);
        setButtonsDisabled(false);
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
                                <span className="d-block">Weight: {currentPlayer.backpack.foodItems.reduce((previousWeight, item) => previousWeight + item.weight, 0.0)}
                                    /{currentPlayer.backpack.maxWeight}
                                </span>
                                Items:
                                {currentPlayer.backpack.foodItems.map(item => (
                                        <span className="d-block" key={item.itemId}>{item.itemName}, Effect: +{item.healthEffect} health, Weight: {item.weight}</span>
                                ))}
                            </div>
                        </>
                    )}
                </div>
                <div className="col border p-4 logo-background" id="shop-backpack-col">
                    { showShop && (
                        <>
                            <h3 className="mb-3">Welcome to {currentShop.shopName}</h3>
                            { currentShop.shopInventory.map( (item) => (
                                <div className="form-check my-2" key={item.itemId}>
                                    <input
                                        type="radio"
                                        className="form-check-input"
                                        name="btnradio"
                                        id={item.itemId}
                                        autoComplete="off"
                                        value={item.itemId}
                                        onChange={onChosenItemChange}
                                    />
                                    <label className="form-check-label" htmlFor={item.itemId}>
                                        {item.itemName}, Price: {item.itemPrice}
                                        {item.healthEffect && (<>, Health effect: {item.healthEffect}</>)}
                                        {item.weight && (<>, Weight: {item.weight}</>)}
                                        {item.strength && (<>, Strength: {item.strength}</>)}
                                    </label>
                                </div>))
                            }
                            <div className="mt-4">
                                <button className="btn btn-primary btn-lg ms-2 me-1" style={{width: "45%"}} onClick={buyItem}>BUY ITEM</button>
                                <button className="btn btn-primary btn-lg ms-1 me-2" style={{width: "45%"}} onClick={closeShop}>BACK</button>
                            </div>
                        </>
                    )}
                    { showBackpack && (
                        <>
                            <h3 className="mb-3">Your backpack</h3>
                            { currentPlayer.backpack.foodItems.map( (item) => (
                                <div className="form-check my-2" key={item.itemId}>
                                    <input
                                        type="radio"
                                        className="form-check-input"
                                        name="btnradio"
                                        id={item.itemId}
                                        autoComplete="off"
                                        value={item.itemId}
                                        onChange={onChosenItemChange}
                                    />
                                    <label className="form-check-label" htmlFor={item.itemId}>
                                        {item.itemName}, Health effect: {item.healthEffect}
                                    </label>
                                </div>))
                            }
                            <div className="mt-4">
                                <button className="btn btn-primary btn-lg ms-2 me-1" style={{width: "45%"}} onClick={eat}>EAT</button>
                                <button className="btn btn-primary btn-lg ms-1 me-2" style={{width: "45%"}} onClick={closeBackpack}>BACK</button>
                            </div>
                        </>
                    )}
                </div>
            </div>
            <div className="row border">
                { (currentPlayer && !buttonsDisabled) && (
                <div className="col border d-flex justify-content-evenly p-4">
                    { (currentPlayer.actionsRemaining !== 0 && currentPlayer.pickaxe !== null && currentPlayer.currentMine !== null) && <button className="btn btn-primary btn-lg" style={{width: "15%"}} id="actionButton1" onClick={dig}>DIG</button> }
                    { (currentPlayer.actionsRemaining !== 0 && currentPlayer.backpack.foodItems.length !== 0) && <button className="btn btn-primary btn-lg" style={{width: "15%"}} id="actionButton2" onClick={openBackpack}>EAT</button> }
                    { (currentPlayer.actionsRemaining !== 0 && currentPlayer.goldAmount > 0) && <button className="btn btn-primary btn-lg" style={{width: "15%"}} id="actionButton3" onClick={openShop}>VISIT SHOP</button> }
                    { currentPlayer.actionsRemaining !== 0 && <button className="btn btn-primary btn-lg" style={{width: "15%"}} id="actionButton4" onClick={move}>MOVE</button> }
                    <button className="btn btn-primary btn-lg" style={{width: "15%"}} id="actionButton5" onClick={sleep}>SLEEP</button>
                </div>)}
            </div>
            <div className="row border">
                <div className="col border p-4">
                    <span>{message}</span>
                </div>
            </div>
        </div>
    );
}

export default Game;