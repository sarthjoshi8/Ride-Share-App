const Ride = require('../models/Ride');

const FARE_PER_KM = 12; // INR per km base rate

exports.requestRide = async (req, res) => {
  try {
    const { originLat, originLng, destLat, destLng, originName, destName } = req.body;
    const distanceKm = calculateDistance(originLat, originLng, destLat, destLng);
    const fare = Math.round(FARE_PER_KM * distanceKm + 30); // base fare + per km

    const ride = await Ride.create({
      passenger: req.user.id,
      origin: { lat: originLat, lng: originLng, name: originName },
      destination: { lat: destLat, lng: destLng, name: destName },
      distanceKm: parseFloat(distanceKm.toFixed(2)),
      fare,
    });

    // Emit via Socket.io for real-time driver matching
    req.io.emit('new_ride_request', { rideId: ride._id, origin: ride.origin, destination: ride.destination });
    res.status(201).json(ride);
  } catch (err) { res.status(500).json({ message: err.message }); }
};

exports.getRideHistory = async (req, res) => {
  try {
    const rides = await Ride.find({ passenger: req.user.id }).sort({ createdAt: -1 }).limit(50);
    res.json(rides);
  } catch (err) { res.status(500).json({ message: err.message }); }
};

function calculateDistance(lat1, lon1, lat2, lon2) {
  const R = 6371;
  const dLat = deg2rad(lat2 - lat1);
  const dLon = deg2rad(lon2 - lon1);
  const a = Math.sin(dLat/2)**2 + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2)**2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
}

function deg2rad(deg) { return deg * (Math.PI / 180); }
