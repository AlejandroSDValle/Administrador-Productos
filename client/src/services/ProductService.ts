import { safeParse , number, parse, string, transform, pipe} from "valibot";
import { DraftProductSchema, ProductsSchema, Product, ProductSchema } from "../types"
import axios from "axios";
import { toBoolean } from "../utils";
import api from "../lib/axios";

type ProductData = {
    [k: string]: FormDataEntryValue
}

export async function addProduct(data: ProductData){
    try{
        const result = safeParse(DraftProductSchema, {
            name: data.name,
            price: +data.price
        });
        if(result.success){
            await api.post('/api/productos', {
                name: result.output.name,
                price: result.output.price,
            });
        }else{
            throw new Error('Datos no validos');
        }
    }catch(error){
        console.log(error);
        
    }
}

export async function getProducts(){
    try{
        const {data} = await api('/api/productos')
        
        const result = safeParse(ProductsSchema, data)
        if(result.success){
            return result.output
        }else{
            throw new Error('Hubo un error...')
        }
    }catch(error){
        console.log(error);
        
    }
}

export async function getProductsById(id : Product['id']){
    try{
        const { data } = await api.get(`/api/productos/${id}`);
        
        const result = safeParse(ProductSchema, data)
        
        if(result.success){
            return result.output
        }else{
            throw new Error('Hubo un error...')
        }
        
    }catch(error){
        console.log(error);
    }
}

export async function updateProduct(data : ProductData, id : Product['id']){
    try{
        const NumberSchema = pipe(string(), transform(Number), number());
        const result = safeParse(ProductSchema,{
            id,
            name: data.name,
            price: parse(NumberSchema, data.price),
            availability: toBoolean(data.availability.toString())
        })
        
        if(result.success){
            await api.put(`/api/productos/${id}`, result.output);
        }
        
    }catch(error){
        console.log(error);
    }
}

export async function deleteProduct(id: Product['id']){
    try{
        await api.delete(`/api/productos/${id}`);
    }catch(error){
        console.log(error);
    }
    
}

export async function updateProductAvailability(id: Product['id']){
    try {
        await api.patch(`/api/productos/${id}`);
    } catch (error) {
        console.log(error);
    }
}