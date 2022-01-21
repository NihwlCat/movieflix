import './style.css'
import  { Link, useLocation } from 'react-router-dom'
import { getTokenDecoded, isAuthenticated, makeLogout } from 'core/functions';
import { useEffect, useState } from 'react';

type User = {
    name: string;
    role: string;
}

const Navbar = () => {
    const [user, setUser] = useState<User>();
    const location = useLocation()

    useEffect(() => {
        if(isAuthenticated()){
            setUser({name: getTokenDecoded().user_name, role: getTokenDecoded().authorities.includes('ROLE_MEMBER') ? 'MEMBRO' : 'VISITANTE'})
        }
    },[location])

    return <div className="navbar-container">
        <Link to="/"><h3 className="navbar-logo-text">MovieFlix</h3></Link>
        {user && (
            <div className="navbar-userinfo">
                <p>{user.name}</p>
                <span>{user.role}</span>
                <button onClick={() => makeLogout()}>SAIR</button>
            </div>)}
    </div>
}

export default Navbar;