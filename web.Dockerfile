FROM node:lts-alpine AS builder

WORKDIR /usr/app
COPY web/weather-check-ui /usr/app
RUN npm install && npm run build

FROM nginx:mainline-alpine3.18-slim

RUN rm -rf /etc/nginx/conf.d/default.conf
COPY --from=builder /usr/app/conf /etc/nginx/conf.d/

COPY --from=builder /usr/app/dist /usr/share/nginx/html