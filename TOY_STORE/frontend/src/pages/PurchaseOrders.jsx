import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const statusColor = {
  Pending: "bg-yellow-100 text-yellow-700",
  Shipped: "bg-blue-100 text-blue-700",
  Completed: "bg-green-100 text-green-700",
};

const PurchaseOrders = () => {
  const [orders, setOrders] = useState([]);
  const [suppliers, setSuppliers] = useState([]);
  const [orderNumber, setOrderNumber] = useState("");
  const [supplierId, setSupplierId] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    loadOrders();
    loadSuppliers();
  }, []);

  const loadOrders = async () => {
    const res = await api.get("/api/purchase-orders");
    setOrders(res.data);
  };

  const loadSuppliers = async () => {
    const res = await api.get("/api/suppliers");
    setSuppliers(res.data);
  };

  const addOrder = async () => {
    if (!orderNumber || !supplierId) return;

    await api.post("/api/purchase-orders", {
      orderNumber,
      status: "Pending",
      supplier: { supplierId },
    });

    setOrderNumber("");
    setSupplierId("");
    loadOrders();
  };

  const updateStatus = async (order, status) => {
    await api.put(`/api/purchase-orders/${order.purchaseOrderId}`, {
      ...order,
      status,
    });
    loadOrders();
  };

  const deleteOrder = async (id) => {
    await api.delete(`/api/purchase-orders/${id}`);
    loadOrders();
  };

  return (
    <div>
      <h1 className="text-3xl font-bold mb-4">Purchase Orders</h1>

      {/* ADD ORDER */}
      <div className="bg-white p-4 rounded shadow mb-6 flex gap-4">
        <input
          value={orderNumber}
          onChange={(e) => setOrderNumber(e.target.value)}
          placeholder="Order Number"
          className="border p-2 rounded w-1/3"
        />

        <select
          value={supplierId}
          onChange={(e) => setSupplierId(e.target.value)}
          className="border p-2 rounded w-1/3"
        >
          <option value="">Select Supplier</option>
          {suppliers.map((s) => (
            <option key={s.supplierId} value={s.supplierId}>
              {s.supplierName}
            </option>
          ))}
        </select>

        <button
          onClick={addOrder}
          className="bg-blue-600 text-white px-4 rounded"
        >
          Add Order
        </button>
      </div>

      {/* TABLE */}
      <table className="w-full bg-white rounded shadow">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-3">Order</th>
            <th>Supplier</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((o) => (
            <tr key={o.purchaseOrderId} className="border-t">
              <td className="p-3">{o.orderNumber}</td>
              <td>{o.supplier?.supplierName}</td>

              <td className="flex gap-3 items-center p-3">
                <span
                  className={`px-3 py-1 rounded-full text-sm ${statusColor[o.status]}`}
                >
                  {o.status}
                </span>

                <select
                  value={o.status}
                  onChange={(e) => updateStatus(o, e.target.value)}
                  className="border rounded p-1"
                >
                  <option>Pending</option>
                  <option>Shipped</option>
                  <option>Completed</option>
                </select>
              </td>

              <td className="space-x-3">
                <button
                  onClick={() => navigate(`/orders/${o.purchaseOrderId}`)}
                  className="text-blue-600"
                >
                  View
                </button>

                <button
                  onClick={() => deleteOrder(o.purchaseOrderId)}
                  className="text-red-600"
                >
                  Cancel
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PurchaseOrders;
