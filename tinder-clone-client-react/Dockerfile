FROM node:20.11.0-alpine
WORKDIR /react-tinder-clone/
COPY public/ /react-tinder-clone/public
COPY src/ /react-tinder-clone/src
COPY package.json /react-tinder-clone/
RUN npm install
CMD ["npm", "start"]