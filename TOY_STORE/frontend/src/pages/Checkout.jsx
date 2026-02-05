import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Checkout = () => {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const cart = JSON.parse(localStorage.getItem("toyCart") || "[]");
    setCartItems(cart);
  }, []);

  const total = cartItems
    .reduce((sum, item) => sum + item.price * item.quantity, 0)
    .toFixed(2);

  // ✅ PLACE ORDER LOGIC
  const placeOrder = () => {
    if (cartItems.length === 0) {
      alert("Cart is empty");
      return;
    }

    // 👉 Future: call backend API here
    // api.post("/api/orders", cartItems)

    alert("Order placed successfully 🎉");

    // 🔥 CLEAR CART
    localStorage.removeItem("toyCart");

    // 🔁 REDIRECT AFTER ORDER
    navigate("/products");
  };

  const continueShopping = () => {
    navigate("/products");
  };

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Checkout</h1>

      <div className="bg-white shadow rounded-lg p-6">
        {cartItems.map((item) => (
          <div
            key={item.id}
            className="flex justify-between border-b py-2"
          >
            <span>
              {item.name} x {item.quantity}
            </span>
            <span>
              ${(item.price * item.quantity).toFixed(2)}
            </span>
          </div>
        ))}

        <h2 className="text-xl font-bold mt-4">
          Total: ${total}
        </h2>

        <div className="mt-6 flex gap-4">
          <button
            type="button"
            onClick={continueShopping}
            className="bg-gray-600 text-white px-6 py-2 rounded"
          >
            Continue Shopping
          </button>

          <button
            type="button"   // 🔥 VERY IMPORTANT
            onClick={placeOrder}
            className="bg-green-600 text-white px-6 py-2 rounded"
          >
            Place Order
          </button>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
