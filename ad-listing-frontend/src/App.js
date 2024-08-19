import React from 'react';
import { BrowserRouter as Router, Route,Routes } from 'react-router-dom';
import Register from './components/Auth/Register';
import Login from './components/Auth/Login';
import CreateAd from './components/Ads/CreateAd';
import EditAd from './components/Ads/EditAd';
import AdList from './components/Ads/AdList';
import AdminDashboard from './components/Admin/AdminDashboard';
import PendingAds from './components/Admin/PendingAds';
import Header from "./components/Header";

function App() {
    return (
        <Router>
            <div className="app">
                <Header></Header>
                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/ads/create" element={<CreateAd />} />
                    <Route path="/ads/edit/:id" element={<EditAd />} />
                    <Route path="/ads" element={<AdList />} />
                    <Route path="/admin/dashboard" element={<AdminDashboard />} />
                    <Route path="/admin/pending-ads" element={<PendingAds />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
