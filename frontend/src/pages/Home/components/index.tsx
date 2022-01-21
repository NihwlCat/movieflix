import { makeRequest, storageSessionData } from 'core/functions'
import QueryString from 'qs'
import { useState } from 'react'
import { useHistory, useLocation } from 'react-router-dom'
import './style.css'

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'movie_flix'
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'password'

type LoginDate = {
    password: string;
    username: string;
}

type LocationState = {
    from: string;
}

const makeLogin = (data: LoginDate) => {
    const token = `${CLIENT_ID}:${CLIENT_SECRET}`

    const headers = {
        Authorization: `Basic ${window.btoa(token)}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    }

    const payload = QueryString.stringify({...data, grant_type: 'password'})

    return makeRequest({url: '/oauth/token', data: payload, method: 'POST', headers: headers})
}

const LoginForm = () => {
    const [login, setLogin] = useState<LoginDate>({
        password: '',
        username: ''
    })
    const [error, setError] = useState(false)

    const location = useLocation<LocationState>()
    const history = useHistory()
    const { from } = location.state || { from: { pathname: '/movies'} }

    return <div className="custom-border login-container">
        <p>LOGIN</p>
        <div className="login-field">
            <input placeholder="Email" value={login.username} onChange={event => setLogin({...login, username: event.target.value})}></input>
        </div>
        <div className="login-field">
            <input placeholder="Senha" value={login.password} type="password" onChange={event => setLogin({...login, password: event.target.value})}></input>
            {error && (
                <div className="login-error-field">Email ou senha inválido.</div>
            )}
        </div>
        <button className="primary-button" style={{ width: '100%', height: '65px' }}  onClick={() => {
            makeLogin(login).then(response => {
                setError(false)
                storageSessionData(response.data)
                history.replace(from)
            })
            .catch(() => setError(true))
        }}>FAZER LOGIN</button>
    </div>
}

export default LoginForm;