import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import Onboarding from './pages/Onboarding';
import {BrowserRouter, Routes, Route} from  'react-router-dom';
import {useCookies} from 'react-cookie'

const App = () => {
  const [cookies, setCookies, removeCookie] = useCookies(['user'])

  const authToken = cookies.AuthToken

  return (
   <BrowserRouter>
    <Routes>
      <Route path="/" element={<Home/>}></Route>
      {authToken && <Route path="/Dashboard" element={<Dashboard/>}></Route>}
      {authToken && <Route path="/Onboarding" element={<Onboarding/>}></Route>}
    </Routes>
   </BrowserRouter>
  );
}

export default App;
