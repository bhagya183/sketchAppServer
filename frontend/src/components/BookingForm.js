import React, { useState } from 'react';
import {
  Box,
  TextField,
  Button,
  Typography,
  Grid,
  MenuItem,
  Alert,
  CircularProgress,
} from '@mui/material';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import FileUpload from './FileUpload';

const BookingForm = ({ artist, onSubmit, loading }) => {
  const [formData, setFormData] = useState({
    sketchType: '',
    description: '',
    deadline: null,
    referenceImage: null,
  });
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    // Clear error when field is modified
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: '',
      }));
    }
  };

  const handleDateChange = (date) => {
    setFormData((prev) => ({
      ...prev,
      deadline: date,
    }));
    if (errors.deadline) {
      setErrors((prev) => ({
        ...prev,
        deadline: '',
      }));
    }
  };

  const handleFileSelect = (fileData) => {
    setFormData((prev) => ({
      ...prev,
      referenceImage: fileData,
    }));
  };

  const validateForm = () => {
    const newErrors = {};
    if (!formData.sketchType) {
      newErrors.sketchType = 'Please select a sketch type';
    }
    if (!formData.description) {
      newErrors.description = 'Please provide a description';
    }
    if (!formData.deadline) {
      newErrors.deadline = 'Please select a deadline';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      onSubmit(formData);
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <TextField
            select
            fullWidth
            label="Sketch Type"
            name="sketchType"
            value={formData.sketchType}
            onChange={handleChange}
            error={!!errors.sketchType}
            helperText={errors.sketchType}
          >
            {artist.sketchTypes?.map((type) => (
              <MenuItem key={type} value={type}>
                {type}
              </MenuItem>
            ))}
          </TextField>
        </Grid>

        <Grid item xs={12}>
          <TextField
            fullWidth
            multiline
            rows={4}
            label="Description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            error={!!errors.description}
            helperText={errors.description}
            placeholder="Describe your sketch requirements..."
          />
        </Grid>

        <Grid item xs={12}>
          <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DatePicker
              label="Deadline"
              value={formData.deadline}
              onChange={handleDateChange}
              renderInput={(params) => (
                <TextField
                  {...params}
                  fullWidth
                  error={!!errors.deadline}
                  helperText={errors.deadline}
                />
              )}
              minDate={new Date()}
            />
          </LocalizationProvider>
        </Grid>

        <Grid item xs={12}>
          <Typography variant="subtitle1" gutterBottom>
            Reference Image (Optional)
          </Typography>
          <FileUpload
            onFileSelect={handleFileSelect}
            accept="image/*"
            label="Upload Reference Image"
          />
        </Grid>

        <Grid item xs={12}>
          <Button
            type="submit"
            variant="contained"
            fullWidth
            size="large"
            disabled={loading}
          >
            {loading ? <CircularProgress size={24} /> : 'Book Now'}
          </Button>
        </Grid>
      </Grid>
    </Box>
  );
};

export default BookingForm; 