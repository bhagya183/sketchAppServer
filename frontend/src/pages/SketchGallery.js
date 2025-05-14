import React, { useState, useEffect } from 'react';
import {
  Container,
  Grid,
  Card,
  CardContent,
  CardMedia,
  Typography,
  Box,
  Chip,
  TextField,
  InputAdornment,
  Dialog,
  DialogContent,
  IconButton,
  CircularProgress,
  Alert,
  Button,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import CloseIcon from '@mui/icons-material/Close';
import { sketchAPI } from '../services/api';
import defaultSketchImage from '../assets/default-sketch.svg';

const SketchGallery = () => {
  const [sketches, setSketches] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedSketch, setSelectedSketch] = useState(null);
  const [imageLoading, setImageLoading] = useState({});

  useEffect(() => {
    const fetchSketches = async () => {
      try {
        setLoading(true);
        const response = await sketchAPI.getAll();
        setSketches(response.data);
        setError(null);
      } catch (err) {
        setError('Failed to load sketches. Please try again later.');
        console.error('Error fetching sketches:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchSketches();
  }, []);

  const handleImageLoad = (sketchId) => {
    setImageLoading(prev => ({ ...prev, [sketchId]: false }));
  };

  const handleImageError = (sketchId) => {
    setImageLoading(prev => ({ ...prev, [sketchId]: false }));
    // You could also set a fallback image here
  };

  const filteredSketches = sketches.filter(sketch =>
    sketch.sketch_name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    (sketch.category && sketch.category.category_name.toLowerCase().includes(searchTerm.toLowerCase()))
  );

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
        <Alert severity="error">{error}</Alert>
      </Box>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Sketch Gallery
      </Typography>

      <Box sx={{ mb: 4 }}>
        <TextField
          fullWidth
          variant="outlined"
          placeholder="Search sketches by name or category..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
        />
      </Box>

      <Grid container spacing={4}>
        {filteredSketches.map((sketch) => (
          <Grid item key={sketch.id} xs={12} sm={6} md={4}>
            <Card 
              sx={{ 
                height: '100%', 
                display: 'flex', 
                flexDirection: 'column',
                cursor: 'pointer',
                '&:hover': {
                  transform: 'scale(1.02)',
                  transition: 'transform 0.2s ease-in-out'
                }
              }}
              onClick={() => setSelectedSketch(sketch)}
            >
              <Box sx={{ position: 'relative', paddingTop: '75%' }}>
                {imageLoading[sketch.id] !== false && (
                  <Box
                    sx={{
                      position: 'absolute',
                      top: 0,
                      left: 0,
                      right: 0,
                      bottom: 0,
                      display: 'flex',
                      alignItems: 'center',
                      justifyContent: 'center',
                      bgcolor: 'grey.100'
                    }}
                  >
                    <CircularProgress size={40} />
                  </Box>
                )}
                <CardMedia
                  component="img"
                  height="200"
                  image={sketch.image_url || defaultSketchImage}
                  alt={sketch.sketch_name}
                  sx={{ 
                    objectFit: 'cover',
                    backgroundColor: '#f5f5f5'
                  }}
                  onError={(e) => {
                    e.target.onerror = null;
                    e.target.src = defaultSketchImage;
                  }}
                />
              </Box>
              <CardContent sx={{ flexGrow: 1 }}>
                <Typography gutterBottom variant="h5" component="h2">
                  {sketch.sketch_name}
                </Typography>
                {sketch.category && (
                  <Chip
                    label={sketch.category.category_name}
                    color="primary"
                    size="small"
                    sx={{ mr: 1 }}
                  />
                )}
                <Typography variant="h6" color="primary" sx={{ mt: 1 }}>
                  ${sketch.price}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Dialog
        open={Boolean(selectedSketch)}
        onClose={() => setSelectedSketch(null)}
        maxWidth="md"
        fullWidth
      >
        {selectedSketch && (
          <>
            <IconButton
              onClick={() => setSelectedSketch(null)}
              sx={{
                position: 'absolute',
                right: 8,
                top: 8,
                color: 'white',
                bgcolor: 'rgba(0, 0, 0, 0.5)',
                '&:hover': {
                  bgcolor: 'rgba(0, 0, 0, 0.7)',
                },
                zIndex: 1
              }}
            >
              <CloseIcon />
            </IconButton>
            <DialogContent sx={{ p: 0 }}>
              <Box sx={{ position: 'relative' }}>
                <img
                  src={selectedSketch.image_url || 'https://via.placeholder.com/800x600?text=Sketch'}
                  alt={selectedSketch.sketch_name}
                  style={{ 
                    width: '100%', 
                    height: 'auto',
                    display: 'block'
                  }}
                />
                <Box
                  sx={{
                    position: 'absolute',
                    bottom: 0,
                    left: 0,
                    right: 0,
                    bgcolor: 'rgba(0, 0, 0, 0.7)',
                    color: 'white',
                    p: 2,
                  }}
                >
                  <Typography variant="h5">{selectedSketch.sketch_name}</Typography>
                  {selectedSketch.category && (
                    <Chip
                      label={selectedSketch.category.category_name}
                      color="primary"
                      size="small"
                      sx={{ mr: 1, mt: 1 }}
                    />
                  )}
                  <Typography variant="h6" sx={{ mt: 1 }}>
                    ${selectedSketch.price}
                  </Typography>
                </Box>
              </Box>
            </DialogContent>
          </>
        )}
      </Dialog>
    </Container>
  );
};

export default SketchGallery; 