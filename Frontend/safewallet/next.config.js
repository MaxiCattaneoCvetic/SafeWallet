/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
		return [
			{
				source: '/user/:path*',
				destination: 'http://localhost:9090/*/:path*', // Cambia esto a tu ruta y dominio de destino
			},
		]
	},
}

module.exports = nextConfig
