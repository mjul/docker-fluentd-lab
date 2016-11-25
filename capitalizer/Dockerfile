FROM clojure:alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Download deps only when project.clj changes
COPY project.clj /usr/src/app/
RUN lein deps

COPY . /usr/src/app
CMD ["lein", "run"]
