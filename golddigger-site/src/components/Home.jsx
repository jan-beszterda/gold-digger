function Home(props) {
    return (
        <div className="contianer-sm my-3 d-flex flex-column align-items-center">
            <img src="/graphics/logo.png" className="image my-2" alt="GoldDigger"/>
            <button className="btn btn-primary btn-lg my-2">Start new game</button>
            <button className="btn btn-primary btn-lg my-2">Load game</button>
            <button className="btn btn-primary btn-lg my-2">High scores</button>
            <button className="btn btn-primary btn-lg my-2">About</button>
        </div>
    );
}

export default Home;