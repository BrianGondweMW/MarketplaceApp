import { useEffect, useState } from "react";
import {
  collection,
  addDoc,
  getDocs,
  updateDoc,
  deleteDoc,
  doc,
} from "firebase/firestore";
import {
  signInWithEmailAndPassword,
  onAuthStateChanged,
  signOut,
} from "firebase/auth";
import { db, auth } from "./firebase";
import "./App.css";
function App() {
  const [user, setUser] = useState(null);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [products, setProducts] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [price, setPrice] = useState("");
  const [rating, setRating] = useState("0");
  const [message, setMessage] = useState("");
  useEffect(() => {
    return onAuthStateChanged(auth, (currentUser) => {
      setUser(currentUser);
    });
  }, []);
  const loadProducts = async () => {
    const snapshot = await getDocs(collection(db, "products"));
    const data = snapshot.docs.map((item) => ({
      id: item.id,
      ...item.data(),
    }));
    setProducts(data);
  };
  useEffect(() => {
    if (user) {
      loadProducts();
    }
  }, [user]);
  const login = async (e) => {
    e.preventDefault();
    try {
      await signInWithEmailAndPassword(auth, email, password);
      setMessage("");
    } catch (error) {
      setMessage("Login failed: " + error.message);
    }
  };
  const clearForm = () => {
    setEditingId(null);
    setName("");
    setCategory("");
    setDescription("");
    setImageUrl("");
    setPrice("");
    setRating("0");
  };
  const saveProduct = async (e) => {
    e.preventDefault();
    if (!name || !category || !price) {
      setMessage("Please enter name, category and price.");
      return;
    }
    const product = {
      name,
      category,
      description,
      imageUrl,
      price,
      rating: Number(rating) || 0,
    };
    try {
      if (editingId) {
        await updateDoc(doc(db, "products", editingId), product);
        setMessage("Product updated successfully.");
      } else {
        await addDoc(collection(db, "products"), {
          ...product,
          isLiked: false,
        });
        setMessage("Product added successfully.");
      }
      clearForm();
      await loadProducts();
    } catch (error) {
      setMessage("Error saving product: " + error.message);
    }
  };
  const editProduct = (product) => {
    setEditingId(product.id);
    setName(product.name || "");
    setCategory(product.category || "");
    setDescription(product.description || "");
    setImageUrl(product.imageUrl || "");
    setPrice(product.price || "");
    setRating(String(product.rating ?? 0));
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };
  const removeProduct = async (id) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this product?"
    );
    if (!confirmed) return;
    try {
      await deleteDoc(doc(db, "products", id));
      setMessage("Product deleted successfully.");
      await loadProducts();
    } catch (error) {
      setMessage("Error deleting product: " + error.message);
    }
  };
  if (!user) {
    return (
      <div className="login-page">
        <form className="login-card" onSubmit={login}>
          <h1>Marketplace Admin</h1>
          <p>Administrator Login</p>
          <input
            type="email"
            placeholder="Admin email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
          {message && <p className="error">{message}</p>}
        </form>
      </div>
    );
  }
  return (
    <div className="admin-page">
      <header className="header">
        <div>
          <h1>Marketplace Admin</h1>
          <p>Manage products</p>
        </div>
        <button onClick={() => signOut(auth)}>Logout</button>
      </header>
      <main>
        <section className="form-card">
          <h2>{editingId ? "Edit Product" : "Add Product"}</h2>
          <form onSubmit={saveProduct}>
            <input
              placeholder="Product name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            <input
              placeholder="Category"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            />
            <textarea
              placeholder="Description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
            <input
              placeholder="Image URL"
              value={imageUrl}
              onChange={(e) => setImageUrl(e.target.value)}
            />
            <input
              placeholder="Price e.g. Mk20000"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            />
            <input
              type="number"
              step="0.1"
              min="0"
              max="5"
              placeholder="Rating"
              value={rating}
              onChange={(e) => setRating(e.target.value)}
            />
            <div className="form-buttons">
              <button type="submit">
                {editingId ? "Update Product" : "Add Product"}
              </button>
              {editingId && (
                <button type="button" onClick={clearForm}>
                  Cancel
                </button>
              )}
            </div>
          </form>
          {message && <p className="message">{message}</p>}
        </section>
        <section>
          <h2>Products ({products.length})</h2>
          <div className="products">
            {products.map((product) => (
              <div className="product-card" key={product.id}>
                {product.imageUrl && (
                  <img src={product.imageUrl} alt={product.name} />
                )}
                <div className="product-info">
                  <h3>{product.name}</h3>
                  <p>
                    <strong>Category:</strong> {product.category}
                  </p>
                  <p>{product.description}</p>
                  <p>
                    <strong>Price:</strong> {product.price}
                  </p>
                  <p>
                    <strong>Rating:</strong> {product.rating ?? 0}
                  </p>
                  <div className="actions">
                    <button onClick={() => editProduct(product)}>
                      Edit
                    </button>
                    <button
                      className="delete"
                      onClick={() => removeProduct(product.id)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </section>
      </main>
    </div>
  );
}
export default App;