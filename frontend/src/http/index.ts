import axios from "axios"

export const $host = axios.create({
    baseURL: 'http://localhost:9098/api'
})

export const $host12 = axios.create({
    baseURL: 'http://localhost:9099/api',
    withCredentials: false,
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        "Access-Control-Allow-Headers": 'Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token',
    }
})
export const $host2 = axios.create({
    baseURL: 'http://localhost:10100'
})
