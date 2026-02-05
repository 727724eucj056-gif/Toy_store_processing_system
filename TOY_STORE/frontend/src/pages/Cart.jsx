import { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const cart = JSON.parse(localStorage.getItem('toyCart') || '[]');
    setCartItems(cart);
  }, []);

  const updateCart = (updatedCart) => {
    localStorage.setItem('toyCart', JSON.stringify(updatedCart));
    setCartItems(updatedCart);
  };

  const updateQuantity = (id, qty) => {
    if (qty <= 0) return removeItem(id);
    updateCart(cartItems.map(i => i.id === id ? { ...i, quantity: qty } : i));
  };

  const removeItem = (id) => {
    updateCart(cartItems.filter(i => i.id !== id));
  };

  const totalPrice = cartItems.reduce((t, i) => t + i.price * i.quantity, 0).toFixed(2);
  const totalItems = cartItems.reduce((t, i) => t + i.quantity, 0);

  if (cartItems.length === 0) {
    return <h2 className="text-center text-2xl mt-10">🛒 Cart is empty</h2>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Shopping Cart</h1>

      {cartItems.map(item => (
        <div key={item.id} className="bg-white p-4 mb-3 rounded shadow flex justify-between">
          <div>
            <h3 className="font-semibold">{item.name}</h3>
            <p>{item.description}</p>
            <p className="font-bold">${item.price}</p>
          </div>

          <div className="flex items-center gap-3">
            <button onClick={() => updateQuantity(item.id, item.quantity - 1)}>-</button>
            <span>{item.quantity}</span>
            <button onClick={() => updateQuantity(item.id, item.quantity + 1)}>+</button>
            <button onClick={() => removeItem(item.id)} className="bg-red-500 text-white px-2 rounded">
              Remove
            </button>
          </div>
        </div>
      ))}

      <div className="mt-6 flex justify-between items-center">
        <h2 className="text-xl font-bold">Total ({totalItems}): ${totalPrice}</h2>

        <div className="flex gap-4">
          <button
            onClick={() => navigate('/products')}
            className="bg-gray-600 text-white px-5 py-2 rounded"
          >
            Continue Shopping
          </button>

          <button
            onClick={() => navigate('/checkout')}
            className="bg-blue-600 text-white px-5 py-2 rounded"
          >
            Proceed to Checkout
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
