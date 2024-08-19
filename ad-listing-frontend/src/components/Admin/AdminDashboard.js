import React from 'react';
import { Link } from 'react-router-dom';

const AdminDashboard = () => {
    return (
        <div className="admin-dashboard">
            <h2>Yönetici Paneli</h2>
            <ul>
                <li><Link to="/admin/pending-ads">Onay Bekleyen İlanlar</Link></li>
                <li><Link to="/admin/reports">Raporlar</Link></li>
            </ul>
        </div>
    );
};

export default AdminDashboard;
