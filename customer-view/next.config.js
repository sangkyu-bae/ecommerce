/** @type {import('next').NextConfig} */
const path = require('path');
const nextConfig = {
  reactStrictMode: false,
  compiler:{
    styledComponents:true,
  },
  webpack: (config) => {
    config.resolve.alias['@'] = path.resolve(__dirname);
    return config;
  },
}

module.exports = nextConfig


