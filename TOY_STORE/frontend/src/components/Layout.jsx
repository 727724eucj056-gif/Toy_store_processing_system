import { Routes, Route } from "react-router-dom";
import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

import Dashboard from "../pages/Dashboard";
import Products from "../pages/Products";
import Cart from "../pages/Cart";
import Suppliers from "../pages/Suppliers";
import PurchaseOrders from "../pages/PurchaseOrders";
import CustomerReturns from "../pages/CustomerReturns";

const Layout = () => {
  return (
    <div className="flex min-h-screen bg-gray-50">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Navbar />

        <main className="flex-1 p-6">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/products" element={<Products />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/suppliers" element={<Suppliers />} />
            <Route path="/orders" element={<PurchaseOrders />} />
            <Route path="/returns" element={<CustomerReturns />} />
          </Routes>
        </main>
      </div>
    </div>
  );
};

export default Layout;
