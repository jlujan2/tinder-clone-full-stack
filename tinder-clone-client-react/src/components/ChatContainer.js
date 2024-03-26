import ChatHeader from "./ChatHeader"
import MatchesDisplay from './MatchesDisplay'
import ChatDisplay from './ChatDisplay'
import { useState } from "react"

const ChatContainer = ({user}) => {
    const [clickedUser, setClickerUser] = useState(null)

    return(
        <div className="chat-container">
            <ChatHeader user={user}/>

            <div>
                <button className="option" onClick={() => setClickerUser(null)}>Matches</button>
                <button className="option" disabled={!clickedUser}>Chat</button>
            </div>

            {!clickedUser && <MatchesDisplay matches={user.matches} setClickerUser={setClickerUser}/>}

            {clickedUser && <ChatDisplay user={user} clickedUser={clickedUser}/>}
        </div>
    )
}

export default ChatContainer