import { createBrowserRouter } from "react-router-dom"
import Layout from "./layouts/Layout"
import Products, {loader as productsLoader, action as updateAvailabilityAction} from "./views/Products"
import NewProdduct, { action as newProductAction} from "./views/NewProdduct"
import EditProduct, {loader as editProductLoader, action as editProductAction} from "./views/EditProduct"
import { action as deleteProductAction } from "./components/ProductDetails"

export const router = createBrowserRouter([
    {
        path: '/',
        element: <Layout/>,
        children: [
            {
                index: true,
                element: <Products />,
                loader: productsLoader,
                action: updateAvailabilityAction
            },
            {
                path: 'productos/nuevo',
                element: <NewProdduct/>,
                action: newProductAction
            },
            {
                path:'productos/:id/edit', 
                element: <EditProduct/>,
                loader: editProductLoader,
                action: editProductAction
            },
            {
                path:'productos/:id/eliminar', 
                action: deleteProductAction
            },
        ]
      }
])