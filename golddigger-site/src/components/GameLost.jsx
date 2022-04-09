import {Link} from "react-router-dom";

function GameLost() {
    return (
        <div className="container-sm d-flex flex-column justify-content-center align-items-center m-4 p-4">
            <h1 className="text-center mb-4">YOU LOST</h1>
            <Link to="/">
                <button className="btn btn-primary btn-lg">Back to home page</button>
            </Link>
        </div>
    );
}

export default GameLost;