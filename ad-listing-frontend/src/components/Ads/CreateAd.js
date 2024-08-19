import React, {useState} from 'react';
import axiosInstance from '../../api/axiosConfig';

const CreateAd = () => {
    const [adData, setAdData] = useState({
        title: '',
        description: '',
        price: ''
    });

    const handleChange = (e) => {
        setAdData({
            ...adData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post('/v1/advertisement/create', adData);
            console.log('İlan oluşturuldu:', response.data);
        } catch (error) {
            console.error('İlan oluşturma hatası:', error);
        }
    };

    return (
        <div className="create-ad-form">
            <h2>İlan Oluştur</h2>
            <form onSubmit={handleSubmit}>
                <input name="title" placeholder="Başlık" onChange={handleChange}/>
                <input name="description" placeholder="Açıklama" onChange={handleChange}/>
                <input name="price" placeholder="Fiyat" onChange={handleChange}/>
                <button type="submit">Oluştur</button>
            </form>

        </div>
    );
};

export default CreateAd;
