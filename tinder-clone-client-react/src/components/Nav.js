import logo from "../images/black-tinder-logo.png";
import colorLogo from "../images/color-tinder-logo.jpg";

const Nav = ({ authToken, minimal, setShowModal, showModal, setIsSignUp }) => {
  
  const handleClick = () => {
    setShowModal(true);
    setIsSignUp(false);
  };

  return (
    <nav>
      <div className="logo-container">
        <img className="logo" src={minimal ? colorLogo : logo}></img>
      </div>

      {!authToken && !minimal && <button 
            className="nav-button"
            onClick={handleClick}
            disabled={showModal}
            >Log in</button>}
    </nav>
  );
};

export default Nav;
