import Graphics from './Graphics/Graphics';
import './GraphicSection.css';

const GraphicSection = () => {

    return (

        <main className="graph-main ms-5">
            <div className="section-graph d-flex">
                <div className="landing-section d-flex">
                    <div className="carousel-section col-md-6 p-0">
                        <div className="">
                            <Graphics />
                        </div>
                    </div>
                </div>

                <div className="text-section col-md-6 d-flex flex-column justify-content-center px-4">
                    <div className="text-container-graph">
                        <p className="fst-italic ms-5">ANALISIS DE RENDIMIENTO COMPLETO</p>
                    </div>
                    <p className="text-end fs-3 fst-italic me-5 pe-4">"Sigue el progreso del próximo campeón"</p>
                </div>
            </div>
        </main>
    );




};

export default GraphicSection;