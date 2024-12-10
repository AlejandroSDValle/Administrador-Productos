import { ActionFunctionArgs, Link, Navigate, useLoaderData, useNavigate } from "react-router-dom"
import { getProducts, updateProductAvailability } from "../services/ProductService"
import ProductDetails from "../components/ProductDetails";
import { Product } from "../types";

export async function loader() {
  const products = await getProducts()

  return products;
}

export async function action({ request }: ActionFunctionArgs) {
  const data = Object.fromEntries(await request.formData())
  await updateProductAvailability(+data.id)

  return {}
}

function Products() {

  const products = useLoaderData() as Product[];
  const token = localStorage.getItem("authToken");
  const user = token ? localStorage.getItem("username") : null;

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("username");
    navigate("/");
  };

  return (
    <>
      <div className="flex justify-between space-x-4 py-4">
        <p className="font-black text-slate-500">Bienvenido, {user}!</p>
        <button
          onClick={handleLogout}
          className="px-4 py-2 bg-red-500 text-white font-bold rounded-lg shadow-md hover:bg-red-400 focus:outline-none focus:ring-2 focus:ring-red-300 transition-all duration-300"
        >
          Logout
        </button>
      </div>
      <div className="flex justify-between">
        <h2 className="text-4xl font-black text-slate-500">Productos</h2>
        <Link
          to={"/nuevo"}
          className="rounded-md bg-indigo-600 p-3 text-sm font-bold text-white shadow-sm hover:bg-indigo-500"
        >
          Agregar Producto
        </Link>
      </div>

      <div className="p-2">
        <table className="w-full mt-5 table-auto">
          <thead className="bg-slate-800 text-white">
            <tr>
              <th className="p-2">Producto</th>
              <th className="p-2">Precio</th>
              <th className="p-2">Disponibilidad</th>
              <th className="p-2">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {products.map(product => (
              <ProductDetails
                key={product.id}
                product={product}
              />
            ))}
          </tbody>
        </table>
      </div>

    </>
  )
}

export default Products