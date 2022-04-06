import {Link} from "react-router-dom";

function Home(props) {
    return (
        <div className="container-sm my-3 p-3 d-flex flex-column align-items-center">
            <img src="/graphics/logo.png" className="image my-2" alt="GoldDigger"/>
            <Link to="/new">
                <button className="btn btn-primary btn-lg my-2">Start new game</button>
            </Link>
            <Link to="/load">
                <button className="btn btn-primary btn-lg my-2">Load game</button>
            </Link>
            <Link to="/scores">
                <button className="btn btn-primary btn-lg my-2">High scores</button>
            </Link>
            <Link to="/about">
                <button className="btn btn-primary btn-lg my-2">About</button>
            </Link>
        </div>
    );
}

export default Home;