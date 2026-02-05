import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";

const PurchaseOrderView = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [order, setOrder] = useState(null);

  useEffect(() => {
    api.get(`/api/purchase-orders/${id}`).then(res => {
      setOrder(res.data);
    });
  }, [id]);

  if (!order) return <p>Loading...</p>;

  return (
    <div>
      <h1 className="text-3xl font-bold mb-4">Order Details</h1>

      <div className="bg-white p-6 rounded shadow w-1/2">
        <p><b>Order Number:</b> {order.orderNumber}</p>
        <p><b>Supplier:</b> {order.supplier?.supplierName}</p>
        <p><b>Status:</b> {order.status}</p>
        <p><b>Date:</b> {order.orderDate}</p>

        <button
          onClick={() => navigate("/orders")}
          className="mt-4 bg-gray-600 text-white px-4 py-2 rounded"
        >
          Back
        </button>
      </div>
    </div>
  );
};

export default PurchaseOrderView;
