import { safeParse } from "valibot";
import { DraftUserSchema } from "../types";
import axios, { isAxiosError } from "axios";
import api from "../lib/axios";

type UserData = {
    [k: string]: FormDataEntryValue;
}

export async function registerUser(data: UserData){

    try {
        const result = safeParse(DraftUserSchema, {
            username: data.usuario,
            password: data.password
        });
        if(result.success){
            const url = `${import.meta.env.VITE_API_URL}/api/users/register`;

            await axios.post(url, {
                username: result.output.username,
                password: result.output.password
            });

            return null;

        }else{
            throw new Error('Datos no validos');
        }
        
    } catch (error) {
        if (axios.isAxiosError(error) && error.response?.status === 400) {
            return error.response.data; 
        }
        console.error(error);
        throw new Error("Error inesperado");
    }
}

export async function loginUser(data: UserData){
    try {
        const result = safeParse(DraftUserSchema, {
            username: data.usuario,
            password: data.password
        })

        if(result.success){
            const url = `${import.meta.env.VITE_API_URL}/login`;

            const response = await axios.post(url, {
                username: result.output.username,
                password: result.output.password
            });

            // Guardar el token en localStorage
            const { token, username } = response.data;          
            localStorage.setItem("authToken", token);
            localStorage.setItem("username", username);

            return null;

        }else{
            throw new Error('Datos no validos');
        }
    } catch (error) {
        if (axios.isAxiosError(error) && error.response?.status === 401) {
            console.log("error");
            
            return error.response.data; 
        }
        console.error(error);
        throw new Error("Error inesperado");
    }
}

export async function getUser(){
    try {
        const { data } = await api('/auth/user')
        return data
    } catch (error) {
        if(isAxiosError(error) && error.response){
            throw new Error(error.response.data.error)
        }
    }
}

