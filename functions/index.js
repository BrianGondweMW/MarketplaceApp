const { setGlobalOptions } = require("firebase-functions");
const { onRequest } = require("firebase-functions/https");
const axios = require("axios");
require("dotenv").config();

setGlobalOptions({ maxInstances: 10 });

exports.createPayChanguPayment = onRequest(async (req, res) => {
  try {
    const {
      amount,
      email,
      first_name,
      last_name
    } = req.body;

    if (!amount || !email || !first_name || !last_name) {
      return res.status(400).json({
        error: "Missing payment information"
      });
    }

    const response = await axios.post(
      "https://api.paychangu.com/payment",
      {
        amount: amount.toString(),
        currency: "MWK",
        email,
        first_name,
        last_name,

        // These must be valid HTTPS URLs for PayChangu.
        // They are not used to control the Android navigation.
        callback_url: "https://example.com/callback",
        return_url: "https://example.com/return",

        tx_ref: "marketplace_" + Date.now(),

        customization: {
          title: "Marketplace",
          description: "Product payment"
        }
      },
      {
        headers: {
          Authorization: `Bearer ${process.env.PAYCHANGU_SECRET_KEY}`,
          "Content-Type": "application/json"
        }
      }
    );

    console.log("PayChangu response:", response.data);

    return res.status(200).json(response.data);

  } catch (error) {
    console.error(
      "PayChangu error:",
      error.response?.data || error.message
    );

    return res.status(500).json({
      error: "Payment initialization failed"
    });
  }
});