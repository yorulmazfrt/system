import React, {useState, useEffect} from 'react';
import axiosInstance from '../../api/axiosConfig';

const PendingAds = () => {
    const [pendingAds, setPendingAds] = useState([]);

    useEffect(() => {
        const fetchPendingAds = async () => {
            try {
                const response = await axiosInstance.get('/v1/advertisement/getPassiveList');
                setPendingAds(response.data);
            } catch (error) {
                console.error('Onay bekleyen ilanları getirme hatası:', error);
            }
        };

        fetchPendingAds();
    }, []);

    const handleApproval = async (id, action) => {
        try {
            await axiosInstance.post(`/v1/advertisement/changeStatus/${id}/${action}`);
            setPendingAds(pendingAds.filter(ad => ad.id !== id));
        } catch (error) {
            console.error('Onay/reddetme hatası:', error);
        }
    };

    return (
        <div className="pending-ads">
            <h2>Onay Bekleyen İlanlar</h2>
            <table>
                <thead>
                <tr>
                    <th>Başlık</th>
                    <th>Açıklama</th>
                    <th>Onayla</th>
                    <th>Reddet</th>
                </tr>
                </thead>
                <tbody>
                {pendingAds.map(ad => (
                    <tr key={ad.id}>
                        <td>{ad.title}</td>
                        <td>{ad.description}</td>
                        <td>
                            <button onClick={() => handleApproval(ad.id, 'approve')}>Onayla</button>
                        </td>
                        <td>
                            <button onClick={() => handleApproval(ad.id, 'reject')}>Reddet</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PendingAds;
