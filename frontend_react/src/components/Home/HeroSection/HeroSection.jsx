import './HeroSection.css';
const HeroSection = () => {
    return (

        <div className="container-fluid position-relative p-0 section-wrapper-hero">
            <div className="img-container position-relative">
                <img className="img-stretch" src="/assets/home/atletismo.jpg" alt="" />
                <div className="gradient-overlay position-absolute w-100"></div>
            </div>
            <div className="position-absolute top-50 start-50 translate-middle text-center w-100">
                <img src="/assets/logo_3.png" alt="" className="logo" />
                <h1 className="eslogan">Alcanza tu Zénit</h1>
            </div>
        </div>

    );
};

export default HeroSection;