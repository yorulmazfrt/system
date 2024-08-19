import React, {useState, useEffect} from 'react';
import {Link, useParams} from 'react-router-dom';
import axiosInstance from '../../api/axiosConfig';

const EditAd = () => {
    const {id} = useParams();
    const [adData, setAdData] = useState({
        title: '',
        description: '',
        price: ''
    });

    useEffect(() => {
        const fetchAd = async () => {
            try {
                const response = await axiosInstance.get(`/v1/advertisement/getAd/${id}`);
                setAdData(response.data);
            } catch (error) {
                console.error('İlan getirme hatası:', error);
            }
        };

        fetchAd();
    }, [id]);

    const handleChange = (e) => {
        setAdData({
            ...adData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.put(`/v1/advertisement/update/${id}`, adData);
            console.log('İlan güncellendi:', response.data);
        } catch (error) {
            console.error('İlan güncelleme hatası:', error);
        }
    };

    return (
        <div className="edit-ad-form">
            <h2>İlan Düzenle</h2>
            <form onSubmit={handleSubmit}>
                <input name="title" value={adData.title} placeholder="Başlık" onChange={handleChange}/>
                <input name="description" value={adData.description} placeholder="Açıklama" onChange={handleChange}/>
                <input name="price" value={adData.price} placeholder="Fiyat" onChange={handleChange}/>
                <button type="submit">Güncelle</button>
            </form>
            <li><Link to="/ads">Ad List</Link></li>
        </div>
    );
};

export default EditAd;
