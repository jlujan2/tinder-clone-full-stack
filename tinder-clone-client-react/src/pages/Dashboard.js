import TinderCard from "react-tinder-card";
import { useState, useEffect } from "react";
import ChatContainer from '../components/ChatContainer'
import axios from 'axios'
import {useCookies} from 'react-cookie'

const Dashboard = () => {
  const [user, setUser] = useState(null)
  const[genderedUsers,setGenderedUsers] = useState([])
  const [cookies, setCookies, removeCookie] = useCookies(['user'])

  const userId = cookies.UserId
  const authToken = cookies.AuthToken
  const headers = {'Authorization': `Bearer ${authToken}`}

  const getUser = async () => {
    const headers = {'Authorization': `Bearer ${authToken}`}
    try {
      const response = await axios.get('http://localhost:8080/api/v1/users' , 
       {params: {id : userId},
       headers: {'Authorization': `Bearer ${authToken}`}}
)
      setUser(response.data)
    } catch(error) {
      console.log(error)
    }
  } 

  const getGenderedUsers = async () => {
    try{
      const response = await axios.get('http://localhost:8080/api/v1/users/gendered-user', {
        params: {id: userId, gender: user?.gender_interest},
        headers: {'Authorization': `Bearer ${authToken}`}}
      )
      setGenderedUsers(response.data)
    } catch(error) {
      console.log(error)
    }
  }

  useEffect(() => {
      getUser()
  }, [])

  useEffect(() => {
    if(user)
      getGenderedUsers()
  }, [user])

  const [lastDirection, setLastDirection] = useState();

  const updatedMatches = async (matchedUserId) => {
    try {
      const response = await axios.put('http://localhost:8080/api/v1/users/match', 
        null, 
       {
        params : {userId: userId, matchedUserId: matchedUserId},
        headers: {'Authorization': `Bearer ${authToken}`}
      })
      console.log(response)
      //getUser()
    } catch(error) {
      console.log(error)
    }
  };

  const swiped = (direction, swipedUserId) => {
    if(direction === 'right') {
      updatedMatches(swipedUserId)
    }
    setLastDirection(direction);
  };

  const outOfFrame = (name) => {
    console.log(name + " left the screen!");
  };

  return (
    <>
    { user && 
    <div className="dashboard">
      <ChatContainer user={user}/>
      <div className="swipe-container">
        <div className="card-container">
          {genderedUsers?.map((genderedUser) => (
            <TinderCard
              className="swipe"
              key={genderedUser.user_id}
              onSwipe={(dir) => swiped(dir, genderedUser.user_id)}
              onCardLeftScreen={() => outOfFrame(genderedUser.first_name)}>
              <div style={{ backgroundImage: "url(" + genderedUser.url + ")" }}
                className="card"
              ><h3>{genderedUser.first_name}</h3>
              </div>
            </TinderCard>
          ))}
          <div className="swipe-info">
            {lastDirection ? <p>You swiped {lastDirection}</p> : <p/>}
          </div>
        </div>
      </div>
    </div>
    }
    </>
  );
};

export default Dashboard;
