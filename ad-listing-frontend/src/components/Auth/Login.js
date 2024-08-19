import React, {useState} from 'react';
import axiosInstance from '../../api/axiosConfig';
import {Link, useNavigate} from "react-router-dom";

const Login = () => {
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();
    const handleChange = (e) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post('/authentication/sign-in', credentials);
            const token = response.data.token;
            const role = response.data.role;

            localStorage.setItem('token', token);

            if (role === 'ADMIN') {
                navigate('/admin/dashboard');
            } else {
                navigate('/ads');
            }
            console.log('Giriş başarılı:', response.data);
        } catch (error) {
            console.error('Giriş hatası:', error);
        }
    };

    return (
        <div className="login-form">
            <h2>Sisteme Giriş</h2>
            <form onSubmit={handleSubmit}>
                <input name="username" placeholder="Email" onChange={handleChange}/>
                <input name="password" type="password" placeholder="Şifre" onChange={handleChange}/>
                <button type="submit">Giriş Yap</button>
            </form>
            <li><Link to="/register">Register</Link></li>
            <li><Link to="/ads/create">Create ad</Link></li>
            <li><Link to="/ads">Ad List</Link></li>
        </div>
    );
};

export default Login;
