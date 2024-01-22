// next.config.js

/** @type {import('next').NextConfig} */
const nextConfig = {
  async headers() {
    return [
      {
        // matching all routes on your backend
        source: "/:path*", // Esto coincidirá con cualquier ruta en tu backend
        headers: [
          { key: "Access-Control-Allow-Credentials", value: "false" },
          { key: "Access-Control-Allow-Origin", value: "*" },
          {
            key: "Access-Control-Allow-Methods",
            value: "GET,DELETE,PATCH,POST,PUT,OPTIONS",
          },
          {
            key: "Access-Control-Allow-Headers",
            value:
              "X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version, Authorization, Access-Control-Allow-Origin, Access-Control-Allow-Headers, Origin, X-Requested-With"
          },
        ]
      },
    ];
  },
  // Otras configuraciones de Next.js si las tienes
};

module.exports = nextConfig;
