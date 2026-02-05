import { useEffect, useState } from "react";
import api from "../services/api";

const Suppliers = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [loading, setLoading] = useState(true);

  // Add supplier state
  const [newSupplier, setNewSupplier] = useState({
    supplierName: "",
    contactNumber: "",
    email: "",
    address: "",
  });

  // Edit supplier state
  const [editingSupplier, setEditingSupplier] = useState(null);

  // 🔹 Fetch suppliers
  const fetchSuppliers = async () => {
    try {
      const res = await api.get("/api/suppliers");
      setSuppliers(res.data);
    } catch (err) {
      console.error("Error fetching suppliers", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchSuppliers();
  }, []);

  // 🔹 Add supplier
  const addSupplier = async () => {
    try {
      await api.post("/api/suppliers", newSupplier);
      alert("Supplier added successfully");

      setNewSupplier({
        supplierName: "",
        contactNumber: "",
        email: "",
        address: "",
      });

      fetchSuppliers();
    } catch (err) {
      alert("Failed to add supplier");
    }
  };

  // 🔹 Update supplier
  const updateSupplier = async () => {
    try {
      await api.put(
        `/api/suppliers/${editingSupplier.supplierId}`,
        editingSupplier
      );
      alert("Supplier updated");
      setEditingSupplier(null);
      fetchSuppliers();
    } catch (err) {
      alert("Update failed");
    }
  };

  // 🔹 Delete supplier
  const deleteSupplier = async (id) => {
    if (!window.confirm("Delete this supplier?")) return;

    try {
      await api.delete(`/api/suppliers/${id}`);
      fetchSuppliers();
    } catch (err) {
      alert("Delete failed");
    }
  };

  if (loading) {
    return <p className="text-center mt-10">Loading suppliers...</p>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Suppliers</h1>

      {/* ================= ADD SUPPLIER ================= */}
      <div className="bg-white p-4 rounded shadow mb-6">
        <h2 className="font-semibold mb-3">Add Supplier</h2>

        <div className="grid grid-cols-1 md:grid-cols-4 gap-3">
          <input
            className="border p-2 rounded"
            placeholder="Company Name"
            value={newSupplier.supplierName}
            onChange={(e) =>
              setNewSupplier({ ...newSupplier, supplierName: e.target.value })
            }
          />

          <input
            className="border p-2 rounded"
            placeholder="Contact Number"
            value={newSupplier.contactNumber}
            onChange={(e) =>
              setNewSupplier({ ...newSupplier, contactNumber: e.target.value })
            }
          />

          <input
            className="border p-2 rounded"
            placeholder="Email"
            value={newSupplier.email}
            onChange={(e) =>
              setNewSupplier({ ...newSupplier, email: e.target.value })
            }
          />

          <input
            className="border p-2 rounded"
            placeholder="Address"
            value={newSupplier.address}
            onChange={(e) =>
              setNewSupplier({ ...newSupplier, address: e.target.value })
            }
          />
        </div>

        <button
          onClick={addSupplier}
          className="mt-4 bg-blue-600 text-white px-4 py-2 rounded"
        >
          Add Supplier
        </button>
      </div>

      {/* ================= EDIT SUPPLIER ================= */}
      {editingSupplier && (
        <div className="bg-yellow-50 p-4 rounded shadow mb-6">
          <h2 className="font-semibold mb-3">Edit Supplier</h2>

          <div className="grid grid-cols-1 md:grid-cols-4 gap-3">
            <input
              className="border p-2 rounded"
              value={editingSupplier.supplierName}
              onChange={(e) =>
                setEditingSupplier({
                  ...editingSupplier,
                  supplierName: e.target.value,
                })
              }
            />

            <input
              className="border p-2 rounded"
              value={editingSupplier.contactNumber}
              onChange={(e) =>
                setEditingSupplier({
                  ...editingSupplier,
                  contactNumber: e.target.value,
                })
              }
            />

            <input
              className="border p-2 rounded"
              value={editingSupplier.email}
              onChange={(e) =>
                setEditingSupplier({
                  ...editingSupplier,
                  email: e.target.value,
                })
              }
            />

            <input
              className="border p-2 rounded"
              value={editingSupplier.address}
              onChange={(e) =>
                setEditingSupplier({
                  ...editingSupplier,
                  address: e.target.value,
                })
              }
            />
          </div>

          <div className="mt-4 flex gap-3">
            <button
              onClick={updateSupplier}
              className="bg-green-600 text-white px-4 py-2 rounded"
            >
              Update
            </button>

            <button
              onClick={() => setEditingSupplier(null)}
              className="bg-gray-500 text-white px-4 py-2 rounded"
            >
              Cancel
            </button>
          </div>
        </div>
      )}

      {/* ================= SUPPLIER TABLE ================= */}
      <div className="bg-white rounded shadow overflow-x-auto">
        <table className="w-full">
          <thead className="bg-gray-100">
            <tr>
              <th className="p-3 text-left">ID</th>
              <th className="p-3 text-left">Company</th>
              <th className="p-3 text-left">Contact</th>
              <th className="p-3 text-left">Email</th>
              <th className="p-3 text-left">Address</th>
              <th className="p-3 text-left">Actions</th>
            </tr>
          </thead>

          <tbody>
            {suppliers.map((s) => (
              <tr key={s.supplierId} className="border-t">
                <td className="p-3">{s.supplierId}</td>
                <td className="p-3">{s.supplierName}</td>
                <td className="p-3">{s.contactNumber}</td>
                <td className="p-3 text-blue-600">{s.email}</td>
                <td className="p-3">{s.address}</td>
                <td className="p-3 space-x-3">
                  <button
                    onClick={() => setEditingSupplier(s)}
                    className="text-blue-600"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => deleteSupplier(s.supplierId)}
                    className="text-red-600"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Suppliers;
