import { createBrowserRouter } from "react-router-dom"
import Layout from "./layouts/Layout"

//Productos
import Products, {loader as productsLoader, action as updateAvailabilityAction} from "./views/Products"
import NewProdduct, { action as newProductAction} from "./views/NewProdduct"
import EditProduct, {loader as editProductLoader, action as editProductAction} from "./views/EditProduct"
import { action as deleteProductAction } from "./components/ProductDetails"

//Usuarios
import Register, {action as newUserAction} from "./views/Register"
import Login, {action as login}  from "./views/Login"


export const router = createBrowserRouter([
    {
        path: '/',
        element: <Layout/>,
        children: [
            {
                index: true,
                element: <Login />,
                action: login
            },
            {
                path: '/register',
                element: <Register />,
                action: newUserAction
            },
            {
                path: '/productos',
                element: <Products />, 
                loader: productsLoader,
                action: updateAvailabilityAction
            },
            {
                path: '/nuevo',
                element: <NewProdduct/>,
                action: newProductAction
            },
            {
                path:'/productos/:id/edit', 
                element: <EditProduct/>,
                loader: editProductLoader,
                action: editProductAction
            },
            {
                path:'/:id/eliminar', 
                action: deleteProductAction
            },
        ]
      }
])