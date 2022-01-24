import axios, { AxiosRequestConfig } from "axios"
import jwtDecode from "jwt-decode"

type SessionData = {
    access_token: string;
    token_type: string;
    expires_in: number;
    scope: string;
    firstname: string;
    id: number;
}

type AccessToken = {
    exp: number;
    user_name: string;
    authorities: string[];
}

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'https://localhost:8080'
// const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'https://nihwl-movieflix.herokuapp.com'

export const makeRequest = (params: AxiosRequestConfig) => {
    return axios({
        ...params,
        baseURL: BASE_URL
    })
}

const recoverSessionData = () => {
    const sessionData = localStorage.getItem('sessionData') ?? '{}'
    return JSON.parse(sessionData) as SessionData
}

export const makePrivateRequest = (params: AxiosRequestConfig) => {
    const token = recoverSessionData()
    const headers = { Authorization: `Bearer ${token.access_token}` }

    return makeRequest({...params, headers});
}

export const storageSessionData = (sessionDate: SessionData) => {
    localStorage.setItem('sessionData', JSON.stringify(sessionDate))
}

export const getTokenDecoded = () => {
    const sessionData = recoverSessionData()
    try {
        const tokenDecoded = jwtDecode(sessionData.access_token)
        return tokenDecoded as AccessToken; 
    } catch (error) {
        return { } as AccessToken
    }
}

const isTokenValid = () => {
    const { exp } =  getTokenDecoded();
    return Date.now() <= exp * 1000
}

export const isAuthenticated = () => {
    const sessionData = recoverSessionData();
    return sessionData.access_token && isTokenValid();
}

export const canReview = () => {
    const { authorities } = getTokenDecoded();
    return authorities && authorities.some(auth => auth === 'ROLE_MEMBER');
}

export const makeLogout = () => {
    localStorage.removeItem('sessionData')
    window.location.href = "/"
}

axios.interceptors.response.use((response) => {return response}, (error) => {
    if(error.response.status === 401){
        window.location.href = "/"
        makeLogout()
    }
    return Promise.reject(error);
})