# microshop
micro service for mini shop

### Setup local evironment
* Setup permission in the first time
    ```shell script
    sudo chmod +x mongo/scripts/setup.sh
    ```
* Run docker
    ```shell script
    cd  docker
    docker-compose up -d
    ```

* Setup mongodb replicate
    ```shell script
    docker exec mongo0 /scripts/setup.sh
    ```
* Check kafka connect plugins that have mongodb connect plugin
    ```shell script
    curl localhost:8083/connector-plugins
    ```

* Create kafka connect mongo source to sync mongodb to kafka
    ```shell script
    curl -X POST -H "Content-Type: application/json" --data '{"name": "mongo-source","config": {"tasks.max":"1","connector.class":"com.mongodb.kafka.connect.MongoSourceConnector","connection.uri":"mongodb://mongo0:27017,mongo1:27017,mongo2:27017","topic.prefix":"dev","database":"microshop","collection":"item", "publish.full.document.only":"true", "key.converter": "org.apache.kafka.connect.json.JsonConverter", "value.converter": "org.apache.kafka.connect.json.JsonConverter", "value.converter.schemas.enable": "false"}}' http://localhost:8083/connectors -w "\n"

    # check kafka connect mongodb status
    curl localhost:8083/connectors/mongo-source/status
    ```
* Consume Kafka topic to check kafka connect mongo status
    ```shell script
    docker run --tty --network ms_kafka confluentinc/cp-kafkacat kafkacat -b kafka:29092 -C -t dev.microshop.item -p 0
    ```

* Create kafka connect elasticsearch source to sync from kafka topic to elasticsearch
    ```shell script
    curl -X POST -H "Content-Type: application/json" --data '{"name": "elasticsearch-sink","config": {"tasks.max":"1","connector.class":"io.confluent.connect.elasticsearch.ElasticsearchSinkConnector","connection.url":"http://elasticsearch:9200","topics": "dev.microshop.item","key.converter": "org.apache.kafka.connect.json.JsonConverter", "key.converter.schemas.enable":"false", "value.converter": "org.apache.kafka.connect.json.JsonConverter", "value.converter.schemas.enable": "false","schema.ignore": "true","key.ignore": "true", "behavior.on.malformed.documents":"warn", "type.name": "_doc","name": "elasticsearch-sink"}}' http://localhost:8083/connectors -w "\n"

    # check kafka connect elasticsearch status
    curl localhost:8083/connectors/elasticsearch-sink/status
    ```

### Run service
#### Item service
* Run service
    ```shell script
    cd item-service
    sh gradlew build :item-api:bootRun
    ```
* Go [swagger-ui](http:localhost:9090/swagger-ui.html) to check API

* Consume Kafka topic to check kafka connect mongo status
    ```shell script
    docker run --tty --network ms_kafka confluentinc/cp-kafkacat kafkacat -b kafka:29092 -C -t dev.microshop.item -p 0
    ```

#### Cart service
* Run service
    ```shell script
    cd cart-service
    sh gradlew build :cart-api:bootRun
    ```
* Go [swagger-ui](http:localhost:9091/swagger-ui.html) to check API
