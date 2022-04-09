import { Link } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import { useState } from "react";

function Header() {
    const [showMenu, setShowMenu] = useState(false);

    const toggleMenu = () => {
        setShowMenu(!showMenu);
    };

    const show = showMenu ? " show" : "";
    return (
        <header className="container-fluid p-3">
            <nav className="navbar">
                <div className="container-fluid" id="nav-container">
                    <Link to="/" className="navbar-brand p-2 m-0">
                        <img src="/graphics/logo.png" className="logo d-inline-block align-text-bottom me-2" alt="Logo" />
                        GoldDigger
                    </Link>
                    <button
                        className="navbar-toggler p-2"
                        type="button"
                        onClick={toggleMenu}
                    >
                        <FaBars />
                    </button>
                    <div className={"collapse navbar-collapse mt-3 px-2" + show}>
                        <ul className="navbar-nav">
                            <li className="nav-item mx-0 mx-lg-2 align-self-end">
                                <Link className="nav-link" to="/new">
                                    New game
                                </Link>
                            </li>
                            <li className="nav-item mx-0 mx-lg-2 align-self-end">
                                <Link className="nav-link" to="/load">
                                    Load game
                                </Link>
                            </li>
                            <li className="nav-item mx-0 mx-lg-2 align-self-end">
                                <Link className="nav-link" to="/scores">
                                    High scores
                                </Link>
                            </li>
                            <li className="nav-item mx-0 mx-lg-2 align-self-end">
                                <Link className="nav-link" to="/about">
                                    About
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;