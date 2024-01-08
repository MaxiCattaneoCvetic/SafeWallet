import { Inter } from "next/font/google";
import "./globals.css";
import NavBar from "@/components/navBar/NavBar";
const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: `Safe Wallet `,
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <link rel="icon" href="/faticon.svg" sizes="any" />
      <body className={inter.className} >
      <NavBar></NavBar>
        {children}
      </body>
    </html>
  );
}
