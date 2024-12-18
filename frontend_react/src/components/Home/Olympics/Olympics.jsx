import'./olympics.css';
const Olympics = () => {
    return (

        <div className="landing-section-olympics d-flex">
            <div className="carousel-section col-md-6 p-0">
                <div className="anillos-div">
                    <img src="/assets/anillos.png" alt="" className="anillos"/>
                </div>        
                <img src="/assets/home/olympics.webp" alt="" className="d-block w-100 h-100 object-fit-cover"/>
            </div>

            <div className="text-section col-md-6 d-flex flex-column justify-content-center px-4">
                <div className="text-container">
                    <p className="fw-bold text-center">CREAMOS LEYENDAS</p>
                </div>
            </div>
        </div>

    );
};

export default Olympics;