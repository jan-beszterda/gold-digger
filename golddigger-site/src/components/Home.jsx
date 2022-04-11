import {Link} from "react-router-dom";

function Home() {
    return (
        <div className="container-sm my-3 p-3 d-flex flex-column align-items-center">
            <img src="/graphics/logo.png" className="image my-2" alt="GoldDigger"/>
            <Link to="/new" className="w-50">
                <button className="btn btn-primary btn-lg my-2 w-100">Start new game</button>
            </Link>
            <Link to="/load" className="w-50">
                <button className="btn btn-primary btn-lg my-2 w-100">Load game</button>
            </Link>
            <Link to="/scores" className="w-50">
                <button className="btn btn-primary btn-lg my-2 w-100">High scores</button>
            </Link>
            <Link to="/about" className="w-50">
                <button className="btn btn-primary btn-lg my-2 w-100">About</button>
            </Link>
        </div>
    );
}

export default Home;