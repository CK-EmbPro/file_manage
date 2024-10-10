import { ServerActionResponse } from "@/types"
import { Server } from "http"

const base_url = 'http://localhost:8000/file/'
export const download = async (file_name:string):Promise<string>=>{
    const res = await fetch(`${base_url}download/${file_name}`)
    const blob = await res.blob();
    const url = URL.createObjectURL(blob)
    console.log('downlod url', url);
    return url
}

export const upload = async (formData:FormData): Promise<ServerActionResponse>=>{
    const res = await fetch(`${base_url}upload`, {
        method: 'POST',
        body: formData
    })

    const data:ServerActionResponse = await res.json()
    return data;
    
    
}

export const getFileNames = async ():Promise<ServerActionResponse>=>{
    const res = await fetch(`${base_url}all`);
    const data:ServerActionResponse = await res.json();
    return data
}