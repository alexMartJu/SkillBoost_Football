FROM node:18-alpine

WORKDIR /app

COPY package.json package-lock.json ./
COPY tsconfig.json ./
RUN npm install

COPY prisma ./prisma
RUN npx prisma generate

COPY . .

EXPOSE 3030

CMD ["npm", "run", "dev"]