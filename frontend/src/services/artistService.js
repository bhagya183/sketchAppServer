import axios from 'axios';

const API_URL = 'http://localhost:8080/api/artists';

export const getArtists = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching artists:', error);
    throw error;
  }
};

export const getArtistById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching artist details:', error);
    throw error;
  }
};

export const createArtist = async (artistData) => {
  try {
    const response = await axios.post(API_URL, artistData);
    return response.data;
  } catch (error) {
    console.error('Error creating artist:', error);
    throw error;
  }
};

export const updateArtist = async (id, artistData) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, artistData);
    return response.data;
  } catch (error) {
    console.error('Error updating artist:', error);
    throw error;
  }
};

export const deleteArtist = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('Error deleting artist:', error);
    throw error;
  }
}; 