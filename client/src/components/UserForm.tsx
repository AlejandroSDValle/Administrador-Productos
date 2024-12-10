type UserFormProps = {
    color: string; 
}

export default function UserForm({ color }: UserFormProps) {
    return (
        <>
            <div className="flex flex-col gap-5">
                <label className="font-normal text-2xl">Usuario</label>
                <input
                    id="usuario"
                    name="usuario"
                    type="text"
                    placeholder="Usuario de Registro"
                    className={`w-full p-3 border-gray-300 border rounded-md focus:outline-none focus:ring-2 focus:ring-${color}-600`}
                />
            </div>

            <div className="flex flex-col gap-5">
                <label className="font-normal text-2xl">Password</label>
                <input
                    type="password"
                    name="password"
                    placeholder="Password de Registro"
                    className={`w-full p-3 border-gray-300 border rounded-md focus:outline-none focus:ring-2 focus:ring-${color}-600`}
                />
            </div>
        </>
    );
}