import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button, Box, Rating } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const ArtistCard = ({ artist }) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 345, m: 2, height: '100%', display: 'flex', flexDirection: 'column' }}>
      <CardMedia
        component="img"
        height="200"
        image={artist.profileImage || '/default-artist.jpg'}
        alt={artist.name}
      />
      <CardContent sx={{ flexGrow: 1 }}>
        <Typography gutterBottom variant="h5" component="div">
          {artist.name}
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
          {artist.specialization}
        </Typography>
        <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}>
          <Rating value={artist.rating || 0} readOnly precision={0.5} />
          <Typography variant="body2" color="text.secondary" sx={{ ml: 1 }}>
            ({artist.reviewCount || 0} reviews)
          </Typography>
        </Box>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
          Starting from ${artist.startingPrice}
        </Typography>
        <Button
          variant="contained"
          fullWidth
          onClick={() => navigate(`/artist/${artist.id}`)}
        >
          View Portfolio
        </Button>
      </CardContent>
    </Card>
  );
};

export default ArtistCard; 