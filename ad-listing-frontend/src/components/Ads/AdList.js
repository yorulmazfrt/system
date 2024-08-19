import React, {useState, useEffect} from 'react';
import axiosInstance from '../../api/axiosConfig';
import {Link, useNavigate} from "react-router-dom";

const AdList = () => {
    const [ads, setAds] = useState([]);

    const navigate = useNavigate();
    useEffect(() => {
        const fetchAds = async () => {
            try {
                const response = await axiosInstance.get('/v1/advertisement/getlist');
                setAds(response.data);
            } catch (error) {
                console.error('İlanları getirme hatası:', error);
            }
        };

        fetchAds();
    }, []);

    const handleEditClick = (adId) => {
        navigate(`/ads/edit/${adId}`);
    };

    return (
        <div className="ad-list">
            <h2>İlanlar</h2>
            <table>
                <thead>
                <tr>
                    <th>Başlık</th>
                    <th>Açıklama</th>
                    <th>Fiyat</th>
                    <th>Görüntülenme Sayısı</th>
                    <th>Düzenle</th>
                </tr>
                </thead>
                <tbody>
                {ads.map((ad) => (
                    <tr key={ad.id}>
                        <td>{ad.title}</td>
                        <td>{ad.description}</td>
                        <td>{ad.price}</td>
                        <td>{ad.viewCount}</td>
                        <td>
                            <button onClick={() => handleEditClick(ad.id)}>Düzenle</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <li><Link to="/ads/create">Create ad</Link></li>

        </div>

    );
};

export default AdList;
