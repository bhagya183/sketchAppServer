import React, { useState, useEffect } from 'react';
import {
  Container,
  Grid,
  Typography,
  Box,
  TextField,
  InputAdornment,
  CircularProgress,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ArtistCard from '../components/ArtistCard';
import { getArtists } from '../services/artistService';

const Home = () => {
  const [artists, setArtists] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchArtists();
  }, []);

  const fetchArtists = async () => {
    try {
      setLoading(true);
      const data = await getArtists();
      setArtists(data);
      setError(null);
    } catch (err) {
      setError('Failed to load artists. Please try again later.');
      console.error('Error fetching artists:', err);
    } finally {
      setLoading(false);
    }
  };

  const filteredArtists = artists.filter((artist) =>
    artist.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    artist.specialization.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Box sx={{ mb: 4, textAlign: 'center' }}>
        <Typography variant="h3" component="h1" gutterBottom>
          Find Your Perfect Sketch Artist
        </Typography>
        <Typography variant="h6" color="text.secondary" paragraph>
          Browse through our talented artists and book your custom sketch today
        </Typography>
      </Box>

      <TextField
        fullWidth
        variant="outlined"
        placeholder="Search artists by name or specialization..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        sx={{ mb: 4 }}
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <SearchIcon />
            </InputAdornment>
          ),
        }}
      />

      {error && (
        <Typography color="error" align="center" sx={{ mb: 4 }}>
          {error}
        </Typography>
      )}

      {loading ? (
        <Box sx={{ display: 'flex', justifyContent: 'center', my: 4 }}>
          <CircularProgress />
        </Box>
      ) : (
        <Grid container spacing={3}>
          {filteredArtists.map((artist) => (
            <Grid item xs={12} sm={6} md={4} key={artist.id}>
              <ArtistCard artist={artist} />
            </Grid>
          ))}
          {filteredArtists.length === 0 && (
            <Grid item xs={12}>
              <Typography align="center" color="text.secondary">
                No artists found matching your search criteria.
              </Typography>
            </Grid>
          )}
        </Grid>
      )}
    </Container>
  );
};

export default Home; 