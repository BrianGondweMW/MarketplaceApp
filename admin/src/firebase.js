import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyCjfWfs_smYM3Ehad3RALz-KQR3ylF8r-Q",
  authDomain: "marketplace-app-d6b93.firebaseapp.com",
  projectId: "marketplace-app-d6b93",
  storageBucket: "marketplace-app-d6b93.firebasestorage.app",
  messagingSenderId: "482864603251",
  appId: "1:482864603251:web:11021a5b85626babe8ca06"
};

const app = initializeApp(firebaseConfig);

export const auth = getAuth(app);
export const db = getFirestore(app);