const mongoose = require('mongoose');

const rideSchema = new mongoose.Schema({
  passenger: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
  driver: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  origin: { lat: Number, lng: Number, name: String },
  destination: { lat: Number, lng: Number, name: String },
  status: {
    type: String,
    enum: ['SEARCHING', 'MATCHED', 'DRIVER_ARRIVING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'],
    default: 'SEARCHING',
  },
  fare: { type: Number, default: 0 },
  distanceKm: Number,
  durationMin: Number,
  rating: { byPassenger: Number, byDriver: Number },
  paymentStatus: { type: String, enum: ['PENDING', 'PAID'], default: 'PENDING' },
  razorpayOrderId: String,
}, { timestamps: true });

module.exports = mongoose.model('Ride', rideSchema);
