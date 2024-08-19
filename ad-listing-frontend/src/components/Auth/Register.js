import React, { useState } from 'react';
import axiosInstance from '../../api/axiosConfig';
import {Link} from "react-router-dom";

const Register = () => {
    const [formData, setFormData] = useState({
        username: '',
        name: '',
        surname: '',
        phone: '',
        email: '',
        password: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post('/authentication/sign-up', formData);
            console.log('Kayıt başarılı:', response.data);
        } catch (error) {
            console.error('Kayıt hatası:', error);
        }
    };

    return (
        <div className="register-form">
            <h2>Kullanıcı Kaydı</h2>
            <form onSubmit={handleSubmit}>
                <input name="username" placeholder="username" onChange={handleChange}/>
                <input name="name" placeholder="name" onChange={handleChange}/>
                <input name="surname" placeholder="surname" onChange={handleChange}/>
                <input name="phone" placeholder="Telefon" onChange={handleChange}/>
                <input name="email" placeholder="Email" onChange={handleChange}/>
                <input name="password" type="password" placeholder="Şifre" onChange={handleChange}/>
                <button type="submit">Kayıt Ol</button>
            </form>
            <li><Link to="/login">Login</Link></li>
        </div>
    );
};

export default Register;
