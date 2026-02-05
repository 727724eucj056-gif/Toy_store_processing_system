import { useState, useEffect } from 'react';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalToys: 0,
    totalOrders: 0,
    cartItems: 0
  });

  useEffect(() => {
    // Get cart items from localStorage
    const cart = JSON.parse(localStorage.getItem('toyCart') || '[]');
    setStats(prev => ({
      ...prev,
      cartItems: cart.reduce((sum, item) => sum + item.quantity, 0)
    }));

    // Simulate fetching stats
    setStats(prev => ({
      ...prev,
      totalToys: 25,
      totalOrders: 12
    }));
  }, []);

  const cards = [
    {
      title: 'Total Toys',
      value: stats.totalToys,
      icon: '🧸',
      color: 'bg-blue-500'
    },
    {
      title: 'Orders',
      value: stats.totalOrders,
      icon: '📋',
      color: 'bg-green-500'
    },
    {
      title: 'Cart Items',
      value: stats.cartItems,
      icon: '🛒',
      color: 'bg-purple-500'
    }
  ];

  return (
    <div>
      <h1 className="text-3xl font-bold text-gray-800 mb-6">Dashboard</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        {cards.map((card, index) => (
          <div key={index} className="bg-white rounded-lg shadow-md p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-600 text-sm">{card.title}</p>
                <p className="text-3xl font-bold text-gray-800">{card.value}</p>
              </div>
              <div className={`${card.color} text-white p-3 rounded-full text-2xl`}>
                {card.icon}
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="bg-white rounded-lg shadow-md p-6">
        <h2 className="text-xl font-semibold text-gray-800 mb-4">Quick Stats</h2>
        <div className="space-y-4">
          <div className="flex justify-between items-center">
            <span className="text-gray-600">Active Products</span>
            <span className="font-semibold">{stats.totalToys}</span>
          </div>
          <div className="flex justify-between items-center">
            <span className="text-gray-600">Pending Orders</span>
            <span className="font-semibold">{stats.totalOrders}</span>
          </div>
          <div className="flex justify-between items-center">
            <span className="text-gray-600">Items in Cart</span>
            <span className="font-semibold">{stats.cartItems}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;