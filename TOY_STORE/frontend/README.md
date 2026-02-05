# Toy Store Management System

A modern, responsive toy store management frontend built with React, Vite, and Tailwind CSS.

## Features

- **Dashboard**: Overview with summary cards and statistics
- **Products**: Browse toys in card/table view with ratings and feedback
- **Cart**: Shopping cart with quantity management and localStorage persistence
- **Suppliers**: Supplier directory with contact information
- **Purchase Orders**: Order management with status tracking
- **Customer Returns**: Return processing and management

## Tech Stack

- React 19
- React Router v6
- Tailwind CSS
- Axios for API calls
- Vite for development

## Setup Instructions

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Open your browser and navigate to `http://localhost:5173`

## API Integration

The application is configured to work with a Spring Boot backend running on `http://localhost:8080`. If the API is not available, the app will gracefully fall back to dummy data.

### API Endpoints:
- `GET /api/products` - Fetch products
- `GET /api/suppliers` - Fetch suppliers
- `GET /api/orders` - Fetch purchase orders
- `GET /api/returns` - Fetch customer returns

## Features Overview

### Products Page
- View toys in card or table format
- Star rating system (1-5 stars)
- Feedback textarea for each product
- Add to cart functionality
- Responsive design

### Cart Management
- Add/remove items
- Increase/decrease quantities
- Persistent storage using localStorage
- Real-time total calculation

### Navigation
- Responsive sidebar navigation
- Active state highlighting
- Clean, professional design

## Build for Production

```bash
npm run build
```

The built files will be in the `dist` directory.