import { ActionFunctionArgs, Form, Link, useActionData } from "react-router-dom";
import UserForm from "../components/UserForm";
import { registerUser } from "../services/LoginService";
import ErrorMessage from "../components/ErrorMessage";
import SuccessMessage from "../components/SuccessMessage";

export async function action({ request }: ActionFunctionArgs) {
    const data = Object.fromEntries(await request.formData())

    const menssages: Record<string, string> = {};

    if (Object.values(data).includes('')) {
        menssages.general = 'Todos los campos son obligatorios';
        return menssages;
    }

    const backendErrors = await registerUser(data);
    if (backendErrors) {
        if (backendErrors.username) {
            menssages.username = backendErrors.username;
        }
        if (backendErrors.password) {
            menssages.password = backendErrors.password;
        }
    }

    if (Object.keys(menssages).length) {
        return menssages; // Devuelve un objeto con los errores
    }

    menssages.successMessage = "Cuenta creada con éxito. ¡Bienvenido!"

    return menssages;
}

export default function Register() {

    const messages = useActionData() as Record<string, string>;


    return (
        <>

            <h1 className="text-4xl font-bold text-center text-fuchsia-700 mb-8">
                Regístrate
            </h1>

            {messages?.general && <ErrorMessage>{messages.general}</ErrorMessage>}
            {messages?.username && <ErrorMessage>Usuario: {messages.username}</ErrorMessage>}
            {messages?.password && <ErrorMessage>Contraseña: {messages.password}</ErrorMessage>}
            {messages?.successMessage ?
                <SuccessMessage>Registrado: {messages.successMessage}</SuccessMessage>
                :
                <Form
                    className="space-y-8 p-10 bg-white"
                    method="POST"
                >
                    <UserForm
                        color="fuchsia"
                    />

                    <input
                        type="submit"
                        value="Regístrate"
                        className="bg-fuchsia-600 hover:bg-fuchsia-700 w-full p-3 text-white font-black text-xl cursor-pointer rounded-md"
                    />
                </Form>
            }


            <p className="font-black text-slate-500 text-center mt-5">
                ¿Ya tienes una cuenta? <Link
                    to={"/"}
                    className="text-fuchsia-800 text-sm font-bold underline"
                >
                    Iniciar sesión
                </Link></p>
        </>
    )
}