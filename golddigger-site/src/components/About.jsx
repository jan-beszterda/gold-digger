function About() {
    return (
        <div className="container-sm d-flex flex-column justify-content-center align-items-center m-4 p-4">
            <img src="/graphics/logo.png" className="image my-2" alt="GoldDigger" />
            <h1 className="text-center mb-4">GoldDigger</h1>
            <p className="text-center mb-2">A simple strategy game created with Java, Spring Boot, Hibernate, PostgreSQL and React.JS</p>
            <p className="text-center">Authors: Jan Beszterda & Damir Kahvic</p>
            <h2 className="text-center my-4">Introduction</h2>
            <p className="text-center mb-2">In GoldDigger you become a miner, fighting for survival and trying to dig some gold on the side.<br/>
                You start at a generic easy mine, with a generic weak pickaxe and two generic items in your backpack.<br/>
                Every day you can do 3 things: you can dig for gold, you can eat something from your backpack to restore your health,
                you can visit the shop to buy something or you can move to a new mine.<br/>
                Once you use your 3 actions, you need to sleep to restore your energy.<br/>
                Watch out for your health, it's dangerous to be a miner and everything you do can have serious consequences.
                Your pickaxe can break, the mine can run dry or you can simply die if you're not careful enough.<br/>
                Once you die, there's no going back, you're out and your player is gone. If you're lucky you might top the score board<br/>
                Don't worry if you close the browser window, the game saves your player with every action.
            </p>
            <p className="text-center my-2">So, if you're ready... "Get rich or die tryin'"</p>
        </div>
    );
}

export default About;