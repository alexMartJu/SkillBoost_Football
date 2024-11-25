import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './components/home';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Router>
      <header>
      </header>
      <main>
        <Routes>
          <Route path="/" element={<Home />} />
        
        </Routes>
      </main>
      <footer>
      </footer>
    </Router>
      
    </>
  )
}

export default App
