import { ActionFunctionArgs, Form, Link, redirect, useActionData } from "react-router-dom";
import UserForm from "../components/UserForm";
import { loginUser } from "../services/LoginService";
import ErrorMessage from "../components/ErrorMessage";

export async function action({request} : ActionFunctionArgs){
    const data = Object.fromEntries(await request.formData())

    const menssages: Record<string, string> = {};
    
    if (Object.values(data).includes('')) {
        menssages.general = 'Todos los campos son obligatorios';
        return menssages;
    }

    const backendErrors = await loginUser(data);
    if (backendErrors) {
        menssages.error = backendErrors.error;
    }

    if (Object.keys(menssages).length) {
        return menssages; // Devuelve un objeto con los errores
    }

    return redirect("/productos");
}

export default function Login() {
    
    const messages = useActionData() as Record<string, string>;

    return (
        <>
            <h1 className="text-4xl font-bold text-center text-indigo-700 mb-8">
                Inicia sesión
            </h1>

            {messages?.general && <ErrorMessage>{messages.general}</ErrorMessage>}
            {messages?.error && <ErrorMessage>{messages.error}</ErrorMessage>}

            <Form 
                className="space-y-8 p-10 bg-white"
                method="POST">

                <UserForm
                    color="indigo"
                />

                <input
                    type="submit"
                    value="Iniciar Sesión"
                    className="bg-indigo-600 hover:bg-indigo-700 w-full p-3 text-white font-black text-xl cursor-pointer rounded-md"
                />
            </Form>

            <p className="font-black text-slate-500 text-center mt-5">
                ¿No tienes una cuenta?{' '}
                <Link
                    to="/register"
                    className="text-indigo-800 text-sm font-bold underline"
                >
                    Regístrate
                </Link>
            </p>
        </>
    );
}
