
import NavBar from "../components/navBar/NavBar";
import HomePage from "../components/home/Homepage";
import Footer from "@/components/footer/Footer";

export default function Home() {
  return (
    <>
      <main className="main">
        <HomePage />
      </main>
      <Footer />
    </>
  );
}
