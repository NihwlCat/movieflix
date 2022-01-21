import LoginForm from './components';
import './style.css';
import homeImage from 'core/images/home-image.svg';

const Home = () => {
    return <section className="home-section">
        <div className="home-content">
            <h2>MovieFlix</h2>
            <p>Diga o que você achou do seu filme favorito</p>
            <img alt="Imagem Principal" src={homeImage}></img>
        </div>
        <LoginForm/>
    </section>
}

export default Home;