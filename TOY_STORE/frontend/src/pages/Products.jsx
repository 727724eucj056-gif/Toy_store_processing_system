import { useState, useEffect } from 'react';
import api from "../services/api";
import Rating from '../components/Rating';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [viewMode, setViewMode] = useState('cards');
  const [ratings, setRatings] = useState({});
  const [feedback, setFeedback] = useState({});

  const dummyProducts = [
    { id: 1, name: 'Teddy Bear', price: 25.99, description: 'Soft and cuddly teddy bear' },
    { id: 2, name: 'LEGO Set', price: 49.99, description: 'Creative building blocks set' },
    { id: 3, name: 'Doll House', price: 89.99, description: 'Beautiful wooden doll house' },
    { id: 4, name: 'Remote Car', price: 35.99, description: 'Fast remote control car' },
    { id: 5, name: 'Puzzle Game', price: 15.99, description: '1000 piece jigsaw puzzle' },
    { id: 6, name: 'Action Figure', price: 19.99, description: 'Superhero action figure' }
  ];

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await api.get("/api/products");
      console.log("API DATA:", response.data);

      const data = Array.isArray(response.data) ? response.data : [];

      if (data.length === 0) {
        setProducts([]);
        setError("No products found");
      } else {
        setProducts(
          data.map(p => ({
            id: p.productId,
            name: p.productName,
            price: p.price,
            description: p.description
          }))
        );
        setError(null);
      }
    } catch (err) {
      console.error("API ERROR:", err);
      setProducts(dummyProducts);
      setError("Using dummy data - API not available");
    } finally {
      setLoading(false);
    }
  };

  const addToCart = (product) => {
    const cart = JSON.parse(localStorage.getItem('toyCart') || '[]');
    const existingItem = cart.find(item => item.id === product.id);

    if (existingItem) {
      existingItem.quantity += 1;
    } else {
      cart.push({ ...product, quantity: 1 });
    }

    localStorage.setItem('toyCart', JSON.stringify(cart));
    alert(`${product.name} added to cart!`);
  };

  const handleRatingChange = (productId, rating) => {
    setRatings(prev => ({ ...prev, [productId]: rating }));
  };

  const handleFeedbackChange = (productId, value) => {
    setFeedback(prev => ({ ...prev, [productId]: value }));
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="text-xl text-gray-600">Loading products...</div>
      </div>
    );
  }

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Products</h1>
        <div className="flex space-x-2">
          <button
            onClick={() => setViewMode('cards')}
            className={`px-4 py-2 rounded ${viewMode === 'cards' ? 'bg-blue-500 text-white' : 'bg-gray-200'}`}
          >
            Cards
          </button>
          <button
            onClick={() => setViewMode('table')}
            className={`px-4 py-2 rounded ${viewMode === 'table' ? 'bg-blue-500 text-white' : 'bg-gray-200'}`}
          >
            Table
          </button>
        </div>
      </div>

      {error && (
        <div className="bg-yellow-100 border border-yellow-400 text-yellow-700 px-4 py-3 rounded mb-4">
          {error}
        </div>
      )}

      {viewMode === 'cards' ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {products.map(product => (
            <div key={product.id} className="bg-white rounded-lg shadow-md p-6">
              <h3 className="text-xl font-semibold mb-2">{product.name}</h3>
              <p className="text-gray-600 mb-3">{product.description}</p>
              <p className="text-2xl font-bold text-blue-600 mb-4">${product.price}</p>

              <Rating
                rating={ratings[product.id] || 0}
                onRatingChange={(rating) => handleRatingChange(product.id, rating)}
              />

              <textarea
                className="w-full p-2 border rounded mt-3"
                rows="3"
                placeholder="Share your thoughts..."
                value={feedback[product.id] || ''}
                onChange={(e) => handleFeedbackChange(product.id, e.target.value)}
              />

              <button
                onClick={() => addToCart(product)}
                className="w-full mt-4 bg-blue-500 hover:bg-blue-600 text-white py-2 rounded"
              >
                Add to Cart
              </button>
            </div>
          ))}
        </div>
      ) : (
        <table className="min-w-full bg-white rounded shadow">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-6 py-3 text-left">Name</th>
              <th className="px-6 py-3 text-left">Description</th>
              <th className="px-6 py-3 text-left">Price</th>
              <th className="px-6 py-3 text-left">Action</th>
            </tr>
          </thead>
          <tbody>
            {products.map(product => (
              <tr key={product.id} className="border-t">
                <td className="px-6 py-3">{product.name}</td>
                <td className="px-6 py-3">{product.description}</td>
                <td className="px-6 py-3">${product.price}</td>
                <td className="px-6 py-3">
                  <button
                    onClick={() => addToCart(product)}
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                  >
                    Add to Cart
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Products;
