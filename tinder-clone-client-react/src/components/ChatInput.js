import {useState} from 'react'
import axios from 'axios'
import {useCookies} from 'react-cookie'

const ChatInput = ({ user, clickedUser, getUserMessages}) => {
    const [textarea, setTextArea] = useState("")
    const userId = user?.user_id
    const clickedUserId = clickedUser?.user_id

    const [cookies, setCookies, removeCookie] = useCookies(['user'])
    const authToken = cookies.AuthToken

    const addMessage = async () => {
        const message = {
            timestamp: new Date().toISOString(),
            fromUserId: userId,
            toUserId: clickedUserId,
            message: textarea
        }

        try {
            await axios.post('http://localhost:8080/api/v1/messages', message,
            {
                headers : {'Authorization': `Bearer ${authToken}`}
            })
            getUserMessages()
            setTextArea("")
        } catch(error) {
            console.log(error)
        }
    }

    return(
   
        <div className="chat-input">
            <textarea value={textarea} onChange={(e) => setTextArea(e.target.value)}/>
            <button className="secondary-button" onClick={addMessage}>Submit</button>
        </div>
    )
}

export default ChatInput