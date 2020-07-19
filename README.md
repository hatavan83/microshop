# microshop
micro service for mini shop

### Setup local evironment
* Run docker
    ```shell script
    cd  docker
    docker-compose up -d
    ```
* Setup mongodb replicate
    ```shell script
    docker exec mongo-0.mongo /scripts/setup.sh
    ```
* Create kafka connect mongo source to sync mongodb to kafka
    ```shell script
    curl -X POST -H "Content-Type: application/json" --data '{"name": "mongo-source","config": {"tasks.max":"1","connector.class":"com.mongodb.kafka.connect.MongoSourceConnector","connection.uri":"mongodb://root:root@mongo-0.mongo:27017,mongo-1.mongo:27017","topic.prefix":"alpha","database":"microshop","collection":"items"}}' http://localhost:8083/connectors -w "\n"
    ```
 
 * Check connector status
    ```shell script
    curl localhost:8083/connectors/mongo-source/status
    ```

* Consume Kafka to check kafka connect status
    ```shell script
    docker run --tty --network microshop confluentinc/cp-kafkacat kafkacat -b kafka:29092 -C -t dev.microshop.items -p 0
    ```