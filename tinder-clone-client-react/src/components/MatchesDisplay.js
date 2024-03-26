import axios from 'axios';
import { useEffect, useState } from 'react';
import {useCookies} from 'react-cookie'

const MatchesDisplay = ({ matches=[], setClickerUser }) => {
    
    const [matchedProfiles, setMatchedProfiles] =  useState(null)
    const [cookies, setCookies, removeCookie] = useCookies(['user'])
    const userId = cookies.userId
    const authToken = cookies.AuthToken

    const getMatches = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/users/match', {
                params: { matchesId: matches },
                paramsSerializer: {
                    indexes: null, // use brackets with indexes
                },
                headers: {'Authorization': `Bearer ${authToken}`}
            });

            setMatchedProfiles(response.data);
        } catch(error) {
            console.log(error)
        }
    }
    
    useEffect(() => {
        getMatches();
    }, [matches]);

    return(
        <div className="matches-display">

            {matchedProfiles?.map((match) => (
                <div key={match.user_id} className="match-card" onClick={() => setClickerUser(match)}>
                    <div className="img-container">
                        <img src={match?.url} alt={match?.first_name + ' profile'}/>
                    </div>
                    <h3>{match?.first_name}</h3>
                </div>
            ))}

        </div>
    )
}

export default MatchesDisplay