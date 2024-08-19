import React from 'react';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');

        navigate('/login');
    };

    return (
        <header>
            <h1>İlan Sistemi</h1>
            <button onClick={handleLogout} style={{ position: 'absolute', right: '10px', top: '10px' }}>
                Logout
            </button>
        </header>
    );
};

export default Header;
