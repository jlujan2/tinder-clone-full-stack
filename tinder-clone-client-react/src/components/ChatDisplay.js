import { useState, useEffect } from "react"
import Chat from "./Chat"
import ChatInput from "./ChatInput"
import axios from "axios"
import {useCookies} from 'react-cookie'

const ChatDisplay = ({user, clickedUser}) => {
    const userId = user?.user_id
    const clickedUserId = clickedUser?.user_id
    const [userMessages, setUserMessages] = useState(null)
    const [clickedUserMessages, setClickedUserMessages] = useState(null)

    const [cookies, setCookies, removeCookie] = useCookies(['user'])

    const authToken = cookies.AuthToken

    const getUserMessages = async () => {
        try{
            const response = await axios.get('http://localhost:8080/api/v1/messages', {
                params: {userId: userId, correspondingUserId: clickedUserId},
                headers: {'Authorization': `Bearer ${authToken}`}
            })

            setUserMessages(response.data);
        } catch(error) {
            console.log(error)
        }
    }

    const getClickedUserMessages = async () => {
        try{
            const response = await axios.get('http://localhost:8080/api/v1/messages', {
                params: {userId: clickedUserId, correspondingUserId: userId},
                headers: {'Authorization': `Bearer ${authToken}`}
            })

            setClickedUserMessages(response.data);
        } catch(error) {
            console.log(error)
        }
    }

    useEffect(() => {
        getUserMessages(userId, clickedUserId)
        //getClickedUserMessages(clickedUserId, userId)
    }, [])

    const messages = []

    userMessages?.forEach(message => {
        const formattedMessage = {}
        formattedMessage['name'] = userId == message.fromUserId ? user?.first_name : clickedUser.first_name
        formattedMessage['img'] = userId == message.fromUserId ? user?.url : clickedUser.url
        formattedMessage['message'] = message?.message
        formattedMessage['timestamp'] = message?.timestamp
        messages.push(formattedMessage)
    })


    const descendingOrderMessages = messages?.sort((a,b) => a.timestamp.localeCompare(b.timestamp))

    return(
        <>
        <Chat descendingOrderMessages={descendingOrderMessages}/>
        <ChatInput user = {user} 
                   clickedUser = {clickedUser} 
                   getUserMessages = {getUserMessages}
                   //getClickedUserMessages= {getClickedUserMessages}/
                   />
        <div className="">
         
        </div>
        </>
    )
}

export default ChatDisplay