const http = require('http');
const httpProxy = require('http-proxy');
const { 
    URL_PROXY, 
    PORT_PROXY, 
    URL_SPRING_MAIN, 
    PORT_SPRING_MAIN, 
    URL_SPRING_PAYMENTS, 
    PORT_SPRING_PAYMENTS,
    URL_EXPRESS_NOTIFICATIONS, 
    PORT_EXPRESS_NOTIFICATIONS 
} = require('./config');

const proxy = httpProxy.createProxyServer({
    changeOrigin: true,
    secure: false
});

const routes = {
    '/api/payment': {
        target: URL_SPRING_PAYMENTS,
        port: PORT_SPRING_PAYMENTS
    },
    '/api/main': {
        target: URL_SPRING_MAIN,
        port: PORT_SPRING_MAIN
    },
    '/api/notifications': {
        target: URL_EXPRESS_NOTIFICATIONS,
        port: PORT_EXPRESS_NOTIFICATIONS
    }
};

proxy.on('proxyReq', (proxyReq, req, res) => {
    const route = Object.keys(routes).find(path => req.url.startsWith(path));
    const targetConfig = routes[route];
    
    proxyReq.setHeader('origin', 'http://localhost:4000');
    proxyReq.setHeader('host', `localhost:${targetConfig.port}`);
});

proxy.on('proxyRes', (proxyRes, req, res) => {
    delete proxyRes.headers['access-control-allow-origin'];
    proxyRes.headers['access-control-allow-origin'] = 'http://localhost:5173';
});

const server = http.createServer((req, res) => {
    if (req.method === 'OPTIONS') {
        res.setHeader('Access-Control-Allow-Origin', 'http://localhost:5173');
        res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, PATCH, OPTIONS');
        res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');
        res.setHeader('Access-Control-Allow-Credentials', 'true');
        res.writeHead(200);
        res.end();
        return;
    }

    const route = Object.keys(routes).find(path => req.url.startsWith(path));
    if (route) {
        console.log(`Redirigiendo a ${routes[route].target}`);
        proxy.web(req, res, { target: routes[route].target });
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Not Found');
    }
});

server.listen(PORT_PROXY, () => {
    console.log('Proxy server running at ' + URL_PROXY);
});
